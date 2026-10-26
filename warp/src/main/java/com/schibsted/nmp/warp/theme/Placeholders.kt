package com.schibsted.nmp.warp.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.dataviz.DatavizColors

internal object Placeholders : WarpColors {
    override val surface: WarpSurfaceColors = PlaceholderWarpSurfaceColors
    override val background: WarpBackgroundColors = PlaceholderWarpBackgroundColors
    override val border: WarpBorderColors = PlaceholderWarpBorderColors
    override val icon: WarpIconColors = PlaceholderWarpIconColors
    override val text: WarpTextColors = PlaceholderWarpTextColors
    override val components: WarpComponentColors = PlaceholderWarpComponentColors
    override val dataviz: WarpDatavizColors = DatavizColors
}

private object PlaceholderWarpSurfaceColors : WarpSurfaceColors {
    override val sunken = Color.Unspecified
    override val elevated100 = Color.Unspecified
    override val elevated100Hover = Color.Unspecified
    override val elevated100Active = Color.Unspecified
    override val elevated200 = Color.Unspecified
    override val elevated200Hover = Color.Unspecified
    override val elevated200Active = Color.Unspecified
    override val elevated300 = Color.Unspecified
    override val elevated300Hover = Color.Unspecified
    override val elevated300Active = Color.Unspecified
}

private object PlaceholderWarpBackgroundColors : WarpBackgroundColors {
    override val default = Color.Unspecified
    override val hover = Color.Unspecified
    override val active = Color.Unspecified
    override val disabled = Color.Unspecified
    override val disabledSubtle = Color.Unspecified
    override val subtle = Color.Unspecified
    override val subtleHover = Color.Unspecified
    override val subtleActive = Color.Unspecified
    override val selected = Color.Unspecified
    override val selectedHover = Color.Unspecified
    override val selectedActive = Color.Unspecified

    override val inverted = Color.Unspecified

    override val primary = Color.Unspecified
    override val primaryHover = Color.Unspecified
    override val primaryActive = Color.Unspecified
    override val primarySubtle = Color.Unspecified
    override val primarySubtleHover = Color.Unspecified
    override val primarySubtleActive = Color.Unspecified

    override val secondary = Color.Unspecified
    override val secondaryHover = Color.Unspecified
    override val secondaryActive = Color.Unspecified

    override val positive = Color.Unspecified
    override val positiveHover = Color.Unspecified
    override val positiveActive = Color.Unspecified
    override val positiveSubtle = Color.Unspecified
    override val positiveSubtleHover = Color.Unspecified
    override val positiveSubtleActive = Color.Unspecified

    override val negative = Color.Unspecified
    override val negativeHover = Color.Unspecified
    override val negativeActive = Color.Unspecified
    override val negativeSubtle = Color.Unspecified
    override val negativeSubtleHover = Color.Unspecified
    override val negativeSubtleActive = Color.Unspecified

    override val warning = Color.Unspecified
    override val warningHover = Color.Unspecified
    override val warningActive = Color.Unspecified
    override val warningSubtle = Color.Unspecified
    override val warningSubtleHover = Color.Unspecified
    override val warningSubtleActive = Color.Unspecified

    override val info = Color.Unspecified
    override val infoHover = Color.Unspecified
    override val infoActive = Color.Unspecified
    override val infoSubtle = Color.Unspecified
    override val infoSubtleHover = Color.Unspecified
    override val infoSubtleActive = Color.Unspecified

    override val notification = Color.Unspecified
    override val transparent0 = Color.Unspecified

    override val interactiveNeutral = Color.Unspecified
    override val interactiveNeutralHover = Color.Unspecified
    override val interactiveNeutralActive = Color.Unspecified
    override val interactiveStatic = Color.Unspecified
    override val interactiveStaticHover = Color.Unspecified
    override val interactiveStaticActive = Color.Unspecified
    override val interactiveInvertedStatic = Color.Unspecified
    override val interactiveInvertedStaticHover = Color.Unspecified
    override val interactiveInvertedStaticActive = Color.Unspecified
    override val interactiveNegativeHover = Color.Unspecified
    override val interactiveNegativeActive = Color.Unspecified
}

private object PlaceholderWarpBorderColors : WarpBorderColors {
    override val default = Color.Unspecified
    override val hover = Color.Unspecified
    override val active = Color.Unspecified
    override val strong = Color.Unspecified
    override val strongHover = Color.Unspecified
    override val disabled = Color.Unspecified
    override val selected = Color.Unspecified
    override val selectedHover = Color.Unspecified
    override val selectedActive = Color.Unspecified
    override val inverted = Color.Unspecified
    override val focus: Color = Color.Unspecified

    override val primary = Color.Unspecified
    override val primaryHover = Color.Unspecified
    override val primaryActive = Color.Unspecified
    override val primarySubtle = Color.Unspecified
    override val primarySubtleHover = Color.Unspecified
    override val primarySubtleActive = Color.Unspecified

    override val secondary = Color.Unspecified
    override val secondaryHover = Color.Unspecified
    override val secondaryActive = Color.Unspecified

    override val positive = Color.Unspecified
    override val positiveHover = Color.Unspecified
    override val positiveActive = Color.Unspecified
    override val positiveSubtle = Color.Unspecified
    override val positiveSubtleHover = Color.Unspecified
    override val positiveSubtleActive = Color.Unspecified

    override val negative = Color.Unspecified
    override val negativeHover = Color.Unspecified
    override val negativeActive = Color.Unspecified
    override val negativeSubtle = Color.Unspecified
    override val negativeSubtleHover = Color.Unspecified
    override val negativeSubtleActive = Color.Unspecified

    override val warning = Color.Unspecified
    override val warningHover = Color.Unspecified
    override val warningActive = Color.Unspecified
    override val warningSubtle = Color.Unspecified
    override val warningSubtleHover = Color.Unspecified
    override val warningSubtleActive = Color.Unspecified

    override val info = Color.Unspecified
    override val infoHover = Color.Unspecified
    override val infoActive = Color.Unspecified
    override val infoSubtle = Color.Unspecified
    override val infoSubtleHover = Color.Unspecified
    override val infoSubtleActive = Color.Unspecified
}

private object PlaceholderWarpIconColors : WarpIconColors {
    override val default = Color.Unspecified
    override val static = Color.Unspecified
    override val hover = Color.Unspecified
    override val active = Color.Unspecified
    override val selected = Color.Unspecified
    override val selectedHover = Color.Unspecified
    override val selectedActive = Color.Unspecified
    override val disabled = Color.Unspecified
    override val subtle = Color.Unspecified
    override val subtleHover = Color.Unspecified
    override val subtleActive = Color.Unspecified
    override val inverted = Color.Unspecified
    override val invertedHover = Color.Unspecified
    override val invertedActive = Color.Unspecified
    override val invertedStatic = Color.Unspecified
    override val primary = Color.Unspecified
    override val secondary = Color.Unspecified
    override val secondaryHover = Color.Unspecified
    override val secondaryActive = Color.Unspecified
    override val positive = Color.Unspecified
    override val negative = Color.Unspecified
    override val warning = Color.Unspecified
    override val info = Color.Unspecified
    override val notification = Color.Unspecified
    override val hoverStatic = Color.Unspecified
    override val invertedHoverStatic = Color.Unspecified
    override val activeStatic = Color.Unspecified
    override val invertedActiveStatic = Color.Unspecified
    override val selectedStatic = Color.Unspecified
    override val invertedSelectedStatic = Color.Unspecified
    override val invertedSelected = Color.Unspecified
    override val selectedHoverStatic = Color.Unspecified
    override val invertedSelectedHoverStatic = Color.Unspecified
    override val invertedSelectedHover = Color.Unspecified
    override val selectedActiveStatic = Color.Unspecified
    override val invertedSelectedActiveStatic = Color.Unspecified
    override val invertedSelectedActive = Color.Unspecified
    override val disabledStatic = Color.Unspecified
    override val invertedDisabledStatic = Color.Unspecified
    override val invertedDisabled = Color.Unspecified
    override val subtleStatic = Color.Unspecified
    override val invertedSubtleStatic = Color.Unspecified
    override val invertedSubtle = Color.Unspecified
    override val subtleHoverStatic = Color.Unspecified
    override val invertedSubtleHoverStatic = Color.Unspecified
    override val invertedSubtleHover = Color.Unspecified
    override val subtleActiveStatic = Color.Unspecified
    override val invertedSubtleActiveStatic = Color.Unspecified
    override val invertedSubtleActive = Color.Unspecified
    override val primaryStatic = Color.Unspecified
    override val invertedPrimaryStatic = Color.Unspecified
    override val invertedPrimary = Color.Unspecified
    override val secondaryStatic = Color.Unspecified
    override val invertedSecondaryStatic = Color.Unspecified
    override val invertedSecondary = Color.Unspecified
    override val secondaryHoverStatic = Color.Unspecified
    override val invertedSecondaryHoverStatic = Color.Unspecified
    override val invertedSecondaryHover = Color.Unspecified
    override val secondaryActiveStatic = Color.Unspecified
    override val invertedSecondaryActiveStatic = Color.Unspecified
    override val invertedSecondaryActive = Color.Unspecified
    override val positiveStatic = Color.Unspecified
    override val invertedPositiveStatic = Color.Unspecified
    override val invertedPositive = Color.Unspecified
    override val negativeStatic = Color.Unspecified
    override val invertedNegativeStatic = Color.Unspecified
    override val invertedNegative = Color.Unspecified
    override val warningStatic = Color.Unspecified
    override val invertedWarningStatic = Color.Unspecified
    override val invertedWarning = Color.Unspecified
    override val infoStatic = Color.Unspecified
    override val invertedInfoStatic = Color.Unspecified
    override val invertedInfo = Color.Unspecified
    override val notificationStatic = Color.Unspecified
    override val invertedNotificationStatic = Color.Unspecified
    override val invertedNotification = Color.Unspecified
}

private object PlaceholderWarpTextColors : WarpTextColors {
    override val default = Color.Unspecified
    override val static = Color.Unspecified
    override val subtle = Color.Unspecified
    override val placeholder = Color.Unspecified
    override val inverted = Color.Unspecified
    override val invertedStatic = Color.Unspecified
    override val invertedSubtle = Color.Unspecified
    override val link = Color.Unspecified
    override val disabled = Color.Unspecified
    override val negative = Color.Unspecified
    override val positive = Color.Unspecified
    override val subtleStatic = Color.Unspecified
    override val invertedSubtleStatic = Color.Unspecified
    override val placeholderStatic = Color.Unspecified
    override val invertedPlaceholderStatic = Color.Unspecified
    override val invertedPlaceholder = Color.Unspecified
    override val linkStatic = Color.Unspecified
    override val invertedLinkStatic = Color.Unspecified
    override val invertedLink = Color.Unspecified
    override val disabledStatic = Color.Unspecified
    override val invertedDisabledStatic = Color.Unspecified
    override val invertedDisabled = Color.Unspecified
    override val negativeStatic = Color.Unspecified
    override val invertedNegativeStatic = Color.Unspecified
    override val invertedNegative = Color.Unspecified
    override val positiveStatic = Color.Unspecified
    override val invertedPositiveStatic = Color.Unspecified
    override val invertedPositive = Color.Unspecified
}

private object PlaceholderWarpComponentColors : WarpComponentColors {
    override val badge: WarpBadgeColors = PlaceholderBadgeColors
    override val button: WarpButtonColors = PlaceholderButtonColors
    override val callout: WarpCalloutColors = PlaceholderCalloutColors
    override val pill: WarpPillColors = PlaceholderPillColors
    override val navBar: WarpNavBarColors = PlaceholderNavBarColors
    override val tooltip: WarpTooltipColors = PlaceholderTooltipColors
    override val switch: WarpSwitchColors = PlaceholderSwitchColors
    override val card: WarpCardColors = PlaceholderCardColors
    override val pageIndicator: WarpPageIndicatorColors = PlaceholderPageIndicatorColors
}

private object PlaceholderPageIndicatorColors : WarpPageIndicatorColors {
    override val indicatorBackground = Color.Unspecified
    override val indicatorBackgroundHover = Color.Unspecified
}

private object PlaceholderCardColors : WarpCardColors {
    override val defaultBackground = Color.Unspecified
}

private object PlaceholderSwitchColors : WarpSwitchColors {
    override val handleBackground: Color = Color.Unspecified
    override val handleBackgroundHover: Color = Color.Unspecified
    override val trackBorder: Color = Color.Unspecified
    override val trackBorderHover: Color = Color.Unspecified
}

private object PlaceholderTooltipColors : WarpTooltipColors {
    override val backgroundStatic: Color = Color.Unspecified
}

private object PlaceholderNavBarColors : WarpNavBarColors {
    override val iconSelected: Color = Color.Unspecified
    override val borderSelected: Color = Color.Unspecified
}

private object PlaceholderPillColors : WarpPillColors {
    override val suggestionBackground = Color.Unspecified
    override val suggestionBackgroundHover = Color.Unspecified
    override val suggestionBackgroundActive = Color.Unspecified
}

private object PlaceholderCalloutColors : WarpCalloutColors {
    override val background: Color = Color.Unspecified
    override val border: Color = Color.Unspecified
}

private object PlaceholderBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = Color.Unspecified
    override val positiveBackground: Color = Color.Unspecified
    override val warningBackground: Color = Color.Unspecified
    override val negativeBackground: Color = Color.Unspecified
    override val neutralBackground: Color = Color.Unspecified
    override val priceBackground: Color = Color.Unspecified
    override val sponsoredBackground: Color = Color.Unspecified
}

private object PlaceholderButtonColors : WarpButtonColors {
    override val primaryBackground: Color = Color.Unspecified
    override val primaryBackgroundHover: Color = Color.Unspecified
    override val primaryBackgroundActive: Color = Color.Unspecified
    override val pillBackgroundHover = Color.Unspecified
    override val pillBackgroundActive = Color.Unspecified
}

internal object PlaceholderShapes : WarpShapes {
    override val roundedSmall: Shape = CircleShape
    override val roundedMedium: Shape = CircleShape
    override val ellipse: Shape = CircleShape
    override val components: WarpComponentShapes = PlaceholderComponentShapes
}

private object PlaceholderComponentShapes : WarpComponentShapes {
    override val badge: WarpBadgeShapes = PlaceholderBadgeShapes
    override val callout = RoundedCornerShape(8.dp)
    override val button = CircleShape
}

private object PlaceholderBadgeShapes : WarpBadgeShapes {
    override val default: Shape = CircleShape
    override val topStart: Shape = CircleShape
    override val topEnd: Shape = CircleShape
    override val bottomEnd: Shape = CircleShape
    override val bottomStart: Shape = CircleShape
}

internal object PlaceholderTypography : WarpTypography {
    override val display: TextStyle = TextStyle.Default
    override val title1: TextStyle = TextStyle.Default
    override val title2: TextStyle = TextStyle.Default
    override val title3: TextStyle = TextStyle.Default
    override val title4: TextStyle = TextStyle.Default
    override val title5: TextStyle = TextStyle.Default
    override val title6: TextStyle = TextStyle.Default
    override val preamble: TextStyle = TextStyle.Default
    override val body: TextStyle = TextStyle.Default
    override val bodyStrong: TextStyle = TextStyle.Default
    override val caption: TextStyle = TextStyle.Default
    override val captionStrong: TextStyle = TextStyle.Default
    override val detail: TextStyle = TextStyle.Default
    override val detailStrong: TextStyle = TextStyle.Default
}