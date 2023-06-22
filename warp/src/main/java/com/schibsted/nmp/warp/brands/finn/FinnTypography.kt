package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpTypography

private val FinnTypeMedium = FontFamily(
    Font(R.font.finntype_medium)
)

private val FinnTypeLight = FontFamily(
    Font(R.font.finntype_light)
)

data class FinnTypography(
    val colors: WarpColors,
    override val title4: TextStyle = TextStyle(
        fontFamily = FinnTypeMedium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    ),
) : WarpTypography
