package com.schibsted.nmp.warp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpSlider(
    modifier: Modifier = Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
    enabled: Boolean = true,
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    /*
    showTooltip: Boolean = true,
    showRange: Boolean = false,
    ,*/
) {
    val rippleConfiguration =
        RippleConfiguration(color = Color.Black, rippleAlpha = RippleAlpha(0.1f, 0f, 0f, 0.2f))
    CompositionLocalProvider(
        LocalRippleConfiguration provides rippleConfiguration
    ) {
        Slider(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            onValueChangeFinished = onValueChangeFinished,
            interactionSource = interactionSource,
            colors = warpSliderColors,
            thumb = {
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
                            warpSliderColors.thumbColor,
                            WarpTheme.shapes.ellipse
                        )
                )
            },
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
                            .background(warpSliderColors.activeTrackColor, CircleShape)
                    )
                    Box(
                        Modifier
                            .fillMaxWidth(1f - fraction)
                            .align(Alignment.CenterEnd)
                            .height(dimensions.components.slider.inactiveTrackHeight)
                            .background(warpSliderColors.inactiveTrackColor, CircleShape)
                    )
                }
            }

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
