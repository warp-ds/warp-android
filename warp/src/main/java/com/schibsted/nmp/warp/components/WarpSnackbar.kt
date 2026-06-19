package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpDimensions.adaptDpToFontScale
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpIconResources
import com.schibsted.nmp.warp.theme.WarpTheme

private const val ACTION_NEW_LINE_THRESHOLD = 10

/**
 * A snackbar in the Warp design system.
 * Snackbars provide brief messages about app processes at the bottom of the screen.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/snackbar/)
 *
 * @param snackbarData Data about the snackbar, including text and action label.
 *   Use WarpSnackbarVisuals to configure the message, type, actions, and placement.
 * @param modifier Modifier for the snackbar.
 */
@Composable
fun WarpSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier
) {
    val warpVisuals = snackbarData.visuals.validate()

    val iconSpec = warpVisuals.type.toSnackbarIconSpec()
    val shape = WarpTheme.shapes.roundedMedium
    val textContentColor = WarpTheme.colors.text.invertedStatic
    val iconContentColor = WarpTheme.colors.icon.invertedStatic
    val containerColor = WarpTheme.colors.components.tooltip.backgroundStatic
    val computedActionOnNewLine = when (warpVisuals.actionPlacement) {
        WarpSnackbarActionPlacement.Auto -> snackbarData.visuals.actionLabel?.let {
            it.length >= ACTION_NEW_LINE_THRESHOLD
        } ?: false
        WarpSnackbarActionPlacement.Inline -> false
        WarpSnackbarActionPlacement.NewLine -> true
    }

    Snackbar(
        modifier = modifier.padding(WarpTheme.dimensions.space05),
        action = snackbarData.visuals.actionLabel?.let { label ->
            @Composable {
                TextButton(
                    onClick = { snackbarData.performAction() }
                ) {
                    Text(
                        text = label,
                        style = WarpTheme.typography.title5,
                        color = textContentColor
                    )
                }
            }
        },
        dismissAction = if (snackbarData.visuals.withDismissAction) {
            @Composable {
                IconButton(
                    onClick = { snackbarData.dismiss() }
                ) {
                    Icon(
                        imageVector = WarpIconResources.close.vector,
                        contentDescription = stringResource(R.string.close),
                        tint = iconContentColor,
                        modifier = Modifier.size(adaptDpToFontScale(WarpTheme.dimensions.icon.default))
                    )
                }
            }
        } else null,
        actionOnNewLine = computedActionOnNewLine,
        shape = shape,
        containerColor = containerColor,
        contentColor = textContentColor,
        dismissActionContentColor = iconContentColor,
    ) {
        Row(
            modifier = Modifier.padding(
                // Padding is needed, to somewhat adjust offsets caused by component internals.
                // Noticeable when displaying multiple lines of text.
                vertical = if (computedActionOnNewLine) {
                    0.dp
                } else {
                    WarpTheme.dimensions.space15 / LocalDensity.current.fontScale
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconSpec != null) {
                // Given the constraint of not being able to programmatically tint vector paths
                // individually, we're layering Icon composables in a Box instead.
                Box(
                    modifier = Modifier.size(adaptDpToFontScale(WarpTheme.dimensions.icon.default)),
                    contentAlignment = Alignment.Center
                ) {
                    WarpIcon(
                        modifier = Modifier.clearAndSetSemantics { },
                        icon = iconSpec.iconBackgroundResource,
                        color = iconSpec.iconBackgroundColor,
                        size = adaptDpToFontScale(WarpTheme.dimensions.icon.default)
                    )
                    iconSpec.iconForegroundResource?.let { foregroundRes ->
                        Icon(
                            imageVector = ImageVector.vectorResource(id = foregroundRes),
                            contentDescription = iconSpec.iconBackgroundResource.description,
                            tint = iconSpec.iconForegroundColor ?: iconContentColor,
                            modifier = Modifier.size(adaptDpToFontScale(WarpTheme.dimensions.icon.default))
                        )
                    }
                }
                Spacer(modifier = Modifier.width(WarpTheme.dimensions.space15))
            }
            Text(
                text = snackbarData.visuals.message,
                style = WarpTheme.typography.caption,
                color = textContentColor
            )
        }
    }
}

@Composable
private fun WarpSnackbarType.toSnackbarIconSpec(): WarpSnackbarIconSpec? {
    val iconForegroundColor = WarpTheme.colors.icon.invertedStatic
    return when (this) {
        is WarpSnackbarType.Positive -> {
            WarpSnackbarIconSpec(
                iconBackgroundResource = WarpIconResources.successFilled,
                iconBackgroundColor = WarpTheme.colors.icon.positive,
                iconForegroundResource = R.drawable.warp_success_checkmark_foreground,
                iconForegroundColor = iconForegroundColor
            )
        }

        is WarpSnackbarType.Negative -> {
            WarpSnackbarIconSpec(
                iconBackgroundResource = WarpIconResources.errorFilled,
                iconBackgroundColor = WarpTheme.colors.icon.negative,
                iconForegroundResource = R.drawable.warp_error_exclamation_foreground,
                iconForegroundColor = iconForegroundColor
            )
        }

        is WarpSnackbarType.Warning -> {
            WarpSnackbarIconSpec(
                iconBackgroundResource = WarpIconResources.warningFilled,
                iconBackgroundColor = WarpTheme.colors.icon.warning,
                iconForegroundResource = R.drawable.warp_warning_exclamation_foreground,
                iconForegroundColor = iconForegroundColor
            )
        }

        is WarpSnackbarType.Info -> {
            WarpSnackbarIconSpec(
                iconBackgroundResource = WarpIconResources.infoFilled,
                iconBackgroundColor = WarpTheme.colors.icon.info,
                iconForegroundResource = R.drawable.warp_info_letter_foreground,
                iconForegroundColor = iconForegroundColor
            )
        }

        is WarpSnackbarType.Neutral -> {
            customIcon?.let {
                WarpSnackbarIconSpec(
                    iconBackgroundResource = it,
                    iconBackgroundColor = WarpTheme.colors.icon.invertedStatic
                )
            }
        }
    }
}

/**
 * Validates that WarpSnackbarVisuals is being used and returns it with the correct type.
 * The actual validation rules are enforced in WarpSnackbarVisuals.init block.
 *
 * @return The WarpSnackbarVisuals instance (already validated in its init block)
 * @throws IllegalArgumentException if not using WarpSnackbarVisuals
 */
fun SnackbarVisuals.validate(): WarpSnackbarVisuals {
    require(this is WarpSnackbarVisuals) {
        "WarpSnackbar requires WarpSnackbarVisuals. Use WarpSnackbarVisuals instead of plain SnackbarVisuals."
    }
    return this
}

// Workaround for tinting multiple SVG paths in a "single" icon
private data class WarpSnackbarIconSpec(
    val iconBackgroundResource: WarpIconResource,
    val iconBackgroundColor: Color,
    val iconForegroundResource: Int? = null,
    val iconForegroundColor: Color? = null
)
