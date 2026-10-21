package com.schibsted.nmp.warp.components

import androidx.activity.OnBackPressedCallback
import androidx.activity.findViewTreeOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme

/**
 * A [WarpBottomSheet] that can be locked while an operation the user must not leave is in progress.
 *
 * Prefer [WarpBottomSheet]. Modal sheets are meant to be dismissible, and an operation can usually
 * carry on after the sheet closes and report its result elsewhere, e.g. in a snackbar.
 *
 * While [locked], the handle, swipe, scrim and back do nothing, and the top app bar's navigation
 * and actions are disabled. Search and tabs stay usable. `dismiss` still works, so the sheet can close itself when the operation finishes. Reset [locked] on error paths
 * too. A `BackHandler` in [content] still runs while locked and should respect the lock itself.
 *
 * @param locked Set to true only while the operation is in progress, and pair it with visible
 * progress.
 *
 * See [WarpBottomSheet] for the other parameters.
 */
@DiscouragedWarpApi
@Composable
fun WarpLockableBottomSheet(
    onDismissRequest: () -> Unit,
    locked: Boolean,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = false,
    topAppBar: TopAppBarConfiguration = TopAppBarConfiguration(title = ""),
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit,
) {
    BottomSheetImpl(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        skipPartiallyExpanded = skipPartiallyExpanded,
        locked = locked,
        topAppBar = topAppBar,
        content = content,
    )
}

/**
 * Swallows back presses while [enabled]. M3 reads `shouldDismissOnBackPress` only when the sheet
 * opens, so a sheet locked later needs this. Registered on the sheet's own window, after M3's
 * callback, so it takes priority.
 */
@Composable
internal fun BlockBack(enabled: Boolean) {
    val callback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() = Unit
        }
    }
    SideEffect { callback.isEnabled = enabled }
    val view = LocalView.current
    DisposableEffect(view) {
        view.findViewTreeOnBackPressedDispatcherOwner()?.onBackPressedDispatcher?.addCallback(callback)
        onDispose { callback.remove() }
    }
}

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
private fun WarpLockableBottomSheetPreview() {
    WarpLockableBottomSheet(
        onDismissRequest = {},
        locked = true,
        topAppBar = TopAppBarConfiguration(title = "Saving", navigation = TopAppBarNavigation.Back {}),
    ) {
        Column(
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WarpSpinner()
            WarpText(
                text = "The back button is disabled and the handle inert until saving is done.",
                style = WarpTextStyle.Body,
                modifier = Modifier.padding(top = WarpTheme.dimensions.space2),
            )
        }
    }
}
