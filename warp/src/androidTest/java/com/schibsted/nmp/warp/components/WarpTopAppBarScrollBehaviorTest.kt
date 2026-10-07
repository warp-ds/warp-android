@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class WarpTopAppBarScrollBehaviorTest {
    @get:Rule
    val compose = createComposeRule()

    private var scope: CoroutineScope? = null

    private fun runWithBehavior(
        initialHeightOffset: Float = 0f,
        heightOffsetLimit: Float = -300f,
        flexHeightPx: Int = 100,
        snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
        block: (WarpTopAppBarScrollBehavior) -> Unit,
    ) {
        var behavior: WarpTopAppBarScrollBehavior? = null
        compose.setContent {
            scope = rememberCoroutineScope()
            val state = remember {
                TopAppBarState(
                    initialHeightOffsetLimit = heightOffsetLimit,
                    initialHeightOffset = initialHeightOffset,
                    initialContentOffset = 0f,
                )
            }
            behavior = remember(state) {
                WarpTopAppBarScrollBehavior(
                    state = state,
                    snapAnimationSpec = snapAnimationSpec,
                    flingAnimationSpec = null,
                ).also {
                    it.flexHeightPxState.intValue = flexHeightPx
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread { block(behavior!!) }
    }

    /** Ends a gesture with zero velocity and waits for any snap animation to finish. */
    private fun releaseAndSettle(behavior: WarpTopAppBarScrollBehavior) {
        compose.runOnUiThread {
            scope!!.launch { behavior.nestedScrollConnection.onPostFling(Velocity.Zero, Velocity.Zero) }
        }
        compose.waitForIdle()
    }

    private fun assertSettlesTo(
        initialHeightOffset: Float,
        expected: Float,
        snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
    ) {
        lateinit var behavior: WarpTopAppBarScrollBehavior
        runWithBehavior(
            initialHeightOffset = initialHeightOffset,
            heightOffsetLimit = -300f,
            flexHeightPx = 100,
            snapAnimationSpec = snapAnimationSpec,
        ) { behavior = it }
        releaseAndSettle(behavior)
        assertEquals(expected, behavior.state.heightOffset, 0.5f)
    }

    @Test
    fun downScroll_collapsesOffset() {
        runWithBehavior(initialHeightOffset = 0f) { b ->
            val consumed = b.nestedScrollConnection.onPreScroll(
                available = Offset(0f, -50f),
                source = NestedScrollSource.UserInput,
            )
            assertEquals(-50f, consumed.y, 0.01f)
            assertEquals(-50f, b.state.heightOffset, 0.01f)
        }
    }

    @Test
    fun downScroll_doesNotExceedHeightOffsetLimit() {
        runWithBehavior(initialHeightOffset = -290f, heightOffsetLimit = -300f) { b ->
            b.nestedScrollConnection.onPreScroll(
                available = Offset(0f, -50f),
                source = NestedScrollSource.UserInput,
            )
            assertEquals(-300f, b.state.heightOffset, 0.01f)
        }
    }

    @Test
    fun upScroll_inSearchTabsRegion_expandsImmediately() {
        // flexHeightPx = 100 → flexThreshold = -100. Offset -150 puts us in the search/tabs region.
        runWithBehavior(initialHeightOffset = -150f, heightOffsetLimit = -300f, flexHeightPx = 100) { b ->
            val consumed = b.nestedScrollConnection.onPreScroll(
                available = Offset(0f, 30f),
                source = NestedScrollSource.UserInput,
            )
            assertTrue("expected immediate expansion in search/tabs region", consumed.y > 0f)
            // Must not expand past flexThreshold (-100)
            assertTrue("offset must not cross flexThreshold", b.state.heightOffset <= -100f)
        }
    }

    @Test
    fun upScroll_inFlexRegion_doesNotExpandImmediately() {
        // Offset -50 is in the flex region (above flexThreshold of -100); expansion deferred to onPostScroll.
        runWithBehavior(initialHeightOffset = -50f, heightOffsetLimit = -300f, flexHeightPx = 100) { b ->
            val consumed = b.nestedScrollConnection.onPreScroll(
                available = Offset(0f, 30f),
                source = NestedScrollSource.UserInput,
            )
            assertEquals("flex region must not consume upward scroll", 0f, consumed.y, 0.01f)
            assertEquals("offset must be unchanged", -50f, b.state.heightOffset, 0.01f)
        }
    }

    @Test
    fun postScroll_withLeftoverUpwardScroll_expandsFlexSection() {
        // available.y > 0 means content is at top and has leftover upward scroll.
        runWithBehavior(initialHeightOffset = -20f, heightOffsetLimit = -300f, flexHeightPx = 100) { b ->
            val consumed = b.nestedScrollConnection.onPostScroll(
                consumed = Offset.Zero,
                available = Offset(0f, 30f),
                source = NestedScrollSource.UserInput,
            )
            assertTrue("flex section should expand on leftover upward scroll", consumed.y > 0f)
            assertTrue("offset should move toward 0", b.state.heightOffset > -20f)
        }
    }

    // Anchors with flexHeightPx = 100 and heightOffsetLimit = -300: 0, -100, -300.

    @Test
    fun settle_inFlexRegion_nearTop_snapsToExpanded() = assertSettlesTo(-40f, 0f)

    @Test
    fun settle_inFlexRegion_nearFlexThreshold_snapsToFlexCollapsed() = assertSettlesTo(-60f, -100f)

    @Test
    fun settle_inSearchTabsRegion_nearFlexThreshold_snapsToFlexCollapsed() = assertSettlesTo(-180f, -100f)

    @Test
    fun settle_inSearchTabsRegion_nearLimit_snapsToFullyCollapsed() = assertSettlesTo(-220f, -300f)

    @Test
    fun settle_withoutSnapSpec_leavesOffsetUnchanged() =
        assertSettlesTo(-40f, -40f, snapAnimationSpec = null)
}
