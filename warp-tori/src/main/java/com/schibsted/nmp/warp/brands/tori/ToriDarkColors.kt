package com.schibsted.nmp.warp.brands.tori

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
import com.schibsted.nmp.warp.theme.WarpTextColors
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
    override val hover = Gray800
    override val active = Gray900
    override val disabled = Gray700
    override val disabledSubtle = Gray900
    override val subtle = Gray900
    override val subtleHover = Gray800
    override val subtleActive = Gray900
    override val selected = Blueberry900
    override val selectedHover = Blueberry800
    override val selectedActive = Blueberry900

    override val inverted = Gray50

    override val primary = Blueberry500
    override val primaryHover = Blueberry700
    override val primaryActive = Blueberry800
    override val primarySubtle = Blueberry900
    override val primarySubtleHover = Blueberry800
    override val primarySubtleActive = Blueberry900

    override val secondary = Watermelon500
    override val secondaryHover = Watermelon600
    override val secondaryActive = Watermelon700

    override val positive = Green500
    override val positiveHover = Green600
    override val positiveActive = Green700
    override val positiveSubtle = Green900
    override val positiveSubtleHover = Green800
    override val positiveSubtleActive = Green900
    override val positiveSelected = Green900
    override val positiveSelectedHover = Green800
    override val positiveSelectedActive = Green900

    override val negative = Red500
    override val negativeHover = Red600
    override val negativeActive = Red700
    override val negativeSubtle = Red900
    override val negativeSubtleHover = Red800
    override val negativeSubtleActive = Red900
    override val negativeSelected = Red900
    override val negativeSelectedHover = Red800
    override val negativeSelectedActive = Red900

    override val warning = Yellow500
    override val warningHover = Yellow600
    override val warningActive = Yellow700
    override val warningSubtle = Yellow900
    override val warningSubtleHover = Yellow800
    override val warningSubtleActive = Yellow900
    override val warningSelected = Yellow900
    override val warningSelectedHover = Yellow800
    override val warningSelectedActive = Yellow900

    override val info = Blue500
    override val infoHover = Blue600
    override val infoActive = Blue700
    override val infoSubtle = Blue900
    override val infoSubtleHover = Blue800
    override val infoSubtleActive = Blue900
    override val infoSelected = Blue500
    override val infoSelectedHover = Blue600
    override val infoSelectedActive = Blue500

    override val notification = Red500
}

internal object ToriDarkBorderColors : WarpBorderColors {
    override val default = Gray600
    override val hover = Gray700
    override val active = Gray800
    override val disabled = Gray700
    override val selected = Blueberry500
    override val selectedHover = Blueberry600
    override val selectedActive = Blueberry500
    override val focus = Blue300

    override val primary = Blueberry500
    override val primaryHover = Blueberry600
    override val primaryActive = Blueberry700
    override val primarySubtle = Blueberry700
    override val primarySubtleHover = Blueberry800
    override val primarySubtleActive = Blueberry900

    override val secondary = Watermelon500
    override val secondaryHover = Watermelon600
    override val secondaryActive = Watermelon700

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

    override val info = Blue500
    override val infoHover = Blue600
    override val infoActive = Blue700
    override val infoSubtle = Blue700
    override val infoSubtleHover = Blue800
    override val infoSubtleActive = Blue900
}

internal object ToriDarkIconColors : WarpIconColors {
    override val default = White
    override val hover = Gray100
    override val active = Gray200
    override val selected = Blueberry500
    override val selectedHover = Blueberry600
    override val selectedActive = Blueberry500
    override val disabled = Gray600
    override val subtle = Gray100
    override val subtleHover = Gray200
    override val subtleActive = Gray300
    override val inverted = Black
    override val invertedHover = Gray800
    override val invertedActive = Gray900
    override val primary = Blueberry500
    override val secondary = Watermelon500
    override val secondaryHover = Watermelon600
    override val secondaryActive = Watermelon700
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
    override val link = Blueberry500
    override val disabled = Gray500
    override val negative = Red500
    override val positive = Green500
    override val notification = White
}

internal object ToriComponentDarkColors : WarpComponentColors {
    override val button: WarpButtonColors = ToriButtonDarkColors
    override val badge: WarpBadgeColors = ToriBadgeDarkColors
    override val callout: WarpCalloutColors = ToriCalloutDarkColors
    override val pill: WarpPillColors = ToriPillDarkColors
    override val avatar: WarpAvatarColors = ToriAvatarDarkColors
}

internal object ToriAvatarDarkColors : WarpAvatarColors {
    override val background = Gray100
    override val icon = ToriDarkIconColors.disabled
}

internal object ToriPillDarkColors : WarpPillColors {
    override val filterTextColor = White
    override val filterIconColor = White
    override val suggestion = Pair(Gray600, Gray800)
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
    override val neutralBackground: Color = Gray700
    override val sponsoredBackground: Color = Blue600
    override val priceBackground: Color = Black70Alpha
}

internal object ToriButtonDarkColors : WarpButtonColors {
    override val loading = Pair(Gray700, Gray900)
    override val primary = Pair(Watermelon600, Watermelon800)
}
