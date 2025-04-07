package com.schibsted.nmp.warp.brands.dba

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
fun DbaWarpTheme(
    content: @Composable () -> Unit,
    forceDarkMode: Boolean? = null
){
    val dbaColors = forceDarkMode?.let { if (it) DbaDarkColors else DbaColors }
        ?: if (isSystemInDarkTheme()) DbaDarkColors else DbaColors
    val dbaDimensions = WarpDimensions
    val dbaResources = WarpResources
    val dbaRippleConfig = RippleConfiguration(color = dbaColors.background.primary, rippleAlpha = RippleAlpha(0.5f, 0.5f, 0.5f, 0.5f))

    WarpTheme(
        colors = dbaColors,
        typography = DbaTypography,
        shapes = DbaShapes(dbaDimensions),
        resources = dbaResources,
        content = content,
        rippleConfig = dbaRippleConfig,
        dimensions = dbaDimensions
    )
}