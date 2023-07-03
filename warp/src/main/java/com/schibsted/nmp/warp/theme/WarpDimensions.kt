package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf

object WarpDimensions {
    val space025: Int = 2
    val space1: Int = 8
    val space2: Int = 16
    val borderRadius3: Int = 8
    val borderWidth1: Int = 1
    val borderWidth2: Int = 2
    val shadowSmall: Int = 1
}

internal val LocalDimensions = staticCompositionLocalOf { WarpDimensions }