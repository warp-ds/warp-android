package com.schibsted.nmp.warpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.theme.WarpTheme


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val flavor = viewModel.flavor.collectAsState()

            BrandTheme(flavor = flavor.value) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = WarpTheme.colors.secondary
                )
                {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        ThemeSelector(
                            theme = flavor.value,
                            onThemeSelected = { viewModel.setFlavor(it) }
                        )
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
                        WarpButton(
                            text = "Wanna buy a duck?",
                            onClick = { },
                            buttonStyle = WarpButtonStyle.Tertiary
                        )
                        WarpButton(
                            text = "This duck cannot be sold!",
                            onClick = {},
                            buttonStyle = WarpButtonStyle.Critical
                        )
                        WarpButton(
                            text = "This duck was already sold!",
                            onClick = {},
                            buttonStyle = WarpButtonStyle.CriticalQuiet
                        )
                    }
                }
            }
        }
    }
}
