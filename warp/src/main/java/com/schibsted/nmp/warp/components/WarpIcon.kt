package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.schibsted.nmp.warp.theme.WarpBrandIconResource
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun WarpIcon(
    modifier: Modifier = Modifier,
    icon: WarpIconResource,
    size: Dp = dimensions.icon.default,
    color: Color = colors.icon.default,
) {
    IconView(modifier, icon, color, size)
}

@Composable
fun WarpIcon(
    modifier: Modifier = Modifier,
    identifier: String,
    size: Dp = dimensions.icon.default,
    color: Color = colors.icon.default,
) {
    val taxonomyIcon = icons.getIconByIdentifier(identifier)
    val icon = taxonomyIcon ?: icons.getIconByName(identifier)
    icon?.let {
        IconView(modifier, it, color, size)
    }
}

@Composable
private fun IconView(
    modifier: Modifier,
    icon: WarpIconResource,
    color: Color,
    size: Dp,
) {
    val iconModifier = Modifier
        .size(size)
        .then(modifier)
    val tint = if (icon is WarpBrandIconResource) {
        Color.Unspecified
    } else {
        color
    }
    Icon(
        imageVector = icon.vector,
        contentDescription = icon.description,
        tint = tint,
        modifier = iconModifier
    )
}
