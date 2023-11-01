package com.schibsted.nmp.warp.brands.tori

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

internal object ToriDarkColors : WarpColors {
    override val background: WarpBackgroundColors = ToriDarkBackgroundColors
    override val border: WarpBorderColors = ToriDarkBorderColors
    override val icon: WarpIconColors = ToriDarkIconColors
    override val text: WarpTextColors = ToriDarkTextColors
    override val components: WarpComponentColors = ToriComponentDarkColors
}

internal object ToriDarkBackgroundColors : WarpBackgroundColors {
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

    override val info = Blue500
    override val infoActive = Blue700
    override val infoSubtle = Blue900
    override val infoSubtleActive = Blue900
    override val infoSelected = Blue500

    override val notification = Red500
}

internal object ToriDarkBorderColors : WarpBorderColors {
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

    override val info = Blue500
    override val infoActive = Blue700
    override val infoSubtle = Blue700
    override val infoSubtleActive = Blue900
}

internal object ToriDarkIconColors : WarpIconColors {
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
    override val info = Blue500
    override val notification = White
}

internal object ToriDarkTextColors : WarpTextColors {
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
    override val box: WarpBoxColors = ToriBoxDarkColors
    override val stepIndicator: WarpStepIndicatorColors = ToriStepIndicatorDarkColors
    override val alert: WarpAlertColors = ToriAlertDarkColors
    override val textField: WarpTextFieldColors = ToriTextFieldDarkColors
    override val tab: WarpTabColors = ToriTabDarkColors
    override val tabRow: WarpTabRowColors = ToriTabRowDarkColors
    override val badge: WarpBadgeColors = ToriBadgeDarkColors
    override val label: WarpLabelColors = ToriLabelDarkColors
    override val callout: WarpCalloutColors = ToriCalloutDarkColors
    override val pill: WarpPillColors = ToriPillDarkColors
}

internal object ToriPillDarkColors : WarpPillColors {
    override val filter: WarpPillStyleColors = ToriPillStyleColors(
        text = ToriDarkTextColors.inverted,
        background = ToriDarkBackgroundColors.primary,
        backgroundActive = ToriDarkBackgroundColors.primaryActive,
        icon = ToriDarkIconColors.inverted
    )
    override val suggestion: WarpPillStyleColors = ToriPillStyleColors(
        text = ToriDarkTextColors.default,
        background = ToriDarkBackgroundColors.subtle,
        backgroundActive = ToriDarkBackgroundColors.subtleActive,
        icon = ToriDarkIconColors.default
    )
}

internal object ToriCalloutDarkColors : WarpCalloutColors {
    override val background: Color = Green800
    override val border: Color = Green600
    override val text: Color = ToriDarkTextColors.default
}

internal object ToriBadgeDarkColors : WarpBadgeColors {
    override val infoBackground: Color = ToriDarkBackgroundColors.infoSubtle
    override val infoText: Color = ToriDarkTextColors.default
    override val positiveBackground: Color = ToriDarkBackgroundColors.positiveSubtle
    override val positiveText: Color = ToriDarkTextColors.default
    override val warningBackground: Color = ToriDarkBackgroundColors.warningSubtle
    override val warningText: Color = ToriDarkTextColors.default
    override val negativeBackground: Color = ToriDarkBackgroundColors.negativeSubtle
    override val negativeText: Color = ToriDarkTextColors.default
    override val disabledBackground: Color = ToriDarkBackgroundColors.disabled
    override val disabledText: Color = ToriDarkTextColors.default
    override val neutralBackground: Color = ToriDarkBackgroundColors.subtle
    override val neutralText: Color = ToriDarkTextColors.default
}

internal object ToriLabelDarkColors: WarpLabelColors {
    override val text: Color = ToriDarkTextColors.default
    override val negative: Color = ToriDarkTextColors.negative
    override val optional: Color = ToriDarkTextColors.default
    override val icon: Color = ToriDarkIconColors.default
    override val disabled: Color = ToriDarkTextColors.disabled
}

internal object ToriButtonDarkColors : WarpButtonColors {
    override val primary: ToriButtonStyleColors = ToriButtonStyleColors(
        text = ToriDarkTextColors.inverted,
        background = ToriButtonElementColors(
            default = ToriDarkBackgroundColors.primary,
            active = ToriDarkBackgroundColors.primaryActive
        ),
        border = null
    )
    override val secondary: ToriButtonStyleColors = ToriButtonStyleColors(
        text = ToriDarkTextColors.link,
        background = ToriButtonElementColors(
            default = ToriDarkBackgroundColors.default,
            active = ToriDarkBackgroundColors.active
        ),
        border = ToriButtonElementColors(
            default = ToriDarkBorderColors.default,
            active = ToriDarkBorderColors.active
        )
    )
    override val disabled: ToriButtonStyleColors = ToriButtonStyleColors(
        text = ToriDarkTextColors.inverted,
        background = ToriButtonElementColors(
            default = ToriDarkBackgroundColors.disabled,
            active = Gray700
        ),
        border = null
    )
    override val quiet: ToriButtonStyleColors = ToriButtonStyleColors(
        text = ToriDarkTextColors.link,
        background = ToriButtonElementColors(
            default = Transparent,
            active = ToriDarkBackgroundColors.active
        ),
        border = null
    )
    override val negative: ToriButtonStyleColors = ToriButtonStyleColors(
        text = ToriDarkTextColors.inverted,
        background = ToriButtonElementColors(
            default = ToriDarkBackgroundColors.negative,
            active = ToriDarkBackgroundColors.negativeActive
        ),
        border = null
    )
    override val negativeQuiet: ToriButtonStyleColors = ToriButtonStyleColors(
        text = ToriDarkTextColors.negative,
        background = ToriButtonElementColors(
            default = Transparent,
            active = ToriDarkBackgroundColors.negativeSubtleActive
        ),
        border = null
    )
    override val utility: ToriButtonStyleColors = ToriButtonStyleColors(
        text = ToriDarkTextColors.default,
        background = ToriButtonElementColors(
            default = ToriDarkBackgroundColors.default,
            active = ToriDarkBackgroundColors.active
        ),
        border = ToriButtonElementColors(
            default = ToriDarkBorderColors.default,
            active = ToriDarkBorderColors.active
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
        text = ToriDarkTextColors.default,
        background = ToriButtonElementColors(
            default = Transparent,
            active = ToriDarkBackgroundColors.subtleActive
        ),
        border = null
    )
    override val loading: ToriButtonStyleColors = ToriButtonStyleColors(
        text = ToriDarkTextColors.subtle,
        background = ToriButtonElementColors(
            default = Gray900,
            active = Gray700
        ),
        border = null
    )
}

internal object ToriTabDarkColors : WarpTabColors {
    override val selectedContent: Color = ToriDarkTextColors.link
    override val unselectedContent: Color = ToriDarkTextColors.subtle
}

internal object ToriTabRowDarkColors : WarpTabRowColors {
    override val container: Color = ToriDarkBackgroundColors.default
    override val content: Color = ToriDarkTextColors.link
    override val indicator: Color = ToriDarkIconColors.selected
}

internal object ToriTextFieldDarkColors : WarpTextFieldColors {
    override val textFilled: Color = ToriDarkTextColors.default
    override val textHint: Color = ToriDarkTextColors.placeholder
    override val textDisabled: Color = ToriDarkTextColors.disabled
    override val textNegative: Color = ToriDarkTextColors.negative
    override val textReadOnly: Color = ToriDarkTextColors.default
    override val iconNegative: Color = ToriDarkIconColors.negative
    override val background: Color = ToriDarkBackgroundColors.default
    override val backgroundDisabled: Color = ToriDarkBackgroundColors.disabled
    override val border: Color = ToriDarkBorderColors.default
    override val borderActive: Color = ToriDarkBorderColors.selected
    override val borderDisabled: Color = ToriDarkBorderColors.disabled
    override val borderNegative: Color = ToriDarkBorderColors.negative
    override val caret: Color = ToriDarkIconColors.default
    override val icon: Color = ToriDarkIconColors.default
}

internal object ToriBoxDarkColors : WarpBoxColors {
    override val neutral = WarpComponentBackground(ToriDarkBackgroundColors.subtle, null)
    override val info = WarpComponentBackground(ToriDarkBackgroundColors.infoSubtle, null)
    override val bordered = WarpComponentBackground(ToriDarkBackgroundColors.default, ToriDarkBorderColors.default)
}

internal object ToriStepIndicatorDarkColors : WarpStepIndicatorColors {
    override val background: Color = ToriDarkBorderColors.default
    override val backgroundActive: Color = ToriDarkBackgroundColors.primary
    override val backgroundTrack: Color = ToriDarkBorderColors.default
    override val backgroundTrackActive: Color = ToriDarkBackgroundColors.primary
    override val border: Color = ToriDarkBorderColors.default
    override val borderActive: Color = ToriDarkBorderColors.primary
    override val icon: Color = ToriDarkIconColors.inverted
}

internal object ToriAlertDarkColors : WarpAlertColors {
    override val info: WarpAlertStyleColors = ToriAlertStyleColors(
        text = ToriDarkTextColors.default,
        background = ToriDarkBackgroundColors.infoSubtle,
        subtleBorder = ToriDarkBorderColors.infoSubtle,
        border = ToriDarkBorderColors.info,
        icon = ToriDarkIconColors.info
    )
    override val positive: WarpAlertStyleColors = ToriAlertStyleColors(
        text = ToriDarkTextColors.default,
        background = ToriDarkBackgroundColors.positiveSubtle,
        subtleBorder = ToriDarkBorderColors.positiveSubtle,
        border = ToriDarkBorderColors.positive,
        icon = ToriDarkIconColors.positive
    )
    override val negative: WarpAlertStyleColors = ToriAlertStyleColors(
        text = ToriDarkTextColors.default,
        background = ToriDarkBackgroundColors.negativeSubtle,
        subtleBorder = ToriDarkBorderColors.negativeSubtle,
        border = ToriDarkBorderColors.negative,
        icon = ToriDarkIconColors.negative
    )
    override val warning: WarpAlertStyleColors = ToriAlertStyleColors(
        text = ToriDarkTextColors.default,
        background = ToriDarkBackgroundColors.warningSubtle,
        subtleBorder = ToriDarkBorderColors.warningSubtle,
        border = ToriDarkBorderColors.warning,
        icon = ToriDarkIconColors.warning
    )
}
