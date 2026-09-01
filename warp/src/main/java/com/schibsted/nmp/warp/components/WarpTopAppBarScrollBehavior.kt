@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDecay
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
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
import androidx.compose.ui.unit.Velocity
import kotlin.math.abs

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
    override val snapAnimationSpec: AnimationSpec<Float>?,
    override val flingAnimationSpec: DecayAnimationSpec<Float>?,
) : TopAppBarScrollBehavior {
    override val isPinned = false

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

        override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
            return settle(available.y)
        }
    }

    /**
     * Continue any residual fling into [state.heightOffset], then snap to the nearest anchor
     * (0, -flexHeightPx, or [state.heightOffsetLimit]) so the bar never rests in a partial state.
     */
    private suspend fun settle(velocity: Float): Velocity {
        var remainingVelocity = velocity
        if (flingAnimationSpec != null && abs(velocity) > 1f) {
            var lastValue = 0f
            AnimationState(initialValue = 0f, initialVelocity = velocity)
                .animateDecay(flingAnimationSpec) {
                    val delta = value - lastValue
                    val before = state.heightOffset
                    state.heightOffset =
                        (state.heightOffset + delta).coerceIn(state.heightOffsetLimit, 0f)
                    val consumed = abs(before - state.heightOffset)
                    lastValue = value
                    remainingVelocity = this.velocity
                    if (abs(delta - consumed) > 0.5f) cancelAnimation()
                }
        }
        if (snapAnimationSpec != null &&
            state.heightOffset < 0f &&
            state.heightOffset > state.heightOffsetLimit
        ) {
            val target = nearestAnchor(state.heightOffset)
            if (target != state.heightOffset) {
                AnimationState(initialValue = state.heightOffset).animateTo(
                    targetValue = target,
                    animationSpec = snapAnimationSpec,
                ) { state.heightOffset = value }
            }
        }
        return Velocity(0f, remainingVelocity)
    }

    private fun nearestAnchor(offset: Float): Float {
        val anchors = buildList {
            add(0f)
            if (flexHeightPxState.intValue > 0) add(-flexHeightPxState.intValue.toFloat())
            add(state.heightOffsetLimit)
        }.filter { it >= state.heightOffsetLimit && it <= 0f }
        return anchors.minBy { abs(it - offset) }
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
        val snapSpec = spring<Float>(stiffness = Spring.StiffnessMediumLow)
        val flingSpec = rememberSplineBasedDecay<Float>()
        remember(style, searchCollapsible, tabsCollapsible, snapSpec, flingSpec) {
            WarpTopAppBarScrollBehavior(
                state = topAppBarState,
                snapAnimationSpec = snapSpec,
                flingAnimationSpec = flingSpec,
            )
        }
    } else {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    }
}
