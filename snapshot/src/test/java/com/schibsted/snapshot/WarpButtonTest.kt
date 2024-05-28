package com.schibsted.snapshot

import androidx.compose.foundation.layout.wrapContentSize
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
import com.schibsted.nmp.warp.brands.finn.FinnWarpTheme
import com.schibsted.nmp.warp.brands.tori.ToriWarpTheme
import com.schibsted.nmp.warp.components.WarpButton
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(TestParameterInjector::class)
class SnapshotTest(
    @TestParameter val flavor: Flavor,
    @TestParameter val nightMode: NightMode,
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(nightMode = nightMode),
        theme = "android:Theme.Material.Light.NoActionBar",
        renderingMode = SessionParams.RenderingMode.SHRINK,
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
    fun `warp button large`() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpButton(
                    modifier = Modifier.wrapContentSize(),
                    text = "This is a large button",
                    onClick = { /*TODO*/ }
                )
            }
        }
    }

    @Test
    fun `warp button small`() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpButton(
                    modifier = Modifier.wrapContentSize(),
                    text = "This is a small button",
                    onClick = { /*TODO*/ },
                    small = true
                )
            }
        }
    }
}

@Composable
fun WarpTheme(flavor: Flavor, content: @Composable () -> Unit) {
    when (flavor) {
        Flavor.Finn -> FinnWarpTheme(content)
        Flavor.Tori -> ToriWarpTheme(content)
    }
}

enum class Flavor(val dir: File) {
    Finn(Config.dirFinn),
    Tori(Config.dirTori)
}