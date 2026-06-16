package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpNavBarLayout
import com.schibsted.nmp.warp.components.WarpNavItem
import com.schibsted.nmp.warp.components.WarpNavigationBar
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

    val tabLabels = listOf("Home", "Activity", "Sell", "Messages", "Profile")

    val items = listOf(
        WarpNavItem(
            label = "Home",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.houseFilled else icons.house, color = color) },
        ),
        WarpNavItem(
            label = "Activity",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.bellFilled else icons.bell, color = color) },
            showDot = true,
        ),
        WarpNavItem(
            label = "Sell",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.circlePlusFilled else icons.circlePlus, color = color) },
        ),
        WarpNavItem(
            label = "Messages",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.messagesFilled else icons.messages, color = color) },
            badgeCount = 4,
        ),
        WarpNavItem(
            label = "Profile",
            icon = { color, selected -> WarpIcon(icon = if (selected) icons.circleUserFilled else icons.circleUser, color = color) },
        ),
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            WarpText(text = "Selected: ${tabLabels[selectedIndex]}")
        }

        WarpNavigationBar(
            items = items,
            selectedIndex = selectedIndex,
            onItemSelected = { selectedIndex = it },
            modifier = Modifier
                .align(Alignment.BottomCenter),
        )
    }
}

@Preview
@Composable
internal fun NavigationBarContentPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String,
) {
    BrandTheme(flavor = flavor).invoke {
        NavigationBarContent()
    }
}

@Preview(name = "WarpNavigationBar — horizontal", showBackground = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
internal fun WarpNavBarHorizontalPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String,
) {
    BrandTheme(flavor = flavor).invoke {
        WarpNavigationBar(
            items = listOf(
                WarpNavItem("Home", { color, sel -> WarpIcon(icon = if (sel) icons.houseFilled else icons.house, color = color) }),
                WarpNavItem("Activity", { color, sel -> WarpIcon(icon = if (sel) icons.bellFilled else icons.bell, color = color) }, showDot = true),
                WarpNavItem("Sell", { color, sel -> WarpIcon(icon = if (sel) icons.circlePlusFilled else icons.circlePlus, color = color) }),
                WarpNavItem("Messages", { color, sel -> WarpIcon(icon = if (sel) icons.messagesFilled else icons.messages, color = color) }, badgeCount = 4),
                WarpNavItem("Profile", { color, sel -> WarpIcon(icon = if (sel) icons.circleUserFilled else icons.circleUser, color = color) }),
            ),
            selectedIndex = 0,
            onItemSelected = {},
            layout = WarpNavBarLayout.Horizontal,
        )
    }
}
