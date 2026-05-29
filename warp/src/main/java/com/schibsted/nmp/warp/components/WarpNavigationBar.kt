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
 * Represents a single item in a [WarpNavigationBar] bar.
 *
 * @param label Text label displayed below the icon.
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
 * @param modifier Modifier applied to the [NavigationBar].
 */
@Composable
fun WarpNavigationBar(
    items: List<WarpNavItem>,
    selectedIndex: Int,
    onItemSelected: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    require(items.isNotEmpty()) { "WarpNavigationBar items must not be empty" }
    require(selectedIndex in items.indices) { "selectedIndex $selectedIndex is out of bounds for ${items.size} items" }

    NavigationBar(
        modifier = modifier,
        containerColor = colors.background.default
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selectedIndex
            val iconColor = if (isSelected) colors.components.navBar.iconSelected else colors.icon.default

            NavigationBarItem(
                modifier = Modifier.semantics { contentDescription = item.contentDescription },
                selected = isSelected,
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
                        item.icon(iconColor, isSelected)
                    }
                },
                label = {
                    WarpText(
                        text = item.label,
                        style = if (isSelected) WarpTextStyle.DetailStrong else WarpTextStyle.Detail,
                        color = iconColor
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun WarpNavigationBarPreview() {
    WarpNavigationBar(
        items = listOf(
            WarpNavItem("Home", { _, _ -> }, contentDescription = "Home"),
            WarpNavItem("Ad", { _, _ -> }, contentDescription = "Create ad"),
            WarpNavItem("Alerts", { _, _ -> }, showDot = true, contentDescription = "Notifications"),
            WarpNavItem("Messages", { _, _ -> }, badgeCount = 3, contentDescription = "Messages"),
            WarpNavItem("My Page", { _, _ -> }, contentDescription = "My page"),
        ),
        selectedIndex = 0,
        onItemSelected = {}
    )
}
