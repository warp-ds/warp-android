package com.schibsted.nmp.warp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.nmp.warp.components.HorizontalWarpStepIndicator
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.Placeholders
import com.schibsted.nmp.warp.theme.WarpBackgroundColors
import com.schibsted.nmp.warp.theme.WarpBorderColors
import com.schibsted.nmp.warp.theme.WarpColors
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WarpStepIndicatorAnimationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun nextStepIsNeverFilledWhileAnimatingTowardsIt() {
        var activeStep by mutableIntStateOf(0)
        composeTestRule.setContent {
            CompositionLocalProvider(LocalColors provides TestColors) {
                // The glitch only shows on an opaque background drawn in the same layer, like a screen
                Box(Modifier.background(BACKGROUND)) {
                    HorizontalWarpStepIndicator(steps = 3, activeStep = activeStep)
                }
            }
        }

        val indicator = composeTestRule
            .onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsProperties.StateDescription))
            .fetchSemanticsNodes()[1]
            .boundsInRoot

        composeTestRule.mainClock.autoAdvance = false
        activeStep = 1

        repeat(FRAMES) { frame ->
            composeTestRule.mainClock.advanceTimeByFrame()
            val center = composeTestRule.onRoot().captureToImage().asAndroidBitmap()
                .getPixel(indicator.center.x.toInt(), indicator.center.y.toInt())
            assertNotEquals("Indicator filled at frame $frame", PRIMARY.toArgb(), center)
        }
    }
}

private const val FRAMES = 60
private val PRIMARY = Color.Blue
private val BACKGROUND = Color.White

private object TestColors : WarpColors by Placeholders {
    override val background = object : WarpBackgroundColors by Placeholders.background {
        override val primary = PRIMARY
    }
    override val border = object : WarpBorderColors by Placeholders.border {
        override val default = Color.Gray
        override val primary = PRIMARY
    }
}
