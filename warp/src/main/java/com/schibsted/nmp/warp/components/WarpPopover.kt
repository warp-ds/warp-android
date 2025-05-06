package com.schibsted.nmp.warp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.schibsted.nmp.warp.components.ext.popoverPadding
import com.schibsted.nmp.warp.components.ext.shadowMedium
import com.schibsted.nmp.warp.components.shapes.popoverShape
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.components.utils.EdgePositionProvider
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * @param modifier Modifier for the popover
 * @param title Title of the popover
 * @param body Body text of the popover
 * @param state WarpPopoverState of the popover
 * @param edge Edge of the popover. Defaults to [Edge.Top]
 * @param horizontalOffset Horizontal offset of the popover. Defaults to 0.dp
 * @param verticalOffset Vertical offset of the popover. Defaults to 0.dp
 * @param inline If true, the popover will be inline with the anchor view. Defaults to false
 * @param dismissPopoverOnClickOutside If true, the popover will be dismissed when clicking outside. Defaults to true
 * @param onDismiss Callback to be invoked when the popover is dismissed.
 * @param anchorView View to anchor the popover to. Defaults to null
 */
@Composable
fun WarpPopover(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    state: WarpPopoverState,
    edge: Edge = Edge.Top,
    horizontalOffset: Dp = 0.dp,
    verticalOffset: Dp = 0.dp,
    inline: Boolean = false,
    dismissPopoverOnClickOutside: Boolean = true,
    onDismiss: () -> Unit = {},
    anchorView: @Composable (() -> Unit)? = null,
) {
    val popupPositionProvider = EdgePositionProvider(
        offset = DpOffset(horizontalOffset, verticalOffset),
        density = LocalDensity.current,
        edge = edge
    )
    var anchorWidth: Dp? = null
    var anchorPosition: Offset? = null

    if (inline) {
        if (state.isVisible) {
            WarpPopoverView(edge, modifier, title, body, anchorWidth, inline, anchorPosition)
        }
    } else {
        Box {
            anchorView?.let {
                Box(
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        anchorWidth = coordinates.size.width.dp
                        anchorPosition = coordinates.positionInWindow()
                    }
                ) {
                    it()
                }
            }
            if (state.isVisible) {
                Popup(
                    popupPositionProvider = popupPositionProvider,
                    onDismissRequest = onDismiss,
                    properties = PopupProperties(dismissOnClickOutside = dismissPopoverOnClickOutside)
                ) {
                    WarpPopoverView(
                        edge,
                        modifier,
                        title,
                        body,
                        anchorWidth,
                        inline,
                        anchorPosition
                    )
                }
            }
        }
    }
}

@Composable
internal fun WarpPopoverView(
    edge: Edge,
    modifier: Modifier,
    title: String,
    body: String,
    offset: Dp?,
    inline: Boolean,
    anchorPosition: Offset?
) {
    val shadow = if (inline) Modifier.shadow(
        5.dp, popoverShape(edge)
    ) else {
        Modifier.shadowMedium(popoverShape(edge, offset, anchorPosition))
    }
    Box(
        modifier = Modifier
            .then(modifier)
            .then(shadow)
            .background(colors.surface.elevated300)
            .popoverPadding(edge)

    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(
                dimensions.space05,
                Alignment.CenterVertically
            )
        ) {
            WarpText(
                text = title,
                style = WarpTextStyle.CaptionStrong,
                maxLines = 1
            )
            WarpText(
                text = body,
                style = WarpTextStyle.Caption
            )
        }
    }
}

@Stable
class WarpPopoverState(isVisible: Boolean = false) {
    var isVisible: Boolean by mutableStateOf(isVisible)
}