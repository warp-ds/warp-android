package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf

data class WarpDimensions(
    val space025: Int = 2,
    val space1: Int = 8,
    val space2: Int = 16,
    val borderRadius3: Int = 8
)

internal val LocalDimensions = staticCompositionLocalOf { WarpDimensions() }