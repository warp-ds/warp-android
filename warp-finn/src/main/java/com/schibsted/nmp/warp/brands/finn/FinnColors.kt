@file:Suppress("unused")

package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpAlertColors
import com.schibsted.nmp.warp.theme.WarpAlertStyleColors
import com.schibsted.nmp.warp.theme.WarpBackgroundColors
import com.schibsted.nmp.warp.theme.WarpBadgeColors
import com.schibsted.nmp.warp.theme.WarpBorderColors
import com.schibsted.nmp.warp.theme.WarpBoxColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpButtonElementColors
import com.schibsted.nmp.warp.theme.WarpButtonStyleColors
import com.schibsted.nmp.warp.theme.WarpCalloutColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentBackground
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpIconColors
import com.schibsted.nmp.warp.theme.WarpLabelColors
import com.schibsted.nmp.warp.theme.WarpStepIndicatorColors
import com.schibsted.nmp.warp.theme.WarpTabColors
import com.schibsted.nmp.warp.theme.WarpTabRowColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.WarpTextFieldColors
import com.schibsted.nmp.warp.theme.White

internal object FinnColors : WarpColors {
    override val background: WarpBackgroundColors = FinnBackgroundColors
    override val border: WarpBorderColors = FinnBorderColors
    override val icon: WarpIconColors = FinnIconColors
    override val text: WarpTextColors = FinnTextColors
    override val components: WarpComponentColors = FinnComponentColors
}

internal object FinnBackgroundColors : WarpBackgroundColors {
    override val default = White
    override val active = Bluegray200
    override val disabled = Bluegray300
    override val subtle = Bluegray50
    override val subtleActive = Bluegray200
    override val selected = Blue50

    override val inverted = Gray900

    override val primary = Blue600
    override val primaryActive = Blue800
    override val primarySubtle = Blue50
    override val primarySubtleActive = Blue200

    override val positive = Green600
    override val positiveActive = Green800
    override val positiveSubtle = Green50
    override val positiveSubtleActive = Green200
    override val positiveSelected = Green50
    
    override val negative = Red600
    override val negativeActive = Red800
    override val negativeSubtle = Red50
    override val negativeSubtleActive = Red200
    override val negativeSelected = Red50
    
    override val warning = Yellow600
    override val warningActive = Yellow800
    override val warningSubtle = Yellow50
    override val warningSubtleActive = Yellow200
    override val warningSelected = Yellow50
    
    override val info = Aqua600
    override val infoActive = Aqua800
    override val infoSubtle = Aqua50
    override val infoSubtleActive = Aqua200
    override val infoSelected = Aqua50
    
    override val notification = Red600
}

internal object FinnBorderColors : WarpBorderColors {
    override val default = Bluegray300
    override val active = Bluegray500
    override val disabled = Bluegray300
    override val selected = Blue600

    override val primary = Blue600
    override val primaryActive = Blue800
    override val primarySubtle = Blue300
    override val primarySubtleActive = Blue500

    override val positive = Green600
    override val positiveActive = Green800
    override val positiveSubtle = Green300
    override val positiveSubtleActive = Green500

    override val negative = Red600
    override val negativeActive = Red800
    override val negativeSubtle = Red300
    override val negativeSubtleActive = Red500

    override val warning = Yellow600
    override val warningActive = Yellow800
    override val warningSubtle = Yellow300
    override val warningSubtleActive = Yellow500

    override val info = Aqua600
    override val infoActive = Aqua800
    override val infoSubtle = Aqua300
    override val infoSubtleActive = Aqua500
}

internal object FinnIconColors : WarpIconColors {
    override val default = Gray700
    override val active = Blue700
    override val selected = Blue600
    override val disabled = Bluegray300
    override val subtle = Bluegray400
    override val subtleActive = Bluegray600
    override val inverted = White
    override val invertedActive = Bluegray200
    override val primary = Blue600
    override val positive = Green600
    override val negative = Red600
    override val warning = Yellow600
    override val info = Aqua600
    override val notification = White
}

internal object FinnTextColors : WarpTextColors {
    override val default = Gray700
    override val subtle = Gray500
    override val placeholder = Bluegray300
    override val inverted = White
    override val invertedSubtle = Gray50
    override val link = Blue600
    override val disabled = Bluegray300
    override val negative = Red600
    override val positive = Green600
    override val notification = White
}

internal object FinnComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = FinnButtonColors
    override val box: WarpBoxColors = FinnBoxColors
    override val alert: WarpAlertColors = FinnAlertColors
    override val stepIndicator: WarpStepIndicatorColors = FinnStepIndicatorColors
    override val textField: WarpTextFieldColors = FinnTextFieldColors
    override val tabRow: WarpTabRowColors = FinnTabRowColors
    override val tab: WarpTabColors = FinnTabColors
    override val badge: WarpBadgeColors = FinnBadgeColors
    override val label: WarpLabelColors = FinnLabelColors
    override val callout: WarpCalloutColors = FinnCalloutColors
}

internal object FinnCalloutColors : WarpCalloutColors {
    override val background: Color = Green100
    override val border: Color = Green400
    override val text: Color = FinnTextColors.default
}

internal object FinnBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = Aqua100
    override val infoText: Color = Aqua900
    override val positiveBackground: Color = Green100
    override val positiveText: Color = Green900
    override val warningBackground: Color = Yellow100
    override val warningText: Color = Yellow900
    override val negativeBackground: Color = Red100
    override val negativeText: Color = Red900
    override val disabledBackground: Color = Bluegray300
    override val disabledText: Color = Bluegray900
    override val neutralBackground: Color = Gray100
    override val neutralText: Color = Bluegray900
}

internal object FinnLabelColors: WarpLabelColors {
    override val text: Color = Gray700
    override val negative: Color = Red600
    override val optional: Color = Gray700
    override val icon: Color = Gray700
}

internal object FinnButtonColors : WarpButtonColors {
    override val primary: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Blue600,
            active = Blue800
        ),
        border = null
    )
    override val secondary: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Blue600,
        background = FinnButtonElementColors(
            default = White,
            active = Bluegray200
        ),
        border = FinnButtonElementColors(
            default = Bluegray300,
            active = Bluegray500
        )
    )
    override val disabled: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Bluegray300,
            active = Bluegray300
        ),
        border = null
    )
    override val quiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Blue600,
        background = FinnButtonElementColors(
            default = Transparent,
            active = Bluegray200
        ),
        border = null
    )
    override val negative: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Red600,
            active = Red800
        ),
        border = null
    )
    override val negativeQuiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Red600,
        background = FinnButtonElementColors(
            default = Transparent,
            active = Red200
        ),
        border = null
    )
    override val utility: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Gray700,
        background = FinnButtonElementColors(
            default = White,
            active = Bluegray200
        ),
        border = FinnButtonElementColors(
            default = Bluegray300,
            active = Bluegray500
        )
    )
    override val utilityOverlay: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Gray700,
        background = FinnButtonElementColors(
            default = White,
            active = Bluegray200
        ),
        border = null
    )
    override val utilityQuiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Gray700,
        background = FinnButtonElementColors(
            default = Transparent,
            active = Transparent
        ),
        border = null
    )
    override val loading: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Gray500,
        background = FinnButtonElementColors(
            default = Bluegray50,
            active = Bluegray200
        ),
        border = null
    )
}

internal object FinnTabColors: WarpTabColors {
    override val selectedContent: Color = Blue600
    override val unselectedContent: Color = Gray500
}

internal object FinnTabRowColors: WarpTabRowColors {
    override val container: Color = White
    override val content: Color = Blue600
    override val indicator: Color = Blue600
}

internal object FinnTextFieldColors : WarpTextFieldColors {
    override val text: Color = Gray700
    override val hintText: Color= Bluegray300
    override val disabledText: Color = Bluegray300
    override val errorText: Color = Red600
    override val readOnlyText: Color = Gray700
    override val errorIconColor: Color = Red600
    override val background: Color = White
    override val disabledBackground: Color = Bluegray300
    override val border: Color = Bluegray300
    override val activeBorder: Color = Blue600
    override val disabledBorder: Color = Bluegray300
    override val errorBorder: Color = Red600
    override val caret: Color = Gray700
    override val iconTint: Color = Gray700
}

internal object FinnAlertColors : WarpAlertColors {
    override val info: WarpAlertStyleColors = FinnAlertStyleColors(
        background = Aqua50,
        leftBorder = Aqua600,
        border = Aqua300
    )
    override val positive: WarpAlertStyleColors = FinnAlertStyleColors(
    background = Green50,
    leftBorder = Green600,
    border = Green300
    )
    override val negative: WarpAlertStyleColors = FinnAlertStyleColors(
        background = Red50,
        leftBorder = Red600,
        border = Red300
    )
    override val warning: WarpAlertStyleColors = FinnAlertStyleColors(
        background = Yellow50,
        leftBorder = Yellow600,
        border = Yellow300
    )
}

internal data class FinnButtonStyleColors(
    override val text: Color,
    override val background: FinnButtonElementColors,
    override val border: FinnButtonElementColors?
) : WarpButtonStyleColors

internal data class FinnButtonElementColors(
    override val default: Color,
    override val active: Color
) : WarpButtonElementColors

internal data class FinnAlertStyleColors(
    override val background: Color,
    override val leftBorder: Color,
    override val border: Color
) : WarpAlertStyleColors

internal object FinnBoxColors : WarpBoxColors {
    override val neutral = WarpComponentBackground(Bluegray50, null)
    override val info = WarpComponentBackground(Aqua50, null)
    override val bordered = WarpComponentBackground(White, Bluegray300)
}

internal object FinnStepIndicatorColors : WarpStepIndicatorColors {
    override val background: Color = Bluegray300
    override val backgroundActive: Color = Blue600
    override val backgroundTrack: Color = Bluegray300
    override val backgroundTrackActive: Color = Blue600
    override val border: Color = Bluegray300
    override val borderActive: Color = Blue600
    override val icon: Color = White
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

//Gray
internal val Gray50 = Color(0xfffafafa)
internal val Gray100 = Color(0xfff4f4f5)
internal val Gray200 = Color(0xffe4e4e7)
internal val Gray300 = Color(0xffd4d4d8)
internal val Gray400 = Color(0xffa1a1aa)
internal val Gray500 = Color(0xff71717a)
internal val Gray600 = Color(0xff52525b)
internal val Gray700 = Color(0xff3f3f46)
internal val Gray800 = Color(0xff27272a)
internal val Gray900 = Color(0xff18181b)

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

//Bluegray
internal val Bluegray50 = Color(0xfff8fafc)
internal val Bluegray100 = Color(0xfff1f4f9)
internal val Bluegray200 = Color(0xffe1e6ee)
internal val Bluegray300 = Color(0xffc3ccd9)
internal val Bluegray400 = Color(0xff9ba8ba)
internal val Bluegray500 = Color(0xff6f7d90)
internal val Bluegray600 = Color(0xff4d586f)
internal val Bluegray700 = Color(0xff3b4353)
internal val Bluegray800 = Color(0xff292d38)
internal val Bluegray900 = Color(0xff181a1f)
