package com.schibsted.nmp.warpapp

import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.brands.blocket.BlocketWarpTheme
import com.schibsted.nmp.warp.brands.dba.DbaWarpTheme
import com.schibsted.nmp.warp.brands.finn.FinnWarpTheme
import com.schibsted.nmp.warp.brands.tori.ToriWarpTheme
import com.schibsted.nmp.warp.components.legacy.LegacyWarpTheme

const val FINN = "finn"
const val TORI = "tori"
const val DBA = "dba"
const val BLOCKET = "blocket"

//To be able to use the theme in legacy xml views we need to implement LegacyWarpTheme.
// This class will be then injected into the legacy implementation of Warp components
class BrandTheme(val flavor: String) : LegacyWarpTheme {
    @Composable
    override fun invoke(content:@Composable  () -> Unit) {
        when (flavor) {
            FINN -> FinnWarpTheme(content, forceDarkMode = false)
            TORI -> ToriWarpTheme(content)
            DBA -> DbaWarpTheme (content)
            BLOCKET -> BlocketWarpTheme(content)
        }
    }
}
