package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.HorizontalWarpStepIndicator
import com.schibsted.nmp.warp.components.VerticalWarpStepIndicator
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun StepIndicatorScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpStepIndicator",
        onUp = onUp
    ) {
        StepIndicatorScreenContent()
    }
}

@Composable
private fun StepIndicatorScreenContent() {
    var wState by remember { mutableStateOf(0) }
    var hState by remember { mutableStateOf( 0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Horizontal", style = WarpTheme.typography.title3)
        HorizontalWarpStepIndicator(
            modifier = Modifier,
            steps = 3,
            activeStep = hState,
            onStepClicked = {hState = it},
            label = { "Step $it" }
        )

        Text(modifier = Modifier.padding(top = 24.dp), text = "Vertical", style = WarpTheme.typography.title3)
        VerticalWarpStepIndicator(
            modifier = Modifier.fillMaxWidth(),
            steps = 5,
            activeStep = wState,
            onStepClicked = { wState = it},
            stepTitle = { "Step $it" }
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text("Here we can have some text and maybe something else", style = WarpTheme.typography.body)
                if (wState == it) {
                    WarpButton(
                        modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                        onClick = { wState++},
                        buttonStyle = WarpButtonStyle.Primary,
                        text = "Finish step"
                    )
                } else {
                    WarpButton(
                        modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                        onClick = { wState = it },
                        buttonStyle = WarpButtonStyle.Primary,
                        text = "Reset"
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun WarpStepIndicatorScreenPreview() {
    StepIndicatorScreenContent()
}