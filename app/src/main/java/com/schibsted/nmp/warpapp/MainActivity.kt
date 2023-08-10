package com.schibsted.nmp.warpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.schibsted.nmp.warpapp.ui.MainScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            //Test new repo location
            MainScreen()
        }
    }
}
