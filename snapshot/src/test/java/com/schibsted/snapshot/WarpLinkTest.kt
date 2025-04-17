package com.schibsted.snapshot

import android.util.Log
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
import com.schibsted.nmp.warp.components.WarpLink
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpLinkTest(
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
    fun warp_link() {
        warpLinkTest()
    }

    private fun warpLinkTest() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(com.schibsted.nmp.warp.theme.WarpTheme.colors.surface.elevated100)
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensions.space25,
                            vertical = dimensions.space2
                        ),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2)
                ) {
                    WarpLink(
                        text = "Warp link",
                        onClick = { Log.d("WarpLink", "Clicked") })
                    WarpLink(
                        text = "Warp link with a very long link text with icon. Also extra long text. Loooooong",
                        onClick = { Log.d("WarpLink", "Clicked long link") },
                    )
                    WarpLink(
                        text = "Warp link with icon",
                        onClick = { Log.d("WarpLink", "Clicked link") },
                        icon = icons.linkExternal
                    )
                    WarpLink(
                        text = "Warp link with underline",
                        onClick = { Log.d("WarpLink", "Clicked link") },
                        icon = icons.link,
                        underline = true
                    )
                    WarpLink(
                        text = "Warp link with a very long link text with icon. Also extra long text. And icon",
                        onClick = { Log.d("WarpLink", "Clicked long link") },
                        icon = icons.smileyHappy
                    )
                }
            }
        }
    }
}