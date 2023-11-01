package com.schibsted.nmp.warp.brands.dba

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpAlertColors
import com.schibsted.nmp.warp.theme.WarpAlertStyleColors
import com.schibsted.nmp.warp.theme.WarpBackgroundColors
import com.schibsted.nmp.warp.theme.WarpBadgeColors
import com.schibsted.nmp.warp.theme.WarpBorderColors
import com.schibsted.nmp.warp.theme.WarpBoxColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpButtonElementColors
import com.schibsted.nmp.warp.theme.WarpButtonStyleColors
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

internal object DbaColors : WarpColors {
    override val background: WarpBackgroundColors = DbaBackgroundColors
    override val border: WarpBorderColors = DbaBorderColors
    override val icon: WarpIconColors = DbaIconColors
    override val text: WarpTextColors = DbaTextColors
    override val components: WarpComponentColors = DbaComponentColors
}

internal object DbaBackgroundColors : WarpBackgroundColors {
    override val default = White
    override val active = Gray200
    override val disabled = Gray300
    override val subtle = Gray50
    override val subtleActive = Gray200
    override val selected = JeanBlue50

    override val inverted = Gray900

    override val primary = JeanBlue600
    override val primaryActive = JeanBlue800
    override val primarySubtle = JeanBlue50
    override val primarySubtleActive = JeanBlue200

    override val positive = Green600
    override val positiveActive = Green800
    override val positiveSubtle = Green50
    override val positiveSubtleActive = Green200
    override val positiveSelected = Green50

    override val negative = Red600
    override val negativeActive = Red800
    override val negativeSubtle = Red50
    override val negativeSubtleActive = Red200
    override val negativeSelected = Red50

    override val warning = Yellow600
    override val warningActive = Yellow800
    override val warningSubtle = Yellow50
    override val warningSubtleActive = Yellow200
    override val warningSelected = Yellow50

    override val info = JeanBlue600
    override val infoActive = JeanBlue800
    override val infoSubtle = JeanBlue50
    override val infoSubtleActive = JeanBlue200
    override val infoSelected = JeanBlue50

    override val notification = Red600
}

internal object DbaBorderColors : WarpBorderColors {
    override val default = Gray300
    override val active = Gray500
    override val disabled = Gray300
    override val selected = JeanBlue600

    override val primary = JeanBlue600
    override val primaryActive = JeanBlue800
    override val primarySubtle = JeanBlue300
    override val primarySubtleActive = JeanBlue500

    override val positive = Green600
    override val positiveActive = Green800
    override val positiveSubtle = Green300
    override val positiveSubtleActive = Green500

    override val negative = Red600
    override val negativeActive = Red800
    override val negativeSubtle = Red300
    override val negativeSubtleActive = Red500

    override val warning = Yellow600
    override val warningActive = Yellow800
    override val warningSubtle = Yellow300
    override val warningSubtleActive = Yellow500

    override val info = JeanBlue600
    override val infoActive = JeanBlue800
    override val infoSubtle = JeanBlue300
    override val infoSubtleActive = JeanBlue500
}

internal object DbaIconColors : WarpIconColors {
    override val default = Gray900
    override val active = Gray900
    override val selected = JeanBlue600
    override val disabled = Gray300
    override val subtle = Gray400
    override val subtleActive = Gray600
    override val inverted = White
    override val invertedActive = Gray200
    override val primary = JeanBlue600
    override val positive = Green600
    override val negative = Red600
    override val warning = Yellow600
    override val info = JeanBlue600
    override val notification = White
}

internal object DbaTextColors : WarpTextColors {
    override val default = Gray900
    override val subtle = Gray500
    override val placeholder = Gray300
    override val inverted = White
    override val invertedSubtle = Gray50
    override val link = JeanBlue600
    override val disabled = Gray300
    override val negative = Red600
    override val positive = Green600
    override val notification = White
}

internal object DbaComponentColors : WarpComponentColors {
    override val badge: WarpBadgeColors = DbaBadgeColors
    override val button: WarpButtonColors = DbaButtonColors
    override val box: WarpBoxColors = DbaBoxColors
    override val stepIndicator: WarpStepIndicatorColors = DbaStepIndicatorColors
    override val alert: WarpAlertColors = DbaAlertColors
    override val textField: WarpTextFieldColors = DbaTextFieldColors
    override val tabRow: WarpTabRowColors = DbaTabRowColors
    override val tab: WarpTabColors = DbaTabColors
    override val label: WarpLabelColors = DbaLabelColors
    override val callout: WarpCalloutColors = DbaCalloutColors
    override val pill: WarpPillColors = DbaPillColors
}

internal object DbaPillColors : WarpPillColors {
    override val filter: WarpPillStyleColors = DbaPillStyleColors(
        text = DbaTextColors.inverted,
        background = DbaBackgroundColors.primary,
        backgroundActive = DbaBackgroundColors.primaryActive,
        icon = DbaIconColors.inverted
    )
    override val suggestion: WarpPillStyleColors = DbaPillStyleColors(
        text = DbaTextColors.default,
        background = DbaBackgroundColors.subtle,
        backgroundActive = DbaBackgroundColors.subtleActive,
        icon = DbaIconColors.default
    )
}

internal object DbaCalloutColors : WarpCalloutColors {
    override val background: Color = Green100
    override val border: Color = Green400
    override val text: Color = DbaTextColors.default
}

internal object DbaBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = DbaBackgroundColors.infoSubtle
    override val infoText: Color = DbaTextColors.default
    override val positiveBackground: Color = DbaBackgroundColors.positiveSubtle
    override val positiveText: Color = DbaTextColors.default
    override val warningBackground: Color = DbaBackgroundColors.warningSubtle
    override val warningText: Color = DbaTextColors.default
    override val negativeBackground: Color = DbaBackgroundColors.negativeSubtle
    override val negativeText: Color = DbaTextColors.default
    override val disabledBackground: Color = DbaBackgroundColors.disabled
    override val disabledText: Color = DbaTextColors.default
    override val neutralBackground: Color = DbaBackgroundColors.subtle
    override val neutralText: Color = DbaTextColors.default
}

internal object DbaLabelColors : WarpLabelColors {
    override val text: Color = DbaTextColors.default
    override val negative: Color = DbaTextColors.negative
    override val optional: Color = DbaTextColors.default
    override val icon: Color = DbaIconColors.default
    override val disabled: Color = DbaTextColors.disabled
}

internal object DbaButtonColors : WarpButtonColors {
    override val primary: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaTextColors.inverted,
        background = DbaButtonElementColors(
            default = DbaBackgroundColors.primary,
            active = DbaBackgroundColors.primaryActive
        ),
        border = null
    )
    override val secondary: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaTextColors.link,
        background = DbaButtonElementColors(
            default = DbaBackgroundColors.default,
            active = DbaBackgroundColors.active
        ),
        border = DbaButtonElementColors(
            default = DbaBorderColors.default,
            active = DbaBorderColors.active
        )
    )
    override val disabled: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaTextColors.inverted,
        background = DbaButtonElementColors(
            default = DbaBackgroundColors.disabled,
            active = Gray300
        ),
        border = null
    )
    override val quiet: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaTextColors.link,
        background = DbaButtonElementColors(
            default = Transparent,
            active = DbaBackgroundColors.active
        ),
        border = null
    )
    override val negative: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaTextColors.inverted,
        background = DbaButtonElementColors(
            default = DbaBackgroundColors.negative,
            active = DbaBackgroundColors.negativeActive
        ),
        border = null
    )
    override val negativeQuiet: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaTextColors.negative,
        background = DbaButtonElementColors(
            default = Transparent,
            active = DbaBackgroundColors.negativeSubtleActive
        ),
        border = null
    )
    override val utility: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaTextColors.default,
        background = DbaButtonElementColors(
            default = DbaBackgroundColors.default,
            active = DbaBackgroundColors.active
        ),
        border = DbaButtonElementColors(
            default = DbaBorderColors.default,
            active = DbaBorderColors.active
        )
    )
    override val utilityOverlay: DbaButtonStyleColors = DbaButtonStyleColors(
        text = Gray900,
        background = DbaButtonElementColors(
            default = White,
            active = Gray200
        ),
        border = null
    )
    override val utilityQuiet: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaTextColors.default,
        background = DbaButtonElementColors(
            default = Transparent,
            active = DbaBackgroundColors.subtleActive
        ),
        border = null
    )
    override val loading: DbaButtonStyleColors = DbaButtonStyleColors(
        text = DbaTextColors.subtle,
        background = DbaButtonElementColors(
            default = Gray50,
            active = Gray200
        ),
        border = null
    )
}

internal object DbaTabColors : WarpTabColors {
    override val selectedContent: Color = DbaTextColors.link
    override val unselectedContent: Color = DbaTextColors.subtle
}

internal object DbaTabRowColors : WarpTabRowColors {
    override val container: Color = DbaBackgroundColors.default
    override val content: Color = DbaTextColors.link
    override val indicator: Color = DbaIconColors.selected
}

internal object DbaTextFieldColors : WarpTextFieldColors {
    override val textFilled: Color = DbaTextColors.default
    override val textHint: Color = DbaTextColors.placeholder
    override val textDisabled: Color = DbaTextColors.disabled
    override val textNegative: Color = DbaTextColors.negative
    override val textReadOnly: Color = DbaTextColors.default
    override val iconNegative: Color = DbaIconColors.negative
    override val background: Color = DbaBackgroundColors.default
    override val backgroundDisabled: Color = DbaBackgroundColors.disabled
    override val border: Color = DbaBorderColors.default
    override val borderActive: Color = DbaBorderColors.selected
    override val borderDisabled: Color = DbaBorderColors.disabled
    override val borderNegative: Color = DbaBorderColors.negative
    override val caret: Color = DbaIconColors.default
    override val icon: Color = DbaIconColors.default
}

internal object DbaBoxColors : WarpBoxColors {
    override val neutral = WarpComponentBackground(DbaBackgroundColors.subtle, null)
    override val info = WarpComponentBackground(DbaBackgroundColors.infoSubtle, null)
    override val bordered =
        WarpComponentBackground(DbaBackgroundColors.default, DbaBorderColors.default)
}

internal object DbaStepIndicatorColors : WarpStepIndicatorColors {
    override val background: Color = DbaBorderColors.default
    override val backgroundActive: Color = DbaBackgroundColors.primary
    override val backgroundTrack: Color = DbaBorderColors.default
    override val backgroundTrackActive: Color = DbaBackgroundColors.primary
    override val border: Color = DbaBorderColors.default
    override val borderActive: Color = DbaBorderColors.primary
    override val icon: Color = DbaIconColors.inverted
}

internal object DbaAlertColors : WarpAlertColors {
    override val info: WarpAlertStyleColors = DbaAlertStyleColors(
        text = DbaTextColors.default,
        background = DbaBackgroundColors.infoSubtle,
        subtleBorder = DbaBorderColors.infoSubtle,
        border = DbaBorderColors.info,
        icon = DbaIconColors.info
    )
    override val positive: WarpAlertStyleColors = DbaAlertStyleColors(
        text = DbaTextColors.default,
        background = DbaBackgroundColors.positiveSubtle,
        subtleBorder = DbaBorderColors.positiveSubtle,
        border = DbaBorderColors.positive,
        icon = DbaIconColors.positive
    )
    override val negative: WarpAlertStyleColors = DbaAlertStyleColors(
        text = DbaTextColors.default,
        background = DbaBackgroundColors.negativeSubtle,
        subtleBorder = DbaBorderColors.negativeSubtle,
        border = DbaBorderColors.negative,
        icon = DbaIconColors.negative
    )
    override val warning: WarpAlertStyleColors = DbaAlertStyleColors(
        text = DbaTextColors.default,
        background = DbaBackgroundColors.warningSubtle,
        subtleBorder = DbaBorderColors.warningSubtle,
        border = DbaBorderColors.warning,
        icon = DbaIconColors.warning
    )
}

internal data class DbaPillStyleColors(
    override val text: Color,
    override val background: Color,
    override val backgroundActive: Color,
    override val icon: Color
) : WarpPillStyleColors

internal data class DbaButtonStyleColors(
    override val text: Color,
    override val background: DbaButtonElementColors,
    override val border: DbaButtonElementColors?
) : WarpButtonStyleColors

internal data class DbaButtonElementColors(
    override val default: Color,
    override val active: Color
) : WarpButtonElementColors

internal data class DbaAlertStyleColors(
    override val text: Color,
    override val background: Color,
    override val subtleBorder: Color,
    override val border: Color,
    override val icon: Color
) : WarpAlertStyleColors

//Jean Blue
internal val JeanBlue50 = Color(0xFFF3F6FC)
internal val JeanBlue100 = Color(0xFFD2E1F5)
internal val JeanBlue200 = Color(0xFFB2C9EC)
internal val JeanBlue300 = Color(0xFF92B1E3)
internal val JeanBlue400 = Color(0xFF7299DA)
internal val JeanBlue500 = Color(0xFF5281D1)
internal val JeanBlue600 = Color(0xFF3269C8)
internal val JeanBlue700 = Color(0xFF244B8F)
internal val JeanBlue800 = Color(0xFF162D56)
internal val JeanBlue900 = Color(0xFF070F1D)

//Phthalo Blue
internal val PhthaloBlue50 = Color(0xFFF0F1FF)
internal val PhthaloBlue100 = Color(0xFFC8CCEE)
internal val PhthaloBlue200 = Color(0xFFA0A5DA)
internal val PhthaloBlue300 = Color(0xFF787EC6)
internal val PhthaloBlue400 = Color(0xFF5057B2)
internal val PhthaloBlue500 = Color(0xFF28309E)
internal val PhthaloBlue600 = Color(0xFF00098A)
internal val PhthaloBlue700 = Color(0xFF000768)
internal val PhthaloBlue800 = Color(0xFF000546)
internal val PhthaloBlue900 = Color(0xFF000324)

//Green
internal val Green50 = Color(0xFFF3FCF9)
internal val Green100 = Color(0xFFCEE8DC)
internal val Green200 = Color(0xFFAAD6C4)
internal val Green300 = Color(0xFF86C4AC)
internal val Green400 = Color(0xFF62B294)
internal val Green500 = Color(0xFF3EA07C)
internal val Green600 = Color(0xFF1A8F64)
internal val Green700 = Color(0xFF136647)
internal val Green800 = Color(0xFF0C3E2A)
internal val Green900 = Color(0xFF072719)

//Yellow
internal val Yellow50 = Color(0xFFFEF7F1)
internal val Yellow100 = Color(0xFFFDE8D5)
internal val Yellow200 = Color(0xFFFBD6B4)
internal val Yellow300 = Color(0xFFF9C493)
internal val Yellow400 = Color(0xFFF7B272)
internal val Yellow500 = Color(0xFFF5A051)
internal val Yellow600 = Color(0xFFF38E30)
internal val Yellow700 = Color(0xFFAD6421)
internal val Yellow800 = Color(0xFF673A12)
internal val Yellow900 = Color(0xFF221102)

//Red
internal val Red50 = Color(0xFFFFF0F1)
internal val Red100 = Color(0xFFF7CACC)
internal val Red200 = Color(0xFFEFA3A6)
internal val Red300 = Color(0xFFE77C80)
internal val Red400 = Color(0xFFDF555A)
internal val Red500 = Color(0xFFD72E34)
internal val Red600 = Color(0xFFCF070E)
internal val Red700 = Color(0xFF96050A)
internal val Red800 = Color(0xFF5D0306)
internal val Red900 = Color(0xFF230103)

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