package com.nmp.warpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.theme.WarpTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrandTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = WarpTheme.colors.secondary
                )
                {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Image(
                            painter = painterResource(WarpTheme.resources.logo),
                            contentDescription = "BrandLogo",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .size(170.dp)
                        )
                        Text(
                            text = "Hello NMPeeps!",
                            color = WarpTheme.colors.primary,
                            style = WarpTheme.typography.title4
                        )
                        Text(
                            text = "Whats up?",
                            color = WarpTheme.colors.primary,
                            style = WarpTheme.typography.title4
                        )
                        WarpButton(
                            text = "Take me to NMP!",
                            onClick = { },
                            buttonStyle = WarpButtonStyle.Primary
                        )
                        WarpButton(
                            text = "Sell your duck",
                            onClick = { },
                            buttonStyle = WarpButtonStyle.Secondary
                        )
                    }
                }
            }
        }
    }
}
