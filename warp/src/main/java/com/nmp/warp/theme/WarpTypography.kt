package com.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import com.nmp.warp.brands.finn.FinnColors
import com.nmp.warp.brands.finn.FinnTypography

interface WarpTypography {
    val heading: TextStyle
    val paragraph: TextStyle
}

internal val LocalTypography =
    staticCompositionLocalOf<WarpTypography> { FinnTypography(FinnColors()) }
