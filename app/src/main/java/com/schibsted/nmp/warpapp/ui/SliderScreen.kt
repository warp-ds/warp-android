package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpSlider
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

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
    val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
    Column(
        modifier = Modifier
            .padding(dimensions.space3),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var from by remember { mutableStateOf(items.first().toString()) }
        WarpText(
            "Slider with integers",
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(bottom = dimensions.space1)
        )
        WarpText("From $from")
        WarpSlider(
            showTooltip = false,
            items = items,
            onValueChanged = {
                from = it.toString()
            },
            onValueChangeFinished = {

            },
            showRange = false
        )
    }
}
