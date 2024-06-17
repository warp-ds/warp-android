package com.schibsted.nmp.warp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.WarpTheme.colors

class WarpRippleTheme(
    private val rippleColor: Color? = null,
    private val rippleColorAlpha: Color? = null
) : RippleTheme {

    @Composable
    override fun defaultColor(): Color = RippleTheme.defaultRippleColor(
        rippleColor ?: colors.background.secondary,
        !isSystemInDarkTheme()
    )

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            rippleColorAlpha ?: colors.background.secondary,
            !isSystemInDarkTheme()
        )
}