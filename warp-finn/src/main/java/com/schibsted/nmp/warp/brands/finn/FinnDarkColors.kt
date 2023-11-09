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
import com.schibsted.nmp.warp.theme.WarpPillColors
import com.schibsted.nmp.warp.theme.WarpPillStyleColors
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

    override val secondary = Aqua400
    override val secondaryActive = Aqua600

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
    override val secondary = Aqua400
    override val secondaryActive = Aqua600
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
    override val pill: WarpPillColors = FinnPillDarkColors
}

internal object FinnPillDarkColors : WarpPillColors {
    override val filter: WarpPillStyleColors = FinnPillStyleColors(
        text = FinnDarkTextColors.inverted,
        background = FinnDarkBackgroundColors.primary,
        backgroundActive = FinnDarkBackgroundColors.primaryActive,
        icon = FinnDarkIconColors.inverted
    )
    override val suggestion: WarpPillStyleColors = FinnPillStyleColors(
        text = FinnDarkTextColors.default,
        background = FinnDarkBackgroundColors.subtle,
        backgroundActive = FinnDarkBackgroundColors.subtleActive,
        icon = FinnDarkIconColors.default
    )
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
    override val content: Color = FinnDarkTextColors.link
    override val indicator: Color = FinnDarkIconColors.selected
}

internal object FinnStepIndicatorDarkColors : WarpStepIndicatorColors {
    override val background: Color = FinnDarkBorderColors.default
    override val backgroundActive: Color = FinnDarkBackgroundColors.primary
    override val backgroundTrack: Color = FinnDarkBorderColors.default
    override val backgroundTrackActive: Color = FinnDarkBackgroundColors.primary
    override val border: Color = FinnDarkBorderColors.default
    override val borderActive: Color = FinnDarkBorderColors.primary
    override val icon: Color = FinnDarkIconColors.inverted
}

internal object FinnAlertDarkColors : WarpAlertColors {
    override val info: WarpAlertStyleColors = FinnAlertStyleColors(
        text = FinnDarkTextColors.default,
        background = FinnDarkBackgroundColors.infoSubtle,
        subtleBorder = FinnDarkBorderColors.infoSubtle,
        border = FinnDarkBorderColors.info,
        icon = FinnDarkIconColors.info
    )
    override val positive: WarpAlertStyleColors = FinnAlertStyleColors(
        text = FinnDarkTextColors.default,
        background = FinnDarkBackgroundColors.positiveSubtle,
        subtleBorder = FinnDarkBorderColors.positiveSubtle,
        border = FinnDarkBorderColors.positive,
        icon = FinnDarkIconColors.positive
    )
    override val negative: WarpAlertStyleColors = FinnAlertStyleColors(
        text = FinnDarkTextColors.default,
        background = FinnDarkBackgroundColors.negativeSubtle,
        subtleBorder = FinnDarkBorderColors.negativeSubtle,
        border = FinnDarkBorderColors.negative,
        icon = FinnDarkIconColors.negative
    )
    override val warning: WarpAlertStyleColors = FinnAlertStyleColors(
        text = FinnDarkTextColors.default,
        background = FinnDarkBackgroundColors.warningSubtle,
        subtleBorder = FinnDarkBorderColors.warningSubtle,
        border = FinnDarkBorderColors.warning,
        icon = FinnDarkIconColors.warning
    )
}

internal object FinnBoxDarkColors : WarpBoxColors {
    override val neutral = WarpComponentBackground(FinnDarkBackgroundColors.subtle, null)
    override val info = WarpComponentBackground(FinnDarkBackgroundColors.infoSubtle, null)
    override val bordered = WarpComponentBackground(FinnDarkBackgroundColors.default, FinnDarkBorderColors.default)
}

internal object FinnLabelDarkColors: WarpLabelColors {
    override val text: Color = FinnDarkTextColors.default
    override val negative: Color = FinnDarkTextColors.negative
    override val optional: Color = FinnDarkTextColors.default
    override val icon: Color = FinnDarkIconColors.default
    override val disabled: Color = FinnDarkTextColors.disabled
}

internal object FinnTextFieldDarkColors : WarpTextFieldColors {
    override val textFilled: Color = FinnDarkTextColors.default
    override val textHint: Color = FinnDarkTextColors.placeholder
    override val textDisabled: Color = FinnDarkTextColors.disabled
    override val textNegative: Color = FinnDarkTextColors.negative
    override val textReadOnly: Color = FinnDarkTextColors.default
    override val iconNegative: Color = FinnDarkIconColors.negative
    override val background: Color = FinnDarkBackgroundColors.default
    override val backgroundDisabled: Color = FinnDarkBackgroundColors.disabled
    override val border: Color = FinnDarkBorderColors.default
    override val borderActive: Color = FinnDarkBorderColors.selected
    override val borderDisabled: Color = FinnDarkBorderColors.disabled
    override val borderNegative: Color = FinnDarkBorderColors.negative
    override val caret: Color = FinnDarkIconColors.default
    override val icon: Color = FinnDarkIconColors.default
}

internal object FinnButtonDarkColors : WarpButtonColors {
    override val primary: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnDarkTextColors.inverted,
        background = FinnButtonElementColors(
            default = FinnDarkBackgroundColors.primary,
            active = FinnDarkBackgroundColors.primaryActive
        ),
        border = null
    )
    override val secondary: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnDarkTextColors.link,
        background = FinnButtonElementColors(
            default = FinnDarkBackgroundColors.default,
            active = FinnDarkBackgroundColors.active
        ),
        border = FinnButtonElementColors(
            default = FinnDarkBorderColors.default,
            active = FinnDarkBorderColors.active
        )
    )
    override val disabled: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnDarkTextColors.inverted,
        background = FinnButtonElementColors(
            default = FinnDarkBackgroundColors.disabled,
            active = Bluegray700
        ),
        border = null
    )
    override val quiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnDarkTextColors.link,
        background = FinnButtonElementColors(
            default = Transparent,
            active = FinnDarkBackgroundColors.active
        ),
        border = null
    )
    override val negative: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnDarkTextColors.inverted,
        background = FinnButtonElementColors(
            default = FinnDarkBackgroundColors.negative,
            active = FinnDarkBackgroundColors.negativeActive
        ),
        border = null
    )
    override val negativeQuiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnDarkTextColors.negative,
        background = FinnButtonElementColors(
            default = Transparent,
            active = FinnDarkBackgroundColors.negativeSubtleActive
        ),
        border = null
    )
    override val utility: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnDarkTextColors.default,
        background = FinnButtonElementColors(
            default = FinnDarkBackgroundColors.default,
            active = FinnDarkBackgroundColors.active
        ),
        border = FinnButtonElementColors(
            default = FinnDarkBorderColors.default,
            active = FinnDarkBorderColors.active
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
        text = FinnDarkTextColors.default,
        background = FinnButtonElementColors(
            default = Transparent,
            active = FinnDarkBackgroundColors.subtleActive
        ),
        border = null
    )
    override val loading: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnDarkTextColors.subtle,
        background = FinnButtonElementColors(
            default = Bluegray900,
            active = Bluegray700
        ),
        border = null
    )
}
