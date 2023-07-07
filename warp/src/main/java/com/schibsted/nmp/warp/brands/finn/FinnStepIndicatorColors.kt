package com.schibsted.nmp.warp.brands.finn

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.WarpStepIndicatorColors
import com.schibsted.nmp.warp.theme.White

object FinnStepIndicatorColors : WarpStepIndicatorColors {
    override val completed: Color = Blue600
    override val unvisited: Color = Bluegray300
    override val completedIconTint: Color = White
}