---
name: scaffold-warp-component
description: Scaffolds a new Warp Compose component from its Figma spec frame, following Warp's scope/token/restrictiveness/M3-alignment conventions. Trigger when the user references a Jira ticket (e.g. FEP-XXX) asking to build a Warp component, or shares a Figma component URL to implement.
---

# scaffold-warp-component

Bootstrap for translating a Figma spec frame into a Kotlin/Compose file under `warp/src/main/java/com/schibsted/nmp/warp/components/`. Optimizes for the four Warp house rules established in prior sessions — spec-only scope, tokens first, restrictive wrapper, M3 alignment.

## Usage

`/scaffold-warp-component <source>`

**source**
- Jira ticket key (e.g. `FEP-130`) — fetches ticket, extracts the Figma URL from its description.
- Figma frame URL (must contain `?node-id=`) — points directly at the master component.
- Omit to be asked upfront via `AskUserQuestion`.

The run always: scaffolds the Warp component, adds a sample-app catalog entry, wires it into `MainScreen`, and compiles both modules. Committing, screenshot / unit tests, and Code Connect mapping are follow-ups the user decides on.

## Pipeline

### 1. Pre-flight

Resolve the Figma URL and target component name before touching files:

- **Jira source**: load `mcp__atlassian__getJiraIssue` via `ToolSearch`, fetch the ticket, pull the Figma URL from `fields.description`. Derive the component name from the ticket summary (e.g. "Android: Create bottom sheet component" → `WarpBottomSheet`).
- **Figma URL source**: ask for the component name if it's not obvious from the frame name.
- **Extract**: `fileKey` and `nodeId` from the URL (`https://figma.com/design/:fileKey/…?node-id=NNN-MMM` → nodeId `NNN:MMM`).
- **Bail** if `warp/src/main/java/com/schibsted/nmp/warp/components/<Name>.kt` already exists — this skill is for new components.

### 2. Inspect the design

Run in parallel:
- `mcp__plugin_figma_figma__get_metadata` on the master nodeId — reveals the child variant structure.
- `mcp__plugin_figma_figma__get_screenshot` on the same nodeId — download the PNG with `curl -L -o /tmp/<slug>.png <url>` and Read it to visually confirm what "the spec frame shows".

Then in a second parallel batch, `mcp__plugin_figma_figma__get_design_context` on **each variant node** listed on the master (typically named `Type=Default`, `Type=Modal`, etc.). If the first call returns a Code Connect prompt, re-issue with `disableCodeConnect: false`. Pass `clientFrameworks: "jetpack-compose"`, `clientLanguages: "kotlin"`, `skillNames: "figma-design-to-code,scaffold-warp-component"`.

### 3. Determine scope

**Only implement variants shown on the master frame.** Sibling frames labelled "Atoms", "Nav sub-variants", etc. are internal building blocks — do not expose them as public parameters. See `[[feedback_warp_component_scope]]`.

If the design has two variants that both map to the same underlying M3 component (e.g. Default + Modal both → `ModalBottomSheet`), collapse them into one wrapper unless the spec calls out a runtime toggle.

### 4. Match against existing Warp patterns

Read one or two neighbor files under `warp/src/main/java/com/schibsted/nmp/warp/components/` that use the same M3 primitive family (dialogs → `WarpDialog.kt`, `WarpFullScreenDialog.kt`, `WarpModal.kt`; overlays → `WarpPopover.kt`, `WarpTooltip.kt`; input → `WarpTextField.kt`). Copy their KDoc shape, param ordering, and `@OptIn` conventions.

### 5. Audit tokens BEFORE writing raw values

For every dp / color / shape in the Figma output, grep `warp/src/main/java/com/schibsted/nmp/warp/theme/` for the token. Concretely:
- Spacing / radius → `WarpDimensions.kt`
- Colors → `WarpColors.kt` (paths like `colors.surface.elevated100`, `colors.background.subtleActive`)
- Shapes → `WarpShapes.kt`

If a value doesn't exist as a token AND matches the M3 default, **drop the override** and let M3 apply it. Do not invent new tokens as part of scaffolding. See `[[feedback_warp_tokens_first]]`.

### 6. Draft the restrictive wrapper

Start from the M3 primitive's full API, then **delete every parameter the Warp spec doesn't call for**. See `[[feedback_warp_restrictive_api]]`.

Typical dropouts:
- Toggles that undermine core UX (e.g. `showDragHandle` on a bottom sheet).
- Shape / color overrides that equal the M3 default.
- Variant enums whose values are not on the master frame.

Typical keepers: the primary callback (`onDismiss` / `onClick` / `onValueChange`), typed content params (`title: String`, `body: String`, `text: String`, `icon: WarpIconResource`), and state hooks needed for programmatic control.

**`modifier` — decide by component category, not by default:**
- **Inline widgets** (buttons, pills, badges, text fields, icons, checkboxes — anything placed inside a layout): keep `modifier`. Callers legitimately need `.weight(1f)`, `.align(...)`, `.fillMaxWidth()` to compose them inside `Row`/`Column`. The codebase already reflects this (e.g. `WarpButton(modifier = Modifier.fillMaxWidth(), …)` in sample screens).
- **Portal / overlay components** (modals, dialogs, bottom sheets, popovers, tooltips — anything that renders in its own window/scope): drop `modifier` unless there is a concrete testing need. M3's `modifier` on these binds to an internal container, not the scrim/window, so layout composition doesn't meaningfully apply — the only realistic use is `testTag` for UI/screenshot tests. If Warp's test infrastructure needs it, keep it and note in the KDoc that it's for tagging, not shaping.

**Composable slot params (`content: @Composable <Scope>.() -> Unit`) are a red flag — default to NO.** Most Warp components should accept typed params only (see `WarpModal`, `WarpAlert`, `WarpButton`). A slot is only warranted when the component's fundamental purpose is to host arbitrary caller-supplied content — bottom sheets, some dialogs, expandable containers. If you're reaching for a `@Composable` slot on anything else, stop and reconsider whether typed params would express the spec more faithfully. Ask the user before adding one.

### 7. Bridge Warp tokens onto M3 defaults

`WarpTheme` in `warp/src/main/java/com/schibsted/nmp/warp/theme/WarpTheme.kt` provides `LocalColors` (Warp tokens) but does NOT wire `MaterialTheme.colorScheme`. So M3 defaults that resolve through `colorScheme.*` will look unbranded — you MUST override them with the Warp token equivalent. Geometry / shape defaults, on the other hand, do not depend on `colorScheme` and generally DO match Figma; leave those alone.

### 8. Flag Figma-vs-M3 mismatches — do not silently code to Figma

Warp's Figma library tracks Material 3. For each spec value, mentally check "M3 default = X, Figma = Y":
- `X == Y` → drop the override.
- `X != Y` → **follow the M3 default by default; do NOT hardcode the Figma value.** Surface the mismatch with both values as a flag in the report so design can decide whether to correct Figma or intentionally diverge — but the code as scaffolded uses M3. See `[[feedback_warp_flag_m3_mismatch]]`.

Also flag any spec value with **no Warp token AND no M3 default coverage** — for example, a scrim color when Warp has no `colors.scrim` and the direction is moving toward platform defaults. Recommend the M3 default; do not add a new Warp token as part of scaffolding.

### 9. Wire the sample app (always, no opt-in)

Every Warp component ships with a sample-app screen. Do this as part of the initial scaffold — do not ask.

- Create `app/src/main/java/com/schibsted/nmp/warpapp/ui/<Name>Screen.kt` following the neighbor screen shape: `<Name>Screen(onUp)` that wraps `DetailsScaffold(title = "Warp<Name>", onUp)` and delegates to `<Name>ScreenContent()`. Show at least one working callsite of the component with meaningful sample content (a button that opens it, a form entry, etc. — pattern-match `ModalScreen.kt` / `DialogScreen.kt` for overlays; `ButtonScreen.kt` for inline widgets).
- Register in `app/src/main/java/com/schibsted/nmp/warpapp/ui/MainScreen.kt` in **two** places:
  - The `composable("<route>") { <Name>Screen { navController.navigateUp() } }` block (near the neighbor).
  - The catalog list of `"<route>" to "Warp<Name>"` entries. Insert alphabetically.

### 10. Verify

```bash
./gradlew :warp:compileDebugKotlin :app:compileDebugKotlin
```

Both must pass. Pre-existing warnings in other files are fine to ignore.

### 11. Report

One short paragraph:
- Files created: the Warp component and the sample-app screen.
- Which variants from the master were folded into the single wrapper.
- Which params were dropped from the M3 primitive and why.
- Any Figma-vs-M3 mismatches: state the recommendation (M3 default) and the Figma value so design can decide whether to correct Figma.
- Whether a `@Composable` slot was used and why (should be rare — justify).
- Named follow-ups the user may want (screenshot / unit tests, additional variants, Code Connect mapping).

## Conventions

- File: `warp/src/main/java/com/schibsted/nmp/warp/components/Warp<Name>.kt`, package `com.schibsted.nmp.warp.components`.
- Preview: `private fun Warp<Name>Preview()` with matching `@OptIn` annotations. Callable from Compose preview without external state.
- KDoc: one-paragraph purpose + one-line-per-`@param`, following `WarpFullScreenDialog.kt` shape.
- `@OptIn(ExperimentalMaterial3Api::class)` goes on both the public composable AND the preview when default params flow through experimental types.
- No `dimensions.space*` in the wrapper unless the spec explicitly shows padding around the whole component; content padding lives at the callsite.
