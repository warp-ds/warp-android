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
import com.schibsted.nmp.warp.theme.WarpSurfaceColors
import com.schibsted.nmp.warp.theme.WarpSwitchColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.WarpTooltipColors
import com.schibsted.nmp.warp.theme.White

internal object DbaColors : WarpColors {
    override val surface: WarpSurfaceColors = DbaSurfaceColors
    override val background: WarpBackgroundColors = DbaBackgroundColors
    override val border: WarpBorderColors = DbaBorderColors
    override val icon: WarpIconColors = DbaIconColors
    override val text: WarpTextColors = DbaTextColors
    override val components: WarpComponentColors = DbaComponentColors
}

internal object DbaSurfaceColors : WarpSurfaceColors {
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

internal object DbaBackgroundColors : WarpBackgroundColors {
    override val default = White
    override val hover = Gray100
    override val active = Gray200
    override val disabled = Gray300
    override val disabledSubtle = Gray200
    override val subtle = Gray100
    override val subtleHover = Gray200
    override val subtleActive = Gray300
    override val selected = Jeanblue50
    override val selectedHover = Jeanblue100
    override val selectedActive = Jeanblue200

    override val inverted = Gray900

    override val primary = Jeanblue600
    override val primaryHover = Jeanblue700
    override val primaryActive = Jeanblue800
    override val primarySubtle = Jeanblue50
    override val primarySubtleHover = Jeanblue100
    override val primarySubtleActive = Jeanblue200

    override val secondary = Phthaloblue600
    override val secondaryHover = Phthaloblue700
    override val secondaryActive = Phthaloblue800

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

    override val info = Jeanblue600
    override val infoHover = Jeanblue700
    override val infoActive = Jeanblue800
    override val infoSubtle = Jeanblue50
    override val infoSubtleHover = Jeanblue100
    override val infoSubtleActive = Jeanblue200

    override val notification = Red600
}

internal object DbaBorderColors : WarpBorderColors {
    override val default = Gray300
    override val hover = Gray400
    override val active = Gray500
    override val disabled = Gray300
    override val selected = Jeanblue600
    override val selectedHover = Jeanblue700
    override val selectedActive = Jeanblue800
    override val focus = Jeanblue400

    override val primary = Jeanblue600
    override val primaryHover = Jeanblue700
    override val primaryActive = Jeanblue800
    override val primarySubtle = Jeanblue300
    override val primarySubtleHover = Jeanblue400
    override val primarySubtleActive = Jeanblue500

    override val secondary = Phthaloblue600
    override val secondaryHover = Phthaloblue700
    override val secondaryActive = Phthaloblue800

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

    override val info = Jeanblue600
    override val infoHover = Jeanblue700
    override val infoActive = Jeanblue800
    override val infoSubtle = Jeanblue300
    override val infoSubtleHover = Jeanblue400
    override val infoSubtleActive = Jeanblue500
}

internal object DbaIconColors : WarpIconColors {
    override val default = Gray800
    override val static = Gray800
    override val hover = Jeanblue700
    override val active = Jeanblue800
    override val selected = Jeanblue600
    override val selectedHover = Jeanblue700
    override val selectedActive = Jeanblue800
    override val disabled = Gray300
    override val subtle = Gray400
    override val subtleHover = Gray500
    override val subtleActive = Gray600
    override val inverted = White
    override val invertedHover = Gray100
    override val invertedActive = Gray200
    override val invertedStatic = White
    override val primary = Jeanblue600
    override val secondary = Phthaloblue600
    override val secondaryHover = Phthaloblue700
    override val secondaryActive = Phthaloblue800
    override val positive = Green600
    override val negative = Red600
    override val warning = Yellow600
    override val info = Jeanblue600
    override val notification = White
}

internal object DbaTextColors : WarpTextColors {
    override val default = Gray800
    override val static = Gray800
    override val subtle = Gray500
    override val placeholder = Gray300
    override val inverted = White
    override val invertedStatic = White
    override val invertedSubtle = Gray50
    override val link = Jeanblue600
    override val disabled = Gray300
    override val negative = Red600
    override val positive = Green600
    override val notification = White
}

internal object DbaComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = DbaButtonColors
    override val badge: WarpBadgeColors = DbaBadgeColors
    override val callout: WarpCalloutColors = DbaCalloutColors
    override val pill: WarpPillColors = DbaPillColors
    override val navBar: WarpNavBarColors = DbaNavBarColors
    override val tooltip: WarpTooltipColors = DbaTooltipColors
    override val switch: WarpSwitchColors = DbaSwitchColors
}

private object DbaSwitchColors : WarpSwitchColors {
    override val trackBackground: Color = Gray200
    override val trackBackgroundHover: Color = Gray300
}

internal object DbaTooltipColors : WarpTooltipColors {
    override val backgroundStatic: Color = DbaBackgroundColors.inverted
}

internal object DbaNavBarColors : WarpNavBarColors {
    override val iconSelected: Color = DbaIconColors.selected
    override val borderSelected: Color = DbaBorderColors.selected
}

internal object DbaPillColors : WarpPillColors {
    override val suggestionBackground = Gray200
    override val suggestionBackgroundHover = Gray300
    override val suggestionBackgroundActive = Gray400
}

internal object DbaCalloutColors : WarpCalloutColors {
    override val background: Color = Green100
    override val border: Color = Green400
    override val text: Color = DbaTextColors.default
}

internal object DbaBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = Jeanblue100
    override val positiveBackground: Color = Green100
    override val warningBackground: Color = Yellow100
    override val negativeBackground: Color = Red100
    override val disabledBackground: Color = DbaBackgroundColors.disabled
    override val neutralBackground: Color = Gray100
    override val sponsoredBackground: Color = Jeanblue200
    override val priceBackground: Color = Black70Alpha
}

internal object DbaButtonColors : WarpButtonColors {
    override val loading = Pair(Gray50, Gray200)
    override val primaryBackground: Color = DbaBackgroundColors.primary
    override val primaryBackgroundHover: Color = DbaBackgroundColors.primaryHover
    override val primaryBackgroundActive: Color = DbaBackgroundColors.primaryActive
}

//Jeanblue
internal val Jeanblue50 = Color(0xfff3f6fc)
internal val Jeanblue100 = Color(0xffd2e1f5)
internal val Jeanblue200 = Color(0xffb2c9ec)
internal val Jeanblue300 = Color(0xff92b1e3)
internal val Jeanblue400 = Color(0xff7299da)
internal val Jeanblue500 = Color(0xff5281d1)
internal val Jeanblue600 = Color(0xff3269c8)
internal val Jeanblue700 = Color(0xff244b8f)
internal val Jeanblue800 = Color(0xff162d56)
internal val Jeanblue900 = Color(0xff070f1d)

//Phthaloblue
internal val Phthaloblue50 = Color(0xfff0f1ff)
internal val Phthaloblue100 = Color(0xffc8ccee)
internal val Phthaloblue200 = Color(0xffa0a5da)
internal val Phthaloblue300 = Color(0xff787ec6)
internal val Phthaloblue400 = Color(0xff5057b2)
internal val Phthaloblue500 = Color(0xff28309e)
internal val Phthaloblue600 = Color(0xff00098a)
internal val Phthaloblue700 = Color(0xff000768)
internal val Phthaloblue800 = Color(0xff000546)
internal val Phthaloblue900 = Color(0xff000324)

//Green
internal val Green50 = Color(0xfff3fcf9)
internal val Green100 = Color(0xffcee8dc)
internal val Green200 = Color(0xffaad6c4)
internal val Green300 = Color(0xff86c4ac)
internal val Green400 = Color(0xff62b294)
internal val Green500 = Color(0xff3ea07c)
internal val Green600 = Color(0xff1a8f64)
internal val Green700 = Color(0xff136647)
internal val Green800 = Color(0xff0c3e2a)
internal val Green900 = Color(0xff072719)

//Yellow
internal val Yellow50 = Color(0xfffef7f1)
internal val Yellow100 = Color(0xfffde8d5)
internal val Yellow200 = Color(0xfffbd6b4)
internal val Yellow300 = Color(0xfff9c493)
internal val Yellow400 = Color(0xfff7b272)
internal val Yellow500 = Color(0xfff5a051)
internal val Yellow600 = Color(0xfff38e30)
internal val Yellow700 = Color(0xffad6421)
internal val Yellow800 = Color(0xff673a12)
internal val Yellow900 = Color(0xff221102)

//Red
internal val Red50 = Color(0xfffff0f1)
internal val Red100 = Color(0xfff7cacc)
internal val Red200 = Color(0xffefa3a6)
internal val Red300 = Color(0xffe77c80)
internal val Red400 = Color(0xffed686b)
internal val Red500 = Color(0xffde383d)
internal val Red600 = Color(0xffcf070e)
internal val Red700 = Color(0xff96050a)
internal val Red800 = Color(0xff5d0306)
internal val Red900 = Color(0xff230103)

//Gray
internal val Gray50 = Color(0xfff6f6f6)
internal val Gray100 = Color(0xfff0f0f2)
internal val Gray200 = Color(0xffdedee3)
internal val Gray300 = Color(0xffcacad1)
internal val Gray400 = Color(0xffafafb8)
internal val Gray500 = Color(0xff84848f)
internal val Gray600 = Color(0xff5c5c66)
internal val Gray700 = Color(0xff47474f)
internal val Gray750 = Color(0xff333338)
internal val Gray800 = Color(0xff2b2b30)
internal val Gray850 = Color(0xff26262b)
internal val Gray900 = Color(0xff1b1b1f)
internal val Gray950 = Color(0xff121212)