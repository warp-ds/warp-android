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
import com.schibsted.nmp.warp.theme.dataviz.DatavizDarkColors

// Generated on Wed, 01 Oct 2025 09:00:59 GMT by https://github.com/warp-ds/tokens

internal object NeutralDarkColors : WarpColors {
    override val surface: WarpSurfaceColors = NeutralDarkSurfaceColors
    override val background: WarpBackgroundColors = NeutralDarkBackgroundColors
    override val border: WarpBorderColors = NeutralDarkBorderColors
    override val icon: WarpIconColors = NeutralDarkIconColors
    override val text: WarpTextColors = NeutralDarkTextColors
    override val components: WarpComponentColors = NeutralDarkComponentColors
    override val dataviz: WarpDatavizColors = DatavizDarkColors
}

internal object NeutralDarkSurfaceColors : WarpSurfaceColors {
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

internal object NeutralDarkBackgroundColors : WarpBackgroundColors {
    override val default = Color(0xFF1b1b1f)
    override val hover = Color(0xFF26262b)
    override val active = Color(0xFF2b2b30)
    override val subtle = Color(0xFF333338)
    override val subtleHover = Color(0xFF47474f)
    override val subtleActive = Color(0xFF5c5c66)
    override val disabled = Color(0xFF47474f)
    override val disabledSubtle = Color(0xFF5c5c66)
    override val selected = Color(0xFF001d3d)
    override val selectedHover = Color(0xFF003977)
    override val selectedActive = Color(0xFF0055b1)
    override val inverted = Color(0xFFf6f6f6)
    override val primary = Color(0xFF509df1)
    override val primaryHover = Color(0xFF78b3f4)
    override val primaryActive = Color(0xFFa0c9f7)
    override val primarySubtle = Color(0xFF002b5a)
    override val primarySubtleHover = Color(0xFF003977)
    override val primarySubtleActive = Color(0xFF004794)
    override val secondary = Color(0xFFffffff)
    override val secondaryHover = Color(0xFFf0f0f2)
    override val secondaryActive = Color(0xFFdedee3)
    override val positive = Color(0xFF8dc570)
    override val positiveHover = Color(0xFFabd493)
    override val positiveActive = Color(0xFFc7e2b6)
    override val positiveSubtle = Color(0xFF0a2000)
    override val positiveSubtleHover = Color(0xFF124000)
    override val positiveSubtleActive = Color(0xFF1a6100)
    override val negative = Color(0xFFef8080)
    override val negativeHover = Color(0xFFf39999)
    override val negativeActive = Color(0xFFf6b2b2)
    override val negativeSubtle = Color(0xFF240000)
    override val negativeSubtleHover = Color(0xFF620000)
    override val negativeSubtleActive = Color(0xFFa10000)
    override val warning = Color(0xFFff8725)
    override val warningHover = Color(0xFFffaa64)
    override val warningActive = Color(0xFFffbb84)
    override val warningSubtle = Color(0xFF241000)
    override val warningSubtleHover = Color(0xFF682e00)
    override val warningSubtleActive = Color(0xFFac4d00)
    override val info = Color(0xFF2887ee)
    override val infoHover = Color(0xFF509df1)
    override val infoActive = Color(0xFF78b3f4)
    override val infoSubtle = Color(0xFF001d3d)
    override val infoSubtleHover = Color(0xFF003977)
    override val infoSubtleActive = Color(0xFF0055b1)
    override val transparent0 = Color(0x00ffffff)
    override val notification = Color(0xFFe63333)
}

internal object NeutralDarkBorderColors : WarpBorderColors {
    override val default = Color(0xFF5c5c66)
    override val hover = Color(0xFF84848f)
    override val active = Color(0xFFafafb8)
    override val strong = Color(0xFF84848f)
    override val strongHover = Color(0xFFafafb8)
    override val disabled = Color(0xFF47474f)
    override val selected = Color(0xFF509df1)
    override val selectedHover = Color(0xFF78b3f4)
    override val selectedActive = Color(0xFFa0c9f7)
    override val inverted = Color(0xFF1b1b1f)
    override val primary = Color(0xFF509df1)
    override val primaryHover = Color(0xFF78b3f4)
    override val primaryActive = Color(0xFFa0c9f7)
    override val primarySubtle = Color(0xFF0055b1)
    override val primarySubtleHover = Color(0xFF0071eb)
    override val primarySubtleActive = Color(0xFF2887ee)
    override val secondary = Color(0xFFffffff)
    override val secondaryHover = Color(0xFFf0f0f2)
    override val secondaryActive = Color(0xFFdedee3)
    override val positive = Color(0xFF8dc570)
    override val positiveHover = Color(0xFFabd493)
    override val positiveActive = Color(0xFFc7e2b6)
    override val positiveSubtle = Color(0xFF1a6100)
    override val positiveSubtleHover = Color(0xFF228000)
    override val positiveSubtleActive = Color(0xFF4ba722)
    override val negative = Color(0xFFef8080)
    override val negativeHover = Color(0xFFf39999)
    override val negativeActive = Color(0xFFf6b2b2)
    override val negativeSubtle = Color(0xFFa10000)
    override val negativeSubtleHover = Color(0xFFe00000)
    override val negativeSubtleActive = Color(0xFFe63333)
    override val warning = Color(0xFFff8725)
    override val warningHover = Color(0xFFffaa64)
    override val warningActive = Color(0xFFffbb84)
    override val warningSubtle = Color(0xFFac4d00)
    override val warningSubtleHover = Color(0xFFf16c00)
    override val warningSubtleActive = Color(0xFFff8725)
    override val info = Color(0xFF2887ee)
    override val infoHover = Color(0xFF509df1)
    override val infoActive = Color(0xFF78b3f4)
    override val infoSubtle = Color(0xFF0055b1)
    override val infoSubtleHover = Color(0xFF0071eb)
    override val infoSubtleActive = Color(0xFF2887ee)
    override val focus = Color(0xFF78b3f4)
}
   
internal object NeutralDarkIconColors : WarpIconColors {
    override val default = Color(0xFFffffff)
    override val hover = Color(0xFFc8dffa)
    override val active = Color(0xFFa0c9f7)
    override val static = Color(0xFF1b1b1f)
    override val selected = Color(0xFF509df1)
    override val selectedHover = Color(0xFF78b3f4)
    override val selectedActive = Color(0xFFa0c9f7)
    override val disabled = Color(0xFF5c5c66)
    override val subtle = Color(0xFFafafb8)
    override val subtleHover = Color(0xFFcacad1)
    override val subtleActive = Color(0xFFdedee3)
    override val inverted = Color(0xFF1b1b1f)
    override val invertedHover = Color(0xFF26262b)
    override val invertedActive = Color(0xFF2b2b30)
    override val invertedStatic = Color(0xFFffffff)
    override val primary = Color(0xFF509df1)
    override val secondary = Color(0xFFffffff)
    override val secondaryHover = Color(0xFFf0f0f2)
    override val secondaryActive = Color(0xFFdedee3)
    override val positive = Color(0xFF8dc570)
    override val negative = Color(0xFFef8080)
    override val warning = Color(0xFFff8725)
    override val info = Color(0xFF2887ee)
    override val notification = Color(0xFFffffff)
}

internal object NeutralDarkTextColors : WarpTextColors {
    override val default = Color(0xFFffffff)
    override val subtle = Color(0xFFafafb8)
    override val static = Color(0xFF1b1b1f)
    override val placeholder = Color(0xFF84848f)
    override val inverted = Color(0xFF1b1b1f)
    override val invertedSubtle = Color(0xFF5c5c66)
    override val invertedStatic = Color(0xFFffffff)
    override val link = Color(0xFF509df1)
    override val disabled = Color(0xFF84848f)
    override val negative = Color(0xFFef8080)
    override val positive = Color(0xFF8dc570)
}

internal object NeutralDarkComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = NeutralDarkButtonColors
    override val badge: WarpBadgeColors = NeutralDarkBadgeColors
    override val callout: WarpCalloutColors = NeutralDarkCalloutColors
    override val pill: WarpPillColors = NeutralDarkPillColors
    override val navBar: WarpNavBarColors = NeutralDarkNavBarColors
    override val tooltip: WarpTooltipColors = NeutralDarkTooltipColors
    override val switch: WarpSwitchColors = NeutralDarkSwitchColors
    override val card: WarpCardColors = NeutralDarkCardColors
    override val pageIndicator: WarpPageIndicatorColors = NeutralDarkPageIndicatorColors
}

internal object NeutralDarkButtonColors : WarpButtonColors {
    override val primaryBackground = Color(0xFF509df1)
    override val primaryBackgroundHover = Color(0xFF78b3f4)
    override val primaryBackgroundActive = Color(0xFFa0c9f7)
    override val pillBackgroundHover = Color(0xFFcacad1)
    override val pillBackgroundActive = Color(0xFFafafb8)
}

internal object NeutralDarkBadgeColors : WarpBadgeColors {
    override val neutralBackground = Color(0xFF5c5c66)
    override val positiveBackground = Color(0xFF1a6100)
    override val infoBackground = Color(0xFF0055b1)
    override val warningBackground = Color(0xFFac4d00)
    override val negativeBackground = Color(0xFFa10000)
    override val sponsoredBackground = Color(0xFF0071eb)
    override val priceBackground = Color(0xFF000000)
}

internal object NeutralDarkCalloutColors : WarpCalloutColors {
    override val background = Color(0xFF124000)
    override val border = Color(0xFF228000)
}

internal object NeutralDarkPillColors : WarpPillColors {
    override val suggestionBackground = Color(0xFF5c5c66)
    override val suggestionBackgroundHover = Color(0xFF84848f)
    override val suggestionBackgroundActive = Color(0xFFafafb8)
}

internal object NeutralDarkNavBarColors : WarpNavBarColors {
    override val iconSelected = Color(0xFF509df1)
    override val borderSelected = Color(0xFF509df1)
}

internal object NeutralDarkTooltipColors : WarpTooltipColors {
    override val backgroundStatic = Color(0xFF333338)
}

private object NeutralDarkSwitchColors : WarpSwitchColors {
    override val handleBackground = Color(0xFF84848f)
    override val handleBackgroundHover = Color(0xFFafafb8)
    override val trackBorder = Color(0xFF84848f)
    override val trackBorderHover = Color(0xFFafafb8)
}

private object NeutralDarkCardColors : WarpCardColors {
    override val defaultBackground = Color(0xFF1b1b1f)
}

private object NeutralDarkPageIndicatorColors : WarpPageIndicatorColors {
    override val indicatorBackground = Color(0xFFcacad1)
    override val indicatorBackgroundHover = Color(0xFFdedee3)
}

