package com.schibsted.nmp.warp.theme


import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

object WarpResources {
    val icons: WarpIconResources
        @Composable
        get() = WarpIconResources
    val taxonomyIcons: WarpTaxonomyIcons
        @Composable
        get() = WarpTaxonomyIcons
}

internal val LocalResources = staticCompositionLocalOf { WarpResources }
