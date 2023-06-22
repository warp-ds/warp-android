package com.nmp.warp.brands.finn

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.nmp.warp.R
import com.nmp.warp.theme.WarpColors
import com.nmp.warp.theme.WarpTypography

private val FinnTypeMedium = FontFamily(
    Font(R.font.finntype_medium)
)

private val FinnTypeLight = FontFamily(
    Font(R.font.finntype_light)
)

data class FinnTypography(
    val colors: WarpColors,
    override val heading: TextStyle = TextStyle(
        color = colors.primary,
        fontFamily = FinnTypeMedium,
        fontSize = 34.sp,
        lineHeight = 41.sp,
    ),
    override val paragraph: TextStyle = TextStyle(
        color = colors.secondary,
        fontFamily = FinnTypeLight,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )
) : WarpTypography
