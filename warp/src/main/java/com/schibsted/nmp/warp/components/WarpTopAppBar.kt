@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.heightIn
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
import com.schibsted.nmp.warp.theme.WarpTheme.colors

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
    val appBarColors = topBarColors ?: TopAppBarDefaults.topAppBarColors(
        containerColor = colors.background.default,
        titleContentColor = colors.text.default,
        navigationIconContentColor = colors.icon.default,
        actionIconContentColor = colors.icon.default,
        scrolledContainerColor = colors.background.default
    )
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