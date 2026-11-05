package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpNotificationBadge
import com.schibsted.nmp.warp.components.WarpNotificationBadgeContent
import com.schibsted.nmp.warp.components.WarpNotificationBadgeMax
import com.schibsted.nmp.warp.components.WarpNotificationBadgeStyle
import com.schibsted.nmp.warp.components.WarpSwitch
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun NotificationBadgeScreen(onUp: () -> Unit) {
    var count by remember { mutableIntStateOf(3) }
    var showCount by remember { mutableStateOf(true) }
    var primary by remember { mutableStateOf(false) }
    var maxNine by remember { mutableStateOf(false) }
    val badge = when {
        count <= 0 -> null
        showCount -> WarpNotificationBadgeContent.Count(
            count = count,
            max = if (maxNine) WarpNotificationBadgeMax.Nine else WarpNotificationBadgeMax.NinetyNine,
        )
        else -> WarpNotificationBadgeContent.Dot
    }
    val style = if (primary) WarpNotificationBadgeStyle.Primary else WarpNotificationBadgeStyle.Default

    DetailsScaffold(
        title = "WarpNotificationBadge",
        onUp = onUp,
        actions = {
            Box(
                modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .clickable(
                        interactionSource = null,
                        indication = ripple(bounded = false, radius = dimensions.space25),
                        role = Role.Button,
                        onClick = { count = 0 },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                MessagesIcon(badge = badge, style = style)
            }
        },
    ) {
        NotificationBadgeScreenContent(
            badge = badge,
            style = style,
            count = count,
            onCountChange = { count = it.coerceAtLeast(0) },
            showCount = showCount,
            onShowCountChange = { showCount = it },
            primary = primary,
            onPrimaryChange = { primary = it },
            maxNine = maxNine,
            onMaxNineChange = { maxNine = it },
        )
    }
}

@Composable
private fun NotificationBadgeScreenContent(
    badge: WarpNotificationBadgeContent?,
    style: WarpNotificationBadgeStyle,
    count: Int,
    onCountChange: (Int) -> Unit,
    showCount: Boolean,
    onShowCountChange: (Boolean) -> Unit,
    primary: Boolean,
    onPrimaryChange: (Boolean) -> Unit,
    maxNine: Boolean,
    onMaxNineChange: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier.padding(vertical = dimensions.space2),
        verticalArrangement = Arrangement.spacedBy(dimensions.space2),
    ) {
        SwitchRow(text = "Primary style", checked = primary, onCheckedChange = onPrimaryChange)
        SwitchRow(text = "Show count", checked = showCount, onCheckedChange = onShowCountChange)
        SwitchRow(text = "Max 9", checked = maxNine, onCheckedChange = onMaxNineChange)
        Row(
            modifier = Modifier.padding(horizontal = dimensions.space2),
            horizontalArrangement = Arrangement.spacedBy(dimensions.space2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WarpText(text = "Count", modifier = Modifier.weight(1f))
            WarpButton(text = "-", onClick = { onCountChange(count - 1) }, style = WarpButtonStyle.Secondary, small = true)
            WarpText(text = count.toString())
            WarpButton(text = "+", onClick = { onCountChange(count + 1) }, style = WarpButtonStyle.Secondary, small = true)
            WarpButton(text = "100", onClick = { onCountChange(100) }, style = WarpButtonStyle.Secondary, small = true)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCountChange(0) }
                .padding(horizontal = dimensions.space2, vertical = dimensions.space1),
            horizontalArrangement = Arrangement.spacedBy(dimensions.space2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MessagesIcon(badge = badge, style = style)
            WarpText(text = "Messages")
        }
    }
}

@Composable
private fun MessagesIcon(badge: WarpNotificationBadgeContent?, style: WarpNotificationBadgeStyle) {
    if (badge != null) {
        WarpNotificationBadge(badge = badge, style = style) {
            WarpIcon(icon = icons.messages)
        }
    } else {
        WarpIcon(icon = icons.messages)
    }
}

@Composable
private fun SwitchRow(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.padding(horizontal = dimensions.space2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WarpText(text = text, modifier = Modifier.weight(1f))
        WarpSwitch(checked = checked, onCheckedChange = onCheckedChange, contentDescr = text)
    }
}

@Preview
@Composable
internal fun NotificationBadgeScreenContentPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        NotificationBadgeScreenContent(
            badge = WarpNotificationBadgeContent.Count(3),
            style = WarpNotificationBadgeStyle.Default,
            count = 3,
            onCountChange = {},
            showCount = true,
            onShowCountChange = {},
            primary = false,
            onPrimaryChange = {},
            maxNine = false,
            onMaxNineChange = {},
        )
    }
}
