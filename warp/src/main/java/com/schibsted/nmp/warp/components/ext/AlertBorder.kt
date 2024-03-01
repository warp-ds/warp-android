package com.schibsted.nmp.warp.components.ext

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

/**
 * Draws a background, border and a thicker line on the left side
 */
internal fun Modifier.alertBorder(
    bigStrokeWidth: Dp,
    smallStrokeWidth: Dp,
    background: Color,
    color: Color,
    outlineColor: Color,
    shape: Shape
) = composed{
    val density = LocalDensity.current
    val (strokeWidthPx, smallStrokeWidthPx) = density.run { bigStrokeWidth.toPx() to smallStrokeWidth.toPx() }
    this.clip(shape).then(Modifier.drawBehind {


        val outline = shape.createOutline(size, layoutDirection, this)

        drawOutline(outline = outline, color = background)
        drawOutline(outline = outline,color = outlineColor, style = Stroke(smallStrokeWidthPx))

        drawLine(
            color = color,
            // start in 0,0 but offset x with half of the stroke width so the entire line is within the views bounds
            start = Offset(
                x = strokeWidthPx/2f,
                y = 0f
            ),
            // end in 0,size.height but use the same offset as above on x
            end = Offset(
                x = strokeWidthPx/2f,
                y = size.height
            ),
            strokeWidth = strokeWidthPx
        )
    })
}
