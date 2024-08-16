package com.schibsted.nmp.warp.brands.dba

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Black70Alpha
import com.schibsted.nmp.warp.theme.WarpBackgroundColors
import com.schibsted.nmp.warp.theme.WarpBadgeColors
import com.schibsted.nmp.warp.theme.WarpBorderColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpCalloutColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpIconColors
import com.schibsted.nmp.warp.theme.WarpNavBarColors
import com.schibsted.nmp.warp.theme.WarpPillColors
import com.schibsted.nmp.warp.theme.WarpSpinnerColors
import com.schibsted.nmp.warp.theme.WarpSurfaceColors
import com.schibsted.nmp.warp.theme.WarpSwitchColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.WarpTooltipColors
import com.schibsted.nmp.warp.theme.White

internal object DbaDarkColors : WarpColors {
    override val surface: WarpSurfaceColors = DbaDarkSurfaceColors
    override val background: WarpBackgroundColors = DbaDarkBackgroundColors
    override val border: WarpBorderColors = DbaDarkBorderColors
    override val icon: WarpIconColors = DbaDarkIconColors
    override val text: WarpTextColors = DbaDarkTextColors
    override val components: WarpComponentColors = DbaDarkComponentColors
}

internal object DbaDarkSurfaceColors : WarpSurfaceColors {
    override val sunken = Gray950
    override val elevated100 = Gray850
    override val elevated100Hover = Gray800
    override val elevated100Active = Gray750
    override val elevated200 = Gray800
    override val elevated200Hover = Gray750
    override val elevated200Active = Gray700
    override val elevated300 = Gray750
    override val elevated300Hover = Gray700
    override val elevated300Active = Gray600
}

internal object DbaDarkBackgroundColors : WarpBackgroundColors {
    override val default = Gray900
    override val hover = Gray850
    override val active = Gray800
    override val disabled = Gray700
    override val disabledSubtle = Gray600
    override val subtle = Gray750
    override val subtleHover = Gray700
    override val subtleActive = Gray600
    override val selected = Jeanblue900
    override val selectedHover = Jeanblue800
    override val selectedActive = Jeanblue700

    override val inverted = Gray50

    override val primary = Jeanblue400
    override val primaryHover = Jeanblue300
    override val primaryActive = Jeanblue200
    override val primarySubtle = Jeanblue800
    override val primarySubtleHover = Jeanblue700
    override val primarySubtleActive = Jeanblue600

    override val secondary = Phthaloblue400
    override val secondaryHover = Phthaloblue300
    override val secondaryActive = Phthaloblue200

    override val positive = Green500
    override val positiveHover = Green400
    override val positiveActive = Green300
    override val positiveSubtle = Green900
    override val positiveSubtleHover = Green800
    override val positiveSubtleActive = Green700

    override val negative = Red400
    override val negativeHover = Red300
    override val negativeActive = Red200
    override val negativeSubtle = Red900
    override val negativeSubtleHover = Red800
    override val negativeSubtleActive = Red700

    override val warning = Yellow500
    override val warningHover = Yellow400
    override val warningActive = Yellow300
    override val warningSubtle = Yellow900
    override val warningSubtleHover = Yellow800
    override val warningSubtleActive = Yellow700

    override val info = Jeanblue500
    override val infoHover = Jeanblue400
    override val infoActive = Jeanblue300
    override val infoSubtle = Jeanblue900
    override val infoSubtleHover = Jeanblue800
    override val infoSubtleActive = Jeanblue700

    override val notification = Red500
}

internal object DbaDarkBorderColors : WarpBorderColors {
    override val default = Gray600
    override val hover = Gray500
    override val active = Gray400
    override val disabled = Gray700
    override val selected = Jeanblue400
    override val selectedHover = Jeanblue300
    override val selectedActive = Jeanblue200
    override val focus = Jeanblue300

    override val primary = Jeanblue400
    override val primaryHover = Jeanblue300
    override val primaryActive = Jeanblue200
    override val primarySubtle = Jeanblue700
    override val primarySubtleHover = Jeanblue600
    override val primarySubtleActive = Jeanblue500

    override val secondary = Phthaloblue400
    override val secondaryHover = Phthaloblue300
    override val secondaryActive = Phthaloblue200

    override val positive = Green500
    override val positiveHover = Green400
    override val positiveActive = Green300
    override val positiveSubtle = Green700
    override val positiveSubtleHover = Green600
    override val positiveSubtleActive = Green500

    override val negative = Red400
    override val negativeHover = Red300
    override val negativeActive = Red200
    override val negativeSubtle = Red700
    override val negativeSubtleHover = Red600
    override val negativeSubtleActive = Red500

    override val warning = Yellow500
    override val warningHover = Yellow400
    override val warningActive = Yellow300
    override val warningSubtle = Yellow700
    override val warningSubtleHover = Yellow600
    override val warningSubtleActive = Yellow500

    override val info = Jeanblue500
    override val infoHover = Jeanblue400
    override val infoActive = Jeanblue300
    override val infoSubtle = Jeanblue700
    override val infoSubtleHover = Jeanblue600
    override val infoSubtleActive = Jeanblue500
}

internal object DbaDarkIconColors : WarpIconColors {
    override val default = White
    override val static = Gray800
    override val hover = Jeanblue100
    override val active = Jeanblue200
    override val selected = Jeanblue400
    override val selectedHover = Jeanblue300
    override val selectedActive = Jeanblue200
    override val disabled = Gray600
    override val subtle = Gray600
    override val subtleHover = Gray500
    override val subtleActive = Gray400
    override val inverted = Gray900
    override val invertedHover = Gray850
    override val invertedActive = Gray800
    override val invertedStatic = White
    override val primary = Jeanblue400
    override val secondary = Phthaloblue400
    override val secondaryHover = Phthaloblue300
    override val secondaryActive = Phthaloblue200
    override val positive = Green500
    override val negative = Red400
    override val warning = Yellow500
    override val info = Jeanblue500
    override val notification = White
}

internal object DbaDarkTextColors : WarpTextColors {
    override val default = White
    override val static = Gray800
    override val subtle = Gray100
    override val placeholder = Gray500
    override val inverted = Gray900
    override val invertedStatic = White
    override val invertedSubtle = Gray800
    override val link = Jeanblue400
    override val disabled = Gray500
    override val negative = Red400
    override val positive = Green500
    override val notification = White
}

internal object DbaDarkComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = DbaButtonDarkColors
    override val badge: WarpBadgeColors = DbaBadgeDarkColors
    override val callout: WarpCalloutColors = DbaCalloutDarkColors
    override val pill: WarpPillColors = DbaPillDarkColors
    override val navBar: WarpNavBarColors = DbaNavBarDarkColors
    override val tooltip: WarpTooltipColors = DbaTooltipDarkColors
    override val switch: WarpSwitchColors = DbaSwitchDarkColors
    override val spinner: WarpSpinnerColors = DbaSpinnerDarkColors
}

private object DbaSpinnerDarkColors : WarpSpinnerColors {
    override val color: Color = DbaDarkBorderColors.primary
    override val trackColor: Color = DbaDarkBorderColors.primarySubtle
}

private object DbaSwitchDarkColors : WarpSwitchColors {
    override val trackBackground: Color = Gray600
    override val trackBackgroundHover: Color = Gray500
}

internal object DbaTooltipDarkColors : WarpTooltipColors {
    override val backgroundStatic: Color = DbaDarkSurfaceColors.elevated300
}

internal object DbaNavBarDarkColors : WarpNavBarColors {
    override val iconSelected: Color = DbaDarkIconColors.selected
    override val borderSelected: Color = DbaDarkBorderColors.selected
}

internal object DbaPillDarkColors : WarpPillColors {
    override val suggestionBackground = Gray600
    override val suggestionBackgroundHover = Gray500
    override val suggestionBackgroundActive = Gray400
}

internal object DbaCalloutDarkColors : WarpCalloutColors {
    override val background: Color = Green800
    override val border: Color = Green600
    override val text: Color = DbaDarkTextColors.default
}

internal object DbaBadgeDarkColors : WarpBadgeColors {
    override val infoBackground: Color = Jeanblue700
    override val positiveBackground: Color = Green700
    override val warningBackground: Color = Yellow700
    override val negativeBackground: Color = Red700
    override val disabledBackground: Color = DbaDarkBackgroundColors.disabled
    override val neutralBackground: Color = Gray600
    override val sponsoredBackground: Color = Jeanblue600
    override val priceBackground: Color = Black70Alpha
}

internal object DbaButtonDarkColors : WarpButtonColors {
    override val loading = Pair(Gray700, Gray900)
    override val primaryBackground: Color = DbaDarkBackgroundColors.primary
    override val primaryBackgroundHover: Color = DbaDarkBackgroundColors.primaryHover
    override val primaryBackgroundActive: Color = DbaDarkBackgroundColors.primaryActive
}
