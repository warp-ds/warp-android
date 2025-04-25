package com.schibsted.nmp.warp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextDecoration
import com.schibsted.nmp.warp.theme.WarpDimensions.adaptDpToFontScale
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A Link component in the warp design system.
 * @param modifier The modifier to be applied to the link.
 * @param text The text to be displayed in the link.
 * @param onClick The callback to be invoked when the link is clicked.
 * @param style The style of the text in the link. Defaults to [WarpTextStyle.Body].
 * @param icon The icon to be displayed in the link.
 * @param underline Whether the link should be underlined. Defaults to false.
 */
@Composable
fun WarpLink(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    style: WarpTextStyle = WarpTextStyle.Body,
    icon: WarpIconResource? = null,
    underline: Boolean = false
) {
    val textColor = colors.text.link
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .defaultMinSize(
                minWidth = dimensions.minimumTouchSize,
                minHeight = dimensions.minimumTouchSize
            )
            .semantics(mergeDescendants = true) { }
            .clickable { onClick.invoke() }
    ) {
        WarpText(
            modifier = modifier.then(Modifier.weight(if (icon != null) 0.9f else 1f, fill = false)),
            text = text,
            color = textColor,
            style = style,
            textDecoration = if (underline) TextDecoration.Underline else null
        )
        icon?.let {
            WarpIcon(
                icon = icon,
                modifier = Modifier
                    .padding(start = dimensions.space1)
                    .weight(0.1f),
                size = adaptDpToFontScale(dimensions.icon.small),
                color = textColor
            )
        }
    }
}