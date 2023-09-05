package com.schibsted.nmp.warp.brands.tori

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpBadgeShapes
import com.schibsted.nmp.warp.theme.WarpComponentShapes
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpShapes

data class ToriShapes(
    val dimensions: WarpDimensions,
    override val medium: Shape = RoundedCornerShape(dimensions.borderRadius3.dp),
) : WarpShapes {
    override val components: WarpComponentShapes = ToriComponentShapes(dimensions)
}

class ToriComponentShapes(
    dimensions: WarpDimensions
) : WarpComponentShapes {
    override val badge = ToriBadgeShapes(dimensions)
}

class ToriBadgeShapes(
    dimensions: WarpDimensions
) : WarpBadgeShapes {
    override val default: Shape = RoundedCornerShape(dimensions.borderWidth2.dp)
    override val topStart: Shape = RoundedCornerShape(topStart = dimensions.borderWidth2.dp, bottomEnd = dimensions.borderWidth2.dp, topEnd = 0.dp, bottomStart = 0.dp)
    override val topEnd: Shape = RoundedCornerShape(topEnd = dimensions.borderWidth2.dp, bottomStart = dimensions.borderWidth2.dp, topStart = 0.dp, bottomEnd = 0.dp)
    override val bottomStart: Shape = RoundedCornerShape(topEnd = dimensions.borderWidth2.dp, bottomStart = dimensions.borderWidth2.dp, topStart = 0.dp, bottomEnd = 0.dp)
    override val bottomEnd: Shape = RoundedCornerShape(topStart = dimensions.borderWidth2.dp, bottomEnd = dimensions.borderWidth2.dp, topEnd = 0.dp, bottomStart = 0.dp)
}