@file:Suppress("unused")

package com.schibsted.nmp.warp.brands.tori

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpAlertColors
import com.schibsted.nmp.warp.theme.WarpAlertStyleColors
import com.schibsted.nmp.warp.theme.WarpBoxColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpButtonElementColors
import com.schibsted.nmp.warp.theme.WarpButtonStyleColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpLabelColors
import com.schibsted.nmp.warp.theme.WarpStepIndicatorColors
import com.schibsted.nmp.warp.theme.WarpTabColors
import com.schibsted.nmp.warp.theme.WarpTabRowColors
import com.schibsted.nmp.warp.theme.WarpTextFieldColors
import com.schibsted.nmp.warp.theme.White

object ToriColors : WarpColors {
    override val background: Color = White
    override val components: WarpComponentColors = ToriComponentColors
}

object ToriComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = ToriButtonColors
    override val box: WarpBoxColors = ToriBoxColors
    override val stepIndicator: WarpStepIndicatorColors = ToriStepIndicatorColors
    override val alert: WarpAlertColors = ToriAlertColors
    override val textField: WarpTextFieldColors = ToriTextFieldColors
    override val tab: WarpTabColors = ToriTabColors
    override val tabRow: WarpTabRowColors = ToriTabRowColors
    override val label: WarpLabelColors = ToriLabelColors
}

object ToriLabelColors: WarpLabelColors {
    override val text: Color = Gray900
    override val negative: Color = Red600
    override val optional: Color = Gray900
    override val icon: Color = Gray900
}

object ToriButtonColors : WarpButtonColors {
    override val primary: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Watermelon600,
            active = Watermelon800
        ),
        border = null
    )
    override val secondary: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Petroleum600,
        background = ToriButtonElementColors(
            default = White,
            active = Gray200
        ),
        border = ToriButtonElementColors(
            default = Gray300,
            active = Gray500
        )
    )
    override val disabled: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Gray300,
            active = Gray300
        ),
        border = null
    )
    override val quiet: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Petroleum600,
        background = ToriButtonElementColors(
            default = Transparent,
            active = Gray200
        ),
        border = null
    )
    override val negative: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Red600,
            active = Red800
        ),
        border = null
    )
    override val negativeQuiet: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Red600,
        background = ToriButtonElementColors(
            default = Transparent,
            active = Red200
        ),
        border = null
    )
    override val utility: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Gray900,
        background = ToriButtonElementColors(
            default = White,
            active = Gray200
        ),
        border = ToriButtonElementColors(
            default = Gray300,
            active = Gray800
        )
    )
    override val utilityOverlay: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Gray900,
        background = ToriButtonElementColors(
            default = White,
            active = Gray200
        ),
        border = null
    )
    override val utilityQuiet: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Gray900,
        background = ToriButtonElementColors(
            default = Transparent,
            active = Transparent
        ),
        border = null
    )
    override val loading: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Gray500,
        background = ToriButtonElementColors(
            default = Gray50,
            active = Gray200
        ),
        border = null
    )
}

object ToriTabColors : WarpTabColors {
    override val selectedContent: Color = Petroleum600
    override val unselectedContent: Color = Gray500
}

object ToriTabRowColors : WarpTabRowColors {
    override val container: Color = White
    override val content: Color = Petroleum600
    override val indicator: Color = Petroleum600

}

object ToriTextFieldColors : WarpTextFieldColors {
    override val text: Color = Gray900
    override val hintText: Color = Gray300
    override val disabledText: Color = Gray300
    override val errorText: Color = Red600
    override val readOnlyText: Color = Gray900
    override val errorIconColor: Color = Red600
    override val background: Color = White
    override val disabledBackground: Color = Gray300
    override val border: Color = Gray300
    override val activeBorder: Color = Petroleum600
    override val disabledBorder: Color = Gray300
    override val errorBorder: Color = Red600
    override val caret: Color = Gray900
    override val iconTint: Color = Gray900

}

object ToriAlertColors : WarpAlertColors {
    override val info: WarpAlertStyleColors = ToriAlertStyleColors(
        background = Petroleum50,
        leftBorder = Petroleum600,
        border = Petroleum300
    )
    override val positive: WarpAlertStyleColors = ToriAlertStyleColors(
        background = Green50,
        leftBorder = Green600,
        border = Green300
    )
    override val negative: WarpAlertStyleColors = ToriAlertStyleColors(
        background = Red50,
        leftBorder = Red600,
        border = Red300
    )
    override val warning: WarpAlertStyleColors = ToriAlertStyleColors(
        background = Yellow50,
        leftBorder = Yellow600,
        border = Yellow300
    )
}

data class ToriButtonStyleColors(
    override val text: Color,
    override val background: ToriButtonElementColors,
    override val border: ToriButtonElementColors?
) : WarpButtonStyleColors

data class ToriButtonElementColors(
    override val default: Color,
    override val active: Color
) : WarpButtonElementColors

data class ToriAlertStyleColors(
    override val background: Color,
    override val leftBorder: Color,
    override val border: Color
) : WarpAlertStyleColors

//Watermelon
internal val Watermelon50 = Color(0xFFFFF3F2)
internal val Watermelon100 = Color(0xFFFFE6E4)
internal val Watermelon200 = Color(0xFFFECBC7)
internal val Watermelon300 = Color(0xFFFEB0AC)
internal val Watermelon400 = Color(0xFFFD948F)
internal val Watermelon500 = Color(0xFFFD7372)
internal val Watermelon600 = Color(0xFFF75159)
internal val Watermelon700 = Color(0xFFB24046)
internal val Watermelon800 = Color(0xFF742E30)
internal val Watermelon900 = Color(0xFF3C1B1B)

//Petroleum
internal val Petroleum50 = Color(0xFFEEF4F5)
internal val Petroleum100 = Color(0xFFDDE9EC)
internal val Petroleum200 = Color(0xFFBAD3DA)
internal val Petroleum300 = Color(0xFF97BDC6)
internal val Petroleum400 = Color(0xFF74A8B4)
internal val Petroleum500 = Color(0xFF4D93A2)
internal val Petroleum600 = Color(0xFF157F91)
internal val Petroleum700 = Color(0xFF1B5E6A)
internal val Petroleum800 = Color(0xFF193F47)
internal val Petroleum900 = Color(0xFF122226)

//Green
internal val Green50 = Color(0xFFEBF1EB)
internal val Green100 = Color(0xFFD7E3D7)
internal val Green200 = Color(0xFFB0C7AF)
internal val Green300 = Color(0xFF89AA89)
internal val Green400 = Color(0xFF648F66)
internal val Green500 = Color(0xFF3C7542)
internal val Green600 = Color(0xFF0A5B21)
internal val Green700 = Color(0xFF10451B)
internal val Green800 = Color(0xFF113015)
internal val Green900 = Color(0xFF0D1C0D)

//Yellow
internal val Yellow50 = Color(0xFFFEF4ED)
internal val Yellow100 = Color(0xFFFCEAD9)
internal val Yellow200 = Color(0xFFF7D5B3)
internal val Yellow300 = Color(0xFFEFC190)
internal val Yellow400 = Color(0xFFE6AD6C)
internal val Yellow500 = Color(0xFFDB9949)
internal val Yellow600 = Color(0xFFCF8623)
internal val Yellow700 = Color(0xFF97631E)
internal val Yellow800 = Color(0xFF634218)
internal val Yellow900 = Color(0xFF342410)

//Red
internal val Red50 = Color(0xFFF9EBEA)
internal val Red100 = Color(0xFFF3D8D5)
internal val Red200 = Color(0xFFE4B2AB)
internal val Red300 = Color(0xFFD38C85)
internal val Red400 = Color(0xFFBF685F)
internal val Red500 = Color(0xFFAA403C)
internal val Red600 = Color(0xFF930B1D)
internal val Red700 = Color(0xFF6D1217)
internal val Red800 = Color(0xFF4A1212)
internal val Red900 = Color(0xFF2A0F0A)

//Gray
internal val Gray50 = Color(0xFFFAFAFA)
internal val Gray100 = Color(0xFFF5F5F5)
internal val Gray200 = Color(0xFFE6E6E6)
internal val Gray300 = Color(0xFFD6D6D6)
internal val Gray400 = Color(0xFFA5A5A5)
internal val Gray500 = Color(0xFF767676)
internal val Gray600 = Color(0xFF575757)
internal val Gray700 = Color(0xFF434343)
internal val Gray800 = Color(0xFF292929)
internal val Gray900 = Color(0xFF1A1A1A)
