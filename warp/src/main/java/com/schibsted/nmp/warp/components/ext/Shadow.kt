package com.schibsted.nmp.warp.components.ext

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun Modifier.shadowSmall(shape : Shape = RoundedCornerShape(dimensions.borderRadius3)): Modifier = composed {
    this.shadow(elevation = dimensions.shadowSmall, shape = shape)
}

@Composable
fun Modifier.shadowMedium(shape : Shape = RoundedCornerShape(dimensions.borderRadius3)): Modifier = composed {
    this.shadow(elevation = dimensions.shadowMedium, shape = shape)
}

@Composable
fun Modifier.shadowLarge(shape : Shape = RoundedCornerShape(dimensions.borderRadius3)): Modifier = composed {
    this.shadow(elevation = dimensions.shadowLarge, shape = shape)
}

@Composable
fun Modifier.shadowXLarge(shape : Shape = RoundedCornerShape(dimensions.borderRadius3)): Modifier = composed {
    this.shadow(elevation = dimensions.shadowXLarge, shape = shape)
}