package com.schibsted.nmp.warp.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

internal object Placeholders : WarpColors {
    override val surface: WarpSurfaceColors = PlaceholderWarpSurfaceColors
    override val background: WarpBackgroundColors = PlaceholderWarpBackgroundColors
    override val border: WarpBorderColors = PlaceholderWarpBorderColors
    override val icon: WarpIconColors = PlaceholderWarpIconColors
    override val text: WarpTextColors = PlaceholderWarpTextColors
    override val components: WarpComponentColors = PlaceholderWarpComponentColors
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
    override val positiveSelected = Color.Unspecified
    override val positiveSelectedHover = Color.Unspecified
    override val positiveSelectedActive = Color.Unspecified

    override val negative = Color.Unspecified
    override val negativeHover = Color.Unspecified
    override val negativeActive = Color.Unspecified
    override val negativeSubtle = Color.Unspecified
    override val negativeSubtleHover = Color.Unspecified
    override val negativeSubtleActive = Color.Unspecified
    override val negativeSelected = Color.Unspecified
    override val negativeSelectedHover = Color.Unspecified
    override val negativeSelectedActive = Color.Unspecified

    override val warning = Color.Unspecified
    override val warningHover = Color.Unspecified
    override val warningActive = Color.Unspecified
    override val warningSubtle = Color.Unspecified
    override val warningSubtleHover = Color.Unspecified
    override val warningSubtleActive = Color.Unspecified
    override val warningSelected = Color.Unspecified
    override val warningSelectedHover = Color.Unspecified
    override val warningSelectedActive = Color.Unspecified

    override val info = Color.Unspecified
    override val infoHover = Color.Unspecified
    override val infoActive = Color.Unspecified
    override val infoSubtle = Color.Unspecified
    override val infoSubtleHover = Color.Unspecified
    override val infoSubtleActive = Color.Unspecified
    override val infoSelected = Color.Unspecified
    override val infoSelectedHover = Color.Unspecified
    override val infoSelectedActive = Color.Unspecified

    override val notification = Color.Unspecified
}

private object PlaceholderWarpBorderColors : WarpBorderColors {
    override val default = Color.Unspecified
    override val hover = Color.Unspecified
    override val active = Color.Unspecified
    override val disabled = Color.Unspecified
    override val selected = Color.Unspecified
    override val selectedHover = Color.Unspecified
    override val selectedActive = Color.Unspecified
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
    override val primary = Color.Unspecified
    override val secondary = Color.Unspecified
    override val secondaryHover = Color.Unspecified
    override val secondaryActive = Color.Unspecified
    override val positive = Color.Unspecified
    override val negative = Color.Unspecified
    override val warning = Color.Unspecified
    override val info = Color.Unspecified
    override val notification = Color.Unspecified
}

private object PlaceholderWarpTextColors : WarpTextColors {
    override val default = Color.Unspecified
    override val subtle = Color.Unspecified
    override val placeholder = Color.Unspecified
    override val inverted = Color.Unspecified
    override val invertedSubtle = Color.Unspecified
    override val link = Color.Unspecified
    override val disabled = Color.Unspecified
    override val negative = Color.Unspecified
    override val positive = Color.Unspecified
    override val notification = Color.Unspecified
}

private object PlaceholderWarpComponentColors : WarpComponentColors {
    override val badge: WarpBadgeColors = PlaceholderBadgeColors
    override val button: WarpButtonColors = PlaceholderButtonColors
    override val callout: WarpCalloutColors = PlaceholderCalloutColors
    override val pill: WarpPillColors = PlaceholderPillColors
    override val avatar: WarpAvatarColors = PlaceholderAvatarColors
}

private object PlaceholderAvatarColors : WarpAvatarColors {
    override val background: Color = Color.Unspecified
    override val icon: Color = Color.Unspecified
}

private object PlaceholderPillColors : WarpPillColors {
    override val filterTextColor = Color.Unspecified
    override val filterIconColor = Color.Unspecified
    override val suggestion: Pair<Color, Color> = Color.Unspecified to Color.Unspecified
}

private object PlaceholderCalloutColors : WarpCalloutColors {
    override val background: Color = Color.Unspecified
    override val border: Color = Color.Unspecified
    override val text: Color = Color.Unspecified
}

private object PlaceholderBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = Color.Unspecified
    override val positiveBackground: Color = Color.Unspecified
    override val warningBackground: Color = Color.Unspecified
    override val negativeBackground: Color = Color.Unspecified
    override val disabledBackground: Color = Color.Unspecified
    override val neutralBackground: Color = Color.Unspecified
    override val priceBackground: Color = Color.Unspecified
    override val sponsoredBackground: Color = Color.Unspecified
}

private object PlaceholderButtonColors : WarpButtonColors {
    override val loading: Pair<Color, Color> = Color.Unspecified to Color.Unspecified
    override val primary: Pair<Color, Color> = Color.Unspecified to Color.Unspecified
}

internal object PlaceholderResources : WarpResources {
    override val logo: Int = 0
}

internal object PlaceholderShapes : WarpShapes {
    override val medium: Shape = CircleShape
    override val ellipse: Shape = CircleShape
    override val components: WarpComponentShapes = PlaceholderComponentShapes
}

private object PlaceholderComponentShapes : WarpComponentShapes {
    override val badge: WarpBadgeShapes = PlaceholderBadgeShapes
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