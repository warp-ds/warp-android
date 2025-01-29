package com.schibsted.nmp.warp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * @param selectedTabIndex The index of the currently selected tab.
 * @param modifier The modifier to be applied to the tab row.
 * @param tabs The tabs to be displayed in the tab row.
 */
@Composable
fun WarpTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
        containerColor = colors.background.default,
        contentColor = colors.text.subtle,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = colors.icon.selected
            )
        },
        tabs = tabs,
    )
}

/**
 * @param selected Whether the tab is selected.
 * @param onClick The callback invoked when the tab is selected.
 * @param modifier The modifier to be applied to the tab.
 * @param text The text to be displayed in the tab.
 * @param icon The icon to be displayed in the tab.
 * @param interactionSource The interaction source to be used for the tab.
 */
@Composable
fun WarpTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
    icon: WarpIconResource? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    LeadingIconTab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        text = {
            WarpText(
                text = text,
                style = WarpTextStyle.Title4,
                softWrap = false,
                color = Color.Unspecified
            )
        },
        selectedContentColor = colors.text.link,
        unselectedContentColor = colors.text.subtle,
        icon = {
            icon?.let {
                Icon(
                    modifier = Modifier.size(dimensions.icon.default),
                    imageVector = it.vector,
                    contentDescription = it.description,
                )
            }
        },
        interactionSource = interactionSource
    )
}

@Preview
@Composable
fun WarpTabsPreview() {
    var selectedIndex by remember { mutableStateOf(0) }
    WarpTabRow(selectedIndex) {
        WarpTab(
            selectedIndex == 0,
            onClick = { selectedIndex = 0 },
            text = "First",
            icon = icons.spa
        )
        WarpTab(
            selectedIndex == 1,
            onClick = { selectedIndex = 1 },
            text = "Second",
            icon = icons.animalPaw
        )
        WarpTab(
            selectedIndex == 2,
            onClick = { selectedIndex = 2 },
            text = "Third",
            icon = icons.awardMedal
        )
    }
}