package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.schibsted.nmp.warp.components.WarpPill
import com.schibsted.nmp.warp.components.WarpPillStyle
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpPillTest(
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
    fun warp_pill_suggestion() {
        warpPillTest(WarpPillStyle.Suggestion)
    }

    @Test
    fun warp_pill_filter() {
        warpPillTest(WarpPillStyle.Filter)
    }

    private fun warpPillTest(type: WarpPillStyle) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(WarpTheme.colors.surface.elevated100)
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensions.space25,
                            vertical = dimensions.space2
                        ),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2)
                ) {
                    WarpPill(
                        text = type.name,
                        onClick = { },
                        style = type,
                        closable = true,
                        selected = false,
                        modifier = Modifier
                            .padding(bottom = dimensions.space2, end = dimensions.space1)
                    )
                    WarpPill(
                        text = type.name,
                        onClick = { },
                        style = type,
                        modifier = Modifier
                            .padding(bottom = dimensions.space2, end = dimensions.space1)
                    )
                    WarpPill(
                        text = "Link",
                        onClick = { },
                        style = type,
                        icon = icons.linkExternal,
                        modifier = Modifier
                            .padding(bottom = dimensions.space2)
                    )
                }

            }
        }
    }
}