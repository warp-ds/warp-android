package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.HorizontalWarpStepIndicator
import com.schibsted.nmp.warp.components.VerticalWarpStepIndicator
import com.schibsted.nmp.warp.components.WarpStepIndicatorType
import com.schibsted.nmp.warp.components.WarpStepState
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpStepIndicatorTest(
    @TestParameter val flavor: Flavor,
    @TestParameter val nightMode: NightMode,
    @TestParameter(valuesProvider = FontScaleProvider::class) private val fontScale: Float,
) {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            nightMode = nightMode,
            fontScale = fontScale
        ),
        theme = "android:Theme.Material.Light.NoActionBar",
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
        snapshotHandler = if (Config.isVerifying) {
            SnapshotVerifier(
                maxPercentDifference = Config.maxPercentDifference,
                rootDirectory = flavor.dir
            )
        } else {
            HtmlReportWriter(snapshotRootDirectory = flavor.dir)
        }
    )

    @Test
    fun warp_step_indicator_horizontal() {
        snapshot {
            HorizontalWarpStepIndicator(
                modifier = Modifier.fillMaxWidth(),
                steps = 3,
                activeStep = 1,
                stepTitle = { titles[it] },
                stepDescription = { descriptions[it] }
            )
        }
    }

    @Test
    fun warp_step_indicator_horizontal_numbered() {
        snapshot {
            HorizontalWarpStepIndicator(
                modifier = Modifier.fillMaxWidth(),
                steps = 3,
                activeStep = 1,
                type = WarpStepIndicatorType.Numbered,
                stepTitle = { titles[it] },
                stepDescription = { descriptions[it] }
            )
        }
    }

    @Test
    fun warp_step_indicator_horizontal_error() {
        snapshot {
            HorizontalWarpStepIndicator(
                modifier = Modifier.fillMaxWidth(),
                steps = listOf(WarpStepState.Complete, WarpStepState.Error, WarpStepState.Incomplete),
                stepTitle = { titles[it] },
                stepDescription = { descriptions[it] }
            )
        }
    }

    @Test
    fun warp_step_indicator_vertical() {
        snapshot {
            VerticalWarpStepIndicator(
                modifier = Modifier.fillMaxWidth(),
                steps = listOf(
                    WarpStepState.Complete,
                    WarpStepState.Error,
                    WarpStepState.InProgress,
                    WarpStepState.Incomplete
                ),
                type = WarpStepIndicatorType.Numbered,
                stepTitle = { titles[it] },
                stepDescription = { descriptions[it] }
            ) {
                WarpText(
                    modifier = Modifier.padding(vertical = dimensions.space1),
                    text = "Step content",
                    style = WarpTextStyle.Body
                )
            }
        }
    }

    private fun snapshot(content: @Composable () -> Unit) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.background.default)
                        .padding(dimensions.space2),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2)
                ) {
                    content()
                }
            }
        }
    }
}

private val titles = listOf("Start", "Profile", "Payment", "Done")
private val descriptions = listOf("Your journey", "About you", "Pay up", "All set")
