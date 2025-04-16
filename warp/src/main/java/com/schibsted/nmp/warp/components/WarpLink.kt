package com.schibsted.nmp.warp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextDecoration
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A Link component in the warp design system.
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
            .then(modifier),
    ) {
        WarpText(
            modifier = Modifier.weight(if (icon != null) 0.9f else 1f, fill = false),
            text = text,
            color = textColor,
            style = style,
            textDecoration = if (underline) TextDecoration.Underline else null
        )
        icon?.let {
            WarpIcon(
                icon = icon,
                modifier = Modifier
                    .weight(0.1f),
                size = dimensions.icon.small,
                color = textColor
            )
        }
    }
}