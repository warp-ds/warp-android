package com.schibsted.nmp.warp.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationItemColors
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors

/**
 * Controls the layout direction of [WarpNavigationBar].
 * [Auto] switches automatically based on device orientation.
 */
enum class WarpNavBarLayout { Auto, Vertical, Horizontal }

/**
 * Represents a single item in a [WarpNavigationBar] bar.
 *
 * @param label Text label displayed below (vertical) or beside (horizontal) the icon.
 * @param icon Composable icon slot receiving the active [Color] and [isSelected] state.
 *   Use [WarpIcon] with the provided color, and switch between filled/outline variants based on [isSelected].
 *   For avatar images that don't tint, ignore the color parameter.
 * @param badgeCount Number to display in the badge. 0 means no badge. Must be >= 0.
 * @param showDot True to show a dot badge (no number). Ignored if [badgeCount] > 0.
 * @param contentDescription Accessibility description for the item, read by TalkBack.
 */
data class WarpNavItem(
    val label: String,
    val icon: @Composable (color: Color, isSelected: Boolean) -> Unit,
    val badgeCount: Int = 0,
    val showDot: Boolean = false,
    val contentDescription: String
) {
    init {
        require(badgeCount >= 0) { "badgeCount must be >= 0, was $badgeCount" }
    }
}

/**
 * A bottom navigation bar following the Warp design system.
 *
 * Stateless — the caller owns [selectedIndex] and badge counts via state (e.g. BottomNavViewModel).
 *
 * @param items Non-empty list of navigation items.
 * @param selectedIndex Index of the currently selected item. Must be in [items] bounds.
 * @param onItemSelected Called with the index of the tapped item.
 * @param modifier Modifier applied to the bar surface.
 * @param layout Layout direction; [WarpNavBarLayout.Auto] follows device orientation.
 */
@Composable
fun WarpNavigationBar(
    items: List<WarpNavItem>,
    selectedIndex: Int,
    onItemSelected: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
    layout: WarpNavBarLayout = WarpNavBarLayout.Auto
) {
    require(items.isNotEmpty()) { "WarpNavigationBar items must not be empty" }
    require(selectedIndex in items.indices) { "selectedIndex $selectedIndex is out of bounds for ${items.size} items" }

    val showHorizontal = when (layout) {
        WarpNavBarLayout.Auto       -> LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
        WarpNavBarLayout.Horizontal -> true
        WarpNavBarLayout.Vertical   -> false
    }
    val iconPosition = if (showHorizontal) NavigationItemIconPosition.Start else NavigationItemIconPosition.Top

    val warpColors = colors
    val navItemColors = NavigationItemColors(
        selectedIconColor = warpColors.components.navBar.iconSelected,
        selectedTextColor = warpColors.icon.default,
        selectedIndicatorColor = warpColors.background.subtle,
        unselectedIconColor = warpColors.icon.default,
        unselectedTextColor = warpColors.icon.default,
        disabledIconColor = warpColors.icon.default,
        disabledTextColor = warpColors.icon.default,
    )

    ShortNavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = warpColors.background.default,
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selectedIndex
            val iconColor = if (isSelected) warpColors.components.navBar.iconSelected else warpColors.icon.default

            ShortNavigationBarItem(
                selected = isSelected,
                onClick = { onItemSelected(index) },
                icon = {
                    when {
                        item.badgeCount > 0 -> BadgedBox(
                            badge = {
                                Badge(containerColor = warpColors.background.negative) {
                                    WarpText(
                                        text = item.badgeCount.toString(),
                                        color = warpColors.text.invertedStatic,
                                        style = WarpTextStyle.Detail
                                    )
                                }
                            }
                        ) { item.icon(iconColor, isSelected) }
                        item.showDot -> BadgedBox(
                            badge = { Badge(containerColor = warpColors.background.notification) }
                        ) { item.icon(iconColor, isSelected) }
                        else -> item.icon(iconColor, isSelected)
                    }
                },
                label = {
                    WarpText(
                        text = item.label,
                        style = if (isSelected) WarpTextStyle.DetailStrong else WarpTextStyle.Detail,
                        color = warpColors.icon.default,
                        maxLines = 1,
                        softWrap = false
                    )
                },
                iconPosition = iconPosition,
                colors = navItemColors,
            )
        }
    }
}

@Preview(name = "WarpNavigationBar", showBackground = true)
@Composable
fun WarpNavigationBarPreview() {
    WarpNavigationBar(
        items = listOf(
            WarpNavItem("Home", { color, _ -> WarpIcon(icon = icons.house, color = color) }, contentDescription = "Home"),
            WarpNavItem("Activity", { color, _ -> WarpIcon(icon = icons.bell, color = color) }, contentDescription = "Activity"),
            WarpNavItem("Sell", { color, _ -> WarpIcon(icon = icons.circlePlus, color = color) }, showDot = true, contentDescription = "Sell"),
            WarpNavItem("Messages", { color, _ -> WarpIcon(icon = icons.messages, color = color) }, badgeCount = 3, contentDescription = "Messages"),
            WarpNavItem("Profile", { color, _ -> WarpIcon(icon = icons.circleUser, color = color) }, contentDescription = "Profile"),
        ),
        selectedIndex = 0,
        onItemSelected = {}
    )
}

@Preview(name = "WarpNavigationBar — horizontal", widthDp = 640, showBackground = true)
@Composable
private fun WarpNavigationBarHorizontalPreview() {
    WarpNavigationBar(
        items = listOf(
            WarpNavItem("Home", { color, _ -> WarpIcon(icon = icons.house, color = color) }, contentDescription = "Home"),
            WarpNavItem("Activity", { color, _ -> WarpIcon(icon = icons.bell, color = color) }, contentDescription = "Activity"),
            WarpNavItem("Sell", { color, _ -> WarpIcon(icon = icons.circlePlus, color = color) }, showDot = true, contentDescription = "Sell"),
            WarpNavItem("Messages", { color, _ -> WarpIcon(icon = icons.messages, color = color) }, badgeCount = 3, contentDescription = "Messages"),
            WarpNavItem("Profile", { color, _ -> WarpIcon(icon = icons.circleUser, color = color) }, contentDescription = "Profile"),
        ),
        selectedIndex = 0,
        onItemSelected = {},
        layout = WarpNavBarLayout.Horizontal
    )
}
