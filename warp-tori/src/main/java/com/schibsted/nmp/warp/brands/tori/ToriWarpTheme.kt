package com.schibsted.nmp.warp.brands.tori

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpResources
import com.schibsted.nmp.warp.theme.WarpRippleTheme
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun ToriWarpTheme(
    content: @Composable () -> Unit
) {
    val toriColors = if (isSystemInDarkTheme()) ToriDarkColors else ToriColors
    val toriDimensions = WarpDimensions
    val toriResources = WarpResources
    val toriRippleTheme = WarpRippleTheme(rippleColor = toriColors.background.primary, rippleColorAlpha = toriColors.background.primary)
    WarpTheme(
        colors = toriColors,
        typography = ToriTypography,
        shapes = ToriShapes(toriDimensions),
        resources = toriResources,
        content = content,
        rippleTheme = toriRippleTheme,
        dimensions = toriDimensions
    )
}