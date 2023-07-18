package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf

object WarpDimensions {
    val space025: Int = 2
    val space05: Int = 4
    val space1: Int = 8
    val space15: Int = 12
    val space2: Int = 16
    val space25: Int = 20
    val space3: Int = 24
    val space4: Int = 32
    val space5: Int = 40
    val space6: Int = 48
    val space7: Int = 56
    val space8: Int = 64
    val space10: Int = 80
    val space12: Int = 96
    val space14: Int = 112
    val space16: Int = 128

    val borderRadius1: Int = 2
    val borderRadius2: Int = 4
    val borderRadius3: Int = 8
    val borderRadius4: Int = 16
    val borderWidth1: Int = 1
    val borderWidth2: Int = 2
    val borderWidth3: Int = 4 // TODO: not sure about this one
    val shadowSmall: Int = 1
}

internal val LocalDimensions = staticCompositionLocalOf { WarpDimensions }