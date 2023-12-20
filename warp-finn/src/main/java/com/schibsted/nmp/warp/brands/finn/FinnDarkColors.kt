package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Black
import com.schibsted.nmp.warp.theme.WarpAvatarColors
import com.schibsted.nmp.warp.theme.WarpBackgroundColors
import com.schibsted.nmp.warp.theme.WarpBadgeColors
import com.schibsted.nmp.warp.theme.WarpBorderColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpCalloutColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpIconColors
import com.schibsted.nmp.warp.theme.WarpPillColors
import com.schibsted.nmp.warp.theme.WarpPillStyleColors
import com.schibsted.nmp.warp.theme.WarpTextColors
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
    override val hover = Bluegray800
    override val active = Bluegray900
    override val disabled = Bluegray700
    override val disabledSubtle = Bluegray900
    override val subtle = Bluegray900
    override val subtleHover = Bluegray800
    override val subtleActive = Bluegray900
    override val selected = Blue900
    override val selectedHover = Blue800

    override val inverted = Gray50

    override val primary = Blue500
    override val primaryHover = Blue600
    override val primaryActive = Blue700
    override val primarySubtle = Blue900
    override val primarySubtleHover = Blue800
    override val primarySubtleActive = Blue900

    override val secondary = Aqua400
    override val secondaryHover = Aqua500
    override val secondaryActive = Aqua600

    override val positive = Green500
    override val positiveHover = Green600
    override val positiveActive = Green700
    override val positiveSubtle = Green900
    override val positiveSubtleHover = Green800
    override val positiveSubtleActive = Green900
    override val positiveSelected = Green900
    override val positiveSelectedHover = Green800

    override val negative = Red500
    override val negativeHover = Red600
    override val negativeActive = Red700
    override val negativeSubtle = Red900
    override val negativeSubtleHover = Red800
    override val negativeSubtleActive = Red900
    override val negativeSelected = Red900
    override val negativeSelectedHover = Red800

    override val warning = Yellow500
    override val warningHover = Yellow600
    override val warningActive = Yellow700
    override val warningSubtle = Yellow900
    override val warningSubtleHover = Yellow800
    override val warningSubtleActive = Yellow900
    override val warningSelected = Yellow900
    override val warningSelectedHover = Yellow800

    override val info = Aqua500
    override val infoHover = Aqua600
    override val infoActive = Aqua700
    override val infoSubtle = Aqua900
    override val infoSubtleHover = Aqua800
    override val infoSubtleActive = Aqua900
    override val infoSelected = Aqua500
    override val infoSelectedHover = Aqua600

    override val notification = Red500
}

internal object FinnDarkBorderColors : WarpBorderColors {
    override val default = Bluegray600
    override val hover = Bluegray700
    override val active = Bluegray800
    override val disabled = Bluegray300
    override val selected = Blue500
    override val selectedHover = Blue600
    override val focus = Aqua300

    override val primary = Blue500
    override val primaryHover = Blue600
    override val primaryActive = Blue700
    override val primarySubtle = Blue700
    override val primarySubtleHover = Blue800
    override val primarySubtleActive = Blue900

    override val secondary = Aqua400
    override val secondaryHover = Aqua500
    override val secondaryActive = Aqua600

    override val positive = Green500
    override val positiveHover = Green600
    override val positiveActive = Green700
    override val positiveSubtle = Green700
    override val positiveSubtleHover = Green800
    override val positiveSubtleActive = Green900

    override val negative = Red500
    override val negativeHover = Red600
    override val negativeActive = Red700
    override val negativeSubtle = Red700
    override val negativeSubtleHover = Red800
    override val negativeSubtleActive = Red900

    override val warning = Yellow500
    override val warningHover = Yellow600
    override val warningActive = Yellow700
    override val warningSubtle = Yellow700
    override val warningSubtleHover = Yellow800
    override val warningSubtleActive = Yellow900

    override val info = Aqua500
    override val infoHover = Aqua600
    override val infoActive = Aqua700
    override val infoSubtle = Aqua700
    override val infoSubtleHover = Aqua800
    override val infoSubtleActive = Aqua900
}

internal object FinnDarkIconColors : WarpIconColors {
    override val default = White
    override val hover = Gray100
    override val active = Gray200
    override val selected = Blue500
    override val selectedHover = Blue600
    override val disabled = Bluegray600
    override val subtle = Bluegray100
    override val subtleHover = Bluegray200
    override val subtleActive = Bluegray300
    override val inverted = Black
    override val invertedHover = Bluegray800
    override val invertedActive = Bluegray900
    override val primary = Blue500
    override val secondary = Aqua400
    override val secondaryHover = Aqua500
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
    override val badge: WarpBadgeColors = FinnBadgeDarkColors
    override val callout: WarpCalloutColors = FinnCalloutDarkColors
    override val pill: WarpPillColors = FinnPillDarkColors
    override val avatar: WarpAvatarColors = FinnAvatarDarkColors
}

internal object FinnAvatarDarkColors : WarpAvatarColors {
    override val background: Color = Bluegray800
    override val icon: Color = FinnDarkIconColors.disabled
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
        background = Gray600,
        backgroundActive = Gray800,
        icon = FinnDarkIconColors.default
    )
}

internal object FinnCalloutDarkColors : WarpCalloutColors {
    override val background: Color = Green800
    override val border: Color = Green600
    override val text: Color = FinnDarkTextColors.default
}

internal object FinnBadgeDarkColors : WarpBadgeColors {
    override val infoBackground: Color = Aqua700
    override val positiveBackground: Color = Green700
    override val warningBackground: Color = Yellow700
    override val negativeBackground: Color = Red700
    override val disabledBackground: Color = FinnDarkBackgroundColors.disabled
    override val neutralBackground: Color = Bluegray700
}

internal object FinnButtonDarkColors : WarpButtonColors {
    override val loading = Pair(Bluegray700, Bluegray900)
    override val primary = Pair(FinnBackgroundColors.primary, FinnBackgroundColors.primaryActive)
}
