package com.schibsted.snapshot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpNotificationBadge
import com.schibsted.nmp.warp.components.WarpNotificationBadgeContent
import com.schibsted.nmp.warp.components.WarpNotificationBadgeMax
import com.schibsted.nmp.warp.components.WarpNotificationBadgeStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpNotificationBadgeTest(
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
        renderingMode = SessionParams.RenderingMode.SHRINK,
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
    fun warp_notification_badge_default() {
        warpNotificationBadgeTest(WarpNotificationBadgeStyle.Default)
    }

    @Test
    fun warp_notification_badge_primary() {
        warpNotificationBadgeTest(WarpNotificationBadgeStyle.Primary)
    }

    private fun warpNotificationBadgeTest(style: WarpNotificationBadgeStyle) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                Row(
                    modifier = Modifier
                        .background(WarpTheme.colors.surface.elevated100)
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensions.space25,
                            vertical = dimensions.space2
                        ),
                    horizontalArrangement = Arrangement.spacedBy(dimensions.space4),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    WarpNotificationBadge(badge = WarpNotificationBadgeContent.Dot, style = style) {
                        WarpIcon(icon = icons.bell)
                    }
                    WarpNotificationBadge(badge = WarpNotificationBadgeContent.Count(3), style = style) {
                        WarpIcon(icon = icons.messages)
                    }
                    WarpNotificationBadge(badge = WarpNotificationBadgeContent.Count(100), style = style) {
                        WarpIcon(icon = icons.messages)
                    }
                    WarpNotificationBadge(
                        badge = WarpNotificationBadgeContent.Count(10, max = WarpNotificationBadgeMax.Nine),
                        style = style,
                    ) {
                        WarpIcon(icon = icons.messages)
                    }
                    WarpNotificationBadge(badge = WarpNotificationBadgeContent.Count(0), style = style) {
                        WarpIcon(icon = icons.messages)
                    }
                    WarpNotificationBadge(badge = WarpNotificationBadgeContent.Dot, style = style) {
                        WarpText(text = "Inbox")
                    }
                }
            }
        }
    }
}
