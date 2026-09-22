---
name: release-warp
description: Release a new version of Warp Android. Prompts for the target version, bumps ConfigData.kt, commits + pushes to develop (with approval), creates and pushes an annotated tag, publishes a GitHub Release, and runs the Artifactory publish script. Trigger when the user says "release warp", "cut a warp release", "publish warp", or asks to bump the version and ship.
---

# release-warp

End-to-end Warp Android release. Follow the steps in order. Ask for approval at each push/publish boundary — never push, tag, release, or publish unprompted.

## Step 1 — preflight

Run these checks before touching anything:

1. Confirm the current branch is `develop`. If not, stop and tell the user.
2. Confirm the working tree is clean (`git status --porcelain` is empty). If not, stop.
3. `git fetch origin` and confirm `develop` is up to date with `origin/develop`. If behind, tell the user to pull; if ahead, tell the user unpushed commits exist.
4. Read `buildSrc/src/main/java/ConfigData.kt` and extract:
   - `warpVersion` string (e.g. `"0.0.65"`)
   - `sampleAppVersionCode` int (e.g. `65`)
   These must be in sync (numeric tail of `warpVersion` == `sampleAppVersionCode`). If they aren't, stop and tell the user.
5. Check `gh` is installed: `command -v gh`. If missing, ask the user for approval to `brew install gh`. If they decline, stop — the GitHub Release step needs it.
6. Check `gh auth status`. If not authenticated, tell the user to run `gh auth login` and stop.

## Step 2 — ask for the target version

Always prompt for the target version with `AskUserQuestion`. Show the current version and offer the auto-increment as the recommended default:

- Current: `0.0.65`
- Recommended: `0.0.66` (patch bump)
- Also present: minor bump (`0.1.0`), or "other" for a custom value.

Validate the answer matches `^\d+\.\d+\.\d+$`. Compute the new `sampleAppVersionCode` as the integer sum of segments if that pattern breaks (currently the code uses only the patch as the int, so extract the trailing integer — if the new version's patch collides with an existing tag, stop and ask).

Then check `git tag -l "v<new-version>"` — if the tag already exists, stop.

## Step 3 — bump ConfigData.kt

Edit `buildSrc/src/main/java/ConfigData.kt`:

- Replace `warpVersion = "<old>"` with `warpVersion = "<new>"`
- Replace `sampleAppVersionCode = <old-int>` with `sampleAppVersionCode = <new-int>`

Show the diff (`git diff buildSrc/src/main/java/ConfigData.kt`) so the user can verify.

## Step 4 — commit and push to develop (ask first)

Use `AskUserQuestion` to confirm the commit + direct push to `develop`. Show:

- The commit message: `NOJIRA: Bump to version .<patch>` (matches recent style — e.g. `NOJIRA: Bump to version .66`)
- The target: `origin/develop` (direct push, no PR — this is the established pattern for version bumps)

Options:
1. **Commit + push** (recommended)
2. **Commit only** — leaves the push to the user; skill stops here
3. **Cancel** — revert the edit and stop

If approved, stage only `buildSrc/src/main/java/ConfigData.kt` (never `git add -A`), commit with the message above plus the `Co-Authored-By: Claude Opus 4.7 <noreply@anthropic.com>` trailer per HEREDOC convention, then `git push origin develop`.

Never use `--no-verify`. If pre-commit hooks fail, fix the underlying issue and create a new commit (do not amend).

## Step 5 — tag and push tag

Create an annotated tag pointing at the commit you just pushed:

```bash
git tag -a "v<new-version>" -m "Release <new-version>"
git push origin "v<new-version>"
```

Confirm the tag landed with `git ls-remote --tags origin "v<new-version>"`.

## Step 6 — create the GitHub Release

Use `gh release create` with auto-generated notes (GitHub builds them from merged PRs since the previous tag — matches how a Warp release's changelog is composed):

```bash
gh release create "v<new-version>" \
  --title "v<new-version>" \
  --target develop \
  --generate-notes
```

After creating, print the release URL from `gh release view "v<new-version>" --json url --jq .url` so the user can open it. Offer to edit the notes if they want to hand-tune anything (`gh release edit "v<new-version>" --notes "…"`), but don't do it unprompted.

## Step 7 — publish to Artifactory (ask first)

Use `AskUserQuestion` to confirm running `scripts/release-warp.sh`. State plainly what it does: assembles and publishes 7 modules (warp, warp-blocket, warp-dba, warp-finn, warp-tori, warp-neutral, warp-vend) to Artifactory via `./gradlew :<module>:artifactoryPublish -PPUBLISH_TO_ARTIFACTORY=true`. This takes several minutes.

Options:
1. **Run it now** (recommended)
2. **Skip** — print the exact command (`./scripts/release-warp.sh`) so the user can run it themselves later

If approved, run `./scripts/release-warp.sh` from the repo root. The script prints per-module success/failure and exits non-zero on any failure. If it fails partway, do NOT re-run the whole thing unprompted — surface which module failed and ask the user how to proceed (the tag and release already exist at this point).

## Step 8 — final report

Report:

- New version: `<new-version>`
- Commit SHA on develop
- Tag: `v<new-version>`
- GitHub Release URL
- Publish result: which modules succeeded / failed, or "skipped" if the user chose that

## Safety rules (do not skip)

- Never push to `develop` without explicit approval in Step 4.
- Never run `scripts/release-warp.sh` without explicit approval in Step 7.
- Never delete a tag or release once created. If something goes wrong after Step 5, surface the state and let the user decide.
- Never `git push --force` anything in this flow.
- Never bypass hooks (`--no-verify`, `--no-gpg-sign`).
- If any preflight check in Step 1 fails, stop and report — do not try to "fix" the repo state.
