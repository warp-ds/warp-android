package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.HorizontalWarpStepIndicator
import com.schibsted.nmp.warp.components.WarpSpinner
import com.schibsted.nmp.warp.components.WarpSpinnerSize
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun SpinnerScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpModal",
        onUp = onUp
    ) {
        SpinnerScreenContent()
    }
}

@Composable
fun SpinnerScreenContent() {
    var sizeStep by rememberSaveable { mutableIntStateOf(0) }
    var strokeSizeStep by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2)
    ) {
        Text(text = "Default", modifier = Modifier.padding(bottom = 4.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            WarpSpinner()
        }

        Text(text = "Small", modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            WarpSpinner(size = WarpSpinnerSize.Small)
        }

        Text(text = "Large", modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            WarpSpinner(size = WarpSpinnerSize.Large)
        }

        Text(text = "Custom", modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
        HorizontalWarpStepIndicator(
            steps = 5,
            activeStep = sizeStep,
            onStepClicked = { sizeStep = it })
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 4.dp)) {
            WarpSpinner(size = WarpSpinnerSize.Custom(16.dp * (sizeStep + 1)))
        }
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp))


        Text(text = "Stroke size", modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
        HorizontalWarpStepIndicator(
            steps = 5,
            activeStep = strokeSizeStep,
            onStepClicked = { strokeSizeStep = it })
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 4.dp)) {
            WarpSpinner(strokeWidth = 2.dp * (strokeSizeStep + 1))
        }
    }
}