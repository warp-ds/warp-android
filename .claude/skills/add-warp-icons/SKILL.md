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
- Omit to be asked upfront.

**flags** (post-run behavior — pick at most one; pre-authorizes the finish step, skipping the prompt)
- `--push` — commit and push (branch must track a remote).
- `--commit` — commit, no push.
- `--stage` — wire and verify, but don't commit (use to review first).
- *default (no flag)* — after the report, ask via `AskUserQuestion` how to finish: A) Commit + push  B) Commit  C) Stage only.

When starting the run, announce the source and any pre-authorized finish behavior in one line so the user can course-correct, e.g.: *"Fetching FEP-153. Will commit and push at the end."* (or *"…will ask how to finish."* for the default).

## Pipeline

Once wiring starts, the run doesn't stop for questions. Every clarification and credential check happens in pre-flight; the only late stop is a gradle failure.

### 1. Pre-flight

Resolve everything that could stall the run *before* touching files:

- **Source**: if omitted, ask via `AskUserQuestion`.
- **Jira source**: load `mcp__atlassian__getJiraIssue` via `ToolSearch` (query: `atlassian`), fetch the ticket (`summary`, `description`, `attachment`), and parse per-icon `altText` blocks from the description. Then run `python3 .claude/skills/add-warp-icons/wire_up.py --check-netrc` to verify Atlassian credentials before committing to a download; if it exits non-zero, surface its output and stop.
- **Path source**: list the `.svg` files in the directory / zip.
- **English descriptions**: if any icon lacks an English `altText`, **error out here** with the list of missing icons. Do not ask, do not proceed.

Nordic translations (nb/sv/da/fi) are drafted from existing entries in `warp/src/main/res/values-{nb,sv,da,fi}/strings.xml` and passed straight into the manifest. They are reviewed post-merge — do not flag them in the report.

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

All three must pass. If the count test fails, investigate before committing — this is the only sanctioned mid-flow stop.

### 4. Report + finish

Report: icons added, drawables over the lint threshold, gradle results.

Then pick the finish action:
- If `--push`, `--commit`, or `--stage` was passed, use that.
- Otherwise, `AskUserQuestion`: *"How should I finish?"* — A) Commit + push  B) Commit  C) Stage only. The user has just seen the report, so this is the informed moment to decide.

For **stage only**: leave the working tree as-is and stop.

For **commit** (and **commit + push**):
- `git status`, stage specific paths only (drawables + `WarpIcons.kt` + `IconScreen.kt` + `WarpIconTest.kt` + the 5 `strings.xml`); never `git add -A`.
- Commit `<TICKET>: Added N new Warp icons` with the `Co-Authored-By: Claude Opus 4.7 <noreply@anthropic.com>` trailer.
- If hooks fail, fix and re-commit — never `--amend`, never `--no-verify`.
- For **commit + push**, `git push` afterward (never force).

## Conventions

- Naming: SVG `ChartPieSlice.svg` → prop `chartPieSlice` → drawable `warp_chartpieslice.xml` → string key `chartpieslice`.
- Alphabetical insertion within each file's existing (imperfect) order — the script handles this; don't reorder pre-existing entries.
- New icons are always `WarpIconResource` (not `WarpTaxonomyIconResource` / `WarpBrandIconResource` — those have identifiers that need coordination).
