package com.schibsted.nmp.warp.brands.finn

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun FinnWarpTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) FinnDarkColors else FinnColors
    val dimensions = WarpDimensions
    WarpTheme(
        colors = colors,
        typography = FinnTypography,
        shapes = FinnShapes(dimensions),
        resources = FinnResources(),
        content = content,
        dimensions = dimensions
    )
}