package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

interface WarpShapes {
    val roundedSmall: Shape
    val roundedMedium: Shape
    val ellipse: Shape
    val components: WarpComponentShapes
}

interface WarpComponentShapes {
    val badge: WarpBadgeShapes
    val callout: Shape
    val button: Shape
}

interface WarpBadgeShapes {
    val default: Shape
    val topStart: Shape
    val topEnd: Shape
    val bottomEnd: Shape
    val bottomStart: Shape
}

internal val LocalShapes = staticCompositionLocalOf<WarpShapes> { PlaceholderShapes }
