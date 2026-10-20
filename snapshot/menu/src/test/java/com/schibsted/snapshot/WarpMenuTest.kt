package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.schibsted.nmp.warp.components.WarpMenuContent
import com.schibsted.nmp.warp.components.WarpMenuDivider
import com.schibsted.nmp.warp.components.WarpMenuItem
import com.schibsted.nmp.warp.components.WarpMenuItemState
import com.schibsted.nmp.warp.components.utils.InternalWarpApi
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(InternalWarpApi::class)
@RunWith(TestParameterInjector::class)
class WarpMenuTest(
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
    fun warp_menu_full() {
        snapshot {
            WarpMenuContent {
                WarpMenuItem(text = "Edit", icon = icons.edit, onClick = {})
                WarpMenuItem(text = "Share", icon = icons.share, onClick = {})
                WarpMenuItem(text = "Copy link", icon = icons.link, selected = true, onClick = {})
                WarpMenuDivider()
                WarpMenuItem(text = "Delete", icon = icons.bin, destructive = true, onClick = {})
            }
        }
    }

    @Test
    fun warp_menu_states() {
        snapshot {
            WarpMenuContent {
                WarpMenuItemState.values().forEach { state ->
                    WarpMenuItem(
                        text = state.name,
                        onClick = {},
                        forcedState = state,
                        icon = icons.edit,
                    )
                }
                WarpMenuItem(text = "Selected", onClick = {}, forcedState = WarpMenuItemState.Default, selected = true)
            }
        }
    }

    @Test
    fun warp_menu_supporting_text() {
        snapshot {
            WarpMenuContent {
                WarpMenuItem(text = "Single line", onClick = {})
                WarpMenuItem(text = "Two lines", supportingText = "Supporting text", onClick = {})
                WarpMenuItem(
                    text = "Two lines with icon",
                    supportingText = "Supporting text",
                    icon = icons.share,
                    onClick = {}
                )
            }
        }
    }

    @Test
    fun warp_menu_trailing_icon() {
        snapshot {
            WarpMenuContent {
                WarpMenuItem(
                    text = "Open settings",
                    icon = icons.edit,
                    trailingIcon = icons.chevronRight,
                    onClick = {}
                )
                WarpMenuItem(
                    text = "Download all your data",
                    icon = icons.edit,
                    trailingIcon = icons.chevronRight,
                    onClick = {}
                )
            }
        }
    }

    @Test
    fun warp_menu_destructive_disabled() {
        snapshot {
            WarpMenuContent {
                WarpMenuItem(text = "Delete", destructive = true, icon = icons.bin, onClick = {})
                WarpMenuItem(text = "Delete (disabled)", destructive = true, enabled = false, icon = icons.bin, onClick = {})
                WarpMenuItem(text = "Selected (disabled)", selected = true, enabled = false, onClick = {})
            }
        }
    }

    @Test
    fun warp_menu_long_label() {
        snapshot {
            WarpMenuContent {
                WarpMenuItem(
                    text = "A very long menu label that has to wrap onto more than one line",
                    supportingText = "And a supporting text that is also rather long",
                    icon = icons.edit,
                    trailingIcon = icons.chevronRight,
                    onClick = {}
                )
            }
        }
    }

    @Test
    fun warp_menu_rtl() {
        snapshot {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                WarpMenuContent {
                    WarpMenuItem(
                        text = "Edit",
                        icon = icons.edit,
                        trailingIcon = icons.chevronRight,
                        onClick = {}
                    )
                    WarpMenuItem(text = "Newest", supportingText = "Shown first", selected = true, onClick = {})
                }
            }
        }
    }

    private fun snapshot(content: @Composable () -> Unit) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Column(
                    modifier = Modifier
                        .background(colors.background.default)
                        .padding(dimensions.space3)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(dimensions.space2, Alignment.Top)
                ) {
                    content()
                }
            }
        }
    }
}
