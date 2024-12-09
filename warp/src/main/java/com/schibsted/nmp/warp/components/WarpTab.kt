package com.schibsted.nmp.warp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun WarpTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit
) {

    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = WarpTheme.colors.background.default,
        contentColor = WarpTheme.colors.text.link,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = WarpTheme.colors.icon.selected
            )
        },
        //divider = {}, // TODO: check with designers if we are to have a divider below or not.
        tabs = tabs,
    )
}

@Composable
fun WarpTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    icon: @Composable (() -> Unit) = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    // TODO check if we should have the icon above or to the start.
    // Here it is above https://warp-ds.github.io/tech-docs/components/tabs/
    // Here it is at the start https://www.figma.com/file/8P1JQsd82b93gQ6K3igO2p/Warp---Components?type=design&node-id=381-41084&mode=design&t=GcnyyKcMZgcGW6gW-0
    // The colors of the active also differs
    // To change the layout we just need to switch between Tab and LeadingIconTab
    LeadingIconTab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = { WarpText(text = text, style = WarpTextStyle.Title4, softWrap = false) },
        selectedContentColor = WarpTheme.colors.text.link,
        unselectedContentColor = WarpTheme.colors.text.subtle,
        icon = icon,
        interactionSource = interactionSource
    )
}

@Preview
@Composable
fun WarpTabsPreview() {
    var selectedIndex by remember { mutableStateOf(0) }
    WarpTabRow(selectedIndex) {
        WarpTab(selectedIndex == 0, onClick = { selectedIndex = 0 }, text = "First", icon = {Icon(Icons.Filled.Call, null)})
        WarpTab(selectedIndex == 1, onClick = { selectedIndex = 1 }, text = "Second", icon = {Icon(Icons.Filled.DateRange, null)})
        WarpTab(selectedIndex == 2, onClick = { selectedIndex = 2 }, text = "Third", icon = {Icon(Icons.Filled.Done, null)})
    }
}