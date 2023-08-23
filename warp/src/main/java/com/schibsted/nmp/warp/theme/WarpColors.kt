package com.schibsted.nmp.warp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.brands.finn.FinnColors

interface WarpColors {
    val primary: Color
    val secondary: Color
    val background: Color
    val components: WarpComponentColors
}

interface WarpComponentColors {
    val button: WarpButtonColors
    val box: WarpBoxColors
    val stepIndicator: WarpStepIndicatorColors
    val alert: WarpAlertColors
    val textField: WarpTextFieldColors
    val tabRow: WarpTabRowColors
    val tab: WarpTabColors
    val label: WarpLabelColors
}

interface WarpLabelColors {
    val text: Color
    val negative: Color
    val optional: Color
    val icon: Color
}

interface WarpTabColors {
    val selectedContent: Color
    val unselectedContent: Color
}
interface WarpTabRowColors {
    val container: Color
    val content: Color
    val indicator: Color
}
interface WarpTextFieldColors {
    val text: Color
    val hintText: Color
    val disabledText: Color
    val errorText: Color
    val readOnlyText: Color

    val errorIconColor: Color // TODO: check if we should have this?
    val background: Color
    val disabledBackground: Color

    val border: Color
    val activeBorder: Color
    val disabledBorder: Color
    val errorBorder: Color
    val caret: Color
    val iconTint: Color
}

interface WarpAlertColors {
    val info: WarpAlertStyleColors
    val positive: WarpAlertStyleColors
    val negative: WarpAlertStyleColors
    val warning: WarpAlertStyleColors
}

interface WarpAlertStyleColors {
    val background: Color
    val leftBorder: Color
    val border: Color
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

interface WarpStepIndicatorColors {
    val completed: Color
    val unvisited: Color
    val completedIconTint: Color
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

internal class WarpRippleTheme(
    private val rippleColor: Color
) : RippleTheme {
    @Composable
    override fun defaultColor(): Color = rippleColor

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            Color.Black,
            !isSystemInDarkTheme()
        )
}

internal val White = Color.White
internal val Black = Color.Black
internal val Transparent = Color.Transparent

internal val LocalColors = staticCompositionLocalOf<WarpColors> { FinnColors }
