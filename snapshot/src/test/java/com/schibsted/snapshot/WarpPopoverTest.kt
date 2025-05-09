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
import com.schibsted.nmp.warp.components.WarpPopover
import com.schibsted.nmp.warp.components.WarpPopoverState
import com.schibsted.nmp.warp.components.utils.Edge
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpPopoverTest(
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
    fun warp_popover() {
        warpPopover()
    }

    private fun warpPopover() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.background.default)
                        .padding(dimensions.space1)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        dimensions.space2,
                        Alignment.CenterVertically
                    )
                ) {
                    val topState = remember { WarpPopoverState(true) }
                    val leadingState = remember { WarpPopoverState(true) }
                    val trailingState = remember { WarpPopoverState(true) }
                    val bottomState = remember { WarpPopoverState(true) }

                    WarpPopover(
                        inline = true,
                        state = topState,
                        title = "Bottom",
                        body = "Bottom Popover body Bottom Popover body Bottom Popover body Bottom Popover body",
                        edge = Edge.Bottom
                    )
                    WarpPopover(
                        inline = true,
                        state = leadingState,
                        title = "Trailing",
                        body = "Trailing Pop over body Trailing Pop over body Trailing Pop over body",
                        edge = Edge.Trailing
                    )
                    WarpPopover(
                        inline = true,
                        state = trailingState,
                        title = "Leading",
                        body = "Leading Popover body ",
                        edge = Edge.Leading
                    )
                    WarpPopover(
                        inline = true,
                        state = bottomState,
                        title = "Top",
                        body = "Top Popover body Top Popover body Top Popover body Top top Popover body",
                        edge = Edge.Top
                    )
                    WarpPopover(
                        inline = true,
                        state = bottomState,
                        title = "Looong long long long longalong longjohn long text. Also long.",
                        body = "Looong long long long longalong longjohn long text. Also long. Looong long long long longalong longjohn long text. Also long. Looong long long long longalong longjohn long text. Also long. Looong long long long longalong longjohn long text. Also long.",
                        edge = Edge.Top
                    )
                }
            }
        }
    }
}