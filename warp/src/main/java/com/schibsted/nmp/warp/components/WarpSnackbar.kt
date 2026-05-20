package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.theme.WarpTheme.typography

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
 * @param actionColor The text color for the action button.
 */
@Composable
fun WarpSnackbar(
    modifier: Modifier = Modifier,
    snackbarData: SnackbarData,
    actionColor: Color = WarpTheme.colors.text.default,
) {
    val warpVisuals = snackbarData.visuals as? WarpSnackbarVisuals

    val icon = warpVisuals?.type?.toIcon()
    val shape = SnackbarDefaults.shape
    val contentColor = WarpTheme.colors.text.default
    val containerColor = WarpTheme.colors.background.default
    val actionOnNewLine = snackbarData.visuals.actionLabel?.let {
        it.length > ACTION_NEW_LINE_THRESHOLD
    } ?: false

    if (icon != null) {
        Snackbar(
            modifier = modifier,
            action = snackbarData.visuals.actionLabel?.let { label ->
                {
                    TextButton(
                        onClick = { snackbarData.performAction() }
                    ) {
                        Text(
                            text = label,
                            style = typography.bodyStrong,
                            color = actionColor
                        )
                    }
                }
            },
            dismissAction = if (snackbarData.visuals.withDismissAction) {
                {
                    IconButton(
                        onClick = { snackbarData.dismiss() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Dismiss",
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
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = contentColor
                )
                Spacer(modifier = Modifier.width(WarpTheme.dimensions.space2))
                Text(
                    text = snackbarData.visuals.message,
                    style = typography.body,
                    color = contentColor
                )
            }
        }
    } else {
        Snackbar(
            snackbarData = snackbarData,
            modifier = modifier,
            actionOnNewLine = actionOnNewLine,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            actionColor = actionColor,
            dismissActionContentColor = contentColor,
        )
    }
}

private fun WarpSnackbarType.toIcon(): ImageVector? = when (this) {
    WarpSnackbarType.SUCCESS -> Icons.Default.CheckCircle
    WarpSnackbarType.ERROR -> Icons.Default.Warning
    WarpSnackbarType.WARNING -> Icons.Default.Warning
    WarpSnackbarType.INFO -> Icons.Default.Info
    WarpSnackbarType.NEUTRAL -> null
}
