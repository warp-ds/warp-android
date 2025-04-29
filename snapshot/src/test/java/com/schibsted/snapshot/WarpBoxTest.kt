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
import com.schibsted.nmp.warp.components.WarpBox
import com.schibsted.nmp.warp.components.WarpBoxStyle
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpBoxTest(
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
    fun warp_box_neutral(){
        warpBox(WarpBoxStyle.Neutral)
    }

    @Test
    fun warp_box_bordered(){
        warpBox(WarpBoxStyle.Bordered)
    }

    @Test
    fun warp_box_info(){
        warpBox(WarpBoxStyle.Info)
    }

    private fun warpBox(style: WarpBoxStyle) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.background.default)
                        .padding(dimensions.space2),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space1)
                ) {
                    WarpBox(
                        boxStyle = style,
                        modifier = Modifier
                            .padding(vertical = dimensions.space1)
                            .fillMaxWidth()
                    ) {
                        WarpText(
                            modifier = Modifier.padding(dimensions.space2),
                            text = "Composable ${style.name}"
                        )
                    }
                    WarpBox(
                        boxStyle = style,
                        modifier = Modifier
                            .padding(vertical = dimensions.space1)
                            .fillMaxWidth(),
                        heading = "Header",
                        text = "Warp box ${style.name} "
                    )
                    WarpBox(
                        modifier = Modifier
                            .padding(vertical = dimensions.space1)
                            .fillMaxWidth(),
                        boxStyle = style,
                        heading = "Hello Box! ",
                        icon = { WarpIcon(icon = icons.spa) },
                        text = "This is a box has all optional UI elements.",
                        link = "This is a link",
                        linkAction = { Log.d("Meow", "Link click") },
                        buttonText = "This is a button",
                        buttonAction = { Log.d("Meow", "Button click") }
                    )
                    WarpBox(
                        boxStyle = style,
                        modifier = Modifier
                            .padding(vertical = dimensions.space1)
                            .fillMaxWidth(),
                        heading = "Warp Box!",
                        text = "${style.name} box with optional heading and button",
                        buttonText = "This is a button",
                        buttonAction = { Log.d("Meow", "Button click") }
                    )
                    WarpBox(
                        modifier = Modifier
                            .padding(vertical = dimensions.space1)
                            .fillMaxWidth(),
                        boxStyle = style,
                        heading = "Warp Box!",
                        icon = { WarpIcon(icon = icons.spa) },
                        text = "${style.name} box with optional heading and icon"
                    )
                }
            }
        }
    }
}