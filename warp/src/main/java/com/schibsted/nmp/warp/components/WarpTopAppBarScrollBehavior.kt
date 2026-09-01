@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource

/**
 * A scroll behavior for [WarpTopAppBar] in [WarpAppBarStyle.MediumFlexible] mode that applies
 * different expand semantics to different collapsible sections:
 *
 * - **Flex title section** (large title + subtitle): only re-expands when the user scrolls
 *   all the way back to the very top (exit-until-collapsed semantics).
 * - **Search / tab sections**: re-expand immediately on any upward scroll, even mid-content
 *   (enter-always semantics).
 *
 * Create via [rememberWarpTopAppBarScrollBehavior] and wire it to both your Scaffold and
 * [WarpTopAppBar] the same way you would use [TopAppBarDefaults.exitUntilCollapsedScrollBehavior]:
 *
 * ```kotlin
 * val scrollBehavior = rememberWarpTopAppBarScrollBehavior(
 *     style = WarpAppBarStyle.MediumFlexible,
 *     searchCollapsible = true,
 * )
 * WarpScaffold(
 *     modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
 *     topBar = {
 *         WarpTopAppBar(
 *             ...,
 *             style = WarpAppBarStyle.MediumFlexible,
 *             scrollBehavior = scrollBehavior,
 *             searchConfig = SearchConfiguration(..., collapsible = true),
 *         )
 *     }
 * )
 * ```
 */
@Stable
class WarpTopAppBarScrollBehavior internal constructor(
    override val state: TopAppBarState,
) : TopAppBarScrollBehavior {
    override val isPinned = false
    override val snapAnimationSpec: AnimationSpec<Float>? = null
    override val flingAnimationSpec: DecayAnimationSpec<Float>? = null

    // Written by WarpTopAppBar once the flex expanded section height is measured.
    // Before measurement (value = 0) the threshold is treated as unreachable so all
    // upward scroll defers to onPostScroll (safe exit-until-collapsed fallback).
    internal val flexHeightPxState = mutableIntStateOf(0)

    override val nestedScrollConnection: NestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            if (available.y < 0f) {
                // Scrolling down — collapse, consume regardless of region
                val old = state.heightOffset
                state.heightOffset =
                    (state.heightOffset + available.y).coerceAtLeast(state.heightOffsetLimit)
                return Offset(0f, state.heightOffset - old)
            }

            // Scrolling up — only expand immediately if we are in the search/tabs region
            // (i.e. the flex section is already fully collapsed).
            // Use NEGATIVE_INFINITY when unmeasured so the enterAlways branch never fires.
            val flexThreshold = if (flexHeightPxState.intValue > 0) {
                -flexHeightPxState.intValue.toFloat()
            } else {
                Float.NEGATIVE_INFINITY
            }
            return if (state.heightOffset <= flexThreshold) {
                // Search/tabs region — expand immediately (enter-always)
                val old = state.heightOffset
                state.heightOffset =
                    (state.heightOffset + available.y).coerceAtMost(flexThreshold)
                Offset(0f, state.heightOffset - old)
            } else {
                // Flex region — let content scroll first; expand via onPostScroll at the top
                Offset.Zero
            }
        }

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource,
        ): Offset {
            state.contentOffset += consumed.y
            // available.y > 0 means the content is at the top and has leftover upward scroll.
            // This is when the flex title section should expand (exit-until-collapsed semantics).
            if (available.y > 0f) {
                val old = state.heightOffset
                state.heightOffset = (state.heightOffset + available.y).coerceAtMost(0f)
                return Offset(0f, state.heightOffset - old)
            }
            return Offset.Zero
        }
    }
}

/**
 * Creates and remembers a [TopAppBarScrollBehavior] appropriate for the given [WarpTopAppBar]
 * configuration.
 *
 * When [style] is [WarpAppBarStyle.MediumFlexible] and either [searchCollapsible] or
 * [tabsCollapsible] is true, this returns a [WarpTopAppBarScrollBehavior] that gives the search
 * and tab sections enter-always expand behavior while the flex title section uses
 * exit-until-collapsed. In all other cases it returns a standard
 * [TopAppBarDefaults.exitUntilCollapsedScrollBehavior].
 */
@Composable
fun rememberWarpTopAppBarScrollBehavior(
    style: WarpAppBarStyle = WarpAppBarStyle.Default,
    searchCollapsible: Boolean = false,
    tabsCollapsible: Boolean = false,
): TopAppBarScrollBehavior {
    val useHybrid = style is WarpAppBarStyle.MediumFlexible && (searchCollapsible || tabsCollapsible)
    return if (useHybrid) {
        val topAppBarState = rememberTopAppBarState()
        remember(style, searchCollapsible, tabsCollapsible) {
            WarpTopAppBarScrollBehavior(state = topAppBarState)
        }
    } else {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    }
}
