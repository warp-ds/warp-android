package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.graphics.Color
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

internal val styleDictionaryColorsDark = StyleDictionaryColorFinnDark

internal object FinnDarkColors : WarpColors {
    override val background: WarpBackgroundColors = FinnDarkBackgroundColors
    override val border: WarpBorderColors = FinnDarkBorderColors
    override val icon: WarpIconColors = FinnDarkIconColors
    override val text: WarpTextColors = FinnDarkTextColors
    override val components: WarpComponentColors = FinnComponentDarkColors
}

internal object FinnDarkBackgroundColors : WarpBackgroundColors {
    override val default = styleDictionaryColorsDark.semanticBackgroundDefault
    override val hover = styleDictionaryColorsDark.semanticBackgroundHover
    override val active = styleDictionaryColorsDark.semanticBackgroundActive
    override val disabled = styleDictionaryColorsDark.semanticBackgroundDisabled
    override val disabledSubtle = styleDictionaryColorsDark.semanticBackgroundDisabledSubtle
    override val subtle = styleDictionaryColorsDark.semanticBackgroundSubtle
    override val subtleHover = styleDictionaryColorsDark.semanticBackgroundSubtleHover
    override val subtleActive = styleDictionaryColorsDark.semanticBackgroundSubtleActive
    override val selected = styleDictionaryColorsDark.semanticBackgroundSelected
    override val selectedHover = styleDictionaryColorsDark.semanticBackgroundSelectedHover
    override val selectedActive = Blue900

    override val inverted = styleDictionaryColorsDark.semanticBackgroundInverted

    override val primary = styleDictionaryColorsDark.semanticBackgroundPrimary
    override val primaryHover = styleDictionaryColorsDark.semanticBackgroundPrimaryHover
    override val primaryActive = styleDictionaryColorsDark.semanticBackgroundPrimaryActive
    override val primarySubtle = styleDictionaryColorsDark.semanticBackgroundPrimarySubtle
    override val primarySubtleHover = styleDictionaryColorsDark.semanticBackgroundPrimarySubtleHover
    override val primarySubtleActive = styleDictionaryColorsDark.semanticBackgroundPrimarySubtleActive

    override val secondary = styleDictionaryColorsDark.semanticBackgroundSecondary
    override val secondaryHover = styleDictionaryColorsDark.semanticBackgroundSecondaryHover
    override val secondaryActive = styleDictionaryColorsDark.semanticBackgroundSecondaryActive

    override val positive = styleDictionaryColorsDark.semanticBackgroundPositive
    override val positiveHover = styleDictionaryColorsDark.semanticBackgroundPositiveHover
    override val positiveActive = styleDictionaryColorsDark.semanticBackgroundPositiveActive
    override val positiveSubtle = styleDictionaryColorsDark.semanticBackgroundPositiveSubtle
    override val positiveSubtleHover = styleDictionaryColorsDark.semanticBackgroundPositiveSubtleHover
    override val positiveSubtleActive = styleDictionaryColorsDark.semanticBackgroundPositiveSubtleActive
    override val positiveSelected = styleDictionaryColorsDark.semanticBackgroundPositiveSelected
    override val positiveSelectedHover = styleDictionaryColorsDark.semanticBackgroundPositiveSelectedHover
    override val positiveSelectedActive = Green900

    override val negative = styleDictionaryColorsDark.semanticBackgroundNegative
    override val negativeHover = styleDictionaryColorsDark.semanticBackgroundNegativeHover
    override val negativeActive = styleDictionaryColorsDark.semanticBackgroundNegativeActive
    override val negativeSubtle = styleDictionaryColorsDark.semanticBackgroundNegativeSubtle
    override val negativeSubtleHover = styleDictionaryColorsDark.semanticBackgroundNegativeSubtleHover
    override val negativeSubtleActive = styleDictionaryColorsDark.semanticBackgroundNegativeSubtleActive
    override val negativeSelected = Red900
    override val negativeSelectedHover = Red800
    override val negativeSelectedActive = Red900

    override val warning = styleDictionaryColorsDark.semanticBackgroundWarning
    override val warningHover = styleDictionaryColorsDark.semanticBackgroundWarningHover
    override val warningActive = styleDictionaryColorsDark.semanticBackgroundWarningActive
    override val warningSubtle = styleDictionaryColorsDark.semanticBackgroundWarningSubtle
    override val warningSubtleHover = styleDictionaryColorsDark.semanticBackgroundWarningSubtleHover
    override val warningSubtleActive = styleDictionaryColorsDark.semanticBackgroundWarningSubtleActive
    override val warningSelected = Yellow900
    override val warningSelectedHover = Yellow800
    override val warningSelectedActive = Yellow900

    override val info = styleDictionaryColorsDark.semanticBackgroundInfo
    override val infoHover = styleDictionaryColorsDark.semanticBackgroundInfoHover
    override val infoActive = styleDictionaryColorsDark.semanticBackgroundInfoActive
    override val infoSubtle = styleDictionaryColorsDark.semanticBackgroundInfoSubtle
    override val infoSubtleHover = styleDictionaryColorsDark.semanticBackgroundInfoSubtleHover
    override val infoSubtleActive = styleDictionaryColorsDark.semanticBackgroundInfoSubtleActive
    override val infoSelected = Aqua500
    override val infoSelectedHover = Aqua600
    override val infoSelectedActive = Aqua500

    override val notification = styleDictionaryColorsDark.semanticBackgroundNotification
}

internal object FinnDarkBorderColors : WarpBorderColors {
    override val default = styleDictionaryColorsDark.semanticBorderDefault
    override val hover = styleDictionaryColorsDark.semanticBorderHover
    override val active = styleDictionaryColorsDark.semanticBorderActive
    override val disabled = styleDictionaryColorsDark.semanticBorderDisabled
    override val selected = styleDictionaryColorsDark.semanticBorderSelected
    override val selectedHover = styleDictionaryColorsDark.semanticBorderSelectedHover
    override val selectedActive = Blue500
    override val focus = styleDictionaryColorsDark.semanticBorderFocus

    override val primary = styleDictionaryColorsDark.semanticBorderPrimary
    override val primaryHover = styleDictionaryColorsDark.semanticBorderPrimaryHover
    override val primaryActive = styleDictionaryColorsDark.semanticBorderPrimaryActive
    override val primarySubtle = styleDictionaryColorsDark.semanticBorderPrimarySubtle
    override val primarySubtleHover = styleDictionaryColorsDark.semanticBorderPrimarySubtleHover
    override val primarySubtleActive = styleDictionaryColorsDark.semanticBorderPrimarySubtleActive

    override val secondary = styleDictionaryColorsDark.semanticBorderSecondary
    override val secondaryHover = styleDictionaryColorsDark.semanticBorderSecondaryHover
    override val secondaryActive = styleDictionaryColorsDark.semanticBorderSecondaryActive

    override val positive = styleDictionaryColorsDark.semanticBorderPositive
    override val positiveHover = styleDictionaryColorsDark.semanticBorderPositiveHover
    override val positiveActive = styleDictionaryColorsDark.semanticBorderPositiveActive
    override val positiveSubtle = styleDictionaryColorsDark.semanticBorderPositiveSubtle
    override val positiveSubtleHover = styleDictionaryColorsDark.semanticBorderPositiveSubtleHover
    override val positiveSubtleActive = styleDictionaryColorsDark.semanticBorderPositiveSubtleActive

    override val negative = styleDictionaryColorsDark.semanticBorderNegative
    override val negativeHover = styleDictionaryColorsDark.semanticBorderNegativeHover
    override val negativeActive = styleDictionaryColorsDark.semanticBorderNegativeActive
    override val negativeSubtle = styleDictionaryColorsDark.semanticBorderNegativeSubtle
    override val negativeSubtleHover = styleDictionaryColorsDark.semanticBorderNegativeSubtleHover
    override val negativeSubtleActive = styleDictionaryColorsDark.semanticBorderNegativeSubtleActive

    override val warning = styleDictionaryColorsDark.semanticBorderWarning
    override val warningHover = styleDictionaryColorsDark.semanticBorderWarningHover
    override val warningActive = styleDictionaryColorsDark.semanticBorderWarningActive
    override val warningSubtle = styleDictionaryColorsDark.semanticBorderWarningSubtle
    override val warningSubtleHover = styleDictionaryColorsDark.semanticBorderWarningSubtleHover
    override val warningSubtleActive = styleDictionaryColorsDark.semanticBorderWarningSubtleActive

    override val info = styleDictionaryColorsDark.semanticBorderInfo
    override val infoHover = styleDictionaryColorsDark.semanticBorderInfoHover
    override val infoActive = styleDictionaryColorsDark.semanticBorderInfoActive
    override val infoSubtle = styleDictionaryColorsDark.semanticBorderInfoSubtle
    override val infoSubtleHover = styleDictionaryColorsDark.semanticBorderInfoSubtleHover
    override val infoSubtleActive = styleDictionaryColorsDark.semanticBorderInfoSubtleActive
}

internal object FinnDarkIconColors : WarpIconColors {
    override val default = styleDictionaryColorsDark.semanticIconDefault
    override val hover = styleDictionaryColorsDark.semanticIconHover
    override val active = styleDictionaryColorsDark.semanticIconActive
    override val selected = styleDictionaryColorsDark.semanticIconSelected
    override val selectedHover = styleDictionaryColorsDark.semanticIconSelectedHover
    override val selectedActive = Blue500
    override val disabled = styleDictionaryColorsDark.semanticIconDisabled
    override val subtle = styleDictionaryColorsDark.semanticIconSubtle
    override val subtleHover = styleDictionaryColorsDark.semanticIconSubtleHover
    override val subtleActive = styleDictionaryColorsDark.semanticIconSubtleActive
    override val inverted = styleDictionaryColorsDark.semanticIconInverted
    override val invertedHover = styleDictionaryColorsDark.semanticIconInvertedHover
    override val invertedActive = styleDictionaryColorsDark.semanticIconInvertedActive
    override val primary = styleDictionaryColorsDark.semanticIconPrimary
    override val secondary = styleDictionaryColorsDark.semanticIconSecondary
    override val secondaryHover = styleDictionaryColorsDark.semanticIconSecondaryHover
    override val secondaryActive = styleDictionaryColorsDark.semanticIconSecondaryActive
    override val positive = styleDictionaryColorsDark.semanticIconPositive
    override val negative = styleDictionaryColorsDark.semanticIconNegative
    override val warning = styleDictionaryColorsDark.semanticIconWarning
    override val info = styleDictionaryColorsDark.semanticIconInfo
    override val notification = styleDictionaryColorsDark.semanticIconNotification
}

internal object FinnDarkTextColors : WarpTextColors {
    override val default = styleDictionaryColorsDark.semanticTextDefault
    override val subtle = styleDictionaryColorsDark.semanticTextSubtle
    override val placeholder = styleDictionaryColorsDark.semanticTextPlaceholder
    override val inverted = styleDictionaryColorsDark.semanticTextInverted
    override val invertedSubtle = styleDictionaryColorsDark.semanticTextInvertedSubtle
    override val link = styleDictionaryColorsDark.semanticTextLink
    override val disabled = styleDictionaryColorsDark.semanticTextDisabled
    override val negative = styleDictionaryColorsDark.semanticTextNegative
    override val positive = styleDictionaryColorsDark.semanticTextPositive
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
    override val background: Color = styleDictionaryColorsDark.componentsAvatarBackground
    override val icon: Color = styleDictionaryColorsDark.componentsAvatarIcon
}

internal object FinnPillDarkColors : WarpPillColors {
    override val filterTextColor = White
    override val filterIconColor = White
    override val suggestion = Pair(styleDictionaryColorsDark.componentsPillSuggestionBackground, styleDictionaryColorsDark.componentsPillSuggestionBackgroundActive)
}

internal object FinnCalloutDarkColors : WarpCalloutColors {
    override val background: Color = styleDictionaryColorsDark.componentsCalloutBackground
    override val border: Color = styleDictionaryColorsDark.componentsCalloutBorder
    override val text: Color = styleDictionaryColorsDark.componentsCalloutText
}

internal object FinnBadgeDarkColors : WarpBadgeColors {
    override val infoBackground: Color = styleDictionaryColorsDark.componentsBadgeInfoBackground
    override val positiveBackground: Color = styleDictionaryColorsDark.componentsBadgePositiveBackground
    override val warningBackground: Color = styleDictionaryColorsDark.componentsBadgeWarningBackground
    override val negativeBackground: Color = styleDictionaryColorsDark.componentsBadgeNegativeBackground
    override val disabledBackground: Color = FinnDarkBackgroundColors.disabled//styleDictionaryColorsDark.componentsBadgeDisabledBackground
    override val neutralBackground: Color = styleDictionaryColorsDark.componentsBadgeNeutralBackground
    override val sponsoredBackground: Color = styleDictionaryColorsDark.componentsBadgeSponsoredBackground
    override val priceBackground: Color = styleDictionaryColorsDark.componentsBadgePriceBackground
}

internal object FinnButtonDarkColors : WarpButtonColors {
    override val loading = Pair(Bluegray700, Bluegray900)
    override val primary = Pair(styleDictionaryColorsDark.componentsButtonPrimaryButtonPrimary, styleDictionaryColorsDark.componentsButtonPrimaryButtonPrimaryActive)
}
