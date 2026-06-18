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
import com.schibsted.nmp.warp.components.WarpTooltip
import com.schibsted.nmp.warp.components.WarpTooltipState
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpTooltipTest(
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
    fun warp_tooltip(){
        warpTooltip()
    }

    private fun warpTooltip() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.background.default)
                        .padding(dimensions.space1)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2, Alignment.CenterVertically)
                ) {
                    val topState = remember { WarpTooltipState(true) }
                    val leadingState = remember { WarpTooltipState(true) }
                    val trailingState = remember { WarpTooltipState(true) }
                    val bottomState = remember { WarpTooltipState(true) }

                    WarpTooltip(
                        inline = true,
                        state = topState,
                        text = "Bottom",
                        edge = Edge.Bottom
                    )
                    WarpTooltip(
                        inline = true,
                        state = topState,
                        text = "1",
                        edge = Edge.Bottom
                    )
                    WarpTooltip(
                        inline = true,
                        state = leadingState,
                        text = "Trailing",
                        edge = Edge.Trailing
                    )
                    WarpTooltip(
                        inline = true,
                        state = trailingState,
                        text = "Leading",
                        edge = Edge.Leading
                    )
                    WarpTooltip(
                        inline = true,
                        state = bottomState,
                        text = "Top",
                        edge = Edge.Top
                    )
                    WarpTooltip(
                        inline = true,
                        state = bottomState,
                        text = "Looong long long long longalong longjohn long text. Also long.",
                        edge = Edge.Top
                    )
                }
            }
        }
    }
}