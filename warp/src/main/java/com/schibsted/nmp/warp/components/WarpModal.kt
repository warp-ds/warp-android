package com.schibsted.nmp.warp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun WarpModal(
    title: String,
    body: String,
    onDismiss: () -> Unit,
    subTitle: String? = null,
    confirmButtonText: String? = null,
    dismissButtonText: String? = null,
    onConfirm: (() -> Unit)? = null,
    showCloseIcon: Boolean = false,
    closeIconContentDescription: String? = null,
    dismissOnClickOutside: Boolean = true,
    dismissOnBackPress: Boolean = true
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
                modifier = Modifier.padding(horizontal = dimensions.space2),
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
                            Icon(
                                imageVector = Icons.Default.Close,
                                tint = colors.icon.default,
                                contentDescription = closeIconContentDescription,
                                modifier = Modifier
                                    .size(dimensions.space3)
                                    .clickable { onDismiss() }
                            )
                        }
                    }
                    subTitle?.let {
                        WarpText(
                            text = subTitle,
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
                        dismissButtonText?.let {
                            WarpButton(
                                text = dismissButtonText,
                                onClick = onDismiss,
                                style = WarpButtonStyle.Secondary
                            )
                        }
                        confirmButtonText?.let {
                            Spacer(modifier = Modifier.width(dimensions.space2))
                            WarpButton(
                                text = confirmButtonText,
                                onClick = onConfirm ?: {},
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
        subTitle = "Subtitle",
        body = "Preview of alert dialog",
        confirmButtonText = "Confirm",
        dismissButtonText = "Cancel",
        onConfirm = { },
        onDismiss = { }
    )
}
