package com.nmp.warp.brands.tori.button

import androidx.compose.ui.graphics.Color
import com.nmp.warp.brands.tori.Gray300
import com.nmp.warp.brands.tori.Petroleum200
import com.nmp.warp.brands.tori.Petroleum300
import com.nmp.warp.brands.tori.Petroleum600
import com.nmp.warp.brands.tori.Petroleum800
import com.nmp.warp.brands.tori.Watermelon600
import com.nmp.warp.brands.tori.Watermelon800
import com.nmp.warp.brands.tori.White
import com.nmp.warp.theme.WarpButtonColors
import com.nmp.warp.theme.WarpButtonElementColors
import com.nmp.warp.theme.WarpButtonStyleColors

data class ToriButtonColors(
    override val primary: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Watermelon600,
            active = Watermelon800
        ),
        border = ToriButtonElementColors(
            default = Watermelon600,
            active = Watermelon800
        )
    ),
    override val secondary: ToriButtonStyleColors = ToriButtonStyleColors(
        text = Petroleum600,
        background = ToriButtonElementColors(
            default = White,
            active = Petroleum200
        ),
        border = ToriButtonElementColors(
            default = Petroleum300,
            active = Petroleum800
        )
    ),
    override val disabled: ToriButtonStyleColors = ToriButtonStyleColors(
        text = White,
        background = ToriButtonElementColors(
            default = Gray300,
            active = Gray300
        ),
        border = ToriButtonElementColors(
            default = Gray300,
            active = Gray300
        )
    )
) : WarpButtonColors

data class ToriButtonStyleColors(
    override val text: Color,
    override val background: ToriButtonElementColors,
    override val border: ToriButtonElementColors
) : WarpButtonStyleColors

data class ToriButtonElementColors(
    override val default: Color,
    override val active: Color
) : WarpButtonElementColors