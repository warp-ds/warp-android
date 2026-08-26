package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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
 * A Material 3 full-screen dialog that covers the entire screen with a top navigation
 * bar (close icon, title, optional confirmation action) and a content area.
 *
 * Use for flows that require a series of tasks — for example creating a calendar entry
 * with title, date, location, and time. Because it fills the screen, a full-screen
 * dialog is the only dialog over which other dialogs can appear.
 *
 * Per the M3 spec, the top bar supports at most one trailing action (typically a confirm
 * or save affordance). The action is rendered as a quiet-style text button — callers pass
 * only its label and click callback.
 *
 * See https://m3.material.io/components/dialogs/guidelines.
 *
 * @param title The title shown next to the close icon.
 * @param onDismiss Callback invoked when the dialog is dismissed (close icon or back press).
 * @param modifier The modifier to be applied to the dialog surface.
 * @param dismissOnBackPress Whether the dialog is dismissed on back press.
 * @param actionText Label for the trailing confirmation action. When null, no action is shown.
 * @param onActionClick Callback for the trailing action. Required when [actionText] is set.
 * @param content Slot for the body of the dialog. Rendered inside a column with 24.dp
 * content padding.
 */
@Composable
fun WarpFullScreenDialog(
    title: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    dismissOnBackPress: Boolean = true,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = false,
        ),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
            color = colors.surface.elevated100,
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                FullScreenDialogTopBar(
                    title = title,
                    onDismiss = onDismiss,
                    actionText = actionText,
                    onActionClick = onActionClick,
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensions.space3),
                    content = content,
                )
            }
        }
    }
}

@Composable
private fun FullScreenDialogTopBar(
    title: String,
    onDismiss: () -> Unit,
    actionText: String?,
    onActionClick: (() -> Unit)?,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.space05, vertical = dimensions.space1),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onDismiss) {
            WarpIcon(icon = icons.close)
        }
        WarpText(
            text = title,
            style = WarpTextStyle.Title3,
            modifier = Modifier
                .weight(1f)
                .padding(start = dimensions.space05),
            maxLines = 1,
        )
        actionText?.let {
            WarpButton(
                text = it,
                onClick = onActionClick ?: {},
                style = WarpButtonStyle.Quiet,
            )
        }
    }
}

@Preview
@Composable
private fun WarpFullScreenDialogPreview() {
    WarpFullScreenDialog(
        title = "Full-screen dialog title",
        onDismiss = { },
        actionText = "Save",
        onActionClick = { },
    ) {
        WarpText(
            text = "Full-screen dialog content goes here.",
            style = WarpTextStyle.Body,
        )
    }
}
