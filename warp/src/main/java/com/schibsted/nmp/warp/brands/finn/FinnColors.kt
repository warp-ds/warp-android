@file:Suppress("unused")

package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.brands.finn.button.FinnButtonColors
import com.schibsted.nmp.warp.theme.WarpAlertColors
import com.schibsted.nmp.warp.theme.WarpAlertStyleColors
import com.schibsted.nmp.warp.theme.WarpBoxColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpStepIndicatorColors
import com.schibsted.nmp.warp.theme.White

object FinnColors : WarpColors {
    override val primary: Color = Blue600
    override val secondary: Color = White
    override val components: WarpComponentColors = FinnComponentColors
}

object FinnComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = FinnButtonColors
    override val box: WarpBoxColors = FinnBoxColors
    override val alert: WarpAlertColors = FinnAlertColors
    override val stepIndicator: WarpStepIndicatorColors = FinnStepIndicatorColors
}

object FinnAlertColors : WarpAlertColors {
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

data class FinnAlertStyleColors(
    override val background: Color,
    override val leftBorder: Color,
    override val border: Color
) : WarpAlertStyleColors

object FinnDarkColors : WarpColors {
    override val primary: Color = Blue500 //todo darkmode for this color
    override val secondary: Color = White
    override val components: WarpComponentColors = FinnComponentColors // TODO darkmode variant
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
