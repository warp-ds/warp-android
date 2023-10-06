package com.schibsted.nmp.warp.components.legacy

import androidx.compose.runtime.Composable

abstract class LegacyWarpTheme {
    @Composable
    abstract fun getTheme(content: @Composable () -> Unit)
}