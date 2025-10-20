package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.WarpDivider
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpDividerTest(
    @TestParameter val flavor: Flavor,
    @TestParameter val nightMode: NightMode,
    @TestParameter(valuesProvider = FontScaleProvider::class) private val fontScale: Float
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
    fun warpDivider() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.surface.elevated100)
                        .padding(dimensions.space3),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(dimensions.space3)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(bottom = dimensions.space3)
                            .fillMaxWidth()
                            .height(300.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WarpText(text = "Vertical divider", style = WarpTextStyle.Title4)
                        WarpDivider(
                            modifier = Modifier.padding(dimensions.space2),
                            Orientation.Vertical
                        )
                        WarpDivider(
                            modifier = Modifier.padding(dimensions.space2),
                            Orientation.Vertical,
                            dashed = true
                        )
                        WarpText(text = "Vertical dashed divider", style = WarpTextStyle.Title4)
                    }

                    WarpText(text = "Horizontal divider", style = WarpTextStyle.Title4)
                    WarpDivider(modifier = Modifier.padding(dimensions.space2))
                    WarpDivider(modifier = Modifier.padding(dimensions.space2), dashed = true)
                    WarpText(text = "Horizontal dashed divider", style = WarpTextStyle.Title4)
                }
            }
        }
    }
}
