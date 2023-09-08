package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpTypography

private val FinnTypeMedium = FontFamily(
    Font(R.font.finntype_medium)
)

private val FinnTypeLight = FontFamily(
    Font(R.font.finntype_light)
)

object FinnTypography : WarpTypography {
    override val display: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 48.sp,
        lineHeight = 56.sp
    )
    override val title1: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 34.sp,
        lineHeight = 41.sp
    )
    override val title2: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 28.sp,
        lineHeight = 34.sp
    )
    override val title3: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 22.sp,
        lineHeight = 28.sp
    )
    override val title4: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )
    override val title5: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 14.sp,
        lineHeight = 18.sp
    )
    override val title6: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )
    override val preamble: TextStyle = TextStyle(
        fontFamily = FinnTypeLight,
        fontSize = 20.sp,
        lineHeight = 26.sp
    )
    override val body: TextStyle = TextStyle(
        fontFamily = FinnTypeLight,
        fontSize = 16.sp,
        lineHeight = 22.sp
    )
    override val bodyStrong: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )
    override val caption: TextStyle = TextStyle(
        fontFamily = FinnTypeLight,
        fontSize = 14.sp,
        lineHeight = 18.sp
    )
    override val captionStrong: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )
    override val detail: TextStyle = TextStyle(
        fontFamily = FinnTypeLight,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
    override val detailStrong: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )
}

