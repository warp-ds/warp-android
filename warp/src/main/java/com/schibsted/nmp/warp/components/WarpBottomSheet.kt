package com.schibsted.nmp.warp.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch

// onDismissRequest fires only after the hide animation completes; content gets the same
// callback so in-sheet actions (e.g. a primary button) can close the sheet the same way.
//
// dismissible gates back-press, scrim-click, and swipe-to-dismiss (via confirmValueChange on the
// internally-built SheetState, which isn't a param - every real customization need found in the
// codebase was this same single busy-gate condition, nothing more elaborate). M3's own tap-to-
// dismiss on the drag handle, and its dismiss/expand/collapse TalkBack actions, read the same
// confirmValueChange, so dismissible covers those paths too with no extra wiring.
//
// showDragHandle only matters when dismissible is true - a non-dismissible sheet never shows a
// handle regardless, since that would advertise a capability that doesn't exist. Hiding the
// handle on an otherwise-dismissible sheet is fine: back-press and scrim-tap still work, so it's
// a discoverability trade-off, not a lost capability (e.g. the content has its own competing
// drag gesture, or a plain confirmation sheet just wants a cleaner look).
//
// No custom drag handle: M3's ModalBottomSheet already wraps BottomSheetDefaults.DragHandle in a
// tap-to-dismiss click target plus TalkBack dismiss/expand/collapse actions using its own bundled,
// fully-localized strings. A hand-rolled clickable wrapper and content-description string would
// only duplicate that, worse (translated into a handful of locales instead of all of them).
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
        confirmValueChange = { dismissible },
    )
    val dismiss: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion { onDismissRequest() }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        containerColor = WarpTheme.colors.surface.elevated100,
        contentColor = WarpTheme.colors.text.default,
        // M3's default only covers Top+Bottom; safeDrawing also picks up Horizontal for
        // landscape display cutouts and 3-button nav bars, which the default misses.
        contentWindowInsets = { WindowInsets.safeDrawing },
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
