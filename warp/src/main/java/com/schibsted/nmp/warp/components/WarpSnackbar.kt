package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpDimensions.adaptDpToFontScale
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpIconResources
import com.schibsted.nmp.warp.theme.WarpTheme

// TODO: Threshold value is WIP
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

    val icon = warpVisuals?.type?.toIconResource()
    val shape = WarpTheme.shapes.roundedMedium
    val contentColor = WarpTheme.colors.text.invertedStatic
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
                        color = contentColor
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
                        tint = contentColor
                    )
                }
            }
        } else null,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        dismissActionContentColor = contentColor,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                WarpIcon(
                    icon = icon,
                    color = warpVisuals.type.toIconColor(),
                    size = adaptDpToFontScale(WarpTheme.dimensions.icon.default)
                )
                Spacer(modifier = Modifier.width(WarpTheme.dimensions.space15))
            }
            Text(
                text = snackbarData.visuals.message,
                style = WarpTheme.typography.body,
                color = contentColor
            )
        }
    }
}

@Composable
private fun WarpSnackbarType.toIconResource(): WarpIconResource? = when (this) {
    WarpSnackbarType.SUCCESS -> WarpIconResources.success
    WarpSnackbarType.ERROR -> WarpIconResources.error
    WarpSnackbarType.WARNING -> WarpIconResources.warning
    WarpSnackbarType.INFO -> WarpIconResources.info
    WarpSnackbarType.NEUTRAL -> null
}

@Composable
private fun WarpSnackbarType.toIconColor(): Color = when (this) {
    WarpSnackbarType.SUCCESS -> WarpTheme.colors.icon.positive
    WarpSnackbarType.ERROR -> WarpTheme.colors.icon.negative
    WarpSnackbarType.WARNING -> WarpTheme.colors.icon.warning
    WarpSnackbarType.INFO -> WarpTheme.colors.icon.info
    WarpSnackbarType.NEUTRAL -> WarpTheme.colors.icon.invertedStatic
}
