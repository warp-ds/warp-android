package com.schibsted.nmp.warp.brands.tori

import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.WarpStepIndicatorColors
import com.schibsted.nmp.warp.theme.White

object ToriStepIndicatorColors : WarpStepIndicatorColors {
    override val completed: Color = Petroleum600
    override val unvisited: Color = Gray300
    override val completedIconTint: Color = White
}