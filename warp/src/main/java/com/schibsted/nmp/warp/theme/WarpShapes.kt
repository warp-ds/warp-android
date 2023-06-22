package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import com.schibsted.nmp.warp.brands.finn.FinnShapes

interface WarpShapes {
    val roundedCornerShape: Shape
    val circleShape: Shape
}

internal val LocalShapes = staticCompositionLocalOf<WarpShapes> { FinnShapes() }
