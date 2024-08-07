@file:Suppress("unused")

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
import com.schibsted.nmp.warp.theme.WarpSpinnerColors
import com.schibsted.nmp.warp.theme.WarpSurfaceColors
import com.schibsted.nmp.warp.theme.WarpSwitchColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.WarpTooltipColors
import com.schibsted.nmp.warp.theme.White

internal object FinnColors : WarpColors {
    override val surface: WarpSurfaceColors = FinnSurfaceColors
    override val background: WarpBackgroundColors = FinnBackgroundColors
    override val border: WarpBorderColors = FinnBorderColors
    override val icon: WarpIconColors = FinnIconColors
    override val text: WarpTextColors = FinnTextColors
    override val components: WarpComponentColors = FinnComponentColors
}

internal object FinnSurfaceColors : WarpSurfaceColors {
    override val sunken = Gray50
    override val elevated100 = White
    override val elevated100Hover = Gray100
    override val elevated100Active = Gray200
    override val elevated200 = White
    override val elevated200Hover = Gray100
    override val elevated200Active = Gray200
    override val elevated300 = White
    override val elevated300Hover = Gray100
    override val elevated300Active = Gray200
}

internal object FinnBackgroundColors : WarpBackgroundColors {
    override val default = White
    override val hover = Gray100
    override val active = Gray200
    override val disabled = Gray300
    override val disabledSubtle = Gray200
    override val subtle = Gray100
    override val subtleHover = Gray200
    override val subtleActive = Gray300
    override val selected = Blue50
    override val selectedHover = Blue100
    override val selectedActive = Blue200

    override val inverted = Gray900

    override val primary = Blue600
    override val primaryHover = Blue700
    override val primaryActive = Blue800
    override val primarySubtle = Blue50
    override val primarySubtleHover = Blue100
    override val primarySubtleActive = Blue200

    override val secondary = Aqua400
    override val secondaryHover = Aqua500
    override val secondaryActive = Aqua600

    override val positive = Green600
    override val positiveHover = Green700
    override val positiveActive = Green800
    override val positiveSubtle = Green50
    override val positiveSubtleHover = Green100
    override val positiveSubtleActive = Green200

    override val negative = Red600
    override val negativeHover = Red700
    override val negativeActive = Red800
    override val negativeSubtle = Red50
    override val negativeSubtleHover = Red100
    override val negativeSubtleActive = Red200

    override val warning = Yellow600
    override val warningHover = Yellow700
    override val warningActive = Yellow800
    override val warningSubtle = Yellow50
    override val warningSubtleHover = Yellow100
    override val warningSubtleActive = Yellow200

    override val info = Aqua600
    override val infoHover = Aqua700
    override val infoActive = Aqua800
    override val infoSubtle = Aqua50
    override val infoSubtleHover = Aqua100
    override val infoSubtleActive = Aqua200

    override val notification = Red600
}

internal object FinnBorderColors : WarpBorderColors {
    override val default = Gray300
    override val hover = Gray400
    override val active = Gray500
    override val disabled = Gray300
    override val selected = Blue600
    override val selectedHover = Blue700
    override val selectedActive = Blue800
    override val focus = Aqua400

    override val primary = Blue600
    override val primaryHover = Blue700
    override val primaryActive = Blue800
    override val primarySubtle = Blue300
    override val primarySubtleHover = Blue400
    override val primarySubtleActive = Blue500

    override val secondary = Aqua400
    override val secondaryHover = Aqua500
    override val secondaryActive = Aqua600

    override val positive = Green600
    override val positiveHover = Green700
    override val positiveActive = Green800
    override val positiveSubtle = Green300
    override val positiveSubtleHover = Green400
    override val positiveSubtleActive = Green500

    override val negative = Red600
    override val negativeHover = Red700
    override val negativeActive = Red800
    override val negativeSubtle = Red300
    override val negativeSubtleHover = Red400
    override val negativeSubtleActive = Red500

    override val warning = Yellow600
    override val warningHover = Yellow700
    override val warningActive = Yellow800
    override val warningSubtle = Yellow300
    override val warningSubtleHover = Yellow400
    override val warningSubtleActive = Yellow500

    override val info = Aqua600
    override val infoHover = Aqua700
    override val infoActive = Aqua800
    override val infoSubtle = Aqua300
    override val infoSubtleHover = Aqua400
    override val infoSubtleActive = Aqua500
}

internal object FinnIconColors : WarpIconColors {
    override val default = Gray700
    override val static = Gray700
    override val hover = Blue600
    override val active = Blue700
    override val selected = Blue600
    override val selectedHover = Blue700
    override val selectedActive = Blue800
    override val disabled = Gray300
    override val subtle = Gray400
    override val subtleHover = Gray500
    override val subtleActive = Gray600
    override val inverted = White
    override val invertedHover = Gray100
    override val invertedActive = Gray200
    override val invertedStatic = White
    override val primary = Blue600
    override val secondary = Aqua400
    override val secondaryHover = Aqua500
    override val secondaryActive = Aqua600
    override val positive = Green600
    override val negative = Red600
    override val warning = Yellow600
    override val info = Aqua600
    override val notification = White
}

internal object FinnTextColors : WarpTextColors {
    override val default = Gray700
    override val static = Gray700
    override val subtle = Gray500
    override val placeholder = Gray300
    override val inverted = White
    override val invertedStatic = White
    override val invertedSubtle = Gray50
    override val link = Blue600
    override val disabled = Gray300
    override val negative = Red600
    override val positive = Green600
    override val notification = White
}

internal object FinnComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = FinnButtonColors
    override val badge: WarpBadgeColors = FinnBadgeColors
    override val callout: WarpCalloutColors = FinnCalloutColors
    override val pill: WarpPillColors = FinnPillColors
    override val navBar: WarpNavBarColors = FinnNavBarColors
    override val tooltip: WarpTooltipColors = FinnTooltipColors
    override val switch: WarpSwitchColors = FinnSwitchColors
    override val spinner: WarpSpinnerColors = FinnSpinnerColors
}

private object FinnSpinnerColors : WarpSpinnerColors {
    override val color: Color = FinnBackgroundColors.primary
}

private object FinnSwitchColors : WarpSwitchColors {
    override val trackBackground: Color = Gray200
    override val trackBackgroundHover: Color = Gray300
}

internal object FinnTooltipColors : WarpTooltipColors {
    override val backgroundStatic: Color = FinnBackgroundColors.inverted
}

internal object FinnNavBarColors : WarpNavBarColors {
    override val iconSelected: Color = FinnIconColors.selected
    override val borderSelected: Color = FinnBorderColors.selected
}

internal object FinnPillColors : WarpPillColors {
    override val suggestionBackground = Gray200
    override val suggestionBackgroundHover = Gray300
    override val suggestionBackgroundActive = Gray400
}

internal object FinnCalloutColors : WarpCalloutColors {
    override val background: Color = Green100
    override val border: Color = Green400
    override val text: Color = FinnTextColors.default
}

internal object FinnBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = Aqua100
    override val positiveBackground: Color = Green100
    override val warningBackground: Color = Yellow100
    override val negativeBackground: Color = Red100
    override val disabledBackground: Color = FinnBackgroundColors.disabled
    override val neutralBackground: Color = Gray100
    override val sponsoredBackground: Color = Aqua200
    override val priceBackground: Color = Black70Alpha
}

internal object FinnButtonColors : WarpButtonColors {
    override val loading = Pair(Gray50, Gray200)
    override val primaryBackground: Color = FinnBackgroundColors.primary
    override val primaryBackgroundHover: Color = FinnBackgroundColors.primaryHover
    override val primaryBackgroundActive: Color = FinnBackgroundColors.primaryActive
}

//Blue
internal val Blue50 = Color(0xffeff5ff)
internal val Blue100 = Color(0xffe1edfe)
internal val Blue200 = Color(0xffc2dafe)
internal val Blue300 = Color(0xff9ac1fe)
internal val Blue400 = Color(0xff5c9cff)
internal val Blue500 = Color(0xff2b7eff)
internal val Blue600 = Color(0xff0063fb)
internal val Blue700 = Color(0xff244eb3)
internal val Blue800 = Color(0xff223474)
internal val Blue900 = Color(0xff191d34)

//Aqua
internal val Aqua50 = Color(0xfff1f9ff)
internal val Aqua100 = Color(0xffe0f6ff)
internal val Aqua200 = Color(0xffb6f0ff)
internal val Aqua300 = Color(0xff66e0ff)
internal val Aqua400 = Color(0xff06befb)
internal val Aqua500 = Color(0xff03a3dd)
internal val Aqua600 = Color(0xff0386bf)
internal val Aqua700 = Color(0xff1e648a)
internal val Aqua800 = Color(0xff1d435a)
internal val Aqua900 = Color(0xff15242f)

//Green
internal val Green50 = Color(0xffebfff6)
internal val Green100 = Color(0xffcdfee5)
internal val Green200 = Color(0xff9efcd1)
internal val Green300 = Color(0xff67eeb8)
internal val Green400 = Color(0xff2ee69f)
internal val Green500 = Color(0xff18c884)
internal val Green600 = Color(0xff059e6f)
internal val Green700 = Color(0xff1d7454)
internal val Green800 = Color(0xff1b4d39)
internal val Green900 = Color(0xff14291f)

//Yellow
internal val Yellow50 = Color(0xfffff8e6)
internal val Yellow100 = Color(0xfffff5c8)
internal val Yellow200 = Color(0xfffeef90)
internal val Yellow300 = Color(0xfffae76b)
internal val Yellow400 = Color(0xffffe11f)
internal val Yellow500 = Color(0xffeeb61b)
internal val Yellow600 = Color(0xffd5840b)
internal val Yellow700 = Color(0xff9b621e)
internal val Yellow800 = Color(0xff654118)
internal val Yellow900 = Color(0xff352310)

//Red
internal val Red50 = Color(0xfffff5f5)
internal val Red100 = Color(0xffffefef)
internal val Red200 = Color(0xffffd1d1)
internal val Red300 = Color(0xffff9999)
internal val Red400 = Color(0xffff5844)
internal val Red500 = Color(0xfffa270f)
internal val Red600 = Color(0xffd91f0a)
internal val Red700 = Color(0xff9e2216)
internal val Red800 = Color(0xff681d11)
internal val Red900 = Color(0xff38140b)

//Gray
internal val Gray50 = Color(0xfff6f6f6)
internal val Gray100 = Color(0xfff0f0f2)
internal val Gray200 = Color(0xffDEDEE3)
internal val Gray300 = Color(0xffCACAD1)
internal val Gray400 = Color(0xffAFAFB8)
internal val Gray500 = Color(0xff84848F)
internal val Gray600 = Color(0xff5c5c66)
internal val Gray700 = Color(0xff47474f)
internal val Gray750 = Color(0xff333338)
internal val Gray800 = Color(0xff2b2b30)
internal val Gray850 = Color(0xff26262b)
internal val Gray900 = Color(0xff1b1b1f)
internal val Gray950 = Color(0xff121212)
