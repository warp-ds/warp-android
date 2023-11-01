package com.schibsted.nmp.warp.brands.blocket

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun BlocketWarpTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) BlocketDarkColors else BlocketColors
    val dimensions = WarpDimensions
    WarpTheme(
        colors = colors,
        typography = BlocketTypography,
        shapes = BlocketShapes(dimensions),
        resources = BlocketResources(),
        content = content,
        dimensions = dimensions
    )
}