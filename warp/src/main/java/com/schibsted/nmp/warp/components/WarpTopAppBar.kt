@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpResources

/**
 * @param titleText The title text.
 * @param modifier The modifier to be applied to the top app bar.
 * @param navigationIcon The navigation icon to be displayed.
 * @param actions The actions to be displayed at the end of the top app bar.
 * @param windowInsets The window insets to be applied to the top app bar.
 * @param topBarColors The colors to be used for the top app bar. Default Warp colors will be used if nothing provided.
 * @param scrollBehavior The scroll behavior to be used for the top app bar.
 * @param searchConfig Configuration for integrated search functionality.
 * @param tabConfig Configuration for integrated tab bar.
 */
@Composable
fun WarpTopAppBar(
    titleText: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    topBarColors: TopAppBarColors? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    searchConfig: SearchConfiguration? = null,
    tabConfig: TabConfiguration? = null,
) {
    var fullHeightPx by remember(tabConfig, searchConfig) { mutableIntStateOf(0) }
    val hasMeasured = fullHeightPx > 0
    val fullHeightDp = with(LocalDensity.current) { fullHeightPx.toDp() }

    // Calculate collapse fraction and alpha
    val collapsedFraction = 1 - (scrollBehavior?.state?.collapsedFraction ?: 0f)
    val alpha = if (collapsedFraction < ALPHA_THRESHOLD) 0f else collapsedFraction

    // Animate height for search section collapse
    val animatedHeight by animateDpAsState(
        targetValue = if (hasMeasured) {
            fullHeightDp * collapsedFraction
        } else {
            Dp.Unspecified
        },
        label = "collapsingHeight"
    )

    // Auto-create scroll behavior if search is present and none provided
    val effectiveScrollBehavior = remember(searchConfig, scrollBehavior) {
        if (searchConfig != null && scrollBehavior == null) {
            TopAppBarDefaults.enterAlwaysScrollBehavior()
        } else {
            scrollBehavior
        }
    }

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
        TopAppBar(
            title = {
                WarpText(
                    text = titleText,
                    style = WarpTextStyle.Title3,
                )
            },
            modifier = Modifier,
            navigationIcon = navigationIcon,
            actions = actions,
            windowInsets = windowInsets,
            colors = appBarColors,
            scrollBehavior = effectiveScrollBehavior
        )

        // Collapsible section for search and tabs
        if (searchConfig != null || tabConfig != null) {
            Column(
                modifier = Modifier
                    .onGloballyPositioned {
                        if (!hasMeasured) fullHeightPx = it.size.height
                    }
                    .then(
                        if (hasMeasured.not()) {
                            Modifier
                        } else {
                            Modifier
                                .graphicsLayer { this.alpha = alpha }
                                .heightIn(min = 0.dp, max = animatedHeight)
                        }
                    )
            ) {
                searchConfig?.let { config ->
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
    }
}