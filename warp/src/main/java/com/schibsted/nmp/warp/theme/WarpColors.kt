package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.brands.finn.FinnColors

interface WarpColors {
    val primary: Color
    val secondary: Color
    val button: WarpButtonColors
}

interface WarpButtonColors {
    val primary: WarpButtonStyleColors
    val secondary: WarpButtonStyleColors
    val disabled: WarpButtonStyleColors
    val quiet: WarpButtonStyleColors
}

interface WarpButtonStyleColors {
    val text: Color
    val background: WarpButtonElementColors
    val border: WarpButtonElementColors?
}

interface WarpButtonElementColors {
    val default : Color
    val active: Color
}

internal val LocalColors = staticCompositionLocalOf<WarpColors> { FinnColors() }
