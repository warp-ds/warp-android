package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.components.HorizontalWarpStepIndicator
import com.schibsted.nmp.warp.components.VerticalWarpStepIndicator
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

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
    var wState by remember { mutableIntStateOf(0) }
    var hState by remember { mutableIntStateOf( 0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2)
            .verticalScroll(rememberScrollState())
    ) {
        WarpText("Horizontal", style = WarpTextStyle.Title3 )
        val labels = listOf("Start", "Profile", "Payment", "Add-ons", "Thanks!")
        val descriptions = listOf("Start your journey here", "Something about you", "Nothing is for free", "Extra everything?", "Have a nice day!")
        HorizontalWarpStepIndicator(
            modifier = Modifier,
            steps = 3,
            activeStep = hState,
            onStepClicked = {hState = it},
            stepTitle = { labels[it] },
            stepDescription = { descriptions[it] }
        )

        WarpText(modifier = Modifier.padding(top = dimensions.space3), text = "Vertical", style = WarpTextStyle.Title3)
        VerticalWarpStepIndicator(
            modifier = Modifier.fillMaxWidth(),
            steps = 5,
            activeStep = wState,
            onStepClicked = { wState = it},
            stepTitle = { labels[it] },
            stepDescription = { descriptions[it] }
        ) {
            Column(modifier = Modifier.padding(vertical = dimensions.space1)) {
                WarpText("Custom content! Maybe some text and maybe something else?", style = WarpTextStyle.Body)
                if (wState == it) {
                    WarpButton(
                        modifier = Modifier.padding(top = dimensions.space1, bottom = dimensions.space3),
                        onClick = { wState++},
                        buttonStyle = WarpButtonStyle.Primary,
                        text = "Finish step"
                    )
                } else {
                    WarpButton(
                        modifier = Modifier.padding(top = dimensions.space1, bottom = dimensions.space3),
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