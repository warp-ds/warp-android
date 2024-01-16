@file:Suppress("unused")

package com.schibsted.nmp.warp.brands.tori

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.Black70Alpha
import com.schibsted.nmp.warp.theme.WarpAvatarColors
import com.schibsted.nmp.warp.theme.WarpBackgroundColors
import com.schibsted.nmp.warp.theme.WarpBadgeColors
import com.schibsted.nmp.warp.theme.WarpBorderColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpCalloutColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpIconColors
import com.schibsted.nmp.warp.theme.WarpPillColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.White

internal object ToriColors : WarpColors {
    override val background: WarpBackgroundColors = ToriBackgroundColors
    override val border: WarpBorderColors = ToriBorderColors
    override val icon: WarpIconColors = ToriIconColors
    override val text: WarpTextColors = ToriTextColors
    override val components: WarpComponentColors = ToriComponentColors
}

internal object ToriBackgroundColors : WarpBackgroundColors {
    override val default = White
    override val hover = Gray100
    override val active = Gray200
    override val disabled = Gray300
    override val disabledSubtle = Gray200
    override val subtle = Gray50
    override val subtleHover = Gray100
    override val subtleActive = Gray200
    override val selected = Blueberry50
    override val selectedHover = Blueberry100

    override val inverted = Gray900

    override val primary = Blueberry600
    override val primaryHover = Blueberry700
    override val primaryActive = Blueberry800
    override val primarySubtle = Blueberry50
    override val primarySubtleHover = Blueberry100
    override val primarySubtleActive = Blueberry200

    override val secondary = Watermelon500
    override val secondaryHover = Watermelon600
    override val secondaryActive = Watermelon700

    override val positive = Green600
    override val positiveHover = Green700
    override val positiveActive = Green800
    override val positiveSubtle = Green50
    override val positiveSubtleHover = Green100
    override val positiveSubtleActive = Green200
    override val positiveSelected = Green50
    override val positiveSelectedHover = Green100

    override val negative = Red600
    override val negativeHover = Red700
    override val negativeActive = Red800
    override val negativeSubtle = Red50
    override val negativeSubtleHover = Red100
    override val negativeSubtleActive = Red200
    override val negativeSelected = Red50
    override val negativeSelectedHover = Red100

    override val warning = Yellow600
    override val warningHover = Yellow700
    override val warningActive = Yellow800
    override val warningSubtle = Yellow50
    override val warningSubtleHover = Yellow100
    override val warningSubtleActive = Yellow200
    override val warningSelected = Yellow50
    override val warningSelectedHover = Yellow100

    override val info = Blue600
    override val infoHover = Blue700
    override val infoActive = Blue800
    override val infoSubtle = Blue50
    override val infoSubtleHover = Blue100
    override val infoSubtleActive = Blue200
    override val infoSelected = Blue50
    override val infoSelectedHover = Blue100

    override val notification = Red600
}

internal object ToriBorderColors : WarpBorderColors {
    override val default = Gray300
    override val hover = Gray400
    override val active = Gray500
    override val disabled = Gray300
    override val selected = Blueberry600
    override val selectedHover = Blueberry700
    override val focus = Blue300

    override val primary = Blueberry600
    override val primaryHover = Blueberry700
    override val primaryActive = Blueberry800
    override val primarySubtle = Blueberry300
    override val primarySubtleHover = Blueberry400
    override val primarySubtleActive = Blueberry500

    override val secondary = Watermelon500
    override val secondaryHover = Watermelon600
    override val secondaryActive = Watermelon700

    override val positive = Green600
    override val positiveHover = Green700
    override val positiveActive = Green800
    override val positiveSubtle = Green300
    override val positiveSubtleHover = Green400
    override val positiveSubtleActive = Green500

    override val negative = Red600
    override val negativeHover = Red700
    override val negativeActive = Red800
    override val negativeSubtle = Red300
    override val negativeSubtleHover = Red400
    override val negativeSubtleActive = Red500

    override val warning = Yellow600
    override val warningHover = Yellow700
    override val warningActive = Yellow800
    override val warningSubtle = Yellow300
    override val warningSubtleHover = Yellow400
    override val warningSubtleActive = Yellow500

    override val info = Blue600
    override val infoHover = Blue700
    override val infoActive = Blue800
    override val infoSubtle = Blue300
    override val infoSubtleHover = Blue400
    override val infoSubtleActive = Blue500
}

internal object ToriIconColors : WarpIconColors {
    override val default = Gray900
    override val hover = Gray900
    override val active = Gray900
    override val selected = Blueberry600
    override val selectedHover = Blueberry700
    override val disabled = Gray300
    override val subtle = Gray400
    override val subtleHover = Gray500
    override val subtleActive = Gray600
    override val inverted = White
    override val invertedHover = Gray100
    override val invertedActive = Gray200
    override val primary = Blueberry600
    override val secondary = Watermelon500
    override val secondaryHover = Watermelon600
    override val secondaryActive = Watermelon700
    override val positive = Green600
    override val negative = Red600
    override val warning = Yellow600
    override val info = Blue600
    override val notification = White
}

internal object ToriTextColors : WarpTextColors {
    override val default = Gray900
    override val subtle = Gray500
    override val placeholder = Gray300
    override val inverted = White
    override val invertedSubtle = Gray50
    override val link = Blueberry600
    override val disabled = Gray300
    override val negative = Red600
    override val positive = Green600
    override val notification = White
}

internal object ToriComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = ToriButtonColors
    override val badge: WarpBadgeColors = ToriBadgeColors
    override val callout: WarpCalloutColors = ToriCalloutColors
    override val pill: WarpPillColors = ToriPillColors
    override val avatar: WarpAvatarColors = ToriAvatarColors
}

internal object ToriAvatarColors : WarpAvatarColors {
    override val background = Gray100
    override val icon = ToriIconColors.disabled
}

internal object ToriPillColors : WarpPillColors {
    override val filterTextColor = White
    override val filterIconColor = White
    override val suggestion = Pair(Gray200, Gray400)
}

internal object ToriCalloutColors : WarpCalloutColors {
    override val background: Color = Blue100
    override val border: Color = Blue400
    override val text: Color = ToriTextColors.default
}

internal object ToriBadgeColors : WarpBadgeColors {
    override val infoBackground: Color = Blue100
    override val positiveBackground: Color = Green100
    override val warningBackground: Color = Yellow100
    override val negativeBackground: Color = Red100
    override val disabledBackground: Color = ToriBackgroundColors.disabled
    override val neutralBackground: Color = Gray100
    override val sponsoredBackground: Color = Blue200
    override val priceBackground: Color = Black70Alpha
}

internal object ToriButtonColors : WarpButtonColors {
    override val loading = Pair(Gray50, Gray200)
    override val primary = Pair(Watermelon600, Watermelon800)
}

//Watermelon
internal val Watermelon50 = Color(0xFFFFF3F2)
internal val Watermelon100 = Color(0xFFFFE6E4)
internal val Watermelon200 = Color(0xFFFECBC7)
internal val Watermelon300 = Color(0xFFFEB0AC)
internal val Watermelon400 = Color(0xFFFD948F)
internal val Watermelon500 = Color(0xFFF95157)
internal val Watermelon600 = Color(0xFFD43D4F)
internal val Watermelon700 = Color(0xFF982C38)
internal val Watermelon800 = Color(0xFF5B1920)
internal val Watermelon900 = Color(0xFF29080C)

//Blueberry
internal val Blueberry50 = Color(0xFFF7F9FD)
internal val Blueberry100 = Color(0xFFD7E0F4)
internal val Blueberry200 = Color(0xFFB4C9EC)
internal val Blueberry300 = Color(0xFF91B2E4)
internal val Blueberry400 = Color(0xFF6E9BDC)
internal val Blueberry500 = Color(0xFF4D88DB)
internal val Blueberry600 = Color(0xFF296DCC)
internal val Blueberry700 = Color(0xFF1D4E92)
internal val Blueberry800 = Color(0xFF122F58)
internal val Blueberry900 = Color(0xFF06101E)

//Blue
internal val Blue50 = Color(0xFFECF7FE)
internal val Blue100 = Color(0xFFC7E7FB)
internal val Blue200 = Color(0xFFA2D5F5)
internal val Blue300 = Color(0xFF7DC3EF)
internal val Blue400 = Color(0xFF58B1E9)
internal val Blue500 = Color(0xFF339FE3)
internal val Blue600 = Color(0xFF0E8DDD)
internal val Blue700 = Color(0xFF0A659F)
internal val Blue800 = Color(0xFF063D61)
internal val Blue900 = Color(0xFF021622)

//Green
internal val Green50 = Color(0xFFF3FCF9)
internal val Green100 = Color(0xFFCEE8DC)
internal val Green200 = Color(0xFFAAD6C4)
internal val Green300 = Color(0xFF86C4AC)
internal val Green400 = Color(0xFF62B294)
internal val Green500 = Color(0xFF3EA07C)
internal val Green600 = Color(0xFF1A8F64)
internal val Green700 = Color(0xFF136647)
internal val Green800 = Color(0xFF0C3E2A)
internal val Green900 = Color(0xFF072719)

//Yellow
internal val Yellow50 = Color(0xFFFEF7F1)
internal val Yellow100 = Color(0xFFFDE8D5)
internal val Yellow200 = Color(0xFFFBD6B4)
internal val Yellow300 = Color(0xFFF9C493)
internal val Yellow400 = Color(0xFFF7B272)
internal val Yellow500 = Color(0xFFF5A051)
internal val Yellow600 = Color(0xFFF38E30)
internal val Yellow700 = Color(0xFFAD6421)
internal val Yellow800 = Color(0xFF673A12)
internal val Yellow900 = Color(0xFF221102)

//Red
internal val Red50 = Color(0xFFFEF6F7)
internal val Red100 = Color(0xFFEDCED1)
internal val Red200 = Color(0xFFDBA7AD)
internal val Red300 = Color(0xFFC98089)
internal val Red400 = Color(0xFFB75965)
internal val Red500 = Color(0xFFA53241)
internal val Red600 = Color(0xFF930B1D)
internal val Red700 = Color(0xFF7D1024)
internal val Red800 = Color(0xFF520B18)
internal val Red900 = Color(0xFF27070D)

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
