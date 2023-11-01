package com.schibsted.nmp.warp.brands.blocket

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
import com.schibsted.nmp.warp.theme.WarpPillColors
import com.schibsted.nmp.warp.theme.WarpPillStyleColors
import com.schibsted.nmp.warp.theme.WarpStepIndicatorColors
import com.schibsted.nmp.warp.theme.WarpTabColors
import com.schibsted.nmp.warp.theme.WarpTabRowColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.WarpTextFieldColors
import com.schibsted.nmp.warp.theme.White

internal object BlocketDarkColors : WarpColors {
    override val background: WarpBackgroundColors = BlocketDarkBackgroundColors
    override val border: WarpBorderColors = BlocketDarkBorderColors
    override val icon: WarpIconColors = BlocketDarkIconColors
    override val text: WarpTextColors = BlocketDarkTextColors
    override val components: WarpComponentColors = BlocketComponentDarkColors
}

internal object BlocketDarkBackgroundColors : WarpBackgroundColors {
    override val default = Black
    override val active = Gray900
    override val disabled = Gray700
    override val subtle = Gray900
    override val subtleActive = Gray900
    override val selected = Red900

    override val inverted = Gray50

    override val primary = Red500
    override val primaryActive = Red800
    override val primarySubtle = Red900
    override val primarySubtleActive = Red900

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

    override val info = Blue500
    override val infoActive = Blue700
    override val infoSubtle = Blue900
    override val infoSubtleActive = Blue900
    override val infoSelected = Blue500

    override val notification = Red500
}

internal object BlocketDarkBorderColors : WarpBorderColors {
    override val default = Gray600
    override val active = Gray800
    override val disabled = Gray300
    override val selected = Red500

    override val primary = Red500
    override val primaryActive = Red700
    override val primarySubtle = Red700
    override val primarySubtleActive = Red900

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

    override val info = Blue500
    override val infoActive = Blue700
    override val infoSubtle = Blue700
    override val infoSubtleActive = Blue900
}

internal object BlocketDarkIconColors : WarpIconColors {
    override val default = White
    override val active = Gray200
    override val selected = Red500
    override val disabled = Gray600
    override val subtle = Gray100
    override val subtleActive = Gray300
    override val inverted = Black
    override val invertedActive = Gray900
    override val primary = Red500
    override val positive = Green500
    override val negative = Red500
    override val warning = Yellow500
    override val info = Blue500
    override val notification = White
}

internal object BlocketDarkTextColors : WarpTextColors {
    override val default = White
    override val subtle = Gray100
    override val placeholder = Gray500
    override val inverted = White
    override val invertedSubtle = Gray800
    override val link = Red500
    override val disabled = Gray500
    override val negative = Red500
    override val positive = Green500
    override val notification = White
}

internal object BlocketComponentDarkColors : WarpComponentColors {
    override val button: WarpButtonColors = BlocketButtonDarkColors
    override val box: WarpBoxColors = BlocketBoxDarkColors
    override val stepIndicator: WarpStepIndicatorColors = BlocketStepIndicatorDarkColors
    override val alert: WarpAlertColors = BlocketAlertDarkColors
    override val textField: WarpTextFieldColors = BlocketTextFieldDarkColors
    override val tab: WarpTabColors = BlocketTabDarkColors
    override val tabRow: WarpTabRowColors = BlocketTabRowDarkColors
    override val badge: WarpBadgeColors = BlocketBadgeDarkColors
    override val label: WarpLabelColors = BlocketLabelDarkColors
    override val callout: WarpCalloutColors = BlocketCalloutDarkColors
    override val pill: WarpPillColors = BlocketPillDarkColors
}

internal object BlocketPillDarkColors : WarpPillColors {
    override val filter: WarpPillStyleColors = BlocketPillStyleColors(
        text = BlocketDarkTextColors.inverted,
        background = BlocketDarkBackgroundColors.primary,
        backgroundActive = BlocketDarkBackgroundColors.primaryActive,
        icon = BlocketDarkIconColors.inverted
    )
    override val suggestion: WarpPillStyleColors = BlocketPillStyleColors(
        text = BlocketDarkTextColors.default,
        background = BlocketDarkBackgroundColors.subtle,
        backgroundActive = BlocketDarkBackgroundColors.subtleActive,
        icon = BlocketDarkIconColors.default
    )
}

internal object BlocketCalloutDarkColors : WarpCalloutColors {
    override val background: Color = Green800
    override val border: Color = Green600
    override val text: Color = BlocketDarkTextColors.default
}

internal object BlocketBadgeDarkColors : WarpBadgeColors {
    override val infoBackground: Color = BlocketDarkBackgroundColors.infoSubtle
    override val infoText: Color = BlocketDarkTextColors.default
    override val positiveBackground: Color = BlocketDarkBackgroundColors.positiveSubtle
    override val positiveText: Color = BlocketDarkTextColors.default
    override val warningBackground: Color = BlocketDarkBackgroundColors.warningSubtle
    override val warningText: Color = BlocketDarkTextColors.default
    override val negativeBackground: Color = BlocketDarkBackgroundColors.negativeSubtle
    override val negativeText: Color = BlocketDarkTextColors.default
    override val disabledBackground: Color = BlocketDarkBackgroundColors.disabled
    override val disabledText: Color = BlocketDarkTextColors.default
    override val neutralBackground: Color = BlocketDarkBackgroundColors.subtle
    override val neutralText: Color = BlocketDarkTextColors.default
}

internal object BlocketLabelDarkColors: WarpLabelColors {
    override val text: Color = BlocketDarkTextColors.default
    override val negative: Color = BlocketDarkTextColors.negative
    override val optional: Color = BlocketDarkTextColors.default
    override val icon: Color = BlocketDarkIconColors.default
    override val disabled: Color = BlocketDarkTextColors.disabled
}

internal object BlocketButtonDarkColors : WarpButtonColors {
    override val primary: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketDarkTextColors.inverted,
        background = BlocketButtonElementColors(
            default = BlocketDarkBackgroundColors.primary,
            active = BlocketDarkBackgroundColors.primaryActive
        ),
        border = null
    )
    override val secondary: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketDarkTextColors.link,
        background = BlocketButtonElementColors(
            default = BlocketDarkBackgroundColors.default,
            active = BlocketDarkBackgroundColors.active
        ),
        border = BlocketButtonElementColors(
            default = BlocketDarkBorderColors.default,
            active = BlocketDarkBorderColors.active
        )
    )
    override val disabled: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketDarkTextColors.inverted,
        background = BlocketButtonElementColors(
            default = BlocketDarkBackgroundColors.disabled,
            active = Gray700
        ),
        border = null
    )
    override val quiet: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketDarkTextColors.link,
        background = BlocketButtonElementColors(
            default = Transparent,
            active = BlocketDarkBackgroundColors.active
        ),
        border = null
    )
    override val negative: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketDarkTextColors.inverted,
        background = BlocketButtonElementColors(
            default = BlocketDarkBackgroundColors.negative,
            active = BlocketDarkBackgroundColors.negativeActive
        ),
        border = null
    )
    override val negativeQuiet: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketDarkTextColors.negative,
        background = BlocketButtonElementColors(
            default = Transparent,
            active = BlocketDarkBackgroundColors.negativeSubtleActive
        ),
        border = null
    )
    override val utility: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketDarkTextColors.default,
        background = BlocketButtonElementColors(
            default = BlocketDarkBackgroundColors.default,
            active = BlocketDarkBackgroundColors.active
        ),
        border = BlocketButtonElementColors(
            default = BlocketDarkBorderColors.default,
            active = BlocketDarkBorderColors.active
        )
    )
    override val utilityOverlay: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = White,
        background = BlocketButtonElementColors(
            default = Black,
            active = Gray900
        ),
        border = null
    )
    override val utilityQuiet: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketDarkTextColors.default,
        background = BlocketButtonElementColors(
            default = Transparent,
            active = BlocketDarkBackgroundColors.subtleActive
        ),
        border = null
    )
    override val loading: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketDarkTextColors.subtle,
        background = BlocketButtonElementColors(
            default = Gray900,
            active = Gray700
        ),
        border = null
    )
}

internal object BlocketTabDarkColors : WarpTabColors {
    override val selectedContent: Color = BlocketDarkTextColors.link
    override val unselectedContent: Color = BlocketDarkTextColors.subtle
}

internal object BlocketTabRowDarkColors : WarpTabRowColors {
    override val container: Color = BlocketDarkBackgroundColors.default
    override val content: Color = BlocketDarkTextColors.link
    override val indicator: Color = BlocketDarkIconColors.selected
}

internal object BlocketTextFieldDarkColors : WarpTextFieldColors {
    override val textFilled: Color = BlocketDarkTextColors.default
    override val textHint: Color = BlocketDarkTextColors.placeholder
    override val textDisabled: Color = BlocketDarkTextColors.disabled
    override val textNegative: Color = BlocketDarkTextColors.negative
    override val textReadOnly: Color = BlocketDarkTextColors.default
    override val iconNegative: Color = BlocketDarkIconColors.negative
    override val background: Color = BlocketDarkBackgroundColors.default
    override val backgroundDisabled: Color = BlocketDarkBackgroundColors.disabled
    override val border: Color = BlocketDarkBorderColors.default
    override val borderActive: Color = BlocketDarkBorderColors.selected
    override val borderDisabled: Color = BlocketDarkBorderColors.disabled
    override val borderNegative: Color = BlocketDarkBorderColors.negative
    override val caret: Color = BlocketDarkIconColors.default
    override val icon: Color = BlocketDarkIconColors.default
}

internal object BlocketBoxDarkColors : WarpBoxColors {
    override val neutral = WarpComponentBackground(BlocketDarkBackgroundColors.subtle, null)
    override val info = WarpComponentBackground(BlocketDarkBackgroundColors.infoSubtle, null)
    override val bordered = WarpComponentBackground(BlocketDarkBackgroundColors.default, BlocketDarkBorderColors.default)
}

internal object BlocketStepIndicatorDarkColors : WarpStepIndicatorColors {
    override val background: Color = BlocketDarkBorderColors.default
    override val backgroundActive: Color = BlocketDarkBackgroundColors.primary
    override val backgroundTrack: Color = BlocketDarkBorderColors.default
    override val backgroundTrackActive: Color = BlocketDarkBackgroundColors.primary
    override val border: Color = BlocketDarkBorderColors.default
    override val borderActive: Color = BlocketDarkBorderColors.primary
    override val icon: Color = BlocketDarkIconColors.inverted
}

internal object BlocketAlertDarkColors : WarpAlertColors {
    override val info: WarpAlertStyleColors = BlocketAlertStyleColors(
        text = BlocketDarkTextColors.default,
        background = BlocketDarkBackgroundColors.infoSubtle,
        subtleBorder = BlocketDarkBorderColors.infoSubtle,
        border = BlocketDarkBorderColors.info,
        icon = BlocketDarkIconColors.info
    )
    override val positive: WarpAlertStyleColors = BlocketAlertStyleColors(
        text = BlocketDarkTextColors.default,
        background = BlocketDarkBackgroundColors.positiveSubtle,
        subtleBorder = BlocketDarkBorderColors.positiveSubtle,
        border = BlocketDarkBorderColors.positive,
        icon = BlocketDarkIconColors.positive
    )
    override val negative: WarpAlertStyleColors = BlocketAlertStyleColors(
        text = BlocketDarkTextColors.default,
        background = BlocketDarkBackgroundColors.negativeSubtle,
        subtleBorder = BlocketDarkBorderColors.negativeSubtle,
        border = BlocketDarkBorderColors.negative,
        icon = BlocketDarkIconColors.negative
    )
    override val warning: WarpAlertStyleColors = BlocketAlertStyleColors(
        text = BlocketDarkTextColors.default,
        background = BlocketDarkBackgroundColors.warningSubtle,
        subtleBorder = BlocketDarkBorderColors.warningSubtle,
        border = BlocketDarkBorderColors.warning,
        icon = BlocketDarkIconColors.warning
    )
}