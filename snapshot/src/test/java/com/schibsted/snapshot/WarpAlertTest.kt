package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.schibsted.nmp.warp.components.WarpAlert
import com.schibsted.nmp.warp.components.WarpAlertType
import com.schibsted.nmp.warp.theme.WarpTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpAlertTest(
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
    fun warp_alert_info_part1(){
        warpAlertPart1(WarpAlertType.Info)
    }

    @Test
    fun warp_alert_info_part2(){
        warpAlertPart2(WarpAlertType.Info)
    }

    @Test
    fun warp_alert_critical_part1(){
        warpAlertPart1(WarpAlertType.Critical)
    }

    @Test
    fun warp_alert_critical_part2(){
        warpAlertPart2(WarpAlertType.Critical)
    }

    @Test
    fun warp_alert_warning_part1(){
        warpAlertPart1(WarpAlertType.Warning)
    }

    @Test
    fun warp_alert_warning_part2(){
        warpAlertPart2(WarpAlertType.Warning)
    }

    @Test
    fun warp_alert_positive_part1(){
        warpAlertPart1(WarpAlertType.Positive)
    }

    @Test
    fun warp_alert_positive_part2(){
        warpAlertPart2(WarpAlertType.Positive)
    }

    private fun warpAlertPart1(type: WarpAlertType) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(WarpTheme.colors.surface.elevated100)
                        .width(450.dp)
                        .padding(horizontal = WarpTheme.dimensions.space25, vertical = WarpTheme.dimensions.space2),
                    verticalArrangement = Arrangement.spacedBy(WarpTheme.dimensions.space2)
                ) {
                    WarpAlert(
                        title = "${type.name} title ",
                        body = "Basic layout showing title & body",
                        type = type
                    )
                    WarpAlert(
                        body = "No title option. Body for ${type.name} type alert.",
                        type = type
                    )
                    WarpAlert(
                        title = "This ${type.name} title is very very very very  long loooong long long loooong long long loooong!",
                        body = "Basic layout showing title & body",
                        type = type
                    )
                    WarpAlert(
                        title = "This is the ${type.name} title",
                        body = "Layout showing title, body & link",
                        type = type,
                        linkText = "A link, click it!",
                    )
                    WarpAlert(
                        title = "This is the ${type.name} title",
                        body = "Layout showing title, body & link",
                        type = type,
                        linkText = "A long long long long long long link, just in case you wanna click click click click click click.",
                    )
                    WarpAlert(
                        title = "${type.name} title",
                        body = "Layout showing title & body & a secondary button",
                        type = type,
                        secondaryButtonText = "With Button",
                    )
                }
            }
        }
    }

    private fun warpAlertPart2(type: WarpAlertType) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(WarpTheme.colors.surface.elevated100)
                        .width(400.dp)
                        .padding(horizontal = WarpTheme.dimensions.space25, vertical = WarpTheme.dimensions.space2),
                    verticalArrangement = Arrangement.spacedBy(WarpTheme.dimensions.space2)
                ) {
                    WarpAlert(
                        title = "This is ${type.name} title",
                        body = "Showing title & body & a quiet button",
                        type = type,
                        quietButtonText = "Quiet Button",
                    )
                    WarpAlert(
                        title = "${type.name} title ",
                        body = "Showing title, body, secondary & quiet button",
                        type = type,
                        secondaryButtonText = "With Button",
                        quietButtonText = "Quiet Button",
                    )
                    WarpAlert(
                        title = "${type.name} title of the alert element",
                        body = "Showing all params title, body, link, secondary & quiet button",
                        type = type,
                        secondaryButtonText = "With Button",
                        quietButtonText = "Quiet Button",
                        linkText = "With link",
                    )
                    WarpAlert(
                        body = "No title version. Showing body, link, secondary & quiet button",
                        type = type,
                        secondaryButtonText = "With Button",
                        quietButtonText = "Quiet Button",
                        linkText = "With link",
                    )
                }
            }
        }
    }
}