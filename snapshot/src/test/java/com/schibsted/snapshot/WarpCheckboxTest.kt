package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.schibsted.nmp.warp.components.WarpCheckbox
import com.schibsted.nmp.warp.components.WarpCheckboxStyle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpCheckboxTest(
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
    fun warp_checkbox_neutral() {
        warpCheckbox(WarpCheckboxStyle.Neutral)
    }

    @Test
    fun warp_checkbox_disabled() {
        warpCheckbox(WarpCheckboxStyle.Disabled)
    }

    @Test
    fun warp_checkbox_negative() {
        warpCheckbox(WarpCheckboxStyle.Negative)
    }

    private fun warpCheckbox(style: WarpCheckboxStyle) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(com.schibsted.nmp.warp.theme.WarpTheme.colors.surface.elevated100)
                        .padding(
                            horizontal = com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2,
                            vertical = com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2
                        ),
                    verticalArrangement = Arrangement.spacedBy(com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2)
                ) {
                    WarpCheckbox(
                        text = "${style.name} checkbox",
                        style = style,
                        enabled = style != WarpCheckboxStyle.Disabled
                    )
                    WarpCheckbox(
                        text = "${style.name} selected checkbox",
                        style = style,
                        checked = true,
                        enabled = style != WarpCheckboxStyle.Disabled
                    )
                }
            }
        }
    }
}