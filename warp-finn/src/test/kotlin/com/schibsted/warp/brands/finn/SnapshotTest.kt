package com.schibsted.warp.brands.finn

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.schibsted.nmp.warp.brands.finn.FinnWarpTheme
import com.schibsted.nmp.warp.components.WarpButton
import org.junit.Rule
import org.junit.Test


class SnapshotTest {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        theme = "android:Theme.Material.Light.NoActionBar",
        renderingMode = SessionParams.RenderingMode.SHRINK,
    )

    @Test
    fun `warp button large`() {
        paparazzi.snapshot {
            FinnWarpTheme {
                WarpButton(
                    modifier = Modifier.wrapContentSize(),
                    text = "This is a button",
                    onClick = { /*TODO*/ }
                )
            }
        }
    }

    @Test
    fun `warp button small`() {
        paparazzi.snapshot {
            FinnWarpTheme {
                WarpButton(
                    modifier = Modifier.wrapContentSize(),
                    text = "This is also a button",
                    onClick = { /*TODO*/ },
                    small = true
                )
            }
        }
    }
}