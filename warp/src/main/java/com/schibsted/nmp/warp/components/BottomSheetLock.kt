package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme

/**
 * Locks a [WarpBottomSheet] while an operation the user must not leave is in progress. Pass it as
 * the sheet's `lock`.
 *
 * Prefer not locking. Modal sheets are meant to be dismissible, and an operation can usually carry
 * on after the sheet closes and report its result elsewhere, e.g. in a snackbar.
 *
 * While [locked], the handle, swipe, scrim and back do nothing, and the header's close button is
 * disabled. A header the content draws itself must disable its own close button.
 * The sheet's `dismiss` still works, so it can close itself when the operation finishes. Reset
 * [locked] on error paths too. A `BackHandler` in the sheet's content still runs while locked and
 * should respect the lock.
 *
 * @param locked Set to true only while the operation is in progress, and pair it with visible
 * progress.
 */
data class BottomSheetLock @DiscouragedWarpApi constructor(val locked: Boolean)

/**
 * M3's handle slot adds a tap action, a tooltip and accessibility actions that can't be turned off,
 * so a locked sheet draws the same handle outside the slot instead.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ColumnScope.LockedDragHandle(color: Color) {
    BottomSheetDefaults.DragHandle(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .clearAndSetSemantics {},
        color = color,
    )
}

@OptIn(DiscouragedWarpApi::class)
@Preview(name = "Locked while saving", showBackground = true)
@Composable
private fun WarpBottomSheetLockedPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        header = WarpBottomSheetHeader(title = "Saving"),
        lock = BottomSheetLock(locked = true),
    ) {
        Column(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WarpSpinner()
            WarpText(
                text = "The close button is disabled and the handle inert until saving is done.",
                style = WarpTextStyle.Body,
                modifier = Modifier.padding(top = WarpTheme.dimensions.space2),
            )
        }
    }
}
