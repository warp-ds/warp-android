package com.schibsted.nmp.warp.brands.dba

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.schibsted.nmp.warp.theme.WarpTypography

private val DbaSansMedium = FontFamily(
    Font(R.font.proxima_nova_black)
)

private val DbaSansBold = FontFamily(
    Font(R.font.proxima_nova_bold)
)

private val DbaSansBoldItalic = FontFamily(
    Font(R.font.proxima_nova_alt_bold)
)

private val DbaSansItalic = FontFamily(
    Font(R.font.proxima_nova_thin)
)

private val DbaSansMediumItalic = FontFamily(
    Font(R.font.proxima_nova_alt_thin)
)

private val DbaSansRegular = FontFamily(
    Font(R.font.proxima_nova_regular)
)

internal object DbaTypography : WarpTypography {
    override val display: TextStyle = TextStyle(
        fontFamily = DbaSansBold,
        fontSize = 48.sp,
        lineHeight = 56.sp,
    )
    override val title1: TextStyle = TextStyle(
        fontFamily = DbaSansBold,
        fontSize = 34.sp,
        lineHeight = 41.sp,
    )
    override val title2: TextStyle = TextStyle(
        fontFamily = DbaSansBold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
    )
    override val title3: TextStyle = TextStyle(
        fontFamily = DbaSansBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
    )
    override val title4: TextStyle = TextStyle(
        fontFamily = DbaSansBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )
    override val title5: TextStyle = TextStyle(
        fontFamily = DbaSansBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )
    override val title6: TextStyle = TextStyle(
        fontFamily = DbaSansBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )
    override val preamble: TextStyle = TextStyle(
        fontFamily = DbaSansRegular,
        fontSize = 20.sp,
        lineHeight = 26.sp,
    )
    override val body: TextStyle = TextStyle(
        fontFamily = DbaSansRegular,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )
    override val bodyStrong: TextStyle = TextStyle(
        fontFamily = DbaSansBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )
    override val caption: TextStyle = TextStyle(
        fontFamily = DbaSansRegular,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )
    override val captionStrong: TextStyle = TextStyle(
        fontFamily = DbaSansBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )
    override val detail: TextStyle = TextStyle(
        fontFamily = DbaSansRegular,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )
    override val detailStrong: TextStyle = TextStyle(
        fontFamily = DbaSansBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )
}