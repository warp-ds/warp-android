package com.schibsted.nmp.warp.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

internal object Placeholders : WarpColors {
    override val background: WarpBackgroundColors = PlaceholderWarpBackgroundColors
    override val border: WarpBorderColors = PlaceholderWarpBorderColors
    override val icon: WarpIconColors = PlaceholderWarpIconColors
    override val text: WarpTextColors = PlaceholderWarpTextColors
    override val components: WarpComponentColors = PlaceholderWarpComponentColors
}

private object PlaceholderWarpBackgroundColors : WarpBackgroundColors {
    override val default = Color.Unspecified
    override val active = Color.Unspecified
    override val disabled = Color.Unspecified
    override val subtle = Color.Unspecified
    override val subtleActive = Color.Unspecified
    override val selected = Color.Unspecified

    override val inverted = Color.Unspecified

    override val primary = Color.Unspecified
    override val primaryActive = Color.Unspecified
    override val primarySubtle = Color.Unspecified
    override val primarySubtleActive = Color.Unspecified

    override val positive = Color.Unspecified
    override val positiveActive = Color.Unspecified
    override val positiveSubtle = Color.Unspecified
    override val positiveSubtleActive = Color.Unspecified
    override val positiveSelected = Color.Unspecified

    override val negative = Color.Unspecified
    override val negativeActive = Color.Unspecified
    override val negativeSubtle = Color.Unspecified
    override val negativeSubtleActive = Color.Unspecified
    override val negativeSelected = Color.Unspecified

    override val warning = Color.Unspecified
    override val warningActive = Color.Unspecified
    override val warningSubtle = Color.Unspecified
    override val warningSubtleActive = Color.Unspecified
    override val warningSelected = Color.Unspecified

    override val info = Color.Unspecified
    override val infoActive = Color.Unspecified
    override val infoSubtle = Color.Unspecified
    override val infoSubtleActive = Color.Unspecified
    override val infoSelected = Color.Unspecified

    override val notification = Color.Unspecified
}

private object PlaceholderWarpBorderColors : WarpBorderColors {
    override val default = Color.Unspecified
    override val active = Color.Unspecified
    override val disabled = Color.Unspecified
    override val selected = Color.Unspecified

    override val primary = Color.Unspecified
    override val primaryActive = Color.Unspecified
    override val primarySubtle = Color.Unspecified
    override val primarySubtleActive = Color.Unspecified

    override val positive = Color.Unspecified
    override val positiveActive = Color.Unspecified
    override val positiveSubtle = Color.Unspecified
    override val positiveSubtleActive = Color.Unspecified

    override val negative = Color.Unspecified
    override val negativeActive = Color.Unspecified
    override val negativeSubtle = Color.Unspecified
    override val negativeSubtleActive = Color.Unspecified

    override val warning = Color.Unspecified
    override val warningActive = Color.Unspecified
    override val warningSubtle = Color.Unspecified
    override val warningSubtleActive = Color.Unspecified

    override val info = Color.Unspecified
    override val infoActive = Color.Unspecified
    override val infoSubtle = Color.Unspecified
    override val infoSubtleActive = Color.Unspecified
}

private object PlaceholderWarpIconColors : WarpIconColors {
    override val default = Color.Unspecified
    override val active = Color.Unspecified
    override val selected = Color.Unspecified
    override val disabled = Color.Unspecified
    override val subtle = Color.Unspecified
    override val subtleActive = Color.Unspecified
    override val inverted = Color.Unspecified
    override val invertedActive = Color.Unspecified
    override val primary = Color.Unspecified
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
    override val box: WarpBoxColors = PlaceholderBoxColors
    override val stepIndicator: WarpStepIndicatorColors = PlaceholderStepIndicatorColors
    override val alert: WarpAlertColors = PlaceholderAlertColors
    override val textField: WarpTextFieldColors = PlaceholderTextFiledColors
    override val tabRow: WarpTabRowColors = PlaceholderTabRow
    override val tab: WarpTabColors = PlaceholderTab
    override val label: WarpLabelColors = PlaceholderLabelColors
    override val callout: WarpCalloutColors = PlaceholderCalloutColors
}

private object PlaceholderCalloutColors : WarpCalloutColors {
    override val background: Color = Color.Unspecified
    override val border: Color = Color.Unspecified
    override val text: Color = Color.Unspecified
}

private object PlaceholderLabelColors : WarpLabelColors {
    override val text: Color = Color.Unspecified
    override val negative: Color = Color.Unspecified
    override val optional: Color = Color.Unspecified
    override val icon: Color = Color.Unspecified
}

private object PlaceholderBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = Color.Unspecified
    override val infoText: Color = Color.Unspecified
    override val positiveBackground: Color = Color.Unspecified
    override val positiveText: Color = Color.Unspecified
    override val warningBackground: Color = Color.Unspecified
    override val warningText: Color = Color.Unspecified
    override val negativeBackground: Color = Color.Unspecified
    override val negativeText: Color = Color.Unspecified
    override val disabledBackground: Color = Color.Unspecified
    override val disabledText: Color = Color.Unspecified
    override val neutralBackground: Color = Color.Unspecified
    override val neutralText: Color = Color.Unspecified

}
private object PlaceholderButtonColors : WarpButtonColors {
    override val primary: WarpButtonStyleColors = PlaceholderButtonStyle
    override val secondary: WarpButtonStyleColors = PlaceholderButtonStyle
    override val disabled: WarpButtonStyleColors = PlaceholderButtonStyle
    override val quiet: WarpButtonStyleColors = PlaceholderButtonStyle
    override val negative: WarpButtonStyleColors = PlaceholderButtonStyle
    override val negativeQuiet: WarpButtonStyleColors = PlaceholderButtonStyle
    override val utility: WarpButtonStyleColors = PlaceholderButtonStyle
    override val utilityQuiet: WarpButtonStyleColors = PlaceholderButtonStyle
    override val utilityOverlay: WarpButtonStyleColors = PlaceholderButtonStyle
    override val loading: WarpButtonStyleColors = PlaceholderButtonStyle
}

private object PlaceholderButtonStyle : WarpButtonStyleColors {
    override val text: Color = Color.Unspecified
    override val background: WarpButtonElementColors = PlaceholderElementColors
    override val border: WarpButtonElementColors = PlaceholderElementColors
}

private object PlaceholderElementColors : WarpButtonElementColors {
    override val default: Color = Color.Unspecified
    override val active: Color = Color.Unspecified
}

private object PlaceholderBoxColors : WarpBoxColors {
    override val neutral: WarpComponentBackground = WarpComponentBackground(Color.Unspecified, Color.Unspecified)
    override val info: WarpComponentBackground = WarpComponentBackground(Color.Unspecified, Color.Unspecified)
    override val bordered: WarpComponentBackground = WarpComponentBackground(Color.Unspecified, Color.Unspecified)
}

private object PlaceholderStepIndicatorColors : WarpStepIndicatorColors {
    override val background: Color = Color.Unspecified
    override val backgroundActive: Color = Color.Unspecified
    override val backgroundTrack: Color = Color.Unspecified
    override val backgroundTrackActive: Color = Color.Unspecified
    override val border: Color = Color.Unspecified
    override val borderActive: Color = Color.Unspecified
    override val icon: Color = Color.Unspecified
}

private object PlaceholderAlertColors : WarpAlertColors {
    override val info: WarpAlertStyleColors = PlaceholderAlertStyle
    override val positive: WarpAlertStyleColors = PlaceholderAlertStyle
    override val negative: WarpAlertStyleColors = PlaceholderAlertStyle
    override val warning: WarpAlertStyleColors = PlaceholderAlertStyle
}

private object PlaceholderAlertStyle : WarpAlertStyleColors {
    override val background: Color = Color.Unspecified
    override val leftBorder: Color = Color.Unspecified
    override val border: Color = Color.Unspecified
}

private object PlaceholderTextFiledColors : WarpTextFieldColors {
    override val text: Color = Color.Unspecified
    override val hintText: Color = Color.Unspecified
    override val disabledText: Color = Color.Unspecified
    override val errorText: Color = Color.Unspecified
    override val readOnlyText: Color = Color.Unspecified
    override val errorIconColor: Color = Color.Unspecified
    override val background: Color = Color.Unspecified
    override val disabledBackground: Color = Color.Unspecified
    override val border: Color = Color.Unspecified
    override val activeBorder: Color = Color.Unspecified
    override val disabledBorder: Color = Color.Unspecified
    override val errorBorder: Color = Color.Unspecified
    override val caret: Color = Color.Unspecified
    override val iconTint: Color = Color.Unspecified
}

private object PlaceholderTabRow : WarpTabRowColors {
    override val container: Color = Color.Unspecified
    override val content: Color = Color.Unspecified
    override val indicator: Color = Color.Unspecified
}

private object PlaceholderTab : WarpTabColors {
    override val selectedContent: Color = Color.Unspecified
    override val unselectedContent: Color = Color.Unspecified
}

internal object PlaceholderResources : WarpResources {
    override val logo: Int = 0
}

internal object PlaceholderShapes : WarpShapes {
    override val medium: Shape = CircleShape
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