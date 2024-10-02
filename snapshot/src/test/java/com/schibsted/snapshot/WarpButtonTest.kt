package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpButtonTest(
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
    fun warp_button_primary() {
        warpButton(WarpButtonStyle.Primary)
    }

    @Test
    fun warp_button_secondary() {
        warpButton(WarpButtonStyle.Secondary)
    }

    @Test
    fun warp_button_quiet() {
        warpButton(WarpButtonStyle.Quiet)
    }

    @Test
    fun warp_button_negative() {
        warpButton(WarpButtonStyle.Negative)
    }

    @Test
    fun warp_button_negative_quiet() {
        warpButton(WarpButtonStyle.NegativeQuiet)
    }

    @Test
    fun warp_button_utility() {
        warpButton(WarpButtonStyle.Utility)
    }

    @Test
    fun warp_button_utility_quiet() {
        warpButton(WarpButtonStyle.UtilityQuiet)
    }

    @Test
    fun warp_button_utility_overlay() {
        warpButton(WarpButtonStyle.UtilityOverlay)
    }

    private fun warpButton(style: WarpButtonStyle) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.surface.elevated100)
                        .padding(horizontal = dimensions.space2, vertical = dimensions.space2),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2)
                ) {
                    WarpButton(
                        style = style,
                        text = "${style.name} button",
                        onClick = { /*TODO*/ }
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} small",
                        onClick = { /*TODO*/ },
                        small = true
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} disabled",
                        onClick = { /*TODO*/ },
                        enabled = false
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} small disabled",
                        onClick = { /*TODO*/ },
                        enabled = false,
                        small = true
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} loading",
                        onClick = { /*TODO*/ },
                        loading = true
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} small loading",
                        onClick = { /*TODO*/ },
                        loading = true,
                        small = true
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} leading icon",
                        leadingIcon = R.drawable.animalpaw,
                        onClick = { /*TODO*/ }
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} leading icon loading",
                        leadingIcon = R.drawable.animalpaw,
                        loading = true,
                        onClick = { /*TODO*/ }
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} small leading icon",
                        leadingIcon = R.drawable.animalpaw,
                        onClick = { /*TODO*/ },
                        small = true
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} small leading icon loading",
                        leadingIcon = R.drawable.animalpaw,
                        onClick = { /*TODO*/ },
                        loading = true,
                        small = true
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} trailing icon",
                        trailingIcon = R.drawable.animalpaw,
                        onClick = { /*TODO*/ }
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} trailing icon loading",
                        trailingIcon = R.drawable.animalpaw,
                        onClick = { /*TODO*/ },
                        loading = true
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} small trailing icon",
                        trailingIcon = R.drawable.animalpaw,
                        onClick = { /*TODO*/ },
                        small = true
                    )
                    WarpButton(
                        style = style,
                        text = "${style.name} small trailing icon loading",
                        trailingIcon = R.drawable.animalpaw,
                        onClick = { /*TODO*/ },
                        small = true,
                        loading = true
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        WarpButton(
                            onClick = { /*TODO*/ },
                            style = style,
                            text = "${style.name} full width",
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        WarpButton(
                            onClick = { /*TODO*/ },
                            style = style,
                            text = "${style.name} full width leading icon",
                            leadingIcon = R.drawable.animalpaw,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        WarpButton(
                            onClick = { /*TODO*/ },
                            style = style,
                            text = "${style.name} full width trailing icon",
                            trailingIcon = R.drawable.animalpaw,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        WarpButton(
                            onClick = { /*TODO*/ },
                            style = style,
                            text = "${style.name} full width loading",
                            modifier = Modifier
                                .fillMaxWidth(),
                            loading = true
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        WarpButton(
                            onClick = { /*TODO*/ },
                            style = style,
                            text = "${style.name} full width disabled",
                            modifier = Modifier
                                .fillMaxWidth(),
                            enabled = false
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        WarpButton(
                            onClick = { /*TODO*/ },
                            style = style,
                            small = true,
                            text = "${style.name} full width small",
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        WarpButton(
                            onClick = { /*TODO*/ },
                            style = style,
                            small = true,
                            text = "${style.name} full width small leading icon",
                            leadingIcon = R.drawable.animalpaw,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        WarpButton(
                            onClick = { /*TODO*/ },
                            style = style,
                            small = true,
                            text = "${style.name} full width small trailing icon",
                            trailingIcon = R.drawable.animalpaw,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        WarpButton(
                            onClick = { /*TODO*/ },
                            style = style,
                            small = true,
                            text = "${style.name} full width small loading",
                            modifier = Modifier
                                .fillMaxWidth(),
                            loading = true
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        WarpButton(
                            onClick = { /*TODO*/ },
                            style = style,
                            small = true,
                            text = "${style.name} full width small disabled",
                            modifier = Modifier
                                .fillMaxWidth(),
                            enabled = false
                        )
                    }
                }
            }
        }
    }
}
