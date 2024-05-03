package com.schibsted.nmp.warp.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.theme.WarpTheme.shapes

/**
 * A pill in the warp design system that displays a text label and an optional dismiss icon.
 * Supports two styles: filter and suggestion.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/pill/)
 *
 *
 * @param modifier The modifier to be applied to the pill. Defaults to [Modifier].
 * @param text The text to be displayed in the pill.
 * @param onClick The callback to be invoked when the pill is clicked.
 * @param selected Whether the pill is selected. Defaults to false.
 * @param style The style of the pill. Defaults to [WarpPillStyle.Filter].
 * @param closable Whether the pill is closable. When true, the pill will display a dismiss icon. Defaults to false.
 * @param icon The icon to be displayed in the pill. If provided, the dismiss icon will not be displayed.
 * @param iconContentDescription The content description to be used for the icon.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpPill(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    selected: Boolean = false,
    style: WarpPillStyle = WarpPillStyle.Filter,
    closable: Boolean = false,
    @DrawableRes icon: Int? = null,
    iconContentDescription: String? = null
) {
    val warpPillColors: WarpPillStyleColors = when (style) {
        WarpPillStyle.Filter -> pillStyleFilter()
        WarpPillStyle.Suggestion -> pillStyleSuggestion()
    }

    val pillColors = InputChipDefaults.inputChipColors(
        containerColor = warpPillColors.background,
        selectedContainerColor = warpPillColors.backgroundActive,
        labelColor = warpPillColors.text,
        selectedLabelColor = warpPillColors.text,
        leadingIconColor = warpPillColors.icon,
        trailingIconColor = warpPillColors.icon,
        selectedLeadingIconColor = warpPillColors.icon,
        selectedTrailingIconColor = warpPillColors.icon,
    )

    InputChip(
        onClick = onClick,
        selected = selected,
        label = {
            WarpText(
                text = text,
                style = WarpTextStyle.CaptionStrong,
                color = warpPillColors.text,
                modifier = Modifier
                    .padding(vertical = dimensions.space1)
            )
        },
        shape = shapes.ellipse,
        colors = pillColors,
        border = null,
        modifier = modifier,
        trailingIcon = if (icon != null) {
            {
                Icon(
                    imageVector = ImageVector.vectorResource(icon),
                    tint = warpPillColors.icon,
                    contentDescription = iconContentDescription,
                    modifier = Modifier.size(dimensions.components.pillIcon)
                )
            }

        } else if (closable) {
            {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = warpPillColors.icon,
                    contentDescription = iconContentDescription,
                    modifier = Modifier.size(dimensions.components.pillIcon)
                )
            }
        } else null
    )
}

enum class WarpPillStyle {
    Filter,
    Suggestion
}

data class WarpPillStyleColors(
    val text: Color,
    val background: Color,
    val backgroundActive: Color,
    val icon: Color
)

@Composable
private fun pillStyleFilter(): WarpPillStyleColors {
    return WarpPillStyleColors(
        text = colors.text.inverted,
        background = colors.background.primary,
        backgroundActive = colors.background.primaryActive,
        icon = colors.icon.inverted
    )
}

@Composable
private fun pillStyleSuggestion(): WarpPillStyleColors {
    return WarpPillStyleColors(
        text = colors.text.default,
        background = colors.components.pill.suggestionBackground,
        backgroundActive = colors.components.pill.suggestionBackgroundActive,
        icon = colors.icon.default
    )
}

@Composable
@Preview
fun WarpPillPreview() {
    WarpPill(text = "Pill", onClick = { }, closable = true, style = WarpPillStyle.Filter)
}
