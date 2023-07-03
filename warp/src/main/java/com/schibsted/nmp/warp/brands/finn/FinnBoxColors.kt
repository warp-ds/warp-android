package com.schibsted.nmp.warp.brands.finn

import com.schibsted.nmp.warp.theme.WarpBoxColors
import com.schibsted.nmp.warp.theme.WarpComponentBackground
import com.schibsted.nmp.warp.theme.White

object FinnBoxColors : WarpBoxColors {
    override val neutral = WarpComponentBackground(Bluegray50, null)
    override val info = WarpComponentBackground(Aqua50, null)
    override val bordered = WarpComponentBackground(White, Bluegray300)
}