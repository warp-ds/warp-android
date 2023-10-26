package com.schibsted.nmp.warp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

interface WarpColors {
    val background : WarpBackgroundColors
    val border : WarpBorderColors
    val icon : WarpIconColors
    val text : WarpTextColors
    val components: WarpComponentColors
}

interface WarpBackgroundColors {
    val default : Color
    val active : Color
    val disabled : Color
    val subtle : Color
    val subtleActive : Color
    val selected : Color

    val inverted : Color

    val primary : Color
    val primaryActive : Color
    val primarySubtle : Color
    val primarySubtleActive : Color

    val positive : Color
    val positiveActive : Color
    val positiveSubtle : Color
    val positiveSubtleActive : Color
    val positiveSelected : Color

    val negative : Color
    val negativeActive : Color
    val negativeSubtle : Color
    val negativeSubtleActive : Color
    val negativeSelected : Color

    val warning : Color
    val warningActive : Color
    val warningSubtle : Color
    val warningSubtleActive : Color
    val warningSelected : Color

    val info : Color
    val infoActive : Color
    val infoSubtle : Color
    val infoSubtleActive : Color
    val infoSelected : Color

    val notification : Color
}

interface WarpBorderColors {
    val default : Color
    val active : Color
    val disabled : Color
    val selected : Color

    val primary : Color
    val primaryActive : Color
    val primarySubtle : Color
    val primarySubtleActive : Color

    val positive : Color
    val positiveActive : Color
    val positiveSubtle : Color
    val positiveSubtleActive : Color

    val negative : Color
    val negativeActive : Color
    val negativeSubtle : Color
    val negativeSubtleActive : Color

    val warning : Color
    val warningActive : Color
    val warningSubtle : Color
    val warningSubtleActive : Color

    val info : Color
    val infoActive : Color
    val infoSubtle : Color
    val infoSubtleActive : Color
}

interface WarpIconColors {
    val default : Color
    val active : Color
    val selected : Color
    val disabled : Color
    val subtle : Color
    val subtleActive : Color
    val inverted : Color
    val invertedActive : Color
    val primary : Color
    val positive : Color
    val negative : Color
    val warning : Color
    val info : Color
    val notification : Color
}

interface WarpTextColors {
    val default : Color
    val subtle : Color
    val placeholder : Color
    val inverted : Color
    val invertedSubtle : Color
    val link : Color
    val disabled : Color
    val negative : Color
    val positive : Color
    val notification : Color
}

interface WarpComponentColors {
    val badge: WarpBadgeColors
    val button: WarpButtonColors
    val box: WarpBoxColors
    val stepIndicator: WarpStepIndicatorColors
    val alert: WarpAlertColors
    val textField: WarpTextFieldColors
    val tabRow: WarpTabRowColors
    val tab: WarpTabColors
    val label: WarpLabelColors
    val callout: WarpCalloutColors
}

interface WarpCalloutColors {
    val background: Color
    val border: Color
    val text: Color
}

interface WarpLabelColors {
    val text: Color
    val negative: Color
    val optional: Color
    val icon: Color
}

interface WarpBadgeColors {
    val infoBackground: Color
    val infoText: Color
    val positiveBackground: Color
    val positiveText: Color
    val warningBackground: Color
    val warningText: Color
    val negativeBackground: Color
    val negativeText: Color
    val disabledBackground: Color
    val disabledText: Color
    val neutralBackground: Color
    val neutralText: Color
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
    val errorIconColor: Color
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
    val background: Color
    val backgroundActive: Color
    val backgroundTrack: Color
    val backgroundTrackActive: Color
    val border: Color
    val borderActive: Color
    val icon: Color
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

val White = Color.White
val Black = Color.Black
val Transparent = Color.Transparent

internal val LocalColors = staticCompositionLocalOf<WarpColors> { Placeholders }
