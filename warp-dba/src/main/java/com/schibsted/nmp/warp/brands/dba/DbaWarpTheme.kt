package com.schibsted.nmp.warp.brands.dba

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun DbaWarpTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) DbaDarkColors else DbaColors
    val dimensions = WarpDimensions
    WarpTheme(
        colors = colors,
        typography = DbaTypography,
        shapes = DbaShapes(dimensions),
        resources = DbaResources(),
        content = content,
        dimensions = dimensions
    )
}