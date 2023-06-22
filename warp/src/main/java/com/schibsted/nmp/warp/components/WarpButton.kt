package com.schibsted.nmp.warp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.LocalColors
import com.schibsted.nmp.warp.theme.LocalShapes
import com.schibsted.nmp.warp.theme.LocalTypography
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.shapes
import com.schibsted.nmp.warp.theme.WarpTheme.typography

@Composable
fun WarpButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean = true,
    buttonStyle: WarpButtonStyle,
    maxLines: Int = 1
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalShapes provides shapes,
        LocalTypography provides typography
    ) {
        val buttonModifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .then(modifier)

        val buttonColors = when (buttonStyle) {
            WarpButtonStyle.Primary -> {
                ButtonDefaults.buttonColors(
                    containerColor = colors.button.primary.background.default,
                    contentColor = colors.button.primary.text,
                    disabledContainerColor = colors.button.disabled.background.default,
                    disabledContentColor = colors.button.disabled.text
                )
            }

            WarpButtonStyle.Secondary -> {
                ButtonDefaults.buttonColors(
                    containerColor = colors.button.secondary.background.default,
                    contentColor = colors.button.disabled.text,
                    disabledContainerColor = colors.button.disabled.background.default,
                    disabledContentColor = colors.button.disabled.text
                )
            }
        }

        val borderColor = when (buttonStyle) {
            WarpButtonStyle.Primary -> {
                BorderStroke(2.dp, colors.button.primary.border.default)
            }

            WarpButtonStyle.Secondary -> {
                BorderStroke(2.dp, colors.button.secondary.border.default)
            }
        }

        val textColor = when (buttonStyle) {
            WarpButtonStyle.Primary -> colors.button.primary.text
            WarpButtonStyle.Secondary -> colors.button.secondary.text
        }

        val content: @Composable RowScope.() -> Unit = {
            Text(
                text = text,
                color = textColor,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .semantics(properties = { contentDescription = text }),
                fontSize = typography.paragraph.fontSize,
                fontStyle = typography.paragraph.fontStyle,
                fontWeight = typography.paragraph.fontWeight,
                fontFamily = typography.paragraph.fontFamily,
                overflow = TextOverflow.Ellipsis,
                maxLines = maxLines,
                style = typography.heading,

                )
        }

        Button(
            modifier = buttonModifier,
            onClick = onClick,
            enabled = enabled,
            shape = shapes.roundedCornerShape,
            colors = buttonColors,
            border = borderColor,
            content = content,
            contentPadding = PaddingValues(horizontal = 8.dp),
            elevation = ButtonDefaults.buttonElevation()
        )
    }
}

sealed class WarpButtonStyle {
    object Primary : WarpButtonStyle()
    object Secondary : WarpButtonStyle()
}

@Composable
@Preview
fun WarpButtonPreview() {
    WarpBrandedTheme(
        flavor = "finn",
        darkTheme = false
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            WarpButton(
                text = "Take me to NMP",
                modifier = Modifier.padding(8.dp),
                onClick = {},
                buttonStyle = WarpButtonStyle.Primary
            )
            WarpButton(
                text = "Buy a duck",
                modifier = Modifier.padding(8.dp),
                onClick = {},
                buttonStyle = WarpButtonStyle.Secondary
            )
        }
    }
}
