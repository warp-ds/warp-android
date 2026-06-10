package com.schibsted.snapshot

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpNavItem
import com.schibsted.nmp.warp.components.WarpNavigationBar
import com.schibsted.nmp.warp.theme.WarpResources.icons
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpNavigationBarTest(
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
        renderingMode = SessionParams.RenderingMode.NORMAL,
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
    fun warp_navigation_bar_first_selected() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpNavigationBar(
                    items = navItems(),
                    selectedIndex = 0,
                    onItemSelected = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    @Test
    fun warp_navigation_bar_badge_selected() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpNavigationBar(
                    items = navItems(),
                    selectedIndex = 3,
                    onItemSelected = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    private fun navItems() = listOf(
        WarpNavItem(
            label = "Home",
            icon = { color: Color, sel: Boolean ->
                WarpIcon(icon = if (sel) icons.houseFilled else icons.house, color = color)
            },
            contentDescription = "Home"
        ),
        WarpNavItem(
            label = "Activity",
            icon = { color: Color, sel: Boolean ->
                WarpIcon(icon = if (sel) icons.bellFilled else icons.bell, color = color)
            },
            showDot = true,
            contentDescription = "Activity"
        ),
        WarpNavItem(
            label = "Sell",
            icon = { color: Color, sel: Boolean ->
                WarpIcon(icon = if (sel) icons.circlePlusFilled else icons.circlePlus, color = color)
            },
            contentDescription = "Sell"
        ),
        WarpNavItem(
            label = "Messages",
            icon = { color: Color, sel: Boolean ->
                WarpIcon(icon = if (sel) icons.messagesFilled else icons.messages, color = color)
            },
            badgeCount = 4,
            contentDescription = "Messages"
        ),
        WarpNavItem(
            label = "Profile",
            icon = { color: Color, sel: Boolean ->
                WarpIcon(icon = if (sel) icons.circleUserFilled else icons.circleUser, color = color)
            },
            contentDescription = "Profile"
        ),
    )
}
