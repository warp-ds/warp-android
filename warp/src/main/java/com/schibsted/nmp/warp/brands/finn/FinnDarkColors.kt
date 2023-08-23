package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Black
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpAlertColors
import com.schibsted.nmp.warp.theme.WarpBoxColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpStepIndicatorColors
import com.schibsted.nmp.warp.theme.WarpTabColors
import com.schibsted.nmp.warp.theme.WarpTabRowColors
import com.schibsted.nmp.warp.theme.WarpTextFieldColors
import com.schibsted.nmp.warp.theme.White

object FinnDarkColors : WarpColors {
    override val background: Color = Black
    override val components: WarpComponentColors = FinnComponentDarkColors
}

object FinnComponentDarkColors : WarpComponentColors {
    override val button: WarpButtonColors = FinnButtonDarkColors
    override val box: WarpBoxColors = FinnBoxColors // Todo darkmode
    override val alert: WarpAlertColors = FinnAlertColors // Todo darkmode
    override val stepIndicator: WarpStepIndicatorColors = FinnStepIndicatorColors // Todo darkmode
    override val textField: WarpTextFieldColors = FinnTextFieldDarkColors
    override val tabRow: WarpTabRowColors = FinnTabRowColors // Todo darkmode
    override val tab: WarpTabColors = FinnTabColors // Todo darkmode
}

object FinnTextFieldDarkColors : WarpTextFieldColors {
    override val text: Color = White
    override val hintText: Color= Bluegray500
    override val disabledText: Color = Bluegray500
    override val errorText: Color = Red500
    override val readOnlyText: Color = White
    override val errorIconColor: Color = Red500
    override val background: Color = Black
    override val disabledBackground: Color = Bluegray700
    override val border: Color = Bluegray600
    override val activeBorder: Color = Blue500
    override val disabledBorder: Color = Bluegray300
    override val errorBorder: Color = Red500
    override val caret: Color = White
    override val iconTint: Color = White
}

object FinnButtonDarkColors : WarpButtonColors {
    override val primary: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Blue500,
            active = Blue700
        ),
        border = null
    )
    override val secondary: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Blue500,
        background = FinnButtonElementColors(
            default = Black,
            active = Bluegray900
        ),
        border = FinnButtonElementColors(
            default = Bluegray600,
            active = Bluegray800
        )
    )
    override val disabled: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Bluegray700,
            active = Bluegray700
        ),
        border = null
    )
    override val quiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Blue500,
        background = FinnButtonElementColors(
            default = Transparent,
            active = Bluegray900
        ),
        border = null
    )
    override val negative: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Red500,
            active = Red700
        ),
        border = null
    )
    override val negativeQuiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Red500,
        background = FinnButtonElementColors(
            default = Transparent,
            active = Red900
        ),
        border = null
    )
    override val utility: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Black,
            active = Bluegray900
        ),
        border = FinnButtonElementColors(
            default = Bluegray600,
            active = Bluegray800
        )
    )
    override val utilityOverlay: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Black,
            active = Bluegray900
        ),
        border = null
    )
    override val utilityQuiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Transparent,
            active = Transparent
        ),
        border = null
    )
    override val loading: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Gray100,
        background = FinnButtonElementColors(
            default = Bluegray900,
            active = Bluegray700
        ),
        border = null
    )
}