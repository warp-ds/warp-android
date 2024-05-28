package com.schibsted.snapshot

import com.google.android.apps.common.testing.accessibility.framework.BuildConfig
import java.io.File

object Config {
    val dirFinn = File("src/test/snapshots/finn")
    val dirTori = File("src/test/snapshots/tori")
    val isVerifying: Boolean = System.getProperty("paparazzi.test.verify")?.toBoolean() == true
    const val maxPercentDifference = 0.1
}