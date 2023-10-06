package com.schibsted.nmp.warpapp

import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.brands.finn.FinnWarpTheme
import com.schibsted.nmp.warp.brands.tori.ToriWarpTheme
import com.schibsted.nmp.warp.components.legacy.LegacyWarpTheme

//To be able to use the theme in legacy xml views we need to extend LegacyWarpTheme.
// This class will be then injected into the legacy implementation of Warp components
class BrandTheme(val flavor: String) : LegacyWarpTheme(){

    @Composable
    override fun getTheme(content:@Composable  () -> Unit) {
        when (flavor) {
            "finn" -> FinnWarpTheme(content)
            "tori" -> ToriWarpTheme(content)
        }
    }
}
