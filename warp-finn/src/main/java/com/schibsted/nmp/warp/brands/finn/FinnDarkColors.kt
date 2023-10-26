package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Black
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpAlertColors
import com.schibsted.nmp.warp.theme.WarpAlertStyleColors
import com.schibsted.nmp.warp.theme.WarpBackgroundColors
import com.schibsted.nmp.warp.theme.WarpBadgeColors
import com.schibsted.nmp.warp.theme.WarpBorderColors
import com.schibsted.nmp.warp.theme.WarpBoxColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
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

internal object FinnDarkColors : WarpColors {
    override val background: WarpBackgroundColors = FinnDarkBackgroundColors
    override val border: WarpBorderColors = FinnDarkBorderColors
    override val icon: WarpIconColors = FinnDarkIconColors
    override val text: WarpTextColors = FinnDarkTextColors
    override val components: WarpComponentColors = FinnComponentDarkColors
}

internal object FinnDarkBackgroundColors : WarpBackgroundColors {
    override val default = Black
    override val active = Bluegray900
    override val disabled = Bluegray700
    override val subtle = Bluegray900
    override val subtleActive = Bluegray900
    override val selected = Blue900

    override val inverted = Gray50

    override val primary = Blue500
    override val primaryActive = Blue700
    override val primarySubtle = Blue900
    override val primarySubtleActive = Blue900

    override val positive = Green500
    override val positiveActive = Green700
    override val positiveSubtle = Green900
    override val positiveSubtleActive = Green900
    override val positiveSelected = Green900

    override val negative = Red500
    override val negativeActive = Red700
    override val negativeSubtle = Red900
    override val negativeSubtleActive = Red900
    override val negativeSelected = Red900

    override val warning = Yellow500
    override val warningActive = Yellow700
    override val warningSubtle = Yellow900
    override val warningSubtleActive = Yellow900
    override val warningSelected = Yellow900

    override val info = Aqua500
    override val infoActive = Aqua700
    override val infoSubtle = Aqua900
    override val infoSubtleActive = Aqua900
    override val infoSelected = Aqua500

    override val notification = Red500
}

internal object FinnDarkBorderColors : WarpBorderColors {
    override val default = Bluegray600
    override val active = Bluegray800
    override val disabled = Bluegray300
    override val selected = Blue500

    override val primary = Blue500
    override val primaryActive = Blue700
    override val primarySubtle = Blue700
    override val primarySubtleActive = Blue900

    override val positive = Green500
    override val positiveActive = Green700
    override val positiveSubtle = Green700
    override val positiveSubtleActive = Green900

    override val negative = Red500
    override val negativeActive = Red700
    override val negativeSubtle = Red700
    override val negativeSubtleActive = Red900

    override val warning = Yellow500
    override val warningActive = Yellow700
    override val warningSubtle = Yellow700
    override val warningSubtleActive = Yellow900

    override val info = Aqua500
    override val infoActive = Aqua700
    override val infoSubtle = Aqua700
    override val infoSubtleActive = Aqua900
}

internal object FinnDarkIconColors : WarpIconColors {
    override val default = White
    override val active = Gray200
    override val selected = Blue500
    override val disabled = Bluegray600
    override val subtle = Bluegray100
    override val subtleActive = Bluegray300
    override val inverted = Black
    override val invertedActive = Bluegray900
    override val primary = Blue500
    override val positive = Green500
    override val negative = Red500
    override val warning = Yellow500
    override val info = Aqua500
    override val notification = White
}

internal object FinnDarkTextColors : WarpTextColors {
    override val default = White
    override val subtle = Gray100
    override val placeholder = Bluegray500
    override val inverted = White
    override val invertedSubtle = Gray800
    override val link = Blue500
    override val disabled = Bluegray500
    override val negative = Red500
    override val positive = Green500
    override val notification = White
}

internal object FinnComponentDarkColors : WarpComponentColors {
    override val button: WarpButtonColors = FinnButtonDarkColors
    override val box: WarpBoxColors = FinnBoxDarkColors
    override val alert: WarpAlertColors = FinnAlertDarkColors
    override val stepIndicator: WarpStepIndicatorColors = FinnStepIndicatorDarkColors
    override val textField: WarpTextFieldColors = FinnTextFieldDarkColors
    override val tabRow: WarpTabRowColors = FinnTabRowDarkColors
    override val tab: WarpTabColors = FinnTabDarkColors
    override val badge: WarpBadgeColors = FinnBadgeDarkColors
    override val label: WarpLabelColors = FinnLabelDarkColors
    override val callout: WarpCalloutColors = FinnCalloutDarkColors
}

internal object FinnCalloutDarkColors : WarpCalloutColors {
    override val background: Color = Green800
    override val border: Color = Green600
    override val text: Color = FinnDarkTextColors.default
}

internal object FinnBadgeDarkColors : WarpBadgeColors {
    override val infoBackground: Color = FinnDarkBackgroundColors.infoSubtle
    override val infoText: Color = FinnDarkTextColors.default
    override val positiveBackground: Color = FinnDarkBackgroundColors.positiveSubtle
    override val positiveText: Color = FinnDarkTextColors.default
    override val warningBackground: Color = FinnDarkBackgroundColors.warningSubtle
    override val warningText: Color = FinnDarkTextColors.default
    override val negativeBackground: Color = FinnDarkBackgroundColors.negativeSubtle
    override val negativeText: Color = FinnDarkTextColors.default
    override val disabledBackground: Color = FinnDarkBackgroundColors.disabled
    override val disabledText: Color = FinnDarkTextColors.default
    override val neutralBackground: Color = FinnDarkBackgroundColors.subtle
    override val neutralText: Color = FinnDarkTextColors.default
}

internal object FinnTabDarkColors: WarpTabColors {
    override val selectedContent: Color = FinnDarkTextColors.link
    override val unselectedContent: Color = FinnDarkTextColors.subtle
}

internal object FinnTabRowDarkColors: WarpTabRowColors {
    override val container: Color = FinnDarkBackgroundColors.default
    override val content: Color = FinnDarkTextColors.subtle
    override val indicator: Color = FinnIconColors.default
}

internal object FinnStepIndicatorDarkColors : WarpStepIndicatorColors {
    override val background: Color = Bluegray600
    override val backgroundActive: Color = Blue500
    override val backgroundTrack: Color = Bluegray600
    override val backgroundTrackActive: Color = Blue500
    override val border: Color = Bluegray600
    override val borderActive: Color = Blue500
    override val icon: Color = Black
}

internal object FinnAlertDarkColors : WarpAlertColors {
    override val info: WarpAlertStyleColors = FinnAlertStyleColors(
        background = Aqua900,
        leftBorder = Aqua500,
        border = Aqua700
    )
    override val positive: WarpAlertStyleColors = FinnAlertStyleColors(
        background = Green900,
        leftBorder = Green500,
        border = Green700
    )
    override val negative: WarpAlertStyleColors = FinnAlertStyleColors(
        background = Red900,
        leftBorder = Red500,
        border = Red700
    )
    override val warning: WarpAlertStyleColors = FinnAlertStyleColors(
        background = Yellow900,
        leftBorder = Yellow500,
        border = Yellow700
    )
}

internal object FinnBoxDarkColors : WarpBoxColors {
    override val neutral = WarpComponentBackground(Bluegray900, null)
    override val info = WarpComponentBackground(Aqua900, null)
    override val bordered = WarpComponentBackground(Black, Bluegray600)
}

internal object FinnLabelDarkColors: WarpLabelColors {
    override val text: Color = White
    override val negative: Color = Red500
    override val optional: Color = White
    override val icon: Color = White
}

internal object FinnTextFieldDarkColors : WarpTextFieldColors {
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

internal object FinnButtonDarkColors : WarpButtonColors {
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