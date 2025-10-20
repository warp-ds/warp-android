package com.schibsted.nmp.warp.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A divider in the warp design system.
 * @param modifier Modifier for the divider. Default value is Modifier
 * @param orientation The orientation of the divider. Default value is Orientation.Horizontal
 * @param dashed Whether the divider should be dashed. Default value is false
 */
@Composable
fun WarpDivider(
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.Horizontal,
    dashed: Boolean = false
) {
    val thickness = dimensions.borderWidth1
    val color = colors.border.default
    val dashedPath = if (dashed) PathEffect.dashPathEffect(floatArrayOf(8f, 8f), 0f) else null

    when (orientation) {
        Orientation.Horizontal -> {
            Canvas(
                modifier
                    .fillMaxWidth()
                    .height(thickness)
            ) {
                drawLine(
                    color = color,
                    strokeWidth = thickness.toPx(),
                    start = Offset(0f, thickness.toPx() / 2),
                    end = Offset(size.width, thickness.toPx() / 2),
                    pathEffect = dashedPath
                )
            }
        }

        Orientation.Vertical -> {
            Canvas(modifier
                .fillMaxHeight()
                .width(thickness)) {
                drawLine(
                    color = color,
                    strokeWidth = thickness.toPx(),
                    start = Offset(thickness.toPx() / 2, 0f),
                    end = Offset(thickness.toPx() / 2, size.height),
                    pathEffect = dashedPath
                )
            }
        }
    }
}
