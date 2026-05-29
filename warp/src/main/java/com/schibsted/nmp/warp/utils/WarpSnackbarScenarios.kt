package com.schibsted.nmp.warp.utils

import androidx.compose.material3.SnackbarDuration
import com.schibsted.nmp.warp.components.WarpSnackbarType

/**
 * Shared test scenarios for WarpSnackbar
 * Used by both the demo app and Paparazzi tests
 */
data class WarpSnackbarScenario(
    val name: String,
    val message: String,
    val type: WarpSnackbarType,
    val actionLabel: String? = null,
    val withDismissAction: Boolean = true,
    val duration: SnackbarDuration = SnackbarDuration.Short
)

object WarpSnackbarScenarios {
    val neutral = WarpSnackbarScenario(
        name = "Neutral w/dismiss button",
        message = "This should have short duration",
        type = WarpSnackbarType.NEUTRAL
    )

    val positive = WarpSnackbarScenario(
        name = "Success w/action to dismiss",
        message = "Successfully saved",
        type = WarpSnackbarType.POSITIVE,
        actionLabel = "OK",
        withDismissAction = false
    )

    val negativeMultiline = WarpSnackbarScenario(
        name = "Error multiline no action",
        message = "Something went wrong. Hang in there while we work things out.",
        type = WarpSnackbarType.NEGATIVE
    )

    val warningLongAction = WarpSnackbarScenario(
        name = "Warning w/long action & text",
        message = "This action cannot be undone and will permanently delete all your data",
        type = WarpSnackbarType.WARNING,
        actionLabel = "Let's do it!"
    )

    val infoShortAction = WarpSnackbarScenario(
        name = "Info w/short action",
        message = "This should have long duration",
        type = WarpSnackbarType.INFO,
        actionLabel = "View",
        duration = SnackbarDuration.Long
    )

    val infoIndefinite = WarpSnackbarScenario(
        name = "Info indefinite w/long action",
        message = "This should be indefinite",
        type = WarpSnackbarType.INFO,
        actionLabel = "View details",
        duration = SnackbarDuration.Indefinite
    )

    val all = listOf(
        neutral,
        positive,
        negativeMultiline,
        warningLongAction,
        infoShortAction,
        infoIndefinite
    )
}
