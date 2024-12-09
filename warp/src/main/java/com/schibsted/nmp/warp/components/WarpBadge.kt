package com.schibsted.nmp.warp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes

/**
 * A badge in the warp design system that displays a text label with a background color.
 * Supports different styles: info, success, warning, error, disabled, neutral, sponsored and price.
 * Supports different alignments: topStart, topEnd, bottomStart, bottomEnd and none.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/badge/)
 *
 *
 * @param modifier Modifier to be applied to the badge.
 * @param text The badge text.
 * @param style The badge style. Defaults to [WarpBadgeStyle.Neutral].
 * @param alignmentStyle The badge alignment style. Defaults to [WarpBadgeAlignment.None].
 * @param icon The badge icon.
 */
@Composable
fun WarpBadge(
    modifier: Modifier = Modifier,
    text: String,
    style: WarpBadgeStyle = WarpBadgeStyle.Neutral,
    alignmentStyle: WarpBadgeAlignment = WarpBadgeAlignment.None,
    icon: WarpIconResource? = null
) {
    val backgroundColor = when (style) {
        WarpBadgeStyle.Info -> colors.components.badge.infoBackground
        WarpBadgeStyle.Success -> colors.components.badge.positiveBackground
        WarpBadgeStyle.Warning -> colors.components.badge.warningBackground
        WarpBadgeStyle.Error -> colors.components.badge.negativeBackground
        WarpBadgeStyle.Disabled -> colors.background.disabled
        WarpBadgeStyle.Neutral -> colors.components.badge.neutralBackground
        WarpBadgeStyle.Sponsored -> colors.components.badge.sponsoredBackground
        WarpBadgeStyle.Price -> colors.components.badge.priceBackground
    }
    val textColor = when (style) {
        WarpBadgeStyle.Info,
        WarpBadgeStyle.Success,
        WarpBadgeStyle.Warning,
        WarpBadgeStyle.Error,
        WarpBadgeStyle.Disabled,
        WarpBadgeStyle.Neutral,
        WarpBadgeStyle.Sponsored -> colors.text.default

        WarpBadgeStyle.Price -> colors.text.invertedStatic
    }
    val shape = when (alignmentStyle) {
        WarpBadgeAlignment.None -> shapes.components.badge.default
        WarpBadgeAlignment.TopStart -> shapes.components.badge.topStart
        WarpBadgeAlignment.TopEnd -> shapes.components.badge.topEnd
        WarpBadgeAlignment.BottomStart -> shapes.components.badge.bottomStart
        WarpBadgeAlignment.BottomEnd -> shapes.components.badge.bottomEnd
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .then(
                Modifier
                    .background(backgroundColor, shape = shape)
                    .padding(
                        horizontal = dimensions.space1,
                        vertical = dimensions.space05
                    )
            ),
    ) {
        icon?.let {
            WarpIcon(
                icon = icon,
                modifier = Modifier.padding(end = dimensions.space05),
                size = dimensions.icon.small,
                color = textColor
            )
        }
        WarpText(
            color = textColor,
            text = text,
            style = WarpTextStyle.Detail,
        )
    }
}

@Composable
fun BoxScope.WarpBadge(
    modifier: Modifier = Modifier,
    style: WarpBadgeStyle = WarpBadgeStyle.Neutral,
    text: String,
    alignment: Alignment = Alignment.TopStart,
    icon: WarpIconResource? = null
) {
    val alignmentStyle = when (alignment) {
        Alignment.TopStart, Alignment.Start -> WarpBadgeAlignment.TopStart
        Alignment.TopEnd, Alignment.End -> WarpBadgeAlignment.TopEnd
        Alignment.BottomEnd -> WarpBadgeAlignment.BottomEnd
        Alignment.BottomStart -> WarpBadgeAlignment.BottomStart
        else -> WarpBadgeAlignment.None
    }
    WarpBadge(
        text = text,
        style = style,
        modifier = modifier.then(Modifier.align(alignment)),
        alignmentStyle = alignmentStyle,
        icon = icon
    )
}

enum class WarpBadgeStyle {
    Info,
    Success,
    Warning,
    Error,
    Disabled,
    Neutral,
    Sponsored,
    Price
}

enum class WarpBadgeAlignment {
    None,
    TopStart,
    TopEnd,
    BottomStart,
    BottomEnd
}

@Preview
@Composable
fun WarpBadgePreview() {
    Column {
        WarpBadgeStyle.entries.forEach {
            Box {
                WarpBadge(
                    modifier = Modifier.padding(4.dp),
                    style = it,
                    text = "WarpBadge",
                    alignment = Alignment.TopEnd
                )
            }
        }
    }
}
