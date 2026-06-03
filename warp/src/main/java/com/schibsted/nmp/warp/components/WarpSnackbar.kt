package com.schibsted.nmp.warp.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.theme.WarpTheme

private const val ACTION_NEW_LINE_THRESHOLD = 10

/**
 * A snackbar in the Warp design system.
 * Snackbars provide brief messages about app processes at the bottom of the screen.
 * For more info, look [here](https://warp-ds.github.io/tech-docs/components/snackbar/)
 *
 * The action button is automatically placed on a new line when the action text is at least chars.
 *
 * @param snackbarData Data about the snackbar, including text and action label.
 * @param modifier Modifier for the snackbar.
 */
@Composable
fun WarpSnackbar(
    modifier: Modifier = Modifier,
    snackbarData: SnackbarData
) {
    snackbarData.visuals.validate()

    val textContentColor = WarpTheme.colors.text.invertedStatic
    val iconContentColor = WarpTheme.colors.icon.invertedStatic
    val containerColor = WarpTheme.colors.components.tooltip.backgroundStatic
    val actionOnNewLine = snackbarData.visuals.actionLabel?.let {
        it.length >= ACTION_NEW_LINE_THRESHOLD
    } ?: false

    MaterialTheme(
        typography = MaterialTheme.typography.copy(
            bodyMedium = WarpTheme.typography.caption,
            labelLarge = WarpTheme.typography.title5
        )
    ) {
        Snackbar(
            modifier = modifier,
            actionOnNewLine = actionOnNewLine,
            containerColor = containerColor,
            contentColor = textContentColor,
            actionContentColor = textContentColor,
            actionColor = textContentColor,
            dismissActionContentColor = iconContentColor,
            snackbarData = snackbarData
        )
    }
}

fun SnackbarVisuals.validate() {
    require(message.isNotBlank()) {
        "Snackbar message cannot be empty."
    }
    require(actionLabel.isNullOrEmpty() || duration != SnackbarDuration.Short) {
        "Snackbars with an action cannot have Short duration. Use Long or Indefinite instead."
    }
    require(duration == SnackbarDuration.Short || withDismissAction || !actionLabel.isNullOrEmpty()) {
        "Long and Indefinite snackbars must have either a dismiss action or an action button."
    }
}
