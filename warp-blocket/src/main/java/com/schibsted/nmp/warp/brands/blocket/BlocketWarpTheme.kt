package com.schibsted.nmp.warp.brands.blocket

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpResources
import com.schibsted.nmp.warp.theme.WarpTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlocketWarpTheme(
    content: @Composable () -> Unit,
    forceDarkMode: Boolean? = null
) {
    val blocketColors = forceDarkMode?.let { if (it) BlocketDarkColors else BlocketColors }
        ?: if (isSystemInDarkTheme()) BlocketDarkColors else BlocketColors
    val blocketDimensions = WarpDimensions
    val blocketResources = WarpResources
    val blocketRippleConfig = RippleConfiguration(color = blocketColors.background.primary, rippleAlpha = RippleAlpha(0.5f, 0.5f, 0.5f, 0.5f))

    WarpTheme(
        colors = blocketColors,
        typography = BlocketTypography,
        shapes = BlocketShapes(blocketDimensions),
        resources = blocketResources,
        content = content,
        rippleConfig = blocketRippleConfig,
        dimensions = blocketDimensions
    )
}
