package com.schibsted.nmp.warp.brands.blocket

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpResources
import com.schibsted.nmp.warp.theme.WarpRippleTheme
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun BlocketWarpTheme(
    content: @Composable () -> Unit,
    forceDarkMode: Boolean? = null
) {
    val blocketColors = forceDarkMode?.let { if (it) BlocketDarkColors else BlocketColors }
        ?: if (isSystemInDarkTheme()) BlocketDarkColors else BlocketColors
    val blocketDimensions = WarpDimensions
    val blocketResources = WarpResources
    val blocketRippleTheme = WarpRippleTheme(
        rippleColor = blocketColors.background.primary,
        rippleColorAlpha = blocketColors.background.primary
    )
    WarpTheme(
        colors = blocketColors,
        typography = BlocketTypography,
        shapes = BlocketShapes(blocketDimensions),
        resources = blocketResources,
        content = content,
        rippleTheme = blocketRippleTheme,
        dimensions = blocketDimensions
    )
}
