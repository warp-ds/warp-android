package com.schibsted.nmp.warpapp.paparazzi

import androidx.compose.foundation.layout.Column
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.brands.finn.FinnWarpTheme
import com.schibsted.nmp.warp.components.WarpButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpModalTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = "android:Theme.Material.ActionBar",
        showSystemUi = false,
        maxPercentDifference = 0.1,
        snapshotHandler = if (PaparazziConfig.isVerifying) {
            SnapshotVerifier(
                maxPercentDifference = PaparazziConfig.maxPercentDifference,
                rootDirectory = PaparazziConfig.dirFinn
            )
        } else {
            HtmlReportWriter(snapshotRootDirectory = PaparazziConfig.dirFinn)
        }
    )

    @Test
    fun testModalWith() {
        paparazzi.snapshot {
            FinnWarpTheme {
                Column {
                    WarpButton("Mjau", {})
                }
            }
        }
    }

    @Test
    fun testthis() {
        paparazzi.snapshot {
            WarpButton("Mjau", {})
        }
    }
}

