package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpTab
import com.schibsted.nmp.warp.components.WarpTabRow
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

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        WarpTabRow(selectedTabIndex = selectedTab) {
            WarpTab(
                selected = selectedTab == 0,
                onClick = { selectedTab = 0 },
                icon = {},
                text = "Option 1"
            )
            WarpTab(
                selected = selectedTab == 1,
                onClick = { selectedTab = 1 },
                icon = {},
                text = "Option 2"
            )
            WarpTab(
                selected = selectedTab == 2,
                onClick = { selectedTab = 2 },
                icon = {},
                text = "Option 3"
            )
        }

        WarpTabRow(selectedTabIndex = selectedTab2, modifier = Modifier.padding(top = 16.dp)) {
            WarpTab(
                selected = selectedTab2 == 0,
                onClick = { selectedTab2 = 0 },
                icon = { Icon(Icons.Filled.Info, "First Option")},
                text = "Option 1"
            )
            WarpTab(
                selected = selectedTab2 == 1,
                onClick = { selectedTab2 = 1 },
                icon = { Icon(Icons.Filled.DateRange, "Second Option")},
                text = "Option 2"
            )
            WarpTab(
                selected = selectedTab2 == 2,
                onClick = { selectedTab2 = 2 },
                icon = { Icon(Icons.Filled.Email, "Third Option")},
                text = "Option 3"
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