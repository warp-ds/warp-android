package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.schibsted.nmp.warp.components.WarpStep
import com.schibsted.nmp.warp.components.WarpStepIndicatorType
import com.schibsted.nmp.warp.components.WarpStepState
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.colors
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
    var hState by remember { mutableIntStateOf(0) }
    var nState by remember { mutableIntStateOf(1) }
    var eState by remember { mutableIntStateOf(0) }
    var eFurthest by remember { mutableIntStateOf(0) }
    var wState by remember { mutableIntStateOf(0) }
    var wFurthest by remember { mutableIntStateOf(0) }
    val labels = listOf("Start", "Profile", "Payment", "Add-ons", "Thanks!")
    val descriptions = listOf(
        "Start your journey here",
        "Something about you",
        "Nothing is for free",
        "Extra everything?",
        "Have a nice day!"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(dimensions.space5)
    ) {
        StepIndicatorDemoSection(
            title = "Horizontal",
            description = "Steps with a title and a description. Tap any step to make it the " +
                "active one, every step is clickable by default."
        ) {
            HorizontalWarpStepIndicator(
                steps = linearSteps(activeStep = hState, titles = labels.take(3), descriptions = descriptions),
                onStepClicked = { hState = it }
            )
        }

        StepIndicatorDemoSection(
            title = "Numbered",
            description = "type = Numbered shows the step number in steps that are not complete. " +
                "Screen readers announce the position of every step, numbered or not."
        ) {
            HorizontalWarpStepIndicator(
                steps = linearSteps(activeStep = nState, titles = labels.take(4)),
                type = WarpStepIndicatorType.Numbered,
                onStepClicked = { nState = it }
            )
        }

        StepIndicatorDemoSection(
            title = "Error",
            description = "A step in error can sit anywhere in the journey. The track after it is " +
                "not filled. Without onStepClicked, no step is clickable."
        ) {
            HorizontalWarpStepIndicator(
                steps = listOf(
                    WarpStep(state = WarpStepState.Complete, title = labels[0]),
                    WarpStep(state = WarpStepState.Error, title = labels[1]),
                    WarpStep(state = WarpStepState.InProgress, title = labels[2]),
                    WarpStep(state = WarpStepState.Incomplete, title = labels[3])
                )
            )
        }

        StepIndicatorDemoSection(
            title = "Disabled steps",
            description = "highestEnabledStep limits which steps are clickable. Use Next to move " +
                "on and Back to return: steps up to the furthest one reached can be tapped, later " +
                "steps are disabled and announced as disabled by screen readers."
        ) {
            HorizontalWarpStepIndicator(
                steps = linearSteps(activeStep = eState, titles = labels.take(4)),
                highestEnabledStep = eFurthest,
                onStepClicked = { eState = it }
            )
            Row(
                modifier = Modifier.padding(top = dimensions.space2),
                horizontalArrangement = Arrangement.spacedBy(dimensions.space1)
            ) {
                WarpButton(
                    text = "Back",
                    onClick = { eState-- },
                    enabled = eState > 0,
                    style = WarpButtonStyle.Secondary
                )
                WarpButton(
                    text = "Next",
                    onClick = {
                        eState++
                        eFurthest = maxOf(eFurthest, eState)
                    },
                    enabled = eState < 4,
                    style = WarpButtonStyle.Primary
                )
            }
        }

        StepIndicatorDemoSection(
            title = "Vertical with content",
            description = "Each step has content below its title. Finish step moves on and Reset " +
                "returns to an earlier step. Like above, steps up to the furthest one reached can " +
                "be tapped and later steps are disabled."
        ) {
            VerticalWarpStepIndicator(
                modifier = Modifier.fillMaxWidth(),
                steps = linearSteps(activeStep = wState, titles = labels, descriptions = descriptions),
                highestEnabledStep = wFurthest,
                onStepClicked = { wState = it }
            ) {
                Column(modifier = Modifier.padding(top = dimensions.space1)) {
                    WarpText(
                        "Custom content! Maybe some text and maybe something else?",
                        style = WarpTextStyle.Body
                    )
                    if (wState == it) {
                        WarpButton(
                            modifier = Modifier.padding(top = dimensions.space1),
                            onClick = {
                                wState++
                                wFurthest = maxOf(wFurthest, wState)
                            },
                            style = WarpButtonStyle.Primary,
                            text = "Finish step"
                        )
                    } else if (it < wState) {
                        WarpButton(
                            modifier = Modifier.padding(top = dimensions.space1),
                            onClick = { wState = it },
                            style = WarpButtonStyle.Primary,
                            text = "Reset"
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StepIndicatorDemoSection(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        WarpText(
            text = title,
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(bottom = dimensions.space1)
        )
        WarpText(
            text = description,
            style = WarpTextStyle.Body,
            color = colors.text.subtle,
            modifier = Modifier.padding(bottom = dimensions.space2)
        )
        content()
    }
}

// A linear journey: steps before the active one are complete, the active one is in progress
private fun linearSteps(
    activeStep: Int,
    titles: List<String>,
    descriptions: List<String> = emptyList()
) = titles.mapIndexed { i, title ->
    WarpStep(
        state = when {
            i < activeStep -> WarpStepState.Complete
            i == activeStep -> WarpStepState.InProgress
            else -> WarpStepState.Incomplete
        },
        title = title,
        description = descriptions.getOrNull(i)
    )
}

@Composable
@Preview
private fun WarpStepIndicatorScreenPreview() {
    StepIndicatorScreenContent()
}