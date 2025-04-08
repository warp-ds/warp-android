package com.schibsted.nmp.warp.brands.finn

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
fun FinnWarpTheme(
    content: @Composable () -> Unit,
    forceDarkMode: Boolean? = null
) {
    val finnColors = forceDarkMode?.let { if (it) FinnDarkColors else FinnColors }
        ?: if (isSystemInDarkTheme()) FinnDarkColors else FinnColors
    val finnDimensions = WarpDimensions
    val finnResources = WarpResources
    val finnRippleConfig = RippleConfiguration(color = finnColors.background.primary, rippleAlpha = RippleAlpha(0.5f, 0.5f, 0.5f, 0.5f))

    WarpTheme(
        colors = finnColors,
        typography = FinnTypography,
        shapes = FinnShapes(finnDimensions),
        resources = finnResources,
        content = content,
        rippleConfig = finnRippleConfig,
        dimensions = finnDimensions
    )
}