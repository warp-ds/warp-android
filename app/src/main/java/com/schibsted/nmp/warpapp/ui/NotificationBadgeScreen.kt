package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpNotificationBadge
import com.schibsted.nmp.warp.components.WarpNotificationBadgeContent
import com.schibsted.nmp.warp.components.WarpNotificationBadgeStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun NotificationBadgeScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpNotificationBadge", onUp = onUp) {
        NotificationBadgeScreenContent()
    }
}

@Composable
private fun NotificationBadgeScreenContent() {
    Column(
        modifier = Modifier.padding(dimensions.space2),
        verticalArrangement = Arrangement.spacedBy(dimensions.space3),
    ) {
        WarpNotificationBadgeStyle.entries.forEach { style ->
            Column(verticalArrangement = Arrangement.spacedBy(dimensions.space2)) {
                WarpText(text = style.name, style = WarpTextStyle.Title4)
                Row(horizontalArrangement = Arrangement.spacedBy(dimensions.space4)) {
                    WarpNotificationBadge(badge = WarpNotificationBadgeContent.Dot, style = style) {
                        WarpIcon(icon = icons.bell)
                    }
                    WarpNotificationBadge(badge = WarpNotificationBadgeContent.Count(3), style = style) {
                        WarpIcon(icon = icons.messages)
                    }
                    WarpNotificationBadge(badge = WarpNotificationBadgeContent.Count(100), style = style) {
                        WarpIcon(icon = icons.messages)
                    }
                    WarpNotificationBadge(badge = WarpNotificationBadgeContent.Dot, style = style) {
                        WarpText(text = "Inbox")
                    }
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(dimensions.space2)) {
            WarpText(text = "Count", style = WarpTextStyle.Title4)
            var count by remember { mutableIntStateOf(1) }
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensions.space2),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                WarpButton(text = "-", onClick = { count-- }, style = WarpButtonStyle.Secondary, small = true)
                WarpNotificationBadge(badge = WarpNotificationBadgeContent.Count(count)) {
                    WarpIcon(icon = icons.messages)
                }
                WarpButton(text = "+", onClick = { count++ }, style = WarpButtonStyle.Secondary, small = true)
            }
        }
    }
}

@Preview
@Composable
internal fun NotificationBadgeScreenContentPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        NotificationBadgeScreenContent()
    }
}
