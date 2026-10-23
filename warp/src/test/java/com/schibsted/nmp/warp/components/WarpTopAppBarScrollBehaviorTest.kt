@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Plain JVM tests for [WarpTopAppBarScrollBehavior]'s nested scroll math. No composition is
 * needed: the connection only reads and writes [TopAppBarState], and snap animations run
 * against [TestFrameClock].
 */
class WarpTopAppBarScrollBehaviorTest {

    /** Advances one 16ms frame per call so animations run to completion without real time. */
    private class TestFrameClock : MonotonicFrameClock {
        private var frameTimeNanos = 0L
        override suspend fun <R> withFrameNanos(onFrame: (frameTimeNanos: Long) -> R): R {
            frameTimeNanos += 16_000_000L
            return onFrame(frameTimeNanos)
        }
    }

    private fun behavior(
        initialHeightOffset: Float = 0f,
        heightOffsetLimit: Float = -300f,
        flexHeightPx: Int = 100,
        snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
        canScroll: () -> Boolean = { true },
    ) = WarpTopAppBarScrollBehavior(
        state = TopAppBarState(
            initialHeightOffsetLimit = heightOffsetLimit,
            initialHeightOffset = initialHeightOffset,
            initialContentOffset = 0f,
        ),
        snapAnimationSpec = snapAnimationSpec,
        flingAnimationSpec = null,
        canScroll = canScroll,
    ).also { it.flexHeightPxState.intValue = flexHeightPx }

    /** Ends a gesture with zero velocity and runs any snap animation to completion. */
    private fun WarpTopAppBarScrollBehavior.releaseAndSettle() = runBlocking(TestFrameClock()) {
        nestedScrollConnection.onPostFling(Velocity.Zero, Velocity.Zero)
    }

    private fun assertSettlesTo(
        initialHeightOffset: Float,
        expected: Float,
        snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = Spring.StiffnessMediumLow),
    ) {
        val b = behavior(initialHeightOffset = initialHeightOffset, snapAnimationSpec = snapAnimationSpec)
        b.releaseAndSettle()
        assertEquals(expected, b.state.heightOffset, 0.5f)
    }

    @Test
    fun downScroll_collapsesOffset() {
        val b = behavior(initialHeightOffset = 0f)
        val consumed = b.nestedScrollConnection.onPreScroll(
            available = Offset(0f, -50f),
            source = NestedScrollSource.UserInput,
        )
        assertEquals(-50f, consumed.y, 0.01f)
        assertEquals(-50f, b.state.heightOffset, 0.01f)
    }

    @Test
    fun downScroll_doesNotExceedHeightOffsetLimit() {
        val b = behavior(initialHeightOffset = -290f, heightOffsetLimit = -300f)
        b.nestedScrollConnection.onPreScroll(
            available = Offset(0f, -50f),
            source = NestedScrollSource.UserInput,
        )
        assertEquals(-300f, b.state.heightOffset, 0.01f)
    }

    @Test
    fun upScroll_inSearchTabsRegion_expandsImmediately() {
        // flexHeightPx = 100 → flexThreshold = -100. Offset -150 puts us in the search/tabs region.
        val b = behavior(initialHeightOffset = -150f)
        val consumed = b.nestedScrollConnection.onPreScroll(
            available = Offset(0f, 30f),
            source = NestedScrollSource.UserInput,
        )
        assertTrue("expected immediate expansion in search/tabs region", consumed.y > 0f)
        // Must not expand past flexThreshold (-100)
        assertTrue("offset must not cross flexThreshold", b.state.heightOffset <= -100f)
    }

    @Test
    fun upScroll_inFlexRegion_doesNotExpandImmediately() {
        // Offset -50 is in the flex region (above flexThreshold of -100); expansion deferred to onPostScroll.
        val b = behavior(initialHeightOffset = -50f)
        val consumed = b.nestedScrollConnection.onPreScroll(
            available = Offset(0f, 30f),
            source = NestedScrollSource.UserInput,
        )
        assertEquals("flex region must not consume upward scroll", 0f, consumed.y, 0.01f)
        assertEquals("offset must be unchanged", -50f, b.state.heightOffset, 0.01f)
    }

    @Test
    fun upScroll_withUnmeasuredFlex_doesNotExpandImmediately() {
        // flexHeightPx = 0 → threshold unreachable, so upward scroll always defers to onPostScroll.
        val b = behavior(initialHeightOffset = -150f, flexHeightPx = 0)
        val consumed = b.nestedScrollConnection.onPreScroll(
            available = Offset(0f, 30f),
            source = NestedScrollSource.UserInput,
        )
        assertEquals(0f, consumed.y, 0.01f)
        assertEquals(-150f, b.state.heightOffset, 0.01f)
    }

    @Test
    fun postScroll_withLeftoverUpwardScroll_expandsFlexSection() {
        // available.y > 0 means content is at top and has leftover upward scroll.
        val b = behavior(initialHeightOffset = -20f)
        val consumed = b.nestedScrollConnection.onPostScroll(
            consumed = Offset.Zero,
            available = Offset(0f, 30f),
            source = NestedScrollSource.UserInput,
        )
        assertTrue("flex section should expand on leftover upward scroll", consumed.y > 0f)
        assertTrue("offset should move toward 0", b.state.heightOffset > -20f)
    }

    @Test
    fun canScrollFalse_ignoresScroll() {
        val b = behavior(initialHeightOffset = -150f, canScroll = { false })
        val pre = b.nestedScrollConnection.onPreScroll(
            available = Offset(0f, -50f),
            source = NestedScrollSource.UserInput,
        )
        val post = b.nestedScrollConnection.onPostScroll(
            consumed = Offset.Zero,
            available = Offset(0f, 30f),
            source = NestedScrollSource.UserInput,
        )
        assertEquals(Offset.Zero, pre)
        assertEquals(Offset.Zero, post)
        assertEquals(-150f, b.state.heightOffset, 0.01f)
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
