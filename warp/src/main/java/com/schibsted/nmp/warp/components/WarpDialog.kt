package com.schibsted.nmp.warp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

/**
 * A Material 3 basic dialog that displays a title, description, and up to two actions.
 *
 * Built on top of [AlertDialog] to inherit the M3 layout, spacing, scroll behavior,
 * and accessibility semantics. Restricts the input surface — strings for title and body,
 * an optional [WarpDialogIcon] above the title, and text-only labels for the
 * quiet-style action buttons — so callers cannot deviate from the design system.
 *
 * See https://m3.material.io/components/dialogs/overview.
 *
 * @param title The title of the dialog.
 * @param body The supporting text of the dialog.
 * @param onDismiss Callback invoked when the dialog is dismissed.
 * @param modifier The modifier to be applied to the dialog.
 * @param icon Optional content for the M3 icon slot above the title — either a Warp
 * icon ([WarpDialogIcon.Icon]) or an image ([WarpDialogIcon.Image]). Centers the title
 * per the M3 spec when set.
 * @param primaryButtonText Label for the trailing (primary) action.
 * @param onPrimaryButtonClick Callback for the primary action.
 * @param secondaryButtonText Label for the leading (secondary) action.
 * @param onSecondaryButtonClick Callback for the secondary action.
 * @param dismissOnClickOutside Whether the dialog is dismissed when the scrim is tapped.
 * @param dismissOnBackPress Whether the dialog is dismissed on back press.
 */
@Composable
fun WarpDialog(
    title: String,
    body: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    icon: WarpDialogIcon? = null,
    primaryButtonText: String? = null,
    onPrimaryButtonClick: (() -> Unit)? = null,
    secondaryButtonText: String? = null,
    onSecondaryButtonClick: (() -> Unit)? = null,
    dismissOnClickOutside: Boolean = true,
    dismissOnBackPress: Boolean = true,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = modifier,
        confirmButton = {
            primaryButtonText?.let {
                WarpButton(
                    text = it,
                    onClick = onPrimaryButtonClick ?: {},
                    style = WarpButtonStyle.Quiet
                )
            }
        },
        dismissButton = secondaryButtonText?.let {
            {
                WarpButton(
                    text = it,
                    onClick = onSecondaryButtonClick ?: {},
                    style = WarpButtonStyle.Quiet
                )
            }
        },
        icon = icon?.let {
            {
                when (it) {
                    is WarpDialogIcon.Icon -> WarpIcon(
                        icon = it.icon,
                        size = dimensions.icon.default,
                    )
                    is WarpDialogIcon.Image -> Image(
                        painter = it.painter,
                        contentDescription = it.contentDescription,
                        contentScale = it.contentScale,
                    )
                }
            }
        },
        title = {
            WarpText(
                text = title,
                style = WarpTextStyle.Title3,
            )
        },
        text = {
            WarpText(
                text = body,
                style = WarpTextStyle.Body,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
            )
        },
        containerColor = colors.surface.elevated100,
        iconContentColor = colors.icon.subtle,
        titleContentColor = colors.text.default,
        textContentColor = colors.text.default,
        properties = DialogProperties(
            dismissOnClickOutside = dismissOnClickOutside,
            dismissOnBackPress = dismissOnBackPress,
        ),
    )
}

/**
 * Content for the M3 icon slot above the title in a [WarpDialog]. Restricted to the two
 * shapes the design system sanctions:
 *  - [Icon] — a Warp icon rendered at the default icon size.
 *  - [Image] — an image supplied as a [Painter] (from `painterResource(...)`,
 *    `rememberVectorPainter(...)`, `BitmapPainter(...)`, etc.). Rendered internally via
 *    [androidx.compose.foundation.Image]; arbitrary composable UI cannot be smuggled in.
 */
sealed interface WarpDialogIcon {
    class Icon(val icon: WarpIconResource) : WarpDialogIcon
    class Image(
        val painter: Painter,
        val contentDescription: String?,
        val contentScale: ContentScale = ContentScale.Fit,
    ) : WarpDialogIcon
}

@Preview
@Composable
private fun WarpDialogPreview() {
    WarpDialog(
        title = "Basic dialog title",
        body = "A description should be a short, complete sentence.",
        primaryButtonText = "Confirm",
        secondaryButtonText = "Cancel",
        onPrimaryButtonClick = { },
        onSecondaryButtonClick = { },
        onDismiss = { },
    )
}
