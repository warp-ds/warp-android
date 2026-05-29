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
import com.schibsted.nmp.warp.components.WarpNavItem
import com.schibsted.nmp.warp.components.WarpNavigationBar
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun NavigationBarScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpNavigationBar", onUp = onUp) {
        NavigationBarContent()
    }
}

@Composable
private fun NavigationBarContent() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    val tabLabels = listOf("Home", "Create ad", "Notifications", "Messages", "My Page")

    val items = listOf(
        WarpNavItem(
            label = "Home",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.houseFilled else icons.house, color = color) },
            contentDescription = "Home"
        ),
        WarpNavItem(
            label = "Create ad",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.circlePlusFilled else icons.circlePlus, color = color) },
            contentDescription = "Create ad"
        ),
        WarpNavItem(
            label = "Notifications",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.bellFilled else icons.bell, color = color) },
            showDot = true,
            contentDescription = "Notifications"
        ),
        WarpNavItem(
            label = "Messages",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.messagesFilled else icons.messages, color = color) },
            badgeCount = 4,
            contentDescription = "Messages"
        ),
        WarpNavItem(
            label = "My Page",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.circleUserFilled else icons.circleUser, color = color) },
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

        WarpNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            items = items,
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it }
        )
    }
}

@Preview
@Composable
internal fun NavigationBarContentPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        NavigationBarContent()
    }
}
