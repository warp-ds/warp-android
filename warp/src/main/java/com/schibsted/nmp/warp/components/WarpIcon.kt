package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.schibsted.nmp.warp.theme.WarpBrandIconResource
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun WarpIcon(
    icon: WarpIconResource,
    size: Dp = dimensions.icon.default,
    color: Color = colors.icon.default,
) {
    val tint = if(icon is WarpBrandIconResource) {
        Color.Unspecified
    } else {
        color
    }
    IconView(icon.vector, icon.description, tint, size)
}

@Composable
private fun IconView(
    vector: ImageVector,
    description: String,
    color: Color,
    size: Dp
) {
    Icon(
        imageVector = vector,
        contentDescription = description,
        tint = color,
        modifier = Modifier.size(size)
    )
}