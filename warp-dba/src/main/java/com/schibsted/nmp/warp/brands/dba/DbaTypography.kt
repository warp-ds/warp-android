package com.schibsted.nmp.warp.brands.dba

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.unit.sp
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpTypography

//Author Daniel Talakoubi

private val DbaRegular = FontFamily(
    Font(R.font.proxima_nova_regular)
)

private val DbaBold = FontFamily(
    Font(R.font.proxima_nova_bold)
)

internal object DbaTypography : WarpTypography {
    override val display: TextStyle = TextStyle(
        fontFamily = DbaBold,
        fontSize = 48.sp,
        lineHeight = 56.sp,
        hyphens = Hyphens.Auto
    )
    override val title1: TextStyle = TextStyle(
        fontFamily = DbaBold,
        fontSize = 34.sp,
        lineHeight = 41.sp,
        hyphens = Hyphens.Auto
    )
    override val title2: TextStyle = TextStyle(
        fontFamily = DbaBold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        hyphens = Hyphens.Auto
    )
    override val title3: TextStyle = TextStyle(
        fontFamily = DbaBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        hyphens = Hyphens.Auto
    )
    override val title4: TextStyle = TextStyle(
        fontFamily = DbaBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        hyphens = Hyphens.Auto
    )
    override val title5: TextStyle = TextStyle(
        fontFamily = DbaBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        hyphens = Hyphens.Auto
    )
    override val title6: TextStyle = TextStyle(
        fontFamily = DbaBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        hyphens = Hyphens.Auto
    )
    override val preamble: TextStyle = TextStyle(
        fontFamily = DbaRegular,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        hyphens = Hyphens.Auto
    )
    override val body: TextStyle = TextStyle(
        fontFamily = DbaRegular,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        hyphens = Hyphens.Auto
    )
    override val bodyStrong: TextStyle = TextStyle(
        fontFamily = DbaBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        hyphens = Hyphens.Auto
    )
    override val caption: TextStyle = TextStyle(
        fontFamily = DbaRegular,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        hyphens = Hyphens.Auto
    )
    override val captionStrong: TextStyle = TextStyle(
        fontFamily = DbaBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        hyphens = Hyphens.Auto
    )
    override val detail: TextStyle = TextStyle(
        fontFamily = DbaRegular,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        hyphens = Hyphens.Auto
    )
    override val detailStrong: TextStyle = TextStyle(
        fontFamily = DbaBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        hyphens = Hyphens.Auto
    )
}