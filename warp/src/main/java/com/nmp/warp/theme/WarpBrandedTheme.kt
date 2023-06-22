package com.nmp.warp.theme

import androidx.compose.runtime.Composable
import com.nmp.warp.brands.Brand
import com.nmp.warp.brands.finn.FinnColors
import com.nmp.warp.brands.finn.FinnDarkColors
import com.nmp.warp.brands.finn.FinnResources
import com.nmp.warp.brands.finn.FinnShapes
import com.nmp.warp.brands.finn.FinnTypography
import com.nmp.warp.brands.getBrandFromFlavor
import com.nmp.warp.brands.tori.ToriColors
import com.nmp.warp.brands.tori.ToriDarkColors
import com.nmp.warp.brands.tori.ToriResources
import com.nmp.warp.brands.tori.ToriShapes
import com.nmp.warp.brands.tori.ToriTypography

@Composable
fun WarpBrandedTheme(
    flavor: String,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    when (getBrandFromFlavor(flavor)) {
        Brand.FINN -> {
            val colors = if (darkTheme) FinnDarkColors() else FinnColors()
            WarpTheme(
                colors = colors,
                typography = FinnTypography(colors),
                shapes = FinnShapes(),
                resources = FinnResources(),
                content = content
            )
        }

        Brand.TORI -> {
            val colors = if (darkTheme) ToriDarkColors() else ToriColors()
            WarpTheme(
                colors = colors,
                typography = ToriTypography(colors),
                shapes = ToriShapes(),
                resources = ToriResources(),
                content = content
            )
        }
    }
}
