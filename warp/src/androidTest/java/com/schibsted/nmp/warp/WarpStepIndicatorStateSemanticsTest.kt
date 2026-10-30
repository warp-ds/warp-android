package com.schibsted.nmp.warp

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.nmp.warp.components.HorizontalWarpStepIndicator
import com.schibsted.nmp.warp.components.VerticalWarpStepIndicator
import com.schibsted.nmp.warp.components.WarpStepState
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WarpStepIndicatorStateSemanticsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private fun stateDescriptions(): List<String> =
        composeTestRule
            .onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsProperties.StateDescription))
            .fetchSemanticsNodes()
            .mapNotNull { it.config.getOrNull(SemanticsProperties.StateDescription) }

    @Test
    fun horizontal_nonClickableStepsAnnounceTheirState() {
        composeTestRule.setContent {
            HorizontalWarpStepIndicator(
                steps = listOf(
                    WarpStepState.Complete,
                    WarpStepState.Error,
                    WarpStepState.InProgress,
                    WarpStepState.Incomplete
                ),
                stepTitle = { "Step $it" }
            )
        }

        assertEquals(
            listOf(
                context.getString(R.string.step_indicator_state_complete),
                context.getString(R.string.step_indicator_state_error),
                context.getString(R.string.step_indicator_state_in_progress),
                context.getString(R.string.step_indicator_state_incomplete),
            ),
            stateDescriptions()
        )
    }

    @Test
    fun vertical_clickableStepsAnnounceDescriptionAndDerivedState() {
        composeTestRule.setContent {
            VerticalWarpStepIndicator(
                steps = 3,
                activeStep = 1,
                onStepClicked = {},
                stepContentDescription = { "Step $it" },
                stepContent = {}
            )
        }

        val nodes = composeTestRule
            .onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsProperties.StateDescription))
            .fetchSemanticsNodes()

        assertEquals(
            listOf(
                listOf("Step 0") to context.getString(R.string.step_indicator_state_complete),
                listOf("Step 1") to context.getString(R.string.step_indicator_state_in_progress),
                listOf("Step 2") to context.getString(R.string.step_indicator_state_incomplete),
            ),
            nodes.map {
                it.config.getOrNull(SemanticsProperties.ContentDescription) to
                    it.config.getOrNull(SemanticsProperties.StateDescription)
            }
        )
    }
}
