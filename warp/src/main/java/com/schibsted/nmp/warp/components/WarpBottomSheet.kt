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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch

/**
 * Content of the [WarpBottomSheet] top bar, next to its navigation icon.
 *
 * @param title Heading text; empty shows no title.
 * @param subtitle Secondary text below the title; empty shows none.
 * @param size Title size.
 * @param actions Icon buttons shown top right, in order.
 */
data class BottomSheetTopBarConfiguration(
    val title: String = "",
    val subtitle: String = "",
    val size: BottomSheetTopBarSize = BottomSheetTopBarSize.Small,
    val actions: List<BottomSheetAction> = emptyList(),
)

enum class BottomSheetTopBarSize { Small, Medium }

/**
 * An icon button in the [WarpBottomSheet] top bar.
 *
 * @param contentDescription Describes the action, e.g. "Reset filters", not the icon.
 */
data class BottomSheetAction(
    val icon: WarpIconResource,
    val contentDescription: String,
    val onClick: () -> Unit,
)

/**
 * A modal bottom sheet anchored to the bottom of the screen that dims content behind it.
 *
 * Wraps Material 3's [ModalBottomSheet] with Warp's surface and drag-handle color tokens; shape
 * and handle size are left at M3 defaults. The sheet always opens fully expanded, and tapping the
 * scrim or pressing back always dismisses. For sub-navigation, intercept back with a `BackHandler`
 * in [content].
 *
 * A [WarpTopAppBar] is always shown above [content], with a close button top left.
 *
 * @param onDismissRequest Called once the hide animation completes. Also passed to [content] as
 * `dismiss`, so in-sheet actions can close the sheet the same way.
 * @param modifier Applied to the sheet surface, on top of Warp's own status-bar inset cap.
 * @param topBar Title, subtitle, size and actions of the top bar.
 * @param navigationIcon Top-left icon; defaults to a close button. Replace only for edge cases,
 * e.g. a back button in a multi-step sheet, and keep the sheet dismissible for assistive tech.
 * Receives the same `dismiss` callback as [content].
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
    topBar: BottomSheetTopBarConfiguration = BottomSheetTopBarConfiguration(),
    navigationIcon: @Composable (dismiss: () -> Unit) -> Unit = { dismiss ->
        IconButton(onClick = dismiss) {
            WarpIcon(icon = icons.close)
        }
    },
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
        WarpTopAppBar(
            titleText = topBar.title,
            subtitleText = topBar.subtitle,
            style = when (topBar.size) {
                BottomSheetTopBarSize.Small -> WarpAppBarStyle.Default
                BottomSheetTopBarSize.Medium -> WarpAppBarStyle.MediumFlexible
            },
            navigationIcon = { navigationIcon(dismiss) },
            actions = { topBar.actions.forEach { BottomSheetActionButton(it) } },
            // The sheet already applies insets.
            windowInsets = WindowInsets(0),
            topBarColors = WarpTopAppBarDefaults.colors(containerColor = WarpTheme.colors.surface.elevated100),
        )
        content(dismiss)
    }
}

@Composable
private fun BottomSheetActionButton(action: BottomSheetAction) {
    IconButton(onClick = action.onClick) {
        Icon(
            imageVector = action.icon.vector,
            contentDescription = action.contentDescription,
            tint = WarpTheme.colors.icon.default,
            modifier = Modifier.size(WarpTheme.dimensions.icon.default),
        )
    }
}

@Preview(name = "Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WarpBottomSheetTitlePreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        topBar = BottomSheetTopBarConfiguration(title = "Sort by"),
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

@Preview(name = "Subtitle and action", showBackground = true)
@Composable
private fun WarpBottomSheetSubtitleAndActionPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        topBar = BottomSheetTopBarConfiguration(
            title = "Filters",
            subtitle = "3 active",
            actions = listOf(BottomSheetAction(icons.filter, "Reset filters") {}),
        ),
    ) {
        WarpText(
            text = "Content below a top bar with a subtitle and an action.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}

@Preview(name = "Medium", showBackground = true)
@Composable
private fun WarpBottomSheetMediumPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        topBar = BottomSheetTopBarConfiguration(
            title = "Filters",
            subtitle = "3 active",
            size = BottomSheetTopBarSize.Medium,
        ),
    ) {
        WarpText(
            text = "Content below a medium top bar.",
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
        topBar = BottomSheetTopBarConfiguration(title = "Step 2 of 2"),
        navigationIcon = {
            IconButton(onClick = {}) {
                WarpIcon(icon = icons.arrowLeft)
            }
        },
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
        topBar = BottomSheetTopBarConfiguration(title = "Reorder photos"),
        draggable = false,
    ) {
        WarpText(
            text = "Content with its own drag gesture, e.g. drag-to-reorder list items.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}
