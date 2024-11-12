package com.schibsted.nmp.warp.brands.warp_blocket

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

internal object BlocketDarkColors : WarpColors {
    override val surface: WarpSurfaceColors = BlocketDarkSurfaceColors
    override val background: WarpBackgroundColors = BlocketDarkBackgroundColors
    override val border: WarpBorderColors = BlocketDarkBorderColors
    override val icon: WarpIconColors = BlocketDarkIconColors
    override val text: WarpTextColors = BlocketDarkTextColors
    override val components: WarpComponentColors = BlocketDarkComponentColors
}

internal object BlocketDarkSurfaceColors : WarpSurfaceColors {
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

internal object BlocketDarkBackgroundColors : WarpBackgroundColors {
    override val default = Gray900
    override val hover = Gray850
    override val active = Gray800
    override val disabled = Gray700
    override val disabledSubtle = Gray600
    override val subtle = Gray750
    override val subtleHover = Gray700
    override val subtleActive = Gray600
    override val selected = Coral900
    override val selectedHover = Coral800
    override val selectedActive = Coral700

    override val inverted = Gray50

    override val primary = Coral400
    override val primaryHover = Coral300
    override val primaryActive = Coral200
    override val primarySubtle = Coral800
    override val primarySubtleHover = Coral700
    override val primarySubtleActive = Coral600

    override val secondary = Blue400
    override val secondaryHover = Blue300
    override val secondaryActive = Blue200

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

    override val info = Coral500
    override val infoHover = Coral400
    override val infoActive = Coral300
    override val infoSubtle = Coral900
    override val infoSubtleHover = Coral800
    override val infoSubtleActive = Coral700

    override val notification = Red500
}

internal object BlocketDarkBorderColors : WarpBorderColors {
    override val default = Gray600
    override val hover = Gray500
    override val active = Gray400
    override val disabled = Gray700
    override val selected = Coral400
    override val selectedHover = Coral300
    override val selectedActive = Coral200
    override val focus = Coral300

    override val primary = Coral400
    override val primaryHover = Coral300
    override val primaryActive = Coral200
    override val primarySubtle = Coral700
    override val primarySubtleHover = Coral600
    override val primarySubtleActive = Coral500

    override val secondary = Blue400
    override val secondaryHover = Blue300
    override val secondaryActive = Blue200

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

    override val info = Coral500
    override val infoHover = Coral400
    override val infoActive = Coral300
    override val infoSubtle = Coral700
    override val infoSubtleHover = Coral600
    override val infoSubtleActive = Coral500
}

internal object BlocketDarkIconColors : WarpIconColors {
    override val default = White
    override val static = Gray800
    override val hover = Coral100
    override val active = Coral200
    override val selected = Coral400
    override val selectedHover = Coral300
    override val selectedActive = Coral200
    override val disabled = Gray600
    override val subtle = Gray600
    override val subtleHover = Gray500
    override val subtleActive = Gray400
    override val inverted = Gray900
    override val invertedHover = Gray850
    override val invertedActive = Gray800
    override val invertedStatic = White
    override val primary = Coral400
    override val secondary = Blue400
    override val secondaryHover = Blue300
    override val secondaryActive = Blue200
    override val positive = Green500
    override val negative = Red400
    override val warning = Yellow500
    override val info = Coral500
    override val notification = White
}

internal object BlocketDarkTextColors : WarpTextColors {
    override val default = White
    override val static = Gray800
    override val subtle = Gray400
    override val placeholder = Gray500
    override val inverted = Gray900
    override val invertedStatic = White
    override val invertedSubtle = Gray800
    override val link = Coral400
    override val disabled = Gray500
    override val negative = Red400
    override val positive = Green500
    override val notification = White
}

internal object BlocketDarkComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = BlocketButtonDarkColors
    override val badge: WarpBadgeColors = BlocketBadgeDarkColors
    override val callout: WarpCalloutColors = BlocketCalloutDarkColors
    override val pill: WarpPillColors = BlocketPillDarkColors
    override val navBar: WarpNavBarColors = BlocketNavBarDarkColors
    override val tooltip: WarpTooltipColors = BlocketTooltipDarkColors
    override val switch: WarpSwitchColors = BlocketSwitchDarkColors
}

private object BlocketSwitchDarkColors : WarpSwitchColors {
    override val trackBackground: Color = Gray600
    override val trackBackgroundHover: Color = Gray500
}

internal object BlocketTooltipDarkColors : WarpTooltipColors {
    override val backgroundStatic: Color = BlocketDarkSurfaceColors.elevated300
}

internal object BlocketNavBarDarkColors : WarpNavBarColors {
    override val iconSelected: Color = BlocketDarkIconColors.selected
    override val borderSelected: Color = BlocketDarkBorderColors.selected
}

internal object BlocketPillDarkColors : WarpPillColors {
    override val suggestionBackground = Gray600
    override val suggestionBackgroundHover = Gray500
    override val suggestionBackgroundActive = Gray400
}

internal object BlocketCalloutDarkColors : WarpCalloutColors {
    override val background: Color = Green800
    override val border: Color = Green600
    override val text: Color = BlocketDarkTextColors.default
}

internal object BlocketBadgeDarkColors : WarpBadgeColors {
    override val infoBackground: Color = Coral700
    override val positiveBackground: Color = Green700
    override val warningBackground: Color = Yellow700
    override val negativeBackground: Color = Red700
    override val disabledBackground: Color = BlocketDarkBackgroundColors.disabled
    override val neutralBackground: Color = Gray600
    override val sponsoredBackground: Color = Coral600
    override val priceBackground: Color = Black70Alpha
}

internal object BlocketButtonDarkColors : WarpButtonColors {
    override val loading = Pair(Gray700, Gray900)
    override val primaryBackground: Color = Blue400
    override val primaryBackgroundHover: Color = Blue300
    override val primaryBackgroundActive: Color = Blue200
}