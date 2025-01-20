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
import com.schibsted.nmp.warp.theme.WarpIconColors
import com.schibsted.nmp.warp.theme.WarpNavBarColors
import com.schibsted.nmp.warp.theme.WarpPageIndicatorColors
import com.schibsted.nmp.warp.theme.WarpPillColors
import com.schibsted.nmp.warp.theme.WarpSurfaceColors
import com.schibsted.nmp.warp.theme.WarpSwitchColors
import com.schibsted.nmp.warp.theme.WarpTextColors
import com.schibsted.nmp.warp.theme.WarpTooltipColors

// Generated on Thu, 09 Jan 2025 13:37:44 GMT by https://github.com/warp-ds/tokens

internal object DbaDarkColors : WarpColors {
    override val surface: WarpSurfaceColors = DbaDarkSurfaceColors
    override val background: WarpBackgroundColors = DbaDarkBackgroundColors
    override val border: WarpBorderColors = DbaDarkBorderColors
    override val icon: WarpIconColors = DbaDarkIconColors
    override val text: WarpTextColors = DbaDarkTextColors
    override val components: WarpComponentColors = DbaDarkComponentColors
}

internal object DbaDarkSurfaceColors : WarpSurfaceColors {
    override val sunken = Color(0xFF121212)
    override val elevated100 = Color(0xFF26262b)
    override val elevated100Hover = Color(0xFF2b2b30)
    override val elevated100Active = Color(0xFF333338)
    override val elevated200 = Color(0xFF2b2b30)
    override val elevated200Hover = Color(0xFF333338)
    override val elevated200Active = Color(0xFF47474f)
    override val elevated300 = Color(0xFF333338)
    override val elevated300Hover = Color(0xFF47474f)
    override val elevated300Active = Color(0xFF5c5c66)
}

internal object DbaDarkBackgroundColors : WarpBackgroundColors {
    override val default = Color(0xFF1b1b1f)
    override val hover = Color(0xFF26262b)
    override val active = Color(0xFF2b2b30)
    override val subtle = Color(0xFF333338)
    override val subtleHover = Color(0xFF47474f)
    override val subtleActive = Color(0xFF5c5c66)
    override val disabled = Color(0xFF47474f)
    override val disabledSubtle = Color(0xFF5c5c66)
    override val selected = Color(0xFF0e1b32)
    override val selectedHover = Color(0xFF1a3564)
    override val selectedActive = Color(0xFF264f96)
    override val inverted = Color(0xFFf6f6f6)
    override val primary = Color(0xFF7299da)
    override val primaryHover = Color(0xFF92b1e3)
    override val primaryActive = Color(0xFFb2c9ec)
    override val primarySubtle = Color(0xFF14284b)
    override val primarySubtleHover = Color(0xFF1a3564)
    override val primarySubtleActive = Color(0xFF20427d)
    override val secondary = Color(0xFF5057b2)
    override val secondaryHover = Color(0xFF787ec6)
    override val secondaryActive = Color(0xFFa0a5da)
    override val positive = Color(0xFF3ea07c)
    override val positiveHover = Color(0xFF62b294)
    override val positiveActive = Color(0xFF86c4ac)
    override val positiveSubtle = Color(0xFF072719)
    override val positiveSubtleHover = Color(0xFF0c3e2a)
    override val positiveSubtleActive = Color(0xFF136647)
    override val negative = Color(0xFFed686b)
    override val negativeHover = Color(0xFFe77c80)
    override val negativeActive = Color(0xFFefa3a6)
    override val negativeSubtle = Color(0xFF230103)
    override val negativeSubtleHover = Color(0xFF5d0306)
    override val negativeSubtleActive = Color(0xFF96050a)
    override val warning = Color(0xFFf5a051)
    override val warningHover = Color(0xFFf7b272)
    override val warningActive = Color(0xFFf9c493)
    override val warningSubtle = Color(0xFF221102)
    override val warningSubtleHover = Color(0xFF673a12)
    override val warningSubtleActive = Color(0xFFad6421)
    override val info = Color(0xFF5281d1)
    override val infoHover = Color(0xFF7299da)
    override val infoActive = Color(0xFF92b1e3)
    override val infoSubtle = Color(0xFF0e1b32)
    override val infoSubtleHover = Color(0xFF1a3564)
    override val infoSubtleActive = Color(0xFF000768)
    override val transparent0 = Color(0x00ffffff)
    override val notification = Color(0xFFde383d)
}

internal object DbaDarkBorderColors : WarpBorderColors {
    override val default = Color(0xFF5c5c66)
    override val hover = Color(0xFF84848f)
    override val active = Color(0xFFafafb8)
    override val disabled = Color(0xFF47474f)
    override val selected = Color(0xFF7299da)
    override val selectedHover = Color(0xFF92b1e3)
    override val selectedActive = Color(0xFFb2c9ec)
    override val inverted = Color(0xFF1b1b1f)
    override val primary = Color(0xFF7299da)
    override val primaryHover = Color(0xFF92b1e3)
    override val primaryActive = Color(0xFFb2c9ec)
    override val primarySubtle = Color(0xFF264f96)
    override val primarySubtleHover = Color(0xFF3269c8)
    override val primarySubtleActive = Color(0xFF5281d1)
    override val secondary = Color(0xFF5057b2)
    override val secondaryHover = Color(0xFF787ec6)
    override val secondaryActive = Color(0xFFa0a5da)
    override val positive = Color(0xFF3ea07c)
    override val positiveHover = Color(0xFF62b294)
    override val positiveActive = Color(0xFF86c4ac)
    override val positiveSubtle = Color(0xFF136647)
    override val positiveSubtleHover = Color(0xFF1a8f64)
    override val positiveSubtleActive = Color(0xFF3ea07c)
    override val negative = Color(0xFFed686b)
    override val negativeHover = Color(0xFFe77c80)
    override val negativeActive = Color(0xFFefa3a6)
    override val negativeSubtle = Color(0xFF96050a)
    override val negativeSubtleHover = Color(0xFFcf070e)
    override val negativeSubtleActive = Color(0xFFde383d)
    override val warning = Color(0xFFf5a051)
    override val warningHover = Color(0xFFf7b272)
    override val warningActive = Color(0xFFf9c493)
    override val warningSubtle = Color(0xFFad6421)
    override val warningSubtleHover = Color(0xFFf38e30)
    override val warningSubtleActive = Color(0xFFf5a051)
    override val info = Color(0xFF5281d1)
    override val infoHover = Color(0xFF7299da)
    override val infoActive = Color(0xFF92b1e3)
    override val infoSubtle = Color(0xFF264f96)
    override val infoSubtleHover = Color(0xFF3269c8)
    override val infoSubtleActive = Color(0xFF5281d1)
    override val focus = Color(0xFF92b1e3)
}
   
internal object DbaDarkIconColors : WarpIconColors {
    override val default = Color(0xFFffffff)
    override val hover = Color(0xFFd2e1f5)
    override val active = Color(0xFFb2c9ec)
    override val static = Color(0xFF1b1b1f)
    override val selected = Color(0xFF7299da)
    override val selectedHover = Color(0xFF92b1e3)
    override val selectedActive = Color(0xFFb2c9ec)
    override val disabled = Color(0xFF5c5c66)
    override val subtle = Color(0xFFafafb8)
    override val subtleHover = Color(0xFFcacad1)
    override val subtleActive = Color(0xFFdedee3)
    override val inverted = Color(0xFF1b1b1f)
    override val invertedHover = Color(0xFF26262b)
    override val invertedActive = Color(0xFF2b2b30)
    override val invertedStatic = Color(0xFFffffff)
    override val primary = Color(0xFF7299da)
    override val secondary = Color(0xFF5057b2)
    override val secondaryHover = Color(0xFF787ec6)
    override val secondaryActive = Color(0xFFa0a5da)
    override val positive = Color(0xFF3ea07c)
    override val negative = Color(0xFFed686b)
    override val warning = Color(0xFFf5a051)
    override val info = Color(0xFF5281d1)
    override val notification = Color(0xFFffffff)
}

internal object DbaDarkTextColors : WarpTextColors {
    override val default = Color(0xFFffffff)
    override val subtle = Color(0xFFafafb8)
    override val static = Color(0xFF1b1b1f)
    override val placeholder = Color(0xFF84848f)
    override val inverted = Color(0xFF1b1b1f)
    override val invertedSubtle = Color(0xFF5c5c66)
    override val invertedStatic = Color(0xFFffffff)
    override val link = Color(0xFF7299da)
    override val disabled = Color(0xFF84848f)
    override val negative = Color(0xFFed686b)
    override val positive = Color(0xFF3ea07c)
}

internal object DbaDarkComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = DbaDarkButtonColors
    override val badge: WarpBadgeColors = DbaDarkBadgeColors
    override val callout: WarpCalloutColors = DbaDarkCalloutColors
    override val pill: WarpPillColors = DbaDarkPillColors
    override val navBar: WarpNavBarColors = DbaDarkNavBarColors
    override val tooltip: WarpTooltipColors = DbaDarkTooltipColors
    override val switch: WarpSwitchColors = DbaDarkSwitchColors
    override val card: WarpCardColors = DbaDarkCardColors
    override val pageIndicator: WarpPageIndicatorColors = DbaDarkPageIndicatorColors
}

internal object DbaDarkButtonColors : WarpButtonColors {
    override val primaryBackground = Color(0xFF7299da)
    override val primaryBackgroundHover = Color(0xFF92b1e3)
    override val primaryBackgroundActive = Color(0xFFb2c9ec)
    override val pillBackgroundHover = Color(0xFF92b1e3)
    override val pillBackgroundActive = Color(0xFF7299da)
}

internal object DbaDarkBadgeColors : WarpBadgeColors {
    override val neutralBackground = Color(0xFF5c5c66)
    override val positiveBackground = Color(0xFF136647)
    override val infoBackground = Color(0xFF264f96)
    override val warningBackground = Color(0xFFad6421)
    override val negativeBackground = Color(0xFF96050a)
    override val sponsoredBackground = Color(0xFF3269c8)
    override val priceBackground = Color(0xFF000000)
}

internal object DbaDarkCalloutColors : WarpCalloutColors {
    override val background = Color(0xFF0c3e2a)
    override val border = Color(0xFF1a8f64)
}

internal object DbaDarkPillColors : WarpPillColors {
    override val suggestionBackground = Color(0xFF5c5c66)
    override val suggestionBackgroundHover = Color(0xFF84848f)
    override val suggestionBackgroundActive = Color(0xFFafafb8)
}

internal object DbaDarkNavBarColors : WarpNavBarColors {
    override val iconSelected = Color(0xFF5057b2)
    override val borderSelected = Color(0xFF5057b2)
}

internal object DbaDarkTooltipColors : WarpTooltipColors {
    override val backgroundStatic = Color(0xFF333338)
}

private object DbaDarkSwitchColors : WarpSwitchColors {
    override val handleBackground = Color(0xFF84848f)
    override val handleBackgroundHover = Color(0xFFafafb8)
    override val trackBorder = Color(0xFF84848f)
    override val trackBorderHover = Color(0xFFafafb8)
}

private object DbaDarkCardColors : WarpCardColors {
    override val defaultBackground = Color(0xFF1b1b1f)
}

private object DbaDarkPageIndicatorColors : WarpPageIndicatorColors {
    override val indicatorBackground = Color(0xFFcacad1)
    override val indicatorBackgroundHover = Color(0xFFdedee3)
}

