package com.schibsted.nmp.warp

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.schibsted.nmp.warp.components.HorizontalWarpStepIndicator
import com.schibsted.nmp.warp.components.VerticalWarpStepIndicator
import com.schibsted.nmp.warp.components.WarpStep
import com.schibsted.nmp.warp.components.WarpStepIndicatorType
import com.schibsted.nmp.warp.components.WarpStepState
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.linearStepStates
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WarpStepIndicatorStateSemanticsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private fun indicatorNodes() =
        composeTestRule
            .onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsProperties.StateDescription))
            .fetchSemanticsNodes()

    private fun stateDescriptions(): List<String> =
        indicatorNodes().mapNotNull { it.config.getOrNull(SemanticsProperties.StateDescription) }

    private fun position(step: Int, steps: Int) =
        context.getString(R.string.step_indicator_position, step, steps)

    // A linear journey: steps before the active one are complete, the active one is in progress
    private fun linearSteps(
        count: Int,
        activeStep: Int = 0,
        title: ((Int) -> String)? = null,
        description: ((Int) -> String)? = null
    ) = linearStepStates(count, activeStep).mapIndexed { i, state ->
        WarpStep(state = state, title = title?.invoke(i), description = description?.invoke(i))
    }

    @Test
    fun horizontal_nonClickableStepsAnnounceTheirState() {
        composeTestRule.setContent {
            HorizontalWarpStepIndicator(
                steps = listOf(
                    WarpStepState.Complete,
                    WarpStepState.Error,
                    WarpStepState.InProgress,
                    WarpStepState.Incomplete
                ).mapIndexed { i, state -> WarpStep(state = state, title = "Step $i") }
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
    fun vertical_clickableStepsAnnouncePositionAndDerivedState() {
        composeTestRule.setContent {
            VerticalWarpStepIndicator(
                steps = linearSteps(3, activeStep = 1, title = { "Step $it" }),
                onStepClicked = {},
                stepContent = {}
            )
        }

        assertEquals(
            listOf(
                listOf(position(1, 3)) to context.getString(R.string.step_indicator_state_complete),
                listOf(position(2, 3)) to context.getString(R.string.step_indicator_state_in_progress),
                listOf(position(3, 3)) to context.getString(R.string.step_indicator_state_incomplete),
            ),
            indicatorNodes().map {
                it.config.getOrNull(SemanticsProperties.ContentDescription) to
                    it.config.getOrNull(SemanticsProperties.StateDescription)
            }
        )
    }

    @Test
    fun horizontal_nonClickableStepsAnnounceTheirPosition() {
        composeTestRule.setContent {
            HorizontalWarpStepIndicator(
                steps = linearSteps(3, activeStep = 1),
                type = WarpStepIndicatorType.Numbered
            )
        }

        assertEquals(
            listOf(listOf(position(1, 3)), listOf(position(2, 3)), listOf(position(3, 3))),
            indicatorNodes().map { it.config.getOrNull(SemanticsProperties.ContentDescription) }
        )
    }

    // What screen readers read, in order: the texts of each label and content group, and the
    // state of each indicator
    private fun readingOrder(): List<String> =
        composeTestRule
            .onAllNodes(
                SemanticsMatcher.keyIsDefined(SemanticsProperties.TraversalIndex),
                useUnmergedTree = true
            )
            .fetchSemanticsNodes()
            .sortedBy { it.config[SemanticsProperties.TraversalIndex] }
            .map { node ->
                node.config.getOrNull(SemanticsProperties.StateDescription)
                    ?: node.children
                        .flatMap { it.config.getOrNull(SemanticsProperties.Text).orEmpty() }
                        .joinToString(" ")
            }

    @Test
    fun horizontal_readsEachIndicatorBeforeItsLabel() {
        composeTestRule.setContent {
            HorizontalWarpStepIndicator(
                steps = linearSteps(
                    2,
                    title = { "Title $it" },
                    description = { "Description $it" }
                )
            )
        }

        assertEquals(
            listOf(
                context.getString(R.string.step_indicator_state_in_progress),
                "Title 0 Description 0",
                context.getString(R.string.step_indicator_state_incomplete),
                "Title 1 Description 1",
            ),
            readingOrder()
        )
        composeTestRule.onAllNodesWithText("Title", substring = true).assertCountEquals(2)
    }

    @Test
    fun vertical_readsIndicatorLabelAndContentOfEachStep() {
        composeTestRule.setContent {
            VerticalWarpStepIndicator(
                steps = linearSteps(2, title = { "Title $it" }),
                stepContent = { WarpText("Content $it") }
            )
        }

        assertEquals(
            listOf(
                context.getString(R.string.step_indicator_state_in_progress),
                "Title 0",
                "Content 0",
                context.getString(R.string.step_indicator_state_incomplete),
                "Title 1",
                "Content 1",
            ),
            readingOrder()
        )
    }

    @Test
    fun clickableStepsDoNotRepeatTheirTitle() {
        composeTestRule.setContent {
            HorizontalWarpStepIndicator(
                steps = linearSteps(2, title = { "Title $it" }),
                onStepClicked = {}
            )
        }

        assertEquals(
            listOf(listOf(position(1, 2)), listOf(position(2, 2))),
            indicatorNodes().map { it.config.getOrNull(SemanticsProperties.ContentDescription) }
        )
    }

    @Test
    fun nonClickableStepsAnnounceOnlyTheirPosition() {
        composeTestRule.setContent {
            HorizontalWarpStepIndicator(
                steps = linearSteps(2, title = { "Title $it" })
            )
        }

        assertEquals(
            listOf(listOf(position(1, 2)), listOf(position(2, 2))),
            indicatorNodes().map { it.config.getOrNull(SemanticsProperties.ContentDescription) }
        )
    }

    @Test
    fun horizontal_stepsAfterHighestEnabledStepAreDisabled() {
        composeTestRule.setContent {
            HorizontalWarpStepIndicator(
                steps = linearSteps(4, activeStep = 1),
                highestEnabledStep = 1,
                onStepClicked = {}
            )
        }

        assertEquals(
            listOf(false, false, true, true),
            indicatorNodes().map { SemanticsProperties.Disabled in it.config }
        )
    }

    @Test
    fun vertical_stepsAfterHighestEnabledStepAreDisabled() {
        composeTestRule.setContent {
            VerticalWarpStepIndicator(
                steps = linearSteps(4, activeStep = 1),
                highestEnabledStep = 2,
                onStepClicked = {},
                stepContent = {}
            )
        }

        assertEquals(
            listOf(false, false, false, true),
            indicatorNodes().map { SemanticsProperties.Disabled in it.config }
        )
    }

    @Test
    fun nonClickableStepsAreNotDisabled() {
        composeTestRule.setContent {
            HorizontalWarpStepIndicator(steps = linearSteps(3), highestEnabledStep = 0)
        }

        assertFalse(indicatorNodes().any { SemanticsProperties.Disabled in it.config })
    }
}
