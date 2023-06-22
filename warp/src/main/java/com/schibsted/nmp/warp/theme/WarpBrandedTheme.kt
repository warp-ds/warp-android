package com.schibsted.nmp.warp.theme

import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.brands.Brand
import com.schibsted.nmp.warp.brands.finn.FinnColors
import com.schibsted.nmp.warp.brands.finn.FinnDarkColors
import com.schibsted.nmp.warp.brands.finn.FinnResources
import com.schibsted.nmp.warp.brands.finn.FinnShapes
import com.schibsted.nmp.warp.brands.finn.FinnTypography
import com.schibsted.nmp.warp.brands.getBrandFromFlavor
import com.schibsted.nmp.warp.brands.tori.ToriColors
import com.schibsted.nmp.warp.brands.tori.ToriDarkColors
import com.schibsted.nmp.warp.brands.tori.ToriResources
import com.schibsted.nmp.warp.brands.tori.ToriShapes
import com.schibsted.nmp.warp.brands.tori.ToriTypography

@Composable
fun WarpBrandedTheme(
    flavor: String,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    when (getBrandFromFlavor(flavor)) {
        Brand.FINN -> {
            val colors = if (darkTheme) FinnDarkColors() else FinnColors()
            val dimensions = WarpDimensions()
            WarpTheme(
                colors = colors,
                typography = FinnTypography(colors),
                shapes = FinnShapes(dimensions),
                resources = FinnResources(),
                content = content,
                dimensions = dimensions
            )
        }

        Brand.TORI -> {
            val colors = if (darkTheme) ToriDarkColors() else ToriColors()
            val dimensions = WarpDimensions()
            WarpTheme(
                colors = colors,
                typography = ToriTypography(colors),
                shapes = ToriShapes(dimensions),
                resources = ToriResources(),
                content = content,
                dimensions = dimensions
            )
        }
    }
}
