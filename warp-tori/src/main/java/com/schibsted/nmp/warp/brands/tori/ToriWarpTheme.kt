package com.schibsted.nmp.warp.brands.tori

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun ToriWarpTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) ToriDarkColors else ToriColors
    val dimensions = WarpDimensions
    WarpTheme(
        colors = colors,
        typography = ToriTypography,
        shapes = ToriShapes(dimensions),
        resources = ToriResources(),
        content = content,
        dimensions = dimensions
    )
}