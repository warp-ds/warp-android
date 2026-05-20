package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf


interface WarpStrings {

    @Composable
    fun getVerifyIdentityButtonText(): String?
}

internal val LocalStrings = staticCompositionLocalOf<WarpStrings> {
    object : WarpStrings {
        @Composable
        override fun getVerifyIdentityButtonText(): String? = null
    }
}
