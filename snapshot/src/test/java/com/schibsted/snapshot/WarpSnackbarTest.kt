package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarVisuals
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
import com.schibsted.nmp.warp.components.WarpSnackbar
import com.schibsted.nmp.warp.components.WarpSnackbarVisuals
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.WarpSnackbarScenario
import com.schibsted.nmp.warp.utils.WarpSnackbarScenarios
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpSnackbarTest(
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
    fun warp_snackbar_neutral() {
        warpSnackbar(WarpSnackbarScenarios.neutral)
    }

    @Test
    fun warp_snackbar_success() {
        warpSnackbar(WarpSnackbarScenarios.success)
    }

    @Test
    fun warp_snackbar_error_multiline() {
        warpSnackbar(WarpSnackbarScenarios.errorMultiline)
    }

    @Test
    fun warp_snackbar_warning_long_action() {
        warpSnackbar(WarpSnackbarScenarios.warningLongAction)
    }

    @Test
    fun warp_snackbar_info_short_action() {
        warpSnackbar(WarpSnackbarScenarios.infoShortAction)
    }

    @Test
    fun warp_snackbar_info_indefinite() {
        warpSnackbar(WarpSnackbarScenarios.infoIndefinite)
    }

    private fun warpSnackbar(scenario: WarpSnackbarScenario) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.background.default)
                        .padding(dimensions.space2),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    val visuals = WarpSnackbarVisuals(
                        message = scenario.message,
                        type = scenario.type,
                        actionLabel = scenario.actionLabel,
                        withDismissAction = scenario.withDismissAction,
                        duration = scenario.duration
                    )

                    WarpSnackbar(
                        snackbarData = FakeSnackbarData(visuals)
                    )
                }
            }
        }
    }

    // Fake SnackbarData for Paparazzi tests
    private class FakeSnackbarData(
        override val visuals: SnackbarVisuals
    ) : SnackbarData {
        override fun performAction() {}
        override fun dismiss() {}
    }
}
