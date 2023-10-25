package com.schibsted.nmp.warp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpTheme


@Composable
fun WarpBadge(
    modifier: Modifier = Modifier,
    text: String,
    style: WarpBadgeStyle = WarpBadgeStyle.Neutral,
    alignmentStyle: WarpBadgeAlignment
) {
    val backgroundColor = when (style) {
        WarpBadgeStyle.Info -> WarpTheme.colors.components.badge.infoBackground
        WarpBadgeStyle.Success -> WarpTheme.colors.components.badge.positiveBackground
        WarpBadgeStyle.Warning -> WarpTheme.colors.components.badge.warningBackground
        WarpBadgeStyle.Error -> WarpTheme.colors.components.badge.negativeBackground
        WarpBadgeStyle.Disabled -> WarpTheme.colors.components.badge.disabledBackground
        WarpBadgeStyle.Neutral -> WarpTheme.colors.components.badge.neutralBackground
    }
    val textColor = when (style) {
        WarpBadgeStyle.Info -> WarpTheme.colors.components.badge.infoText
        WarpBadgeStyle.Success -> WarpTheme.colors.components.badge.positiveText
        WarpBadgeStyle.Warning -> WarpTheme.colors.components.badge.warningText
        WarpBadgeStyle.Error -> WarpTheme.colors.components.badge.negativeText
        WarpBadgeStyle.Disabled -> WarpTheme.colors.components.badge.disabledText
        WarpBadgeStyle.Neutral -> WarpTheme.colors.components.badge.neutralText
    }
    val shape = when (alignmentStyle) {
        WarpBadgeAlignment.None -> WarpTheme.shapes.components.badge.default
        WarpBadgeAlignment.TopStart -> WarpTheme.shapes.components.badge.topStart
        WarpBadgeAlignment.TopEnd -> WarpTheme.shapes.components.badge.topEnd
        WarpBadgeAlignment.BottomStart -> WarpTheme.shapes.components.badge.bottomStart
        WarpBadgeAlignment.BottomEnd -> WarpTheme.shapes.components.badge.bottomEnd
    }
    WarpText(
        modifier = modifier
            .then(
                Modifier
                    .background(backgroundColor, shape = shape)
                    .padding(
                        horizontal = WarpTheme.dimensions.space1,
                        vertical = WarpTheme.dimensions.space05
                    )
            ),
        color = textColor,
        text = text,
        style = WarpTextStyle.Detail,
    )

}

@Composable
fun BoxScope.WarpBadge(
    modifier: Modifier = Modifier,
    style: WarpBadgeStyle = WarpBadgeStyle.Neutral,
    text: String,
    alignment: Alignment = Alignment.TopStart,
) {
    val alignmentStyle = when (alignment) {
        Alignment.TopStart, Alignment.Start -> WarpBadgeAlignment.TopStart
        Alignment.TopEnd, Alignment.End -> WarpBadgeAlignment.TopEnd
        Alignment.BottomEnd -> WarpBadgeAlignment.BottomEnd
        Alignment.BottomStart -> WarpBadgeAlignment.BottomStart
        else -> WarpBadgeAlignment.None
    }
    WarpBadge(text = text, style = style, modifier = modifier.then(Modifier.align(alignment)), alignmentStyle = alignmentStyle)
}

enum class WarpBadgeStyle {
    Info,
    Success,
    Warning,
    Error,
    Disabled,
    Neutral
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
        WarpBadgeStyle.values().forEach {
            Box() {
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
