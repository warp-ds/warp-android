package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
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
import com.schibsted.nmp.warp.components.WarpCheckbox
import com.schibsted.nmp.warp.components.WarpCheckboxGroup
import com.schibsted.nmp.warp.components.WarpCheckboxStyle
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpCheckboxTest(
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
    fun warp_checkbox_default() {
        warpCheckbox(WarpCheckboxStyle.Default)
    }

    @Test
    fun warp_checkbox_disabled() {
        warpCheckbox(WarpCheckboxStyle.Disabled)
    }

    @Test
    fun warp_checkbox_negative() {
        warpCheckbox(WarpCheckboxStyle.Negative)
    }

    @Test
    fun warp_checkboxGroup_default() {
        warpCheckboxGroup(WarpCheckboxStyle.Default)
    }

    @Test
    fun warp_checkboxGroup_disabled() {
        warpCheckboxGroup(WarpCheckboxStyle.Disabled)
    }

    @Test
    fun warp_checkboxGroup_negative() {
        warpCheckboxGroup(WarpCheckboxStyle.Negative)
    }

    private fun warpCheckbox(style: WarpCheckboxStyle) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.surface.elevated100)
                        .padding(
                            horizontal = dimensions.space2,
                            vertical = dimensions.space2
                        ),
                ) {
                    WarpCheckbox(
                        checked = false,
                        label = style.name,
                        style = style,
                        enabled = style != WarpCheckboxStyle.Disabled
                    )
                    WarpCheckbox(
                        label = "${style.name} selected",
                        style = style,
                        checked = true,
                        enabled = style != WarpCheckboxStyle.Disabled
                    )
                    WarpCheckbox(
                        label = "${style.name} With extraText",
                        onCheckedChange = { },
                        checked = true,
                        style = style,
                        extraText = "So extra",
                        enabled = style != WarpCheckboxStyle.Disabled
                    )
                    WarpCheckbox(
                        label = "${style.name} With icon",
                        onCheckedChange = { },
                        checked = true,
                        style = style,
                        slot = {
                            Icon(
                                Icons.Filled.AccountCircle,
                                contentDescription = "Content description for the leading icon",
                                tint = colors.icon.disabled
                            )
                        },
                        enabled = style != WarpCheckboxStyle.Disabled
                    )
                    WarpCheckbox(
                        style = style,
                        enabled = style != WarpCheckboxStyle.Disabled
                    )
                }
            }
        }
    }

    private fun warpCheckboxGroup(style: WarpCheckboxStyle) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.surface.elevated100)
                        .padding(dimensions.space2)
                ) {
                    val checkboxOptions =
                        listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
                    val preSelectedOptions = listOf("Option 1", "Option 3")
                    val onOptionSelected = remember { mutableListOf(checkboxOptions) }
                    WarpCheckboxGroup(
                        title = "${style.name} Vertical",
                        helpText = "Help me",
                        orientation = Orientation.Vertical,
                        options = checkboxOptions,
                        selectedOptions = preSelectedOptions,
                        onOptionsSelected = { onOptionSelected },
                        isError = style == WarpCheckboxStyle.Negative,
                        enabled = style != WarpCheckboxStyle.Disabled
                    )
                    WarpCheckboxGroup(
                        title = "${style.name} Horizontal",
                        helpText = "Help me",
                        orientation = Orientation.Horizontal,
                        options = checkboxOptions,
                        selectedOptions = preSelectedOptions,
                        onOptionsSelected = { onOptionSelected },
                        isError = style == WarpCheckboxStyle.Negative,
                        enabled = style != WarpCheckboxStyle.Disabled
                    )
                }
            }
        }
    }
}