package com.schibsted.nmp.warp.brands.blocket

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

internal object BlocketColors : WarpColors {
    override val surface: WarpSurfaceColors = BlocketSurfaceColors
    override val background: WarpBackgroundColors = BlocketBackgroundColors
    override val border: WarpBorderColors = BlocketBorderColors
    override val icon: WarpIconColors = BlocketIconColors
    override val text: WarpTextColors = BlocketTextColors
    override val components: WarpComponentColors = BlocketComponentColors
}

internal object BlocketSurfaceColors : WarpSurfaceColors {
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

internal object BlocketBackgroundColors : WarpBackgroundColors {
    override val default = White
    override val hover = Gray100
    override val active = Gray200
    override val disabled = Gray300
    override val disabledSubtle = Gray200
    override val subtle = Gray100
    override val subtleHover = Gray200
    override val subtleActive = Gray300
    override val selected = Coral50
    override val selectedHover = Coral100
    override val selectedActive = Coral200

    override val inverted = Gray900

    override val primary = Coral600
    override val primaryHover = Coral700
    override val primaryActive = Coral800
    override val primarySubtle = Coral50
    override val primarySubtleHover = Coral100
    override val primarySubtleActive = Coral200

    override val secondary = Blue600
    override val secondaryHover = Blue700
    override val secondaryActive = Blue800

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

    override val info = Coral600
    override val infoHover = Coral700
    override val infoActive = Coral800
    override val infoSubtle = Coral50
    override val infoSubtleHover = Coral100
    override val infoSubtleActive = Coral200

    override val notification = Red600
}

internal object BlocketBorderColors : WarpBorderColors {
    override val default = Gray300
    override val hover = Gray400
    override val active = Gray500
    override val disabled = Gray300
    override val selected = Coral600
    override val selectedHover = Coral700
    override val selectedActive = Coral800
    override val focus = Coral400

    override val primary = Coral600
    override val primaryHover = Coral700
    override val primaryActive = Coral800
    override val primarySubtle = Coral300
    override val primarySubtleHover = Coral400
    override val primarySubtleActive = Coral500

    override val secondary = Blue600
    override val secondaryHover = Blue700
    override val secondaryActive = Blue800

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

    override val info = Coral600
    override val infoHover = Coral700
    override val infoActive = Coral800
    override val infoSubtle = Coral300
    override val infoSubtleHover = Coral400
    override val infoSubtleActive = Coral500
}

internal object BlocketIconColors : WarpIconColors {
    override val default = Gray900
    override val static = Gray800
    override val hover = Coral700
    override val active = Coral800
    override val selected = Coral600
    override val selectedHover = Coral700
    override val selectedActive = Coral800
    override val disabled = Gray300
    override val subtle = Gray400
    override val subtleHover = Gray500
    override val subtleActive = Gray600
    override val inverted = White
    override val invertedHover = Gray100
    override val invertedActive = Gray200
    override val invertedStatic = White
    override val primary = Coral600
    override val secondary = Blue600
    override val secondaryHover = Blue700
    override val secondaryActive = Blue800
    override val positive = Green600
    override val negative = Red600
    override val warning = Yellow600
    override val info = Coral600
    override val notification = White
}

internal object BlocketTextColors : WarpTextColors {
    override val default = Gray900
    override val static = Gray800
    override val subtle = Gray600
    override val placeholder = Gray300
    override val inverted = White
    override val invertedStatic = White
    override val invertedSubtle = Gray50
    override val link = Coral600
    override val disabled = Gray300
    override val negative = Red600
    override val positive = Green600
    override val notification = White
}

internal object BlocketComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = BlocketButtonColors
    override val badge: WarpBadgeColors = BlocketBadgeColors
    override val callout: WarpCalloutColors = BlocketCalloutColors
    override val pill: WarpPillColors = BlocketPillColors
    override val navBar: WarpNavBarColors = BlocketNavBarColors
    override val tooltip: WarpTooltipColors = BlocketTooltipColors
    override val switch: WarpSwitchColors = BlocketSwitchColors
}

private object BlocketSwitchColors : WarpSwitchColors {
    override val trackBackground: Color = Gray200
    override val trackBackgroundHover: Color = Gray300
}

internal object BlocketTooltipColors : WarpTooltipColors {
    override val backgroundStatic: Color = BlocketBackgroundColors.inverted
}

internal object BlocketNavBarColors : WarpNavBarColors {
    override val iconSelected: Color = BlocketIconColors.selected
    override val borderSelected: Color = BlocketBorderColors.selected
}

internal object BlocketPillColors : WarpPillColors {
    override val suggestionBackground = Gray200
    override val suggestionBackgroundHover = Gray300
    override val suggestionBackgroundActive = Gray400
}

internal object BlocketCalloutColors : WarpCalloutColors {
    override val background: Color = Green100
    override val border: Color = Green400
    override val text: Color = BlocketTextColors.default
}

internal object BlocketBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = Coral100
    override val positiveBackground: Color = Green100
    override val warningBackground: Color = Yellow100
    override val negativeBackground: Color = Red100
    override val disabledBackground: Color = BlocketBackgroundColors.disabled
    override val neutralBackground: Color = Gray100
    override val sponsoredBackground: Color = Coral200
    override val priceBackground: Color = Black70Alpha
}

internal object BlocketButtonColors : WarpButtonColors {
    override val loading = Pair(Gray50, Gray200)
    override val primaryBackground: Color = Blue600
    override val primaryBackgroundHover: Color = Blue700
    override val primaryBackgroundActive: Color = Blue800
}

internal val Coral50  = Color(0xfffaf3f2)
internal val Coral100 = Color(0xfff7d7d5)
internal val Coral200 = Color(0xfff1b8b3)
internal val Coral300 = Color(0xffeb9991)
internal val Coral400 = Color(0xffe57a6f)
internal val Coral500 = Color(0xffdf5b4d)
internal val Coral600 = Color(0xffd93c2b)
internal val Coral700 = Color(0xff9b2b1e)
internal val Coral800 = Color(0xff5d1a11)
internal val Coral900 = Color(0xff1f0805)

internal val Yellow50  = Color(0xfffcf5e3)
internal val Yellow100 = Color(0xfffff1c8)
internal val Yellow200 = Color(0xffffe5a0)
internal val Yellow300 = Color(0xffffd978)
internal val Yellow400 = Color(0xffffcd50)
internal val Yellow500 = Color(0xffffc128)
internal val Yellow600 = Color(0xffffb500)
internal val Yellow700 = Color(0xffb67b00)
internal val Yellow800 = Color(0xff6d4100)
internal val Yellow900 = Color(0xff240700)

internal val Green50  = Color(0xfff7fdfa)
internal val Green100 = Color(0xffd3ecdf)
internal val Green200 = Color(0xffb0dac5)
internal val Green300 = Color(0xff8dc8ab)
internal val Green400 = Color(0xff6ab691)
internal val Green500 = Color(0xff47a477)
internal val Green600 = Color(0xff24925d)
internal val Green700 = Color(0xff1a6b44)
internal val Green800 = Color(0xff10442b)
internal val Green900 = Color(0xff071d12)

internal val Red50  = Color(0xfffff0f1)
internal val Red100 = Color(0xfffdc8cb)
internal val Red200 = Color(0xfff9a0a7)
internal val Red300 = Color(0xfff57883)
internal val Red400 = Color(0xfff1505f)
internal val Red500 = Color(0xffed283b)
internal val Red600 = Color(0xffe90017)
internal val Red700 = Color(0xffa70011)
internal val Red800 = Color(0xff65000b)
internal val Red900 = Color(0xff240004)

internal val Blue50  = Color(0xfff0f7ff)
internal val Blue100 = Color(0xffc8dffa)
internal val Blue200 = Color(0xffa0c9f7)
internal val Blue300 = Color(0xff78b3f4)
internal val Blue400 = Color(0xff509df1)
internal val Blue500 = Color(0xff2887ee)
internal val Blue600 = Color(0xff0071eb)
internal val Blue700 = Color(0xff0051a9)
internal val Blue800 = Color(0xff003167)
internal val Blue900 = Color(0xff001124)

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