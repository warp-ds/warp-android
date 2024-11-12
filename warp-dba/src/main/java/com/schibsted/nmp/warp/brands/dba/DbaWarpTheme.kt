package com.schibsted.nmp.warp.brands.dba

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpResources
import com.schibsted.nmp.warp.theme.WarpRippleTheme
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun DbaWarpTheme(
    content: @Composable () -> Unit
){
    val dbaColors = if (isSystemInDarkTheme()) DbaDarkColors else DbaColors
    val dbaDimensions = WarpDimensions
    val dbaResources = WarpResources
    val dbaRippleTheme = WarpRippleTheme(rippleColor = dbaColors.background.primary, rippleColorAlpha = dbaColors.background.primary)
    WarpTheme(
        colors = dbaColors,
        typography = DbaTypography,
        shapes = DbaShapes(dbaDimensions),
        resources = dbaResources,
        content = content,
        rippleTheme = dbaRippleTheme,
        dimensions = dbaDimensions
    )
}