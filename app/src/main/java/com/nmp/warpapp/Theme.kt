package com.nmp.warpapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpBrandedTheme

@Composable
fun BrandTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    WarpBrandedTheme(
        flavor = "finn",
        darkTheme = darkTheme,
        content = content
    )
}
