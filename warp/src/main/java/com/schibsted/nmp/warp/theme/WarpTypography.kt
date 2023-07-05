package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import com.schibsted.nmp.warp.brands.finn.FinnTypography

interface WarpTypography {
    val display: TextStyle
    val title1: TextStyle
    val title2: TextStyle
    val title3: TextStyle
    val title4: TextStyle
    val title5: TextStyle
    val title6: TextStyle
    val preamble: TextStyle
    val body: TextStyle
    val bodyStrong: TextStyle
    val caption: TextStyle
    val captionStrong: TextStyle
    val detail: TextStyle
    val detailStrong: TextStyle
}

internal val LocalTypography =
    staticCompositionLocalOf<WarpTypography> { FinnTypography }
