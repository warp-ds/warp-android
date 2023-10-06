package com.schibsted.nmp.warpapp.di

import com.schibsted.nmp.warp.components.legacy.LegacyWarpTheme
import com.schibsted.nmp.warpapp.BrandTheme
import org.koin.dsl.module

val warpAppModule = module {
    single<LegacyWarpTheme> { BrandTheme(flavor = "tori") }
}
