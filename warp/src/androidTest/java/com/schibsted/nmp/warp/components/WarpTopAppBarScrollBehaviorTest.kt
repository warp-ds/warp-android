@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warp.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class WarpTopAppBarScrollBehaviorTest {
    @get:Rule
    val compose = createComposeRule()

    private fun runWithBehavior(
        initialHeightOffset: Float = 0f,
        heightOffsetLimit: Float = -300f,
        flexHeightPx: Int = 100,
        block: (WarpTopAppBarScrollBehavior) -> Unit,
    ) {
        var behavior: WarpTopAppBarScrollBehavior? = null
        compose.setContent {
            val state = remember {
                TopAppBarState(
                    initialHeightOffsetLimit = heightOffsetLimit,
                    initialHeightOffset = initialHeightOffset,
                    initialContentOffset = 0f,
                )
            }
            behavior = remember(state) {
                WarpTopAppBarScrollBehavior(state = state).also {
                    it.flexHeightPxState.intValue = flexHeightPx
                }
            }
        }
        compose.waitForIdle()
        compose.runOnUiThread { block(behavior!!) }
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
}
