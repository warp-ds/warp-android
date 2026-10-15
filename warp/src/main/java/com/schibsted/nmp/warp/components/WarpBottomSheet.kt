package com.schibsted.nmp.warp.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch

/**
 * A modal bottom sheet anchored to the bottom of the screen that dims content behind it.
 *
 * Wraps Material 3's [ModalBottomSheet] with Warp's surface and drag-handle color tokens; shape
 * and handle size are left at M3 defaults. The sheet always opens fully expanded, and tapping the
 * scrim or pressing back always dismisses. For sub-navigation, intercept back with a `BackHandler`
 * in [content].
 *
 * A header row is always shown above [content]: a close button top left, an optional [title] and
 * an optional quiet action button top right.
 *
 * @param onDismissRequest Called once the hide animation completes. Also passed to [content] as
 * `dismiss`, so in-sheet actions can close the sheet the same way.
 * @param modifier Applied to the sheet surface, on top of Warp's own status-bar inset cap.
 * @param navigationIcon Top-left icon; defaults to a close button. Replace only for edge cases,
 * e.g. a back button in a multi-step sheet, and keep the sheet dismissible for assistive tech.
 * Receives the same `dismiss` callback as [content].
 * @param title Optional plain-text heading next to the navigation icon.
 * @param actionText Label for the top-right action. When null, no action is shown.
 * @param onActionClick Callback for the top-right action. Required when [actionText] is set.
 * @param draggable Whether the sheet shows a drag handle and responds to swipe. Set to `false` only
 * when content has its own vertical drag gestures, e.g. drag-to-reorder; scrolling lists don't need
 * it. The sheet can still be dismissed by close button, scrim tap and back press.
 * @param content Sheet body; receives a `dismiss` callback (see [onDismissRequest]).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (dismiss: () -> Unit) -> Unit = { dismiss ->
        IconButton(onClick = dismiss) {
            WarpIcon(icon = icons.close)
        }
    },
    title: String? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    draggable: Boolean = true,
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
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
    ) {
        Row(
            // M3's 48dp handle already spaces the header from the top edge; without it, we pad it ourselves.
            // No bottom padding - content owns its spacing below the header.
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = WarpTheme.dimensions.space05,
                    end = WarpTheme.dimensions.space05,
                    top = if (draggable) 0.dp else WarpTheme.dimensions.space2,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            navigationIcon(dismiss)
            if (title != null) {
                WarpText(
                    text = title,
                    style = WarpTextStyle.Title3,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = WarpTheme.dimensions.space05),
                    maxLines = 1,
                )
            } else {
                Spacer(Modifier.weight(1f))
            }
            actionText?.let {
                WarpButton(
                    text = it,
                    onClick = onActionClick ?: {},
                    style = WarpButtonStyle.Quiet,
                )
            }
        }
        content(dismiss)
    }
}

@Preview(name = "Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WarpBottomSheetTitlePreview() {
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

@Preview(name = "Title and action", showBackground = true)
@Composable
private fun WarpBottomSheetTitleAndActionPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        title = "Filters",
        actionText = "Reset",
        onActionClick = {},
    ) {
        WarpText(
            text = "Content below a header with a title and an action.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}

@Preview(name = "Custom navigation icon", showBackground = true)
@Composable
private fun WarpBottomSheetNavigationIconPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        navigationIcon = {
            IconButton(onClick = {}) {
                WarpIcon(icon = icons.arrowLeft)
            }
        },
        title = "Step 2 of 2",
    ) {
        WarpText(
            text = "Content with a back button replacing the close button.",
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
