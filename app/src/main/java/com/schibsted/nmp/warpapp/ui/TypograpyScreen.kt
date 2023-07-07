package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpTheme
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun TypographyScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Typography", onUp = onUp) {
        TypographyContent()
    }
}

@Composable
private fun TypographyContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Name")
            Text("Size")
        }
        Divider(modifier = Modifier.fillMaxWidth())
        TextItem("display", style = WarpTheme.typography.display)
        TextItem("title1", style = WarpTheme.typography.title1)
        TextItem("title2", style = WarpTheme.typography.title2)
        TextItem("title3", style = WarpTheme.typography.title3)
        TextItem("title4", style = WarpTheme.typography.title4)
        TextItem("title5", style = WarpTheme.typography.title5)
        TextItem("title6", style = WarpTheme.typography.title6)
        TextItem("preamble", style = WarpTheme.typography.preamble)
        TextItem("body", style = WarpTheme.typography.body)
        TextItem("bodyStrong", style = WarpTheme.typography.bodyStrong)
        TextItem("caption", style = WarpTheme.typography.caption)
        TextItem("captionStrong", style = WarpTheme.typography.captionStrong)
        TextItem("detail", style = WarpTheme.typography.detail)
        TextItem("detailStrong", style = WarpTheme.typography.detailStrong)
    }
}

@Composable
private fun TextItem(text: String, style: TextStyle) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, style = style)
        Text("${style.fontSize.value} ${style.fontSize.type}")
    }
    Divider(Modifier.fillMaxWidth())
}

@Preview
@Composable
internal fun PreviewTypographyScreen(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor) {
        TypographyContent()
    }
}

