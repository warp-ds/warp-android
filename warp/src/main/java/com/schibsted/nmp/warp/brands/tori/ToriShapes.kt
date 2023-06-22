package com.schibsted.nmp.warp.brands.tori

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpShapes

data class ToriShapes(
    override val roundedCornerShape: Shape = RoundedCornerShape(8.dp),
    override val circleShape: Shape = CircleShape
) : WarpShapes
