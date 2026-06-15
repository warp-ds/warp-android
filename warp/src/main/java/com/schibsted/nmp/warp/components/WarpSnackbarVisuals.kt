package com.schibsted.nmp.warp.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

/**
 * Warp-specific SnackbarVisuals that includes a type for displaying icons.
 * Validates configuration at creation time.
 *
 * @throws IllegalArgumentException if validation fails
 */
data class WarpSnackbarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = true,
    override val duration: SnackbarDuration = if (actionLabel.isNullOrEmpty()) {
        SnackbarDuration.Short
    } else {
        SnackbarDuration.Long
    },
    val type: WarpSnackbarType = WarpSnackbarType.Neutral(),
) : SnackbarVisuals {
    init {
        require(message.isNotBlank()) {
            "Snackbar message cannot be empty."
        }
        require(actionLabel.isNullOrEmpty() || duration != SnackbarDuration.Short) {
            "Snackbars with an action cannot have Short duration. Use Long or Indefinite instead."
        }
        require(duration != SnackbarDuration.Indefinite || withDismissAction || !actionLabel.isNullOrEmpty()) {
            "Indefinite snackbars must have either a dismiss action or an action button."
        }
    }
}
