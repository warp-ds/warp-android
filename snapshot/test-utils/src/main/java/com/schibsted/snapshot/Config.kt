package com.schibsted.snapshot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import com.google.testing.junit.testparameterinjector.TestParameter
import com.schibsted.nmp.warp.brands.blocket.BlocketWarpTheme
import com.schibsted.nmp.warp.brands.dba.DbaWarpTheme
import com.schibsted.nmp.warp.brands.finn.FinnWarpTheme
import com.schibsted.nmp.warp.brands.tori.ToriWarpTheme
import java.io.File
import java.util.Locale

object Config {
    val dirFinn = File("src/test/snapshots/finn")
    val dirTori = File("src/test/snapshots/tori")
    val dirDba = File("src/test/snapshots/dba")
    val dirBlocket = File("src/test/snapshots/blocket")
    val isVerifying: Boolean = System.getProperty("paparazzi.test.verify")?.toBoolean() == true
    const val maxPercentDifference = 0.1
}

@Composable
fun WarpTheme(flavor: Flavor, content: @Composable () -> Unit) {
    val configuration = android.content.res.Configuration(LocalConfiguration.current).apply {
        setLocale(flavor.locale)
    }
    CompositionLocalProvider(LocalConfiguration provides configuration) {
        when (flavor) {
            Flavor.Finn -> FinnWarpTheme(content)
            Flavor.Tori -> ToriWarpTheme(content)
            Flavor.Dba -> DbaWarpTheme(content)
            Flavor.Blocket -> BlocketWarpTheme(content)
        }
    }
}

enum class Flavor(val dir: File, val locale: Locale) {
    Finn(Config.dirFinn, Locale("nb", "NO")),
    Tori(Config.dirTori, Locale("fi", "FI")),
    Dba(Config.dirDba, Locale("da", "DK")),
    Blocket(Config.dirBlocket, Locale("sv", "SE"))
}

object FontScaleProvider : TestParameter.TestParameterValuesProvider {
    override fun provideValues(): List<Float> {
        return listOf(1.0F, 2.0F)
    }
}