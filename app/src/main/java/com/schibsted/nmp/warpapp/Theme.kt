package com.schibsted.nmp.warpapp

import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.brands.finn.FinnWarpTheme
import com.schibsted.nmp.warp.brands.tori.ToriWarpTheme

@Composable
fun BrandTheme(flavor: String, content: @Composable () -> Unit) {
    when (flavor) {
        "finn" -> FinnWarpTheme(content)
        "tori" -> ToriWarpTheme(content)
    }
}
