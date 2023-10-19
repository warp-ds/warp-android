package com.schibsted.nmp.warp.brands.tori

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Black
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpAlertColors
import com.schibsted.nmp.warp.theme.WarpBadgeColors
import com.schibsted.nmp.warp.theme.WarpBoxColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpLabelColors
import com.schibsted.nmp.warp.theme.WarpSemanticBackgroundColors
import com.schibsted.nmp.warp.theme.WarpSemanticBorderColors
import com.schibsted.nmp.warp.theme.WarpSemanticIconColors
import com.schibsted.nmp.warp.theme.WarpSemanticTextColors
import com.schibsted.nmp.warp.theme.WarpStepIndicatorColors
import com.schibsted.nmp.warp.theme.WarpTabColors
import com.schibsted.nmp.warp.theme.WarpTabRowColors
import com.schibsted.nmp.warp.theme.WarpTextFieldColors
import com.schibsted.nmp.warp.theme.White

internal object ToriDarkColors : WarpColors {
    override val background: WarpSemanticBackgroundColors = ToriSemanticDarkBackgroundColors
    override val border: WarpSemanticBorderColors = ToriSemanticDarkBorderColors
    override val icon: WarpSemanticIconColors = ToriSemanticDarkIconColors
    override val text: WarpSemanticTextColors = ToriSemanticDarkTextColors
    override val components: WarpComponentColors = ToriComponentDarkColors
}

internal object ToriSemanticDarkBackgroundColors : WarpSemanticBackgroundColors {
    override val default = Black
    override val active = Gray900
    override val disabled = Gray700
    override val subtle = Gray900
    override val subtleActive = Gray900
    override val selected = Petroleum900

    override val inverted = Gray50

    override val primary = Petroleum500
    override val primaryActive = Petroleum800
    override val primarySubtle = Petroleum900
    override val primarySubtleActive = Petroleum900

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

    override val info = Petroleum500
    override val infoActive = Petroleum700
    override val infoSubtle = Petroleum900
    override val infoSubtleActive = Petroleum900
    override val infoSelected = Petroleum500

    override val notification = Red500
}

internal object ToriSemanticDarkBorderColors : WarpSemanticBorderColors {
    override val default = Gray600
    override val active = Gray800
    override val disabled = Gray300
    override val selected = Petroleum500

    override val primary = Petroleum500
    override val primaryActive = Petroleum700
    override val primarySubtle = Petroleum700
    override val primarySubtleActive = Petroleum900

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

    override val info = Petroleum500
    override val infoActive = Petroleum700
    override val infoSubtle = Petroleum700
    override val infoSubtleActive = Petroleum900
}

internal object ToriSemanticDarkIconColors : WarpSemanticIconColors {
    override val default = White
    override val active = Gray200
    override val selected = Petroleum500
    override val disabled = Gray600
    override val subtle = Gray100
    override val subtleActive = Gray300
    override val inverted = Black
    override val invertedActive = Gray900
    override val primary = Petroleum500
    override val positive = Green500
    override val negative = Red500
    override val warning = Yellow500
    override val info = Petroleum500
    override val notification = White
}

internal object ToriSemanticDarkTextColors : WarpSemanticTextColors {
    override val default = White
    override val subtle = Gray100
    override val placeholder = Gray500
    override val inverted = White
    override val invertedSubtle = Gray800
    override val link = Petroleum500
    override val disabled = Gray500
    override val negative = Red500
    override val positive = Green500
    override val notification = White
}

internal object ToriComponentDarkColors : WarpComponentColors {
    override val button: WarpButtonColors = ToriButtonDarkColors
    override val box: WarpBoxColors = ToriBoxColors // Todo darkmode
    override val stepIndicator: WarpStepIndicatorColors = ToriStepIndicatorColors // Todo darkmode
    override val alert: WarpAlertColors = ToriAlertColors // Todo darkmode
    override val textField: WarpTextFieldColors = ToriTextFieldDarkColors
    override val tab: WarpTabColors = ToriTabColors // Todo darkmode
    override val tabRow: WarpTabRowColors = ToriTabRowColors // Todo darkmode
    override val badge: WarpBadgeColors = ToriBadgeColors // TODO: Darkmode
    override val label: WarpLabelColors = ToriLabelDarkColors
}

internal object ToriLabelDarkColors: WarpLabelColors {
    override val text: Color = White
    override val negative: Color = Red500
    override val optional: Color = White
    override val icon: Color = White
}

internal object ToriTextFieldDarkColors : WarpTextFieldColors {
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

internal object ToriButtonDarkColors : WarpButtonColors {
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