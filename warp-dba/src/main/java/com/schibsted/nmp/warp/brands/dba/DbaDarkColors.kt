package com.schibsted.nmp.warp.brands.dba

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

internal object DbaDarkColors : WarpColors {
    override val background: WarpBackgroundColors = DbaDarkBackgroundColors
    override val border: WarpBorderColors = DbaDarkBorderColors
    override val icon: WarpIconColors = DbaDarkIconColors
    override val text: WarpTextColors = DbaDarkTextColors
    override val components: WarpComponentColors = DbaComponentDarkColors
}

internal object DbaDarkBackgroundColors : WarpBackgroundColors {
    override val default = Black
    override val active = Gray900
    override val disabled = Gray700
    override val subtle = Gray900
    override val subtleActive = Gray900
    override val selected = JeanBlue900

    override val inverted = Gray50

    override val primary = JeanBlue500
    override val primaryActive = JeanBlue800
    override val primarySubtle = JeanBlue900
    override val primarySubtleActive = JeanBlue900

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

    override val info = JeanBlue500
    override val infoActive = JeanBlue700
    override val infoSubtle = JeanBlue900
    override val infoSubtleActive = JeanBlue900
    override val infoSelected = JeanBlue500

    override val notification = Red500
}

internal object DbaDarkBorderColors : WarpBorderColors {
    override val default = Gray600
    override val active = Gray800
    override val disabled = Gray300
    override val selected = JeanBlue500

    override val primary = JeanBlue500
    override val primaryActive = JeanBlue700
    override val primarySubtle = JeanBlue700
    override val primarySubtleActive = JeanBlue900

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

    override val info = JeanBlue500
    override val infoActive = JeanBlue700
    override val infoSubtle = JeanBlue700
    override val infoSubtleActive = JeanBlue900
}

internal object DbaDarkIconColors : WarpIconColors {
    override val default = White
    override val active = Gray200
    override val selected = JeanBlue500
    override val disabled = Gray600
    override val subtle = Gray100
    override val subtleActive = Gray300
    override val inverted = Black
    override val invertedActive = Gray900
    override val primary = JeanBlue500
    override val positive = Green500
    override val negative = Red500
    override val warning = Yellow500
    override val info = JeanBlue500
    override val notification = White
}

internal object DbaDarkTextColors : WarpTextColors {
    override val default = White
    override val subtle = Gray100
    override val placeholder = Gray500
    override val inverted = White
    override val invertedSubtle = Gray800
    override val link = JeanBlue500
    override val disabled = Gray500
    override val negative = Red500
    override val positive = Green500
    override val notification = White
}

internal object DbaComponentDarkColors : WarpComponentColors {
    override val button: WarpButtonColors = DbaButtonDarkColors
    override val box: WarpBoxColors = DbaBoxDarkColors
    override val stepIndicator: WarpStepIndicatorColors = DbaStepIndicatorDarkColors
    override val alert: WarpAlertColors = DbaAlertDarkColors
    override val textField: WarpTextFieldColors = DbaTextFieldDarkColors
    override val tab: WarpTabColors = DbaTabDarkColors
    override val tabRow: WarpTabRowColors = DbaTabRowDarkColors
    override val badge: WarpBadgeColors = DbaBadgeDarkColors
    override val label: WarpLabelColors = DbaLabelDarkColors
    override val callout: WarpCalloutColors = DbaCalloutDarkColors
    override val pill: WarpPillColors = DbaPillDarkColors
}

internal object DbaPillDarkColors : WarpPillColors {
    override val filter: WarpPillStyleColors = DbaPillStyleColors(
        text = DbaDarkTextColors.inverted,
        background = DbaDarkBackgroundColors.primary,
        backgroundActive = DbaDarkBackgroundColors.primaryActive,
        icon = DbaDarkIconColors.inverted
    )
    override val suggestion: WarpPillStyleColors = DbaPillStyleColors(
        text = DbaDarkTextColors.default,
        background = DbaDarkBackgroundColors.subtle,
        backgroundActive = DbaDarkBackgroundColors.subtleActive,
        icon = DbaDarkIconColors.default
    )
}

internal object DbaCalloutDarkColors : WarpCalloutColors {
    override val background: Color = Green800
    override val border: Color = Green600
    override val text: Color = DbaDarkTextColors.default
}

internal object DbaBadgeDarkColors : WarpBadgeColors {
    override val infoBackground: Color = DbaDarkBackgroundColors.infoSubtle
    override val infoText: Color = DbaDarkTextColors.default
    override val positiveBackground: Color = DbaDarkBackgroundColors.positiveSubtle
    override val positiveText: Color = DbaDarkTextColors.default
    override val warningBackground: Color = DbaDarkBackgroundColors.warningSubtle
    override val warningText: Color = DbaDarkTextColors.default
    override val negativeBackground: Color = DbaDarkBackgroundColors.negativeSubtle
    override val negativeText: Color = DbaDarkTextColors.default
    override val disabledBackground: Color = DbaDarkBackgroundColors.disabled
    override val disabledText: Color = DbaDarkTextColors.default
    override val neutralBackground: Color = DbaDarkBackgroundColors.subtle
    override val neutralText: Color = DbaDarkTextColors.default
}

internal object DbaLabelDarkColors: WarpLabelColors {
    override val text: Color = DbaDarkTextColors.default
    override val negative: Color = DbaDarkTextColors.negative
    override val optional: Color = DbaDarkTextColors.default
    override val icon: Color = DbaDarkIconColors.default
    override val disabled: Color = DbaDarkTextColors.disabled
}

internal object DbaButtonDarkColors : WarpButtonColors {
    override val primary: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaDarkTextColors.inverted,
        background = DbaButtonElementColors(
            default = DbaDarkBackgroundColors.primary,
            active = DbaDarkBackgroundColors.primaryActive
        ),
        border = null
    )
    override val secondary: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaDarkTextColors.link,
        background = DbaButtonElementColors(
            default = DbaDarkBackgroundColors.default,
            active = DbaDarkBackgroundColors.active
        ),
        border = DbaButtonElementColors(
            default = DbaDarkBorderColors.default,
            active = DbaDarkBorderColors.active
        )
    )
    override val disabled: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaDarkTextColors.inverted,
        background = DbaButtonElementColors(
            default = DbaDarkBackgroundColors.disabled,
            active = Gray700
        ),
        border = null
    )
    override val quiet: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaDarkTextColors.link,
        background = DbaButtonElementColors(
            default = Transparent,
            active = DbaDarkBackgroundColors.active
        ),
        border = null
    )
    override val negative: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaDarkTextColors.inverted,
        background = DbaButtonElementColors(
            default = DbaDarkBackgroundColors.negative,
            active = DbaDarkBackgroundColors.negativeActive
        ),
        border = null
    )
    override val negativeQuiet: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaDarkTextColors.negative,
        background = DbaButtonElementColors(
            default = Transparent,
            active = DbaDarkBackgroundColors.negativeSubtleActive
        ),
        border = null
    )
    override val utility: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaDarkTextColors.default,
        background = DbaButtonElementColors(
            default = DbaDarkBackgroundColors.default,
            active = DbaDarkBackgroundColors.active
        ),
        border = DbaButtonElementColors(
            default = DbaDarkBorderColors.default,
            active = DbaDarkBorderColors.active
        )
    )
    override val utilityOverlay: DbaButtonStyleColors = DbaButtonStyleColors(
        text = White,
        background = DbaButtonElementColors(
            default = Black,
            active = Gray900
        ),
        border = null
    )
    override val utilityQuiet: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaDarkTextColors.default,
        background = DbaButtonElementColors(
            default = Transparent,
            active = DbaDarkBackgroundColors.subtleActive
        ),
        border = null
    )
    override val loading: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaDarkTextColors.subtle,
        background = DbaButtonElementColors(
            default = Gray900,
            active = Gray700
        ),
        border = null
    )
}

internal object DbaTabDarkColors : WarpTabColors {
    override val selectedContent: Color = DbaDarkTextColors.link
    override val unselectedContent: Color = DbaDarkTextColors.subtle
}

internal object DbaTabRowDarkColors : WarpTabRowColors {
    override val container: Color = DbaDarkBackgroundColors.default
    override val content: Color = DbaDarkTextColors.link
    override val indicator: Color = DbaDarkIconColors.selected
}

internal object DbaTextFieldDarkColors : WarpTextFieldColors {
    override val textFilled: Color = DbaDarkTextColors.default
    override val textHint: Color = DbaDarkTextColors.placeholder
    override val textDisabled: Color = DbaDarkTextColors.disabled
    override val textNegative: Color = DbaDarkTextColors.negative
    override val textReadOnly: Color = DbaDarkTextColors.default
    override val iconNegative: Color = DbaDarkIconColors.negative
    override val background: Color = DbaDarkBackgroundColors.default
    override val backgroundDisabled: Color = DbaDarkBackgroundColors.disabled
    override val border: Color = DbaDarkBorderColors.default
    override val borderActive: Color = DbaDarkBorderColors.selected
    override val borderDisabled: Color = DbaDarkBorderColors.disabled
    override val borderNegative: Color = DbaDarkBorderColors.negative
    override val caret: Color = DbaDarkIconColors.default
    override val icon: Color = DbaDarkIconColors.default
}

internal object DbaBoxDarkColors : WarpBoxColors {
    override val neutral = WarpComponentBackground(DbaDarkBackgroundColors.subtle, null)
    override val info = WarpComponentBackground(DbaDarkBackgroundColors.infoSubtle, null)
    override val bordered = WarpComponentBackground(DbaDarkBackgroundColors.default, DbaDarkBorderColors.default)
}

internal object DbaStepIndicatorDarkColors : WarpStepIndicatorColors {
    override val background: Color = DbaDarkBorderColors.default
    override val backgroundActive: Color = DbaDarkBackgroundColors.primary
    override val backgroundTrack: Color = DbaDarkBorderColors.default
    override val backgroundTrackActive: Color = DbaDarkBackgroundColors.primary
    override val border: Color = DbaDarkBorderColors.default
    override val borderActive: Color = DbaDarkBorderColors.primary
    override val icon: Color = DbaDarkIconColors.inverted
}

internal object DbaAlertDarkColors : WarpAlertColors {
    override val info: WarpAlertStyleColors = DbaAlertStyleColors(
        text = DbaDarkTextColors.default,
        background = DbaDarkBackgroundColors.infoSubtle,
        subtleBorder = DbaDarkBorderColors.infoSubtle,
        border = DbaDarkBorderColors.info,
        icon = DbaDarkIconColors.info
    )
    override val positive: WarpAlertStyleColors = DbaAlertStyleColors(
        text = DbaDarkTextColors.default,
        background = DbaDarkBackgroundColors.positiveSubtle,
        subtleBorder = DbaDarkBorderColors.positiveSubtle,
        border = DbaDarkBorderColors.positive,
        icon = DbaDarkIconColors.positive
    )
    override val negative: WarpAlertStyleColors = DbaAlertStyleColors(
        text = DbaDarkTextColors.default,
        background = DbaDarkBackgroundColors.negativeSubtle,
        subtleBorder = DbaDarkBorderColors.negativeSubtle,
        border = DbaDarkBorderColors.negative,
        icon = DbaDarkIconColors.negative
    )
    override val warning: WarpAlertStyleColors = DbaAlertStyleColors(
        text = DbaDarkTextColors.default,
        background = DbaDarkBackgroundColors.warningSubtle,
        subtleBorder = DbaDarkBorderColors.warningSubtle,
        border = DbaDarkBorderColors.warning,
        icon = DbaDarkIconColors.warning
    )
}