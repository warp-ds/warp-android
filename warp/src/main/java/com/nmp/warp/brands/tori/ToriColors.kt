package com.nmp.warp.brands.tori

import androidx.compose.ui.graphics.Color
import com.nmp.warp.brands.tori.button.ToriButtonColors
import com.nmp.warp.theme.WarpColors

data class ToriColors(
    override val primary: Color = Watermelon600,
    override val secondary: Color = Petroleum600,
    override val button: ToriButtonColors = ToriButtonColors()
) : WarpColors

data class ToriDarkColors(
    override val primary: Color = Petroleum600,
    override val secondary: Color = White,
    override val button: ToriButtonColors = ToriButtonColors()
) : WarpColors

internal val White = Color.White
internal val Black = Color.Black
internal val Transparent = Color.Transparent

//Watermelon
internal val Watermelon50 = Color(0xFFFFF3F2)
internal val Watermelon100 = Color(0xFFFFE6E4)
internal val Watermelon200 = Color(0xFFFECBC7)
internal val Watermelon300 = Color(0xFFFEB0AC)
internal val Watermelon400 = Color(0xFFFD948F)
internal val Watermelon500 = Color(0xFFFD7372)
internal val Watermelon600 = Color(0xFFF75159)
internal val Watermelon700 = Color(0xFFB24046)
internal val Watermelon800 = Color(0xFF742E30)
internal val Watermelon900 = Color(0xFF3C1B1B)

//Petroleum
internal val Petroleum50 = Color(0xFFEEF4F5)
internal val Petroleum100 = Color(0xFFDDE9EC)
internal val Petroleum200 = Color(0xFFBAD3DA)
internal val Petroleum300 = Color(0xFF97BDC6)
internal val Petroleum400 = Color(0xFF74A8B4)
internal val Petroleum500 = Color(0xFF4D93A2)
internal val Petroleum600 = Color(0xFF157F91)
internal val Petroleum700 = Color(0xFF1B5E6A)
internal val Petroleum800 = Color(0xFF193F47)
internal val Petroleum900 = Color(0xFF122226)

//Green
internal val Green50 = Color(0xFFEBF1EB)
internal val Green100 = Color(0xFFD7E3D7)
internal val Green200 = Color(0xFFB0C7AF)
internal val Green300 = Color(0xFF89AA89)
internal val Green400 = Color(0xFF648F66)
internal val Green500 = Color(0xFF3C7542)
internal val Green600 = Color(0xFF0A5B21)
internal val Green700 = Color(0xFF10451B)
internal val Green800 = Color(0xFF113015)
internal val Green900 = Color(0xFF0D1C0D)

//Yellow
internal val Yellow50 = Color(0xFFFEF4ED)
internal val Yellow100 = Color(0xFFFCEAD9)
internal val Yellow200 = Color(0xFFF7D5B3)
internal val Yellow300 = Color(0xFFEFC190)
internal val Yellow400 = Color(0xFFE6AD6C)
internal val Yellow500 = Color(0xFFDB9949)
internal val Yellow600 = Color(0xFFCF8623)
internal val Yellow700 = Color(0xFF97631E)
internal val Yellow800 = Color(0xFF634218)
internal val Yellow900 = Color(0xFF342410)

//Red
internal val Red50 = Color(0xFFF9EBEA)
internal val Red100 = Color(0xFFF3D8D5)
internal val Red200 = Color(0xFFE4B2AB)
internal val Red300 = Color(0xFFD38C85)
internal val Red400 = Color(0xFFBF685F)
internal val Red500 = Color(0xFFAA403C)
internal val Red600 = Color(0xFF930B1D)
internal val Red700 = Color(0xFF6D1217)
internal val Red800 = Color(0xFF4A1212)
internal val Red900 = Color(0xFF2A0F0A)

//Gray
internal val Gray50 = Color(0xFFFAFAFA)
internal val Gray100 = Color(0xFFF5F5F5)
internal val Gray200 = Color(0xFFE6E6E6)
internal val Gray300 = Color(0xFFD6D6D6)
internal val Gray400 = Color(0xFFA5A5A5)
internal val Gray500 = Color(0xFF767676)
internal val Gray600 = Color(0xFF575757)
internal val Gray700 = Color(0xFF434343)
internal val Gray800 = Color(0xFF292929)
internal val Gray900 = Color(0xFF1A1A1A)
