package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
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
import com.schibsted.nmp.warp.components.WarpRadio
import com.schibsted.nmp.warp.components.WarpRadioGroup
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
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
    fun warp_radio() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.surface.elevated100)
                        .padding(
                            horizontal = dimensions.space2,
                            vertical = dimensions.space2
                        ),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2)
                ) {
                    WarpRadio(label = "Single Radio on", selected = true, enabled = true)
                    WarpRadio(label = "Single Radio off", selected = false, enabled = true)
                    WarpRadio(label = "Single Radio disabled on", selected = true, enabled = false)
                    WarpRadio(
                        label = "Single Radio disabled off",
                        selected = false,
                        enabled = false
                    )
                    WarpRadio(
                        label = "Single Radio negative on",
                        selected = true,
                        enabled = true,
                        isError = true
                    )
                    WarpRadio(
                        label = "Single Radio negative off",
                        selected = false,
                        enabled = true,
                        isError = true
                    )
                    WarpRadio(
                        label = "Radio with extraText",
                        selected = true,
                        enabled = true,
                        extraText = "(Extra)"
                    )
                    WarpRadio(label = "Radio with icon", selected = true, enabled = true, slot = {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Content description for the leading icon",
                            tint = colors.icon.disabled
                        )
                    })
                }
            }
        }
    }

    @Test
    fun warp_radio_group_disabled() {
        warp_radio_group("Disabled", enabled = false, isError = false)
    }

    @Test
    fun warp_radio_group_negative() {
        warp_radio_group("Error", enabled = true, isError = true)
    }

    @Test
    fun warp_radio_group_default() {
        warp_radio_group("Default", enabled = true, isError = false)
    }

    private fun warp_radio_group(title: String, enabled: Boolean, isError: Boolean) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.surface.elevated100)
                        .padding(
                            horizontal = dimensions.space2,
                            vertical = dimensions.space2
                        ),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2)
                ) {
                    val radioOptions = listOf("One", "Two", "Three")
                    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
                    WarpRadioGroup(
                        title = title,
                        orientation = Orientation.Vertical,
                        options = radioOptions,
                        selectedOption = selectedOption,
                        enabled = enabled,
                        isError = isError,
                        onOptionSelected = onOptionSelected,
                        helpText = "Required"
                    )
                    WarpRadioGroup(
                        title = title,
                        orientation = Orientation.Horizontal,
                        options = radioOptions,
                        selectedOption = selectedOption,
                        enabled = enabled,
                        isError = isError,
                        onOptionSelected = onOptionSelected,
                        helpText = "Required"
                    )
                }
            }
        }
    }
}
