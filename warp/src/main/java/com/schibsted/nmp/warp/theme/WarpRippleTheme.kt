package com.schibsted.nmp.warp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object WarpRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color {
        return WarpTheme.colors.primary
    }

    @Composable
    override fun rippleAlpha(): RippleAlpha {
        return RippleTheme.defaultRippleAlpha(WarpTheme.colors.primary, !isSystemInDarkTheme())
    }
}
