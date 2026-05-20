package com.schibsted.nmp.warp.theme


import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.schibsted.nmp.warp.components.Brand

object WarpResources {
    val icons: WarpIconResources
        @Composable
        get() = WarpIconResources
}

internal val LocalResources = staticCompositionLocalOf { WarpResources }
internal val LocalBrand = staticCompositionLocalOf<Brand?> { null }
