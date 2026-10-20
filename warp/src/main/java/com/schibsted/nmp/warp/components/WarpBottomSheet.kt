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
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch

/**
 * The leading back button of a [WarpBottomSheet], replacing its close button, e.g. on later steps
 * of a multi-step sheet. System back isn't routed here; use a `BackHandler` in the content.
 *
 * @param onClick Receives the sheet's `dismiss` callback.
 */
data class BottomSheetBackButton(val onClick: (dismiss: () -> Unit) -> Unit)

/**
 * The trailing action of a [WarpBottomSheet], shown as a quiet text button. Not for dismissing;
 * the sheet has its own close button.
 *
 * @param destructive Shows the action as a quiet negative button, e.g. for Delete.
 */
data class BottomSheetAction(
    val text: String,
    val destructive: Boolean = false,
    val onClick: () -> Unit,
)

/**
 * Values used by [WarpBottomSheet].
 */
@Stable
object WarpBottomSheetDefaults {

    /** The sheet's surface color, for anything that must match it, e.g. a sticky footer. */
    val containerColor: Color
        @Composable
        get() = WarpTheme.colors.surface.elevated100
}

/**
 * A modal bottom sheet with a header row: a close button (or [backButton]), an optional [title]
 * and an optional [action]. Content taller than about half the screen opens half-expanded first,
 * unless [skipPartiallyExpanded] is set.
 *
 * @param onDismissRequest Called once the hide animation completes. Also passed to [content] as
 * `dismiss`, so in-sheet actions can close the sheet the same way.
 * @param modifier Applied to the sheet surface.
 * @param skipPartiallyExpanded Always open fully expanded, e.g. for forms that would be cut off.
 * @param backButton Optional leading back button.
 * @param title Optional plain-text heading. Use it rather than a heading in [content].
 * @param action Optional trailing action.
 * @param content Sheet body; receives `dismiss` (see [onDismissRequest]).
 */
@Composable
fun WarpBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = false,
    backButton: BottomSheetBackButton? = null,
    title: String? = null,
    action: BottomSheetAction? = null,
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit,
) {
    BottomSheetImpl(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        skipPartiallyExpanded = skipPartiallyExpanded,
        locked = false,
        backButton = backButton,
        title = title,
        action = action,
        content = content,
    )
}

/** Shared by [WarpBottomSheet] and [WarpLockableBottomSheet], which documents [locked]. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomSheetImpl(
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    skipPartiallyExpanded: Boolean,
    locked: Boolean,
    backButton: BottomSheetBackButton?,
    title: String?,
    action: BottomSheetAction?,
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    val dismiss: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) onDismissRequest()
        }
    }

    val dragHandleColor = WarpTheme.colors.background.subtleActive

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        // Caps the sheet below the status bar, so a scrim strip stays visible even with tall content.
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top))
            .then(modifier),
        sheetState = sheetState,
        containerColor = WarpBottomSheetDefaults.containerColor,
        contentColor = WarpTheme.colors.text.default,
        contentWindowInsets = { WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal) },
        sheetGesturesEnabled = !locked,
        properties = ModalBottomSheetProperties(shouldDismissOnClickOutside = !locked),
        dragHandle = if (locked) null else { { BottomSheetDefaults.DragHandle(color = dragHandleColor) } },
    ) {
        BlockBack(enabled = locked)
        if (locked) LockedDragHandle(color = dragHandleColor)
        Row(
            // The icon button's own 12dp inset plus this 4dp puts the icon at 16dp.
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = WarpTheme.dimensions.space05, end = WarpTheme.dimensions.space05)
                .heightIn(min = LocalMinimumInteractiveComponentSize.current),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (backButton != null) {
                IconButton(onClick = { backButton.onClick(dismiss) }, enabled = !locked) {
                    WarpIcon(
                        icon = icons.arrowLeft,
                        color = if (locked) WarpTheme.colors.icon.disabled else WarpTheme.colors.icon.default,
                    )
                }
            } else {
                IconButton(onClick = dismiss, enabled = !locked) {
                    WarpIcon(
                        icon = icons.close,
                        color = if (locked) WarpTheme.colors.icon.disabled else WarpTheme.colors.icon.default,
                    )
                }
            }
            if (title != null) {
                WarpText(
                    text = title,
                    style = WarpTextStyle.Title3,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = WarpTheme.dimensions.space05)
                        .semantics { heading() },
                )
            } else {
                Spacer(Modifier.weight(1f))
            }
            if (action != null) {
                WarpButton(
                    text = action.text,
                    onClick = action.onClick,
                    style = if (action.destructive) WarpButtonStyle.NegativeQuiet else WarpButtonStyle.Quiet,
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

@Preview(name = "Text action", showBackground = true)
@Composable
private fun WarpBottomSheetTextActionPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        title = "Filters",
        action = BottomSheetAction("Reset") {},
    ) {
        WarpText(
            text = "Content below a header with a quiet text action.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}

@Preview(name = "Back and action", showBackground = true)
@Composable
private fun WarpBottomSheetBackAndActionPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        backButton = BottomSheetBackButton {},
        title = "Step 2 of 2",
        action = BottomSheetAction("Reset") {},
    ) {
        WarpText(
            text = "Content below a header with a back button and a quiet text action.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}

@Preview(name = "Destructive action", showBackground = true)
@Composable
private fun WarpBottomSheetDestructiveActionPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        title = "Edit education",
        action = BottomSheetAction("Delete", destructive = true) {},
    ) {
        WarpText(
            text = "Content below a header with a close button and a quiet destructive action.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}
