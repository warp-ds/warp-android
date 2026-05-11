package com.schibsted.nmp.warp.components

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.schibsted.nmp.warp.theme.WarpTheme

/**
 * A snackbar in the Warp design system.
 * Snackbars provide brief messages about app processes at the bottom of the screen.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/snackbar/)
 *
 * @param snackbarData Data about the snackbar, including text and action label.
 * @param modifier Modifier for the snackbar.
 * @param actionOnNewLine Whether the action should be placed on a new line.
 * @param shape The shape of the snackbar.
 * @param containerColor The background color of the snackbar.
 * @param contentColor The color for the text content.
 * @param actionColor The text color for the action button.
 * @param actionContentColor The content color for the action.
 * @param dismissActionContentColor The color for the dismiss icon.
 */
@Composable
fun WarpSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    shape: Shape = SnackbarDefaults.shape,
    containerColor: Color = WarpTheme.colors.background.default,
    contentColor: Color = WarpTheme.colors.text.default,
    actionColor: Color = WarpTheme.colors.text.default,
    actionContentColor: Color = SnackbarDefaults.actionContentColor,
    dismissActionContentColor: Color = WarpTheme.colors.icon.default,
) {
    Snackbar(
        snackbarData = snackbarData,
        modifier = modifier,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        actionColor = actionColor,
        actionContentColor = actionContentColor,
        dismissActionContentColor = dismissActionContentColor,
    )
}
