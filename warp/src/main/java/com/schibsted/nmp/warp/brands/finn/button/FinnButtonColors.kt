package com.schibsted.nmp.warp.brands.finn.button

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.brands.finn.Blue200
import com.schibsted.nmp.warp.brands.finn.Blue600
import com.schibsted.nmp.warp.brands.finn.Blue800
import com.schibsted.nmp.warp.brands.finn.Bluegray300
import com.schibsted.nmp.warp.brands.finn.Gray200
import com.schibsted.nmp.warp.brands.finn.Red600
import com.schibsted.nmp.warp.brands.finn.Red800
import com.schibsted.nmp.warp.brands.finn.Transparent
import com.schibsted.nmp.warp.brands.finn.White
import com.schibsted.nmp.warp.theme.WarpButtonColors
import com.schibsted.nmp.warp.theme.WarpButtonElementColors
import com.schibsted.nmp.warp.theme.WarpButtonStyleColors

data class FinnButtonStyleColors(
    override val text: Color,
    override val background: FinnButtonElementColors,
    override val border: FinnButtonElementColors?
) : WarpButtonStyleColors

data class FinnButtonElementColors(
    override val default: Color,
    override val active: Color
) : WarpButtonElementColors

data class FinnButtonColors(
    override val primary: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Blue600,
            active = Blue800
        ),
        border = null
    ),
    override val secondary: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Blue600,
        background = FinnButtonElementColors(
            default = White,
            active = Blue200
        ),
        border = FinnButtonElementColors(
            default = Bluegray300,
            active = Blue800
        )
    ),
    override val disabled: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Bluegray300,
            active = Bluegray300
        ),
        border = FinnButtonElementColors(
            default = Bluegray300,
            active = Bluegray300
        )
    ),
    override val quiet: FinnButtonStyleColors = FinnButtonStyleColors(
        text = Blue600,
        background = FinnButtonElementColors(
            default = Transparent,
            active = Gray200
        ),
        border = null
    ),
    override val negative: FinnButtonStyleColors = FinnButtonStyleColors(
        text = White,
        background = FinnButtonElementColors(
            default = Red600,
            active = Red800
        ),
        border = null
    )
) : WarpButtonColors
