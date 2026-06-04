package com.schibsted.nmp.warp.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * Controls the layout direction of [WarpNavigationBar].
 * [Auto] switches automatically based on device orientation.
 */
enum class WarpNavBarLayout { Auto, Vertical, Horizontal }

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
 * @param modifier Modifier applied to the bar surface.
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

    Surface(
        modifier = modifier,
        color = colors.background.default,
        tonalElevation = NavigationBarDefaults.Elevation
    ) {
        if (showHorizontal) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                    .padding(horizontal = dimensions.space5)
                    .selectableGroup(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    val isSelected = index == selectedIndex
                    val iconColor = if (isSelected) colors.components.navBar.iconSelected else colors.icon.default
                    WarpHorizontalNavBarItem(
                        item = item,
                        isSelected = isSelected,
                        iconColor = iconColor,
                        onClick = { onItemSelected(index) }
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                    .selectableGroup()
            ) {
                items.forEachIndexed { index, item ->
                    val isSelected = index == selectedIndex
                    val iconColor = if (isSelected) colors.components.navBar.iconSelected else colors.icon.default
                    WarpNavBarItem(
                        item = item,
                        isSelected = isSelected,
                        iconColor = iconColor,
                        onClick = { onItemSelected(index) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.WarpNavBarItem(
    item: WarpNavItem,
    isSelected: Boolean,
    iconColor: Color,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .selectable(
                selected = isSelected,
                onClick = onClick,
                role = Role.Tab,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .semantics { contentDescription = item.contentDescription }
            .padding(top = dimensions.space075, bottom = dimensions.space075),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensions.space05),
    ) {
        Box(
            modifier = Modifier
                .size(width = dimensions.space7, height = dimensions.space4)
                .background(
                    color = if (isSelected) colors.background.subtle else Color.Transparent,
                    shape = RoundedCornerShape(50)
                ),
            contentAlignment = Alignment.Center
        ) {
            BadgedBox(
                badge = {
                    when {
                        item.badgeCount > 0 -> Badge(containerColor = colors.background.negative) {
                            WarpText(
                                text = item.badgeCount.toString(),
                                color = colors.text.invertedStatic,
                                style = WarpTextStyle.Detail
                            )
                        }
                        item.showDot -> Badge(containerColor = colors.background.negative)
                    }
                }
            ) {
                Box(
                    modifier = Modifier.size(dimensions.space3),
                    contentAlignment = Alignment.Center
                ) {
                    item.icon(iconColor, isSelected)
                }
            }
        }

        WarpText(
            text = item.label,
            style = if (isSelected) WarpTextStyle.DetailStrong else WarpTextStyle.Detail,
            color = colors.icon.default
        )
    }
}

@Composable
private fun WarpHorizontalNavBarItem(
    item: WarpNavItem,
    isSelected: Boolean,
    iconColor: Color,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .selectable(
                selected = isSelected,
                onClick = onClick,
                role = Role.Tab,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .semantics { contentDescription = item.contentDescription }
            .padding(vertical = dimensions.space15)
            .background(
                color = if (isSelected) colors.background.subtle else Color.Transparent,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = dimensions.space2),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensions.space1)
    ) {
        BadgedBox(
            badge = {
                when {
                    item.badgeCount > 0 -> Badge(containerColor = colors.background.negative) {
                        WarpText(
                            text = item.badgeCount.toString(),
                            color = colors.text.invertedStatic,
                            style = WarpTextStyle.Detail
                        )
                    }
                    item.showDot -> Badge(containerColor = colors.background.negative)
                }
            }
        ) {
            Box(
                modifier = Modifier.size(dimensions.space3),
                contentAlignment = Alignment.Center
            ) {
                item.icon(iconColor, isSelected)
            }
        }
        WarpText(
            text = item.label,
            style = if (isSelected) WarpTextStyle.DetailStrong else WarpTextStyle.Detail,
            color = colors.icon.default
        )
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

@Preview(name = "WarpNavBarItem — unselected", showBackground = true)
@Composable
private fun WarpNavBarItemPreview() {
    Row(
        modifier = Modifier.size(82.dp, 64.dp)
    ) {
        WarpNavBarItem(
            item = WarpNavItem(
                label = "Activity",
                icon = { color, _ -> WarpIcon(icon = icons.bell, color = color) },
                showDot = true,
                contentDescription = "Activity"
            ),
            isSelected = false,
            iconColor = colors.icon.default,
            onClick = {}
        )
    }
}

@Preview(name = "WarpNavBarItem — selected", showBackground = true)
@Composable
private fun WarpNavBarItemSelectedPreview() {
    Row(
        modifier = Modifier.size(82.dp, 64.dp)
    ) {
        WarpNavBarItem(
            item = WarpNavItem(
                label = "Home",
                icon = { color, _ -> WarpIcon(icon = icons.houseFilled, color = color) },
                contentDescription = "Home"
            ),
            isSelected = true,
            iconColor = colors.components.navBar.iconSelected,
            onClick = {}
        )
    }
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

@Preview(name = "WarpHorizontalNavBarItem — unselected", showBackground = true)
@Composable
private fun WarpHorizontalNavBarItemPreview() {
    WarpHorizontalNavBarItem(
        item = WarpNavItem(
            label = "Activity",
            icon = { color, _ -> WarpIcon(icon = icons.bell, color = color) },
            showDot = true,
            contentDescription = "Activity"
        ),
        isSelected = false,
        iconColor = colors.icon.default,
        onClick = {}
    )
}

@Preview(name = "WarpHorizontalNavBarItem — selected", showBackground = true)
@Composable
private fun WarpHorizontalNavBarItemSelectedPreview() {
    WarpHorizontalNavBarItem(
        item = WarpNavItem(
            label = "Home",
            icon = { color, _ -> WarpIcon(icon = icons.houseFilled, color = color) },
            contentDescription = "Home"
        ),
        isSelected = true,
        iconColor = colors.components.navBar.iconSelected,
        onClick = {}
    )
}