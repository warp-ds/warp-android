package com.schibsted.nmp.warpapp

import android.app.Application
import com.schibsted.nmp.warpapp.di.warpAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WarpApplication : Application() {

        override fun onCreate() {
            super.onCreate()
            startKoin {
                androidContext(this@WarpApplication)
                modules(warpAppModule)
            }
        }
}