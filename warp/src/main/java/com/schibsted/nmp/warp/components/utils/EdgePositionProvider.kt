package com.schibsted.nmp.warp.components.utils

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider

internal class EdgePositionProvider(
    val offset: DpOffset,
    val density: Density,
    val edge: Edge,
) : PopupPositionProvider {

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val contentOffsetX = with(density) { offset.x.roundToPx() }
        val contentOffsetY = with(density) { offset.y.roundToPx() }

        val xOffset = when (edge) {
            Edge.Top -> 0
            Edge.Bottom -> 0
            Edge.Leading -> -anchorBounds.width
            Edge.Trailing -> anchorBounds.width
        }
        val yOffset = when (edge) {
            Edge.Top -> anchorBounds.height
            Edge.Bottom -> -anchorBounds.height
            Edge.Leading -> 0
            Edge.Trailing -> 0
        }
        val alignment = when (edge) {
            Edge.Top -> Alignment.TopCenter
            Edge.Bottom -> Alignment.BottomCenter
            Edge.Leading -> Alignment.CenterStart
            Edge.Trailing -> Alignment.CenterEnd
        }
        val anchorAlignmentPoint = alignment.align(
            IntSize.Zero,
            anchorBounds.size,
            layoutDirection
        )
        val popupAlignmentPoint = -alignment.align(
            IntSize.Zero,
            popupContentSize,
            layoutDirection
        )
        val resolvedUserOffset = IntOffset(
            contentOffsetX * (if (layoutDirection == LayoutDirection.Ltr) 1 else -1) - xOffset,
            contentOffsetY + yOffset
        )

        return anchorBounds.topLeft +
                anchorAlignmentPoint +
                popupAlignmentPoint +
                resolvedUserOffset

    }
}