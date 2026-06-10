package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpResources.icons
import org.junit.Rule
import org.junit.Test

class WarpNavigationBarTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun horizontalItemsUseEqualClickableSlots() {
        compose.setContent {
            var selectedIndex by mutableIntStateOf(0)

            Box(modifier = Modifier.width(500.dp).testTag("bar")) {
                WarpNavigationBar(
                    items = navItems(),
                    selectedIndex = selectedIndex,
                    onItemSelected = { selectedIndex = it },
                    modifier = Modifier.fillMaxWidth(),
                    layout = WarpNavBarLayout.Horizontal
                )
            }
        }

        compose.onNodeWithTag("bar").performTouchInput {
            click(Offset(x = width * 0.39f, y = height / 2f))
        }

        compose.onNodeWithContentDescription("Activity").assertIsSelected()
    }

    @Test
    fun itemsExposeSingleAccessibleNodePerContentDescription() {
        compose.setContent {
            WarpNavigationBar(
                items = navItems(),
                selectedIndex = 0,
                onItemSelected = {},
                layout = WarpNavBarLayout.Horizontal
            )
        }

        compose
            .onAllNodesWithContentDescription("Home", useUnmergedTree = true)
            .assertCountEquals(1)
    }

    private fun navItems() = listOf(
        WarpNavItem(
            label = "Home",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.houseFilled else icons.house, color = color) },
            contentDescription = "Home"
        ),
        WarpNavItem(
            label = "Activity",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.bellFilled else icons.bell, color = color) },
            showDot = true,
            contentDescription = "Activity"
        ),
        WarpNavItem(
            label = "Sell",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.circlePlusFilled else icons.circlePlus, color = color) },
            contentDescription = "Sell"
        ),
        WarpNavItem(
            label = "Messages",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.messagesFilled else icons.messages, color = color) },
            badgeCount = 4,
            contentDescription = "Messages"
        ),
        WarpNavItem(
            label = "Profile",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.circleUserFilled else icons.circleUser, color = color) },
            contentDescription = "Profile"
        ),
    )
}
