package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.schibsted.nmp.warp.components.WarpRangeSlider
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpRangeSliderTest(
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
    fun warp_range_slider_int() {
        val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        warpRangeSlider(items, startItem = 7, endItem = 17)
    }

    @Test
    fun warp_range_slider_same_start_end_value_with_reset() {
        val items = listOf(
            "1900",
            "1910",
            "1920",
            "1930",
            "1940",
            "1950",
            "1960",
            "1970",
            "1980",
            "1990",
            "2000",
            "2010",
            "2020"
        )
        warpRangeSlider(items, startItem = "1930", endItem = "1930", reset = true)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun warpRangeSlider(items: List<Any>, startItem: Any? = null, endItem: Any? = null, reset: Boolean = false) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.background.default)
                        .padding(dimensions.space2),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(dimensions.space3)
                ) {
                    WarpRangeSlider(items = items)
                    WarpRangeSlider(
                        items = items,
                        initialStartItem = startItem,
                        initialEndItem = endItem,
                        resetAtStartTerminal = reset,
                        resetAtEndTerminal = reset
                    )
                    WarpRangeSlider(
                        items = items,
                        enabled = false,
                        initialStartItem = startItem,
                        initialEndItem = endItem
                    )
                }
            }
        }
    }
}