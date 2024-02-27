package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Black
import com.schibsted.nmp.warp.theme.Black70Alpha
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
import com.schibsted.nmp.warp.theme.WarpSurfaceColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.White

internal object FinnDarkColors : WarpColors {
    override val surface: WarpSurfaceColors = FinnDarkSurfaceColors
    override val background: WarpBackgroundColors = FinnDarkBackgroundColors
    override val border: WarpBorderColors = FinnDarkBorderColors
    override val icon: WarpIconColors = FinnDarkIconColors
    override val text: WarpTextColors = FinnDarkTextColors
    override val components: WarpComponentColors = FinnComponentDarkColors
}

internal object FinnDarkSurfaceColors : WarpSurfaceColors {
    override val sunken = Gray950
    override val elevated100 = Gray850
    override val elevated100Hover = Gray800
    override val elevated100Active = Gray850
    override val elevated200 = Gray800
    override val elevated200Hover = Gray750
    override val elevated200Active = Gray800
    override val elevated300 = Gray750
    override val elevated300Hover = Gray700
    override val elevated300Active = Gray750
}

internal object FinnDarkBackgroundColors : WarpBackgroundColors {
    override val default = Gray900
    override val hover = Gray850
    override val active = Gray900
    override val disabled = Gray700
    override val disabledSubtle = Gray600
    override val neutralSubtle = Gray750
    override val neutralSubtleHover = Gray700
    override val neutralSubtleActive = Gray750
    override val selected = Blue900
    override val selectedHover = Blue800
    override val selectedActive = Blue900

    override val inverted = Gray50

    override val primary = Blue400
    override val primaryHover = Blue300
    override val primaryActive = Blue400
    override val primarySubtle = Blue800
    override val primarySubtleHover = Blue700
    override val primarySubtleActive = Blue800

    override val secondary = Aqua400
    override val secondaryHover = Aqua300
    override val secondaryActive = Aqua400

    override val positive = Green500
    override val positiveHover = Green400
    override val positiveActive = Green500
    override val positiveSubtle = Green900
    override val positiveSubtleHover = Green800
    override val positiveSubtleActive = Green900
    override val positiveSelected = Green900
    override val positiveSelectedHover = Green800
    override val positiveSelectedActive = Green900

    override val negative = Red400
    override val negativeHover = Red300
    override val negativeActive = Red400
    override val negativeSubtle = Red900
    override val negativeSubtleHover = Red800
    override val negativeSubtleActive = Red900
    override val negativeSelected = Red900
    override val negativeSelectedHover = Red800
    override val negativeSelectedActive = Red900

    override val warning = Yellow500
    override val warningHover = Yellow400
    override val warningActive = Yellow500
    override val warningSubtle = Yellow900
    override val warningSubtleHover = Yellow800
    override val warningSubtleActive = Yellow900
    override val warningSelected = Yellow900
    override val warningSelectedHover = Yellow800
    override val warningSelectedActive = Yellow900

    override val info = Aqua500
    override val infoHover = Aqua400
    override val infoActive = Aqua500
    override val infoSubtle = Aqua900
    override val infoSubtleHover = Aqua800
    override val infoSubtleActive = Aqua900
    override val infoSelected = Aqua900
    override val infoSelectedHover = Aqua800
    override val infoSelectedActive = Aqua900

    override val notification = Red500
}

internal object FinnDarkBorderColors : WarpBorderColors {
    override val default = Gray600
    override val hover = Gray700
    override val active = Gray800
    override val disabled = Gray700
    override val selected = Blue500
    override val selectedHover = Blue600
    override val selectedActive = Blue500
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
    override val selectedActive = Blue500
    override val disabled = Gray600
    override val subtle = Gray100
    override val subtleHover = Gray200
    override val subtleActive = Gray300
    override val inverted = Black
    override val invertedHover = Gray800
    override val invertedActive = Gray900
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
    override val placeholder = Gray500
    override val inverted = White
    override val invertedSubtle = Gray800
    override val link = Blue500
    override val disabled = Gray500
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
    override val background: Color = Gray800
    override val icon: Color = FinnDarkIconColors.disabled
}

internal object FinnPillDarkColors : WarpPillColors {
    override val filterTextColor = White
    override val filterIconColor = White
    override val suggestion = Pair(Gray600, Gray800)
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
    override val neutralBackground: Color = Gray700
    override val sponsoredBackground: Color = Aqua600
    override val priceBackground: Color = Black70Alpha
}

internal object FinnButtonDarkColors : WarpButtonColors {
    override val loading = Pair(Gray700, Gray900)
    override val primary = Pair(FinnBackgroundColors.primary, FinnBackgroundColors.primaryActive)
}
