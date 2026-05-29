package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.SearchConfiguration
import com.schibsted.nmp.warp.components.TabConfiguration
import com.schibsted.nmp.warp.components.TabData
import com.schibsted.nmp.warp.components.WarpCheckbox
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpScaffold
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.components.WarpTopAppBar
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpTopAppBarDemoScreen(onUp: () -> Unit) {
    val searchState = remember { TextFieldState("") }
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var titleCollapsible by remember { mutableStateOf(false) }
    var searchCollapsible by remember { mutableStateOf(true) }
    var tabsCollapsible by remember { mutableStateOf(false) }
    val currentFlavor = rememberCurrentFlavor()

    val tabs = listOf(
        TabData("Messages", "messages", hasBadge = true),
        TabData("Favorites", "favorites"),
        TabData("Profile", "profile")
    )

    // Create scroll behavior for collapse tracking
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    WarpScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = colors.background.default,
        topBar = {
            WarpTopAppBar(
                titleText = "Top App Bar Demo ($currentFlavor)",
                subtitleText = "Subtitle",
                titleCollapsible = titleCollapsible,
                navigationIcon = {
                    IconButton(onClick = onUp) {
                        WarpIcon(icon = icons.arrowLeft)
                    }
                },
                actions = {
                    FlavorSwitcher()
                },
                searchConfig = SearchConfiguration(
                    state = searchState,
                    onSearch = { query ->
                        // Handle search submission
                        println("Search: $query")
                    },
                    hint = "Search messages, favorites...",
                    collapsible = searchCollapsible
                ),
                tabConfig = TabConfiguration(
                    tabs = tabs,
                    selectedIndex = selectedTabIndex,
                    onTabSelected = { index ->
                        selectedTabIndex = index
                    },
                    collapsible = tabsCollapsible
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    WarpText(
                        text = "Collapse Controls",
                        style = WarpTextStyle.Title3,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    WarpText(
                        text = "Toggle which sections collapse on scroll (title → search → tabs):",
                        style = WarpTextStyle.Body,
                        color = colors.text.subtle,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Title collapsible toggle
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WarpCheckbox(
                            checked = titleCollapsible,
                            onCheckedChange = { titleCollapsible = it }
                        )
                        WarpText(
                            text = "Title collapsible (collapses first)",
                            style = WarpTextStyle.Body,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    // Search collapsible toggle
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WarpCheckbox(
                            checked = searchCollapsible,
                            onCheckedChange = { searchCollapsible = it }
                        )
                        WarpText(
                            text = "Search collapsible (collapses second)",
                            style = WarpTextStyle.Body,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    // Tabs collapsible toggle
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WarpCheckbox(
                            checked = tabsCollapsible,
                            onCheckedChange = { tabsCollapsible = it }
                        )
                        WarpText(
                            text = "Tabs collapsible (collapses last)",
                            style = WarpTextStyle.Body,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            items(50) { index ->
                Box(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Column {
                        WarpText(
                            text = "Item ${index + 1}",
                            style = WarpTextStyle.Title3
                        )
                        WarpText(
                            text = "Scroll down to see sections collapse sequentially based on your settings above.",
                            style = WarpTextStyle.Body,
                            color = colors.text.subtle
                        )
                    }
                }
            }
        }
    }
}
