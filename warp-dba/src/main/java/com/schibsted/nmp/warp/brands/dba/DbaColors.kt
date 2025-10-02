package com.schibsted.nmp.warp.brands.dba
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

// Generated on Wed, 01 Oct 2025 09:00:59 GMT by https://github.com/warp-ds/tokens

internal object DbaColors : WarpColors {
    override val surface: WarpSurfaceColors = DbaSurfaceColors
    override val background: WarpBackgroundColors = DbaBackgroundColors
    override val border: WarpBorderColors = DbaBorderColors
    override val icon: WarpIconColors = DbaIconColors
    override val text: WarpTextColors = DbaTextColors
    override val components: WarpComponentColors = DbaComponentColors
    override val dataviz: WarpDatavizColors = DatavizColors
}

internal object DbaSurfaceColors : WarpSurfaceColors {
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

internal object DbaBackgroundColors : WarpBackgroundColors {
    override val default = Color(0xFFffffff)
    override val hover = Color(0xFFf0f0f2)
    override val active = Color(0xFFdedee3)
    override val subtle = Color(0xFFf0f0f2)
    override val subtleHover = Color(0xFFdedee3)
    override val subtleActive = Color(0xFFcacad1)
    override val disabled = Color(0xFFcacad1)
    override val disabledSubtle = Color(0xFFdedee3)
    override val selected = Color(0xFFf3f6fc)
    override val selectedHover = Color(0xFFd2e1f5)
    override val selectedActive = Color(0xFFb2c9ec)
    override val inverted = Color(0xFF1b1b1f)
    override val primary = Color(0xFF3269c8)
    override val primaryHover = Color(0xFF264f96)
    override val primaryActive = Color(0xFF1a3564)
    override val primarySubtle = Color(0xFFd2e1f5)
    override val primarySubtleHover = Color(0xFFb2c9ec)
    override val primarySubtleActive = Color(0xFF92b1e3)
    override val secondary = Color(0xFF00098a)
    override val secondaryHover = Color(0xFF000768)
    override val secondaryActive = Color(0xFF000546)
    override val positive = Color(0xFF1a8f64)
    override val positiveHover = Color(0xFF136647)
    override val positiveActive = Color(0xFF0c3e2a)
    override val positiveSubtle = Color(0xFFf3fcf9)
    override val positiveSubtleHover = Color(0xFFcee8dc)
    override val positiveSubtleActive = Color(0xFFaad6c4)
    override val negative = Color(0xFFcf070e)
    override val negativeHover = Color(0xFF96050a)
    override val negativeActive = Color(0xFF5d0306)
    override val negativeSubtle = Color(0xFFfff0f1)
    override val negativeSubtleHover = Color(0xFFf7cacc)
    override val negativeSubtleActive = Color(0xFFefa3a6)
    override val warning = Color(0xFFf38e30)
    override val warningHover = Color(0xFFad6421)
    override val warningActive = Color(0xFF673a12)
    override val warningSubtle = Color(0xFFfef7f1)
    override val warningSubtleHover = Color(0xFFfde8d5)
    override val warningSubtleActive = Color(0xFFfbd6b4)
    override val info = Color(0xFF3269c8)
    override val infoHover = Color(0xFF264f96)
    override val infoActive = Color(0xFF1a3564)
    override val infoSubtle = Color(0xFFf3f6fc)
    override val infoSubtleHover = Color(0xFFd2e1f5)
    override val infoSubtleActive = Color(0xFFb2c9ec)
    override val transparent0 = Color(0x00ffffff)
    override val notification = Color(0xFFcf070e)
}

internal object DbaBorderColors : WarpBorderColors {
    override val default = Color(0xFFcacad1)
    override val hover = Color(0xFFafafb8)
    override val active = Color(0xFF84848f)
    override val strong = Color(0xFF84848f)
    override val strongHover = Color(0xFF5c5c66)
    override val disabled = Color(0xFFcacad1)
    override val selected = Color(0xFF3269c8)
    override val selectedHover = Color(0xFF264f96)
    override val selectedActive = Color(0xFF1a3564)
    override val inverted = Color(0xFFffffff)
    override val primary = Color(0xFF3269c8)
    override val primaryHover = Color(0xFF264f96)
    override val primaryActive = Color(0xFF1a3564)
    override val primarySubtle = Color(0xFF92b1e3)
    override val primarySubtleHover = Color(0xFF7299da)
    override val primarySubtleActive = Color(0xFF5281d1)
    override val secondary = Color(0xFF00098a)
    override val secondaryHover = Color(0xFF000768)
    override val secondaryActive = Color(0xFF000546)
    override val positive = Color(0xFF1a8f64)
    override val positiveHover = Color(0xFF136647)
    override val positiveActive = Color(0xFF0c3e2a)
    override val positiveSubtle = Color(0xFF86c4ac)
    override val positiveSubtleHover = Color(0xFF62b294)
    override val positiveSubtleActive = Color(0xFF3ea07c)
    override val negative = Color(0xFFcf070e)
    override val negativeHover = Color(0xFF96050a)
    override val negativeActive = Color(0xFF5d0306)
    override val negativeSubtle = Color(0xFFe77c80)
    override val negativeSubtleHover = Color(0xFFed686b)
    override val negativeSubtleActive = Color(0xFFde383d)
    override val warning = Color(0xFFf38e30)
    override val warningHover = Color(0xFFad6421)
    override val warningActive = Color(0xFF673a12)
    override val warningSubtle = Color(0xFFf9c493)
    override val warningSubtleHover = Color(0xFFf7b272)
    override val warningSubtleActive = Color(0xFFf5a051)
    override val info = Color(0xFF3269c8)
    override val infoHover = Color(0xFF264f96)
    override val infoActive = Color(0xFF1a3564)
    override val infoSubtle = Color(0xFF92b1e3)
    override val infoSubtleHover = Color(0xFF7299da)
    override val infoSubtleActive = Color(0xFF5281d1)
    override val focus = Color(0xFF7299da)
}
   
internal object DbaIconColors : WarpIconColors {
    override val default = Color(0xFF1b1b1f)
    override val hover = Color(0xFF264f96)
    override val active = Color(0xFF1a3564)
    override val static = Color(0xFF1b1b1f)
    override val selected = Color(0xFF3269c8)
    override val selectedHover = Color(0xFF264f96)
    override val selectedActive = Color(0xFF1a3564)
    override val disabled = Color(0xFFcacad1)
    override val subtle = Color(0xFF5c5c66)
    override val subtleHover = Color(0xFF47474f)
    override val subtleActive = Color(0xFF2b2b30)
    override val inverted = Color(0xFFffffff)
    override val invertedHover = Color(0xFFf0f0f2)
    override val invertedActive = Color(0xFFdedee3)
    override val invertedStatic = Color(0xFFffffff)
    override val primary = Color(0xFF3269c8)
    override val secondary = Color(0xFF00098a)
    override val secondaryHover = Color(0xFF000768)
    override val secondaryActive = Color(0xFF000546)
    override val positive = Color(0xFF1a8f64)
    override val negative = Color(0xFFcf070e)
    override val warning = Color(0xFFf38e30)
    override val info = Color(0xFF3269c8)
    override val notification = Color(0xFFffffff)
}

internal object DbaTextColors : WarpTextColors {
    override val default = Color(0xFF1b1b1f)
    override val subtle = Color(0xFF5c5c66)
    override val static = Color(0xFF1b1b1f)
    override val placeholder = Color(0xFFcacad1)
    override val inverted = Color(0xFFffffff)
    override val invertedSubtle = Color(0xFFafafb8)
    override val invertedStatic = Color(0xFFffffff)
    override val link = Color(0xFF3269c8)
    override val disabled = Color(0xFFcacad1)
    override val negative = Color(0xFFcf070e)
    override val positive = Color(0xFF1a8f64)
}

internal object DbaComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = DbaButtonColors
    override val badge: WarpBadgeColors = DbaBadgeColors
    override val callout: WarpCalloutColors = DbaCalloutColors
    override val pill: WarpPillColors = DbaPillColors
    override val navBar: WarpNavBarColors = DbaNavBarColors
    override val tooltip: WarpTooltipColors = DbaTooltipColors
    override val switch: WarpSwitchColors = DbaSwitchColors
    override val card: WarpCardColors = DbaCardColors
    override val pageIndicator: WarpPageIndicatorColors = DbaPageIndicatorColors
}

internal object DbaButtonColors : WarpButtonColors {
    override val primaryBackground = Color(0xFF3269c8)
    override val primaryBackgroundHover = Color(0xFF264f96)
    override val primaryBackgroundActive = Color(0xFF1a3564)
    override val pillBackgroundHover = Color(0xFF92b1e3)
    override val pillBackgroundActive = Color(0xFF7299da)
}

internal object DbaBadgeColors : WarpBadgeColors {
    override val neutralBackground = Color(0xFFf0f0f2)
    override val positiveBackground = Color(0xFFcee8dc)
    override val infoBackground = Color(0xFFd2e1f5)
    override val warningBackground = Color(0xFFfde8d5)
    override val negativeBackground = Color(0xFFf7cacc)
    override val sponsoredBackground = Color(0xFFb2c9ec)
    override val priceBackground = Color(0xFF000000)
}

internal object DbaCalloutColors : WarpCalloutColors {
    override val background = Color(0xFFcee8dc)
    override val border = Color(0xFF62b294)
}

internal object DbaPillColors : WarpPillColors {
    override val suggestionBackground = Color(0xFFdedee3)
    override val suggestionBackgroundHover = Color(0xFFcacad1)
    override val suggestionBackgroundActive = Color(0xFFafafb8)
}

internal object DbaNavBarColors : WarpNavBarColors {
    override val iconSelected = Color(0xFF00098a)
    override val borderSelected = Color(0xFF00098a)
}

internal object DbaTooltipColors : WarpTooltipColors {
    override val backgroundStatic = Color(0xFF1b1b1f)
}

private object DbaSwitchColors : WarpSwitchColors {
    override val handleBackground = Color(0xFF84848f)
    override val handleBackgroundHover = Color(0xFF5c5c66)
    override val trackBorder = Color(0xFF84848f)
    override val trackBorderHover = Color(0xFF5c5c66)
}

private object DbaCardColors : WarpCardColors {
    override val defaultBackground = Color(0xFFffffff)
}

private object DbaPageIndicatorColors : WarpPageIndicatorColors {
    override val indicatorBackground = Color(0xFFcacad1)
    override val indicatorBackgroundHover = Color(0xFFafafb8)
}
    internal val Jeanblue50 = Color(0xFFf3f6fc)
    internal val Jeanblue100 = Color(0xFFd2e1f5)
    internal val Jeanblue200 = Color(0xFFb2c9ec)
    internal val Jeanblue300 = Color(0xFF92b1e3)
    internal val Jeanblue400 = Color(0xFF7299da)
    internal val Jeanblue500 = Color(0xFF5281d1)
    internal val Jeanblue600 = Color(0xFF3269c8)
    internal val Jeanblue700 = Color(0xFF264f96)
    internal val Jeanblue750 = Color(0xFF20427d)
    internal val Jeanblue800 = Color(0xFF1a3564)
    internal val Jeanblue850 = Color(0xFF14284b)
    internal val Jeanblue900 = Color(0xFF0e1b32)
    internal val Jeanblue950 = Color(0xFF080e19)
    internal val Phthaloblue50 = Color(0xFFf0f1ff)
    internal val Phthaloblue100 = Color(0xFFc8ccee)
    internal val Phthaloblue200 = Color(0xFFa0a5da)
    internal val Phthaloblue300 = Color(0xFF787ec6)
    internal val Phthaloblue400 = Color(0xFF5057b2)
    internal val Phthaloblue500 = Color(0xFF28309e)
    internal val Phthaloblue600 = Color(0xFF00098a)
    internal val Phthaloblue700 = Color(0xFF000768)
    internal val Phthaloblue800 = Color(0xFF000546)
    internal val Phthaloblue900 = Color(0xFF000324)
    internal val Green50 = Color(0xFFf3fcf9)
    internal val Green100 = Color(0xFFcee8dc)
    internal val Green200 = Color(0xFFaad6c4)
    internal val Green300 = Color(0xFF86c4ac)
    internal val Green400 = Color(0xFF62b294)
    internal val Green500 = Color(0xFF3ea07c)
    internal val Green600 = Color(0xFF1a8f64)
    internal val Green700 = Color(0xFF136647)
    internal val Green800 = Color(0xFF0c3e2a)
    internal val Green900 = Color(0xFF072719)
    internal val Yellow50 = Color(0xFFfef7f1)
    internal val Yellow100 = Color(0xFFfde8d5)
    internal val Yellow200 = Color(0xFFfbd6b4)
    internal val Yellow300 = Color(0xFFf9c493)
    internal val Yellow400 = Color(0xFFf7b272)
    internal val Yellow500 = Color(0xFFf5a051)
    internal val Yellow600 = Color(0xFFf38e30)
    internal val Yellow700 = Color(0xFFad6421)
    internal val Yellow800 = Color(0xFF673a12)
    internal val Yellow900 = Color(0xFF221102)
    internal val Red50 = Color(0xFFfff0f1)
    internal val Red100 = Color(0xFFf7cacc)
    internal val Red200 = Color(0xFFefa3a6)
    internal val Red300 = Color(0xFFe77c80)
    internal val Red400 = Color(0xFFed686b)
    internal val Red500 = Color(0xFFde383d)
    internal val Red600 = Color(0xFFcf070e)
    internal val Red700 = Color(0xFF96050a)
    internal val Red800 = Color(0xFF5d0306)
    internal val Red900 = Color(0xFF230103)
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
    internal val White = Color(0xFFffffff)
    internal val Black = Color(0xFF000000)
    internal val Transparent = Color(0x00ffffff)
