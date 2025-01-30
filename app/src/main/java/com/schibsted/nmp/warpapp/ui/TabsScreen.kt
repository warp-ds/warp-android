package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.components.WarpTab
import com.schibsted.nmp.warp.components.WarpTabRow
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun TabsScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpTabs", onUp = onUp) {
        TabsContent()
    }
}

@Composable
private fun TabsContent() {
    var selectedTab by remember { mutableStateOf(0) }
    var selectedTab2 by remember { mutableStateOf(0) }
    var selectedTab3 by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WarpTheme.colors.background.default)
    ) {
        WarpText(
            text = "WarpTabs without icon",
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(dimensions.space3)
        )
        WarpTabRow(selectedTabIndex = selectedTab) {
            WarpTab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                text = "Loooong Option 1"
            )
            WarpTab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                text = "Option 2"
            )
            WarpTab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                text = "Option 3"
            )
        }
        WarpText(
            text = "WarpTabs with icon",
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(dimensions.space3)
        )

        WarpTabRow(
            selectedTabIndex = selectedTab2,
            modifier = Modifier.padding(top = dimensions.space2)
        ) {
            WarpTab(
                selected = selectedTab2 == 0,
                onClick = { selectedTab2 = 0 },
                icon = icons.spa,
                text = "Option 1"
            )
            WarpTab(
                selected = selectedTab2 == 1,
                onClick = { selectedTab2 = 1 },
                icon = icons.animalPaw,
                text = "Option 2"
            )
            WarpTab(
                selected = selectedTab2 == 2,
                onClick = { selectedTab2 = 2 },
                icon = icons.awardMedal,
                text = "Option 3"
            )
        }

        WarpText(
            text = "Scrollable WarpTabs",
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(dimensions.space3)
        )
        WarpTabRow(selectedTabIndex = selectedTab3, scrollable = true) {
            WarpTab(
                selected = selectedTab3 == 0,
                onClick = { selectedTab3 = 0 },
                text = "Option 1"
            )
            WarpTab(
                selected = selectedTab3 == 1,
                onClick = { selectedTab3 = 1 },
                text = "Option 2"
            )
            WarpTab(
                selected = selectedTab3 == 2,
                onClick = { selectedTab3 = 2 },
                text = "Option 3"
            )
            WarpTab(
                selected = selectedTab3 == 3,
                onClick = { selectedTab3 = 3 },
                text = "Option 4"
            )
            WarpTab(
                selected = selectedTab3 == 4,
                onClick = { selectedTab3 = 4 },
                text = "Option 5"
            )
            WarpTab(
                selected = selectedTab3 == 5,
                onClick = { selectedTab3 = 5 },
                text = "Option 6"
            )
            WarpTab(
                selected = selectedTab3 == 6,
                onClick = { selectedTab3 = 6 },
                text = "Option 7"
            )
            WarpTab(
                selected = selectedTab3 == 7,
                onClick = { selectedTab3 = 7 },
                text = "Option 8"
            )
        }
    }
}

@Preview
@Composable
private fun WarpTabsPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        TabsContent()
    }
}
