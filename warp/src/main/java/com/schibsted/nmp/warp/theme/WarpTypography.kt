package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import com.schibsted.nmp.warp.brands.finn.FinnColors
import com.schibsted.nmp.warp.brands.finn.FinnTypography

interface WarpTypography {
    val title4: TextStyle
}

internal val LocalTypography =
    staticCompositionLocalOf<WarpTypography> { FinnTypography(FinnColors()) }
