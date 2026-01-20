import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import com.schibsted.nmp.warp.brands.neutral.NeutralDarkColors
import com.schibsted.nmp.warp.theme.WarpDimensions
import com.schibsted.nmp.warp.theme.WarpResources
import com.schibsted.nmp.warp.theme.WarpTheme
import com.vend.warp.brands.neutral.NeutralColors
import com.vend.warp.brands.neutral.NeutralShapes
import com.vend.warp.brands.neutral.NeutralTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeutralWarpTheme(
    content: @Composable () -> Unit,
    forceDarkMode: Boolean? = null
) {
    val neutralColors = forceDarkMode?.let { if (it) NeutralDarkColors else NeutralColors }
        ?: if (isSystemInDarkTheme()) NeutralDarkColors else NeutralColors
    val neutralDimensions = WarpDimensions
    val neutralResources = WarpResources
    val neutralRippleConfig = RippleConfiguration(color = neutralColors.background.primary, rippleAlpha = RippleAlpha(0.5f, 0.5f, 0.5f, 0.5f))

    WarpTheme(
        colors = neutralColors,
        typography = NeutralTypography,
        shapes = NeutralShapes(neutralDimensions),
        resources = neutralResources,
        content = content,
        rippleConfig = neutralRippleConfig,
        dimensions = neutralDimensions
    )
}