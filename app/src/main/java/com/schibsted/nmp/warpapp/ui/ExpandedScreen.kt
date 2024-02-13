package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpBox
import com.schibsted.nmp.warp.components.WarpBoxStyle
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpExpandable
import com.schibsted.nmp.warp.components.WarpText

@Composable
fun ExpandableScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpExpandable", onUp = onUp) {
        ExpandableScreenContent()
    }
}

@Composable
private fun ExpandableScreenContent() {
    Column(modifier = Modifier.fillMaxWidth()) {
        WarpText("Just an expandable")
        WarpExpandable(title = "Title", modifier = Modifier.padding(16.dp)) {
            WarpText("This is the hidden content")
        }

        WarpText(modifier = Modifier.padding(top = 16.dp), text = "WarpExpandable inside box")
        WarpBox(modifier = Modifier.padding(16.dp), boxStyle = WarpBoxStyle.Info) {
            WarpExpandable(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                title = "Title",
                initiallyExpanded = true
            ) {
                Column(Modifier.padding(bottom = 8.dp)) {
                    WarpText("Here we have a little text. But we can add pretty much anything so why not a button")
                    WarpButton(onClick = {  }, buttonStyle = WarpButtonStyle.Utility) {
                        WarpText("Click me")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun ExpandableScreenPreview() {
    ExpandableScreenContent()
}