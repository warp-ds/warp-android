package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.WarpSelect
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpSelectTest(
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
    fun warp_select(){
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.surface.elevated100)
                        .padding(dimensions.space1),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2)
                ) {
                    val items = listOf("One", "Two", "Three", "Four", "Five", "Six")
                    WarpSelect(
                        modifier = Modifier.padding(bottom = dimensions.space3),
                        value = "",
                        onValueChange = { },
                        label = "Label",
                        placeholderText = "Select an option",
                        items = items,
                        helpText = "Help text"
                    )
                    WarpSelect(
                        modifier = Modifier.padding(bottom = dimensions.space3),
                        value = "Two",
                        onValueChange = { },
                        label = "Error",
                        placeholderText = "Select an option",
                        items = items,
                        isError = true,
                        helpText = "Help text"
                    )
                    WarpSelect(
                        modifier = Modifier.padding(bottom = dimensions.space3),
                        value = "Three",
                        onValueChange = {  },
                        label = "ReadOnly",
                        placeholderText = "Select an option",
                        items = items,
                        readOnly = true,
                        helpText = "Read only"
                    )
                    WarpSelect(
                        modifier = Modifier.padding(bottom = dimensions.space3),
                        value = "Four",
                        onValueChange = { },
                        label = "Disabled",
                        placeholderText = "Select an option",
                        items = items,
                        enabled = false,
                        helpText = "Disabled"
                    )
                }
            }
        }
    }
}