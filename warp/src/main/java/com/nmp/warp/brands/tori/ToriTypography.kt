package com.nmp.warp.brands.tori

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.nmp.warp.R
import com.nmp.warp.theme.WarpColors
import com.nmp.warp.theme.WarpTypography

private val ToriTypeMedium = FontFamily(
    Font(R.font.finntype_medium)
)

private val ToriTypeLight = FontFamily(
    Font(R.font.finntype_light)
)

data class ToriTypography(
    val colors: WarpColors,
    override val heading: TextStyle = TextStyle(
        color = colors.primary,
        fontFamily = ToriTypeMedium,
        fontSize = 55.sp,
        lineHeight = 41.sp,
    ),
    override val paragraph: TextStyle = TextStyle(
        color = colors.secondary,
        fontFamily = ToriTypeLight,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )
) : WarpTypography
