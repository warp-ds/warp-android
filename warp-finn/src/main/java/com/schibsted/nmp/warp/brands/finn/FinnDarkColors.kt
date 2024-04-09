package com.schibsted.nmp.warp.brands.finn

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
import com.schibsted.nmp.warp.theme.WarpSurfaceColors
import com.schibsted.nmp.warp.theme.WarpSwitchColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.WarpTooltipColors
import com.schibsted.nmp.warp.theme.White

internal object FinnDarkColors : WarpColors {
    override val surface: WarpSurfaceColors = FinnDarkSurfaceColors
    override val background: WarpBackgroundColors = FinnDarkBackgroundColors
    override val border: WarpBorderColors = FinnDarkBorderColors
    override val icon: WarpIconColors = FinnDarkIconColors
    override val text: WarpTextColors = FinnDarkTextColors
    override val components: WarpComponentColors = FinnComponentDarkColors
}

internal object FinnDarkSurfaceColors : WarpSurfaceColors {
    override val sunken = Gray950
    override val elevated100 = Gray850
    override val elevated100Hover = Gray800
    override val elevated100Active = Gray850
    override val elevated200 = Gray800
    override val elevated200Hover = Gray750
    override val elevated200Active = Gray800
    override val elevated300 = Gray750
    override val elevated300Hover = Gray700
    override val elevated300Active = Gray750
}

internal object FinnDarkBackgroundColors : WarpBackgroundColors {
    override val default = Gray900
    override val hover = Gray850
    override val active = Gray900
    override val disabled = Gray700
    override val disabledSubtle = Gray600
    override val subtle = Gray750
    override val subtleHover = Gray700
    override val subtleActive = Gray750
    override val selected = Blue900
    override val selectedHover = Blue800
    override val selectedActive = Blue900

    override val inverted = Gray50

    override val primary = Blue400
    override val primaryHover = Blue300
    override val primaryActive = Blue400
    override val primarySubtle = Blue800
    override val primarySubtleHover = Blue700
    override val primarySubtleActive = Blue800

    override val secondary = Aqua400
    override val secondaryHover = Aqua300
    override val secondaryActive = Aqua400

    override val positive = Green500
    override val positiveHover = Green400
    override val positiveActive = Green500
    override val positiveSubtle = Green900
    override val positiveSubtleHover = Green800
    override val positiveSubtleActive = Green900

    override val negative = Red400
    override val negativeHover = Red300
    override val negativeActive = Red400
    override val negativeSubtle = Red900
    override val negativeSubtleHover = Red800
    override val negativeSubtleActive = Red900

    override val warning = Yellow500
    override val warningHover = Yellow400
    override val warningActive = Yellow500
    override val warningSubtle = Yellow900
    override val warningSubtleHover = Yellow800
    override val warningSubtleActive = Yellow900

    override val info = Aqua500
    override val infoHover = Aqua400
    override val infoActive = Aqua500
    override val infoSubtle = Aqua900
    override val infoSubtleHover = Aqua800
    override val infoSubtleActive = Aqua900

    override val notification = Red500
}

internal object FinnDarkBorderColors : WarpBorderColors {
    override val default = Gray600
    override val hover = Gray500
    override val active = Gray600
    override val disabled = Gray700
    override val selected = Blue400
    override val selectedHover = Blue300
    override val selectedActive = Blue400
    override val focus = Aqua300

    override val primary = Blue400
    override val primaryHover = Blue300
    override val primaryActive = Blue400
    override val primarySubtle = Blue700
    override val primarySubtleHover = Blue800
    override val primarySubtleActive = Blue700

    override val secondary = Aqua400
    override val secondaryHover = Aqua300
    override val secondaryActive = Aqua400

    override val positive = Green500
    override val positiveHover = Green400
    override val positiveActive = Green500
    override val positiveSubtle = Green700
    override val positiveSubtleHover = Green600
    override val positiveSubtleActive = Green700

    override val negative = Red400
    override val negativeHover = Red300
    override val negativeActive = Red400
    override val negativeSubtle = Red700
    override val negativeSubtleHover = Red600
    override val negativeSubtleActive = Red700

    override val warning = Yellow500
    override val warningHover = Yellow400
    override val warningActive = Yellow500
    override val warningSubtle = Yellow700
    override val warningSubtleHover = Yellow600
    override val warningSubtleActive = Yellow700

    override val info = Aqua500
    override val infoHover = Aqua400
    override val infoActive = Aqua500
    override val infoSubtle = Aqua700
    override val infoSubtleHover = Aqua600
    override val infoSubtleActive = Aqua700
}

internal object FinnDarkIconColors : WarpIconColors {
    override val default = White
    override val static = Gray700
    override val hover = Gray100
    override val active = Gray200
    override val selected = Blue400
    override val selectedHover = Blue300
    override val selectedActive = Blue400
    override val disabled = Gray600
    override val subtle = Gray600
    override val subtleHover = Gray200
    override val subtleActive = Gray300
    override val inverted = Gray900
    override val invertedHover = Gray950
    override val invertedActive = Gray900
    override val invertedStatic = White
    override val primary = Blue400
    override val secondary = Aqua400
    override val secondaryHover = Aqua300
    override val secondaryActive = Aqua400
    override val positive = Green500
    override val negative = Red400
    override val warning = Yellow500
    override val info = Aqua500
    override val notification = White
}

internal object FinnDarkTextColors : WarpTextColors {
    override val default = White
    override val static = Gray700
    override val subtle = Gray100
    override val placeholder = Gray500
    override val inverted = Gray900
    override val invertedStatic = White
    override val invertedSubtle = Gray800
    override val link = Blue400
    override val disabled = Gray500
    override val negative = Red400
    override val positive = Green500
    override val notification = White
}

internal object FinnComponentDarkColors : WarpComponentColors {
    override val button: WarpButtonColors = FinnButtonDarkColors
    override val badge: WarpBadgeColors = FinnBadgeDarkColors
    override val callout: WarpCalloutColors = FinnCalloutDarkColors
    override val pill: WarpPillColors = FinnPillDarkColors
    override val navBar: WarpNavBarColors = FinnNavBarDarkColors
    override val tooltip: WarpTooltipColors = FinnTooltipDarkColors
    override val switch: WarpSwitchColors = FinnSwitchDarkColors
}

private object FinnSwitchDarkColors : WarpSwitchColors {
    override val trackBackground: Color = Gray600
    override val trackBackgroundHover: Color = Gray500
}

internal object FinnTooltipDarkColors : WarpTooltipColors {
    override val backgroundStatic: Color = FinnDarkSurfaceColors.elevated300
}

internal object FinnNavBarDarkColors : WarpNavBarColors {
    override val iconSelected: Color = Blue500
    override val borderSelected: Color = Blue500
}

internal object FinnPillDarkColors : WarpPillColors {
    override val suggestionBackground = Gray600
    override val suggestionBackgroundHover = Gray500
    override val suggestionBackgroundActive = Gray400
}

internal object FinnCalloutDarkColors : WarpCalloutColors {
    override val background: Color = Green800
    override val border: Color = Green600
    override val text: Color = FinnDarkTextColors.default
}

internal object FinnBadgeDarkColors : WarpBadgeColors {
    override val infoBackground: Color = Aqua700
    override val positiveBackground: Color = Green700
    override val warningBackground: Color = Yellow700
    override val negativeBackground: Color = Red700
    override val disabledBackground: Color = FinnDarkBackgroundColors.disabled
    override val neutralBackground: Color = Gray600
    override val sponsoredBackground: Color = Aqua600
    override val priceBackground: Color = Black70Alpha
}

internal object FinnButtonDarkColors : WarpButtonColors {
    override val loading = Pair(Gray700, Gray900)
    override val primaryBackground: Color = FinnDarkBackgroundColors.primary
    override val primaryBackgroundHover: Color = FinnDarkBackgroundColors.primaryHover
    override val primaryBackgroundActive: Color = FinnDarkBackgroundColors.primaryActive
}
