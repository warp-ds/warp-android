@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpResources
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * Represents a tab in the top app bar.
 * @param label The text label displayed in the tab
 * @param id Unique identifier for the tab
 * @param hasBadge Whether to show a badge indicator on the tab
 */
data class TabData(
    val label: String,
    val id: String,
    val hasBadge: Boolean = false
)

/**
 * Configuration for the integrated search bar.
 * @param state TextFieldState managing the search input text
 * @param onSearch Callback when search is submitted (enter key pressed)
 * @param hint Placeholder text shown when search is empty
 * @param enabled Whether the search field is enabled
 */
data class SearchConfiguration(
    val state: TextFieldState,
    val onSearch: (String) -> Unit,
    val hint: String = "",
    val enabled: Boolean = true,
    val collapsible: Boolean = false,
)

/**
 * Configuration for the integrated tab bar.
 * @param tabs List of tabs to display
 * @param selectedIndex Index of the currently selected tab
 * @param onTabSelected Callback when a tab is selected
 * @param scrollable Whether to use scrollable tabs (for many tabs)
 */
data class TabConfiguration(
    val tabs: List<TabData>,
    val selectedIndex: Int,
    val onTabSelected: (Int) -> Unit,
    val scrollable: Boolean = false,
    val collapsible: Boolean = false,
)

private const val ALPHA_THRESHOLD = 0.3f

/**
 * Represents the collapse boundaries for a section in the top app bar.
 * @param startFraction When this section starts collapsing (0.0-1.0)
 * @param endFraction When this section finishes collapsing (0.0-1.0)
 * @param heightPx The height of this section in pixels
 */
private data class SectionBoundary(
    val startFraction: Float,
    val endFraction: Float,
    val heightPx: Int
)

/**
 * Calculate the collapse fraction for a specific section based on overall scroll progress.
 * @param overallProgress Overall collapse progress from scrollBehavior (0.0-1.0)
 * @param boundary The section boundary defining when this section collapses
 * @return Collapse fraction for this section (1.0 = fully visible, 0.0 = fully collapsed)
 */
private fun calculateSectionCollapseFraction(
    overallProgress: Float,
    boundary: SectionBoundary
): Float {
    return when {
        overallProgress < boundary.startFraction -> 1f  // Not started collapsing
        overallProgress >= boundary.endFraction -> 0f   // Fully collapsed
        else -> {  // Currently collapsing
            val sectionProgress = (overallProgress - boundary.startFraction) /
                                  (boundary.endFraction - boundary.startFraction)
            1f - sectionProgress  // Invert: 1.0 = visible, 0.0 = collapsed
        }
    }
}

/**
 * @param titleText The title text.
 * @param modifier The modifier to be applied to the top app bar.
 * @param navigationIcon The navigation icon to be displayed.
 * @param actions The actions to be displayed at the end of the top app bar.
 * @param windowInsets The window insets to be applied to the top app bar.
 * @param topBarColors The colors to be used for the top app bar. Default Warp colors will be used if nothing provided.
 * @param scrollBehavior The scroll behavior to be used for the top app bar.
 * @param subtitleText The subtitle text.
 * @param titleCollapsible Whether the title section should collapse on scroll.
 * @param searchConfig Configuration for integrated search functionality.
 * @param tabConfig Configuration for integrated tab bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpTopAppBar(
    titleText: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    topBarColors: TopAppBarColors? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    subtitleText: String = "",
    titleCollapsible: Boolean = false,
    searchConfig: SearchConfiguration? = null,
    tabConfig: TabConfiguration? = null,
) {
    // Height tracking for collapsible sections (in pixels)
    var titleHeightPx by remember(titleText, subtitleText, titleCollapsible) { mutableIntStateOf(0) }
    var searchHeightPx by remember(searchConfig) { mutableIntStateOf(0) }
    var tabsHeightPx by remember(tabConfig) { mutableIntStateOf(0) }

    val titleMeasured = titleHeightPx > 0
    val searchMeasured = searchHeightPx > 0
    val tabsMeasured = tabsHeightPx > 0

    // Convert to Dp for animations
    val searchHeightDp = with(LocalDensity.current) { searchHeightPx.toDp() }
    val tabsHeightDp = with(LocalDensity.current) { tabsHeightPx.toDp() }

    // Auto-create scrollBehavior when searchConfig is present and none provided
    // The consumer needs to use effectiveScrollBehavior.nestedScrollConnection on their Scaffold
    val effectiveScrollBehavior = if (searchConfig != null && scrollBehavior == null) {
        TopAppBarDefaults.enterAlwaysScrollBehavior()
    } else {
        scrollBehavior
    }

    // Determine if we need to manually handle title collapse
    // (when title + search/tabs are all collapsible, we can't use Material3's built-in collapse)
    val hasOtherCollapsibleSections = (searchConfig?.collapsible == true) || (tabConfig?.collapsible == true)
    val manualTitleCollapse = titleCollapsible && hasOtherCollapsibleSections
    val useMaterial3TitleCollapse = titleCollapsible && !hasOtherCollapsibleSections

    // Calculate section boundaries for sequential collapse
    val sectionBoundaries = remember(titleHeightPx, searchHeightPx, tabsHeightPx, titleCollapsible, searchConfig, tabConfig, manualTitleCollapse) {
        val boundaries = mutableMapOf<String, SectionBoundary>()
        val collapsibleSections = buildList {
            if (manualTitleCollapse && titleHeightPx > 0) add("title" to titleHeightPx)
            if (searchConfig?.collapsible == true && searchHeightPx > 0) add("search" to searchHeightPx)
            if (tabConfig?.collapsible == true && tabsHeightPx > 0) add("tabs" to tabsHeightPx)
        }

        val totalHeight = collapsibleSections.sumOf { it.second }
        if (totalHeight > 0) {
            var currentOffset = 0f
            collapsibleSections.forEach { (name, height) ->
                val sectionFraction = height.toFloat() / totalHeight
                boundaries[name] = SectionBoundary(currentOffset, currentOffset + sectionFraction, height)
                currentOffset += sectionFraction
            }
        }

        boundaries
    }

    // Calculate total collapsible height
    val totalCollapsibleHeightPx = sectionBoundaries.values.sumOf { it.heightPx }

    // Set heightOffsetLimit to track scroll for all manually-collapsible sections
    // When Material3 handles title collapse, it sets its own limit (don't interfere)
    SideEffect {
        if (effectiveScrollBehavior != null && totalCollapsibleHeightPx > 0 && !useMaterial3TitleCollapse) {
            val limit = -totalCollapsibleHeightPx.toFloat()
            if (effectiveScrollBehavior.state.heightOffsetLimit != limit) {
                effectiveScrollBehavior.state.heightOffsetLimit = limit
            }
        }
    }

    // Calculate collapse from scroll offset
    val scrollOffset = effectiveScrollBehavior?.state?.heightOffset ?: 0f

    // Map scroll offset to overall progress (0-1)
    val overallCollapsedFraction = if (totalCollapsibleHeightPx > 0) {
        (-scrollOffset / totalCollapsibleHeightPx).coerceIn(0f, 1f)
    } else {
        0f
    }

    // Calculate per-section collapse fractions
    val titleCollapseFraction = sectionBoundaries["title"]?.let {
        calculateSectionCollapseFraction(overallCollapsedFraction, it)
    } ?: 1f

    val searchCollapseFraction = sectionBoundaries["search"]?.let {
        calculateSectionCollapseFraction(overallCollapsedFraction, it)
    } ?: 1f

    val tabsCollapseFraction = sectionBoundaries["tabs"]?.let {
        calculateSectionCollapseFraction(overallCollapsedFraction, it)
    } ?: 1f

    // Animate container heights (Box height shrinks while content slides up)
    val searchAnimatedHeight by animateDpAsState(
        targetValue = (searchHeightDp * searchCollapseFraction).coerceAtLeast(0.dp),
        animationSpec = tween(durationMillis = 200),
        label = "searchContainerHeight"
    )

    val tabsAnimatedHeight by animateDpAsState(
        targetValue = (tabsHeightDp * tabsCollapseFraction).coerceAtLeast(0.dp),
        animationSpec = tween(durationMillis = 200),
        label = "tabsContainerHeight"
    )

    // Alpha fade calculations for collapsible sections
    val titleAlpha = if (titleCollapseFraction < ALPHA_THRESHOLD) 0f else titleCollapseFraction
    val searchAlpha = if (searchCollapseFraction < ALPHA_THRESHOLD) 0f else searchCollapseFraction
    val tabsAlpha = if (tabsCollapseFraction < ALPHA_THRESHOLD) 0f else tabsCollapseFraction

    Column(modifier = modifier) {
        // Define colors for the app bar
        val appBarColors = topBarColors ?: TopAppBarDefaults.topAppBarColors(
            containerColor = colors.background.default,
            titleContentColor = colors.text.default,
            navigationIconContentColor = colors.icon.default,
            actionIconContentColor = colors.icon.default,
            scrolledContainerColor = colors.background.default
        )

        // Main top app bar (always left-aligned, never centered)
        // Only let Material3 handle title collapse when search/tabs are NOT collapsible
        // Otherwise we handle all collapse manually to avoid heightOffsetLimit conflicts

        // Wrap TopAppBar in collapsible container when using manual title collapse
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (manualTitleCollapse && titleMeasured) {
                        Modifier.layout { measurable, constraints ->
                            // Measure content with unlimited height
                            val placeable = measurable.measure(constraints.copy(maxHeight = Int.MAX_VALUE))

                            // Container height shrinks as section collapses
                            val containerHeightPx = (placeable.height * titleCollapseFraction).toInt().coerceAtLeast(0)

                            layout(placeable.width, containerHeightPx) {
                                // Content slides up and fades out
                                placeable.placeWithLayer(0, 0) {
                                    alpha = titleAlpha
                                    translationY = -(placeable.height * (1f - titleCollapseFraction))
                                    clip = true
                                }
                            }
                        }
                    } else {
                        Modifier
                    }
                )
        ) {
            TopAppBar(
            title = {
                Column(
                    modifier = Modifier.onGloballyPositioned {
                        if (titleCollapsible && !titleMeasured && it.size.height > 0) {
                            titleHeightPx = it.size.height
                        }
                    }
                ) {
                    WarpText(
                        text = titleText,
                        style = WarpTextStyle.Title3,
                    )
                    if (subtitleText.isNotEmpty()) {
                        WarpText(
                            text = subtitleText,
                            style = WarpTextStyle.Body,
                        )
                    }
                }
            },
            modifier = Modifier,
            navigationIcon = navigationIcon,
            actions = actions,
            windowInsets = windowInsets,
            colors = appBarColors,
            scrollBehavior = if (useMaterial3TitleCollapse) effectiveScrollBehavior else null
        )
        }

        // Collapsible section for search only
        if (searchConfig != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (searchConfig.collapsible && searchMeasured) {
                            Modifier.layout { measurable, constraints ->
                                // Measure content with unlimited height to get natural size
                                val placeable = measurable.measure(constraints.copy(maxHeight = Int.MAX_VALUE))

                                // Container height shrinks as section collapses
                                val containerHeightPx = (placeable.height * searchCollapseFraction).toInt().coerceAtLeast(0)

                                layout(placeable.width, containerHeightPx) {
                                    // Content slides up and fades out
                                    placeable.placeWithLayer(0, 0) {
                                        alpha = searchAlpha
                                        translationY = -(placeable.height * (1f - searchCollapseFraction))
                                        clip = true
                                    }
                                }
                            }
                        } else {
                            Modifier
                        }
                    )
                    .onGloballyPositioned {
                        if (searchConfig.collapsible && !searchMeasured && it.size.height > 0) {
                            searchHeightPx = it.size.height
                        }
                    }
            ) {
                    searchConfig.let { config ->
                        SearchBar(
                            modifier = Modifier
                                .padding(
                                    horizontal = dimensions.space2,
                                    vertical = dimensions.space1
                                )
                                .fillMaxWidth(),
                        inputField = {
                            SearchBarDefaults.InputField(
                                enabled = config.enabled,
                                query = config.state.text.toString(),
                                onQueryChange = {
                                    config.state.edit { replace(0, length, it) }
                                },
                                onSearch = { config.onSearch(config.state.text.toString()) },
                                expanded = false,
                                onExpandedChange = { },
                                placeholder = {
                                    WarpText(
                                        text = config.hint,
                                        color = colors.text.placeholder,
                                        style = WarpTextStyle.Body,
                                    )
                                },
                                leadingIcon = {
                                    WarpIcon(
                                        icon = WarpResources.icons.search,
                                        size = dimensions.icon.small
                                    )
                                },
                                trailingIcon = {
                                    if (config.state.text.isNotEmpty()) {
                                        IconButton(onClick = config.state::clearText) {
                                            WarpIcon(
                                                icon = WarpResources.icons.close,
                                                size = dimensions.icon.small
                                            )
                                        }
                                    }
                                },
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = colors.text.default,
                                    focusedContainerColor = colors.background.subtle,
                                    unfocusedTextColor = colors.text.default,
                                    unfocusedContainerColor = colors.background.subtle,
                                    disabledTextColor = colors.text.disabled,
                                    focusedPlaceholderColor = colors.text.placeholder,
                                    unfocusedPlaceholderColor = colors.text.placeholder,
                                    focusedLabelColor = colors.text.subtle,
                                    unfocusedLabelColor = colors.text.subtle,
                                    cursorColor = colors.icon.default,
                                )
                            )
                        },
                        expanded = false,
                        onExpandedChange = { },
                        colors = SearchBarDefaults.colors(
                            containerColor = colors.background.subtle
                        ),
                        content = {},
                        windowInsets = WindowInsets(
                            left = 0.dp,
                            top = 0.dp,
                            right = 0.dp,
                            bottom = 0.dp,
                        )
                    )
                }
            }
        }

        // Tabs section
        tabConfig?.let { config ->
            if (config.tabs.isEmpty()) return@let

            val selectedIndex = config.selectedIndex.coerceIn(0, config.tabs.lastIndex)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (config.collapsible && tabsMeasured) {
                            Modifier.layout { measurable, constraints ->
                                // Measure content with unlimited height to get natural size
                                val placeable = measurable.measure(constraints.copy(maxHeight = Int.MAX_VALUE))

                                // Container height shrinks as section collapses
                                val containerHeightPx = (placeable.height * tabsCollapseFraction).toInt().coerceAtLeast(0)

                                layout(placeable.width, containerHeightPx) {
                                    // Content slides up and fades out
                                    placeable.placeWithLayer(0, 0) {
                                        alpha = tabsAlpha
                                        translationY = -(placeable.height * (1f - tabsCollapseFraction))
                                        clip = true
                                    }
                                }
                            }
                        } else {
                            Modifier
                        }
                    )
                    .onGloballyPositioned {
                        if (config.collapsible && !tabsMeasured && it.size.height > 0) {
                            tabsHeightPx = it.size.height
                        }
                    }
            ) {
                    val tabsContent: @Composable () -> Unit = {
                config.tabs.forEachIndexed { index, tab ->
                    Tab(
                        selectedContentColor = colors.background.active,
                        selected = selectedIndex == index,
                        onClick = {
                            config.onTabSelected(index)
                        },
                        text = {
                            Box {
                                WarpText(
                                    text = tab.label,
                                    style = WarpTextStyle.Title4,
                                )
                                // Badge indicator
                                if (tab.hasBadge) {
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .offset(x = 8.dp, y = (-4).dp)
                                            .size(8.dp)
                                            .background(
                                                color = colors.background.notification,
                                                shape = CircleShape
                                            )
                                    )
                                }
                            }
                        }
                    )
                }
            }

            if (config.scrollable) {
                PrimaryScrollableTabRow(
                    selectedTabIndex = selectedIndex,
                    edgePadding = dimensions.space2,
                    containerColor = colors.background.default,
                    contentColor = colors.text.default,
                    indicator = {
                        TabRowDefaults.PrimaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(
                                selectedIndex,
                                matchContentSize = true
                            ),
                            color = colors.icon.active,
                            width = Dp.Unspecified,
                        )
                    },
                    divider = {},
                    tabs = tabsContent
                )
            } else {
                PrimaryTabRow(
                    selectedTabIndex = selectedIndex,
                    containerColor = colors.background.default,
                    contentColor = colors.text.default,
                    indicator = {
                        TabRowDefaults.PrimaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(
                                selectedIndex,
                                matchContentSize = true
                            ),
                            color = colors.icon.active,
                            width = Dp.Unspecified,
                        )
                    },
                    divider = {},
                    tabs = tabsContent
                )
            }
            }
        }
    }
}