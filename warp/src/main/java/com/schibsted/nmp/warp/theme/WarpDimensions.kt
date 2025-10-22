package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object WarpDimensions {
    val space025: Dp = 2.dp
    val space05: Dp = 4.dp
    val space1: Dp = 8.dp
    val space15: Dp = 12.dp
    val space2: Dp = 16.dp
    val space25: Dp = 20.dp
    val space3: Dp = 24.dp
    val space4: Dp = 32.dp
    val space5: Dp = 40.dp
    val space6: Dp = 48.dp
    val space7: Dp = 56.dp
    val space8: Dp = 64.dp
    val space10: Dp = 80.dp
    val space12: Dp = 96.dp
    val space14: Dp = 112.dp
    val space16: Dp = 128.dp

    val borderRadius1: Dp = 2.dp
    val borderRadius2: Dp = 4.dp
    val borderRadius3: Dp = 8.dp
    val borderRadius4: Dp = 16.dp
    val borderWidth1: Dp = 1.dp
    val borderWidth2: Dp = 2.dp
    val borderWidth3: Dp = 4.dp

    val shadowSmall: Dp = 2.dp
    val shadowMedium: Dp = 5.dp
    val shadowLarge: Dp = 12.dp
    val shadowXLarge: Dp = 16.dp

    val minimumTouchSize: Dp = 44.dp

    val components = WarpComponentDimensions

    val icon = WarpIconDimensions


    /**
     * Helper methods contributed by Shiva Bernhard
     */
    @Composable
    fun adaptDpToFontScale(
        baseSize: Dp,
        coerceMinSize: Dp? = null,
        coerceMaxSize: Dp? = null
    ): Dp {
        val density = LocalDensity.current
        val fontScale = density.fontScale
        val scaledBase = baseSize * fontScale
        val coercedMin = coerceMinSize?.let { scaledBase.coerceAtLeast(it) } ?: scaledBase
        val coercedMax = coerceMaxSize?.let { coercedMin.coerceAtMost(it) } ?: scaledBase
        return coercedMax
    }

}

object WarpIconDimensions {
    val small = 16.dp
    val default = 24.dp
    val large = 32.dp
}

object WarpComponentDimensions {
    val stepIndicator = StepIndicatorDimensions
    val pillIcon = 16.dp
    val smallButtonHeight = 35.dp
    val checkbox = CheckboxDimensions
    val switch = SwitchDimensions
    val radioIconSize = 20.dp
    val callout = CalloutDimensions
    val topAppBarHeight = 64.dp
    val tooltip = TooltipDimensions
    val slider = SliderDimensions
    val indicatorDotSize = 10.dp
    val textFieldHeight = 48.dp
}

object SliderDimensions {
    val thumbSize = 28.dp
    val activeTrackHeight = 6.dp
    val inactiveTrackHeight = 4.dp
    val indicatorHeight = 4.dp
    val indicatorWidth = 4.dp
    val tooltipWidth = 60.dp
}

object TooltipDimensions {
    val arrowHeight = 7.dp
    val arrowWidth = 18.dp
    val cornerRadius = 8.dp
}

object CalloutDimensions {
    val arrowHeight = 10.dp
    val arrowWidth = 20.dp
    val cornerRadius = 8.dp
}

object StepIndicatorDimensions {
    val placeholderIndicatorSize = 24.dp
    val placeholderIconSize = 16.dp
    val placeholderBorderWidth = 2.dp
    val placeholderTrackWidth = 2.dp
}

object CheckboxDimensions {
    val checkboxSize = 20.dp
    val cornerRadius = 4.dp
    val iconHeight = 16.dp
}

object SwitchDimensions {
    val height = 24.dp
    val width = 44.dp
    val thumbSize = 24.dp
    val thumbPadding = 4.dp
}

internal val LocalDimensions = staticCompositionLocalOf { WarpDimensions }