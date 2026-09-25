# WarpBottomSheet — Finn-app migration audit

Audit date: 2026-09-25
Scope surveyed: `/Users/fredrik.kvitvik@m10s.io/Development/android_finn_app` (branch `master`)
Component audited: `warp/src/main/java/com/schibsted/nmp/warp/components/WarpBottomSheet.kt` (FEP-130)

## Wrapper API being evaluated

```kotlin
@Composable
fun WarpBottomSheet(
    onDismiss: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable ColumnScope.() -> Unit,
)
```

Hardcodes `containerColor = colors.surface.elevated100` and drag handle color `colors.background.subtleActive`. Everything else defers to M3 defaults. No `modifier`, `dragHandle`, `shape`, `containerColor`, `scrimColor`, `contentWindowInsets`, `properties`, or `tonalElevation` exposed.

## Headline numbers

- 76 `ModalBottomSheet(...)` callsites + 3 `BottomSheetScaffold(...)` across ~85 non-test files.
- ~15 callsites (~20%) would migrate cleanly today.
- 61 callsites would have to drop at least one currently-used param.

## Would migrate cleanly (~20%)

Thin sheets passing only `onDismissRequest`, `sheetState`, and content. Examples: `AIFeedbackSheet.kt`, `PhoneActionsSheet.kt`, `NoneOfTheseBottomSheet.kt`, `MessageBottomSheet.kt`, most `mobility-adinput` sheets. Sites that only add `rememberModalBottomSheetState(skipPartiallyExpanded = true)` also migrate cleanly — `sheetState` is exposed.

## Params callers use that the wrapper drops

### `containerColor` — 75 occurrences (nearly every callsite)

Almost all pass `WarpTheme.colors.background.default`. Wrapper hardcodes `colors.surface.elevated100`.

Verdict: **spec disagreement, escalate to design**. Not a case for exposing `containerColor` as a param — the empirical vote from Finn is `background.default`, so either the Figma spec is wrong or the token mapping is wrong. Fix one side, then align everything.

### `dragHandle` — 21 occurrences

- 12 pass `null` (hide the handle entirely).
- 4 pass a custom color (`colors.icon.disabled`).
- 3 pass a themed replacement.
- 1 conditional via `BottomSheetHelper`'s `showDragHandle: Boolean` param.
- 1 pass `{}` empty.

Verdict:
- Hide-handle sites (12) — **rethink**. This is the "toggle that undermines core UX" the scaffold skill's dropout list calls out. The handle is the a11y affordance for TalkBack users. Force migration to keep it; coordinate with product before the migration lands so those teams are not surprised.
- Custom-color sites (4) — **rethink**. Same token-disagreement as `containerColor`. Fix via design conversation, not by exposing `dragHandle`.

### `shape` — 18 occurrences

All `RoundedCornerShape(topStart = X, topEnd = X)` with X ranging 12–28dp.

Verdict: **rethink for all**. Legacy code predating a stable Warp shape token. Wrapper's fall-through to M3's 28dp is the correct target.

### `properties = ModalBottomSheetProperties(...)` — 10 occurrences

Two real needs:
- (a) `shouldDismissOnBackPress = false` / `shouldDismissOnClickOutside = false` while a long-running action commits (`CategoryPickerBottomSheet`, `CVParsingBottomSheet`, `DocumentsOverlays`) — 3 sites.
- (b) `isAppearanceLightStatusBars` / `isAppearanceLightNavigationBars` matched to app theme via a shared helper (`FavoritesSheetContents`) — 7 sites.

Verdict: **both legitimate**. (a) is a real "cannot dismiss during commit" UX pattern. (b) is a real system-bar-appearance bug that surfaces on some flavors. Expose `properties: ModalBottomSheetProperties = ModalBottomSheetProperties()` on the wrapper.

### `modifier` with real semantics — ~10 legitimate occurrences (of ~35)

Most `modifier` usage is `.padding` on inner content, not on the sheet itself. Real uses on the sheet:
- `Modifier.onKeyEvent { … }` for hardware-keyboard Enter/Escape dismissal (3 trust sites).
- `Modifier.statusBarsPadding()` + `Modifier.windowInsetsPadding(WindowInsets.displayCutout)` for edge-to-edge fixes (3 sites).
- `Modifier.offset(y = ...)` (`JobSmartSearch`, 1 — questionable).
- `Modifier.withStickyActionHeight(...)` custom layout modifier (1).

Verdict: **onKeyEvent and insets padding are legitimate** — correctness fixes, not shaping. **rethink** the offset/sticky ones. Confirms adding `modifier` back to `WarpBottomSheet` is warranted (per the portal-component rule in the scaffold skill: keep `modifier` on portals only when there is a concrete testing or correctness need).

### `tonalElevation` — 3 occurrences

2 pass `0.dp` to defeat M3 elevation, 1 passes `dimensions.shadowSmall`.

Verdict: **rethink**. The 0dp workarounds trace back to the `MaterialTheme.colorScheme`-not-wired theme gap discussed separately. Fix at the theme layer, not by exposing `tonalElevation`.

### `contentWindowInsets` — 2 occurrences

`BaseBottomSheet` pass-through, `CategoryPickerBottomSheet` sets `WindowInsets.navigationBars`.

Verdict: **legitimate** for edge-to-edge. Only 2 sites, but touches a real correctness issue. Either expose it or bake edge-to-edge handling into the wrapper.

## Out of scope for `WarpBottomSheet` — separate component

3 sites (`OverviewFragment`, `CompareSelectionBottomSheet`, `LongPressedScreen`) use `BottomSheetScaffold` — a persistent, non-modal sheet with `sheetPeekHeight` and drag-up-from-collapsed behavior over a scaffold body. Different M3 primitive, different UX. Warrants a separate `WarpBottomSheetScaffold` if the pattern is here to stay, not folded into `WarpBottomSheet`.

## Recommended wrapper delta (additive)

```kotlin
fun WarpBottomSheet(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    properties: ModalBottomSheetProperties = ModalBottomSheetProperties(),
    content: @Composable ColumnScope.() -> Unit,
)
```

KDoc note on `modifier`: for correctness fixes (`onKeyEvent`, edge-to-edge insets) and test tagging — not for shaping.

Open question: whether to expose `contentWindowInsets` or handle edge-to-edge internally.

## Params to hold firm on

- `containerColor` — settle via design, do not expose.
- `dragHandle` (including `null`) — a11y-critical, do not expose.
- `shape` — legacy churn, do not expose.
- `scrimColor` — no real demand, keep dropped.
- `tonalElevation` — symptom of the theme gap, do not expose.

## Related decisions logged elsewhere

- Scrim color: keep M3 default, no `colors.scrim` token needed (decided during scaffolding).
- Drag handle press visual (dark and square): traces to `WarpTheme` not wiring `MaterialTheme.colorScheme`. Broader theme-architecture decision, not a per-component fix.
- Sample app: uses a 20-item category list to demonstrate drag-to-expand behavior (`app/src/main/java/com/schibsted/nmp/warpapp/ui/BottomSheetScreen.kt`).

## Next steps

1. Take the container-color question (75 callsites vs. Figma spec) to design.
2. Once settled, apply the additive wrapper delta above in the same release.
3. Coordinate with teams owning the 12 `dragHandle = null` sites before the migration lands.
4. Decide separately whether a `WarpBottomSheetScaffold` component is warranted for the 3 persistent-sheet sites.
