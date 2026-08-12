package com.schibsted.nmp.warpapp.ui

import com.schibsted.nmp.warp.theme.WarpIconResource
import com.schibsted.nmp.warp.theme.WarpIconResources
import com.schibsted.nmp.warp.theme.WarpTaxonomyIconResource
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import kotlin.reflect.full.memberProperties

/**
 * Guards against forgetting to add newly-exposed icons to [IconScreen].
 *
 * When a new `val foo` is added to `WarpIconResources` and nobody references
 * `icons.foo` in `IconScreen.CreateIcons`, this test fails with the exact list
 * of missing accessors so it's obvious what to add.
 *
 * Brand icons are intentionally excluded — they render via `WarpLogo`, not the
 * icon gallery.
 */
class IconScreenTest {

    @Test
    fun `IconScreen references every standard and taxonomy icon accessor`() {
        val galleryIconTypes = setOf(
            WarpIconResource::class,
            WarpTaxonomyIconResource::class,
        )

        val allAccessors = WarpIconResources::class.memberProperties
            .filter { it.returnType.classifier in galleryIconTypes }
            .map { it.name }
            .toSet()

        val iconScreenSource = File(ICON_SCREEN_PATH).readText()
        val referenced = Regex("""icons\.(\w+)""")
            .findAll(iconScreenSource)
            .map { it.groupValues[1] }
            .toSet()

        val missing = (allAccessors - referenced).sorted()
        assertEquals(
            "IconScreen is out of sync with WarpIconResources. " +
                "Add these lines to CreateIcons in IconScreen.kt:\n" +
                missing.joinToString("\n") { "    CreateIcon(icons.$it, size)" },
            emptyList<String>(),
            missing,
        )
    }

    private companion object {
        const val ICON_SCREEN_PATH =
            "src/main/java/com/schibsted/nmp/warpapp/ui/IconScreen.kt"
    }
}
