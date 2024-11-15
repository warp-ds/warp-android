package com.schibsted.nmp.warp.theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun WarpTheme(
    colors: WarpColors,
    typography: WarpTypography,
    shapes: WarpShapes,
    resources: WarpResources,
    dimensions: WarpDimensions,
    rippleTheme: WarpRippleTheme,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalShapes provides shapes,
        LocalResources provides resources,
        LocalDimensions provides dimensions,
        LocalRippleTheme provides rippleTheme,
        LocalTextStyle provides typography.body,
        content = content
    )
}

object WarpTheme {
    val colors: WarpColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: WarpTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: WarpShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val resources: WarpResources
        @Composable
        @ReadOnlyComposable
        get() = LocalResources.current

    val dimensions: WarpDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
}
