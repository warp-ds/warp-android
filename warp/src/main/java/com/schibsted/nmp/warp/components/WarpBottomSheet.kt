package com.schibsted.nmp.warp.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.takeOrElse
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Values used by [WarpBottomSheet].
 */
@Stable
object WarpBottomSheetDefaults {

    /** The sheet's surface color, for anything that must match it, e.g. a sticky footer. */
    val containerColor: Color
        @Composable
        get() = WarpTheme.colors.surface.elevated100

    /**
     * The header title's horizontal inset. Pad sheet content with it, so the content lines up with the
     * title and follows if the header's inset changes.
     */
    val contentHorizontalPadding: Dp
        @Composable
        get() = WarpTheme.dimensions.space2
}

/**
 * The header Warp draws at the top of a [WarpBottomSheet]: an optional icon, an optional title and an
 * optional close button. With none of them, Warp draws no header; see [empty].
 *
 * @param title Optional plain-text heading. Use it for the sheet's heading rather than a heading in
 * the sheet's content.
 * @param showCloseButton Shows a close button top right. The sheet can be closed without it too, by
 * swiping down, tapping the scrim, pressing back or with the drag handle's accessibility action.
 * @param icon Optional decorative icon left of the title. Warp owns its size.
 */
data class WarpBottomSheetHeader(
    val title: String? = null,
    val showCloseButton: Boolean = false,
    val icon: WarpBottomSheetHeaderIcon? = null,
) {
    companion object {
        /** A header that draws nothing, for sheets whose content keeps its own header. */
        fun empty() = WarpBottomSheetHeader()
    }
}

/** The icon left of a [WarpBottomSheetHeader]'s title. */
sealed interface WarpBottomSheetHeaderIcon {

    /**
     * A Warp icon, the same size as the close button.
     *
     * @param color The icon's tint. Unspecified uses the default icon color.
     */
    data class Icon(
        val icon: WarpIconResource,
        val color: Color = Color.Unspecified,
    ) : WarpBottomSheetHeaderIcon

    /**
     * An untinted image, e.g. a logo or a badge, fitted into a square of [size].
     *
     * @param size [ImageSize.Default] matches an [Icon]; [ImageSize.Large] is for artwork that needs more room.
     */
    data class Image(
        val painter: Painter,
        val size: ImageSize = ImageSize.Default,
    ) : WarpBottomSheetHeaderIcon

    /** The sizes an [Image] can take. */
    enum class ImageSize { Default, Large }
}

/**
 * A modal bottom sheet. Warp draws the [header], a title and an optional close button top right,
 * above [content]. Content taller than about half the screen opens half-expanded first, unless
 * [skipPartiallyExpanded] is set. Swiping down, tapping the scrim, pressing back and the close
 * button all close it.
 *
 * @param onDismissRequest Called once the hide animation completes. Also passed to [content] as
 * `dismiss`, so in-sheet actions can close the sheet the same way.
 * @param header Warp's header. Pass [WarpBottomSheetHeader.empty] when [content] draws its own
 * header, e.g. one with actions; it should then close the sheet with `dismiss`.
 * @param modifier Applied to the sheet surface.
 * @param skipPartiallyExpanded Always open fully expanded, e.g. for forms that would be cut off.
 * @param content Sheet body; receives `dismiss` (see [onDismissRequest]). Pad it horizontally with
 * [WarpBottomSheetDefaults.contentHorizontalPadding] to line up with the header's title.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpBottomSheet(
    onDismissRequest: () -> Unit,
    header: WarpBottomSheetHeader,
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
        if (header.icon != null || header.title != null || header.showCloseButton) {
            // The first row is a touch target tall, or one title line if that's taller. The title's first line,
            // the icon and the close button are centred in it, so a long title grows downwards and the icon and
            // close button stay put. The title keeps the same space below its last line.
            val minRowHeight = LocalMinimumInteractiveComponentSize.current
            val titleStyle = WarpTheme.typography.title3
            val titleLineHeightUnit = titleStyle.lineHeight.takeOrElse { titleStyle.fontSize }
            val titleLineHeight = if (titleLineHeightUnit.isSp) {
                with(LocalDensity.current) { titleLineHeightUnit.toDp() }
            } else {
                minRowHeight
            }
            val firstRowHeight = max(minRowHeight, titleLineHeight)
            val titleVerticalPadding = (firstRowHeight - titleLineHeight) / 2
            Row(
                // No top padding - M3's 48dp handle already spaces the header from the top edge.
                // The close button's own 12dp inset plus this 4dp puts its icon 16dp from the edge.
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = WarpBottomSheetDefaults.contentHorizontalPadding,
                        end = if (header.showCloseButton) {
                            WarpTheme.dimensions.space05
                        } else {
                            WarpBottomSheetDefaults.contentHorizontalPadding
                        },
                    )
                    .heightIn(min = minRowHeight),
                verticalAlignment = Alignment.Top,
            ) {
                if (header.icon != null) {
                    Box(Modifier.height(firstRowHeight), contentAlignment = Alignment.Center) {
                        HeaderIcon(header.icon)
                    }
                    Spacer(Modifier.width(WarpBottomSheetDefaults.contentHorizontalPadding))
                }
                if (header.title != null) {
                    WarpText(
                        text = header.title,
                        style = WarpTextStyle.Title3,
                        modifier = Modifier
                            .weight(1f)
                            .firstLineCentered(firstRowHeight, titleVerticalPadding)
                            .semantics { heading() },
                    )
                } else {
                    Spacer(Modifier.weight(1f))
                }
                if (header.showCloseButton) {
                    Box(Modifier.height(firstRowHeight), contentAlignment = Alignment.Center) {
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
            }
        }
        content(dismiss)
    }
}

/**
 * A title of one line is centred in [rowHeight], as a row would centre it. A taller title starts [padding] down and
 * keeps the same [padding] below, so its first line sits where a single line would.
 */
private fun Modifier.firstLineCentered(rowHeight: Dp, padding: Dp) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints.copy(minHeight = 0))
    val rowPx = rowHeight.roundToPx()
    if (placeable.height <= rowPx) {
        layout(placeable.width, rowPx) { placeable.place(0, ((rowPx - placeable.height) / 2f).roundToInt()) }
    } else {
        val paddingPx = padding.roundToPx()
        layout(placeable.width, placeable.height + 2 * paddingPx) { placeable.place(0, paddingPx) }
    }
}

@Composable
private fun HeaderIcon(icon: WarpBottomSheetHeaderIcon) {
    // Decorative: the title carries the meaning.
    val modifier = Modifier.clearAndSetSemantics {}
    when (icon) {
        is WarpBottomSheetHeaderIcon.Icon -> WarpIcon(
            icon = icon.icon,
            size = WarpTheme.dimensions.icon.default,
            color = icon.color.takeOrElse { WarpTheme.colors.icon.default },
            modifier = modifier,
        )
        is WarpBottomSheetHeaderIcon.Image -> Image(
            painter = icon.painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = modifier.size(
                when (icon.size) {
                    WarpBottomSheetHeaderIcon.ImageSize.Default -> WarpTheme.dimensions.icon.default
                    WarpBottomSheetHeaderIcon.ImageSize.Large -> WarpTheme.dimensions.icon.large
                },
            ),
        )
    }
}

@Preview(name = "Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WarpBottomSheetTitlePreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        header = WarpBottomSheetHeader(title = "Sort by", showCloseButton = true),
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
    WarpBottomSheet(onDismissRequest = {}, header = WarpBottomSheetHeader(showCloseButton = true)) {
        WarpText(
            text = "A sheet without a title: the header only holds the close button.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}

@Preview(name = "Leading icon", showBackground = true)
@Composable
private fun WarpBottomSheetLeadingIconPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        header = WarpBottomSheetHeader(
            title = "About the short version",
            showCloseButton = true,
            icon = WarpBottomSheetHeaderIcon.Icon(icons.sparkles),
        ),
    ) {
        WarpText(
            text = "A decorative icon left of the title.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}
