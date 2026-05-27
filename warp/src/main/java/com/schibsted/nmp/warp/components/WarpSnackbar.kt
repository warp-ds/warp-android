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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.clearAndSetSemantics
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
 * The action button is automatically placed on a new line when the action text
 * exceeds a certain character length threshold.
 *
 * @param snackbarData Data about the snackbar, including text and action label.
 * @param modifier Modifier for the snackbar.
 */
@Composable
fun WarpSnackbar(
    modifier: Modifier = Modifier,
    snackbarData: SnackbarData
) {
    val warpVisuals = snackbarData.visuals as? WarpSnackbarVisuals

    val iconSpec = warpVisuals?.type?.toSnackbarIconSpec()
    val shape = WarpTheme.shapes.roundedMedium
    val textContentColor = WarpTheme.colors.text.invertedStatic
    val iconContentColor = WarpTheme.colors.icon.invertedStatic
    val containerColor = WarpTheme.colors.components.tooltip.backgroundStatic
    val actionOnNewLine = snackbarData.visuals.actionLabel?.let {
        it.length > ACTION_NEW_LINE_THRESHOLD
    } ?: false

    Snackbar(
        modifier = modifier.padding(WarpTheme.dimensions.space05),
        action = snackbarData.visuals.actionLabel?.let { label ->
            @Composable {
                TextButton(
                    onClick = { snackbarData.performAction() }
                ) {
                    Text(
                        text = label,
                        style = WarpTheme.typography.bodyStrong,
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
                        tint = iconContentColor
                    )
                }
            }
        } else null,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        containerColor = containerColor,
        contentColor = textContentColor,
        dismissActionContentColor = iconContentColor,
    ) {1
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconSpec != null) {
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
                    Icon(
                        imageVector = ImageVector.vectorResource(id = iconSpec.iconForegroundResource),
                        contentDescription = iconSpec.iconBackgroundResource.description,
                        tint = iconSpec.iconForegroundColor,
                        modifier = Modifier.size(adaptDpToFontScale(WarpTheme.dimensions.icon.default))
                    )
                }
                Spacer(modifier = Modifier.width(WarpTheme.dimensions.space15))
            }
            Text(
                text = snackbarData.visuals.message,
                style = WarpTheme.typography.body,
                color = textContentColor
            )
        }
    }
}

@Composable
private fun WarpSnackbarType.toSnackbarIconSpec(): WarpSnackbarIconSpec? {
    val iconForegroundColor = WarpTheme.colors.icon.invertedStatic
    return when (this) {
        WarpSnackbarType.SUCCESS -> {
            WarpSnackbarIconSpec(
                iconBackgroundResource = WarpIconResources.successFilled,
                iconBackgroundColor = WarpTheme.colors.icon.positive,
                iconForegroundResource = R.drawable.warp_success_checkmark_foreground,
                iconForegroundColor = iconForegroundColor
            )
        }
        WarpSnackbarType.ERROR -> {
            WarpSnackbarIconSpec(
                iconBackgroundResource = WarpIconResources.errorFilled,
                iconBackgroundColor = WarpTheme.colors.icon.negative,
                iconForegroundResource = R.drawable.warp_error_exclamation_foreground,
                iconForegroundColor = iconForegroundColor
            )
        }
        WarpSnackbarType.WARNING -> {
            WarpSnackbarIconSpec(
                iconBackgroundResource = WarpIconResources.warningFilled,
                iconBackgroundColor = WarpTheme.colors.icon.warning,
                iconForegroundResource = R.drawable.warp_warning_exclamation_foreground,
                iconForegroundColor = iconForegroundColor
            )
        }
        WarpSnackbarType.INFO -> {
            WarpSnackbarIconSpec(
                iconBackgroundResource = WarpIconResources.infoFilled,
                iconBackgroundColor = WarpTheme.colors.icon.info,
                iconForegroundResource = R.drawable.warp_info_letter_foreground,
                iconForegroundColor = iconForegroundColor
            )
        }
        WarpSnackbarType.NEUTRAL -> {
            null
        }
    }
}

// Workaround for tinting multiple SVG paths in a "single" icon
private data class WarpSnackbarIconSpec(
    val iconBackgroundResource: WarpIconResource,
    val iconBackgroundColor: Color,
    val iconForegroundResource: Int,
    val iconForegroundColor: Color
)
