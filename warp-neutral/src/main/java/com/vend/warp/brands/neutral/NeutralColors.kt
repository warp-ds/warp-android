package com.vend.warp.brands.neutral
import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.WarpBackgroundColors
import com.schibsted.nmp.warp.theme.WarpBadgeColors
import com.schibsted.nmp.warp.theme.WarpBorderColors
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpCalloutColors
import com.schibsted.nmp.warp.theme.WarpCardColors
import com.schibsted.nmp.warp.theme.WarpColors
import com.schibsted.nmp.warp.theme.WarpComponentColors
import com.schibsted.nmp.warp.theme.WarpDatavizColors
import com.schibsted.nmp.warp.theme.WarpIconColors
import com.schibsted.nmp.warp.theme.WarpNavBarColors
import com.schibsted.nmp.warp.theme.WarpPageIndicatorColors
import com.schibsted.nmp.warp.theme.WarpPillColors
import com.schibsted.nmp.warp.theme.WarpSurfaceColors
import com.schibsted.nmp.warp.theme.WarpSwitchColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.WarpTooltipColors
import com.schibsted.nmp.warp.theme.dataviz.DatavizColors

// Generated on Wed, 17 Sep 2025 09:11:09 GMT by https://github.com/warp-ds/tokens

internal object NeutralColors : WarpColors {
    override val surface: WarpSurfaceColors = NeutralSurfaceColors
    override val background: WarpBackgroundColors = NeutralBackgroundColors
    override val border: WarpBorderColors = NeutralBorderColors
    override val icon: WarpIconColors = NeutralIconColors
    override val text: WarpTextColors = NeutralTextColors
    override val components: WarpComponentColors = NeutralComponentColors
    override val dataviz: WarpDatavizColors = DatavizColors
}

internal object NeutralSurfaceColors : WarpSurfaceColors {
    override val sunken = Color(0xFFf6f6f6)
    override val elevated100 = Color(0xFFffffff)
    override val elevated100Hover = Color(0xFFf0f0f2)
    override val elevated100Active = Color(0xFFdedee3)
    override val elevated200 = Color(0xFFffffff)
    override val elevated200Hover = Color(0xFFf0f0f2)
    override val elevated200Active = Color(0xFFdedee3)
    override val elevated300 = Color(0xFFffffff)
    override val elevated300Hover = Color(0xFFf0f0f2)
    override val elevated300Active = Color(0xFFdedee3)
}

internal object NeutralBackgroundColors : WarpBackgroundColors {
    override val default = Color(0xFFffffff)
    override val hover = Color(0xFFf0f0f2)
    override val active = Color(0xFFdedee3)
    override val subtle = Color(0xFFf0f0f2)
    override val subtleHover = Color(0xFFdedee3)
    override val subtleActive = Color(0xFFcacad1)
    override val disabled = Color(0xFFcacad1)
    override val disabledSubtle = Color(0xFFdedee3)
    override val selected = Color(0xFFf6f6f6)
    override val selectedHover = Color(0xFFf0f0f2)
    override val selectedActive = Color(0xFFdedee3)
    override val inverted = Color(0xFF1b1b1f)
    override val primary = Color(0xFF0071eb)
    override val primaryHover = Color(0xFF0055b1)
    override val primaryActive = Color(0xFF003977)
    override val primarySubtle = Color(0xFFc8dffa)
    override val primarySubtleHover = Color(0xFFa0c9f7)
    override val primarySubtleActive = Color(0xFF78b3f4)
    override val secondary = Color(0xFF1b1b1f)
    override val secondaryHover = Color(0xFF2b2b30)
    override val secondaryActive = Color(0xFF47474f)
    override val positive = Color(0xFF228000)
    override val positiveHover = Color(0xFF1a6100)
    override val positiveActive = Color(0xFF124000)
    override val positiveSubtle = Color(0xFFf1f8ec)
    override val positiveSubtleHover = Color(0xFFe3f1da)
    override val positiveSubtleActive = Color(0xFFc7e2b6)
    override val negative = Color(0xFFe00000)
    override val negativeHover = Color(0xFFa10000)
    override val negativeActive = Color(0xFF620000)
    override val negativeSubtle = Color(0xFFfdf1f1)
    override val negativeSubtleHover = Color(0xFFfad6d6)
    override val negativeSubtleActive = Color(0xFFf6b2b2)
    override val warning = Color(0xFFf16c00)
    override val warningHover = Color(0xFFac4d00)
    override val warningActive = Color(0xFF682e00)
    override val warningSubtle = Color(0xFFfff4eb)
    override val warningSubtleHover = Color(0xFFffe0c7)
    override val warningSubtleActive = Color(0xFFffd0a9)
    override val info = Color(0xFF0071eb)
    override val infoHover = Color(0xFF0055b1)
    override val infoActive = Color(0xFF003977)
    override val infoSubtle = Color(0xFFf0f7ff)
    override val infoSubtleHover = Color(0xFFc8dffa)
    override val infoSubtleActive = Color(0xFFa0c9f7)
    override val transparent0 = Color(0x00ffffff)
    override val notification = Color(0xFFe00000)
}

internal object NeutralBorderColors : WarpBorderColors {
    override val default = Color(0xFFcacad1)
    override val hover = Color(0xFFafafb8)
    override val active = Color(0xFF84848f)
    override val disabled = Color(0xFFcacad1)
    override val selected = Color(0xFF0071eb)
    override val selectedHover = Color(0xFF0055b1)
    override val selectedActive = Color(0xFF003977)
    override val inverted = Color(0xFFffffff)
    override val primary = Color(0xFF0071eb)
    override val primaryHover = Color(0xFF0055b1)
    override val primaryActive = Color(0xFF003977)
    override val primarySubtle = Color(0xFF78b3f4)
    override val primarySubtleHover = Color(0xFF509df1)
    override val primarySubtleActive = Color(0xFF2887ee)
    override val secondary = Color(0xFF1b1b1f)
    override val secondaryHover = Color(0xFF2b2b30)
    override val secondaryActive = Color(0xFF47474f)
    override val positive = Color(0xFF228000)
    override val positiveHover = Color(0xFF1a6100)
    override val positiveActive = Color(0xFF124000)
    override val positiveSubtle = Color(0xFFabd493)
    override val positiveSubtleHover = Color(0xFF8dc570)
    override val positiveSubtleActive = Color(0xFF4ba722)
    override val negative = Color(0xFFe00000)
    override val negativeHover = Color(0xFFa10000)
    override val negativeActive = Color(0xFF620000)
    override val negativeSubtle = Color(0xFFf39999)
    override val negativeSubtleHover = Color(0xFFef8080)
    override val negativeSubtleActive = Color(0xFFe63333)
    override val warning = Color(0xFFf16c00)
    override val warningHover = Color(0xFFac4d00)
    override val warningActive = Color(0xFF682e00)
    override val warningSubtle = Color(0xFFffbb84)
    override val warningSubtleHover = Color(0xFFffaa64)
    override val warningSubtleActive = Color(0xFFff8725)
    override val info = Color(0xFF0071eb)
    override val infoHover = Color(0xFF0055b1)
    override val infoActive = Color(0xFF003977)
    override val infoSubtle = Color(0xFF78b3f4)
    override val infoSubtleHover = Color(0xFF509df1)
    override val infoSubtleActive = Color(0xFF2887ee)
    override val focus = Color(0xFF509df1)
}
   
internal object NeutralIconColors : WarpIconColors {
    override val default = Color(0xFF1b1b1f)
    override val hover = Color(0xFF0055b1)
    override val active = Color(0xFF003977)
    override val static = Color(0xFF1b1b1f)
    override val selected = Color(0xFF0071eb)
    override val selectedHover = Color(0xFF0055b1)
    override val selectedActive = Color(0xFF003977)
    override val disabled = Color(0xFFcacad1)
    override val subtle = Color(0xFF5c5c66)
    override val subtleHover = Color(0xFF47474f)
    override val subtleActive = Color(0xFF2b2b30)
    override val inverted = Color(0xFFffffff)
    override val invertedHover = Color(0xFFf0f0f2)
    override val invertedActive = Color(0xFFdedee3)
    override val invertedStatic = Color(0xFFffffff)
    override val primary = Color(0xFF0071eb)
    override val secondary = Color(0xFF1b1b1f)
    override val secondaryHover = Color(0xFF2b2b30)
    override val secondaryActive = Color(0xFF47474f)
    override val positive = Color(0xFF228000)
    override val negative = Color(0xFFe00000)
    override val warning = Color(0xFFf16c00)
    override val info = Color(0xFF0071eb)
    override val notification = Color(0xFFffffff)
}

internal object NeutralTextColors : WarpTextColors {
    override val default = Color(0xFF1b1b1f)
    override val subtle = Color(0xFF5c5c66)
    override val static = Color(0xFF1b1b1f)
    override val placeholder = Color(0xFFcacad1)
    override val inverted = Color(0xFFffffff)
    override val invertedSubtle = Color(0xFFafafb8)
    override val invertedStatic = Color(0xFFffffff)
    override val link = Color(0xFF0071eb)
    override val disabled = Color(0xFFcacad1)
    override val negative = Color(0xFFe00000)
    override val positive = Color(0xFF228000)
}

internal object NeutralComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = NeutralButtonColors
    override val badge: WarpBadgeColors = NeutralBadgeColors
    override val callout: WarpCalloutColors = NeutralCalloutColors
    override val pill: WarpPillColors = NeutralPillColors
    override val navBar: WarpNavBarColors = NeutralNavBarColors
    override val tooltip: WarpTooltipColors = NeutralTooltipColors
    override val switch: WarpSwitchColors = NeutralSwitchColors
    override val card: WarpCardColors = NeutralCardColors
    override val pageIndicator: WarpPageIndicatorColors = NeutralPageIndicatorColors
}

internal object NeutralButtonColors : WarpButtonColors {
    override val primaryBackground = Color(0xFF0071eb)
    override val primaryBackgroundHover = Color(0xFF0055b1)
    override val primaryBackgroundActive = Color(0xFF003977)
    override val pillBackgroundHover = Color(0xFFcacad1)
    override val pillBackgroundActive = Color(0xFFafafb8)
}

internal object NeutralBadgeColors : WarpBadgeColors {
    override val neutralBackground = Color(0xFFf0f0f2)
    override val positiveBackground = Color(0xFFe3f1da)
    override val infoBackground = Color(0xFFc8dffa)
    override val warningBackground = Color(0xFFffe0c7)
    override val negativeBackground = Color(0xFFfad6d6)
    override val sponsoredBackground = Color(0xFFa0c9f7)
    override val priceBackground = Color(0xFF000000)
}

internal object NeutralCalloutColors : WarpCalloutColors {
    override val background = Color(0xFFe3f1da)
    override val border = Color(0xFF8dc570)
}

internal object NeutralPillColors : WarpPillColors {
    override val suggestionBackground = Color(0xFFdedee3)
    override val suggestionBackgroundHover = Color(0xFFcacad1)
    override val suggestionBackgroundActive = Color(0xFFafafb8)
}

internal object NeutralNavBarColors : WarpNavBarColors {
    override val iconSelected = Color(0xFF1b1b1f)
    override val borderSelected = Color(0xFF1b1b1f)
}

internal object NeutralTooltipColors : WarpTooltipColors {
    override val backgroundStatic = Color(0xFF1b1b1f)
}

private object NeutralSwitchColors : WarpSwitchColors {
    override val handleBackground = Color(0xFF84848f)
    override val handleBackgroundHover = Color(0xFF5c5c66)
    override val trackBorder = Color(0xFF84848f)
    override val trackBorderHover = Color(0xFF5c5c66)
}

private object NeutralCardColors : WarpCardColors {
    override val defaultBackground = Color(0xFFffffff)
}

private object NeutralPageIndicatorColors : WarpPageIndicatorColors {
    override val indicatorBackground = Color(0xFFcacad1)
    override val indicatorBackgroundHover = Color(0xFFafafb8)
}
    internal val Gray50 = Color(0xFFf6f6f6)
    internal val Gray100 = Color(0xFFf0f0f2)
    internal val Gray200 = Color(0xFFdedee3)
    internal val Gray300 = Color(0xFFcacad1)
    internal val Gray400 = Color(0xFFafafb8)
    internal val Gray500 = Color(0xFF84848f)
    internal val Gray600 = Color(0xFF5c5c66)
    internal val Gray700 = Color(0xFF47474f)
    internal val Gray750 = Color(0xFF333338)
    internal val Gray800 = Color(0xFF2b2b30)
    internal val Gray850 = Color(0xFF26262b)
    internal val Gray900 = Color(0xFF1b1b1f)
    internal val Gray950 = Color(0xFF121212)
    internal val Blue50 = Color(0xFFf0f7ff)
    internal val Blue100 = Color(0xFFc8dffa)
    internal val Blue200 = Color(0xFFa0c9f7)
    internal val Blue300 = Color(0xFF78b3f4)
    internal val Blue400 = Color(0xFF509df1)
    internal val Blue500 = Color(0xFF2887ee)
    internal val Blue600 = Color(0xFF0071eb)
    internal val Blue700 = Color(0xFF0055b1)
    internal val Blue750 = Color(0xFF004794)
    internal val Blue800 = Color(0xFF003977)
    internal val Blue850 = Color(0xFF002b5a)
    internal val Blue900 = Color(0xFF001d3d)
    internal val Blue950 = Color(0xFF000f20)
    internal val Green50 = Color(0xFFf1f8ec)
    internal val Green100 = Color(0xFFe3f1da)
    internal val Green200 = Color(0xFFc7e2b6)
    internal val Green300 = Color(0xFFabd493)
    internal val Green400 = Color(0xFF8dc570)
    internal val Green500 = Color(0xFF4ba722)
    internal val Green600 = Color(0xFF228000)
    internal val Green650 = Color(0xFF1e7000)
    internal val Green700 = Color(0xFF1a6100)
    internal val Green750 = Color(0xFF165000)
    internal val Green800 = Color(0xFF124000)
    internal val Green850 = Color(0xFF0e3000)
    internal val Green900 = Color(0xFF0a2000)
    internal val Green950 = Color(0xFF061000)
    internal val Orange50 = Color(0xFFfff4eb)
    internal val Orange100 = Color(0xFFffe0c7)
    internal val Orange200 = Color(0xFFffd0a9)
    internal val Orange300 = Color(0xFFffbb84)
    internal val Orange400 = Color(0xFFffaa64)
    internal val Orange500 = Color(0xFFff8725)
    internal val Orange600 = Color(0xFFf16c00)
    internal val Orange700 = Color(0xFFac4d00)
    internal val Orange800 = Color(0xFF682e00)
    internal val Orange900 = Color(0xFF241000)
    internal val Red50 = Color(0xFFfdf1f1)
    internal val Red100 = Color(0xFFfad6d6)
    internal val Red200 = Color(0xFFf6b2b2)
    internal val Red300 = Color(0xFFf39999)
    internal val Red400 = Color(0xFFef8080)
    internal val Red500 = Color(0xFFe63333)
    internal val Red600 = Color(0xFFe00000)
    internal val Red700 = Color(0xFFa10000)
    internal val Red800 = Color(0xFF620000)
    internal val Red900 = Color(0xFF240000)
    internal val White = Color(0xFFffffff)
    internal val Black = Color(0xFF000000)
    internal val Transparent = Color(0x00ffffff)
