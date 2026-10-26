package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
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
import com.schibsted.nmp.warp.components.WarpButtonSize
import com.schibsted.nmp.warp.components.WarpButtonState
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpIconButton
import com.schibsted.nmp.warp.components.utils.InternalWarpApi
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private val ModernStyles = listOf(
    WarpButtonStyle.Prominent,
    WarpButtonStyle.Tonal,
    WarpButtonStyle.Text,
    WarpButtonStyle.Flat,
    WarpButtonStyle.Elevated,
    WarpButtonStyle.Static,
    WarpButtonStyle.InvertedStatic,
    WarpButtonStyle.Destructive,
    WarpButtonStyle.DestructiveText,
)

// Stand-in for the photo or video the static styles sit on.
private val ImageStandIn = Color(0xFF6E6E78)

private const val LongLabel = "A very long button label that cannot fit on a single line"

@OptIn(InternalWarpApi::class)
@RunWith(TestParameterInjector::class)
class WarpButtonStylesTest(
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
    fun warp_button_states() {
        snapshot {
            ModernStyles.forEach { style ->
                WarpButtonState.values().forEach { state ->
                    WarpButton(
                        text = "${style.name} ${state.name}",
                        onClick = {},
                        forcedState = state,
                        style = style,
                        leadingIcon = R.drawable.warp_animalpaw,
                    )
                }
            }
        }
    }

    @Test
    fun warp_button_sizes() {
        snapshot {
            ModernStyles.forEach { style ->
                WarpButtonSize.values().forEach { size ->
                    WarpButton(text = "${style.name} ${size.name}", onClick = {}, style = style, size = size)
                }
            }
        }
    }

    @Test
    fun warp_icon_button() {
        snapshot {
            ModernStyles.forEach { style ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(dimensions.space1),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WarpButtonSize.values().forEach { size ->
                        WarpIconButton(icon = icons.share, contentDescription = null, onClick = {}, style = style, size = size)
                    }
                    WarpIconButton(
                        icon = icons.share,
                        contentDescription = null,
                        onClick = {},
                        forcedState = WarpButtonState.Focus,
                        style = style
                    )
                    WarpIconButton(
                        icon = icons.share,
                        contentDescription = null,
                        onClick = {},
                        forcedState = WarpButtonState.Disabled,
                        style = style
                    )
                }
            }
        }
    }

    @Test
    fun warp_button_loading() {
        snapshot {
            listOf(WarpButtonStyle.Prominent, WarpButtonStyle.Elevated).forEach { style ->
                WarpButtonSize.values().forEach { size ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensions.space1),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WarpButton(text = "Loading ${size.name}", onClick = {}, style = style, size = size, loading = true)
                        WarpIconButton(
                            icon = icons.share,
                            contentDescription = null,
                            onClick = {},
                            style = style,
                            size = size,
                            loading = true
                        )
                    }
                }
            }
        }
    }

    @Test
    fun warp_button_icons() {
        snapshot {
            WarpButtonSize.values().forEach { size ->
                WarpButton(text = "Leading ${size.name}", onClick = {}, size = size, leadingIcon = R.drawable.warp_animalpaw)
                WarpButton(text = "Trailing ${size.name}", onClick = {}, size = size, trailingIcon = R.drawable.warp_animalpaw)
                WarpButton(
                    text = "Both ${size.name}",
                    onClick = {},
                    style = WarpButtonStyle.Tonal,
                    size = size,
                    leadingIcon = R.drawable.warp_animalpaw,
                    trailingIcon = R.drawable.warp_animalpaw
                )
            }
            WarpButton(
                text = "Full width",
                onClick = {},
                leadingIcon = R.drawable.warp_animalpaw,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    @Test
    fun warp_button_slot_icons() {
        snapshot {
            listOf(WarpButtonStyle.Prominent, WarpButtonStyle.DestructiveText).forEach { style ->
                listOf(true, false).forEach { enabled ->
                    WarpButton(
                        text = "${style.name} slot icon",
                        onClick = {},
                        style = style,
                        enabled = enabled,
                        leadingIcon = { WarpIcon(icon = icons.share, color = LocalContentColor.current) }
                    )
                }
            }
        }
    }

    @Test
    fun warp_button_long_label() {
        snapshot {
            WarpButton(
                text = LongLabel,
                onClick = {},
                leadingIcon = R.drawable.warp_animalpaw,
                trailingIcon = R.drawable.warp_animalpaw
            )
            WarpButton(
                text = LongLabel,
                onClick = {},
                size = WarpButtonSize.ExtraSmall,
                trailingIcon = R.drawable.warp_animalpaw
            )
            WarpButton(
                text = LongLabel,
                onClick = {},
                style = WarpButtonStyle.Tonal,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    @Test
    fun warp_button_rtl() {
        snapshot {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Column(verticalArrangement = Arrangement.spacedBy(dimensions.space2)) {
                    WarpButton(text = "Leading", onClick = {}, leadingIcon = R.drawable.warp_animalpaw)
                    WarpButton(
                        text = "Trailing",
                        onClick = {},
                        style = WarpButtonStyle.Tonal,
                        trailingIcon = R.drawable.warp_animalpaw
                    )
                    WarpButton(
                        text = "Focus",
                        onClick = {},
                        forcedState = WarpButtonState.Focus,
                        leadingIcon = R.drawable.warp_animalpaw
                    )
                }
            }
        }
    }

    @Test
    fun warp_button_static_on_image() {
        snapshot {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ImageStandIn)
                    .padding(dimensions.space2),
                verticalArrangement = Arrangement.spacedBy(dimensions.space2)
            ) {
                listOf(WarpButtonStyle.Static, WarpButtonStyle.InvertedStatic).forEach { style ->
                    WarpButtonState.values().forEach { state ->
                        WarpButton(text = "${style.name} ${state.name}", onClick = {}, forcedState = state, style = style)
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensions.space1),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WarpButtonSize.values().forEach { size ->
                            WarpIconButton(icon = icons.share, contentDescription = null, onClick = {}, style = style, size = size)
                        }
                    }
                }
            }
        }
    }

    private fun snapshot(content: @Composable ColumnScope.() -> Unit) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.background.default)
                        .padding(dimensions.space3)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2, Alignment.Top),
                    content = content
                )
            }
        }
    }
}
