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
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch

/**
 * A modal bottom sheet anchored to the bottom of the screen that dims content behind it.
 *
 * Wraps Material 3's [ModalBottomSheet] with Warp's surface and drag-handle color tokens; shape
 * and handle size are left at M3 defaults, which already match the Warp Figma spec.
 *
 * @param onDismissRequest Called once the hide animation completes. Also passed to [content] as
 * `dismiss`, so in-sheet actions can close the sheet the same way.
 * @param modifier Applied to the sheet surface, on top of Warp's own status-bar inset cap.
 * @param dismissible Whether back-press, scrim-tap, swipe, and the drag handle can close the
 * sheet. Set to `false` only while a non-cancellable operation is in flight.
 * @param showDragHandle Ignored when [dismissible] is false - a non-dismissible sheet never shows
 * a handle, since it wouldn't do anything.
 * @param title Optional plain-text heading above [content].
 * @param content Sheet body; receives a `dismiss` callback (see [onDismissRequest]).
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
        // Gate only Hidden - gating every target would also block the initial reveal.
        confirmValueChange = { targetValue -> dismissible || targetValue != SheetValue.Hidden },
    )
    val dismiss: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) onDismissRequest()
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        // Caps the sheet below the status bar, so a scrim strip stays visible even with tall content.
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top))
            .then(modifier),
        sheetState = sheetState,
        containerColor = WarpTheme.colors.surface.elevated100,
        contentColor = WarpTheme.colors.text.default,
        // Top excluded - already handled by the modifier above.
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
