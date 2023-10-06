package com.schibsted.nmp.warp.components.legacy

import androidx.compose.runtime.Composable

interface LegacyWarpTheme {
    @Composable
    operator fun invoke(content: @Composable () -> Unit)
}