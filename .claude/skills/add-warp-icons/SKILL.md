---
name: add-warp-icons
description: Adds new Warp icons to this Android codebase. Converts SVGs to VectorDrawables, wires into WarpIcons.kt / IconScreen.kt / snapshot tests, and adds content descriptions across all 5 locales (en/nb/sv/da/fi). Trigger when the user says "add warp icons" or references a Jira ticket (like FEP-XXX) with new icon assets.
---

# add-warp-icons

## Usage

`/add-warp-icons <source> [flags]`

**source**
- Jira ticket key (e.g. `FEP-153`) — fetches descriptions + SVG zip attachment.
- Local path — directory of `.svg` files, or a `.zip` file.
- Omit to be asked.

**flags**
- `--no-commit` — wire the changes but don't commit; leave them staged for review.
- `--confirm` — ask via `AskUserQuestion` before committing (default: commit without asking).
- `--push` — push after committing (branch must track a remote).

When starting the run, announce the source and non-default behavior in one line so the user can course-correct, e.g.: *"Fetching FEP-153. Will commit at the end — pass `--no-commit` to review first."*

## Pipeline

### 1. Gather

**Jira source**: load `mcp__atlassian__getJiraIssue` via `ToolSearch` (query: `atlassian`) and fetch the ticket (`summary`, `description`, `attachment`). Parse per-icon `altText` blocks from the description into the manifest. Pass the `.zip` attachment's `content` URL as `attachment_url` — `wire_up.py` downloads it via `~/.netrc` and will print setup instructions if credentials are missing.

**Path source**: pass as `svg_dir`.

If English descriptions are missing (path source, or ticket has none), ask. Draft nb/sv/da/fi in the style of existing entries in `warp/src/main/res/values-{nb,sv,da,fi}/strings.xml` — flag drafts in the final report.

### 2. Wire

```bash
python3 .claude/skills/add-warp-icons/wire_up.py <<'JSON'
{
  "attachment_url": "https://api.atlassian.com/ex/jira/<cloudId>/rest/api/3/attachment/content/<id>",
  "icons": [
    {"name": "chartBar", "en": "A bar chart with ascending bars",
     "nb": "…", "sv": "…", "da": "…", "fi": "…"}
  ]
}
JSON
```

For a path source, replace `attachment_url` with `"svg_dir": "/absolute/path"`. The script skips icons that already exist (idempotent) and reports drawables that trip the 800-char `VectorPath` lint.

### 3. Verify

```bash
./gradlew :warp:compileDebugKotlin :app:compileDebugKotlin \
  :snapshot:snapshot-icons:testDebugUnitTest --tests "com.schibsted.snapshot.WarpIconTest.warp_icon_count*"
```

All three must pass. If the count test fails, investigate before committing.

### 4. Report + commit

Report: icons added, auto-drafted translations (needs review), drawables over the lint threshold, gradle results.

Unless `--no-commit`:
- If `--confirm`, ask via `AskUserQuestion` before committing.
- `git status`, stage specific paths only (drawables + `WarpIcons.kt` + `IconScreen.kt` + `WarpIconTest.kt` + the 5 `strings.xml`); never `git add -A`.
- Commit `<TICKET>: Added N new Warp icons` with the `Co-Authored-By: Claude Opus 4.7 <noreply@anthropic.com>` trailer.
- If hooks fail, fix and re-commit — never `--amend`, never `--no-verify`.
- If `--push`, `git push` afterward (never force).

## Conventions

- Naming: SVG `ChartPieSlice.svg` → prop `chartPieSlice` → drawable `warp_chartpieslice.xml` → string key `chartpieslice`.
- Alphabetical insertion within each file's existing (imperfect) order — the script handles this; don't reorder pre-existing entries.
- New icons are always `WarpIconResource` (not `WarpTaxonomyIconResource` / `WarpBrandIconResource` — those have identifiers that need coordination).
