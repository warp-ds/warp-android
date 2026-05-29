package com.schibsted.nmp.warp.components

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme.colors

/**
 * Represents a single item in a [WarpBottomNavigation] bar.
 *
 * @param label Text label displayed below the icon.
 * @param icon Composable icon slot — use [WarpIcon] or any composable (e.g. avatar image).
 * @param badgeCount Number to display in the badge. 0 means no badge. Must be >= 0.
 * @param showDot True to show a dot badge (no number). Ignored if [badgeCount] > 0.
 * @param contentDescription Accessibility description for the item, read by TalkBack.
 */
data class WarpBottomNavItem(
    val label: String,
    val icon: @Composable () -> Unit,
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
 * @param modifier Modifier applied to the [NavigationBar].
 */
@Composable
fun WarpBottomNavigation(
    items: List<WarpBottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    require(items.isNotEmpty()) { "WarpBottomNavigation items must not be empty" }
    require(selectedIndex in items.indices) { "selectedIndex $selectedIndex is out of bounds for ${items.size} items" }

    NavigationBar(
        modifier = modifier,
        containerColor = colors.background.default
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                modifier = Modifier.semantics { contentDescription = item.contentDescription },
                selected = index == selectedIndex,
                onClick = { onItemSelected(index) },
                icon = {
                    BadgedBox(
                        badge = {
                            when {
                                item.badgeCount > 0 -> Badge(
                                    containerColor = colors.background.negative
                                ) {
                                    WarpText(
                                        text = item.badgeCount.toString(),
                                        color = colors.text.invertedStatic,
                                        style = WarpTextStyle.Detail
                                    )
                                }
                                item.showDot -> Badge(
                                    containerColor = colors.background.negative
                                )
                            }
                        }
                    ) {
                        item.icon()
                    }
                },
                label = {
                    WarpText(
                        text = item.label,
                        style = WarpTextStyle.Detail
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colors.components.navBar.iconSelected,
                    unselectedIconColor = colors.icon.default,
                    selectedTextColor = colors.components.navBar.iconSelected,
                    unselectedTextColor = colors.icon.default,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun WarpBottomNavigationPreview() {
    WarpBottomNavigation(
        items = listOf(
            WarpBottomNavItem("Home", { }, contentDescription = "Home"),
            WarpBottomNavItem("Ad", { }, contentDescription = "Create ad"),
            WarpBottomNavItem("Alerts", { }, showDot = true, contentDescription = "Notifications"),
            WarpBottomNavItem("Messages", { }, badgeCount = 3, contentDescription = "Messages"),
            WarpBottomNavItem("My Page", { }, contentDescription = "My page"),
        ),
        selectedIndex = 0,
        onItemSelected = {}
    )
}
