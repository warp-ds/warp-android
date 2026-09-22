---
name: add-warp-icons
description: Adds new Warp icons to this Android codebase in a single pass. Handles SVG → Android VectorDrawable conversion, alphabetical insertion into WarpIcons.kt and IconScreen.kt, snapshot test count + letter-group updates, and content descriptions across all 5 locales (en/nb/sv/da/fi). Trigger when the user says "add warp icons", "import warp icons", or references a Jira ticket (like FEP-XXX) with new icon assets.
---

# add-warp-icons

Runs the full pipeline for adding new Warp icons. Follow these steps in order; do NOT skip verification. Ask the user about the commit at the end (Step 6) — never commit unprompted.

## Step 1 — collect inputs

You need, per icon:
1. An SVG source (24×24 canvas, stroke-only Phosphor-style — that's what Warp uses)
2. A camelCase property name (e.g. `chartPieSlice`)
3. An English content description for accessibility (e.g. "A pie chart with a highlighted slice")
4. Translations for nb, sv, da, fi

**Two sources to gather, independently:**

**Descriptions/keywords — use the Jira MCP when there's a ticket.** If the user references a ticket (e.g. `FEP-153`), use `ToolSearch` with `atlassian` to load `mcp__atlassian__getJiraIssue` and fetch the ticket body. Tickets from design typically embed per-icon `altText` + `keywords` blocks — parse them out so the user doesn't have to retype. If the MCP isn't connected or the ticket body lacks descriptions, ask the user for English descriptions.

**SVG files — ask the user for a local path.** The Atlassian MCP does *not* expose attachment binary downloads (the `/attachment/content/<id>` endpoint requires auth the MCP doesn't provide), so don't try — ask the user where the SVGs are on disk. Ways they might arrive:

- **Extracted directory** (most common — macOS auto-unzips downloads). Use it as-is; don't re-unzip.
- **`.zip` still zipped.** Before unzipping, check whether a sibling directory of the same name already exists next to it — if so, use that. Otherwise `unzip` into a `mktemp -d`.
- **Inline paste.** If the user pastes SVG source directly, write each to a temp file.

Use `AskUserQuestion` to get the path; don't guess. Once you have the directory, list its `.svg` files and confirm the set with the user before wiring anything up.

For any source, if English descriptions aren't provided, ask for them. For Nordic translations (nb/sv/da/fi), it's fine to draft them in the style of existing entries (concise, describes the visual — see `warp/src/main/res/values-{nb,sv,da,fi}/strings.xml` for tone). Flag drafted translations in the final report so the user knows to review.

If any input is missing (SVGs, names, or English descriptions), ask before proceeding. Translations can be drafted.

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

Invoke it with a small YAML or JSON manifest as stdin. `svg_dir` is whatever tempdir or user-supplied dir you resolved in Step 1:

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

## Step 6 — report and offer to commit

Report:
- Which icons were added
- Which translations were auto-drafted (needs review) vs user-supplied
- Which drawables tripped the 800-char VectorPath lint
- Confirmation that all three gradle tasks passed

Then ask the user how to handle the commit using `AskUserQuestion`. Suggested options:

1. **Commit now** — you stage the new/changed files and commit with a message prefixed by the Jira ticket, e.g. `FEP-153: Added N new Warp icons`. Follow the repo's commit style (look at recent commits — they use `<TICKET>: <short imperative>` with no body for icon additions; keep it that way). Include the `Co-Authored-By: Claude Opus 4.7 <noreply@anthropic.com>` trailer per the harness convention.
2. **Commit + push** — same as above, then `git push`. Only offer this if the current branch already tracks a remote; check with `git rev-parse --abbrev-ref --symbolic-full-name @{u}`.
3. **Leave for user** — do nothing; user commits themselves.

If translations were auto-drafted, recommend option 3 by default and flag it in the question — the user probably wants to review the translations before they land in git history.

If the user picks commit/push:
- Use `git status` first to confirm the diff is only the icon additions (no stray files).
- Stage specific paths, not `git add -A`: the drawable dir, `WarpIcons.kt`, `IconScreen.kt`, `WarpIconTest.kt`, the 5 `strings.xml` files.
- Never `--no-verify`; if pre-commit hooks fail, fix and re-commit (don't amend).
- Never force-push.

## Convention reference

- Alphabetical order everywhere (WarpIcons.kt, IconScreen.kt, WarpIconTest.kt letter groups, strings.xml). The existing files aren't perfectly sorted; match the local alphabetical cluster, don't reorder existing entries.
- Property naming: SVG file `ChartPieSlice.svg` → property `chartPieSlice` → drawable `warp_chartpieslice.xml` → string key `chartpieslice`.
- Never emit `WarpTaxonomyIconResource` or `WarpBrandIconResource` — new icons are always `WarpIconResource` unless the user explicitly asks otherwise (those types have identifiers that need coordination).
- Translation tone: concise, visual description (e.g. "Side view of a bicycle"), matches existing entries in each locale file.
