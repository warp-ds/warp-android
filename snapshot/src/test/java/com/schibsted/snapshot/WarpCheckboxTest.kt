package com.schibsted.snapshot

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.WarpCheckbox
import com.schibsted.nmp.warp.components.WarpCheckboxStyle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class CheckbxoxSnapshotTest(
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
    fun `warp checkbox neutral`() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpCheckbox(
                    text = "This is a checkbox",
                    modifier = Modifier.wrapContentSize(),
                    onCheckedChange = { /*TODO*/ })
            }
        }
    }

    @Test
    fun `warp checkbox selected neutral`() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpCheckbox(
                    text = "This is a selected checkbox",
                    modifier = Modifier.wrapContentSize(),
                    checked = true,
                    onCheckedChange = { /*TODO*/ })
            }
        }
    }

    @Test
    fun `warp checkbox disabled`() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpCheckbox(
                    text = "This is a disabled checkbox",
                    modifier = Modifier.wrapContentSize(),
                    enabled = false,
                    onCheckedChange = { /*TODO*/ })
            }
        }
    }

    @Test
    fun `warp checkbox selected disabled`() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpCheckbox(
                    text = "This is a selected disabled checkbox",
                    modifier = Modifier.wrapContentSize(),
                    checked = true,
                    enabled = false,
                    onCheckedChange = { /*TODO*/ })
            }
        }
    }

    @Test
    fun `warp checkbox negative`() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpCheckbox(
                    text = "This is a negative checkbox",
                    style = WarpCheckboxStyle.Negative,
                    modifier = Modifier.wrapContentSize(),
                    onCheckedChange = { /*TODO*/ })
            }
        }
    }

    @Test
    fun `warp checkbox selected negative`() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpCheckbox(
                    text = "This is a selected negative checkbox",
                    style = WarpCheckboxStyle.Negative,
                    modifier = Modifier.wrapContentSize(),
                    checked = true,
                    onCheckedChange = { /*TODO*/ })
            }
        }
    }
}