package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import com.schibsted.nmp.warp.brands.finn.FinnResources

interface WarpResources {
    val logo: Int
}

internal val LocalResources = staticCompositionLocalOf<WarpResources> { FinnResources() }
