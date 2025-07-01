package com.schibsted.nmp.warp.components.shapes

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.CalloutSize
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions


@Composable
fun calloutShape(
    arrowEdge: Edge,
    anchorWidth: Dp? = null,
    anchorPosition: Offset? = null,
    padding: Dp? = null,
    size: CalloutSize
): Shape {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    val tipHeight = with(density) {
        dimensions.components.callout.arrowHeight.toPx()
    }
    val tipWidth = with(density) {
        dimensions.components.callout.arrowWidth.toPx()
    }
    val cornerRadius = with(density) {
        dimensions.components.callout.cornerRadius.toPx()
    }
    var alignment = Alignment.CenterHorizontally

    val paddingOffset: Float = with(density) {
        padding?.toPx()
    } ?: 0f

    val sizeOffset = when (size) {
        CalloutSize.Small -> 0f
        CalloutSize.Default -> 3f
    }

    if (anchorPosition != null && anchorWidth != null) {
        val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }
        val anchorWidthPx = with(density) { anchorWidth.toPx() }
        val anchorCenterX = anchorPosition.x + (anchorWidthPx / 2)

        val leftThreshold = screenHeightPx / 3
        val rightThreshold = screenHeightPx * 2 / 3
        val centerThreshold = screenHeightPx / 2

        alignment = when {
            anchorCenterX < leftThreshold && anchorCenterX < centerThreshold -> Alignment.Start
            anchorCenterX < rightThreshold && anchorCenterX > centerThreshold -> Alignment.End
            else -> Alignment.CenterHorizontally
        }
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
                        x = size.width - tipHeight + sizeOffset,
                        y = size.height / 2 - tipWidth / 2 + sizeOffset
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
                        x = size.width - tipHeight - sizeOffset,
                        y = size.height / 2 + tipWidth / 2
                    )
                    lineTo(
                        size.width - tipHeight + sizeOffset / 2,
                        size.height - cornerRadius - sizeOffset
                    )
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
                val shapeWidth = size.width
                var diff = 0f
                if (anchorWidth != null && shapeWidth > anchorWidth.value) {
                    diff = when (alignment) {
                        Alignment.Start -> -(shapeWidth - anchorWidth.value) / 2 + paddingOffset
                        Alignment.End -> (shapeWidth - anchorWidth.value) / 2 - paddingOffset
                        else -> 0f
                    }
                }
                addPath(Path().apply {
                    moveTo(cornerRadius, tipHeight)
                    lineTo((size.width / 2 - tipWidth / 2) + diff, tipHeight)
                    arcTo(
                        rect = Rect(
                            left = (size.width / 2 - tipWidth / 6) + diff,
                            top = -tipHeight / 6,
                            right = (size.width / 2 + tipWidth / 4) + diff,
                            bottom = tipHeight / 2
                        ),
                        startAngleDegrees = 200f,
                        sweepAngleDegrees = 90f,
                        forceMoveTo = false
                    )
                    arcTo(
                        rect = Rect(
                            left = (size.width / 2 - tipWidth / 4) + diff,
                            top = -tipHeight / 6,
                            right = (size.width / 2 + tipWidth / 6) + diff,
                            bottom = tipHeight / 2
                        ),
                        startAngleDegrees = 270f,
                        sweepAngleDegrees = 70f,
                        forceMoveTo = false
                    )
                    lineTo((size.width / 2 + tipWidth / 2) + diff, tipHeight)
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
                val shapeWidth = size.width
                var diff = 0f
                if (anchorWidth != null && shapeWidth > anchorWidth.value) {
                    diff = when (alignment) {
                        Alignment.Start -> -(shapeWidth - anchorWidth.value) / 2 + paddingOffset
                        Alignment.End -> (shapeWidth - anchorWidth.value) / 2 - paddingOffset
                        else -> 0f
                    }
                }
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
                    lineTo((size.width / 2 + tipWidth / 2) + diff, size.height - tipHeight)
                    arcTo(
                        rect = Rect(
                            left = (size.width / 2 - tipWidth / 5) + diff,
                            top = size.height - tipHeight - tipWidth / 6,
                            right = (size.width / 2 + tipWidth / 6) + diff,
                            bottom = size.height
                        ),
                        startAngleDegrees = 50f,
                        sweepAngleDegrees = 80f,
                        forceMoveTo = false
                    )
                    lineTo((size.width / 2 - tipWidth / 2) + diff, size.height - tipHeight)
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
                    lineTo(tipHeight + cornerRadius + sizeOffset / 2, size.height)
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
                    lineTo(tipHeight + sizeOffset, size.height / 2 + tipWidth / 2 + sizeOffset / 2)
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
                    lineTo(tipHeight, size.height / 2 - tipWidth / 2 + sizeOffset / 2)
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