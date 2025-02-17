package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.WarpToast
import com.schibsted.nmp.warp.components.WarpToastDuration
import com.schibsted.nmp.warp.components.WarpToastState
import com.schibsted.nmp.warp.components.WarpToastType
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpToastTest(
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
    fun warp_toast_success() {
        warpToast(WarpToastType.Success, "Success toast")
    }

    @Test
    fun warp_toast_warning() {
        warpToast(WarpToastType.Warning, "Warning toast")
    }

    @Test
    fun warp_toast_error() {
        warpToast(WarpToastType.Error, "Error toast")
    }

    @Test
    fun warp_toast_long_message() {
        warpToast(
            WarpToastType.Success,
            "Loong long longjohn suppper longalong long long message. Also long and longer than you think"
        )
    }

    private fun warpToast(type: WarpToastType, message: String) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.background.default)
                        .fillMaxSize()
                        .padding(
                            horizontal = dimensions.space25,
                            vertical = dimensions.space2
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    val state = remember { WarpToastState() }
                    state.showToast(message)

                    WarpToast(
                        type = type,
                        state = state,
                        duration = WarpToastDuration.INFINITE,
                    )
                }
            }
        }
    }
}
