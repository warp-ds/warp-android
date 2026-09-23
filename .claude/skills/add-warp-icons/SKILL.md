---
name: add-warp-icons
description: Adds new Warp icons to this Android codebase in a single pass. Handles SVG → Android VectorDrawable conversion, alphabetical insertion into WarpIcons.kt and IconScreen.kt, snapshot test count + letter-group updates, and content descriptions across all 5 locales (en/nb/sv/da/fi). Trigger when the user says "add warp icons", "import warp icons", or references a Jira ticket (like FEP-XXX) with new icon assets.
---

# add-warp-icons

Runs the full pipeline for adding new Warp icons. Follow these steps in order; do NOT skip verification. Ask the user about the commit up front (Step 1) so the rest of the run is uninterrupted — never commit unprompted.

## Step 1 — collect inputs

You need, per icon:
1. An SVG source (24×24 canvas, stroke-only Phosphor-style — that's what Warp uses)
2. A camelCase property name (e.g. `chartPieSlice`)
3. An English content description for accessibility (e.g. "A pie chart with a highlighted slice")
4. Translations for nb, sv, da, fi

**Two sources to gather, independently:**

**Descriptions/keywords — use the Jira MCP when there's a ticket.** If the user references a ticket (e.g. `FEP-153`), use `ToolSearch` with `atlassian` to load `mcp__atlassian__getJiraIssue` and fetch the ticket body. Tickets from design typically embed per-icon `altText` + `keywords` blocks — parse them out so the user doesn't have to retype. If the MCP isn't connected or the ticket body lacks descriptions, ask the user for English descriptions.

**SVG files — download from Jira via `.netrc`, else ask for a local path.** The Atlassian MCP does *not* expose attachment binary downloads, but a direct authenticated `curl` does. Preferred path:

1. If the ticket has attachments (from the `getJiraIssue` call above), check for a `~/.netrc` entry for `api.atlassian.com`:
   ```bash
   grep -q "^machine api.atlassian.com" ~/.netrc 2>/dev/null && echo OK
   ```
   The entry should look like:
   ```
   machine api.atlassian.com
     login <atlassian-email>
     password <atlassian-api-token>
   ```
   with `chmod 600 ~/.netrc`.
2. If present, download the attachment. **Do this as two separate Bash calls, not one with `$(mktemp)` inline** — the harness cannot statically analyze a URL sitting behind a `$(...)` substitution, so a `curl $(mktemp)/… https://…` invocation triggers an ask prompt even when the URL is on an allowlist. Instead:

   ```bash
   # 1) create a random temp DIR (not file) in one call — copy the printed path out of the output
   mktemp -d
   # → /var/folders/…/tmp.RANDOM

   # 2) curl to a fixed filename inside it — literal path, so the URL stays statically analyzable
   curl --netrc -sSL -o /var/folders/…/tmp.RANDOM/attachment.zip \
     https://api.atlassian.com/ex/jira/<cloudId>/rest/api/3/attachment/content/<attachmentId>
   ```

   The random component lives in the directory name; the file gets a clean `.zip` extension (which `wire_up.py` requires). Verify with `file` that it's a real zip/svg and that the byte count matches the metadata `size` from the ticket. **Do not unzip in shell** — pass the `.zip` path straight to `wire_up.py` as `svg_dir` and it will extract into a tempdir itself. This avoids the sandbox friction of `cd`ing into `/var/folders/...` (which is blocked) and the manual `__MACOSX/` / `.DS_Store` exclusion dance.

If `.netrc` isn't configured, **do not prompt for a token inline** — that would put it in the transcript. Instead, tell the user the exact `.netrc` block to add and offer the local-path fallback below. Point them at [id.atlassian.com/manage-profile/security/api-tokens](https://id.atlassian.com/manage-profile/security/api-tokens) for a classic API token (no scopes to pick — Basic auth uses the whole token). The file usually doesn't exist yet — have the user create it themselves with `touch ~/.netrc && chmod 600 ~/.netrc && open -e ~/.netrc`, paste the block, save; then continue.

**Fallback — user-supplied local path.** If there's no ticket, no attachments, or the user prefers, ask them where the SVGs are. `wire_up.py` accepts any of:

- **A directory of `.svg` files** — used as-is.
- **A wrapper directory** containing a single subdirectory of SVGs (macOS auto-unzip pattern) — the script descends into it automatically.
- **A `.zip` file, still zipped** — the script extracts into a tempdir and applies the wrapper-descent rule above.
- **Inline pasted SVG source** — write each to a temp file yourself and pass the tempdir.

Once you have the source path, list its `.svg` files and confirm the set with the user before wiring anything up. When listing in Bash, use absolute paths (`find /path -name '*.svg'`) — do not `cd` into `/var/folders/...` tempdirs, the sandbox blocks it.

For any source, if English descriptions aren't provided, ask for them. For Nordic translations (nb/sv/da/fi), it's fine to draft them in the style of existing entries (concise, describes the visual — see `warp/src/main/res/values-{nb,sv,da,fi}/strings.xml` for tone). Flag drafted translations in the final report so the user knows to review.

If any input is missing (SVGs, names, or English descriptions), ask before proceeding. Translations can be drafted.

**Then, before touching any files, ask about the commit disposition.** Doing this now — while you're already asking the user questions — means the run itself won't stop for input again. First check whether the current branch tracks a remote so you know whether to offer the "commit + push" option:

```bash
git rev-parse --abbrev-ref --symbolic-full-name @{u} 2>&1
```

Then ask via `AskUserQuestion`. Recommend **Commit now** (mark it `(Recommended)` in the label and list it first), but keep the "leave it" option available so the user can end the skill at the end and take over. Do not add caveats about auto-drafted translations in the question body.

1. **Commit now (Recommended)** — at Step 6, stage the new/changed files and commit with a message prefixed by the Jira ticket, e.g. `FEP-153: Added N new Warp icons`. Follow the repo's commit style (look at recent commits — they use `<TICKET>: <short imperative>` with no body for icon additions; keep it that way). Include the `Co-Authored-By: Claude Opus 4.7 <noreply@anthropic.com>` trailer per the harness convention.
2. **Commit + push** — same as above, then `git push`. Only offer this if the branch tracks a remote.
3. **Leave it** — at Step 6, the skill just reports and ends; user commits themselves.

Remember the answer — you'll execute it in Step 6.

## Step 2 — verify no name collisions

Before touching files, grep `WarpIcons.kt` for each new camelCase name to catch collisions with existing icons:

```bash
grep -nE "val (crosshair|chartBar|...)" warp/src/main/java/com/schibsted/nmp/warp/theme/WarpIcons.kt
```

Stop and ask if any collide.

## Step 3 — run the conversion + wiring script

The heavy lifting is done by `wire_up.py` in this skill dir. It:

1. Converts each SVG to Android VectorDrawable format matching the existing Warp style (24dp, `strokeWidth="1.5"`, `strokeColor="#1B1B1F"` or `#000000` matching source, `fillColor="#00000000"`, round joins/caps, optional `<clip-path>` wrapper if the source SVG has a `<g clip-path>`)
2. Writes drawables to `warp/src/main/res/drawable/warp_<lowercase>.xml`
3. Inserts `val <camelCase>: WarpIconResource { … }` blocks alphabetically into `warp/src/main/java/com/schibsted/nmp/warp/theme/WarpIcons.kt`
4. Adds `CreateIcon(icons.<camelCase>, size)` to `app/src/main/java/com/schibsted/nmp/warpapp/ui/IconScreen.kt` in alphabetical order
5. Adds each icon to the correct letter-group in `snapshot/icons/src/test/java/com/schibsted/snapshot/WarpIconTest.kt` AND bumps the `warp_icon_count` assertion by the number of icons added
6. Inserts translated strings alphabetically into all 5 `warp/src/main/res/values{,-nb,-sv,-da,-fi}/strings.xml` files

Invoke it with a small YAML or JSON manifest as stdin. `svg_dir` is whatever path you resolved in Step 1 — a directory, a wrapper directory, or a `.zip` file; the script normalises all three:

```bash
python3 .claude/skills/add-warp-icons/wire_up.py <<'JSON'
{
  "svg_dir": "<absolute path to the directory containing the .svg files>",
  "icons": [
    {
      "name": "chartBar",
      "en": "A bar chart with ascending bars",
      "nb": "Stolpediagram med stigende stolper",
      "sv": "Stapeldiagram med stigande staplar",
      "da": "Søjlediagram med stigende søjler",
      "fi": "Pylväsdiagrammi nousevilla pylväillä"
    }
  ]
}
JSON
```

(JSON is the safe default — no PyYAML dependency. YAML works if PyYAML is installed.)

The script is idempotent-adjacent: if an entry already exists (drawable file exists, property exists, string exists), it skips. It errors loudly on ambiguous anchors rather than guessing.

## Step 4 — verify

Run these three, in parallel:

```bash
./gradlew :warp:compileDebugKotlin
./gradlew :app:compileDebugKotlin
./gradlew :snapshot:snapshot-icons:testDebugUnitTest --tests "com.schibsted.snapshot.WarpIconTest.warp_icon_count*"
```

All must pass. If the count test fails, the script's count-bump math is wrong — check `warp_icon_count` in `WarpIconTest.kt` against `grep -cE 'val [a-zA-Z]+: (WarpIconResource|WarpTaxonomyIconResource)$' WarpIcons.kt`.

## Step 5 — check the lint threshold

For each new drawable, if `android:pathData` exceeds **800 chars**, the `VectorPath` lint check will warn. Report which icons (if any) crossed the threshold and offer to split the path or run avocado. Don't do it unprompted — the icons still render fine.

## Step 6 — report and execute the pre-selected commit disposition

Report:
- Which icons were added
- Which translations were auto-drafted (needs review) vs user-supplied
- Which drawables tripped the 800-char VectorPath lint
- Confirmation that all three gradle tasks passed

Then act on the disposition the user chose in Step 1 — **do not re-ask**:

- **Commit now** or **Commit + push**:
  - Run `git status` first to confirm the diff is only the icon additions (no stray files).
  - Stage specific paths, not `git add -A`: the drawable dir, `WarpIcons.kt`, `IconScreen.kt`, `WarpIconTest.kt`, the 5 `strings.xml` files.
  - Commit with the ticket-prefixed message and `Co-Authored-By` trailer (as described in Step 1).
  - If **Commit + push** was chosen, follow with `git push` (never force).
  - Never `--no-verify`; if pre-commit hooks fail, fix and re-commit (don't amend).
- **Leave it**: do nothing further. Skill ends after the report.

If the disposition wasn't captured in Step 1 (e.g. skill was re-entered mid-flow), fall back to asking now with the same three options.

## Convention reference

- Alphabetical order everywhere (WarpIcons.kt, IconScreen.kt, WarpIconTest.kt letter groups, strings.xml). The existing files aren't perfectly sorted; match the local alphabetical cluster, don't reorder existing entries.
- Property naming: SVG file `ChartPieSlice.svg` → property `chartPieSlice` → drawable `warp_chartpieslice.xml` → string key `chartpieslice`.
- Never emit `WarpTaxonomyIconResource` or `WarpBrandIconResource` — new icons are always `WarpIconResource` unless the user explicitly asks otherwise (those types have identifiers that need coordination).
- Translation tone: concise, visual description (e.g. "Side view of a bicycle"), matches existing entries in each locale file.
