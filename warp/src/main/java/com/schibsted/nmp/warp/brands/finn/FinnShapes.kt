package com.schibsted.nmp.warp.brands.finn

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpShapes

data class FinnShapes(
    val dimensions: WarpDimensions,
    override val medium: Shape = RoundedCornerShape(dimensions.borderRadius3.dp),
) : WarpShapes
