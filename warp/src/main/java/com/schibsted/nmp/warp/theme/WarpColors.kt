package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

interface WarpColors {
    val surface: WarpSurfaceColors
    val background: WarpBackgroundColors
    val border: WarpBorderColors
    val icon: WarpIconColors
    val text: WarpTextColors
    val components: WarpComponentColors
    val dataviz: WarpDatavizColors
}

interface WarpDatavizColors {
    val chart: WarpDatavizChartColors
    val background: WarpDatavizBackgroundColors
    val line: WarpDatavizLineColors
    val border: WarpDatavizBorderColors
    val text: WarpDatavizTextColors
    val icon: WarpDatavizIconColors
}

interface WarpDatavizChartColors {
    val backgroundDefault: Color
    val chartLineDefault: Color
    val chartLineSubtle: Color
    val chartTextDefault: Color
    val chartTextSubtle: Color
}

interface WarpDatavizBackgroundColors {
    val primary: Color
    val primarySubtle: Color
    val primaryHighlight: Color
    val primarySubtleHighlight: Color
    val secondary: Color
    val secondarySubtle: Color
    val secondaryHighlight: Color
    val secondarySubtleHighlight: Color
    val category1: Color
    val category1Subtle: Color
    val category1Highlight: Color
    val category1SubtleHighlight: Color
    val category2: Color
    val category2Subtle: Color
    val category2Highlight: Color
    val category2SubtleHighlight: Color
    val category3: Color
    val category3Subtle: Color
    val category3Highlight: Color
    val category3SubtleHighlight: Color
    val category4: Color
    val category4Subtle: Color
    val category4Highlight: Color
    val category4SubtleHighlight: Color
    val category5: Color
    val category5Subtle: Color
    val category5Highlight: Color
    val category5SubtleHighlight: Color
    val category6: Color
    val category6Subtle: Color
    val category6Highlight: Color
    val category6SubtleHighlight: Color
    val category7: Color
    val category7Subtle: Color
    val category7Highlight: Color
    val category7SubtleHighlight: Color
    val category8: Color
    val category8Subtle: Color
    val category8Highlight: Color
    val category8SubtleHighlight: Color
    val positive: Color
    val positiveSubtle: Color
    val positiveHighlight: Color
    val positiveSubtleHighlight: Color
    val warning: Color
    val warningSubtle: Color
    val warningHighlight: Color
    val warningSubtleHighlight: Color
    val negative: Color
    val negativeSubtle: Color
    val negativeHighlight: Color
    val negativeSubtleHighlight: Color
    val neutral: Color
    val neutralSubtle: Color
    val neutralHighlight: Color
    val neutralSubtleHighlight: Color
}

interface WarpDatavizLineColors {
    val primary: Color
    val primaryHighlight: Color
    val secondary: Color
    val secondaryHighlight: Color
    val category1: Color
    val category1Highlight: Color
    val category2: Color
    val category2Highlight: Color
    val category3: Color
    val category3Highlight: Color
    val category4: Color
    val category4Highlight: Color
    val category5: Color
    val category5Highlight: Color
    val category6: Color
    val category6Highlight: Color
    val category7: Color
    val category7Highlight: Color
    val category8: Color
    val category8Highlight: Color
    val positive: Color
    val positiveHighlight: Color
    val warning: Color
    val warningHighlight: Color
    val negative: Color
    val negativeHighlight: Color
    val neutral: Color
    val neutralHighlight: Color
}

interface WarpDatavizBorderColors {
    val primary: Color
    val primaryHighlight: Color
    val secondary: Color
    val secondaryHighlight: Color
    val category1: Color
    val category1Highlight: Color
    val category2: Color
    val category2Highlight: Color
    val category3: Color
    val category3Highlight: Color
    val category4: Color
    val category4Highlight: Color
    val category5: Color
    val category5Highlight: Color
    val category6: Color
    val category6Highlight: Color
    val category7: Color
    val category7Highlight: Color
    val category8: Color
    val category8Highlight: Color
    val positive: Color
    val positiveHighlight: Color
    val warning: Color
    val warningHighlight: Color
    val negative: Color
    val negativeHighlight: Color
    val neutral: Color
    val neutralHighlight: Color
}

interface WarpDatavizTextColors {
    val primary: Color
    val primaryHighlight: Color
    val secondary: Color
    val secondaryHighlight: Color
    val category1: Color
    val category1Highlight: Color
    val category2: Color
    val category2Highlight: Color
    val category3: Color
    val category3Highlight: Color
    val category4: Color
    val category4Highlight: Color
    val category5: Color
    val category5Highlight: Color
    val category6: Color
    val category6Highlight: Color
    val category7: Color
    val category7Highlight: Color
    val category8: Color
    val category8Highlight: Color
    val positive: Color
    val positiveHighlight: Color
    val warning: Color
    val warningHighlight: Color
    val negative: Color
    val negativeHighlight: Color
    val neutral: Color
    val neutralHighlight: Color
}

interface WarpDatavizIconColors {
    val primary: Color
    val primaryHighlight: Color
    val secondary: Color
    val secondaryHighlight: Color
    val category1: Color
    val category1Highlight: Color
    val category2: Color
    val category2Highlight: Color
    val category3: Color
    val category3Highlight: Color
    val category4: Color
    val category4Highlight: Color
    val category5: Color
    val category5Highlight: Color
    val category6: Color
    val category6Highlight: Color
    val category7: Color
    val category7Highlight: Color
    val category8: Color
    val category8Highlight: Color
    val positive: Color
    val positiveHighlight: Color
    val warning: Color
    val warningHighlight: Color
    val negative: Color
    val negativeHighlight: Color
    val neutral: Color
    val neutralHighlight: Color
}

interface WarpSurfaceColors {
    val sunken: Color
    val elevated100: Color
    val elevated100Hover: Color
    val elevated100Active: Color
    val elevated200: Color
    val elevated200Hover: Color
    val elevated200Active: Color
    val elevated300: Color
    val elevated300Hover: Color
    val elevated300Active: Color
}

interface WarpBackgroundColors {
    val default: Color
    val hover: Color
    val active: Color
    val disabled: Color
    val disabledSubtle: Color
    val subtle: Color
    val subtleHover: Color
    val subtleActive: Color
    val selected: Color
    val selectedHover: Color
    val selectedActive: Color

    val inverted: Color

    val primary: Color
    val primaryHover: Color
    val primaryActive: Color
    val primarySubtle: Color
    val primarySubtleHover: Color
    val primarySubtleActive: Color

    val secondary: Color
    val secondaryHover: Color
    val secondaryActive: Color

    val positive: Color
    val positiveHover: Color
    val positiveActive: Color
    val positiveSubtle: Color
    val positiveSubtleHover: Color
    val positiveSubtleActive: Color

    val negative: Color
    val negativeHover: Color
    val negativeActive: Color
    val negativeSubtle: Color
    val negativeSubtleHover: Color
    val negativeSubtleActive: Color

    val warning: Color
    val warningHover: Color
    val warningActive: Color
    val warningSubtle: Color
    val warningSubtleHover: Color
    val warningSubtleActive: Color

    val info: Color
    val infoHover: Color
    val infoActive: Color
    val infoSubtle: Color
    val infoSubtleHover: Color
    val infoSubtleActive: Color

    val notification: Color
    val transparent0: Color
}

interface WarpBorderColors {
    val default: Color
    val hover: Color
    val active: Color
    val disabled: Color
    val selected: Color
    val selectedHover: Color
    val selectedActive: Color
    val inverted: Color
    val focus: Color

    val primary: Color
    val primaryHover: Color
    val primaryActive: Color
    val primarySubtle: Color
    val primarySubtleHover: Color
    val primarySubtleActive: Color

    val secondary: Color
    val secondaryHover: Color
    val secondaryActive: Color

    val positive: Color
    val positiveHover: Color
    val positiveActive: Color
    val positiveSubtle: Color
    val positiveSubtleHover: Color
    val positiveSubtleActive: Color

    val negative: Color
    val negativeHover: Color
    val negativeActive: Color
    val negativeSubtle: Color
    val negativeSubtleHover: Color
    val negativeSubtleActive: Color

    val warning: Color
    val warningHover: Color
    val warningActive: Color
    val warningSubtle: Color
    val warningSubtleHover: Color
    val warningSubtleActive: Color

    val info: Color
    val infoHover: Color
    val infoActive: Color
    val infoSubtle: Color
    val infoSubtleHover: Color
    val infoSubtleActive: Color
}

interface WarpIconColors {
    val default: Color
    val static: Color
    val hover: Color
    val active: Color
    val selected: Color
    val selectedHover: Color
    val selectedActive: Color
    val disabled: Color
    val subtle: Color
    val subtleHover: Color
    val subtleActive: Color
    val inverted: Color
    val invertedHover: Color
    val invertedActive: Color
    val invertedStatic: Color
    val primary: Color
    val secondary: Color
    val secondaryHover: Color
    val secondaryActive: Color
    val positive: Color
    val negative: Color
    val warning: Color
    val info: Color
    val notification: Color
}

interface WarpTextColors {
    val default: Color
    val static: Color
    val subtle: Color
    val placeholder: Color
    val inverted: Color
    val invertedStatic: Color
    val invertedSubtle: Color
    val link: Color
    val disabled: Color
    val negative: Color
    val positive: Color
}

interface WarpComponentColors {
    val badge: WarpBadgeColors
    val button: WarpButtonColors
    val callout: WarpCalloutColors
    val pill: WarpPillColors
    val navBar: WarpNavBarColors
    val tooltip: WarpTooltipColors
    val switch: WarpSwitchColors
    val card: WarpCardColors
    val pageIndicator: WarpPageIndicatorColors
}

interface WarpPageIndicatorColors {
    val indicatorBackground: Color
    val indicatorBackgroundHover: Color
}

interface WarpCardColors {
    val defaultBackground: Color
}

interface WarpSwitchColors {
    val handleBackground: Color
    val handleBackgroundHover: Color
    val trackBorder: Color
    val trackBorderHover: Color
}

interface WarpTooltipColors {
    val backgroundStatic: Color
}

interface WarpNavBarColors {
    val iconSelected: Color
    val borderSelected: Color
}

interface WarpPillColors {
    val suggestionBackground: Color
    val suggestionBackgroundHover: Color
    val suggestionBackgroundActive: Color
}

interface WarpCalloutColors {
    val background: Color
    val border: Color
}

interface WarpBadgeColors {
    val infoBackground: Color
    val positiveBackground: Color
    val warningBackground: Color
    val negativeBackground: Color
    val neutralBackground: Color
    val sponsoredBackground: Color
    val priceBackground: Color
}

interface WarpButtonColors {
    val primaryBackground: Color
    val primaryBackgroundHover: Color
    val primaryBackgroundActive: Color
    val pillBackgroundHover: Color
    val pillBackgroundActive: Color
}

val White = Color.White
val Black = Color.Black
val Transparent = Color.Transparent
val Black70Alpha = Color(0x70000000)

internal val LocalColors = staticCompositionLocalOf<WarpColors> { Placeholders }
