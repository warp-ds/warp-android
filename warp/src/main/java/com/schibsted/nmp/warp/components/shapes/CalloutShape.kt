package com.schibsted.nmp.warp.components.shapes

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.LayoutDirection
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions


@Composable
fun CalloutShape(arrowEdge: Edge): Shape {
    val tipHeight = with(LocalDensity.current) {
        dimensions.components.callout.arrowHeight.toPx()
    }
    val tipWidth = with(LocalDensity.current) {
        dimensions.components.callout.arrowWidth.toPx()
    }
    val cornerRadius = with(LocalDensity.current) {
        dimensions.components.callout.cornerRadius.toPx()
    }

    return when (arrowEdge) {
        Edge.Trailing -> {
            GenericShape { size: Size, layoutDirection: LayoutDirection ->
                addPath(Path().apply {
                    moveTo(cornerRadius, 0f)
                    lineTo(size.width - tipHeight - cornerRadius, 0f)
                    arcTo(
                        rect = Rect(
                            left = size.width - tipHeight - 2 * cornerRadius,
                            top = 0f,
                            right = size.width - tipHeight,
                            bottom = 2 * cornerRadius
                        ),
                        startAngleDegrees = 270f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(
                        x = size.width - tipHeight,
                        y = size.height / 2 - tipWidth / 2
                    )

                    arcTo(
                        rect = Rect(
                            left = size.width - tipHeight - tipWidth / 4,
                            top = size.height / 2 - tipWidth / 4,
                            right = size.width,
                            bottom = size.height / 2 + tipWidth / 4
                        ),
                        startAngleDegrees = 310f,
                        sweepAngleDegrees = 80f,
                        forceMoveTo = false
                    )

                    arcTo(
                        rect = Rect(
                            left = size.width - tipHeight - tipWidth / 4,
                            top = size.height / 2 - tipWidth / 4,
                            right = size.width,
                            bottom = size.height / 2 + tipWidth / 4
                        ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 30f,
                        forceMoveTo = false
                    )

                    lineTo(
                        x = size.width - tipHeight,
                        y = size.height / 2 + tipWidth / 2
                    )
                    lineTo(size.width - tipHeight, size.height - cornerRadius)
                    arcTo(
                        rect = Rect(
                            left = size.width - tipHeight - 2 * cornerRadius,
                            top = size.height - 2 * cornerRadius,
                            right = size.width - tipHeight,
                            bottom = size.height
                        ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(cornerRadius, size.height)
                    arcTo(
                        rect = Rect(
                            left = 0f,
                            top = size.height - 2 * cornerRadius,
                            right = 2 * cornerRadius,
                            bottom = size.height
                        ),
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(0f, cornerRadius)
                    arcTo(
                        rect = Rect(
                            left = 0f,
                            top = 0f,
                            right = 2 * cornerRadius,
                            bottom = 2 * cornerRadius
                        ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    close()
                })
            }
        }

        Edge.Top -> {
            GenericShape { size: Size, layoutDirection: LayoutDirection ->
                addPath(Path().apply {
                    moveTo(cornerRadius, tipHeight)
                    lineTo(size.width / 2 - tipWidth / 2, tipHeight)
                    arcTo(
                        rect = Rect(
                            left = size.width / 2 - tipWidth / 6,
                            top = -tipHeight / 6,
                            right = size.width / 2 + tipWidth / 4,
                            bottom = tipHeight / 2
                        ),
                        startAngleDegrees = 200f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    arcTo(
                        rect = Rect(
                            left = size.width / 2 - tipWidth / 4,
                            top = -tipHeight / 6,
                            right = size.width / 2 + tipWidth / 6,
                            bottom = tipHeight / 2
                        ),
                        startAngleDegrees = 270f,
                        sweepAngleDegrees = 70f,
                        forceMoveTo = false
                    )
                    lineTo(size.width / 2 + tipWidth / 2, tipHeight)
                    lineTo(size.width - cornerRadius, tipHeight)
                    arcTo(
                        rect = Rect(
                            left = size.width - 2 * cornerRadius,
                            top = tipHeight,
                            right = size.width,
                            bottom = tipHeight + 2 * cornerRadius
                        ),
                        startAngleDegrees = 270f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(size.width, size.height - cornerRadius)
                    arcTo(
                        rect = Rect(
                            left = size.width - 2 * cornerRadius,
                            top = size.height - 2 * cornerRadius,
                            right = size.width,
                            bottom = size.height
                        ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(cornerRadius, size.height)
                    arcTo(
                        rect = Rect(
                            left = 0f,
                            top = size.height - 2 * cornerRadius,
                            right = 2 * cornerRadius,
                            bottom = size.height
                        ),
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(0f, tipHeight + cornerRadius)
                    arcTo(
                        rect = Rect(
                            left = 0f,
                            top = tipHeight,
                            right = 2 * cornerRadius,
                            bottom = tipHeight + 2 * cornerRadius
                        ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    close()
                })
            }
        }

        Edge.Bottom -> {
            GenericShape { size: Size, layoutDirection: LayoutDirection ->
                addPath(Path().apply {
                    moveTo(cornerRadius, 0f)
                    lineTo(size.width - cornerRadius, 0f)
                    arcTo(
                        rect = Rect(
                            left = size.width - 2 * cornerRadius,
                            top = 0f,
                            right = size.width,
                            bottom = 2 * cornerRadius
                        ),
                        startAngleDegrees = 270f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(size.width, size.height - tipHeight - cornerRadius)
                    arcTo(
                        rect = Rect(
                            left = size.width - 2 * cornerRadius,
                            top = size.height - tipHeight - 2 * cornerRadius,
                            right = size.width,
                            bottom = size.height - tipHeight
                        ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(size.width / 2 + tipWidth / 2, size.height - tipHeight)
                    arcTo(
                        rect = Rect(
                            left = size.width / 2 - tipWidth / 5,
                            top = size.height - tipHeight - tipWidth / 6,
                            right = size.width / 2 + tipWidth / 6,
                            bottom = size.height
                        ),
                        startAngleDegrees = 50f,
                        sweepAngleDegrees = 80f,
                        forceMoveTo = false
                    )
                    lineTo(size.width / 2 - tipWidth / 2, size.height - tipHeight)
                    lineTo(cornerRadius, size.height - tipHeight)
                    arcTo(
                        rect = Rect(
                            left = 0f,
                            top = size.height - tipHeight - 2 * cornerRadius,
                            right = 2 * cornerRadius,
                            bottom = size.height - tipHeight
                        ),
                        startAngleDegrees = 70f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(0f, cornerRadius)
                    arcTo(
                        rect = Rect(
                            left = 0f,
                            top = 0f,
                            right = 2 * cornerRadius,
                            bottom = 2 * cornerRadius
                        ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    close()
                })
            }
        }

        Edge.Leading -> {
            GenericShape { size: Size, layoutDirection: LayoutDirection ->
                addPath(Path().apply {
                    moveTo(tipHeight + cornerRadius, 0f)
                    lineTo(size.width - cornerRadius, 0f)
                    arcTo(
                        rect = Rect(
                            left = size.width - 2 * cornerRadius,
                            top = 0f,
                            right = size.width,
                            bottom = 2 * cornerRadius
                        ),
                        startAngleDegrees = 270f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(size.width, size.height - cornerRadius)
                    arcTo(
                        rect = Rect(
                            left = size.width - 2 * cornerRadius,
                            top = size.height - 2 * cornerRadius,
                            right = size.width,
                            bottom = size.height
                        ),
                        startAngleDegrees = 0f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(tipHeight + cornerRadius, size.height)
                    arcTo(
                        rect = Rect(
                            left = tipHeight,
                            top = size.height - 2 * cornerRadius,
                            right = tipHeight + 2 * cornerRadius,
                            bottom = size.height
                        ),
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    lineTo(tipHeight, size.height / 2 + tipWidth / 2)
                    arcTo(
                        rect = Rect(
                            left = 0f,
                            top = size.height / 2 - tipWidth / 5,
                            right = tipHeight,
                            bottom = size.height / 2 + tipWidth / 5
                        ),
                        startAngleDegrees = 120f,
                        sweepAngleDegrees = 120f,
                        forceMoveTo = false
                    )
                    lineTo(tipHeight, size.height / 2 - tipWidth / 2)
                    lineTo(tipHeight, cornerRadius)
                    arcTo(
                        rect = Rect(
                            left = tipHeight,
                            top = 0f,
                            right = tipHeight + 2 * cornerRadius,
                            bottom = 2 * cornerRadius
                        ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    close()
                })
            }
        }
    }
}