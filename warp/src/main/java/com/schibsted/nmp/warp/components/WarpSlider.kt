package com.schibsted.nmp.warp.components

import androidx.annotation.IntRange
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.HapticFeedbackConstantsCompat
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A Slider component in the Warp design system.
 * @param modifier Modifier for the slider. Default value is Modifier
 * @param onValueChange The callback that is triggered when the value is changed. Returns a float value.
 * @param value The current value of the slider. Default value is 0f
 * @param valueRange The range of the slider. Default value is 0f..1f
 * @param enabled Whether the slider is enabled. Default value is true
 * @param onValueChangeFinished The callback that is triggered when the value change is finished. Default value is null
 * @param showRange Whether to show the range of the slider as text below the track. Default value is false
 * @param rangeStartText The text to be displayed on the start of the range. Default value is null, but if showRange is true, it will be the first value of the range using its toString() method
 * @param rangeEndText The text to be displayed on the end of the range. Default value is null, but if showRange is true, it will be the last value of the range using its toString() method
 * @param showTooltip Whether to show the tooltip. Default value is true
 * @param formattedTooltipText The text to be displayed in the tooltip. Default value is null, but if showTooltip is true, it will be the current value of the slider using its toString() method
 * @param steps The number of steps the slider can have. Default value is 0
 * @param interactionSource The interaction source for the slider. Default value is remember { MutableInteractionSource() }
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpSlider(
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
    value: Float = 0f,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    enabled: Boolean = true,
    onValueChangeFinished: (() -> Unit)? = null,
    showRange: Boolean = false,
    rangeStartText: String? = null,
    rangeEndText: String? = null,
    showTooltip: Boolean = true,
    formattedTooltipText: String? = null,
    @IntRange(from = 0) steps: Int = 0,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val tooltipState = remember { WarpTooltipState(false) }
    if (showTooltip) {
        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> tooltipState.isVisible = true
                    is PressInteraction.Release -> tooltipState.isVisible = false
                    is PressInteraction.Cancel -> tooltipState.isVisible = false
                    is DragInteraction.Start -> tooltipState.isVisible = true
                    is DragInteraction.Stop -> tooltipState.isVisible = false
                    is DragInteraction.Cancel -> tooltipState.isVisible = false
                }
            }
        }
    }
    val rippleConfiguration =
        RippleConfiguration(color = Color.Black, rippleAlpha = RippleAlpha(0.1f, 0f, 0f, 0.2f))
    CompositionLocalProvider(
        LocalRippleConfiguration provides rippleConfiguration
    ) {
        require(valueRange.endInclusive > valueRange.start)

        val view = LocalView.current
        Slider(
            modifier = modifier,
            value = value,
            onValueChange = {
                view.performHapticFeedback(HapticFeedbackConstantsCompat.CLOCK_TICK)
                onValueChange.invoke(it)
            },
            enabled = enabled,
            onValueChangeFinished = onValueChangeFinished,
            valueRange = valueRange,
            interactionSource = interactionSource,
            colors = warpSliderColors,
            steps = steps,
            track = { sliderState ->
                val fraction by remember {
                    derivedStateOf {
                        (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start)
                    }
                }
                Box(Modifier.fillMaxWidth()) {
                    Box(
                        Modifier
                            .fillMaxWidth(fraction)
                            .align(Alignment.CenterStart)
                            .height(dimensions.components.slider.activeTrackHeight)
                            .background(
                                if (enabled) warpSliderColors.activeTrackColor else warpSliderColors.disabledActiveTrackColor,
                                CircleShape
                            )
                    )
                    Box(
                        Modifier
                            .fillMaxWidth(1f - fraction)
                            .align(Alignment.CenterEnd)
                            .height(dimensions.components.slider.inactiveTrackHeight)
                            .background(
                                if (enabled) warpSliderColors.inactiveTrackColor else warpSliderColors.disabledInactiveTrackColor,
                                CircleShape
                            )
                    )
                    if (showRange) {
                        val startText =
                            rangeStartText ?: "${sliderState.valueRange.start}"
                        val endText =
                            rangeEndText ?: "${sliderState.valueRange.endInclusive}"
                        Row(
                            modifier = Modifier
                                .padding(top = dimensions.space8)
                                .fillMaxWidth()
                        ) {
                            WarpText(
                                text = startText,
                                color = if (enabled) colors.text.default else colors.text.disabled
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            WarpText(
                                text = endText,
                                color = if (enabled) colors.text.default else colors.text.disabled
                            )
                        }
                    }
                }
            },
            thumb = { sliderState ->
                val tooltipModifier = if (showTooltip) {
                    Modifier.size(dimensions.components.slider.tooltipWidth)
                } else Modifier
                Box(
                    modifier = tooltipModifier,
                    contentAlignment = Alignment.Center
                ) {
                    Spacer(
                        Modifier
                            .size(dimensions.components.slider.thumbSize)
                            .indication(
                                interactionSource = interactionSource,
                                indication = ripple(
                                    bounded = false,
                                    radius = 45.dp / 2
                                )
                            )
                            .background(
                                if (enabled) warpSliderColors.thumbColor else warpSliderColors.disabledThumbColor,
                                WarpTheme.shapes.ellipse
                            )
                    )
                    if (showTooltip) {
                        WarpTooltip(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .offset(y = (-dimensions.components.slider.thumbSize) - dimensions.space05),
                            state = tooltipState,
                            text = formattedTooltipText ?: "${sliderState.value}",
                            edge = Edge.Bottom,
                            autoCenter = true,
                            inline = true
                        )
                    }
                }
            },
        )
    }
}

internal val warpSliderColors: SliderColors
    @Composable
    get() {
        return SliderColors(
            thumbColor = colors.background.primary,
            activeTrackColor = colors.background.primary,
            activeTickColor = colors.background.primary,
            inactiveTrackColor = colors.background.disabledSubtle,
            inactiveTickColor = colors.background.disabledSubtle,
            disabledThumbColor = colors.background.disabledSubtle,
            disabledActiveTrackColor = colors.background.disabledSubtle,
            disabledActiveTickColor = colors.background.disabledSubtle,
            disabledInactiveTrackColor = colors.background.disabledSubtle,
            disabledInactiveTickColor = colors.background.disabledSubtle
        )
    }
