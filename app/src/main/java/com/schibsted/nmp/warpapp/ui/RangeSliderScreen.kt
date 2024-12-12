package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.SliderValue
import com.schibsted.nmp.warp.components.WarpRangeSlider
import com.schibsted.nmp.warp.components.WarpRangeSliderState
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
@ExperimentalMaterial3Api
fun RangeSliderScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpRangeSlider",
        onUp = onUp
    ) {
        RangeSliderScreenContent()
    }
}
@ExperimentalMaterial3Api
@Composable
fun RangeSliderScreenContent() {
    val WarpRangeSliderState: WarpRangeSliderState = WarpRangeSliderState(
        initialStartItem = SliderValue(2),
        initialEndItem = SliderValue(17),
        items = listOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20),
        resetAtStartTerminal = true,
        resetAtEndTerminal = false,
        onLeftValueChanged = {

        },
        onRightValueChanged = {

        },
        onValueChangeFinished = {

        }
    )
    Column(
        modifier = Modifier
            .padding(dimensions.space2)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WarpRangeSlider(
            state = WarpRangeSliderState,
            blockDrag = false,
        )

        WarpRangeSlider(
            state = WarpRangeSliderState,
            enabled = false,
            blockDrag = false,
        )
    }
}