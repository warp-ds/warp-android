package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpBottomNavItem
import com.schibsted.nmp.warp.components.WarpBottomNavigation
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun BottomNavigationScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpBottomNavigation", onUp = onUp) {
        BottomNavigationContent()
    }
}

@Composable
private fun BottomNavigationContent() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val tabLabels = listOf("Home", "Create ad", "Notifications", "Messages", "My Page")

    val items = listOf(
        WarpBottomNavItem(
            label = "Home",
            icon = { WarpIcon(icon = icons.house) },
            contentDescription = "Home"
        ),
        WarpBottomNavItem(
            label = "Create ad",
            icon = { WarpIcon(icon = icons.plus) },
            contentDescription = "Create ad"
        ),
        WarpBottomNavItem(
            label = "Notifications",
            icon = { WarpIcon(icon = icons.bell) },
            showDot = true,
            contentDescription = "Notifications"
        ),
        WarpBottomNavItem(
            label = "Messages",
            icon = { WarpIcon(icon = icons.messages) },
            badgeCount = 4,
            contentDescription = "Messages"
        ),
        WarpBottomNavItem(
            label = "My Page",
            icon = { WarpIcon(icon = icons.user) },
            contentDescription = "My Page"
        ),
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WarpText(text = "Selected: ${tabLabels[selectedIndex]}")
        }

        WarpBottomNavigation(
            modifier = Modifier.align(Alignment.BottomCenter),
            items = items,
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it }
        )
    }
}

@Preview
@Composable
internal fun BottomNavigationContentPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        BottomNavigationContent()
    }
}
