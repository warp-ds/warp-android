package com.schibsted.nmp.warpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.schibsted.nmp.warpapp.ui.MainScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*setContentView(R.layout.main_activity_layout)

        val callout = findViewById<WarpCalloutView>(R.id.callout1)
        callout.setAnchorView {
            WarpButton(
                text = "Show me a callout",
                onClick = { callout.state.isVisible = !callout.state.isVisible }
            )
        }*/


        setContent {
            MainScreen()
        }
    }
}
