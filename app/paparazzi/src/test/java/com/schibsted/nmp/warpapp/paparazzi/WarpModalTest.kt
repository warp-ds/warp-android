package com.schibsted.nmp.warpapp.paparazzi

import androidx.compose.foundation.layout.Column
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.schibsted.nmp.warp.brands.finn.FinnWarpTheme
import com.schibsted.nmp.warp.components.WarpButton
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.Test


class WarpModalTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = "android:Theme.Material.ActionBar",
        //showSystemUi = false,
        maxPercentDifference = 0.1,
        /*snapshotHandler = if (PaparazziConfig.isVerifying) {
            SnapshotVerifier(
                maxPercentDifference = PaparazziConfig.maxPercentDifference,
                rootDirectory = PaparazziConfig.dirFinn
            )
        } else {
            HtmlReportWriter(snapshotRootDirectory = PaparazziConfig.dirFinn)
        }*/
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

    @Test
    fun testTheTest() {
        val one = 1
        Assert.assertEquals(one, one)
    }
}

