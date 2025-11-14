package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.Brand
import com.schibsted.nmp.warp.components.WarpLogo
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpLogoTest(
    @TestParameter val flavor: Flavor,
    @TestParameter val nightMode: NightMode,
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            nightMode = nightMode
        ),
        theme = "android:Theme.Material.Light.NoActionBar",
        renderingMode = SessionParams.RenderingMode.FULL_EXPAND,
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
    fun warp_logo_small() {
        paparazzi.snapshot {
            WarpLogos(small = true)
        }
    }

    @Test
    fun warp_logo_large() {
        paparazzi.snapshot {
            WarpLogos()
        }
    }

    @Composable
    private fun WarpLogos(small: Boolean = false) {
        WarpTheme(flavor = flavor) {
            Column(
                modifier = Modifier
                    .background(colors.surface.elevated100)
                    .padding(
                        horizontal = dimensions.space2,
                        vertical = dimensions.space2
                    ),
                verticalArrangement = Arrangement.spacedBy(dimensions.space2)
            ) {
                Brand.entries.forEach { brand ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        WarpLogo(brand = brand, small = small)
                    }
                }
            }
        }
    }
}