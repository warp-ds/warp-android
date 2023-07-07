package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.StepIndicatorState
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpStepIndicator
import com.schibsted.nmp.warp.components.WarpStepIndicatorOrientation
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider

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
    var wState by remember { mutableStateOf(StepIndicatorState(5, 0)) }
    var hState by remember { mutableStateOf(StepIndicatorState(3, 0)) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Horizontal", style = WarpTheme.typography.title3)
        WarpStepIndicator(
            modifier = Modifier.fillMaxWidth(),
            state = hState,
            onStepClicked = {hState = hState.copy(activeStep = it)},
            stepTitle = { Text("Step $it", style = WarpTheme.typography.title4) }
        ) {
            Text("Description", style = WarpTheme.typography.detail)
        }

        Text(modifier = Modifier.padding(top = 24.dp), text = "Vertical", style = WarpTheme.typography.title3)
        WarpStepIndicator(
            modifier = Modifier.fillMaxWidth(),
            orientation = WarpStepIndicatorOrientation.Vertical,
            state = wState,
            onStepClicked = { wState = wState.copy(activeStep = it)},
            stepTitle = { Text(modifier = Modifier.padding(start = 16.dp), text = "Step $it", style = WarpTheme.typography.title4) }
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
                Text("Here we can have some text and maybe something else", style = WarpTheme.typography.body)
                if (wState.activeStep == it) {
                    WarpButton(
                        modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                        onClick = { wState = wState.copy(activeStep = it + 1) },
                        buttonStyle = WarpButtonStyle.Primary,
                        text = "Finish step"
                    )
                } else {
                    WarpButton(
                        modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                        onClick = { wState = wState.copy(activeStep = it) },
                        buttonStyle = WarpButtonStyle.Primary,
                        text = "Reset")
                }
            }

        }

    }
}

@Composable
@Preview
private fun WarpStepIndicatorScreenPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    WarpBrandedTheme(
        flavor = flavor,
        darkTheme = isSystemInDarkTheme()
    ) {
        StepIndicatorScreenContent()
    }
}