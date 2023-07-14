package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.Expandable
import com.schibsted.nmp.warp.components.WarpAlert
import com.schibsted.nmp.warp.components.WarpAlertType
import com.schibsted.nmp.warp.components.WarpBox
import com.schibsted.nmp.warp.components.WarpBoxStyle
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.theme.WarpBrandedTheme
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider

@Composable
fun ExpandableScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Expandable", onUp = onUp) {
        ExpandableScreenContent()
    }
}

@Composable
private fun ExpandableScreenContent() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Just an expandable")
        Expandable(title = "Title", modifier = Modifier.padding(16.dp)) {
            Text("This is the hidden content")
        }

        Text(modifier = Modifier.padding(top = 16.dp), text = "Expandable with box")
        WarpBox(modifier = Modifier.padding(16.dp), boxStyle = WarpBoxStyle.BORDERED) {
            Expandable(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                title = "Title",
                initiallyExpanded = true
            ) {
                Column(Modifier.padding(bottom = 8.dp)) {
                    Text("Here we have a little text. But we can add pretty much anything so why not a button")
                    WarpButton(onClick = {  }, buttonStyle = WarpButtonStyle.Utility) {
                        Text("Click me")
                    }
                }
            }
        }

        Text(modifier = Modifier.padding(top = 16.dp), text = "Expandable with alert")
        WarpAlert(
            modifier = Modifier.padding(16.dp),
            type = WarpAlertType.Warning
        ) {
            Expandable(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                title = "Title",
                initiallyExpanded = true
            ) {
                Column(Modifier.padding(bottom = 8.dp)) {
                    Text("Here we have a little text. But we can add pretty much anything so why not a button")
                    WarpButton(onClick = {}, buttonStyle = WarpButtonStyle.Utility) {
                        Text("Click me")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun ExpandableScreenPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    WarpBrandedTheme(
        flavor = flavor,
        darkTheme = isSystemInDarkTheme()
    ) {
        ExpandableScreenContent()
    }
}