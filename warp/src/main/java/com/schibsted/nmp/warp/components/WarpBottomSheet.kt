package com.schibsted.nmp.warp.components

import android.content.res.Configuration
import androidx.activity.OnBackPressedCallback
import androidx.activity.findViewTreeOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.takeOrElse
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme
import kotlinx.coroutines.launch

/**
 * Values used by [WarpBottomSheet].
 */
@Stable
object WarpBottomSheetDefaults {

    /** The sheet's surface color, for anything that must match it, e.g. a sticky footer. */
    val containerColor: Color
        @Composable
        get() = WarpTheme.colors.surface.elevated100
}

/**
 * A modal bottom sheet with a [WarpTopAppBar] above [content]. Content taller than about half the
 * screen opens half-expanded first, unless [skipPartiallyExpanded] is set.
 *
 * @param onDismissRequest Called once the hide animation completes. Also passed to [content] as
 * `dismiss`, so in-sheet actions can close the sheet the same way.
 * @param modifier Applied to the sheet surface.
 * @param skipPartiallyExpanded Always open fully expanded, e.g. for forms that would be cut off.
 * @param topAppBar What the sheet's top app bar shows. The sheet always adds its own close button
 * top right, after the actions, so don't add one; a [TopAppBarNavigation.Close] is ignored. Use
 * [TopAppBarNavigation.Back] for sub-navigation: while it's shown, system back runs its action too,
 * so don't add a `BackHandler` for it. Use the title for the sheet's heading rather than a heading
 * in [content]. Collapsing styles, search and tabs collapse as [content] scrolls.
 * @param lock Locks the sheet while an operation the user must not leave is in progress; see
 * [BottomSheetLock]. Prefer not locking.
 * @param content Sheet body; receives `dismiss` (see [onDismissRequest]).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = false,
    topAppBar: TopAppBarConfiguration = TopAppBarConfiguration(title = ""),
    lock: BottomSheetLock? = null,
    content: @Composable ColumnScope.(dismiss: () -> Unit) -> Unit,
) {
    val locked = lock?.locked == true
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    val dismiss: () -> Unit = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) onDismissRequest()
        }
    }
    val scrollBehavior = with(topAppBar) {
        rememberDefaultTopAppBarScrollBehavior(style, titleCollapsible, search, tabs)
    }

    val dragHandleColor = WarpTheme.colors.background.subtleActive

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        // Caps the sheet below the status bar, so a scrim strip stays visible even with tall content.
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Top))
            .then(modifier),
        sheetState = sheetState,
        containerColor = WarpBottomSheetDefaults.containerColor,
        contentColor = WarpTheme.colors.text.default,
        contentWindowInsets = { WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom + WindowInsetsSides.Horizontal) },
        sheetGesturesEnabled = !locked,
        properties = ModalBottomSheetProperties(shouldDismissOnClickOutside = !locked),
        dragHandle = if (locked) null else { { BottomSheetDefaults.DragHandle(color = dragHandleColor) } },
    ) {
        // A locked sheet swallows back. Otherwise, while Back is shown, system back does what it
        // does, so the two can't disagree. Without Back, M3 dismisses the sheet.
        val back = topAppBar.navigation as? TopAppBarNavigation.Back
        SheetBackHandler(enabled = locked || back != null) { if (!locked) back?.onClick?.invoke() }
        if (locked) LockedDragHandle(color = dragHandleColor)
        val close = TopAppBarAction.Icon(
            icon = icons.close,
            label = stringResource(R.string.top_app_bar_close),
            onClick = dismiss,
        )
        TopAppBarImpl(
            // Every sheet closes the same way: with the sheet's own close button, top right.
            configuration = topAppBar.copy(
                navigation = topAppBar.navigation.takeUnless { it is TopAppBarNavigation.Close },
                actions = topAppBar.actions + close,
            ),
            modifier = Modifier,
            // The sheet already applies insets.
            windowInsets = WindowInsets(0),
            topBarColors = warpTopAppBarColors(containerColor = WarpBottomSheetDefaults.containerColor),
            scrollBehavior = scrollBehavior,
            enabled = !locked,
            // Shorter than a screen's bar, so the header sits close to the handle. The buttons keep
            // their full touch target.
            expandedHeight = LocalMinimumInteractiveComponentSize.current
                .takeOrElse { TopAppBarDefaults.TopAppBarExpandedHeight },
        )
        if (scrollBehavior != null) {
            Column(Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)) { content(dismiss) }
        } else {
            content(dismiss)
        }
    }
}

/**
 * Runs [onBack] on back presses while [enabled], instead of M3 dismissing the sheet. M3 reads
 * `shouldDismissOnBackPress` only when the sheet opens, so this is also how a sheet locked later
 * blocks back. Registered on the sheet's own window, after M3's callback, so it takes priority.
 */
@Composable
private fun SheetBackHandler(enabled: Boolean, onBack: () -> Unit) {
    val currentOnBack by rememberUpdatedState(onBack)
    val callback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() = currentOnBack()
        }
    }
    SideEffect { callback.isEnabled = enabled }
    val view = LocalView.current
    DisposableEffect(view) {
        view.findViewTreeOnBackPressedDispatcherOwner()?.onBackPressedDispatcher?.addCallback(callback)
        onDispose { callback.remove() }
    }
}

@Preview(name = "Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun WarpBottomSheetTitlePreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        topAppBar = TopAppBarConfiguration(title = "Sort by"),
    ) { dismiss ->
        Column(modifier = Modifier.padding(WarpTheme.dimensions.space2)) {
            WarpText(text = "Newest first", style = WarpTextStyle.Body)
            WarpText(text = "Oldest first", style = WarpTextStyle.Body)
            WarpButton(
                text = "Done",
                onClick = dismiss,
                style = WarpButtonStyle.Primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = WarpTheme.dimensions.space2),
            )
        }
    }
}

@Preview(name = "Back and text action", showBackground = true)
@Composable
private fun WarpBottomSheetBackAndActionPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        topAppBar = TopAppBarConfiguration(
            title = "Step 2 of 2",
            navigation = TopAppBarNavigation.Back {},
            actions = listOf(TopAppBarAction.Text("Reset") {}),
        ),
    ) {
        WarpText(
            text = "Content below a top app bar with a back button and a quiet text action.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}

@Preview(name = "Toggle and menu", showBackground = true)
@Composable
private fun WarpBottomSheetToggleAndMenuPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        topAppBar = TopAppBarConfiguration(
            title = "Vintage armchair",
            subtitle = "Oslo",
            actions = listOf(
                TopAppBarAction.Toggle(
                    icon = icons.heart,
                    checkedIcon = icons.heartFilled,
                    checked = true,
                    label = "Favourite",
                    onCheckedChange = {},
                ),
                TopAppBarAction.Menu(listOf(TopAppBarMenuItem("Share") {}, TopAppBarMenuItem("Report") {})),
            ),
        ),
    ) {
        WarpText(
            text = "Content below a top app bar with a close button, a toggle and a menu.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}

@Preview(name = "Destructive action", showBackground = true)
@Composable
private fun WarpBottomSheetDestructiveActionPreview() {
    WarpBottomSheet(
        onDismissRequest = {},
        topAppBar = TopAppBarConfiguration(
            title = "Edit education",
            actions = listOf(TopAppBarAction.Text("Delete", destructive = true) {}),
        ),
    ) {
        WarpText(
            text = "Content below a top app bar with a close button and a quiet destructive action.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(WarpTheme.dimensions.space2),
        )
    }
}
