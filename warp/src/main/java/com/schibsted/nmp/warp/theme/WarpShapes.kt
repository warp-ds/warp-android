package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

interface WarpShapes {
    val medium: Shape
}

internal val LocalShapes = staticCompositionLocalOf<WarpShapes> { PlaceholderShapes }
