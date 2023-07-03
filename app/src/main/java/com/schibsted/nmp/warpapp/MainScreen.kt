package com.schibsted.nmp.warpapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpBox
import com.schibsted.nmp.warp.components.WarpBoxStyle
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun MainScreen(flavor: String, onSetFlavor: (String) -> Unit) {
    var loading by remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = WarpTheme.colors.secondary
    )
    {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Surface {

            }
            ThemeSelector(
                theme = flavor,
                onThemeSelected = { onSetFlavor(it) }
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
                loading = loading,
                onClick = { loading = !loading },
                buttonStyle = WarpButtonStyle.Primary,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            WarpButton(
                text = "Sell your duck",
                onClick = { enabled = !enabled },
                buttonStyle = WarpButtonStyle.Secondary,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            WarpButton(
                text = "Wanna buy a duck?",
                enabled = enabled,
                onClick = { },
                buttonStyle = WarpButtonStyle.Tertiary,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            WarpButton(
                text = "This duck cannot be sold!",
                onClick = {},
                buttonStyle = WarpButtonStyle.Critical,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            WarpButton(
                text = "This duck was already sold!",
                onClick = {},
                buttonStyle = WarpButtonStyle.CriticalQuiet,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            WarpButton(
                text = "Duck",
                onClick = {},
                buttonStyle = WarpButtonStyle.Utility,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            WarpButton(
                text = "Duck duck",
                onClick = {},
                buttonStyle = WarpButtonStyle.UtilityQuiet,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            WarpButton(
                text = "Duck duck duck",
                onClick = {},
                buttonStyle = WarpButtonStyle.UtilityOverlay,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            WarpButton(
                text = "Loading ducks",
                loading = loading,
                onClick = {loading = !loading},
                buttonStyle = WarpButtonStyle.UtilityQuiet,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            WarpBox(
                boxStyle = WarpBoxStyle.NEUTRAL,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(modifier = Modifier.padding(16.dp), text = "Neutral")
            }
            WarpBox(
                boxStyle = WarpBoxStyle.INFO,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(modifier = Modifier.padding(16.dp), text = "Info")
            }
            WarpBox(
                boxStyle = WarpBoxStyle.BORDERED,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(modifier = Modifier.padding(16.dp), text = "Bordered")
            }
        }
    }
}