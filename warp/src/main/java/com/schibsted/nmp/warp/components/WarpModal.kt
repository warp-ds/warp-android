package com.schibsted.nmp.warp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions


/**
 * A modal dialog that displays a title, subtitle, body, and optional buttons.
 * @param modifier The modifier to be applied to the dialog.
 * @param title The title of the dialog.
 * @param body The body of the dialog.
 * @param onDismiss The callback to be invoked when the dialog is dismissed.
 * @param dismissOnClickOutside Whether the dialog should be dismissed when clicked outside of it.
 * @param dismissOnBackPress Whether the dialog should be dismissed when the back button is pressed.
 * @param subtitle The subtitle of the dialog.
 * @param primaryButtonText The text of the primary button.
 * @param onPrimaryButtonClick The callback to be invoked when the primary button is clicked.
 * @param secondaryButtonText The text of the secondary button.
 * @param onSecondaryButtonClick The callback to be invoked when the secondary button is clicked.
 * @param showCloseIcon Whether to show a close icon in the top right corner of the dialog.
 */
@Composable
fun WarpModal(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    onDismiss: () -> Unit,
    dismissOnClickOutside: Boolean = true,
    dismissOnBackPress: Boolean = true,
    subtitle: String? = null,
    primaryButtonText: String? = null,
    onPrimaryButtonClick: (() -> Unit)? = null,
    secondaryButtonText: String? = null,
    onSecondaryButtonClick: (() -> Unit)? = null,
    showCloseIcon: Boolean = false
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = dismissOnClickOutside,
            dismissOnBackPress = dismissOnBackPress
        ),
        content = {
            Card(
                modifier = Modifier
                    .padding(horizontal = dimensions.space2)
                    .then(modifier),
                shape = RoundedCornerShape(dimensions.borderRadius3),
                colors = CardDefaults.cardColors(
                    containerColor = colors.surface.elevated100,
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensions.space2)
                        .verticalScroll(state = rememberScrollState()),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = dimensions.space2
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        WarpText(
                            text = title,
                            style = WarpTextStyle.Title3,
                            modifier = Modifier
                                .weight(1f)
                        )
                        if (showCloseIcon) {
                            WarpIcon(
                                icon = icons.close,
                                modifier = Modifier
                                    .clickable { onDismiss() }
                            )
                        }
                    }
                    subtitle?.let {
                        WarpText(
                            text = subtitle,
                            style = WarpTextStyle.Title4
                        )
                    }
                    WarpText(
                        modifier = Modifier.fillMaxWidth(),
                        text = body,
                        style = WarpTextStyle.Body
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = dimensions.space3
                            ),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        secondaryButtonText?.let {
                            WarpButton(
                                text = secondaryButtonText,
                                onClick = onSecondaryButtonClick ?: {},
                                style = WarpButtonStyle.Secondary
                            )
                        }
                        primaryButtonText?.let {
                            Spacer(modifier = Modifier.width(dimensions.space2))
                            WarpButton(
                                text = primaryButtonText,
                                onClick = onPrimaryButtonClick ?: {},
                                style = WarpButtonStyle.Primary
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
@Preview
fun WarpAlertDialogPreview() {
    WarpModal(
        title = "Alert dialog",
        subtitle = "Subtitle",
        body = "Preview of alert dialog",
        primaryButtonText = "Confirm",
        secondaryButtonText = "Cancel",
        onPrimaryButtonClick = { },
        onSecondaryButtonClick = { },
        onDismiss = { }
    )
}
