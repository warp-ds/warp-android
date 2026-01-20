package com.schibsted.nmp.warp.brands.vend
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

// Generated on Tue, 06 Jan 2026 08:48:49 GMT by https://github.com/warp-ds/tokens

internal object VendColors : WarpColors {
    override val surface: WarpSurfaceColors = VendSurfaceColors
    override val background: WarpBackgroundColors = VendBackgroundColors
    override val border: WarpBorderColors = VendBorderColors
    override val icon: WarpIconColors = VendIconColors
    override val text: WarpTextColors = VendTextColors
    override val components: WarpComponentColors = VendComponentColors
    override val dataviz: WarpDatavizColors = DatavizColors
}

internal object VendSurfaceColors : WarpSurfaceColors {
    override val sunken = Color(0xFFfafafb)
    override val elevated100 = Color(0xFFffffff)
    override val elevated100Hover = Color(0xFFefeff2)
    override val elevated100Active = Color(0xFFe5e5ea)
    override val elevated200 = Color(0xFFffffff)
    override val elevated200Hover = Color(0xFFefeff2)
    override val elevated200Active = Color(0xFFe5e5ea)
    override val elevated300 = Color(0xFFffffff)
    override val elevated300Hover = Color(0xFFefeff2)
    override val elevated300Active = Color(0xFFe5e5ea)
}

internal object VendBackgroundColors : WarpBackgroundColors {
    override val default = Color(0xFFffffff)
    override val hover = Color(0xFFefeff2)
    override val active = Color(0xFFe5e5ea)
    override val subtle = Color(0xFFe5e5ea)
    override val subtleHover = Color(0xFFc2c2c9)
    override val subtleActive = Color(0xFFa0a0a8)
    override val disabled = Color(0xFFc2c2c9)
    override val disabledSubtle = Color(0xFFe5e5ea)
    override val selected = Color(0xFFfafafb)
    override val selectedHover = Color(0xFFefeff2)
    override val selectedActive = Color(0xFFe5e5ea)
    override val inverted = Color(0xFF1b1b1f)
    override val primary = Color(0xFF1b1b1f)
    override val primaryHover = Color(0xFF2b2b30)
    override val primaryActive = Color(0xFF47474f)
    override val primarySubtle = Color(0xFFefeff2)
    override val primarySubtleHover = Color(0xFFe5e5ea)
    override val primarySubtleActive = Color(0xFFc2c2c9)
    override val secondary = Color(0xFF7a2822)
    override val secondaryHover = Color(0xFF711e17)
    override val secondaryActive = Color(0xFF5a0d08)
    override val positive = Color(0xFF4a8221)
    override val positiveHover = Color(0xFF376419)
    override val positiveActive = Color(0xFF264511)
    override val positiveSubtle = Color(0xFFf1faeb)
    override val positiveSubtleHover = Color(0xFFcfe8bf)
    override val positiveSubtleActive = Color(0xFFabd58e)
    override val negative = Color(0xFFe22d2c)
    override val negativeHover = Color(0xFFa32020)
    override val negativeActive = Color(0xFF641414)
    override val negativeSubtle = Color(0xFFfbe9e9)
    override val negativeSubtleHover = Color(0xFFf9caca)
    override val negativeSubtleActive = Color(0xFFf4a7a7)
    override val warning = Color(0xFFee873c)
    override val warningHover = Color(0xFFad5f28)
    override val warningActive = Color(0xFF6c3916)
    override val warningSubtle = Color(0xFFfdf3ed)
    override val warningSubtleHover = Color(0xFFfddfd0)
    override val warningSubtleActive = Color(0xFFfacdb2)
    override val info = Color(0xFF1b77d2)
    override val infoHover = Color(0xFF14579a)
    override val infoActive = Color(0xFF0d3862)
    override val infoSubtle = Color(0xFFe5f5ff)
    override val infoSubtleHover = Color(0xFFcceaff)
    override val infoSubtleActive = Color(0xFFadddff)
    override val transparent0 = Color(0xFFffffff00)
    override val notification = Color(0xFFe22d2c)
}

internal object VendBorderColors : WarpBorderColors {
    override val default = Color(0xFFc2c2c9)
    override val hover = Color(0xFFa0a0a8)
    override val active = Color(0xFF84848f)
    override val strong = Color(0xFF84848f)
    override val strongHover = Color(0xFF5c5c66)
    override val disabled = Color(0xFFc2c2c9)
    override val selected = Color(0xFF1b1b1f)
    override val selectedHover = Color(0xFF2b2b30)
    override val selectedActive = Color(0xFF47474f)
    override val inverted = Color(0xFFffffff)
    override val primary = Color(0xFF1b1b1f)
    override val primaryHover = Color(0xFF2b2b30)
    override val primaryActive = Color(0xFF47474f)
    override val primarySubtle = Color(0xFFa0a0a8)
    override val primarySubtleHover = Color(0xFF84848f)
    override val primarySubtleActive = Color(0xFF5c5c66)
    override val secondary = Color(0xFF7a2822)
    override val secondaryHover = Color(0xFF711e17)
    override val secondaryActive = Color(0xFF5a0d08)
    override val positive = Color(0xFF4a8221)
    override val positiveHover = Color(0xFF376419)
    override val positiveActive = Color(0xFF264511)
    override val positiveSubtle = Color(0xFF87c25d)
    override val positiveSubtleHover = Color(0xFF63af2c)
    override val positiveSubtleActive = Color(0xFF569927)
    override val negative = Color(0xFFe22d2c)
    override val negativeHover = Color(0xFFa32020)
    override val negativeActive = Color(0xFF641414)
    override val negativeSubtle = Color(0xFFef8484)
    override val negativeSubtleHover = Color(0xFFea6161)
    override val negativeSubtleActive = Color(0xFFe53f3e)
    override val warning = Color(0xFFee873c)
    override val warningHover = Color(0xFFad5f28)
    override val warningActive = Color(0xFF6c3916)
    override val warningSubtle = Color(0xFFf7bb94)
    override val warningSubtleHover = Color(0xFFf4a976)
    override val warningSubtleActive = Color(0xFFf19758)
    override val info = Color(0xFF1b77d2)
    override val infoHover = Color(0xFF14579a)
    override val infoActive = Color(0xFF0d3862)
    override val infoSubtle = Color(0xFF8ac5f3)
    override val infoSubtleHover = Color(0xFF65abe8)
    override val infoSubtleActive = Color(0xFF4091dd)
    override val focus = Color(0xFF65abe8)
}
   
internal object VendIconColors : WarpIconColors {
    override val default = Color(0xFF1b1b1f)
    override val hover = Color(0xFF2b2b30)
    override val active = Color(0xFF47474f)
    override val static = Color(0xFF1b1b1f)
    override val selected = Color(0xFF1b1b1f)
    override val selectedHover = Color(0xFF2b2b30)
    override val selectedActive = Color(0xFF47474f)
    override val disabled = Color(0xFFc2c2c9)
    override val subtle = Color(0xFF5c5c66)
    override val subtleHover = Color(0xFF47474f)
    override val subtleActive = Color(0xFF2b2b30)
    override val inverted = Color(0xFFffffff)
    override val invertedHover = Color(0xFFefeff2)
    override val invertedActive = Color(0xFFe5e5ea)
    override val invertedStatic = Color(0xFFffffff)
    override val primary = Color(0xFF1b1b1f)
    override val secondary = Color(0xFF7a2822)
    override val secondaryHover = Color(0xFF711e17)
    override val secondaryActive = Color(0xFF5a0d08)
    override val positive = Color(0xFF4a8221)
    override val negative = Color(0xFFe22d2c)
    override val warning = Color(0xFFee873c)
    override val info = Color(0xFF1b77d2)
    override val notification = Color(0xFFffffff)
}

internal object VendTextColors : WarpTextColors {
    override val default = Color(0xFF1b1b1f)
    override val subtle = Color(0xFF5c5c66)
    override val static = Color(0xFF1b1b1f)
    override val placeholder = Color(0xFFc2c2c9)
    override val inverted = Color(0xFFffffff)
    override val invertedSubtle = Color(0xFFa0a0a8)
    override val invertedStatic = Color(0xFFffffff)
    override val link = Color(0xFF1b1b1f)
    override val disabled = Color(0xFFc2c2c9)
    override val negative = Color(0xFFe22d2c)
    override val positive = Color(0xFF4a8221)
}

internal object VendComponentColors : WarpComponentColors {
    override val button: WarpButtonColors = VendButtonColors
    override val badge: WarpBadgeColors = VendBadgeColors
    override val callout: WarpCalloutColors = VendCalloutColors
    override val pill: WarpPillColors = VendPillColors
    override val navBar: WarpNavBarColors = VendNavBarColors
    override val tooltip: WarpTooltipColors = VendTooltipColors
    override val switch: WarpSwitchColors = VendSwitchColors
    override val card: WarpCardColors = VendCardColors
    override val pageIndicator: WarpPageIndicatorColors = VendPageIndicatorColors
}

internal object VendButtonColors : WarpButtonColors {
    override val primaryBackground = Color(0xFF1b1b1f)
    override val primaryBackgroundHover = Color(0xFF2b2b30)
    override val primaryBackgroundActive = Color(0xFF47474f)
    override val pillBackgroundHover = Color(0xFFc2c2c9)
    override val pillBackgroundActive = Color(0xFFa0a0a8)
}

internal object VendBadgeColors : WarpBadgeColors {
    override val neutralBackground = Color(0xFFefeff2)
    override val positiveBackground = Color(0xFFcfe8bf)
    override val infoBackground = Color(0xFFcceaff)
    override val warningBackground = Color(0xFFfddfd0)
    override val negativeBackground = Color(0xFFf9caca)
    override val sponsoredBackground = Color(0xFFadddff)
    override val priceBackground = Color(0xFF000000)
}

internal object VendCalloutColors : WarpCalloutColors {
    override val background = Color(0xFFcfe8bf)
    override val border = Color(0xFF63af2c)
}

internal object VendPillColors : WarpPillColors {
    override val suggestionBackground = Color(0xFFe5e5ea)
    override val suggestionBackgroundHover = Color(0xFFc2c2c9)
    override val suggestionBackgroundActive = Color(0xFFa0a0a8)
}

internal object VendNavBarColors : WarpNavBarColors {
    override val iconSelected = Color(0xFF7a2822)
    override val borderSelected = Color(0xFF7a2822)
}

internal object VendTooltipColors : WarpTooltipColors {
    override val backgroundStatic = Color(0xFF1b1b1f)
}

private object VendSwitchColors : WarpSwitchColors {
    override val handleBackground = Color(0xFF84848f)
    override val handleBackgroundHover = Color(0xFF5c5c66)
    override val trackBorder = Color(0xFF84848f)
    override val trackBorderHover = Color(0xFF5c5c66)
}

private object VendCardColors : WarpCardColors {
    override val defaultBackground = Color(0xFFffffff)
}

private object VendPageIndicatorColors : WarpPageIndicatorColors {
    override val indicatorBackground = Color(0xFFc2c2c9)
    override val indicatorBackgroundHover = Color(0xFFa0a0a8)
}
    internal val Gray50 = Color(0xFFfafafb)
    internal val Gray100 = Color(0xFFefeff2)
    internal val Gray200 = Color(0xFFe5e5ea)
    internal val Gray300 = Color(0xFFc2c2c9)
    internal val Gray400 = Color(0xFFa0a0a8)
    internal val Gray500 = Color(0xFF84848f)
    internal val Gray600 = Color(0xFF5c5c66)
    internal val Gray700 = Color(0xFF47474f)
    internal val Gray750 = Color(0xFF333338)
    internal val Gray800 = Color(0xFF2b2b30)
    internal val Gray850 = Color(0xFF26262b)
    internal val Gray900 = Color(0xFF1b1b1f)
    internal val Gray950 = Color(0xFF121212)
    internal val Brown50 = Color(0xFFf6e6e6)
    internal val Brown100 = Color(0xFFe7c1bf)
    internal val Brown200 = Color(0xFFd69e9b)
    internal val Brown300 = Color(0xFFc27e79)
    internal val Brown400 = Color(0xFFac5f5a)
    internal val Brown500 = Color(0xFF94433d)
    internal val Brown600 = Color(0xFF7a2822)
    internal val Brown700 = Color(0xFF711e17)
    internal val Brown750 = Color(0xFF69150f)
    internal val Brown800 = Color(0xFF5a0d08)
    internal val Brown850 = Color(0xFF4c0702)
    internal val Brown900 = Color(0xFF3c0300)
    internal val Brown950 = Color(0xFF2a0100)
    internal val Forest50 = Color(0xFFebfaed)
    internal val Forest100 = Color(0xFFc7ebcf)
    internal val Forest200 = Color(0xFFa1daac)
    internal val Forest300 = Color(0xFF7bc989)
    internal val Forest400 = Color(0xFF55b866)
    internal val Forest500 = Color(0xFF2fa944)
    internal val Forest600 = Color(0xFF268736)
    internal val Forest700 = Color(0xFF1c6328)
    internal val Forest800 = Color(0xFF123f19)
    internal val Forest900 = Color(0xFF081b0b)
    internal val Blue50 = Color(0xFFe5f5ff)
    internal val Blue100 = Color(0xFFcceaff)
    internal val Blue200 = Color(0xFFadddff)
    internal val Blue300 = Color(0xFF8ac5f3)
    internal val Blue400 = Color(0xFF65abe8)
    internal val Blue500 = Color(0xFF4091dd)
    internal val Blue600 = Color(0xFF1b77d2)
    internal val Blue700 = Color(0xFF14579a)
    internal val Blue800 = Color(0xFF0d3862)
    internal val Blue900 = Color(0xFF071f37)
    internal val White = Color(0xFFffffff)
    internal val Black = Color(0xFF000000)
    internal val Transparent = Color(0xFFffffff00)
    internal val Pink50 = Color(0xFFfeecf5)
    internal val Pink100 = Color(0xFFfcc9e1)
    internal val Pink200 = Color(0xFFf9a6cf)
    internal val Pink300 = Color(0xFFf683bd)
    internal val Pink400 = Color(0xFFf360ab)
    internal val Pink500 = Color(0xFFf03c9a)
    internal val Pink600 = Color(0xFFdd2280)
    internal val Pink700 = Color(0xFFa1195d)
    internal val Pink800 = Color(0xFF65103a)
    internal val Pink900 = Color(0xFF2c071a)
    internal val Purple50 = Color(0xFFf3eefc)
    internal val Purple100 = Color(0xFFdfd1f7)
    internal val Purple200 = Color(0xFFc8b2f1)
    internal val Purple300 = Color(0xFFb193eb)
    internal val Purple400 = Color(0xFF9a74e5)
    internal val Purple500 = Color(0xFF8355df)
    internal val Purple600 = Color(0xFF6c36d9)
    internal val Purple700 = Color(0xFF4e269e)
    internal val Purple800 = Color(0xFF301663)
    internal val Purple900 = Color(0xFF14082b)
    internal val Green50 = Color(0xFFf1faeb)
    internal val Green100 = Color(0xFFcfe8bf)
    internal val Green200 = Color(0xFFabd58e)
    internal val Green300 = Color(0xFF87c25d)
    internal val Green400 = Color(0xFF63af2c)
    internal val Green500 = Color(0xFF569927)
    internal val Green600 = Color(0xFF4a8221)
    internal val Green700 = Color(0xFF376419)
    internal val Green800 = Color(0xFF264511)
    internal val Green900 = Color(0xFF142509)
    internal val Red50 = Color(0xFFfbe9e9)
    internal val Red100 = Color(0xFFf9caca)
    internal val Red200 = Color(0xFFf4a7a7)
    internal val Red300 = Color(0xFFef8484)
    internal val Red400 = Color(0xFFea6161)
    internal val Red500 = Color(0xFFe53f3e)
    internal val Red600 = Color(0xFFe22d2c)
    internal val Red700 = Color(0xFFa32020)
    internal val Red800 = Color(0xFF641414)
    internal val Red900 = Color(0xFF290a0a)
    internal val Orange50 = Color(0xFFfdf3ed)
    internal val Orange100 = Color(0xFFfddfd0)
    internal val Orange200 = Color(0xFFfacdb2)
    internal val Orange300 = Color(0xFFf7bb94)
    internal val Orange400 = Color(0xFFf4a976)
    internal val Orange500 = Color(0xFFf19758)
    internal val Orange600 = Color(0xFFee873c)
    internal val Orange700 = Color(0xFFad5f28)
    internal val Orange800 = Color(0xFF6c3916)
    internal val Orange900 = Color(0xFF2a1404)
