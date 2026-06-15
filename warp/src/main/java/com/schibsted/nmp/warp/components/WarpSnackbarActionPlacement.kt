package com.schibsted.nmp.warp.components

/**
 * Defines how the action button should be placed in a snackbar.
 */
sealed interface WarpSnackbarActionPlacement {
    /**
     * Automatically determines placement based on action label length.
     * Action is placed on a new line when the label is 10 or more characters.
     */
    data object Auto : WarpSnackbarActionPlacement

    /**
     * Forces the action button to be placed on the same line as the message.
     */
    data object Inline : WarpSnackbarActionPlacement

    /**
     * Forces the action button to be placed on a new line below the message.
     */
    data object NewLine : WarpSnackbarActionPlacement
}
