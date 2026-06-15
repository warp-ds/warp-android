package com.schibsted.nmp.warp.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.components.WarpSnackbarActionPlacement
import com.schibsted.nmp.warp.components.WarpSnackbarType
import com.schibsted.nmp.warp.theme.WarpIconResources

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
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val placement: WarpSnackbarActionPlacement = WarpSnackbarActionPlacement.Auto
)

object WarpSnackbarScenarios {
    val neutral = WarpSnackbarScenario(
        name = "Neutral w/dismiss button",
        message = "This should have short duration",
        type = WarpSnackbarType.Neutral()
    )

    @Composable
    fun neutralWithIcon() = WarpSnackbarScenario(
        name = "Neutral w/custom icon",
        message = "This should have short duration",
        type = WarpSnackbarType.Neutral(WarpIconResources.camera)
    )

    val positive = WarpSnackbarScenario(
        name = "Positive w/action to dismiss",
        message = "Successfully saved",
        type = WarpSnackbarType.Positive,
        actionLabel = "OK",
        withDismissAction = false,
        duration = SnackbarDuration.Long
    )

    val negativeMultiline = WarpSnackbarScenario(
        name = "Negative multiline no action",
        message = "Something went wrong. Hang in there while we work things out.",
        type = WarpSnackbarType.Negative
    )

    val warningLongAction = WarpSnackbarScenario(
        name = "Warning w/long action & text",
        message = "This action cannot be undone and will permanently delete all your data",
        type = WarpSnackbarType.Warning,
        actionLabel = "Let's do it!",
        duration = SnackbarDuration.Long
    )

    val infoShortAction = WarpSnackbarScenario(
        name = "Info w/short action",
        message = "This should have long duration",
        type = WarpSnackbarType.Info,
        actionLabel = "View",
        duration = SnackbarDuration.Long,

    )

    val infoIndefinite = WarpSnackbarScenario(
        name = "Info indefinite w/long action",
        message = "This should be indefinite",
        type = WarpSnackbarType.Info,
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
