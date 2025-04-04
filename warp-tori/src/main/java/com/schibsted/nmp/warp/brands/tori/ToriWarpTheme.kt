package com.schibsted.nmp.warp.brands.tori

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
fun ToriWarpTheme(
    content: @Composable () -> Unit,
    forceDarkMode: Boolean? = null
) {
    val toriColors = forceDarkMode?.let { if (it) ToriDarkColors else ToriColors }
        ?: if (isSystemInDarkTheme()) ToriDarkColors else ToriColors
    val toriDimensions = WarpDimensions
    val toriResources = WarpResources
    val toriRippleConfig = RippleConfiguration(color = toriColors.background.primary, rippleAlpha = RippleAlpha(0f, 0f, 0f, 0.5f))

    WarpTheme(
        colors = toriColors,
        typography = ToriTypography,
        shapes = ToriShapes(toriDimensions),
        resources = toriResources,
        content = content,
        rippleConfig = toriRippleConfig,
        dimensions = toriDimensions
    )
}