package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme.colors

/**
 * A modal bottom sheet anchored to the bottom of the screen that dims content behind it.
 * Use for supplementary content and actions — menus, filter panels, confirmations — that
 * shouldn't take the user out of the current screen.
 *
 * Wraps Material 3's [ModalBottomSheet] with Warp tokens: [colors.surface.elevated100]
 * surface and [colors.background.subtleActive] drag handle. Corner shape falls through
 * to [BottomSheetDefaults] (28.dp top corners per M3, matching the Warp spec).
 *
 * See https://m3.material.io/components/bottom-sheets/overview.
 *
 * @param onDismiss Callback invoked when the sheet is dismissed by swipe, scrim tap, or back press.
 * @param sheetState State controlling expansion and hiding of the sheet.
 * @param content Slot for the sheet body.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpBottomSheet(
    onDismiss: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = colors.surface.elevated100,
        dragHandle = { BottomSheetDefaults.DragHandle(color = colors.background.subtleActive) },
        content = content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun WarpBottomSheetPreview() {
    WarpBottomSheet(onDismiss = { }) {
        WarpText(
            text = "Bottom sheet content goes here.",
            style = WarpTextStyle.Body,
        )
    }
}
