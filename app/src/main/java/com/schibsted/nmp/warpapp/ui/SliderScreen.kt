package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpSlider
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import kotlin.math.roundToInt

@Composable
fun SliderScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpSlider",
        onUp = onUp
    ) {
        SliderScreenContent()
    }
}

@Composable
fun SliderScreenContent() {
    Column(
        modifier = Modifier
            .padding(vertical = dimensions.space7, horizontal = dimensions.space2),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var value by remember { mutableFloatStateOf(33f) }
        val values = 0f..100f
        WarpText("Slider with tooltip")
        WarpSlider(
            modifier = Modifier.padding(bottom = dimensions.space4),
            value = value,
            onValueChange = { value = it },
            valueRange = values,
            showTooltip = true,
            formattedTooltipText = "${value.roundToInt()}"
            )

        WarpText("Regular Slider with range")

        WarpSlider(
            modifier = Modifier.padding(horizontal = dimensions.space2),
            value = value,
            onValueChange = { value = it },
            valueRange = values,
            showRange = true,
            rangeStartText = values.start.roundToInt().toString(),
            rangeEndText = values.endInclusive.roundToInt().toString(),
            showTooltip = false
        )
    }
}
