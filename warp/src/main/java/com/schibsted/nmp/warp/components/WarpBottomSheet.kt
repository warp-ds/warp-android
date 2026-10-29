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
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch

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
 * The header Warp draws at the top of a [WarpBottomSheet]: an optional title and the close button.
 *
 * @param title Optional plain-text heading. Use it for the sheet's heading rather than a heading in
 * the sheet's content.
 */
data class WarpBottomSheetHeader(val title: String? = null)

/**
 * A modal bottom sheet. With a [header], Warp draws a title and a close button top right above
 * [content]. Content taller than about half the screen opens half-expanded first, unless
 * [skipPartiallyExpanded] is set. Swiping down, tapping the scrim, pressing back and the close
 * button all close it.
 *
 * @param onDismissRequest Called once the hide animation completes. Also passed to [content] as
 * `dismiss`, so in-sheet actions can close the sheet the same way.
 * @param header Warp's header with the close button. Prefer it: pass null only when the sheet needs
 * no header, or when its header needs more than a title, e.g. actions, and [content] draws it
 * instead. That header should then close the sheet with `dismiss`.
 * @param modifier Applied to the sheet surface.
 * @param skipPartiallyExpanded Always open fully expanded, e.g. for forms that would be cut off.
 * @param content Sheet body; receives `dismiss` (see [onDismissRequest]).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpBottomSheet(
    onDismissRequest: () -> Unit,
    header: WarpBottomSheetHeader?,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = false,
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val sheetState = if (LocalBottomSheetStartsOpen.current) {
        rememberOpenSheetState(skipPartiallyExpanded)
    } else {
        rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    }
    val dismiss: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) onDismissRequest()
        }
    }

    val closeLabel = stringResource(R.string.bottom_sheet_close)

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        // Caps the sheet below the status bar, so a scrim strip stays visible even with tall content.
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top))
            .then(modifier),
        sheetState = sheetState,
        containerColor = WarpBottomSheetDefaults.containerColor,
        contentColor = WarpTheme.colors.text.default,
        // Top excluded - already handled by the modifier above.
        contentWindowInsets = { WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal) },
        dragHandle = { BottomSheetDefaults.DragHandle(color = WarpTheme.colors.background.subtleActive) },
    ) {
        if (header != null) {
            Row(
                // No top padding - M3's 48dp handle already spaces the header from the top edge.
                // No bottom padding - content owns its spacing below the header.
                // Min height is a touch target, so the header grows with a long title instead of clipping.
                // The close button's own 12dp inset plus this 4dp puts its icon 16dp from the edge.
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = WarpTheme.dimensions.space2, end = WarpTheme.dimensions.space05)
                    .heightIn(min = LocalMinimumInteractiveComponentSize.current),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (header.title != null) {
                    WarpText(
                        text = header.title,
                        style = WarpTextStyle.Title3,
                        modifier = Modifier
                            .weight(1f)
                            .semantics { heading() },
                    )
                } else {
                    Spacer(Modifier.weight(1f))
                }
                // Merged before the icon's own description, so it reads e.g. "Close, X".
                IconButton(
                    onClick = dismiss,
                    modifier = Modifier.semantics { contentDescription = closeLabel },
                ) {
                    WarpIcon(
                        icon = icons.close,
                    )
                }
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
        header = WarpBottomSheetHeader(title = "Sort by"),
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

@Preview(name = "No title", showBackground = true)
@Composable
private fun WarpBottomSheetNoTitlePreview() {
    WarpBottomSheet(onDismissRequest = {}, header = WarpBottomSheetHeader()) {
        WarpText(
            text = "A sheet without a title: the header only holds the close button.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}
