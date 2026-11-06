package com.schibsted.nmp.warp.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

@Suppress("DEPRECATION")
class WarpButtonStyleMappingTest {
    private val replacements = mapOf(
        WarpButtonStyle.Utility to WarpButtonStyle.Overlay,
        WarpButtonStyle.UtilityQuiet to WarpButtonStyle.Quiet,
        WarpButtonStyle.UtilityOverlay to WarpButtonStyle.OverlayElevated,
    )

    @Test
    fun legacyStylesMapToTheirReplacement() {
        replacements.forEach { (legacy, modern) ->
            assertEquals(legacy.name, modern, legacy.modernEquivalent())
        }
    }

    @Test
    fun onlyTheThreeUtilityStylesAreLegacy() {
        assertEquals(replacements.keys, WarpButtonStyle.values().filter { it.isLegacy }.toSet())
    }

    @Test
    fun modernStylesMapToThemselves() {
        val modern = WarpButtonStyle.values().filterNot { it.isLegacy }
        assertEquals(9, modern.size)
        modern.forEach { assertEquals(it, it.modernEquivalent()) }
    }

    @Test
    fun deprecatedStylesPointAtTheirMappedReplacement() {
        replacements.forEach { (legacy, modern) ->
            val deprecated = WarpButtonStyle::class.java.getField(legacy.name).getAnnotation(Deprecated::class.java)
            assertNotNull("${legacy.name} is not @Deprecated", deprecated)
            assertEquals(legacy.name, "WarpButtonStyle.${modern.name}", deprecated.replaceWith.expression)
        }
    }
}
