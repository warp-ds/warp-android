package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.schibsted.nmp.warp.components.WarpTab
import com.schibsted.nmp.warp.components.WarpTabRow
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpTabsTest(
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
        validateAccessibility = true,
        theme = "android:Theme.Material.Light.NoActionBar",
        renderingMode = SessionParams.RenderingMode.NORMAL,
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
    fun warp_tabs() {
        warpTabs(scrollable = false)
    }

    @Test
    fun warp_tabs_scrollable() {
        warpTabs(scrollable = true)
    }

    private fun warpTabs(scrollable: Boolean) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.background.default)
                ) {
                    WarpTabRow(
                        modifier = Modifier.padding(top = dimensions.space3),
                        selectedTabIndex = 1,
                        scrollable = scrollable
                    ) {
                        WarpTab(
                            selected = false,
                            text = "Loooong Tab 1"
                        )
                        WarpTab(
                            selected = true,
                            text = "Tab 2"
                        )
                        WarpTab(
                            selected = false,
                            text = "Tab 3"
                        )
                        if(scrollable) {
                            WarpTab(
                                selected = false,
                                text = "Tab 4"
                            )
                            WarpTab(
                                selected = false,
                                text = "Tab 5"
                            )
                        }
                    }
                    WarpTabRow(
                        modifier = Modifier.padding(top = dimensions.space3),
                        selectedTabIndex = 2,
                        scrollable = scrollable
                    ) {
                        WarpTab(
                            selected = false,
                            text = "Tab1",
                            icon = icons.lotusFlower,
                        )
                        WarpTab(
                            selected = false,
                            text = "Tab2",
                            icon = icons.awardMedal,
                        )
                        WarpTab(
                            selected = true,
                            text = "Tab3",
                            icon = icons.sparkles,
                        )
                        if(scrollable) {
                            WarpTab(
                                selected = true,
                                text = "Tab4",
                                icon = icons.animalPaw,
                            )
                            WarpTab(
                                selected = true,
                                text = "Tab5",
                                icon = icons.cabinHut,
                            )
                        }
                    }
                }
            }
        }
    }
}
