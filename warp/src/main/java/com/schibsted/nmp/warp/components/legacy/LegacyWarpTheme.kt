package com.schibsted.nmp.warp.components.legacy

import androidx.compose.runtime.Composable

interface LegacyWarpTheme {
    @Suppress("ComposableNaming")
    @Composable
    operator fun invoke(content: @Composable () -> Unit)
}