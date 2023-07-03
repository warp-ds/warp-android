package com.schibsted.nmp.warp.brands.tori

import com.schibsted.nmp.warp.theme.WarpBoxColors
import com.schibsted.nmp.warp.theme.WarpComponentBackground
import com.schibsted.nmp.warp.theme.White

object ToriBoxColors : WarpBoxColors {
    override val neutral = WarpComponentBackground(Gray50, null)
    override val info = WarpComponentBackground(Petroleum50, null)
    override val bordered = WarpComponentBackground(White, Gray300)
}