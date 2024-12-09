package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.schibsted.nmp.warp.components.WarpBadge
import com.schibsted.nmp.warp.components.WarpBadgeStyle
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpBadgeTest(
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
    fun warp_badge() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {


                Column {
                    Card(
                        Modifier
                            .padding(dimensions.space1)
                            .background(WarpTheme.colors.background.default),
                        colors = CardDefaults.cardColors(containerColor = WarpTheme.colors.background.default),
                        elevation = CardDefaults.cardElevation(defaultElevation = dimensions.space05),
                        shape = shapes.roundedSmall
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            WarpBadge(
                                text = "Neutral",
                                alignment = Alignment.Center
                            )
                            WarpBadge(
                                text = "Error",
                                style = WarpBadgeStyle.Error,
                                alignment = Alignment.TopStart,
                            )
                            WarpBadge(
                                text = "Warning",
                                style = WarpBadgeStyle.Warning,
                                alignment = Alignment.TopEnd
                            )
                            WarpBadge(
                                text = "Success",
                                style = WarpBadgeStyle.Success,
                                alignment = Alignment.BottomStart
                            )
                            WarpBadge(
                                text = "Info",
                                style = WarpBadgeStyle.Info,
                                alignment = Alignment.BottomEnd
                            )
                            WarpBadge(
                                text = "Disabled",
                                style = WarpBadgeStyle.Disabled,
                                alignment = Alignment.CenterStart
                            )
                            WarpBadge(
                                text = "Sponsored",
                                style = WarpBadgeStyle.Sponsored,
                                alignment = Alignment.TopCenter
                            )
                            WarpBadge(
                                text = "Price",
                                style = WarpBadgeStyle.Price,
                                alignment = Alignment.BottomCenter
                            )
                            WarpBadge(
                                text = "Icon",
                                style = WarpBadgeStyle.Sponsored,
                                alignment = Alignment.CenterEnd,
                                icon = icons.awardMedal
                            )
                        }
                    }
                }
            }
        }
    }
}