package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
                    VerticalRadioGroupSample("Default", enabled = true, isError = false)

                    VerticalRadioGroupSample("Disabled", enabled = false, isError = false)

                    VerticalRadioGroupSample("Error", enabled = true, isError = true)

                    HorizontalRadioGroupSample("Default", enabled = true, isError = false)

                    HorizontalRadioGroupSample("Disabled", enabled = false, isError = false)

                    HorizontalRadioGroupSample("Error", enabled = true, isError = true)
                }
            }
        }
    }

    @Composable
    fun VerticalRadioGroupSample(title: String, enabled: Boolean, isError: Boolean) {
        val radioOptions = listOf("One", "Two")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
        VerticalWarpRadioGroup(
            title = title,
            options = radioOptions,
            selectedOption = selectedOption,
            enabled = enabled,
            isError = isError,
            onOptionSelected = onOptionSelected,
            helpText = "Required"
        )
    }

    @Composable
    fun HorizontalRadioGroupSample(title: String, enabled: Boolean, isError: Boolean) {
        val radioOptions = listOf("One", "Two")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
        HorizontalWarpRadioGroup(
            title = title,
            options = radioOptions,
            selectedOption = selectedOption,
            enabled = enabled,
            isError = isError,
            onOptionSelected = onOptionSelected,
            helpText = "Required"
        )
    }

}
