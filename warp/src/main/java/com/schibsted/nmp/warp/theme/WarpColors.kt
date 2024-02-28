package com.schibsted.nmp.warp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

interface WarpColors {
    val surface: WarpSurfaceColors
    val background : WarpBackgroundColors
    val border : WarpBorderColors
    val icon : WarpIconColors
    val text : WarpTextColors
    val components: WarpComponentColors
}

interface WarpSurfaceColors {
    val sunken: Color
    val elevated100 : Color
    val elevated100Hover : Color
    val elevated100Active : Color
    val elevated200 : Color
    val elevated200Hover : Color
    val elevated200Active : Color
    val elevated300 : Color
    val elevated300Hover : Color
    val elevated300Active : Color
}

interface WarpBackgroundColors {
    val default : Color
    val hover : Color
    val active : Color
    val disabled : Color
    val disabledSubtle : Color
    val subtle : Color
    val subtleHover : Color
    val subtleActive : Color
    val selected : Color
    val selectedHover : Color
    val selectedActive : Color

    val inverted : Color

    val primary : Color
    val primaryHover : Color
    val primaryActive : Color
    val primarySubtle : Color
    val primarySubtleHover : Color
    val primarySubtleActive : Color

    val secondary : Color
    val secondaryHover : Color
    val secondaryActive : Color

    val positive : Color
    val positiveHover : Color
    val positiveActive : Color
    val positiveSubtle : Color
    val positiveSubtleHover : Color
    val positiveSubtleActive : Color

    val negative : Color
    val negativeHover : Color
    val negativeActive : Color
    val negativeSubtle : Color
    val negativeSubtleHover : Color
    val negativeSubtleActive : Color

    val warning : Color
    val warningHover : Color
    val warningActive : Color
    val warningSubtle : Color
    val warningSubtleHover : Color
    val warningSubtleActive : Color

    val info : Color
    val infoHover : Color
    val infoActive : Color
    val infoSubtle : Color
    val infoSubtleHover : Color
    val infoSubtleActive : Color

    val notification : Color
}

interface WarpBorderColors {
    val default : Color
    val hover : Color
    val active : Color
    val disabled : Color
    val selected : Color
    val selectedHover : Color
    val selectedActive : Color
    val focus : Color

    val primary : Color
    val primaryHover : Color
    val primaryActive : Color
    val primarySubtle : Color
    val primarySubtleHover : Color
    val primarySubtleActive : Color

    val secondary : Color
    val secondaryHover : Color
    val secondaryActive : Color

    val positive : Color
    val positiveHover : Color
    val positiveActive : Color
    val positiveSubtle : Color
    val positiveSubtleHover : Color
    val positiveSubtleActive : Color

    val negative : Color
    val negativeHover : Color
    val negativeActive : Color
    val negativeSubtle : Color
    val negativeSubtleHover : Color
    val negativeSubtleActive : Color

    val warning : Color
    val warningHover : Color
    val warningActive : Color
    val warningSubtle : Color
    val warningSubtleHover : Color
    val warningSubtleActive : Color

    val info : Color
    val infoHover : Color
    val infoActive : Color
    val infoSubtle : Color
    val infoSubtleHover : Color
    val infoSubtleActive : Color
}

interface WarpIconColors {
    val default : Color
    val static : Color
    val hover : Color
    val active : Color
    val selected : Color
    val selectedHover : Color
    val selectedActive : Color
    val disabled : Color
    val subtle : Color
    val subtleHover : Color
    val subtleActive : Color
    val inverted : Color
    val invertedHover : Color
    val invertedActive : Color
    val invertedStatic : Color
    val primary : Color
    val secondary : Color
    val secondaryHover : Color
    val secondaryActive : Color
    val positive : Color
    val negative : Color
    val warning : Color
    val info : Color
    val notification : Color
}

interface WarpTextColors {
    val default : Color
    val static : Color
    val subtle : Color
    val placeholder : Color
    val inverted : Color
    val invertedStatic : Color
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
    val callout: WarpCalloutColors
    val pill: WarpPillColors
    val avatar : WarpAvatarColors
}

interface WarpAvatarColors {
    val background: Color
    val icon: Color
}

interface WarpPillColors {
    val filterTextColor: Color
    val filterIconColor: Color
    val suggestion: Pair<Color, Color>
}

interface WarpCalloutColors {
    val background: Color
    val border: Color
    val text: Color
}

interface WarpBadgeColors {
    val infoBackground: Color
    val positiveBackground: Color
    val warningBackground: Color
    val negativeBackground: Color
    val disabledBackground: Color
    val neutralBackground: Color
    val sponsoredBackground: Color
    val priceBackground: Color
}

interface WarpButtonColors {
    val loading: Pair<Color, Color>
    val primary: Pair<Color, Color>
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
val Black70Alpha = Color(0x70000000)

internal val LocalColors = staticCompositionLocalOf<WarpColors> { Placeholders }
