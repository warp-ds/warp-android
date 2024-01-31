@file:Suppress("unused")

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

internal val styleDictionaryColors = StyleDictionaryColorFinnLight

internal object FinnColors : WarpColors {
    override val background: WarpBackgroundColors = FinnBackgroundColors
    override val border: WarpBorderColors = FinnBorderColors
    override val icon: WarpIconColors = FinnIconColors
    override val text: WarpTextColors = FinnTextColors
    override val components: WarpComponentColors = FinnComponentColors
}

internal object FinnBackgroundColors : WarpBackgroundColors {
    override val default = styleDictionaryColors.semanticBackgroundDefault
    override val hover = styleDictionaryColors.semanticBackgroundHover
    override val active = styleDictionaryColors.semanticBackgroundActive
    override val disabled = styleDictionaryColors.semanticBackgroundDisabled
    override val disabledSubtle = styleDictionaryColors.semanticBackgroundDisabledSubtle
    override val subtle = styleDictionaryColors.semanticBackgroundSubtle
    override val subtleHover = styleDictionaryColors.semanticBackgroundSubtleHover
    override val subtleActive = styleDictionaryColors.semanticBackgroundSubtleActive
    override val selected = styleDictionaryColors.semanticBackgroundSelected
    override val selectedHover = styleDictionaryColors.semanticBackgroundSelectedHover
    override val selectedActive = Blue200//styleDictionaryColors.semanticBackgroundSelectedActive

    override val inverted = styleDictionaryColors.semanticBackgroundInverted

    override val primary = styleDictionaryColors.semanticBackgroundPrimary
    override val primaryHover = styleDictionaryColors.semanticBackgroundPrimaryHover
    override val primaryActive = styleDictionaryColors.semanticBackgroundPrimaryActive
    override val primarySubtle = styleDictionaryColors.semanticBackgroundPrimarySubtle
    override val primarySubtleHover = styleDictionaryColors.semanticBackgroundPrimarySubtleHover
    override val primarySubtleActive = styleDictionaryColors.semanticBackgroundPrimarySubtleActive

    override val secondary = styleDictionaryColors.semanticBackgroundSecondary
    override val secondaryHover = styleDictionaryColors.semanticBackgroundSecondaryHover
    override val secondaryActive = styleDictionaryColors.semanticBackgroundSecondaryActive

    override val positive = styleDictionaryColors.semanticBackgroundPositive
    override val positiveHover = styleDictionaryColors.semanticBackgroundPositiveHover
    override val positiveActive = styleDictionaryColors.semanticBackgroundPositiveActive
    override val positiveSubtle = styleDictionaryColors.semanticBackgroundPositiveSubtle
    override val positiveSubtleHover = styleDictionaryColors.semanticBackgroundPositiveSubtleHover
    override val positiveSubtleActive = styleDictionaryColors.semanticBackgroundPositiveSubtleActive
    override val positiveSelected = styleDictionaryColors.semanticBackgroundPositiveSelected
    override val positiveSelectedHover = styleDictionaryColors.semanticBackgroundPositiveSelectedHover
    override val positiveSelectedActive = Green200//styleDictionaryColors.semanticBackgroundPositiveSelectedActive

    override val negative = styleDictionaryColors.semanticBackgroundNegative
    override val negativeHover = styleDictionaryColors.semanticBackgroundNegativeHover
    override val negativeActive = styleDictionaryColors.semanticBackgroundNegativeActive
    override val negativeSubtle = styleDictionaryColors.semanticBackgroundNegativeSubtle
    override val negativeSubtleHover = styleDictionaryColors.semanticBackgroundNegativeSubtleHover
    override val negativeSubtleActive = styleDictionaryColors.semanticBackgroundNegativeSubtleActive
    override val negativeSelected = Red50//styleDictionaryColors.semanticBackgroundNegativeSelected
    override val negativeSelectedHover = Red100//styleDictionaryColors.semanticBackgroundNegativeSelectedHover
    override val negativeSelectedActive = Red200//styleDictionaryColors.semanticBackgroundNegativeSelectedActive

    override val warning = styleDictionaryColors.semanticBackgroundWarning
    override val warningHover = styleDictionaryColors.semanticBackgroundWarningHover
    override val warningActive = styleDictionaryColors.semanticBackgroundWarningActive
    override val warningSubtle = styleDictionaryColors.semanticBackgroundWarningSubtle
    override val warningSubtleHover = styleDictionaryColors.semanticBackgroundWarningSubtleHover
    override val warningSubtleActive = styleDictionaryColors.semanticBackgroundWarningSubtleActive
    override val warningSelected = Yellow50//styleDictionaryColors.semanticBackgroundWarningSelected
    override val warningSelectedHover = Yellow100//styleDictionaryColors.semanticBackgroundWarningSelectedHover
    override val warningSelectedActive = Yellow200//styleDictionaryColors.semanticBackgroundWarningSelectedActive

    override val info = styleDictionaryColors.semanticBackgroundInfo
    override val infoHover = styleDictionaryColors.semanticBackgroundInfoHover
    override val infoActive = styleDictionaryColors.semanticBackgroundInfoActive
    override val infoSubtle = styleDictionaryColors.semanticBackgroundInfoSubtle
    override val infoSubtleHover = styleDictionaryColors.semanticBackgroundInfoSubtleHover
    override val infoSubtleActive = styleDictionaryColors.semanticBackgroundInfoSubtleActive
    override val infoSelected = Aqua50//styleDictionaryColors.semanticBackgroundInfoSelected
    override val infoSelectedHover = Aqua100//styleDictionaryColors.semanticBackgroundInfoSelectedHover
    override val infoSelectedActive = Aqua200//styleDictionaryColors.semanticBackgroundInfoSelectedActive

    override val notification = styleDictionaryColors.semanticBackgroundNotification
}

internal object FinnBorderColors : WarpBorderColors {
    override val default = styleDictionaryColors.semanticBorderDefault
    override val hover = styleDictionaryColors.semanticBorderHover
    override val active = styleDictionaryColors.semanticBorderActive
    override val disabled = styleDictionaryColors.semanticBorderDisabled
    override val selected = styleDictionaryColors.semanticBorderSelected
    override val selectedHover = styleDictionaryColors.semanticBorderSelectedHover
    override val selectedActive = Blue800//styleDictionaryColors.semanticBorderSelectedActive
    override val focus = styleDictionaryColors.semanticBorderFocus

    override val primary = styleDictionaryColors.semanticBorderPrimary
    override val primaryHover = styleDictionaryColors.semanticBorderPrimaryHover
    override val primaryActive = styleDictionaryColors.semanticBorderPrimaryActive
    override val primarySubtle = styleDictionaryColors.semanticBorderPrimarySubtle
    override val primarySubtleHover = styleDictionaryColors.semanticBorderPrimarySubtleHover
    override val primarySubtleActive = styleDictionaryColors.semanticBorderPrimarySubtleActive

    override val secondary = styleDictionaryColors.semanticBorderSecondary
    override val secondaryHover = styleDictionaryColors.semanticBorderSecondaryHover
    override val secondaryActive = styleDictionaryColors.semanticBorderSecondaryActive

    override val positive = styleDictionaryColors.semanticBorderPositive
    override val positiveHover = styleDictionaryColors.semanticBorderPositiveHover
    override val positiveActive = styleDictionaryColors.semanticBorderPositiveActive
    override val positiveSubtle = styleDictionaryColors.semanticBorderPositiveSubtle
    override val positiveSubtleHover = styleDictionaryColors.semanticBorderPositiveSubtleHover
    override val positiveSubtleActive = styleDictionaryColors.semanticBorderPositiveSubtleActive

    override val negative = styleDictionaryColors.semanticBorderNegative
    override val negativeHover = styleDictionaryColors.semanticBorderNegativeHover
    override val negativeActive = styleDictionaryColors.semanticBorderNegativeActive
    override val negativeSubtle = styleDictionaryColors.semanticBorderNegativeSubtle
    override val negativeSubtleHover = styleDictionaryColors.semanticBorderNegativeSubtleHover
    override val negativeSubtleActive = styleDictionaryColors.semanticBorderNegativeSubtleActive

    override val warning = styleDictionaryColors.semanticBorderWarning
    override val warningHover = styleDictionaryColors.semanticBorderWarningHover
    override val warningActive = styleDictionaryColors.semanticBorderWarningActive
    override val warningSubtle = styleDictionaryColors.semanticBorderWarningSubtle
    override val warningSubtleHover = styleDictionaryColors.semanticBorderWarningSubtleHover
    override val warningSubtleActive = styleDictionaryColors.semanticBorderWarningSubtleActive

    override val info = styleDictionaryColors.semanticBorderInfo
    override val infoHover = styleDictionaryColors.semanticBorderInfoHover
    override val infoActive = styleDictionaryColors.semanticBorderInfoActive
    override val infoSubtle = styleDictionaryColors.semanticBorderInfoSubtle
    override val infoSubtleHover = styleDictionaryColors.semanticBorderInfoSubtleHover
    override val infoSubtleActive = styleDictionaryColors.semanticBorderInfoSubtleActive
}

internal object FinnIconColors : WarpIconColors {
    override val default = styleDictionaryColors.semanticIconDefault
    override val hover = styleDictionaryColors.semanticIconHover
    override val active = styleDictionaryColors.semanticIconActive
    override val selected = styleDictionaryColors.semanticIconSelected
    override val selectedHover = styleDictionaryColors.semanticIconSelectedHover
    override val selectedActive = Blue800//styleDictionaryColors.semanticIconSelectedActive
    override val disabled = styleDictionaryColors.semanticIconDisabled
    override val subtle = styleDictionaryColors.semanticIconSubtle
    override val subtleHover = styleDictionaryColors.semanticIconSubtleHover
    override val subtleActive = styleDictionaryColors.semanticIconSubtleActive
    override val inverted = styleDictionaryColors.semanticIconInverted
    override val invertedHover = styleDictionaryColors.semanticIconInvertedHover
    override val invertedActive = styleDictionaryColors.semanticIconInvertedActive
    override val primary = styleDictionaryColors.semanticIconPrimary
    override val secondary = styleDictionaryColors.semanticIconSecondary
    override val secondaryHover = styleDictionaryColors.semanticIconSecondaryHover
    override val secondaryActive = styleDictionaryColors.semanticIconSecondaryActive
    override val positive = styleDictionaryColors.semanticIconPositive
    override val negative = styleDictionaryColors.semanticIconNegative
    override val warning = styleDictionaryColors.semanticIconWarning
    override val info = styleDictionaryColors.semanticIconInfo
    override val notification = styleDictionaryColors.semanticIconNotification
}

internal object FinnTextColors : WarpTextColors {
    override val default = styleDictionaryColors.semanticTextDefault
    override val subtle = styleDictionaryColors.semanticTextSubtle
    override val placeholder = styleDictionaryColors.semanticTextPlaceholder
    override val inverted = styleDictionaryColors.semanticTextInverted
    override val invertedSubtle = styleDictionaryColors.semanticTextInvertedSubtle
    override val link = styleDictionaryColors.semanticTextLink
    override val disabled = styleDictionaryColors.semanticTextDisabled
    override val negative = styleDictionaryColors.semanticTextNegative
    override val positive = styleDictionaryColors.semanticTextPositive
    override val notification = White//styleDictionaryColors.semanticTextNotification
}

internal object FinnComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = FinnButtonColors
    override val badge: WarpBadgeColors = FinnBadgeColors
    override val callout: WarpCalloutColors = FinnCalloutColors
    override val pill: WarpPillColors = FinnPillColors
    override val avatar: WarpAvatarColors = FinnAvatarColors
}

internal object FinnAvatarColors : WarpAvatarColors {
    override val background: Color = styleDictionaryColors.componentsAvatarBackground
    override val icon: Color = styleDictionaryColors.componentsAvatarIcon
}

internal object FinnPillColors : WarpPillColors {
    override val filterTextColor = White//styleDictionaryColors.componentsPillFilterTextColor
    override val filterIconColor = White//styleDictionaryColors.componentsPillFilterIconColor
    override val suggestion = Pair(styleDictionaryColors.componentsPillSuggestionBackground, styleDictionaryColors.componentsPillSuggestionBackgroundActive)
}

internal object FinnCalloutColors : WarpCalloutColors {
    override val background: Color = styleDictionaryColors.componentsCalloutBackground
    override val border: Color = styleDictionaryColors.componentsCalloutBorder
    override val text: Color = styleDictionaryColors.componentsCalloutText
}

internal object FinnBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = styleDictionaryColors.componentsBadgeInfoBackground
    override val positiveBackground: Color = styleDictionaryColors.componentsBadgePositiveBackground
    override val warningBackground: Color = styleDictionaryColors.componentsBadgeWarningBackground
    override val negativeBackground: Color = styleDictionaryColors.componentsBadgeNegativeBackground
    override val disabledBackground: Color = FinnBackgroundColors.disabled//styleDictionaryColors.componentsBadgeDisabledBackground
    override val neutralBackground: Color = styleDictionaryColors.componentsBadgeNeutralBackground
    override val sponsoredBackground: Color = styleDictionaryColors.componentsBadgeSponsoredBackground
    override val priceBackground: Color = styleDictionaryColors.componentsBadgePriceBackground
}

internal object FinnButtonColors : WarpButtonColors {
    override val loading = Pair(Bluegray50, Bluegray200)//todo missing in Figma
    override val primary = Pair(styleDictionaryColors.componentsButtonPrimaryButtonPrimary, styleDictionaryColors.componentsButtonPrimaryButtonPrimaryActive)
}

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
