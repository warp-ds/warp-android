package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.WarpSlider
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpSliderTest(
    @TestParameter val flavor: Flavor,
    @TestParameter val nightMode: NightMode,
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            nightMode = nightMode
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
    fun warp_slider() {
        val values = 0f..100f
        warpRangeSlider(values)

    }

    private fun warpRangeSlider(items: ClosedFloatingPointRange<Float>) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.background.default)
                        .padding(dimensions.space4),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(dimensions.space3)
                ) {
                    //Regular
                    WarpSlider(value = 0f, onValueChange = {}, valueRange = items)
                    //Value selected
                    WarpSlider(value = 33f, onValueChange = {}, valueRange = items)
                    //Max value selected
                    WarpSlider(value = items.endInclusive, onValueChange = {}, valueRange = items)
                    //Range indicators
                    WarpSlider(
                        value = 33f,
                        onValueChange = {},
                        valueRange = items,
                        showRange = true
                    )

                    //All of the above but disabled
                    WarpSlider(value = 0f, onValueChange = {}, valueRange = items, enabled = false)
                    //Value selected
                    WarpSlider(value = 33f, onValueChange = {}, valueRange = items, enabled = false)
                    //Max value selected
                    WarpSlider(
                        value = items.endInclusive,
                        onValueChange = {},
                        valueRange = items,
                        enabled = false
                    )
                    //Range indicators
                    WarpSlider(
                        value = 33f,
                        onValueChange = {},
                        valueRange = items,
                        showRange = true,
                        enabled = false
                    )

                }
            }
        }
    }
}