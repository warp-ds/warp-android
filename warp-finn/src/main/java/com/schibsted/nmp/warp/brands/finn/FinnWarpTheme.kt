package com.schibsted.nmp.warp.brands.finn

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpRippleTheme
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun FinnWarpTheme(
    content: @Composable () -> Unit
) {
    val finnColors = if (isSystemInDarkTheme()) FinnDarkColors else FinnColors
    val finnDimensions = WarpDimensions
    val finnRippleTheme = WarpRippleTheme(rippleColor = finnColors.background.primary, rippleColorAlpha = finnColors.background.primary)
    WarpTheme(
        colors = finnColors,
        typography = FinnTypography,
        shapes = FinnShapes(finnDimensions),
        resources = FinnResources(),
        content = content,
        rippleTheme = finnRippleTheme,
        dimensions = finnDimensions
    )
}