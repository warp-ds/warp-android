package com.schibsted.nmp.warp.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.utils.InternalWarpApi

/**
 * Renders the [WarpBottomSheet]s in [content] already open, for
 * snapshot tests. A sheet normally animates in after its first frame, which a snapshot doesn't
 * capture, so it would come out blank.
 *
 * Sheets open as users see them: fully expanded with `skipPartiallyExpanded`, otherwise
 * half-expanded when the content is taller than about half the screen.
 */
@InternalWarpApi
@Composable
fun WarpBottomSheetSnapshotTesting(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalBottomSheetStartsOpen provides true, content = content)
}

internal val LocalBottomSheetStartsOpen = staticCompositionLocalOf { false }

/** The state `rememberModalBottomSheetState` would settle in once shown, without the animation. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun rememberOpenSheetState(skipPartiallyExpanded: Boolean): SheetState {
    val density = LocalDensity.current
    return remember(skipPartiallyExpanded, density) {
        SheetState(
            skipPartiallyExpanded = skipPartiallyExpanded,
            // M3's own thresholds, which are internal.
            positionalThreshold = { with(density) { 56.dp.toPx() } },
            velocityThreshold = { with(density) { 125.dp.toPx() } },
            initialValue = if (skipPartiallyExpanded) SheetValue.Expanded else SheetValue.PartiallyExpanded,
        )
    }
}
