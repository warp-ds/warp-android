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
import com.schibsted.nmp.warp.components.WarpExpandable
import com.schibsted.nmp.warp.components.WarpExpandableType
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpExpandableTest(
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
    fun warp_expandable_expanded() {
        expandable(true)
    }

    @Test
    fun warp_expandable_closed() {
        expandable(false)
    }

    private fun expandable(expanded: Boolean) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.surface.elevated100)
                        .padding(
                            horizontal = dimensions.space1,
                            vertical = dimensions.space1
                        ),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2)
                ) {
                    WarpExpandable(
                        modifier = Modifier.padding(
                            vertical = dimensions.space1,
                            horizontal = dimensions.space2
                        ),
                        title = "Default",
                        initiallyExpanded = expanded
                    ) {
                        WarpText("Byte was a sleek, silver-furred cat with LED-bright eyes and a knack for coding.")
                    }

                    WarpExpandable(
                        modifier = Modifier.padding(
                            vertical = dimensions.space1,
                            horizontal = dimensions.space2
                        ),
                        title = "Boxed",
                        type = WarpExpandableType.Box,
                        initiallyExpanded = expanded
                    ) {
                        WarpText("Byte was a sleek, silver-furred cat with LED-bright eyes and a knack for coding. By day, she prowled the office, batting at stray USB cables and diagnosing minor hardware issues with a tilt of her head. By night, Byte tapped her paws on a miniature keyboard, crafting clever hacks and futuristic software. ")
                    }
                }
            }
        }
    }
}