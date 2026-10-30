package com.schibsted.nmp.warp.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.schibsted.nmp.warp.theme.WarpTheme.colors

// TODO(tokens): interim values for the new buttons, not yet in warp-ds/tokens
/**
 * Hover/pressed overlay colours for the new button styles that don't exist as tokens yet.
 *
 * - Neutral overlays are the theme's default text colour (dark in light mode, white in dark mode) with alpha.
 * - Inverted-static fills are constants, identical in light and dark mode and across all brands.
 * - Negative overlays are the theme's negative background colour with alpha.
 *
 * Remove once the tokens are published and synced from warp-ds/tokens.
 */
internal object WarpButtonInterimColors {
    private const val RestAlpha = 0x0F / 255f
    private const val HoverAlpha = 0x1F / 255f
    private const val ActiveAlpha = 0x29 / 255f
    private const val NegativeActiveAlpha = 0x33 / 255f

    private val OverlayInvertedStaticBase = Color(0xFF1b1b1f)
    private val SecondaryInvertedStaticBase = Color(0xFFffffff)

    val neutral: Color @Composable get() = colors.text.default.copy(alpha = RestAlpha)
    val neutralHover: Color @Composable get() = colors.text.default.copy(alpha = HoverAlpha)
    val neutralActive: Color @Composable get() = colors.text.default.copy(alpha = ActiveAlpha)

    val overlayInvertedStatic: Color = OverlayInvertedStaticBase.copy(alpha = 0xCC / 255f)
    val overlayInvertedStaticHover: Color = OverlayInvertedStaticBase.copy(alpha = 0xB8 / 255f)
    val overlayInvertedStaticActive: Color = OverlayInvertedStaticBase.copy(alpha = 0xA3 / 255f)

    val secondaryInvertedStatic: Color = SecondaryInvertedStaticBase.copy(alpha = RestAlpha)
    val secondaryInvertedStaticHover: Color = SecondaryInvertedStaticBase.copy(alpha = HoverAlpha)
    val secondaryInvertedStaticActive: Color = SecondaryInvertedStaticBase.copy(alpha = ActiveAlpha)

    val negativeHover: Color @Composable get() = colors.background.negative.copy(alpha = HoverAlpha)
    val negativeActive: Color @Composable get() = colors.background.negative.copy(alpha = NegativeActiveAlpha)
}
