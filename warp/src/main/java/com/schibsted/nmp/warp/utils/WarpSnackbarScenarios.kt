package com.schibsted.nmp.warp.utils

import androidx.compose.material3.SnackbarDuration

/**
 * Shared test scenarios for WarpSnackbar
 * Used by both the demo app and Paparazzi tests
 */
data class WarpSnackbarScenario(
    val name: String,
    val message: String,
    val actionLabel: String? = null,
    val withDismissAction: Boolean = true,
    val duration: SnackbarDuration = SnackbarDuration.Short
)

object WarpSnackbarScenarios {
    val shortDurationDismissable = WarpSnackbarScenario(
        name = "Short duration w/dismiss button",
        message = "This should have short duration",
    )

    val shortActionDismissable = WarpSnackbarScenario(
        name = "Short action + dismiss",
        message = "Successfully saved",
        actionLabel = "OK",
        withDismissAction = true,
        duration = SnackbarDuration.Long
    )

    val defaultDurationMultilineNoAction = WarpSnackbarScenario(
        name = "Multiline text + no action",
        message = "Something went wrong. Hang in there while we work things out."
    )

    val longDurationLongAction = WarpSnackbarScenario(
        name = "Long text + long action",
        message = "This action cannot be undone and will permanently delete all your data",
        actionLabel = "Let's do it!",
        duration = SnackbarDuration.Long
    )

    val longDurationShortAction = WarpSnackbarScenario(
        name = "Info w/short action",
        message = "This should have long duration",
        actionLabel = "View",
        duration = SnackbarDuration.Long
    )

    val indefiniteDurationLongAction = WarpSnackbarScenario(
        name = "Indefinite w/long action",
        message = "This should be indefinite",
        actionLabel = "View details",
        duration = SnackbarDuration.Indefinite
    )

    val all = listOf(
        shortDurationDismissable,
        shortActionDismissable,
        defaultDurationMultilineNoAction,
        longDurationLongAction,
        longDurationShortAction,
        indefiniteDurationLongAction
    )
}
