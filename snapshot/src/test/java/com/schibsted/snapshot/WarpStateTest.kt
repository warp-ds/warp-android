package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.WarpState
import com.schibsted.nmp.warp.components.WarpStateType
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(TestParameterInjector::class)
class WarpStateTest(
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
    fun warp_state_no_search_results() {
        createWarpState(WarpStateType.NoSearchResults)
    }

    @Test
    fun warp_state_load_failed() {
        createWarpState(WarpStateType.LoadFailed)
    }

    @Test
    fun warp_state_loading() {
        createWarpState(WarpStateType.Loading)
    }

    @Test
    fun warp_state_login() {
        createWarpState(WarpStateType.Login)
    }

    @Test
    fun warp_state_offline() {
        createWarpState(WarpStateType.Offline)
    }

    @Test
    fun warp_state_verify() {
        createWarpState(WarpStateType.Verify)
    }

    @Test
    fun warp_state_custom_icon() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.surface.elevated100)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    WarpState(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Custom state",
                        description = "Custom things happening here. Custom text and custom content. Unpredictable",
                        tintColor = colors.icon.secondary,
                        icon = icons.shovel,
                        primaryButtonText = "Okay",
                        quietButtonText = "Meow?"
                    )
                }
            }
        }
    }

    private fun createWarpState(type: WarpStateType) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.surface.elevated100)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    WarpState(
                        modifier = Modifier.fillMaxWidth(),
                        type = type
                    )
                }
            }
        }
    }
}
