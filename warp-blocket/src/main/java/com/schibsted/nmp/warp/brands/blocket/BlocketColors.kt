package com.schibsted.nmp.warp.brands.blocket
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

internal object BlocketColors : WarpColors {
    override val surface: WarpSurfaceColors = BlocketSurfaceColors
    override val background: WarpBackgroundColors = BlocketBackgroundColors
    override val border: WarpBorderColors = BlocketBorderColors
    override val icon: WarpIconColors = BlocketIconColors
    override val text: WarpTextColors = BlocketTextColors
    override val components: WarpComponentColors = BlocketComponentColors
    override val dataviz: WarpDatavizColors = DatavizColors
}

internal object BlocketSurfaceColors : WarpSurfaceColors {
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

internal object BlocketBackgroundColors : WarpBackgroundColors {
    override val default = Color(0xFFffffff)
    override val hover = Color(0xFFf0f0f2)
    override val active = Color(0xFFdedee3)
    override val subtle = Color(0xFFf0f0f2)
    override val subtleHover = Color(0xFFdedee3)
    override val subtleActive = Color(0xFFcacad1)
    override val disabled = Color(0xFFcacad1)
    override val disabledSubtle = Color(0xFFdedee3)
    override val selected = Color(0xFFf0f7ff)
    override val selectedHover = Color(0xFFc8dffa)
    override val selectedActive = Color(0xFFa0c9f7)
    override val inverted = Color(0xFF1b1b1f)
    override val primary = Color(0xFF0071eb)
    override val primaryHover = Color(0xFF0055b1)
    override val primaryActive = Color(0xFF003977)
    override val primarySubtle = Color(0xFFc8dffa)
    override val primarySubtleHover = Color(0xFF78b3f4)
    override val primarySubtleActive = Color(0xFFa0c9f7)
    override val secondary = Color(0xFFe90017)
    override val secondaryHover = Color(0xFFa70011)
    override val secondaryActive = Color(0xFF65000b)
    override val positive = Color(0xFF24925d)
    override val positiveHover = Color(0xFF1a6b44)
    override val positiveActive = Color(0xFF10442b)
    override val positiveSubtle = Color(0xFFf7fdfa)
    override val positiveSubtleHover = Color(0xFFd3ecdf)
    override val positiveSubtleActive = Color(0xFFb0dac5)
    override val negative = Color(0xFFd93c2b)
    override val negativeHover = Color(0xFF9b2b1e)
    override val negativeActive = Color(0xFF5d1a11)
    override val negativeSubtle = Color(0xFFfaf3f2)
    override val negativeSubtleHover = Color(0xFFf7d7d5)
    override val negativeSubtleActive = Color(0xFFf1b8b3)
    override val warning = Color(0xFFffb500)
    override val warningHover = Color(0xFFb67b00)
    override val warningActive = Color(0xFF6d4100)
    override val warningSubtle = Color(0xFFfcf5e3)
    override val warningSubtleHover = Color(0xFFfff1c8)
    override val warningSubtleActive = Color(0xFFffe5a0)
    override val info = Color(0xFF0071eb)
    override val infoHover = Color(0xFF0055b1)
    override val infoActive = Color(0xFF003977)
    override val infoSubtle = Color(0xFFf0f7ff)
    override val infoSubtleHover = Color(0xFFc8dffa)
    override val infoSubtleActive = Color(0xFFa0c9f7)
    override val transparent0 = Color(0x00ffffff)
    override val notification = Color(0xFFe90017)
}

internal object BlocketBorderColors : WarpBorderColors {
    override val default = Color(0xFFcacad1)
    override val hover = Color(0xFFafafb8)
    override val active = Color(0xFF84848f)
    override val strong = Color(0xFF84848f)
    override val strongHover = Color(0xFF5c5c66)
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
    override val secondary = Color(0xFFe90017)
    override val secondaryHover = Color(0xFFa70011)
    override val secondaryActive = Color(0xFF65000b)
    override val positive = Color(0xFF24925d)
    override val positiveHover = Color(0xFF1a6b44)
    override val positiveActive = Color(0xFF10442b)
    override val positiveSubtle = Color(0xFF8dc8ab)
    override val positiveSubtleHover = Color(0xFF6ab691)
    override val positiveSubtleActive = Color(0xFF47a477)
    override val negative = Color(0xFFd93c2b)
    override val negativeHover = Color(0xFF9b2b1e)
    override val negativeActive = Color(0xFF5d1a11)
    override val negativeSubtle = Color(0xFFeb9991)
    override val negativeSubtleHover = Color(0xFFe57a6f)
    override val negativeSubtleActive = Color(0xFFdf5b4d)
    override val warning = Color(0xFFffb500)
    override val warningHover = Color(0xFFb67b00)
    override val warningActive = Color(0xFF6d4100)
    override val warningSubtle = Color(0xFFffd978)
    override val warningSubtleHover = Color(0xFFffcd50)
    override val warningSubtleActive = Color(0xFFffc128)
    override val info = Color(0xFF0071eb)
    override val infoHover = Color(0xFF0055b1)
    override val infoActive = Color(0xFF003977)
    override val infoSubtle = Color(0xFF78b3f4)
    override val infoSubtleHover = Color(0xFF509df1)
    override val infoSubtleActive = Color(0xFF2887ee)
    override val focus = Color(0xFF509df1)
}
   
internal object BlocketIconColors : WarpIconColors {
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
    override val secondary = Color(0xFFe90017)
    override val secondaryHover = Color(0xFFa70011)
    override val secondaryActive = Color(0xFF65000b)
    override val positive = Color(0xFF24925d)
    override val negative = Color(0xFFd93c2b)
    override val warning = Color(0xFFffb500)
    override val info = Color(0xFF0071eb)
    override val notification = Color(0xFFffffff)
}

internal object BlocketTextColors : WarpTextColors {
    override val default = Color(0xFF1b1b1f)
    override val subtle = Color(0xFF5c5c66)
    override val static = Color(0xFF1b1b1f)
    override val placeholder = Color(0xFFcacad1)
    override val inverted = Color(0xFFffffff)
    override val invertedSubtle = Color(0xFFafafb8)
    override val invertedStatic = Color(0xFFffffff)
    override val link = Color(0xFF0071eb)
    override val disabled = Color(0xFFcacad1)
    override val negative = Color(0xFFd93c2b)
    override val positive = Color(0xFF24925d)
}

internal object BlocketComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = BlocketButtonColors
    override val badge: WarpBadgeColors = BlocketBadgeColors
    override val callout: WarpCalloutColors = BlocketCalloutColors
    override val pill: WarpPillColors = BlocketPillColors
    override val navBar: WarpNavBarColors = BlocketNavBarColors
    override val tooltip: WarpTooltipColors = BlocketTooltipColors
    override val switch: WarpSwitchColors = BlocketSwitchColors
    override val card: WarpCardColors = BlocketCardColors
    override val pageIndicator: WarpPageIndicatorColors = BlocketPageIndicatorColors
}

internal object BlocketButtonColors : WarpButtonColors {
    override val primaryBackground = Color(0xFF0071eb)
    override val primaryBackgroundHover = Color(0xFF0055b1)
    override val primaryBackgroundActive = Color(0xFF003977)
    override val pillBackgroundHover = Color(0xFF78b3f4)
    override val pillBackgroundActive = Color(0xFF509df1)
}

internal object BlocketBadgeColors : WarpBadgeColors {
    override val neutralBackground = Color(0xFFf0f0f2)
    override val positiveBackground = Color(0xFFd3ecdf)
    override val infoBackground = Color(0xFFc8dffa)
    override val warningBackground = Color(0xFFfff1c8)
    override val negativeBackground = Color(0xFFf7d7d5)
    override val sponsoredBackground = Color(0xFFa0c9f7)
    override val priceBackground = Color(0xFF000000)
}

internal object BlocketCalloutColors : WarpCalloutColors {
    override val background = Color(0xFFd3ecdf)
    override val border = Color(0xFF6ab691)
}

internal object BlocketPillColors : WarpPillColors {
    override val suggestionBackground = Color(0xFFdedee3)
    override val suggestionBackgroundHover = Color(0xFFcacad1)
    override val suggestionBackgroundActive = Color(0xFFafafb8)
}

internal object BlocketNavBarColors : WarpNavBarColors {
    override val iconSelected = Color(0xFFe90017)
    override val borderSelected = Color(0xFFe90017)
}

internal object BlocketTooltipColors : WarpTooltipColors {
    override val backgroundStatic = Color(0xFF1b1b1f)
}

private object BlocketSwitchColors : WarpSwitchColors {
    override val handleBackground = Color(0xFF84848f)
    override val handleBackgroundHover = Color(0xFF5c5c66)
    override val trackBorder = Color(0xFF84848f)
    override val trackBorderHover = Color(0xFF5c5c66)
}

private object BlocketCardColors : WarpCardColors {
    override val defaultBackground = Color(0xFFffffff)
}

private object BlocketPageIndicatorColors : WarpPageIndicatorColors {
    override val indicatorBackground = Color(0xFFcacad1)
    override val indicatorBackgroundHover = Color(0xFFafafb8)
}
    internal val White = Color(0xFFffffff)
    internal val Black = Color(0xFF000000)
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
    internal val Red50 = Color(0xFFfff0f1)
    internal val Red100 = Color(0xFFfdc8cb)
    internal val Red200 = Color(0xFFf9a0a7)
    internal val Red300 = Color(0xFFf57883)
    internal val Red400 = Color(0xFFf1505f)
    internal val Red500 = Color(0xFFed283b)
    internal val Red600 = Color(0xFFe90017)
    internal val Red700 = Color(0xFFa70011)
    internal val Red800 = Color(0xFF65000b)
    internal val Red900 = Color(0xFF240004)
    internal val Green50 = Color(0xFFf7fdfa)
    internal val Green100 = Color(0xFFd3ecdf)
    internal val Green200 = Color(0xFFb0dac5)
    internal val Green300 = Color(0xFF8dc8ab)
    internal val Green400 = Color(0xFF6ab691)
    internal val Green500 = Color(0xFF47a477)
    internal val Green600 = Color(0xFF24925d)
    internal val Green700 = Color(0xFF1a6b44)
    internal val Green800 = Color(0xFF10442b)
    internal val Green900 = Color(0xFF071d12)
    internal val Yellow50 = Color(0xFFfcf5e3)
    internal val Yellow100 = Color(0xFFfff1c8)
    internal val Yellow200 = Color(0xFFffe5a0)
    internal val Yellow300 = Color(0xFFffd978)
    internal val Yellow400 = Color(0xFFffcd50)
    internal val Yellow500 = Color(0xFFffc128)
    internal val Yellow600 = Color(0xFFffb500)
    internal val Yellow700 = Color(0xFFb67b00)
    internal val Yellow800 = Color(0xFF6d4100)
    internal val Yellow900 = Color(0xFF240700)
    internal val Coral50 = Color(0xFFfaf3f2)
    internal val Coral100 = Color(0xFFf7d7d5)
    internal val Coral200 = Color(0xFFf1b8b3)
    internal val Coral300 = Color(0xFFeb9991)
    internal val Coral400 = Color(0xFFe57a6f)
    internal val Coral500 = Color(0xFFdf5b4d)
    internal val Coral600 = Color(0xFFd93c2b)
    internal val Coral700 = Color(0xFF9b2b1e)
    internal val Coral800 = Color(0xFF5d1a11)
    internal val Coral900 = Color(0xFF1f0805)
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
    internal val Transparent = Color(0x00ffffff)
