package com.schibsted.nmp.warp.brands.tori

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Black70Alpha
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

internal object ToriDarkColors : WarpColors {
    override val surface: WarpSurfaceColors = ToriDarkSurfaceColors
    override val background: WarpBackgroundColors = ToriDarkBackgroundColors
    override val border: WarpBorderColors = ToriDarkBorderColors
    override val icon: WarpIconColors = ToriDarkIconColors
    override val text: WarpTextColors = ToriDarkTextColors
    override val components: WarpComponentColors = ToriComponentDarkColors
}

internal object ToriDarkSurfaceColors : WarpSurfaceColors {
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

internal object ToriDarkBackgroundColors : WarpBackgroundColors {
    override val default = Gray900
    override val hover = Gray850
    override val active = Gray900
    override val disabled = Gray700
    override val disabledSubtle = Gray600
    override val subtle = Gray750
    override val subtleHover = Gray700
    override val subtleActive = Gray750
    override val selected = Blueberry900
    override val selectedHover = Blueberry800
    override val selectedActive = Blueberry900

    override val inverted = Gray50

    override val primary = Blueberry400
    override val primaryHover = Blueberry300
    override val primaryActive = Blueberry400
    override val primarySubtle = Blueberry800
    override val primarySubtleHover = Blueberry700
    override val primarySubtleActive = Blueberry800

    override val secondary = Watermelon500
    override val secondaryHover = Watermelon400
    override val secondaryActive = Watermelon500

    override val positive = Green500
    override val positiveHover = Green400
    override val positiveActive = Green500
    override val positiveSubtle = Green900
    override val positiveSubtleHover = Green800
    override val positiveSubtleActive = Green900

    override val negative = Red300
    override val negativeHover = Red200
    override val negativeActive = Red300
    override val negativeSubtle = Red900
    override val negativeSubtleHover = Red800
    override val negativeSubtleActive = Red900

    override val warning = Yellow500
    override val warningHover = Yellow400
    override val warningActive = Yellow500
    override val warningSubtle = Yellow900
    override val warningSubtleHover = Yellow800
    override val warningSubtleActive = Yellow900

    override val info = Blue500
    override val infoHover = Blue400
    override val infoActive = Blue500
    override val infoSubtle = Blue900
    override val infoSubtleHover = Blue800
    override val infoSubtleActive = Blue900

    override val notification = Watermelon500
}

internal object ToriDarkBorderColors : WarpBorderColors {
    override val default = Gray600
    override val hover = Gray500
    override val active = Gray600
    override val disabled = Gray700
    override val selected = Blueberry400
    override val selectedHover = Blueberry300
    override val selectedActive = Blueberry400
    override val focus = Blue300

    override val primary = Blueberry400
    override val primaryHover = Blueberry300
    override val primaryActive = Blueberry400
    override val primarySubtle = Blueberry700
    override val primarySubtleHover = Blueberry800
    override val primarySubtleActive = Blueberry700

    override val secondary = Watermelon500
    override val secondaryHover = Watermelon400
    override val secondaryActive = Watermelon500

    override val positive = Green500
    override val positiveHover = Green400
    override val positiveActive = Green500
    override val positiveSubtle = Green700
    override val positiveSubtleHover = Green600
    override val positiveSubtleActive = Green700

    override val negative = Red300
    override val negativeHover = Red200
    override val negativeActive = Red300
    override val negativeSubtle = Red700
    override val negativeSubtleHover = Red600
    override val negativeSubtleActive = Red700

    override val warning = Yellow500
    override val warningHover = Yellow400
    override val warningActive = Yellow500
    override val warningSubtle = Yellow700
    override val warningSubtleHover = Yellow600
    override val warningSubtleActive = Yellow700

    override val info = Blue500
    override val infoHover = Blue400
    override val infoActive = Blue500
    override val infoSubtle = Blue700
    override val infoSubtleHover = Blue600
    override val infoSubtleActive = Blue700
}

internal object ToriDarkIconColors : WarpIconColors {
    override val default = White
    override val static = Gray900
    override val hover = Gray100
    override val active = Gray200
    override val selected = Blueberry400
    override val selectedHover = Blueberry300
    override val selectedActive = Blueberry400
    override val disabled = Gray600
    override val subtle = Gray600
    override val subtleHover = Gray200
    override val subtleActive = Gray300
    override val inverted = Gray900
    override val invertedHover = Gray950
    override val invertedActive = Gray900
    override val invertedStatic = White
    override val primary = Blueberry400
    override val secondary = Watermelon500
    override val secondaryHover = Watermelon400
    override val secondaryActive = Watermelon500
    override val positive = Green500
    override val negative = Red300
    override val warning = Yellow500
    override val info = Blue500
    override val notification = White
}

internal object ToriDarkTextColors : WarpTextColors {
    override val default = White
    override val static = Gray900
    override val subtle = Gray100
    override val placeholder = Gray500
    override val inverted = Gray900
    override val invertedStatic = White
    override val invertedSubtle = Gray800
    override val link = Blueberry400
    override val disabled = Gray500
    override val negative = Red300
    override val positive = Green500
    override val notification = White
}

internal object ToriComponentDarkColors : WarpComponentColors {
    override val button: WarpButtonColors = ToriButtonDarkColors
    override val badge: WarpBadgeColors = ToriBadgeDarkColors
    override val callout: WarpCalloutColors = ToriCalloutDarkColors
    override val pill: WarpPillColors = ToriPillDarkColors
}

internal object ToriPillDarkColors : WarpPillColors {
    override val filterTextColor = White
    override val filterIconColor = White
    override val suggestion = Pair(Gray600, Gray600)
}

internal object ToriCalloutDarkColors : WarpCalloutColors {
    override val background: Color = Blue800
    override val border: Color = Blue600
    override val text: Color = ToriDarkTextColors.default
}

internal object ToriBadgeDarkColors : WarpBadgeColors {
    override val infoBackground: Color = Blue700
    override val positiveBackground: Color = Green700
    override val warningBackground: Color = Yellow700
    override val negativeBackground: Color = Red700
    override val disabledBackground: Color = ToriDarkBackgroundColors.disabled
    override val neutralBackground: Color = Gray600
    override val sponsoredBackground: Color = Blue600
    override val priceBackground: Color = Black70Alpha
}

internal object ToriButtonDarkColors : WarpButtonColors {
    override val loading = Pair(Gray700, Gray900)
    override val primary = Pair(Watermelon600, Watermelon800)
}
