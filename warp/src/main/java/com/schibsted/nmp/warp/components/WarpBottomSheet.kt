package com.schibsted.nmp.warp.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch

/**
 * The top-left back button of a [WarpBottomSheet]. The sheet decides how it looks.
 *
 * System back isn't routed here; handle it with a `BackHandler` in the sheet's content.
 *
 * @param onClick Called on tap with the sheet's `dismiss` callback, e.g. to go to the previous
 * step, or to dismiss on the first step.
 */
internal data class BottomSheetBackButton(val onClick: (dismiss: () -> Unit) -> Unit)

/**
 * The top-right action of a [WarpBottomSheet]. The sheet decides how it looks.
 */
internal sealed interface BottomSheetAction {
    val onClick: () -> Unit

    /** An icon button, labelled with the icon's own description. */
    data class Icon(
        val icon: WarpIconResource,
        override val onClick: () -> Unit,
    ) : BottomSheetAction

    /** A quiet text button. */
    data class Text(
        val text: String,
        override val onClick: () -> Unit,
    ) : BottomSheetAction
}

/**
 * A modal bottom sheet anchored to the bottom of the screen that dims content behind it.
 *
 * Wraps Material 3's [ModalBottomSheet] with Warp's surface and drag-handle color tokens; shape
 * and handle size are left at M3 defaults. The sheet always opens fully expanded and always shows
 * a drag handle, which is also its accessible dismiss control. Swiping down, tapping the scrim or
 * pressing back always dismisses. For sub-navigation, intercept back with a `BackHandler` in
 * [content].
 *
 * A header row is shown above [content] when [backButton], [title] or [action] is set.
 *
 * @param onDismissRequest Called once the hide animation completes. Also passed to [content] as
 * `dismiss`, so in-sheet actions can close the sheet the same way.
 * @param modifier Applied to the sheet surface, on top of Warp's own status-bar inset cap.
 * @param backButton Optional back button top left.
 * @param title Optional plain-text heading.
 * @param action Optional action top right.
 * @param content Sheet body; receives a `dismiss` callback (see [onDismissRequest]).
 */
@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WarpBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    backButton: BottomSheetBackButton? = null,
    title: String? = null,
    action: BottomSheetAction? = null,
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
        containerColor = WarpTheme.colors.surface.elevated100,
        contentColor = WarpTheme.colors.text.default,
        // Top excluded - already handled by the modifier above.
        contentWindowInsets = { WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal) },
        dragHandle = { BottomSheetDefaults.DragHandle(color = WarpTheme.colors.background.subtleActive) },
    ) {
        if (backButton != null || title != null || action != null) {
            Row(
                // No top padding - M3's 48dp handle already spaces the header from the top edge.
                // No bottom padding - content owns its spacing below the header.
                // Min height matches a button's touch target, so headers are the same height with or without buttons.
                // A back button brings its own 12dp inset, so the row's start padding shrinks to keep the icon at 16dp.
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = if (backButton != null) WarpTheme.dimensions.space05 else WarpTheme.dimensions.space2,
                        end = WarpTheme.dimensions.space05,
                    )
                    .heightIn(min = LocalMinimumInteractiveComponentSize.current),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (backButton != null) {
                    IconButton(onClick = { backButton.onClick(dismiss) }) {
                        WarpIcon(icon = icons.arrowLeft)
                    }
                }
                if (title != null) {
                    WarpText(
                        text = title,
                        style = WarpTextStyle.Title3,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = if (backButton != null) WarpTheme.dimensions.space05 else 0.dp),
                    )
                } else {
                    Spacer(Modifier.weight(1f))
                }
                when (action) {
                    is BottomSheetAction.Icon -> IconButton(onClick = action.onClick) {
                        WarpIcon(icon = action.icon)
                    }

                    is BottomSheetAction.Text -> WarpButton(
                        text = action.text,
                        onClick = action.onClick,
                        style = WarpButtonStyle.Quiet,
                    )

                    null -> Unit
                }
            }
        }
        content(dismiss)
    }
}

@Suppress("DEPRECATION")
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

@Preview(name = "Text action", showBackground = true)
@Composable
private fun WarpBottomSheetTextActionPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        title = "Filters",
        action = BottomSheetAction.Text("Reset") {},
    ) {
        WarpText(
            text = "Content below a header with a quiet text action.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}

@Preview(name = "Back and icon action", showBackground = true)
@Composable
private fun WarpBottomSheetBackAndIconActionPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        backButton = BottomSheetBackButton {},
        title = "Step 2 of 2",
        action = BottomSheetAction.Icon(icons.filter) {},
    ) {
        WarpText(
            text = "Content below a header with a back button and an icon action.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}
