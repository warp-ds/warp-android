package com.schibsted.nmp.warpapp.paparazzi

import java.io.File

object PaparazziConfig {
    val dirFinn = File("src/test/snapshots/finn")
    val dirTori = File("src/test/snapshots/tori")
    val isVerifying: Boolean = System.getProperty("paparazzi.test.verify")?.toBoolean() == true
    const val maxPercentDifference = 0.1
}