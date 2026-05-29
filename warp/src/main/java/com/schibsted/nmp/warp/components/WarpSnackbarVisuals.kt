package com.schibsted.nmp.warp.components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import com.schibsted.nmp.warp.theme.WarpIconResource

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
    val type: WarpSnackbarType = WarpSnackbarType.Neutral(),
) : SnackbarVisuals

sealed class WarpSnackbarType {
    data object Positive : WarpSnackbarType()
    data object Negative : WarpSnackbarType()
    data object Warning : WarpSnackbarType()
    data object Info : WarpSnackbarType()
    data class Neutral(val customIcon: WarpIconResource? = null) : WarpSnackbarType()
}

