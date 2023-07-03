package com.schibsted.nmp.warpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val flavor = viewModel.flavor.collectAsState()

            BrandTheme(flavor = flavor.value) {
                MainScreen(flavor.value) {
                    viewModel.setFlavor(it)
                }
            }
        }
    }
}
