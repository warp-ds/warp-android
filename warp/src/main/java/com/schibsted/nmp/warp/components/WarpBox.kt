package com.schibsted.nmp.warp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalDimensions
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider

/**
 * A component for separating content areas on a screen.
 * For more information, look [here](https://warp-ds.github.io/tech-docs/components/box/)
 * @param modifier Modifier applied to the component
 * @param boxStyle The style that will be applied to the component
 * @param content The content that will be rendered inside the box
 */
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

/**
 * An enum representing the different styles of the WarpBox component
 */
enum class WarpBoxStyle {
    NEUTRAL,
    INFO,
    BORDERED
}

@Preview
@Composable
fun BoxPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String,
) {
    WarpBrandedTheme(
        flavor = flavor,
        darkTheme = isSystemInDarkTheme()
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            WarpBox(
                modifier = Modifier
                    .padding(8.dp),
                boxStyle = WarpBoxStyle.NEUTRAL
            ) {
                Text(modifier = Modifier.padding(16.dp).fillMaxWidth(), text = "This is the neutral style")
            }
            WarpBox(
                modifier = Modifier
                    .padding(8.dp),
                boxStyle = WarpBoxStyle.INFO
            ) {
                Text(modifier = Modifier.padding(16.dp).fillMaxWidth(), text = "This is the info style")
            }
            WarpBox(
                modifier = Modifier
                    .padding(8.dp),
                boxStyle = WarpBoxStyle.BORDERED
            ) {
                Text(modifier = Modifier.padding(16.dp).fillMaxWidth(), text = "This is the bordered style")
            }

        }
    }
}