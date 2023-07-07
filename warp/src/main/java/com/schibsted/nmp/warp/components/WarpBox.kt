package com.schibsted.nmp.warp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes

@Composable
fun WarpBox(
    modifier: Modifier = Modifier,
    boxStyle: WarpBoxStyle,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalDimensions provides dimensions,
        LocalShapes provides shapes
    ) {

        val style = when (boxStyle) {
            WarpBoxStyle.NEUTRAL -> colors.components.box.neutral
            WarpBoxStyle.INFO -> colors.components.box.info
            WarpBoxStyle.BORDERED -> colors.components.box.bordered
        }

        Surface(
            modifier = modifier,
            shape = shapes.medium,
            border = style.border?.let { BorderStroke(width = dimensions.borderWidth2.dp, color = it) },
            color = style.background,
            content = content
        )
    }
}

enum class WarpBoxStyle {
    NEUTRAL,
    INFO,
    BORDERED
}

@Preview
@Composable
fun BoxPreview() {
    WarpBox(modifier = Modifier.padding(16.dp).fillMaxWidth(), boxStyle = WarpBoxStyle.NEUTRAL) {
        Text("This is the neutral style")
    }
}