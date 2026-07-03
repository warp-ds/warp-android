package com.schibsted.snapshot

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.HtmlReportWriter
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.SnapshotVerifier
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.schibsted.nmp.warp.components.SearchConfiguration
import com.schibsted.nmp.warp.components.TabConfiguration
import com.schibsted.nmp.warp.components.TabData
import com.schibsted.nmp.warp.components.WarpAppBarStyle
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpScaffold
import com.schibsted.nmp.warp.components.WarpTopAppBar
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class WarpTopAppBarTest(
    @TestParameter val flavor: Flavor,
    @TestParameter val nightMode: NightMode
) {
    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            nightMode = nightMode
        ),
        theme = "android:Theme.Material.Light.NoActionBar",
        renderingMode = SessionParams.RenderingMode.V_SCROLL,
        snapshotHandler = if (Config.isVerifying) {
            SnapshotVerifier(
                maxPercentDifference = Config.maxPercentDifference,
                rootDirectory = flavor.dir
            )
        } else {
            HtmlReportWriter(snapshotRootDirectory = flavor.dir)
        }
    )

    @Test
    fun warp_top_app_bar_title() {
        topAppBar()
    }

    @Test
    fun warp_top_app_bar_with_icons() {
        topAppBar(showIcons = true)
    }

    @Test
    fun warp_top_app_bar_with_search() {
        topAppBarWithSearch()
    }

    @Test
    fun warp_top_app_bar_with_search_and_icons() {
        topAppBarWithSearch(showIcons = true)
    }

    @Test
    fun warp_top_app_bar_with_tabs() {
        topAppBarWithTabs()
    }

    @Test
    fun warp_top_app_bar_with_tabs_and_icons() {
        topAppBarWithTabs(showIcons = true)
    }

    @Test
    fun warp_top_app_bar_with_search_and_tabs() {
        topAppBarWithSearchAndTabs()
    }

    @Test
    fun warp_top_app_bar_with_collapsible_title() {
        topAppBarWithCollapsibleTitle()
    }

    @Test
    fun warp_top_app_bar_with_all_sections_collapsible() {
        topAppBarWithAllSectionsCollapsible()
    }

    @Test
    fun warp_top_app_bar_with_tabs_collapsible() {
        topAppBarWithTabsCollapsible()
    }

    @Test
    fun warp_medium_flexible_top_app_bar() {
        mediumFlexibleTopAppBar()
    }

    @Test
    fun warp_medium_flexible_top_app_bar_without_subtitle() {
        mediumFlexibleTopAppBar(showSubtitle = false)
    }

    @Test
    fun warp_medium_flexible_top_app_bar_with_icons() {
        mediumFlexibleTopAppBar(showIcons = true)
    }

    @Test
    fun warp_medium_flexible_top_app_bar_with_search() {
        mediumFlexibleTopAppBarWithSearch()
    }

    @Test
    fun warp_medium_flexible_top_app_bar_with_tabs() {
        mediumFlexibleTopAppBarWithTabs()
    }

    @Test
    fun warp_medium_flexible_top_app_bar_with_search_and_tabs() {
        mediumFlexibleTopAppBarWithSearchAndTabs()
    }


    @OptIn(ExperimentalMaterial3Api::class)
    private fun topAppBar(showIcons: Boolean = false) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            navigationIcon = {
                                if (showIcons) {
                                    IconButton(
                                        onClick = {}
                                    ) {
                                        WarpIcon(icon = icons.arrowLeft)
                                    }
                                }
                            },
                            actions = {
                                if (showIcons) {
                                    IconButton(onClick = { }) {
                                        WarpIcon(
                                            icon = icons.dotsVertical
                                        )
                                    }
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier
                        .height(300.dp)
                ) {

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun topAppBarWithSearch(showIcons: Boolean = false) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                val searchState = remember { TextFieldState("Search query") }
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            searchConfig = SearchConfiguration(
                                state = searchState,
                                onSearch = {},
                                hint = "Search"
                            ),
                            navigationIcon = {
                                if (showIcons) {
                                    IconButton(
                                        onClick = {}
                                    ) {
                                        WarpIcon(icon = icons.arrowLeft)
                                    }
                                }
                            },
                            actions = {
                                if (showIcons) {
                                    IconButton(onClick = { }) {
                                        WarpIcon(
                                            icon = icons.dotsVertical
                                        )
                                    }
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier
                        .height(300.dp)
                ) {

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun topAppBarWithTabs(showIcons: Boolean = false) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            tabConfig = TabConfiguration(
                                tabs = listOf(
                                    TabData(label = "Tab 1", id = "tab1"),
                                    TabData(label = "Tab 2", id = "tab2"),
                                    TabData(label = "Tab 3", id = "tab3")
                                ),
                                selectedIndex = 0,
                                onTabSelected = {}
                            ),
                            navigationIcon = {
                                if (showIcons) {
                                    IconButton(
                                        onClick = {}
                                    ) {
                                        WarpIcon(icon = icons.arrowLeft)
                                    }
                                }
                            },
                            actions = {
                                if (showIcons) {
                                    IconButton(onClick = { }) {
                                        WarpIcon(
                                            icon = icons.dotsVertical
                                        )
                                    }
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier
                        .height(300.dp)
                ) {

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun topAppBarWithSearchAndTabs() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                val searchState = remember { TextFieldState("Search query") }
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            searchConfig = SearchConfiguration(
                                state = searchState,
                                onSearch = {},
                                hint = "Search"
                            ),
                            tabConfig = TabConfiguration(
                                tabs = listOf(
                                    TabData(label = "Tab 1", id = "tab1"),
                                    TabData(label = "Tab 2", id = "tab2"),
                                    TabData(label = "Tab 3", id = "tab3")
                                ),
                                selectedIndex = 1,
                                onTabSelected = {}
                            ),
                            navigationIcon = {
                                IconButton(
                                    onClick = {}
                                ) {
                                    WarpIcon(icon = icons.arrowLeft)
                                }
                            },
                            actions = {
                                IconButton(onClick = { }) {
                                    WarpIcon(
                                        icon = icons.dotsVertical
                                    )
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier
                        .height(400.dp)
                ) {

                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun topAppBarWithCollapsibleTitle() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            subtitleText = "Collapsible Title",
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
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier.height(300.dp)
                ) {}
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun topAppBarWithAllSectionsCollapsible() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                val searchState = remember { TextFieldState("Search query") }
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            subtitleText = "All Collapsible",
                            titleCollapsible = true,
                            searchConfig = SearchConfiguration(
                                state = searchState,
                                onSearch = {},
                                hint = "Search",
                                collapsible = true
                            ),
                            tabConfig = TabConfiguration(
                                tabs = listOf(
                                    TabData(label = "Tab 1", id = "tab1"),
                                    TabData(label = "Tab 2", id = "tab2"),
                                    TabData(label = "Tab 3", id = "tab3")
                                ),
                                selectedIndex = 0,
                                onTabSelected = {},
                                collapsible = true
                            ),
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    WarpIcon(icon = icons.arrowLeft)
                                }
                            },
                            actions = {
                                IconButton(onClick = {}) {
                                    WarpIcon(icon = icons.dotsVertical)
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier.height(450.dp)
                ) {}
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun topAppBarWithTabsCollapsible() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            tabConfig = TabConfiguration(
                                tabs = listOf(
                                    TabData(label = "Tab 1", id = "tab1", hasBadge = true),
                                    TabData(label = "Tab 2", id = "tab2"),
                                    TabData(label = "Tab 3", id = "tab3")
                                ),
                                selectedIndex = 0,
                                onTabSelected = {},
                                collapsible = true
                            ),
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    WarpIcon(icon = icons.arrowLeft)
                                }
                            },
                            actions = {
                                IconButton(onClick = {}) {
                                    WarpIcon(icon = icons.dotsVertical)
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier.height(300.dp)
                ) {}
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun mediumFlexibleTopAppBar(
        showIcons: Boolean = false,
        showSubtitle: Boolean = true,
    ) {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            subtitleText = if (showSubtitle) "Medium Flexible subtitle" else "",
                            style = WarpAppBarStyle.MediumFlexible,
                            navigationIcon = {
                                if (showIcons) {
                                    IconButton(onClick = {}) {
                                        WarpIcon(icon = icons.arrowLeft)
                                    }
                                }
                            },
                            actions = {
                                if (showIcons) {
                                    IconButton(onClick = {}) {
                                        WarpIcon(icon = icons.dotsVertical)
                                    }
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier.height(300.dp)
                ) {}
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun mediumFlexibleTopAppBarWithSearch() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                val searchState = remember { TextFieldState("") }
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            subtitleText = "Medium Flexible subtitle",
                            style = WarpAppBarStyle.MediumFlexible,
                            searchConfig = SearchConfiguration(
                                state = searchState,
                                onSearch = {},
                                hint = "Search"
                            ),
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    WarpIcon(icon = icons.arrowLeft)
                                }
                            },
                            actions = {
                                IconButton(onClick = {}) {
                                    WarpIcon(icon = icons.dotsVertical)
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier.height(400.dp)
                ) {}
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun mediumFlexibleTopAppBarWithTabs() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            subtitleText = "Medium Flexible subtitle",
                            style = WarpAppBarStyle.MediumFlexible,
                            tabConfig = TabConfiguration(
                                tabs = listOf(
                                    TabData(label = "Tab 1", id = "tab1"),
                                    TabData(label = "Tab 2", id = "tab2"),
                                    TabData(label = "Tab 3", id = "tab3")
                                ),
                                selectedIndex = 0,
                                onTabSelected = {}
                            ),
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    WarpIcon(icon = icons.arrowLeft)
                                }
                            },
                            actions = {
                                IconButton(onClick = {}) {
                                    WarpIcon(icon = icons.dotsVertical)
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier.height(400.dp)
                ) {}
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun mediumFlexibleTopAppBarWithSearchAndTabs() {
        paparazzi.snapshot {
            WarpTheme(flavor = flavor) {
                val searchState = remember { TextFieldState("") }
                WarpScaffold(
                    topBar = {
                        WarpTopAppBar(
                            titleText = "Warp Android",
                            subtitleText = "Medium Flexible subtitle",
                            style = WarpAppBarStyle.MediumFlexible,
                            searchConfig = SearchConfiguration(
                                state = searchState,
                                onSearch = {},
                                hint = "Search",
                                collapsible = true
                            ),
                            tabConfig = TabConfiguration(
                                tabs = listOf(
                                    TabData(label = "Tab 1", id = "tab1"),
                                    TabData(label = "Tab 2", id = "tab2"),
                                    TabData(label = "Tab 3", id = "tab3")
                                ),
                                selectedIndex = 0,
                                onTabSelected = {},
                                collapsible = true
                            ),
                            navigationIcon = {
                                IconButton(onClick = {}) {
                                    WarpIcon(icon = icons.arrowLeft)
                                }
                            },
                            actions = {
                                IconButton(onClick = {}) {
                                    WarpIcon(icon = icons.dotsVertical)
                                }
                            }
                        )
                    },
                    containerColor = colors.surface.sunken,
                    modifier = Modifier.height(500.dp)
                ) {}
            }
        }
    }
}