package com.schibsted.nmp.warp.brands.tori

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpTypography

private val ToriSansMedium = FontFamily(
    Font(R.font.torisans_medium)
)

private val ToriSansBold = FontFamily(
    Font(R.font.torisans_bold)
)

private val ToriSansBoldItalic = FontFamily(
    Font(R.font.torisans_bolditalic)
)

private val ToriSansItalic = FontFamily(
    Font(R.font.torisans_italic)
)

private val ToriSansMediumItalic = FontFamily(
    Font(R.font.torisans_mediumitalic)
)

private val ToriSansRegular = FontFamily(
    Font(R.font.torisans_regular)
)

data class ToriTypography(
    val colors: WarpColors,
    override val title4: TextStyle = TextStyle(
        fontFamily = ToriSansBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    ),
) : WarpTypography
