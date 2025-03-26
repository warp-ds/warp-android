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
    fun warp_range_slider_ints() {
        val ints = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        warpRangeSlider(ints)
    }

    @Test
    fun warp_range_slider_strings() {
        val strings = listOf(
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
        warpRangeSlider(strings)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun warpRangeSlider(items: List<Any>) {
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
                    WarpRangeSlider(items = items)
                    //Range selected
                    WarpRangeSlider(
                        items = items,
                        initialStartItem = items[3],
                        initialEndItem = items[7],
                    )
                    //Range indicators
                    WarpRangeSlider(
                        items = items,
                        showRange = true,
                        showTooltips = true
                    )
                    //With resets
                    WarpRangeSlider(
                        items = items,
                        initialStartItem = null,
                        initialEndItem = null,
                        resetAtStartText =  "Min",
                        resetAtEndText = "Max"
                    )
                    //With resets and range
                    WarpRangeSlider(
                        items = items,
                        initialStartItem = items[7],
                        initialEndItem = items[9],
                        resetAtStartText =  "Min",
                        resetAtEndText = "Max",
                        showRange = true
                    )
                    //All of the above but disabled
                    //Regular
                    WarpRangeSlider(items = items, enabled = false)
                    //Range selected
                    WarpRangeSlider(
                        items = items,
                        initialStartItem = items[3],
                        initialEndItem = items[7],
                        enabled = false
                    )
                    //Range indicators
                    WarpRangeSlider(
                        items = items,
                        showRange = true,
                        showTooltips = true,
                        enabled = false
                    )
                    //With resets
                    WarpRangeSlider(
                        items = items,
                        resetAtStartText =  "Min",
                        resetAtEndText = "Max",
                        enabled = false
                    )
                    //With resets and range
                    WarpRangeSlider(
                        items = items,
                        initialStartItem = items[3],
                        initialEndItem = items[7],
                        resetAtStartText =  "Min",
                        resetAtEndText = "Max",
                        enabled = false,
                        showRange = true
                    )
                }
            }
        }
    }
}