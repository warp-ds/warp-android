package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A notification badge in the warp design system, used to indicate a notification, item count or
 * other information relating to a navigation destination. The badge is placed on the ending edge
 * of [content], typically an icon.
 * Not to be confused with [WarpBadge], which is a text label.
 * For more info, look [here](https://m3.material.io/components/badges/overview)
 *
 * @param modifier Modifier to be applied to the badged content.
 * @param badge What the badge shows: a [WarpNotificationBadgeContent.Dot] or a
 * [WarpNotificationBadgeContent.Count].
 * @param style The badge style. Defaults to [WarpNotificationBadgeStyle.Default].
 * @param content The anchor the badge is placed on.
 */
@Composable
fun WarpNotificationBadge(
    modifier: Modifier = Modifier,
    badge: WarpNotificationBadgeContent,
    style: WarpNotificationBadgeStyle = WarpNotificationBadgeStyle.Default,
    content: @Composable BoxScope.() -> Unit,
) {
    BadgedBox(
        modifier = modifier,
        badge = {
            when (badge) {
                WarpNotificationBadgeContent.Dot -> NotificationBadge(text = null, style = style)
                is WarpNotificationBadgeContent.Count -> if (badge.count > 0) {
                    NotificationBadge(
                        text = if (badge.count > badge.max.value) "${badge.max.value}+" else badge.count.toString(),
                        style = style,
                    )
                }
            }
        },
        content = content,
    )
}

/**
 * What a [WarpNotificationBadge] shows.
 */
sealed interface WarpNotificationBadgeContent {
    /**
     * A small dot indicating a new item.
     */
    data object Dot : WarpNotificationBadgeContent

    /**
     * An item count. Counts above [max] are shown as e.g. "99+". Counts of 0 or less are not shown.
     *
     * @param count The number of items.
     * @param max The highest count shown before it is capped. Defaults to
     * [WarpNotificationBadgeMax.NinetyNine].
     */
    data class Count(
        val count: Int,
        val max: WarpNotificationBadgeMax = WarpNotificationBadgeMax.NinetyNine,
    ) : WarpNotificationBadgeContent
}

/**
 * The highest count a [WarpNotificationBadgeContent.Count] shows before it is capped with a "+".
 */
enum class WarpNotificationBadgeMax {
    /** Counts above 9 are shown as "9+". */
    Nine,

    /** Counts above 99 are shown as "99+". */
    NinetyNine,
}

private val WarpNotificationBadgeMax.value: Int
    get() = when (this) {
        WarpNotificationBadgeMax.Nine -> 9
        WarpNotificationBadgeMax.NinetyNine -> 99
    }

@Composable
private fun NotificationBadge(
    text: String?,
    style: WarpNotificationBadgeStyle,
) {
    val containerColor = when (style) {
        WarpNotificationBadgeStyle.Default -> colors.background.notification
        WarpNotificationBadgeStyle.Primary -> colors.background.primary
    }
    val textColor = colors.text.inverted

    Badge(
        containerColor = containerColor,
        contentColor = textColor,
        content = text?.let {
            {
                WarpText(
                    text = it,
                    color = textColor,
                    style = WarpTextStyle.Title6,
                    maxLines = 1,
                )
            }
        },
    )
}

enum class WarpNotificationBadgeStyle {
    Default,
    Primary
}

@Preview(showBackground = true)
@Composable
private fun WarpNotificationBadgePreview() {
    Row(
        modifier = Modifier.padding(dimensions.space2),
        horizontalArrangement = Arrangement.spacedBy(dimensions.space4),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WarpNotificationBadgeStyle.entries.forEach { style ->
            WarpNotificationBadge(badge = WarpNotificationBadgeContent.Dot, style = style) {
                WarpIcon(icon = icons.bell)
            }
            WarpNotificationBadge(badge = WarpNotificationBadgeContent.Count(3), style = style) {
                WarpIcon(icon = icons.messages)
            }
            WarpNotificationBadge(badge = WarpNotificationBadgeContent.Count(100), style = style) {
                WarpIcon(icon = icons.messages)
            }
            WarpNotificationBadge(
                badge = WarpNotificationBadgeContent.Count(10, max = WarpNotificationBadgeMax.Nine),
                style = style,
            ) {
                WarpIcon(icon = icons.messages)
            }
        }
    }
}
