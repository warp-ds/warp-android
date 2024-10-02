package com.schibsted.nmp.warp.brands.warp_blocket

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpResources
import com.schibsted.nmp.warp.theme.WarpRippleTheme
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun BlocketWarpTheme(content: @Composable () -> Unit) {
    val blocketColors = if(isSystemInDarkTheme()) BlocketDarkColors else BlocketColors
    val blocketDimensions = WarpDimensions
    val blocketRippleTheme = WarpRippleTheme(rippleColor = blocketColors.background.primary, rippleColorAlpha = blocketColors.background.primary)
    WarpTheme(
        colors = blocketColors,
        typography = BlocketTypography,
        shapes = BlocketShapes(blocketDimensions),
        resources = BlocketResources(),
        content = content,
        rippleTheme = blocketRippleTheme,
        dimensions = blocketDimensions
    )
}

internal data class BlocketResources(
    override val logo: Int = -1
) : WarpResources