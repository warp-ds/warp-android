import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.brands.vend.VendColors
import com.schibsted.nmp.warp.brands.vend.VendDarkColors
import com.schibsted.nmp.warp.brands.vend.VendShapes
import com.schibsted.nmp.warp.brands.vend.VendTypography
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpResources
import com.schibsted.nmp.warp.theme.WarpTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendWarpTheme(
    content: @Composable () -> Unit,
    forceDarkMode: Boolean? = null
) {
    val vendColors = forceDarkMode?.let { if (it) VendDarkColors else VendColors }
        ?: if (isSystemInDarkTheme()) VendDarkColors else VendColors
    val vendDimensions = WarpDimensions
    val vendResources = WarpResources
    val vendRippleConfig = RippleConfiguration(color = vendColors.background.primary, rippleAlpha = RippleAlpha(0.5f, 0.5f, 0.5f, 0.5f))

    WarpTheme(
        colors = vendColors,
        typography = VendTypography,
        shapes = VendShapes(vendDimensions),
        resources = vendResources,
        content = content,
        rippleConfig = vendRippleConfig,
        dimensions = vendDimensions
    )
}