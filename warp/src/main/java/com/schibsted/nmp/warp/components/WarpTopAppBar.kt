@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.theme.WarpTheme.colors

/**
 * @param titleText The title text.
 * @param modifier The modifier to be applied to the top app bar.
 * @param centered If true, the title will be centered.
 * @param navigationIcon The navigation icon to be displayed.
 * @param actions The actions to be displayed at the end of the top app bar.
 * @param windowInsets The window insets to be applied to the top app bar.
 * @param topBarColors The colors to be used for the top app bar. Default Warp colors will be used if nothing provided.
 * @param scrollBehavior The scroll behavior to be used for the top app bar.
 */
@Composable
fun WarpTopAppBar(
    titleText: String,
    modifier: Modifier = Modifier,
    centered: Boolean = false,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    topBarColors: TopAppBarColors? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val appBarColors = topBarColors ?: TopAppBarDefaults.topAppBarColors(
        containerColor = colors.background.default,
        titleContentColor = colors.text.default,
        navigationIconContentColor = colors.icon.default,
        actionIconContentColor = colors.icon.default,
        scrolledContainerColor = colors.background.default
    )
    if (centered) {
        CenterAlignedTopAppBar(
            title = { WarpText(text = titleText, style = WarpTextStyle.Title3) },
            modifier = modifier,
            navigationIcon = navigationIcon,
            actions = actions,
            windowInsets = windowInsets,
            colors = appBarColors,
            scrollBehavior = scrollBehavior
        )
    } else {
        TopAppBar(
            title = { WarpText(text = titleText, style = WarpTextStyle.Title3) },
            modifier = modifier,
            navigationIcon = navigationIcon,
            actions = actions,
            windowInsets = windowInsets,
            colors = appBarColors,
            scrollBehavior = scrollBehavior
        )
    }
}