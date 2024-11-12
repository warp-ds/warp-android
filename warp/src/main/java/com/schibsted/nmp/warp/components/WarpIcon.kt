package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun WarpIcon(
    icon: Pair<String, ImageVector>,
    size: Dp = dimensions.icon.default,
    color: Color = Color.Unspecified,

) {
    val (name, vector) = icon
    IconView(vector, name, color, size)
}

@Composable
private fun IconView(
    vector: ImageVector,
    name: String,
    color: Color,
    size: Dp
) {
    Icon(
        imageVector = vector,
        contentDescription = name,
        tint = color,
        modifier = Modifier.size(size)
    )
}