package com.schibsted.nmp.warp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
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
import com.schibsted.nmp.warp.components.ext.shadowMedium
import com.schibsted.nmp.warp.components.ext.tooltipPadding
import com.schibsted.nmp.warp.components.shapes.tooltipShape
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.components.utils.EdgePositionProvider
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * @param modifier Modifier for the tooltip
 * @param state WarpTooltipState of the tooltip. used to toggle hide/show
 * @param text Text to be displayed in the tooltip
 * @param edge Edge of the tooltip that indicates the anchor of the tooltip
 * @param horizontalOffset Horizontal offset of the tooltip
 * @param verticalOffset Vertical offset of the tooltip
 * @param inline If true, the tooltip will be displayed inline, no anchor is needed
 * @param anchorView The view which the Tooltip is anchored to. Will always align on the center point.
 */
@Composable
fun WarpTooltip(
    modifier: Modifier = Modifier,
    state: WarpTooltipState,
    text: String,
    edge: Edge = Edge.Top,
    horizontalOffset: Dp = 0.dp,
    verticalOffset: Dp = 0.dp,
    inline: Boolean = false,
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
            WarpTooltipView(edge, modifier, text, anchorWidth, inline, anchorPosition)
        }
    } else {
        Box {
            anchorView?.let { view ->
                Box(
                    modifier = Modifier.onGloballyPositioned { coordinates ->
                        anchorWidth = coordinates.size.width.dp
                        anchorPosition = coordinates.positionInWindow()
                    }
                ) {
                    view()
                }
            }
            if (state.isVisible) {
                Popup(
                    popupPositionProvider = popupPositionProvider
                ) {
                    WarpTooltipView(edge, modifier, text, anchorWidth, inline, anchorPosition)
                }
            }
        }
    }
}

@Composable
internal fun WarpTooltipView(
    edge: Edge,
    modifier: Modifier,
    text: String,
    offset: Dp?,
    inline: Boolean,
    anchorPosition: Offset?
) {
    val shadow = if (inline) Modifier.shadow(
        0.5.dp, tooltipShape(edge)
    ) else {
        Modifier.shadowMedium(tooltipShape(edge, offset, anchorPosition))
    }
    Box(
        modifier = Modifier
            .then(modifier)
            .then(shadow)
            .border(
                dimensions.borderWidth2,
                colors.components.tooltip.backgroundStatic,
                tooltipShape(edge, offset, anchorPosition)
            )
            .background(colors.components.tooltip.backgroundStatic)
            .tooltipPadding(edge)

    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensions.space05),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WarpText(
                text = text,
                color = colors.text.invertedStatic,
                style = WarpTextStyle.Caption,
                maxLines = 1,
                softWrap = true,
            )
        }
    }
}


class WarpTooltipState(isVisible: Boolean = false) {
    var isVisible: Boolean by mutableStateOf(isVisible)
}