package com.schibsted.nmp.warp.brands.blocket

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

internal object BlocketColors : WarpColors {
    override val background: WarpBackgroundColors = BlocketBackgroundColors
    override val border: WarpBorderColors = BlocketBorderColors
    override val icon: WarpIconColors = BlocketIconColors
    override val text: WarpTextColors = BlocketTextColors
    override val components: WarpComponentColors = BlocketComponentColors
}

internal object BlocketBackgroundColors : WarpBackgroundColors {
    override val default = White
    override val active = Gray200
    override val disabled = Gray300
    override val subtle = Gray50
    override val subtleActive = Gray200
    override val selected = Red50

    override val inverted = Gray900

    override val primary = Red600
    override val primaryActive = Red800
    override val primarySubtle = Red50
    override val primarySubtleActive = Red200

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

    override val info = Blue600
    override val infoActive = Blue800
    override val infoSubtle = Blue50
    override val infoSubtleActive = Blue200
    override val infoSelected = Blue50

    override val notification = Red600
}

internal object BlocketBorderColors : WarpBorderColors {
    override val default = Gray300
    override val active = Gray500
    override val disabled = Gray300
    override val selected = Red600

    override val primary = Red600
    override val primaryActive = Red800
    override val primarySubtle = Red300
    override val primarySubtleActive = Red500

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

    override val info = Blue600
    override val infoActive = Blue800
    override val infoSubtle = Blue300
    override val infoSubtleActive = Blue500
}

internal object BlocketIconColors : WarpIconColors {
    override val default = Gray900
    override val active = Gray900
    override val selected = Red600
    override val disabled = Gray300
    override val subtle = Gray400
    override val subtleActive = Gray600
    override val inverted = White
    override val invertedActive = Gray200
    override val primary = Red600
    override val positive = Green600
    override val negative = Red600
    override val warning = Yellow600
    override val info = Blue600
    override val notification = White
}

internal object BlocketTextColors : WarpTextColors {
    override val default = Gray900
    override val subtle = Gray500
    override val placeholder = Gray300
    override val inverted = White
    override val invertedSubtle = Gray50
    override val link = Red600
    override val disabled = Gray300
    override val negative = Red600
    override val positive = Green600
    override val notification = White
}

internal object BlocketComponentColors : WarpComponentColors {
    override val badge: WarpBadgeColors = BlocketBadgeColors
    override val button: WarpButtonColors = BlocketButtonColors
    override val box: WarpBoxColors = BlocketBoxColors
    override val stepIndicator: WarpStepIndicatorColors = BlocketStepIndicatorColors
    override val alert: WarpAlertColors = BlocketAlertColors
    override val textField: WarpTextFieldColors = BlocketTextFieldColors
    override val tabRow: WarpTabRowColors = BlocketTabRowColors
    override val tab: WarpTabColors = BlocketTabColors
    override val label: WarpLabelColors = BlocketLabelColors
    override val callout: WarpCalloutColors = BlocketCalloutColors
    override val pill: WarpPillColors = BlocketPillColors
}

internal object BlocketPillColors : WarpPillColors {
    override val filter: WarpPillStyleColors = BlocketPillStyleColors(
        text = BlocketTextColors.inverted,
        background = BlocketBackgroundColors.primary,
        backgroundActive = BlocketBackgroundColors.primaryActive,
        icon = BlocketIconColors.inverted
    )
    override val suggestion: WarpPillStyleColors = BlocketPillStyleColors(
        text = BlocketTextColors.default,
        background = BlocketBackgroundColors.subtle,
        backgroundActive = BlocketBackgroundColors.subtleActive,
        icon = BlocketIconColors.default
    )
}

internal object BlocketCalloutColors : WarpCalloutColors {
    override val background: Color = Green100
    override val border: Color = Green400
    override val text: Color = BlocketTextColors.default
}

internal object BlocketBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = BlocketBackgroundColors.infoSubtle
    override val infoText: Color = BlocketTextColors.default
    override val positiveBackground: Color = BlocketBackgroundColors.positiveSubtle
    override val positiveText: Color = BlocketTextColors.default
    override val warningBackground: Color = BlocketBackgroundColors.warningSubtle
    override val warningText: Color = BlocketTextColors.default
    override val negativeBackground: Color = BlocketBackgroundColors.negativeSubtle
    override val negativeText: Color = BlocketTextColors.default
    override val disabledBackground: Color = BlocketBackgroundColors.disabled
    override val disabledText: Color = BlocketTextColors.default
    override val neutralBackground: Color = BlocketBackgroundColors.subtle
    override val neutralText: Color = BlocketTextColors.default
}

internal object BlocketLabelColors : WarpLabelColors {
    override val text: Color = BlocketTextColors.default
    override val negative: Color = BlocketTextColors.negative
    override val optional: Color = BlocketTextColors.default
    override val icon: Color = BlocketIconColors.default
    override val disabled: Color = BlocketTextColors.disabled
}

internal object BlocketButtonColors : WarpButtonColors {
    override val primary: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketTextColors.inverted,
        background = BlocketButtonElementColors(
            default = BlocketBackgroundColors.primary,
            active = BlocketBackgroundColors.primaryActive
        ),
        border = null
    )
    override val secondary: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketTextColors.link,
        background = BlocketButtonElementColors(
            default = BlocketBackgroundColors.default,
            active = BlocketBackgroundColors.active
        ),
        border = BlocketButtonElementColors(
            default = BlocketBorderColors.default,
            active = BlocketBorderColors.active
        )
    )
    override val disabled: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketTextColors.inverted,
        background = BlocketButtonElementColors(
            default = BlocketBackgroundColors.disabled,
            active = Gray300
        ),
        border = null
    )
    override val quiet: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketTextColors.link,
        background = BlocketButtonElementColors(
            default = Transparent,
            active = BlocketBackgroundColors.active
        ),
        border = null
    )
    override val negative: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketTextColors.inverted,
        background = BlocketButtonElementColors(
            default = BlocketBackgroundColors.negative,
            active = BlocketBackgroundColors.negativeActive
        ),
        border = null
    )
    override val negativeQuiet: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketTextColors.negative,
        background = BlocketButtonElementColors(
            default = Transparent,
            active = BlocketBackgroundColors.negativeSubtleActive
        ),
        border = null
    )
    override val utility: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketTextColors.default,
        background = BlocketButtonElementColors(
            default = BlocketBackgroundColors.default,
            active = BlocketBackgroundColors.active
        ),
        border = BlocketButtonElementColors(
            default = BlocketBorderColors.default,
            active = BlocketBorderColors.active
        )
    )
    override val utilityOverlay: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = Gray900,
        background = BlocketButtonElementColors(
            default = White,
            active = Gray200
        ),
        border = null
    )
    override val utilityQuiet: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketTextColors.default,
        background = BlocketButtonElementColors(
            default = Transparent,
            active = BlocketBackgroundColors.subtleActive
        ),
        border = null
    )
    override val loading: BlocketButtonStyleColors = BlocketButtonStyleColors(
        text = BlocketTextColors.subtle,
        background = BlocketButtonElementColors(
            default = Gray50,
            active = Gray200
        ),
        border = null
    )
}

internal object BlocketTabColors : WarpTabColors {
    override val selectedContent: Color = BlocketTextColors.link
    override val unselectedContent: Color = BlocketTextColors.subtle
}

internal object BlocketTabRowColors : WarpTabRowColors {
    override val container: Color = BlocketBackgroundColors.default
    override val content: Color = BlocketTextColors.link
    override val indicator: Color = BlocketIconColors.selected
}

internal object BlocketTextFieldColors : WarpTextFieldColors {
    override val textFilled: Color = BlocketTextColors.default
    override val textHint: Color = BlocketTextColors.placeholder
    override val textDisabled: Color = BlocketTextColors.disabled
    override val textNegative: Color = BlocketTextColors.negative
    override val textReadOnly: Color = BlocketTextColors.default
    override val iconNegative: Color = BlocketIconColors.negative
    override val background: Color = BlocketBackgroundColors.default
    override val backgroundDisabled: Color = BlocketBackgroundColors.disabled
    override val border: Color = BlocketBorderColors.default
    override val borderActive: Color = BlocketBorderColors.selected
    override val borderDisabled: Color = BlocketBorderColors.disabled
    override val borderNegative: Color = BlocketBorderColors.negative
    override val caret: Color = BlocketIconColors.default
    override val icon: Color = BlocketIconColors.default
}

internal object BlocketBoxColors : WarpBoxColors {
    override val neutral = WarpComponentBackground(BlocketBackgroundColors.subtle, null)
    override val info = WarpComponentBackground(BlocketBackgroundColors.infoSubtle, null)
    override val bordered =
        WarpComponentBackground(BlocketBackgroundColors.default, BlocketBorderColors.default)
}

internal object BlocketStepIndicatorColors : WarpStepIndicatorColors {
    override val background: Color = BlocketBorderColors.default
    override val backgroundActive: Color = BlocketBackgroundColors.primary
    override val backgroundTrack: Color = BlocketBorderColors.default
    override val backgroundTrackActive: Color = BlocketBackgroundColors.primary
    override val border: Color = BlocketBorderColors.default
    override val borderActive: Color = BlocketBorderColors.primary
    override val icon: Color = BlocketIconColors.inverted
}

internal object BlocketAlertColors : WarpAlertColors {
    override val info: WarpAlertStyleColors = BlocketAlertStyleColors(
        text = BlocketTextColors.default,
        background = BlocketBackgroundColors.infoSubtle,
        subtleBorder = BlocketBorderColors.infoSubtle,
        border = BlocketBorderColors.info,
        icon = BlocketIconColors.info
    )
    override val positive: WarpAlertStyleColors = BlocketAlertStyleColors(
        text = BlocketTextColors.default,
        background = BlocketBackgroundColors.positiveSubtle,
        subtleBorder = BlocketBorderColors.positiveSubtle,
        border = BlocketBorderColors.positive,
        icon = BlocketIconColors.positive
    )
    override val negative: WarpAlertStyleColors = BlocketAlertStyleColors(
        text = BlocketTextColors.default,
        background = BlocketBackgroundColors.negativeSubtle,
        subtleBorder = BlocketBorderColors.negativeSubtle,
        border = BlocketBorderColors.negative,
        icon = BlocketIconColors.negative
    )
    override val warning: WarpAlertStyleColors = BlocketAlertStyleColors(
        text = BlocketTextColors.default,
        background = BlocketBackgroundColors.warningSubtle,
        subtleBorder = BlocketBorderColors.warningSubtle,
        border = BlocketBorderColors.warning,
        icon = BlocketIconColors.warning
    )
}

internal data class BlocketPillStyleColors(
    override val text: Color,
    override val background: Color,
    override val backgroundActive: Color,
    override val icon: Color
) : WarpPillStyleColors

internal data class BlocketButtonStyleColors(
    override val text: Color,
    override val background: BlocketButtonElementColors,
    override val border: BlocketButtonElementColors?
) : WarpButtonStyleColors

internal data class BlocketButtonElementColors(
    override val default: Color,
    override val active: Color
) : WarpButtonElementColors

internal data class BlocketAlertStyleColors(
    override val text: Color,
    override val background: Color,
    override val subtleBorder: Color,
    override val border: Color,
    override val icon: Color
) : WarpAlertStyleColors

//Red
internal val Red50 = Color(0xFFFFE9EC)
internal val Red100 = Color(0xFFFFC7CC)
internal val Red200 = Color(0xFFF4908D)
internal val Red300 = Color(0xFFEB6261)
internal val Red400 = Color(0xFFF43738)
internal val Red500 = Color(0xFFF71414)
internal val Red600 = Color(0xFFE90017)
internal val Red700 = Color(0xFFD70012)
internal val Red800 = Color(0xFFCB0008)
internal val Red900 = Color(0xFFBD0000)

//Yellow
internal val Yellow50 = Color(0xFFFFF9E1)
internal val Yellow100 = Color(0xFFFEEDB4)
internal val Yellow200 = Color(0xFFFEE283)
internal val Yellow300 = Color(0xFFFED850)
internal val Yellow400 = Color(0xFFFFCD29)
internal val Yellow500 = Color(0xFFFFC509)
internal val Yellow600 = Color(0xFFFFB500)
internal val Yellow700 = Color(0xFFFFA400)
internal val Yellow800 = Color(0xFFFF9300)
internal val Yellow900 = Color(0xFFFF7300)

//Blue
internal val Blue50 = Color(0xFFE2F2FF)
internal val Blue100 = Color(0xFFB9DEFF)
internal val Blue200 = Color(0xFF8BC9FF)
internal val Blue300 = Color(0xFF56B4FF)
internal val Blue400 = Color(0xFF25A3FF)
internal val Blue500 = Color(0xFF0093FF)
internal val Blue600 = Color(0xFF0084FF)
internal val Blue700 = Color(0xFF0071EB)
internal val Blue800 = Color(0xFF0D60D9)
internal val Blue900 = Color(0xFF163FB9)

//Green
internal val Green50 = Color(0xFFE5F3EB)
internal val Green100 = Color(0xFFC0E1CE)
internal val Green200 = Color(0xFF98CEB0)
internal val Green300 = Color(0xFF6EBC92)
internal val Green400 = Color(0xFF4EAE7D)
internal val Green500 = Color(0xFF2AA067)
internal val Green600 = Color(0xFF24925D)
internal val Green700 = Color(0xFF1D8050)
internal val Green800 = Color(0xFF196F45)
internal val Green900 = Color(0xFF0C5130)

//Purple
internal val Purple50 = Color(0xFFF9E4F0)
internal val Purple100 = Color(0xFFF0BCDB)
internal val Purple200 = Color(0xFFE58EC4)
internal val Purple300 = Color(0xFFD85DAD)
internal val Purple400 = Color(0xFFCD329D)
internal val Purple500 = Color(0xFFC1008D)
internal val Purple600 = Color(0xFFB40089)
internal val Purple700 = Color(0xFFA20083)
internal val Purple800 = Color(0xFF93007D)
internal val Purple900 = Color(0xFF780072)

//Gray
internal val Gray50 = Color(0xFFFCFAF8)
internal val Gray100 = Color(0xFFF6F4F3)
internal val Gray200 = Color(0xFFEFEDEC)
internal val Gray300 = Color(0xFFE1DFDE)
internal val Gray400 = Color(0xFFBEBCBB)
internal val Gray500 = Color(0xFF9F9D9C)
internal val Gray600 = Color(0xFF767473)
internal val Gray700 = Color(0xFF62605F)
internal val Gray800 = Color(0xFF434140)
internal val Gray900 = Color(0xFF22201F)