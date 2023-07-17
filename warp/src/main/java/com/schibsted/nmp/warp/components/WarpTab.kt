package com.schibsted.nmp.warp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider

@Composable
fun WarpTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit
    ) {

    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = WarpTheme.colors.components.tabRow.container,
        contentColor = WarpTheme.colors.components.tabRow.content,
        indicator = {tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = WarpTheme.colors.components.tabRow.indicator
                    )
        },
        divider = {}, // Has no divider according to the design
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
    icon: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = { Text(text, style = WarpTheme.typography.title4)},
        selectedContentColor = WarpTheme.colors.components.tab.selectedContent,
        unselectedContentColor = WarpTheme.colors.components.tab.unselectedContent,
        icon = icon,
        interactionSource = interactionSource
    )
}

@Composable
fun WarpTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        selectedContentColor = WarpTheme.colors.components.tab.selectedContent,
        unselectedContentColor = WarpTheme.colors.components.tab.unselectedContent,
        interactionSource = interactionSource,
        content = content
    )

}

@Preview
@Composable
fun WarpTabsPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String,
) {
    WarpBrandedTheme(
        flavor,
        isSystemInDarkTheme()
    ) {
        WarpTabRow(1) {
            WarpTab(false, onClick = {}, text = "first")
            WarpTab(true, onClick = {}, text = "second")
            WarpTab(false, onClick = {}, text = "third")
        }
    }
}