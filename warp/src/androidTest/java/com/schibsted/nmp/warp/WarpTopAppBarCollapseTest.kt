package com.schibsted.nmp.warp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.test.ext.junit.runners.AndroidJUnit4
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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests for WarpTopAppBar collapse functionality.
 * These tests verify that different collapse configurations work without crashing.
 */
@RunWith(AndroidJUnit4::class)
class WarpTopAppBarCollapseTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun topAppBar_searchOnlyCollapsible_scrollsWithoutCrashing() {
        composeTestRule.setContent {
            val searchState = remember { TextFieldState("") }
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

            WarpScaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                containerColor = colors.background.default,
                topBar = {
                    WarpTopAppBar(
                        titleText = "Search Only Test",
                        searchConfig = SearchConfiguration(
                            state = searchState,
                            onSearch = {},
                            hint = "Search...",
                            collapsible = true
                        ),
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .testTag("scrollableList")
                ) {
                    items(100) { index ->
                        WarpText(
                            text = "Item $index",
                            style = WarpTextStyle.Title3,
                            modifier = Modifier.padding(com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2)
                        )
                    }
                }
            }
        }

        // Scroll to trigger collapse - swipe up multiple times to ensure full collapse
        composeTestRule.onNodeWithTag("scrollableList").performTouchInput {
            repeat(10) { swipeUp() }
        }
        // If we get here without crash, test passes
        composeTestRule.waitForIdle()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun topAppBar_titleCollapsible_scrollsWithoutCrashing() {
        composeTestRule.setContent {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

            WarpScaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                containerColor = colors.background.default,
                topBar = {
                    WarpTopAppBar(
                        titleText = "Title Collapsible Test",
                        subtitleText = "Subtitle",
                        titleCollapsible = true,
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                WarpIcon(icon = icons.arrowLeft)
                            }
                        },
                        actions = {
                            IconButton(onClick = {}) {
                                WarpIcon(icon = icons.dotsVertical)
                            }
                        },
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .testTag("scrollableList")
                ) {
                    items(100) { index ->
                        WarpText(
                            text = "Item $index",
                            style = WarpTextStyle.Title3,
                            modifier = Modifier.padding(com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2)
                        )
                    }
                }
            }
        }

        // Scroll to trigger collapse
        composeTestRule.onNodeWithTag("scrollableList").performTouchInput {
            repeat(10) { swipeUp() }
        }
        composeTestRule.waitForIdle()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun topAppBar_tabsCollapsible_scrollsWithoutCrashing() {
        composeTestRule.setContent {
            var selectedTabIndex by remember { mutableIntStateOf(0) }
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

            val tabs = listOf(
                TabData("Tab 1", "tab1", hasBadge = true),
                TabData("Tab 2", "tab2"),
                TabData("Tab 3", "tab3")
            )

            WarpScaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                containerColor = colors.background.default,
                topBar = {
                    WarpTopAppBar(
                        titleText = "Tabs Collapsible Test",
                        tabConfig = TabConfiguration(
                            tabs = tabs,
                            selectedIndex = selectedTabIndex,
                            onTabSelected = { selectedTabIndex = it },
                            collapsible = true
                        ),
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .testTag("scrollableList")
                ) {
                    items(100) { index ->
                        WarpText(
                            text = "Item $index",
                            style = WarpTextStyle.Title3,
                            modifier = Modifier.padding(com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2)
                        )
                    }
                }
            }
        }

        // Scroll to trigger collapse
        composeTestRule.onNodeWithTag("scrollableList").performTouchInput {
            repeat(10) { swipeUp() }
        }
        composeTestRule.waitForIdle()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun topAppBar_allSectionsCollapsible_scrollsWithoutCrashing() {
        composeTestRule.setContent {
            val searchState = remember { TextFieldState("") }
            var selectedTabIndex by remember { mutableIntStateOf(0) }
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

            val tabs = listOf(
                TabData("Messages", "messages", hasBadge = true),
                TabData("Favorites", "favorites"),
                TabData("Profile", "profile")
            )

            WarpScaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                containerColor = colors.background.default,
                topBar = {
                    WarpTopAppBar(
                        titleText = "All Collapsible Test",
                        subtitleText = "All sections collapse",
                        titleCollapsible = true,
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                WarpIcon(icon = icons.arrowLeft)
                            }
                        },
                        actions = {
                            IconButton(onClick = {}) {
                                WarpIcon(icon = icons.dotsVertical)
                            }
                        },
                        searchConfig = SearchConfiguration(
                            state = searchState,
                            onSearch = {},
                            hint = "Search...",
                            collapsible = true
                        ),
                        tabConfig = TabConfiguration(
                            tabs = tabs,
                            selectedIndex = selectedTabIndex,
                            onTabSelected = { selectedTabIndex = it },
                            collapsible = true
                        ),
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .testTag("scrollableList")
                ) {
                    items(100) { index ->
                        WarpText(
                            text = "Item $index",
                            style = WarpTextStyle.Title3,
                            modifier = Modifier.padding(com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2)
                        )
                    }
                }
            }
        }

        // Scroll to trigger full collapse of all sections
        composeTestRule.onNodeWithTag("scrollableList").performTouchInput {
            repeat(10) { swipeUp() }
        }
        composeTestRule.waitForIdle()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun topAppBar_searchAndTabsCollapsible_scrollsWithoutCrashing() {
        composeTestRule.setContent {
            val searchState = remember { TextFieldState("") }
            var selectedTabIndex by remember { mutableIntStateOf(0) }
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

            val tabs = listOf(
                TabData("Tab 1", "tab1"),
                TabData("Tab 2", "tab2"),
                TabData("Tab 3", "tab3")
            )

            WarpScaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                containerColor = colors.background.default,
                topBar = {
                    WarpTopAppBar(
                        titleText = "Search & Tabs Test",
                        titleCollapsible = false,
                        searchConfig = SearchConfiguration(
                            state = searchState,
                            onSearch = {},
                            hint = "Search...",
                            collapsible = true
                        ),
                        tabConfig = TabConfiguration(
                            tabs = tabs,
                            selectedIndex = selectedTabIndex,
                            onTabSelected = { selectedTabIndex = it },
                            collapsible = true
                        ),
                        scrollBehavior = scrollBehavior
                    )
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .testTag("scrollableList")
                ) {
                    items(100) { index ->
                        WarpText(
                            text = "Item $index",
                            style = WarpTextStyle.Title3,
                            modifier = Modifier.padding(com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2)
                        )
                    }
                }
            }
        }

        // Scroll to trigger collapse
        composeTestRule.onNodeWithTag("scrollableList").performTouchInput {
            repeat(10) { swipeUp() }
        }
        composeTestRule.waitForIdle()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun topAppBar_noCollapse_scrollsNormally() {
        composeTestRule.setContent {
            val searchState = remember { TextFieldState("") }
            var selectedTabIndex by remember { mutableIntStateOf(0) }

            val tabs = listOf(
                TabData("Tab 1", "tab1"),
                TabData("Tab 2", "tab2")
            )

            WarpScaffold(
                containerColor = colors.background.default,
                topBar = {
                    WarpTopAppBar(
                        titleText = "No Collapse Test",
                        titleCollapsible = false,
                        searchConfig = SearchConfiguration(
                            state = searchState,
                            onSearch = {},
                            hint = "Search...",
                            collapsible = false
                        ),
                        tabConfig = TabConfiguration(
                            tabs = tabs,
                            selectedIndex = selectedTabIndex,
                            onTabSelected = { selectedTabIndex = it },
                            collapsible = false
                        )
                    )
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .testTag("scrollableList")
                ) {
                    items(100) { index ->
                        WarpText(
                            text = "Item $index",
                            style = WarpTextStyle.Title3,
                            modifier = Modifier.padding(com.schibsted.nmp.warp.theme.WarpTheme.dimensions.space2)
                        )
                    }
                }
            }
        }

        // Scroll normally - nothing should collapse
        composeTestRule.onNodeWithTag("scrollableList").performTouchInput {
            repeat(10) { swipeUp() }
        }
        composeTestRule.waitForIdle()
    }
}
