package com.schibsted.nmp.warp.components

import androidx.compose.foundation.LocalIndication
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

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
    modifier: Modifier = Modifier
) {
    require(items.isNotEmpty()) { "WarpNavigationBar items must not be empty" }
    require(selectedIndex in items.indices) { "selectedIndex $selectedIndex is out of bounds for ${items.size} items" }

    Surface(
        modifier = modifier,
        color = colors.background.default,
        tonalElevation = NavigationBarDefaults.Elevation
    ) {
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
                indication = LocalIndication.current,
                interactionSource = remember { MutableInteractionSource() }
            )
            .semantics { contentDescription = item.contentDescription }
            .padding(top = 6.dp, bottom = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Indicator pill — 13.2dp horizontal padding per Figma spec
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.2.dp)
                .background(
                    color = if (isSelected) colors.background.subtle else Color.Transparent,
                    shape = RoundedCornerShape(50)
                )
                .padding(vertical = 4.dp),
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

@Preview
@Composable
fun WarpNavigationBarPreview() {
    WarpNavigationBar(
        items = listOf(
            WarpNavItem("Home", { _, _ -> }, contentDescription = "Home"),
            WarpNavItem("Activity", { _, _ -> }, contentDescription = "Activity"),
            WarpNavItem("Sell", { _, _ -> }, showDot = true, contentDescription = "Sell"),
            WarpNavItem("Messages", { _, _ -> }, badgeCount = 3, contentDescription = "Messages"),
            WarpNavItem("Profile", { _, _ -> }, contentDescription = "Profile"),
        ),
        selectedIndex = 0,
        onItemSelected = {}
    )
}

@Preview(name = "WarpNavBarItem — selected + unselected", showBackground = true)
@Composable
private fun WarpNavBarItemPreview() {
    Row {
        WarpNavBarItem(
            item = WarpNavItem(
                label = "Home",
                icon = { color, _ ->
                    Box(
                        modifier = Modifier
                            .size(dimensions.icon.default)
                            .background(color, RoundedCornerShape(4.dp))
                    )
                },
                contentDescription = "Home"
            ),
            isSelected = true,
            iconColor = colors.components.navBar.iconSelected,
            onClick = {}
        )
        WarpNavBarItem(
            item = WarpNavItem(
                label = "Activity",
                icon = { color, _ ->
                    Box(
                        modifier = Modifier
                            .size(dimensions.icon.default)
                            .background(color, RoundedCornerShape(4.dp))
                    )
                },
                showDot = true,
                contentDescription = "Activity"
            ),
            isSelected = false,
            iconColor = colors.icon.default,
            onClick = {}
        )
    }
}