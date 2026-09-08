package com.schibsted.nmp.warp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.schibsted.nmp.warp.components.WarpDialogIcon
import com.schibsted.nmp.warp.theme.WarpIconResources

/**
 * Shared demo/test scenarios for WarpDialog.
 * Used by both the sample app and Paparazzi tests so copy and structure stay in sync.
 */
data class WarpDialogScenario(
    val name: String,
    val title: String,
    val body: String,
    val icon: WarpDialogIcon? = null,
    val primaryButtonText: String? = null,
    val secondaryButtonText: String? = null,
)

object WarpDialogScenarios {
    val basic = WarpDialogScenario(
        name = "Basic dialog",
        title = "Basic dialog title",
        body = "A description should be a short, complete sentence.",
        primaryButtonText = "Confirm",
        secondaryButtonText = "Cancel",
    )

    val singleButton = WarpDialogScenario(
        name = "Single button",
        title = "Single action",
        body = "Only a primary button is shown.",
        primaryButtonText = "Got it",
    )

    val longText = WarpDialogScenario(
        name = "Lots of text",
        title = "This dialog title is long enough to wrap onto multiple lines",
        body = "If a dialog needs this much explanation, it is probably the wrong container. " +
            "Dialogs interrupt the user and disable everything behind them, so they should " +
            "hold a single decision or a short, critical message.\n\n" +
            "For long-form content, guide the user to a dedicated screen or a bottom sheet. " +
            "For non-urgent information, an inline callout or a snackbar is usually kinder.",
        primaryButtonText = "Okay",
        secondaryButtonText = "Cancel",
    )

    /**
     * Plain-data scenarios. Variants that carry an icon or image ([withIcon], [withImage])
     * are composable functions instead, because Warp icon accessors require a composable
     * context (their getters are annotated `@Composable`).
     */
    val all = listOf(basic, singleButton, longText)

    @Composable
    fun withIcon(): WarpDialogScenario = WarpDialogScenario(
        name = "With Warp icon",
        title = "Basic dialog title",
        body = "A description should be a short, complete sentence.",
        icon = WarpDialogIcon.Icon(WarpIconResources.starEmpty),
        primaryButtonText = "Confirm",
        secondaryButtonText = "Cancel",
    )

    @Composable
    fun withImage(): WarpDialogScenario {
        // Warp is migrating brand logos to the WarpLogo composable, but WarpDialogIcon.Image
        // needs a Painter and there is no non-deprecated Painter accessor for brand logos yet.
        // Suppressing narrowly here — this file is a demo/test fixture, not a runtime API surface.
        @Suppress("DEPRECATION")
        val logo = WarpIconResources.finnLarge
        return WarpDialogScenario(
            name = "With image",
            title = "Welcome to FINN",
            body = "A description should be a short, complete sentence.",
            icon = WarpDialogIcon.Image(
                painter = rememberVectorPainter(image = logo.vector),
                contentDescription = logo.description,
            ),
            primaryButtonText = "Confirm",
            secondaryButtonText = "Cancel",
        )
    }
}
