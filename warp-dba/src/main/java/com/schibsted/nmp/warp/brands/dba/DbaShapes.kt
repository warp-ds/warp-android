package com.schibsted.nmp.warp.brands.dba

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpBadgeShapes
import com.schibsted.nmp.warp.theme.WarpComponentShapes
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpShapes

internal data class DbaShapes(
    val dimensions: WarpDimensions,
    override val roundedMedium: Shape = RoundedCornerShape(dimensions.borderRadius3),
    override val ellipse: Shape = CircleShape
) : WarpShapes {
    override val components: WarpComponentShapes = DbaComponentShapes(dimensions)
}

internal class DbaComponentShapes(
    dimensions: WarpDimensions
) : WarpComponentShapes {
    override val badge = DbaBadgeShapes(dimensions)
    override val callout = RoundedCornerShape(dimensions.components.callout.cornerRadius)
}

internal class DbaBadgeShapes(
    dimensions: WarpDimensions
) : WarpBadgeShapes {
    override val default: Shape = RoundedCornerShape(dimensions.borderWidth2)
    override val topStart: Shape = RoundedCornerShape(topStart = dimensions.borderWidth2, bottomEnd = dimensions.borderWidth2, topEnd = 0.dp, bottomStart = 0.dp)
    override val topEnd: Shape = RoundedCornerShape(topEnd = dimensions.borderWidth2, bottomStart = dimensions.borderWidth2, topStart = 0.dp, bottomEnd = 0.dp)
    override val bottomEnd: Shape = RoundedCornerShape(topStart = dimensions.borderWidth2, bottomEnd = dimensions.borderWidth2, topEnd = 0.dp, bottomStart = 0.dp)
    override val bottomStart: Shape = RoundedCornerShape(topEnd = dimensions.borderWidth2, bottomStart = dimensions.borderWidth2, topStart = 0.dp, bottomEnd = 0.dp)
}