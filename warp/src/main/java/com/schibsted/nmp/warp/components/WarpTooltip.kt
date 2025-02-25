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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.schibsted.nmp.warp.components.ext.shadowMedium
import com.schibsted.nmp.warp.components.ext.tooltipPadding
import com.schibsted.nmp.warp.components.shapes.CalloutShape
import com.schibsted.nmp.warp.components.shapes.TooltipShape
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.components.utils.EdgePositionProvider
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun WarpTooltip(
    modifier: Modifier = Modifier,
    state: WarpTooltipState,
    text: String,
    edge: Edge = Edge.Top,
    dismissOnClickOutside: Boolean = true,
    horizontalOffset: Dp = 0.dp,
    verticalOffset: Dp = 0.dp,
    anchorView: @Composable (() -> Unit)? = null,
) {
    val popupPositionProvider = EdgePositionProvider(
        offset = DpOffset(horizontalOffset, verticalOffset),
        density = LocalDensity.current,
        edge = edge
    )

    Box {
        anchorView?.let { it() }
        if (state.isVisible) {
            Popup(
                popupPositionProvider = popupPositionProvider,
                properties = PopupProperties(
                    dismissOnClickOutside = dismissOnClickOutside
                )
            ) {
                Box(
                    modifier = Modifier
                        .shadowMedium(TooltipShape(edge))
                        .then(modifier)
                        .border(
                            dimensions.borderWidth2,
                            colors.components.tooltip.backgroundStatic, CalloutShape(edge)
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
                            softWrap = true,
                        )
                    }
                }
            }
        }
    }
}


class WarpTooltipState(isVisible: Boolean = false) {
    var isVisible: Boolean by mutableStateOf(isVisible)
}