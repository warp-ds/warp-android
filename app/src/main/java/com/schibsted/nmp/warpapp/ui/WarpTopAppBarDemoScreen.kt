package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.SearchConfiguration
import com.schibsted.nmp.warp.components.TabConfiguration
import com.schibsted.nmp.warp.components.TabData
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpScaffold
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.components.WarpTopAppBar
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpTopAppBarDemoScreen() {
    val searchState = remember { TextFieldState("") }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        TabData("Messages", "messages", hasBadge = true),
        TabData("Favorites", "favorites"),
        TabData("Profile", "profile")
    )

    val scrollBehavior = remember {
        androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior()
    }

    WarpScaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = colors.background.default,
        topBar = {
            WarpTopAppBar(
                titleText = "Top App Bar Demo",
                navigationIcon = {
                    IconButton(onClick = { /* Navigate back */ }) {
                        WarpIcon(icon = icons.arrowLeft)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Open menu */ }) {
                        WarpIcon(icon = icons.dotsVertical)
                    }
                },
                searchConfig = SearchConfiguration(
                    state = searchState,
                    onSearch = { query ->
                        // Handle search submission
                        println("Search: $query")
                    },
                    hint = "Search messages, favorites..."
                ),
                tabConfig = TabConfiguration(
                    tabs = tabs,
                    selectedIndex = selectedTabIndex,
                    onTabSelected = { index ->
                        selectedTabIndex = index
                    }
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
                            text = "Scroll to see the search bar collapse. Tabs stay visible.",
                            style = WarpTextStyle.Body,
                            color = colors.text.subtle
                        )
                    }
                }
            }
        }
    }
}
