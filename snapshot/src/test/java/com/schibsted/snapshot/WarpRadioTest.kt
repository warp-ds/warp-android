package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.HorizontalWarpRadioGroup
import com.schibsted.nmp.warp.components.VerticalWarpRadioGroup
import com.schibsted.nmp.warp.components.WarpRadio
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpRadioTest(
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
        renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
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
    fun warp_radio() {
        warpRadio()
    }

    private fun warpRadio() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(com.schibsted.nmp.warp.theme.WarpTheme.colors.surface.elevated100)
                        .padding(
                            horizontal = com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2,
                            vertical = com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2
                        ),
                    verticalArrangement = Arrangement.spacedBy(com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2)
                ) {
                        WarpText(
                            text = "Default",
                            style = WarpTextStyle.Title3,
                            modifier = Modifier.semantics { heading() })
                        VerticalRadioGroupSample(enabled = true, valid = true)

                        WarpText(text = "Disabled", style = WarpTextStyle.Title3)
                        VerticalRadioGroupSample(enabled = false, valid = true)

                        WarpText(text = "Invalid", style = WarpTextStyle.Title3)
                        VerticalRadioGroupSample(enabled = true, valid = false)

                        WarpText(
                            text = "Default",
                            style = WarpTextStyle.Title3,
                            modifier = Modifier.semantics { heading() })
                        HorizontalRadioGroupSample(enabled = true, valid = true)

                        WarpText(text = "Disabled", style = WarpTextStyle.Title3)
                        HorizontalRadioGroupSample(enabled = false, valid = true)

                        WarpText(text = "Invalid", style = WarpTextStyle.Title3)
                        HorizontalRadioGroupSample(enabled = true, valid = false)
                    }
            }
        }
    }
    @Composable
    fun VerticalRadioGroupSample(enabled: Boolean, valid: Boolean) {
        val radioOptions = listOf("One", "Two")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
        VerticalWarpRadioGroup(
            options = radioOptions,
            selectedOption = selectedOption,
            enabled = enabled,
            valid = valid,
            onOptionSelected = onOptionSelected,
            helpText = if (!valid) "Required" else null
        )
    }

    @Composable
    fun HorizontalRadioGroupSample(enabled: Boolean, valid: Boolean) {
        val radioOptions = listOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
        HorizontalWarpRadioGroup(
            options = radioOptions,
            selectedOption = selectedOption,
            enabled = enabled,
            valid = valid,
            onOptionSelected = onOptionSelected,
            helpText = if (!valid) "Required" else null
        )
    }

}
