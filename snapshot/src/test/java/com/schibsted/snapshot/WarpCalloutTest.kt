package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.CalloutSize
import com.schibsted.nmp.warp.components.CalloutState
import com.schibsted.nmp.warp.components.CalloutType
import com.schibsted.nmp.warp.components.Edge
import com.schibsted.nmp.warp.components.WarpCallout
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpCalloutTest(
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
    fun warp_callout_top() {
        warpCallout(Edge.Top)
    }

    @Test
    fun warp_callout_bottom() {
        warpCallout(Edge.Bottom)
    }

    @Test
    fun warp_callout_leading() {
        warpCallout(Edge.Leading)
    }

    @Test
    fun warp_callout_trailing() {
        warpCallout(Edge.Trailing)
    }

    @Test
    fun warp_callout_v_offset() {
        warpCallout(vOffset = 16.dp)
    }

    @Test
    fun warp_callout_h_offset() {
        warpCallout(hOffset = 16.dp)
    }

    private fun warpCallout(
        edge: Edge = Edge.Top,
        type: CalloutType = CalloutType.Inline,
        vOffset: Dp = 0.dp,
        hOffset: Dp = 0.dp
    ) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.surface.elevated100)
                        .padding(
                            horizontal = dimensions.space1,
                            vertical = dimensions.space1
                        ),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2)
                ) {
                    val calloutState = remember { CalloutState(true) }

                    WarpCallout(
                        text = "Small ${edge.name}",
                        edge = edge,
                        state = calloutState,
                        type = type,
                        inlineModifier = Modifier.padding(vertical = vOffset, horizontal = hOffset),
                        size = CalloutSize.Small,
                        closable = false,
                        onDismiss = { calloutState.isVisible = false },
                    )
                    WarpCallout(
                        text = "Default ${edge.name}",
                        edge = edge,
                        state = calloutState,
                        type = type,
                        inlineModifier = Modifier.padding(vertical = vOffset, horizontal = hOffset),
                        size = CalloutSize.Default,
                        closable = false,
                        onDismiss = { calloutState.isVisible = false },
                    )
                    WarpCallout(
                        text = "Closable ${edge.name}",
                        edge = edge,
                        state = calloutState,
                        type = type,
                        inlineModifier = Modifier.padding(vertical = vOffset, horizontal = hOffset),
                        size = CalloutSize.Default,
                        closable = true,
                        onDismiss = { calloutState.isVisible = false },
                    )
                }
            }
        }
    }
}