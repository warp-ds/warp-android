package com.schibsted.snapshot

import androidx.compose.runtime.Composable
import com.google.testing.junit.testparameterinjector.TestParameter
import com.schibsted.nmp.warp.brands.finn.FinnWarpTheme
import com.schibsted.nmp.warp.brands.tori.ToriWarpTheme
import java.io.File

object Config {
    val dirFinn = File("src/test/snapshots/finn")
    val dirTori = File("src/test/snapshots/tori")
    val isVerifying: Boolean = System.getProperty("paparazzi.test.verify")?.toBoolean() == true
    const val maxPercentDifference = 0.1
}

@Composable
fun WarpTheme(flavor: Flavor, content: @Composable () -> Unit) {
    when (flavor) {
        Flavor.Finn -> FinnWarpTheme(content)
        Flavor.Tori -> ToriWarpTheme(content)
    }
}

enum class Flavor(val dir: File) {
    Finn(Config.dirFinn),
    Tori(Config.dirTori)
}

internal object FontScaleProvider : TestParameter.TestParameterValuesProvider {
    override fun provideValues(): List<Float> {
        return listOf(1.0F, 2.0F)
    }
}