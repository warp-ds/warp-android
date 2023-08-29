package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import com.schibsted.nmp.warp.brands.finn.FinnShapes

interface WarpShapes {
    val medium: Shape
    val components: WarpComponentShapes
}

interface WarpComponentShapes {
    val badge: WarpBadgeShapes
}

interface WarpBadgeShapes {
    val default: Shape
    val topStart: Shape
    val topEnd: Shape
    val bottomEnd: Shape
    val bottomStart: Shape
}

internal val LocalShapes = staticCompositionLocalOf<WarpShapes> { FinnShapes(WarpDimensions) }
