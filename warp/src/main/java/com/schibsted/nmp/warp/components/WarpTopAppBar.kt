@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalTypography
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.typography

@Composable
fun WarpTopAppBar(
    titleText: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    topBarcolors: TopAppBarColors? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography
    ) {

        TopAppBar(
            title = { WarpText(text = titleText, style = WarpTextStyle.Title3) },
            modifier,
            navigationIcon,
            actions,
            windowInsets,
            topBarcolors?.let { it } ?: TopAppBarDefaults.smallTopAppBarColors(
                containerColor = colors.background.default,
                titleContentColor = colors.text.default,
                navigationIconContentColor = colors.icon.default,
                actionIconContentColor = colors.icon.default,
            ),
            scrollBehavior
        )
    }
}