package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

interface WarpShapes {
    val roundedMedium: Shape
    val ellipse: Shape
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

internal val LocalShapes = staticCompositionLocalOf<WarpShapes> { PlaceholderShapes }
