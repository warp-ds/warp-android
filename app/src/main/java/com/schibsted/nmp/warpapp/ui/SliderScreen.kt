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
    Column(
        modifier = Modifier
            .padding(dimensions.space3),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var value by remember { mutableFloatStateOf(.5f) }
        WarpSlider(
            value = value,
            onValueChange = { value = it })
    }
}
