package com.schibsted.nmp.warp.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch

/**
 * A modal bottom sheet anchored to the bottom of the screen that dims content behind it. Use for
 * supplementary content and actions - menus, filter panels, confirmations - that shouldn't take
 * the user out of the current screen.
 *
 * Wraps Material 3's [ModalBottomSheet] with Warp tokens: [WarpTheme.colors.surface.elevated100]
 * surface and [WarpTheme.colors.background.subtleActive] drag handle. Corner shape and drag
 * handle size/shape fall through to M3 defaults, which already match the Warp Figma spec.
 *
 * No custom drag handle: M3's [ModalBottomSheet] already wraps [BottomSheetDefaults.DragHandle] in
 * a tap-to-dismiss click target plus TalkBack dismiss/expand/collapse actions, using its own
 * bundled, fully-localized strings. A hand-rolled clickable wrapper would only duplicate that,
 * worse (translated into a handful of locales instead of all of them).
 *
 * The sheet's own top inset is capped at the status bar/display cutout boundary so a scrim strip
 * is always visible above it - without this, a sheet with tall enough content (e.g. a long list)
 * would measure taller than the screen and get clamped flush to the very top, looking like a
 * full-screen overlay instead of a floating sheet.
 *
 * See https://m3.material.io/components/bottom-sheets/overview.
 *
 * @param onDismissRequest Invoked once the hide animation completes - from back-press, scrim-tap,
 * swipe, or M3's own drag-handle tap/TalkBack actions. [content] receives the same callback (as
 * `dismiss`) so in-sheet actions, e.g. a primary button, can close the sheet the same way.
 * @param modifier Applied to the sheet surface, after Warp's own top-inset cap (see above) - not
 * for shaping; keep overrides to correctness or test-tagging needs.
 * @param dismissible Gates back-press, scrim-click, drag-handle tap, and swipe/TalkBack dismissal
 * via `confirmValueChange` on the internally-built [SheetState] (not a param - every real need
 * found in the codebase was this same single busy-gate condition, nothing more elaborate). Set to
 * `false` only while a non-cancellable operation (e.g. an upload) is in flight, derived from that
 * operation's own state, so the sheet is never left permanently undismissable.
 * @param showDragHandle Only matters when [dismissible] is true - a non-dismissible sheet never
 * shows a handle regardless, since that would advertise a capability that doesn't exist. Hiding
 * the handle on an otherwise-dismissible sheet is fine: back-press and scrim-tap still work, so
 * it's a discoverability trade-off (e.g. the content has its own competing drag gesture).
 * @param title Optional plain-text heading rendered above [content]. Deliberately plain - no icon
 * pairing, back button, or subtitle; a richer bottom-sheet header is tracked as separate scope.
 * @param content Slot for the sheet body; receives a `dismiss` callback (see [onDismissRequest]).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    dismissible: Boolean = true,
    showDragHandle: Boolean = true,
    title: String? = null,
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        // Must only gate the transition to Hidden - gating every transition would also block the
        // initial reveal (SheetState.show() targets Expanded through this same check) whenever
        // dismissible is false, leaving the sheet permanently invisible.
        confirmValueChange = { targetValue -> dismissible || targetValue != SheetValue.Hidden },
    )
    val dismiss: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) onDismissRequest()
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top))
            .then(modifier),
        sheetState = sheetState,
        containerColor = WarpTheme.colors.surface.elevated100,
        contentColor = WarpTheme.colors.text.default,
        // No Top here - the modifier above already caps the sheet's top edge at the status bar.
        // Adding it here too would pad the content down a second time underneath that cap.
        contentWindowInsets = { WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal) },
        dragHandle = if (dismissible && showDragHandle) {
            { BottomSheetDefaults.DragHandle(color = WarpTheme.colors.background.subtleActive) }
        } else {
            null
        },
        properties = ModalBottomSheetProperties(
            shouldDismissOnBackPress = dismissible,
            shouldDismissOnClickOutside = dismissible,
        ),
    ) {
        if (title != null) {
            WarpText(
                text = title,
                style = WarpTextStyle.Title3,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = WarpTheme.dimensions.space2, vertical = WarpTheme.dimensions.space1),
            )
        }
        content(dismiss)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WarpBottomSheetTitleAndActionPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        title = "Sort by",
    ) { dismiss ->
        Column(modifier = Modifier.padding(WarpTheme.dimensions.space2)) {
            WarpText(text = "Newest first", style = WarpTextStyle.Body)
            WarpText(text = "Oldest first", style = WarpTextStyle.Body)
            WarpButton(
                text = "Done",
                onClick = dismiss,
                style = WarpButtonStyle.Primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = WarpTheme.dimensions.space2),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "No header, not dismissible", showBackground = true)
@Composable
private fun WarpBottomSheetMinimalPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        dismissible = false,
    ) {
        WarpText(
            text = "Plain content, no header, no drag handle.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dismissible, handle hidden", showBackground = true)
@Composable
private fun WarpBottomSheetHiddenHandlePreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        showDragHandle = false,
        title = "Reorder photos",
    ) {
        WarpText(
            text = "Content with its own drag gesture, e.g. drag-to-reorder list items.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}
