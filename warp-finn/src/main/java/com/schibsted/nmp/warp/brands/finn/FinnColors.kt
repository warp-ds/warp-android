@file:Suppress("unused")

package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Transparent
import com.schibsted.nmp.warp.theme.WarpBackgroundColors
import com.schibsted.nmp.warp.theme.WarpBadgeColors
import com.schibsted.nmp.warp.theme.WarpBorderColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpButtonElementColors
import com.schibsted.nmp.warp.theme.WarpButtonStyleColors
import com.schibsted.nmp.warp.theme.WarpCalloutColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpIconColors
import com.schibsted.nmp.warp.theme.WarpPillColors
import com.schibsted.nmp.warp.theme.WarpPillStyleColors
import com.schibsted.nmp.warp.theme.WarpStepIndicatorColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.White

internal object FinnColors : WarpColors {
    override val background: WarpBackgroundColors = FinnBackgroundColors
    override val border: WarpBorderColors = FinnBorderColors
    override val icon: WarpIconColors = FinnIconColors
    override val text: WarpTextColors = FinnTextColors
    override val components: WarpComponentColors = FinnComponentColors
}

internal object FinnBackgroundColors : WarpBackgroundColors {
    override val default = White
    override val hover = Bluegray100
    override val active = Bluegray200
    override val disabled = Bluegray300
    override val disabledSubtle = Bluegray200
    override val subtle = Bluegray50
    override val subtleHover = Bluegray100
    override val subtleActive = Bluegray200
    override val selected = Blue50
    override val selectedHover = Blue100

    override val inverted = Gray900

    override val primary = Blue600
    override val primaryHover = Blue700
    override val primaryActive = Blue800
    override val primarySubtle = Blue50
    override val primarySubtleHover = Blue100
    override val primarySubtleActive = Blue200

    override val secondary = Aqua400
    override val secondaryHover = Aqua500
    override val secondaryActive = Aqua600

    override val positive = Green600
    override val positiveHover = Green700
    override val positiveActive = Green800
    override val positiveSubtle = Green50
    override val positiveSubtleHover = Green100
    override val positiveSubtleActive = Green200
    override val positiveSelected = Green50
    override val positiveSelectedHover = Green100

    override val negative = Red600
    override val negativeHover = Red700
    override val negativeActive = Red800
    override val negativeSubtle = Red50
    override val negativeSubtleHover = Red100
    override val negativeSubtleActive = Red200
    override val negativeSelected = Red50
    override val negativeSelectedHover = Red100

    override val warning = Yellow600
    override val warningHover = Yellow700
    override val warningActive = Yellow800
    override val warningSubtle = Yellow50
    override val warningSubtleHover = Yellow100
    override val warningSubtleActive = Yellow200
    override val warningSelected = Yellow50
    override val warningSelectedHover = Yellow100

    override val info = Aqua600
    override val infoHover = Aqua700
    override val infoActive = Aqua800
    override val infoSubtle = Aqua50
    override val infoSubtleHover = Aqua100
    override val infoSubtleActive = Aqua200
    override val infoSelected = Aqua50
    override val infoSelectedHover = Aqua100

    override val notification = Red600
}

internal object FinnBorderColors : WarpBorderColors {
    override val default = Bluegray300
    override val hover = Bluegray400
    override val active = Bluegray500
    override val disabled = Bluegray300
    override val selected = Blue600
    override val selectedHover = Blue700

    override val primary = Blue600
    override val primaryHover = Blue700
    override val primaryActive = Blue800
    override val primarySubtle = Blue300
    override val primarySubtleHover = Blue400
    override val primarySubtleActive = Blue500

    override val secondary = Aqua400
    override val secondaryHover = Aqua500
    override val secondaryActive = Aqua600

    override val positive = Green600
    override val positiveHover = Green700
    override val positiveActive = Green800
    override val positiveSubtle = Green300
    override val positiveSubtleHover = Green400
    override val positiveSubtleActive = Green500

    override val negative = Red600
    override val negativeHover = Red700
    override val negativeActive = Red800
    override val negativeSubtle = Red300
    override val negativeSubtleHover = Red400
    override val negativeSubtleActive = Red500

    override val warning = Yellow600
    override val warningHover = Yellow700
    override val warningActive = Yellow800
    override val warningSubtle = Yellow300
    override val warningSubtleHover = Yellow400
    override val warningSubtleActive = Yellow500

    override val info = Aqua600
    override val infoHover = Aqua700
    override val infoActive = Aqua800
    override val infoSubtle = Aqua300
    override val infoSubtleHover = Aqua400
    override val infoSubtleActive = Aqua500
}

internal object FinnIconColors : WarpIconColors {
    override val default = Gray700
    override val hover = Blue600
    override val active = Blue700
    override val selected = Blue600
    override val selectedHover = Blue700
    override val disabled = Bluegray300
    override val subtle = Bluegray400
    override val subtleHover = Bluegray500
    override val subtleActive = Bluegray600
    override val inverted = White
    override val invertedHover = Bluegray100
    override val invertedActive = Bluegray200
    override val primary = Blue600
    override val secondary = Aqua400
    override val secondaryHover = Aqua500
    override val secondaryActive = Aqua600
    override val positive = Green600
    override val negative = Red600
    override val warning = Yellow600
    override val info = Aqua600
    override val notification = White
}

internal object FinnTextColors : WarpTextColors {
    override val default = Gray700
    override val subtle = Gray500
    override val placeholder = Bluegray300
    override val inverted = White
    override val invertedSubtle = Gray50
    override val link = Blue600
    override val disabled = Bluegray300
    override val negative = Red600
    override val positive = Green600
    override val notification = White
}

internal object FinnComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = FinnButtonColors
    override val stepIndicator: WarpStepIndicatorColors = FinnStepIndicatorColors
    override val badge: WarpBadgeColors = FinnBadgeColors
    override val callout: WarpCalloutColors = FinnCalloutColors
    override val pill: WarpPillColors = FinnPillColors
}

internal object FinnPillColors : WarpPillColors {
    override val filter: WarpPillStyleColors = FinnPillStyleColors(
        text = FinnTextColors.inverted,
        background = FinnBackgroundColors.primary,
        backgroundActive = FinnBackgroundColors.primaryActive,
        icon = FinnIconColors.inverted
    )
    override val suggestion: WarpPillStyleColors = FinnPillStyleColors(
        text = FinnTextColors.default,
        background = Bluegray300,
        backgroundActive = Bluegray500,
        icon = FinnIconColors.default
    )
}

internal object FinnCalloutColors : WarpCalloutColors {
    override val background: Color = Green100
    override val border: Color = Green400
    override val text: Color = FinnTextColors.default
}

internal object FinnBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = Aqua100
    override val positiveBackground: Color = Green100
    override val warningBackground: Color = Yellow100
    override val negativeBackground: Color = Red100
    override val disabledBackground: Color = FinnBackgroundColors.disabled
    override val neutralBackground: Color = Bluegray100
}

internal object FinnStepIndicatorColors : WarpStepIndicatorColors {
    override val background: Color = FinnBorderColors.default
    override val backgroundActive: Color = FinnBackgroundColors.primary
    override val backgroundTrack: Color = FinnBorderColors.default
    override val backgroundTrackActive: Color = FinnBackgroundColors.primary
    override val border: Color = FinnBorderColors.default
    override val borderActive: Color = FinnBorderColors.primary
    override val icon: Color = FinnIconColors.inverted
}

internal object FinnButtonColors : WarpButtonColors {
    override val primary: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnTextColors.inverted,
        background = FinnButtonElementColors(
            default = FinnBackgroundColors.primary,
            active = FinnBackgroundColors.primaryActive
        ),
        border = null
    )
    override val secondary: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnTextColors.link,
        background = FinnButtonElementColors(
            default = FinnBackgroundColors.default,
            active = FinnBackgroundColors.active
        ),
        border = FinnButtonElementColors(
            default = FinnBorderColors.default,
            active = FinnBorderColors.active
        )
    )
    override val disabled: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnTextColors.inverted,
        background = FinnButtonElementColors(
            default = FinnBackgroundColors.disabled,
            active = Bluegray300
        ),
        border = null
    )
    override val quiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnTextColors.link,
        background = FinnButtonElementColors(
            default = Transparent,
            active = FinnBackgroundColors.active
        ),
        border = null
    )
    override val negative: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnTextColors.inverted,
        background = FinnButtonElementColors(
            default = FinnBackgroundColors.negative,
            active = FinnBackgroundColors.negativeActive
        ),
        border = null
    )
    override val negativeQuiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnTextColors.negative,
        background = FinnButtonElementColors(
            default = Transparent,
            active = FinnBackgroundColors.negativeSubtleActive
        ),
        border = null
    )
    override val utility: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnTextColors.default,
        background = FinnButtonElementColors(
            default = FinnBackgroundColors.default,
            active = FinnBackgroundColors.active
        ),
        border = FinnButtonElementColors(
            default = FinnBorderColors.default,
            active = FinnBorderColors.active
        )
    )
    override val utilityOverlay: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Gray700,
        background = FinnButtonElementColors(
            default = White,
            active = Bluegray200
        ),
        border = null
    )
    override val utilityQuiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnTextColors.default,
        background = FinnButtonElementColors(
            default = Transparent,
            active = FinnBackgroundColors.subtleActive
        ),
        border = null
    )
    override val loading: FinnButtonStyleColors = FinnButtonStyleColors(
        text = FinnTextColors.subtle,
        background = FinnButtonElementColors(
            default = Bluegray50,
            active = Bluegray200
        ),
        border = null
    )
}

internal data class FinnPillStyleColors (
    override val text: Color,
    override val background: Color,
    override val backgroundActive: Color,
    override val icon: Color
) : WarpPillStyleColors

internal data class FinnButtonStyleColors(
    override val text: Color,
    override val background: FinnButtonElementColors,
    override val border: FinnButtonElementColors?
) : WarpButtonStyleColors

internal data class FinnButtonElementColors(
    override val default: Color,
    override val active: Color
) : WarpButtonElementColors

//Blue
internal val Blue50 = Color(0xffeff5ff)
internal val Blue100 = Color(0xffe1edfe)
internal val Blue200 = Color(0xffc2dafe)
internal val Blue300 = Color(0xff9ac1fe)
internal val Blue400 = Color(0xff5c9cff)
internal val Blue500 = Color(0xff2b7eff)
internal val Blue600 = Color(0xff0063fb)
internal val Blue700 = Color(0xff244eb3)
internal val Blue800 = Color(0xff223474)
internal val Blue900 = Color(0xff191d34)

//Gray
internal val Gray50 = Color(0xfffafafa)
internal val Gray100 = Color(0xfff4f4f5)
internal val Gray200 = Color(0xffe4e4e7)
internal val Gray300 = Color(0xffd4d4d8)
internal val Gray400 = Color(0xffa1a1aa)
internal val Gray500 = Color(0xff71717a)
internal val Gray600 = Color(0xff52525b)
internal val Gray700 = Color(0xff3f3f46)
internal val Gray800 = Color(0xff27272a)
internal val Gray900 = Color(0xff18181b)

//Aqua
internal val Aqua50 = Color(0xfff1f9ff)
internal val Aqua100 = Color(0xffe0f6ff)
internal val Aqua200 = Color(0xffb6f0ff)
internal val Aqua300 = Color(0xff66e0ff)
internal val Aqua400 = Color(0xff06befb)
internal val Aqua500 = Color(0xff03a3dd)
internal val Aqua600 = Color(0xff0386bf)
internal val Aqua700 = Color(0xff1e648a)
internal val Aqua800 = Color(0xff1d435a)
internal val Aqua900 = Color(0xff15242f)

//Green
internal val Green50 = Color(0xffebfff6)
internal val Green100 = Color(0xffcdfee5)
internal val Green200 = Color(0xff9efcd1)
internal val Green300 = Color(0xff67eeb8)
internal val Green400 = Color(0xff2ee69f)
internal val Green500 = Color(0xff18c884)
internal val Green600 = Color(0xff059e6f)
internal val Green700 = Color(0xff1d7454)
internal val Green800 = Color(0xff1b4d39)
internal val Green900 = Color(0xff14291f)

//Yellow
internal val Yellow50 = Color(0xfffff8e6)
internal val Yellow100 = Color(0xfffff5c8)
internal val Yellow200 = Color(0xfffeef90)
internal val Yellow300 = Color(0xfffae76b)
internal val Yellow400 = Color(0xffffe11f)
internal val Yellow500 = Color(0xffeeb61b)
internal val Yellow600 = Color(0xffd5840b)
internal val Yellow700 = Color(0xff9b621e)
internal val Yellow800 = Color(0xff654118)
internal val Yellow900 = Color(0xff352310)

//Red
internal val Red50 = Color(0xfffff5f5)
internal val Red100 = Color(0xffffefef)
internal val Red200 = Color(0xffffd1d1)
internal val Red300 = Color(0xffff9999)
internal val Red400 = Color(0xffff5844)
internal val Red500 = Color(0xfffa270f)
internal val Red600 = Color(0xffd91f0a)
internal val Red700 = Color(0xff9e2216)
internal val Red800 = Color(0xff681d11)
internal val Red900 = Color(0xff38140b)

//Bluegray
internal val Bluegray50 = Color(0xfff8fafc)
internal val Bluegray100 = Color(0xfff1f4f9)
internal val Bluegray200 = Color(0xffe1e6ee)
internal val Bluegray300 = Color(0xffc3ccd9)
internal val Bluegray400 = Color(0xff9ba8ba)
internal val Bluegray500 = Color(0xff6f7d90)
internal val Bluegray600 = Color(0xff4d586f)
internal val Bluegray700 = Color(0xff3b4353)
internal val Bluegray800 = Color(0xff292d38)
internal val Bluegray900 = Color(0xff181a1f)
