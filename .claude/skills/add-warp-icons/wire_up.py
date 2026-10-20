#!/usr/bin/env python3
"""Wire up new Warp icons across drawables, WarpIcons.kt, IconScreen.kt, snapshot test, and 5 locale strings.xml files.

Manifest format (JSON on stdin, or --manifest path; YAML also works if PyYAML installed):

    # exactly one of these two:
    svg_dir: /absolute/path/to/svgs        # a dir of .svg, a .zip file, or a wrapper dir
    attachment_url: https://api.atlassian.com/ex/jira/<cloudId>/rest/api/3/attachment/content/<id>

    icons:
      - name: chartBar           # camelCase property name
        svg: ChartBar.svg        # optional; defaults to <Name>.svg
        en: A bar chart with ascending bars
        nb: Stolpediagram med stigende stolper
        sv: Stapeldiagram med stigande staplar
        da: Søjlediagram med stigende søjler
        fi: Pylväsdiagrammi nousevilla pylväillä

`svg_dir` accepts three shapes and normalises them:
  - a directory of .svg files                                → used as-is
  - a directory whose only contents are one subdirectory     → descends into it
    (macOS unzip pattern, e.g. wrapper/ → wrapper/Icons.../*.svg)
  - a .zip file                                              → extracted to a tempdir
    then the wrapper-descent rule above is re-applied

`attachment_url` downloads the given Jira attachment via ~/.netrc credentials
(no shell curl needed) and treats the result like a .zip svg_dir.
"""
from __future__ import annotations

import argparse
import json
import netrc
import re
import sys
import tempfile
import zipfile
from base64 import b64encode
from dataclasses import dataclass
from pathlib import Path
from urllib.error import HTTPError, URLError
from urllib.request import Request, urlopen

try:
    import yaml  # type: ignore
    _HAS_YAML = True
except ImportError:
    _HAS_YAML = False


def _parse_manifest(text: str) -> dict:
    """Accept JSON always, YAML if PyYAML installed."""
    text = text.strip()
    if text.startswith("{") or text.startswith("["):
        return json.loads(text)
    if _HAS_YAML:
        return yaml.safe_load(text)
    sys.exit(
        "Manifest looks like YAML but PyYAML isn't installed.\n"
        "Either install with `pip3 install --user pyyaml`, or use JSON."
    )


REPO = Path(__file__).resolve().parents[3]
WARP_ICONS_KT = REPO / "warp/src/main/java/com/schibsted/nmp/warp/theme/WarpIcons.kt"
ICON_SCREEN_KT = REPO / "app/src/main/java/com/schibsted/nmp/warpapp/ui/IconScreen.kt"
SNAPSHOT_TEST_KT = REPO / "snapshot/icons/src/test/java/com/schibsted/snapshot/WarpIconTest.kt"
DRAWABLE_DIR = REPO / "warp/src/main/res/drawable"
RES_DIR = REPO / "warp/src/main/res"
LOCALES = ["", "-nb", "-sv", "-da", "-fi"]


@dataclass
class Icon:
    name: str  # camelCase e.g. chartBar
    svg: Path
    en: str
    nb: str
    sv: str
    da: str
    fi: str

    @property
    def key(self) -> str:
        return self.name.lower()

    @property
    def drawable_file(self) -> Path:
        return DRAWABLE_DIR / f"warp_{self.key}.xml"

    def translation(self, locale: str) -> str:
        return {"": self.en, "-nb": self.nb, "-sv": self.sv, "-da": self.da, "-fi": self.fi}[locale]


# --- SVG → VectorDrawable ------------------------------------------------

NUM_RE = re.compile(r"-?\d*\.?\d+(?:e[-+]?\d+)?")
CMD_RE = re.compile(r"[MmLlHhVvCcSsQqTtAaZz]")
ARGCOUNTS = {
    "M": 2, "m": 2, "L": 2, "l": 2, "H": 1, "h": 1, "V": 1, "v": 1,
    "C": 6, "c": 6, "S": 4, "s": 4, "Q": 4, "q": 4, "T": 2, "t": 2,
    "A": 7, "a": 7, "Z": 0, "z": 0,
}


def _round(s: str) -> str:
    try:
        r = round(float(s), 3)
    except ValueError:
        return s
    out = f"{r:.3f}".rstrip("0").rstrip(".")
    if out in ("", "-", "-0"):
        return "0"
    return out


def _convert_path_data(d: str) -> str:
    """Round numbers to 3 decimals and format like existing Warp drawables."""
    tokens = []
    i = 0
    while i < len(d):
        c = d[i]
        if c.isspace() or c == ",":
            i += 1
            continue
        if CMD_RE.match(c):
            tokens.append(("cmd", c))
            i += 1
            continue
        m = NUM_RE.match(d, i)
        if m:
            tokens.append(("num", _round(m.group(0))))
            i = m.end()
            continue
        i += 1

    pieces = []
    cur_cmd = None
    cur_args: list[str] = []

    def flush():
        nonlocal cur_cmd, cur_args
        if cur_cmd is None:
            return
        n = ARGCOUNTS[cur_cmd]
        if n == 0:
            pieces.append(cur_cmd)
        else:
            first = True
            idx = 0
            while idx < len(cur_args):
                chunk = cur_args[idx:idx + n]
                if first:
                    prefix = cur_cmd
                    first = False
                else:
                    if cur_cmd == "M":
                        prefix = "L"
                    elif cur_cmd == "m":
                        prefix = "l"
                    else:
                        prefix = ""
                if cur_cmd in ("H", "h", "V", "v"):
                    body = chunk[0]
                else:
                    pairs = []
                    for j in range(0, len(chunk), 2):
                        if j + 1 < len(chunk):
                            pairs.append(f"{chunk[j]},{chunk[j+1]}")
                        else:
                            pairs.append(chunk[j])
                    body = " ".join(pairs)
                if prefix:
                    pieces.append(prefix + body)
                else:
                    pieces.append(" " + body)
                idx += n
        cur_cmd = None
        cur_args = []

    for kind, val in tokens:
        if kind == "cmd":
            flush()
            cur_cmd = val
        else:
            cur_args.append(val)
    flush()

    return "".join(pieces)


def _parse_svg(text: str):
    has_clip = "<g " in text or "<g>" in text
    paths = []
    # Match both self-closing <path .../> and paired <path ...>...</path>.
    total_tags = len(re.findall(r"<path\b", text))
    for m in re.finditer(r"<path\b([^>]*?)(?:/>|>)", text):
        attrs = m.group(1)
        d_m = re.search(r'\bd\s*=\s*"([^"]*)"', attrs)
        s_m = re.search(r'\bstroke\s*=\s*"([^"]*)"', attrs)
        if not d_m:
            continue
        stroke = s_m.group(1) if s_m else "#1B1B1F"
        paths.append((d_m.group(1), stroke))
    if len(paths) != total_tags:
        print(f"WARN: parsed {len(paths)} of {total_tags} <path> tags — some were skipped", file=sys.stderr)
    return paths, has_clip


def _normalize_stroke(color: str) -> str:
    c = color.strip().lower()
    if c in ("black", "#000", "#000000"):
        return "#000000"
    if c.startswith("#"):
        return "#" + c[1:].upper()
    return "#1B1B1F"


HEADER = ('<vector xmlns:android="http://schemas.android.com/apk/res/android"\n'
          '    android:width="24dp"\n'
          '    android:height="24dp"\n'
          '    android:viewportWidth="24"\n'
          '    android:viewportHeight="24">\n')
FOOTER = "</vector>\n"


def _build_drawable(paths, has_clip: bool) -> str:
    if has_clip:
        indent = "        "
        wrapper_open = ('  <group>\n'
                        '    <clip-path\n'
                        '        android:pathData="M0,0h24v24h-24z"/>\n')
        wrapper_close = "  </group>\n"
    else:
        indent = "      "
        wrapper_open = ""
        wrapper_close = ""

    path_blocks = []
    for d, stroke in paths:
        color = _normalize_stroke(stroke)
        prefix = "    " if has_clip else "  "
        path_blocks.append(
            f'{prefix}<path\n'
            f'{indent}android:pathData="{d}"\n'
            f'{indent}android:strokeLineJoin="round"\n'
            f'{indent}android:strokeWidth="1.5"\n'
            f'{indent}android:fillColor="#00000000"\n'
            f'{indent}android:strokeColor="{color}"\n'
            f'{indent}android:strokeLineCap="round"/>'
        )
    return HEADER + wrapper_open + "\n".join(path_blocks) + "\n" + wrapper_close + FOOTER


def convert_svg(icon: Icon) -> tuple[bool, int]:
    """Write the drawable. Returns (created, longest_path_chars)."""
    if icon.drawable_file.exists():
        # Idempotent: don't overwrite an existing drawable
        existing = icon.drawable_file.read_text()
        longest = max((len(m.group(1)) for m in re.finditer(r'android:pathData="([^"]*)"', existing)), default=0)
        return False, longest
    text = icon.svg.read_text()
    paths_raw, has_clip = _parse_svg(text)
    paths = [(_convert_path_data(d), s) for d, s in paths_raw]
    xml = _build_drawable(paths, has_clip)
    icon.drawable_file.write_text(xml)
    longest = max((len(d) for d, _ in paths), default=0)
    return True, longest


# --- WarpIcons.kt insertion ---------------------------------------------

PROP_RE = re.compile(
    r"^    val ([a-zA-Z]+): (WarpIconResource|WarpTaxonomyIconResource|WarpBrandIconResource)$",
    re.MULTILINE,
)


def insert_warp_icons_kt(icons: list[Icon]) -> int:
    text = WARP_ICONS_KT.read_text()
    lines = text.splitlines()

    # Existing properties, in file order, with their line numbers
    props: list[tuple[str, int]] = []
    for i, line in enumerate(lines):
        m = re.match(r"    val ([a-zA-Z]+): (WarpIconResource|WarpTaxonomyIconResource|WarpBrandIconResource)$", line)
        if m:
            props.append((m.group(1), i))

    inserted = 0
    for icon in icons:
        if any(name == icon.name for name, _ in props):
            continue  # already there
        # Find alphabetical insertion point
        target = None
        for name, idx in props:
            if name.lower() > icon.name.lower():
                target = idx
                break
        if target is None:
            # Append at end (before last WarpBrandIconResource cluster ideally, but keep simple)
            # Fall back to before the first WarpBrandIconResource
            for name, idx in props:
                # find first Brand
                line = lines[idx]
                if "WarpBrandIconResource" in line:
                    target = idx
                    break
            if target is None:
                target = len(lines) - 1  # before closing brace
        block = [
            f"    val {icon.name}: WarpIconResource",
            "        @Composable",
            "        get() = WarpIconResource(",
            f"            ImageVector.vectorResource(id = R.drawable.warp_{icon.key}),",
            f"            stringResource(R.string.{icon.key})",
            "        )",
            "",
        ]
        lines[target:target] = block
        # Rebuild props list with new offsets
        props = []
        for i, line in enumerate(lines):
            m = re.match(r"    val ([a-zA-Z]+): (WarpIconResource|WarpTaxonomyIconResource|WarpBrandIconResource)$", line)
            if m:
                props.append((m.group(1), i))
        inserted += 1

    WARP_ICONS_KT.write_text("\n".join(lines) + ("\n" if text.endswith("\n") else ""))
    return inserted


# --- IconScreen.kt insertion --------------------------------------------

def insert_icon_screen_kt(icons: list[Icon]) -> int:
    text = ICON_SCREEN_KT.read_text()
    lines = text.splitlines()

    entries: list[tuple[str, int]] = []
    for i, line in enumerate(lines):
        m = re.match(r"    CreateIcon\(icons\.([a-zA-Z]+), size\)", line)
        if m:
            entries.append((m.group(1), i))

    inserted = 0
    for icon in icons:
        if any(name == icon.name for name, _ in entries):
            continue
        target = None
        for name, idx in entries:
            if name.lower() > icon.name.lower():
                target = idx
                break
        if target is None:
            target = entries[-1][1] + 1 if entries else len(lines)
        lines.insert(target, f"    CreateIcon(icons.{icon.name}, size)")
        entries = []
        for i, line in enumerate(lines):
            m = re.match(r"    CreateIcon\(icons\.([a-zA-Z]+), size\)", line)
            if m:
                entries.append((m.group(1), i))
        inserted += 1

    ICON_SCREEN_KT.write_text("\n".join(lines) + ("\n" if text.endswith("\n") else ""))
    return inserted


# --- WarpIconTest.kt: bump count + add to letter groups -----------------

def update_snapshot_test(icons: list[Icon]) -> tuple[int, int]:
    text = SNAPSHOT_TEST_KT.read_text()

    warp_text = WARP_ICONS_KT.read_text()
    m = re.search(r"Assert\.assertEquals\((\d+),\s*count\)", text)
    if not m:
        raise RuntimeError("Could not find warp_icon_count assertion in WarpIconTest.kt")
    asserted = int(m.group(1))
    # Count actual WarpIconResource + WarpTaxonomyIconResource in WarpIcons.kt (matches test logic)
    actual = len(re.findall(
        r"^    val [a-zA-Z]+: (?:WarpIconResource|WarpTaxonomyIconResource)$",
        warp_text, re.MULTILINE,
    ))
    if actual != asserted:
        text = re.sub(r"(Assert\.assertEquals\()\d+(,\s*count\))", rf"\g<1>{actual}\g<2>", text, count=1)

    # Add each icon to its letter group. Letter group is warp_icons_<UPPER LETTER>().
    added_to_groups = 0
    for icon in icons:
        letter = icon.name[0].upper()
        # Locate the test function block
        func_re = re.compile(
            r"(fun warp_icons_" + letter + r"\(\)\s*\{[\s\S]*?listOf\(\n)([\s\S]*?)(\n\s*\)\s*\n\s*\}\s*\n\s*\})",
        )
        fm = func_re.search(text)
        if not fm:
            # No group for this letter — could add one, but keep simple and skip with a printed warning
            print(f"WARN: no warp_icons_{letter}() test group found; skipping test-group insert for {icon.name}", file=sys.stderr)
            continue
        head, body, tail = fm.group(1), fm.group(2), fm.group(3)
        # Skip if already there
        if re.search(r"\bicons\." + re.escape(icon.name) + r"\b", body):
            continue
        # Parse existing entries in body
        body_lines = body.split("\n")
        entries = []
        indent = "                    "
        for i, line in enumerate(body_lines):
            m = re.match(r"\s*icons\.([a-zA-Z]+),?\s*$", line)
            if m:
                entries.append((m.group(1), i, line))
                # infer indent from first hit
                indent = line[: len(line) - len(line.lstrip())]
        # Find alphabetical position
        target = None
        for name, i, _ in entries:
            if name.lower() > icon.name.lower():
                target = i
                break
        new_line = f"{indent}icons.{icon.name},"
        if target is None:
            # Append after the last entry. If the last entry has no trailing comma, add one.
            if entries:
                last_name, last_i, last_line = entries[-1]
                if not last_line.rstrip().endswith(","):
                    body_lines[last_i] = last_line.rstrip() + ","
                body_lines.insert(last_i + 1, new_line)
            else:
                body_lines.insert(0, new_line)
        else:
            body_lines.insert(target, new_line)
        new_body = "\n".join(body_lines)
        text = text[: fm.start()] + head + new_body + tail + text[fm.end():]
        added_to_groups += 1

    SNAPSHOT_TEST_KT.write_text(text)
    return actual, added_to_groups


# --- strings.xml insertion (5 locales) ----------------------------------

STRING_LINE = '    <string name="{key}">{val}</string>'


def insert_strings(icons: list[Icon]) -> dict[str, int]:
    stats = {}
    for locale in LOCALES:
        path = RES_DIR / f"values{locale}" / "strings.xml"
        text = path.read_text()
        lines = text.splitlines()

        # Existing string keys in file order
        key_re = re.compile(r'<string name="([^"]+)">')
        existing = []
        for i, line in enumerate(lines):
            m = key_re.search(line)
            if m:
                existing.append((m.group(1), i))
        existing_keys = {k for k, _ in existing}

        added = 0
        for icon in icons:
            if icon.key in existing_keys:
                continue
            val = _xml_escape(icon.translation(locale))
            new_line = STRING_LINE.format(key=icon.key, val=val)

            # Find alphabetical anchor: last existing key that's < icon.key AND close in prefix
            # Safer: find previous alphabetical neighbour among existing keys and insert after it.
            prev_idx = None
            for k, idx in existing:
                if k.lower() < icon.key:
                    prev_idx = idx
                else:
                    break  # existing keys not strictly alphabetical, but close-enough neighbour
            # If no previous, insert before first string
            if prev_idx is None and existing:
                target = existing[0][1]
            elif prev_idx is None:
                # No existing strings; put before </resources>
                target = next(i for i, l in enumerate(lines) if "</resources>" in l)
            else:
                target = prev_idx + 1

            lines.insert(target, new_line)
            existing = []
            for i, line in enumerate(lines):
                m = key_re.search(line)
                if m:
                    existing.append((m.group(1), i))
            existing_keys = {k for k, _ in existing}
            added += 1

        path.write_text("\n".join(lines) + ("\n" if text.endswith("\n") else ""))
        stats[locale or "en"] = added
    return stats


def _xml_escape(s: str) -> str:
    return (s.replace("&", "&amp;")
             .replace("<", "&lt;")
             .replace(">", "&gt;")
             .replace('"', "\\\""))


# --- main ----------------------------------------------------------------

_NETRC_HELP = """\
Missing ~/.netrc credentials for api.atlassian.com.

One-time setup:
  1. Get an Atlassian API token:
       https://id.atlassian.com/manage-profile/security/api-tokens
  2. touch ~/.netrc && chmod 600 ~/.netrc && open -e ~/.netrc
  3. Paste (replacing the placeholders):
        machine api.atlassian.com
          login <your-atlassian-email>
          password <the-token>
  4. Save and re-run.
"""


def _netrc_auth() -> tuple[str, str | None, str]:
    """Return (login, account, password) for api.atlassian.com or exit with setup help."""
    try:
        auth = netrc.netrc().authenticators("api.atlassian.com")
    except (FileNotFoundError, netrc.NetrcParseError):
        sys.exit(_NETRC_HELP)
    if not auth:
        sys.exit(_NETRC_HELP)
    return auth


def _download_attachment(url: str) -> Path:
    """Download a Jira attachment via ~/.netrc → path to a .zip in a tempdir."""
    auth = _netrc_auth()
    user, _, password = auth
    token = b64encode(f"{user}:{password}".encode()).decode()
    req = Request(url, headers={"Authorization": f"Basic {token}"})
    try:
        with urlopen(req) as resp:
            data = resp.read()
    except HTTPError as e:
        sys.exit(f"Attachment download failed: HTTP {e.code} {e.reason}\n  URL: {url}")
    except URLError as e:
        sys.exit(f"Attachment download failed: {e.reason}\n  URL: {url}")
    dest = Path(tempfile.mkdtemp(prefix="warp-icons-")) / "attachment.zip"
    dest.write_bytes(data)
    print(f"  downloaded {len(data)} bytes → {dest}")
    return dest


def _resolve_svg_dir(raw: Path) -> Path:
    """Normalise svg_dir: unpack .zip, descend a single-subdir wrapper.

    Extracted zips are placed in a tempdir owned by the OS — we don't clean up;
    the temp files are tiny and macOS purges /var/folders periodically.
    """
    if not raw.exists():
        sys.exit(f"svg_dir not found: {raw}")

    if raw.is_file():
        if raw.suffix.lower() != ".zip":
            sys.exit(f"svg_dir must be a directory or .zip file, got: {raw}")
        tmp = Path(tempfile.mkdtemp(prefix="warp-icons-"))
        with zipfile.ZipFile(raw) as zf:
            for member in zf.namelist():
                if member.startswith("__MACOSX/") or member.endswith(".DS_Store"):
                    continue
                zf.extract(member, tmp)
        raw = tmp

    # Descend a single-subdir wrapper (macOS unzip pattern) if there are no
    # SVGs at the top level.
    for _ in range(2):  # at most one level of nesting in practice
        svgs = list(raw.glob("*.svg"))
        if svgs:
            return raw
        subdirs = [p for p in raw.iterdir() if p.is_dir() and not p.name.startswith(".")]
        if len(subdirs) == 1:
            raw = subdirs[0]
            continue
        break

    if not list(raw.glob("*.svg")):
        sys.exit(f"No .svg files found in: {raw}")
    return raw


def load_manifest(path: Path | None) -> tuple[Path, list[Icon]]:
    raw = path.read_text() if path else sys.stdin.read()
    data = _parse_manifest(raw)
    if "attachment_url" in data and data["attachment_url"]:
        zip_path = _download_attachment(data["attachment_url"])
        svg_dir = _resolve_svg_dir(zip_path)
    elif "svg_dir" in data and data["svg_dir"]:
        svg_dir = _resolve_svg_dir(Path(data["svg_dir"]).expanduser())
    else:
        sys.exit("Manifest must have 'attachment_url' or 'svg_dir'.")
    icons = []
    for entry in data["icons"]:
        svg_name = entry.get("svg") or f"{entry['name'][0].upper() + entry['name'][1:]}.svg"
        icons.append(Icon(
            name=entry["name"],
            svg=svg_dir / svg_name,
            en=entry["en"],
            nb=entry["nb"],
            sv=entry["sv"],
            da=entry["da"],
            fi=entry["fi"],
        ))
    for ic in icons:
        if not ic.svg.exists():
            sys.exit(f"SVG not found: {ic.svg}")
    return svg_dir, icons


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--manifest", type=Path, help="Path to YAML manifest (else read stdin)")
    ap.add_argument("--check-netrc", action="store_true",
                    help="Verify ~/.netrc has api.atlassian.com credentials, then exit")
    args = ap.parse_args()

    if args.check_netrc:
        _netrc_auth()
        print("netrc: ok (api.atlassian.com credentials found)")
        return

    _, icons = load_manifest(args.manifest)

    print(f"→ Wiring {len(icons)} icon(s)")

    created = 0
    long_paths: list[tuple[str, int]] = []
    for icon in icons:
        made, longest = convert_svg(icon)
        created += int(made)
        if longest > 800:
            long_paths.append((icon.name, longest))
    print(f"  drawables: {created} created ({len(icons) - created} already existed)")

    n_props = insert_warp_icons_kt(icons)
    print(f"  WarpIcons.kt: +{n_props} property blocks")

    n_screen = insert_icon_screen_kt(icons)
    print(f"  IconScreen.kt: +{n_screen} entries")

    asserted_now, n_groups = update_snapshot_test(icons)
    print(f"  WarpIconTest.kt: count assertion now {asserted_now}, +{n_groups} letter-group entries")

    stats = insert_strings(icons)
    print(f"  strings.xml: +" + ", ".join(f"{k}={v}" for k, v in stats.items()))

    if long_paths:
        print("\nVectorPath lint (>800 chars):")
        for n, c in long_paths:
            print(f"  {n}: {c} chars")

    print("\nDone. Run to verify:")
    print("  ./gradlew :warp:compileDebugKotlin :app:compileDebugKotlin \\")
    print("    :snapshot:snapshot-icons:testDebugUnitTest --tests \"com.schibsted.snapshot.WarpIconTest.warp_icon_count*\"")


if __name__ == "__main__":
    main()
