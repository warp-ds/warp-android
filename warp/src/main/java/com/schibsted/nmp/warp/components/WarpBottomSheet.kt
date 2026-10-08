package com.schibsted.nmp.warp.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch

/**
 * A modal bottom sheet anchored to the bottom of the screen that dims content behind it.
 *
 * Wraps Material 3's [ModalBottomSheet] with Warp's surface and drag-handle color tokens; shape
 * and handle size are left at M3 defaults, which already match the Warp Figma spec. Tapping the
 * scrim always dismisses.
 *
 * @param onDismissRequest Called once the hide animation completes. Also passed to [content] as
 * `dismiss`, so in-sheet actions can close the sheet the same way.
 * @param modifier Applied to the sheet surface, on top of Warp's own status-bar inset cap.
 * @param draggable Whether the sheet shows a drag handle and responds to swipe. Set to `false` when
 * content has its own drag gestures or a swipe would throw away user input.
 * @param dismissOnBackPress Whether back-press dismisses the sheet. Set to `false` when content
 * handles back itself, e.g. its own sub-navigation.
 * @param skipPartiallyExpanded Whether the sheet opens fully expanded. Requires [draggable] when
 * `false`, since a non-draggable sheet can't leave the half-expanded state.
 * @param title Optional plain-text heading above [content].
 * @param content Sheet body; receives a `dismiss` callback (see [onDismissRequest]).
 */
@Composable
fun WarpBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    draggable: Boolean = true,
    dismissOnBackPress: Boolean = true,
    // TODO: Confirm the need for partial expand, likely the search filter sheets.
    skipPartiallyExpanded: Boolean = true,
    title: String? = null,
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit,
) {
    WarpBottomSheetImpl(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        draggable = draggable,
        dismissOnBackPress = dismissOnBackPress,
        skipPartiallyExpanded = skipPartiallyExpanded,
        header = title?.let {
            {
                WarpText(
                    text = it,
                    style = WarpTextStyle.Title3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = WarpTheme.dimensions.space2, vertical = WarpTheme.dimensions.space1),
                )
            }
        },
        content = content,
    )
}

/**
 * A [WarpBottomSheet] with a custom header row above [content], e.g. back navigation, title and a
 * trailing action. The sheet provides only the row; navigation and action behavior are up to the
 * caller.
 *
 * @param header Content of a full-width, vertically centered row above [content].
 */
@Composable
fun WarpBottomSheet(
    onDismissRequest: () -> Unit,
    header: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    draggable: Boolean = true,
    dismissOnBackPress: Boolean = true,
    // TODO: Confirm the need for partial expand, likely the search filter sheets.
    skipPartiallyExpanded: Boolean = true,
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit,
) {
    WarpBottomSheetImpl(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        draggable = draggable,
        dismissOnBackPress = dismissOnBackPress,
        skipPartiallyExpanded = skipPartiallyExpanded,
        header = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = WarpTheme.dimensions.space1),
                verticalAlignment = Alignment.CenterVertically,
                content = header,
            )
        },
        content = content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WarpBottomSheetImpl(
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    draggable: Boolean,
    dismissOnBackPress: Boolean,
    skipPartiallyExpanded: Boolean,
    header: (@Composable () -> Unit)?,
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit,
) {
    require(skipPartiallyExpanded || draggable) {
        "A non-draggable WarpBottomSheet must skip the partially expanded state."
    }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
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
        sheetGesturesEnabled = draggable,
        containerColor = WarpTheme.colors.surface.elevated100,
        contentColor = WarpTheme.colors.text.default,
        // Top excluded - already handled by the modifier above.
        contentWindowInsets = { WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal) },
        dragHandle = if (draggable) {
            { BottomSheetDefaults.DragHandle(color = WarpTheme.colors.background.subtleActive) }
        } else {
            null
        },
        properties = ModalBottomSheetProperties(
            shouldDismissOnBackPress = dismissOnBackPress,
            shouldDismissOnClickOutside = true,
        ),
    ) {
        header?.invoke()
        content(dismiss)
    }
}

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

@Preview(name = "Header row", showBackground = true)
@Composable
private fun WarpBottomSheetHeaderPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        header = {
            WarpText(
                text = "Filters",
                style = WarpTextStyle.Title3,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = WarpTheme.dimensions.space1),
            )
            WarpButton(text = "Reset", onClick = {}, style = WarpButtonStyle.Quiet)
        },
    ) {
        WarpText(
            text = "Content below a custom header row.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}

@Preview(name = "Not draggable", showBackground = true)
@Composable
private fun WarpBottomSheetNotDraggablePreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        draggable = false,
        title = "Reorder photos",
    ) {
        WarpText(
            text = "Content with its own drag gesture, e.g. drag-to-reorder list items.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}
