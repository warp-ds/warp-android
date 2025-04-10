package com.schibsted.nmp.warp.brands.vend

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.unit.sp
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpTypography

private val VendSansBold = FontFamily(
    Font(R.font.vendsanstext_bold)
)

private val VendSansRegular = FontFamily(
    Font(R.font.vendsanstext_regular)
)

internal object VendTypography : WarpTypography {
    override val display: TextStyle = TextStyle(
        fontFamily = VendSansBold,
        fontSize = 48.sp,
        lineHeight = 56.sp,
        hyphens = Hyphens.Auto
    )
    override val title1: TextStyle = TextStyle(
        fontFamily = VendSansBold,
        fontSize = 34.sp,
        lineHeight = 41.sp,
        hyphens = Hyphens.Auto
    )
    override val title2: TextStyle = TextStyle(
        fontFamily = VendSansBold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        hyphens = Hyphens.Auto
    )
    override val title3: TextStyle = TextStyle(
        fontFamily = VendSansBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        hyphens = Hyphens.Auto
    )
    override val title4: TextStyle = TextStyle(
        fontFamily = VendSansBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        hyphens = Hyphens.Auto
    )
    override val title5: TextStyle = TextStyle(
        fontFamily = VendSansBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        hyphens = Hyphens.Auto
    )
    override val title6: TextStyle = TextStyle(
        fontFamily = VendSansBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        hyphens = Hyphens.Auto
    )
    override val preamble: TextStyle = TextStyle(
        fontFamily = VendSansRegular,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        hyphens = Hyphens.Auto
    )
    override val body: TextStyle = TextStyle(
        fontFamily = VendSansRegular,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        hyphens = Hyphens.Auto
    )
    override val bodyStrong: TextStyle = TextStyle(
        fontFamily = VendSansBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        hyphens = Hyphens.Auto
    )
    override val caption: TextStyle = TextStyle(
        fontFamily = VendSansRegular,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        hyphens = Hyphens.Auto
    )
    override val captionStrong: TextStyle = TextStyle(
        fontFamily = VendSansBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        hyphens = Hyphens.Auto
    )
    override val detail: TextStyle = TextStyle(
        fontFamily = VendSansRegular,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        hyphens = Hyphens.Auto
    )
    override val detailStrong: TextStyle = TextStyle(
        fontFamily = VendSansBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        hyphens = Hyphens.Auto
    )
}