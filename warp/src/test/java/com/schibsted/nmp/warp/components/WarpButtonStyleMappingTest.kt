package com.schibsted.nmp.warp.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

@Suppress("DEPRECATION")
class WarpButtonStyleMappingTest {
    private val replacements = mapOf(
        WarpButtonStyle.Primary to WarpButtonStyle.Prominent,
        WarpButtonStyle.Secondary to WarpButtonStyle.Tonal,
        WarpButtonStyle.Quiet to WarpButtonStyle.Text,
        WarpButtonStyle.Negative to WarpButtonStyle.Destructive,
        WarpButtonStyle.NegativeQuiet to WarpButtonStyle.DestructiveText,
        WarpButtonStyle.Utility to WarpButtonStyle.Flat,
        WarpButtonStyle.UtilityQuiet to WarpButtonStyle.Text,
        WarpButtonStyle.UtilityOverlay to WarpButtonStyle.Elevated,
    )

    @Test
    fun legacyStylesMapToTheirReplacement() {
        replacements.forEach { (legacy, modern) ->
            assertEquals(legacy.name, modern, legacy.modernEquivalent())
        }
    }

    @Test
    fun onlyTheEightOldStylesAreLegacy() {
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
