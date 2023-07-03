package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.brands.finn.FinnColors

interface WarpColors {
    val primary: Color
    val secondary: Color
    val button: WarpButtonColors
    val box: WarpBoxColors
}

interface WarpButtonColors {
    val primary: WarpButtonStyleColors
    val secondary: WarpButtonStyleColors
    val disabled: WarpButtonStyleColors
    val quiet: WarpButtonStyleColors
    val negative: WarpButtonStyleColors
    val negativeQuiet: WarpButtonStyleColors
    val utility: WarpButtonStyleColors
    val utilityQuiet: WarpButtonStyleColors
    val utilityOverlay: WarpButtonStyleColors
    val loading: WarpButtonStyleColors
}

interface WarpBoxColors {
    val neutral: WarpComponentBackground
    val info: WarpComponentBackground
    val bordered: WarpComponentBackground
}

data class WarpComponentBackground(
    val background: Color,
    val border: Color?
)

interface WarpButtonStyleColors {
    val text: Color
    val background: WarpButtonElementColors
    val border: WarpButtonElementColors?
}

interface WarpButtonElementColors {
    val default : Color
    val active: Color
}

internal val White = Color.White
internal val Black = Color.Black
internal val Transparent = Color.Transparent

internal val LocalColors = staticCompositionLocalOf<WarpColors> { FinnColors }
