package com.schibsted.nmp.warpapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.theme.WarpBrandedTheme

@Composable
fun BrandTheme(darkTheme: Boolean = isSystemInDarkTheme(), flavor: String, content: @Composable () -> Unit) {
    WarpBrandedTheme(
        flavor = flavor,
        darkTheme = darkTheme,
        content = content
    )
}
