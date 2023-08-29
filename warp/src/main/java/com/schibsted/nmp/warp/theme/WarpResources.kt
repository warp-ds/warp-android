package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf

interface WarpResources {
    val logo: Int
}

internal val LocalResources = staticCompositionLocalOf<WarpResources> { PlaceholderResources }
