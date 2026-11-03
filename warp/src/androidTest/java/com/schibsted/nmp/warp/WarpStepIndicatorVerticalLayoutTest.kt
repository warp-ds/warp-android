package com.schibsted.nmp.warp

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.nmp.warp.components.VerticalWarpStepIndicator
import com.schibsted.nmp.warp.components.WarpStep
import com.schibsted.nmp.warp.components.linearStepStates
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WarpStepIndicatorVerticalLayoutTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun stepsShorterThanTheIndicator_doNotOverlap() {
        composeTestRule.setContent {
            VerticalWarpStepIndicator(
                steps = linearStepStates(steps = 3, activeStep = 0).mapIndexed { i, state ->
                    WarpStep(state = state, title = "Step $i")
                },
                stepContent = {}
            )
        }

        val indicators = composeTestRule
            .onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsProperties.StateDescription))
            .fetchSemanticsNodes()
            .map { it.boundsInRoot }

        indicators.zipWithNext().forEach { (current, next) ->
            assertTrue("$next overlaps $current", next.top >= current.bottom)
        }
    }
}
