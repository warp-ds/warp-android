package com.schibsted.nmp.warp.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

/**
 * Custom SnackbarVisuals for Warp that includes a type for displaying icons.
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
    val type: WarpSnackbarType = WarpSnackbarType.NEUTRAL,
) : SnackbarVisuals

enum class WarpSnackbarType {
    NEUTRAL,
    POSITIVE,
    NEGATIVE,
    WARNING,
    INFO
}
