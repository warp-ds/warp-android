package com.schibsted.snapshot

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpScaffold
import com.schibsted.nmp.warp.components.WarpTopAppBar
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpTopAppBarTest(
    @TestParameter val flavor: Flavor,
    @TestParameter val nightMode: NightMode
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            nightMode = nightMode
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
    fun warp_top_app_bar_title() {
        topAppBar()
    }

    @Test
    fun warp_top_app_bar_with_icons() {
        topAppBar(showIcons = true)
    }

    @Test
    fun warp_top_app_bar_title_centered() {
        topAppBar(centered = true)
    }

    @Test
    fun warp_top_app_bar_centered_with_icons() {
        topAppBar(showIcons = true, centered = true)
    }


    @OptIn(ExperimentalMaterial3Api::class)
    private fun topAppBar(showIcons: Boolean = false, centered: Boolean = false) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            centered = centered,
                            navigationIcon = {
                                if (showIcons) {
                                    IconButton(
                                        onClick = {}
                                    ) {
                                        WarpIcon(icon = icons.arrowLeft)
                                    }
                                }
                            },
                            actions = {
                                if (showIcons) {
                                    IconButton(onClick = { }) {
                                        WarpIcon(
                                            icon = icons.dotsVertical
                                        )
                                    }
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier
                        .height(300.dp)
                ) {

                }
            }
        }
    }
}