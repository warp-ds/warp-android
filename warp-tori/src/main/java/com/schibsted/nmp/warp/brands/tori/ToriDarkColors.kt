package com.schibsted.nmp.warp.brands.tori

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Black
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpAlertColors
import com.schibsted.nmp.warp.theme.WarpBoxColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpLabelColors
import com.schibsted.nmp.warp.theme.WarpStepIndicatorColors
import com.schibsted.nmp.warp.theme.WarpTabColors
import com.schibsted.nmp.warp.theme.WarpTabRowColors
import com.schibsted.nmp.warp.theme.WarpTextFieldColors
import com.schibsted.nmp.warp.theme.White

object ToriDarkColors : WarpColors {
    override val background: Color = Black
    override val components: WarpComponentColors = ToriComponentDarkColors
}

object ToriComponentDarkColors : WarpComponentColors {
    override val button: WarpButtonColors = ToriButtonDarkColors
    override val box: WarpBoxColors = ToriBoxColors // Todo darkmode
    override val stepIndicator: WarpStepIndicatorColors = ToriStepIndicatorColors // Todo darkmode
    override val alert: WarpAlertColors = ToriAlertColors // Todo darkmode
    override val textField: WarpTextFieldColors = ToriTextFieldDarkColors
    override val tab: WarpTabColors = ToriTabColors // Todo darkmode
    override val tabRow: WarpTabRowColors = ToriTabRowColors // Todo darkmode
    override val label: WarpLabelColors = ToriLabelDarkColors
}

object ToriLabelDarkColors: WarpLabelColors {
    override val text: Color = White
    override val negative: Color = Red500
    override val optional: Color = White
    override val icon: Color = White
}

object ToriTextFieldDarkColors : WarpTextFieldColors {
    override val text: Color = White
    override val hintText: Color = Gray500
    override val disabledText: Color = Gray500
    override val errorText: Color = Red500
    override val readOnlyText: Color = White
    override val errorIconColor: Color = Red500
    override val background: Color = Black
    override val disabledBackground: Color = Gray700
    override val border: Color = Gray600
    override val activeBorder: Color = Petroleum500
    override val disabledBorder: Color = Gray300
    override val errorBorder: Color = Red500
    override val caret: Color = White
    override val iconTint: Color = White

}

object ToriButtonDarkColors : WarpButtonColors {
    override val primary: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Watermelon500,
            active = Watermelon800
        ),
        border = null
    )
    override val secondary: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Petroleum500,
        background = ToriButtonElementColors(
            default = Black,
            active = Gray900
        ),
        border = ToriButtonElementColors(
            default = Gray600,
            active = Gray800
        )
    )
    override val disabled: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Gray700,
            active = Gray700
        ),
        border = null
    )
    override val quiet: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Petroleum500,
        background = ToriButtonElementColors(
            default = Transparent,
            active = Gray900
        ),
        border = null
    )
    override val negative: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Red500,
            active = Red700
        ),
        border = null
    )
    override val negativeQuiet: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Red500,
        background = ToriButtonElementColors(
            default = Transparent,
            active = Red900
        ),
        border = null
    )
    override val utility: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Black,
            active = Gray900
        ),
        border = ToriButtonElementColors(
            default = Gray300,
            active = Gray800
        )
    )
    override val utilityOverlay: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Black,
            active = Gray900
        ),
        border = null
    )
    override val utilityQuiet: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Transparent,
            active = Transparent
        ),
        border = null
    )
    override val loading: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Gray100,
        background = ToriButtonElementColors(
            default = Gray900,
            active = Gray700
        ),
        border = null
    )
}