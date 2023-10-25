package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WarpText("Name")
            WarpText("Size")
        }
        Divider(modifier = Modifier
            .fillMaxWidth()
            .background(WarpTheme.colors.border.default)
        )
        TextItem("display", style = WarpTheme.typography.display, warpTextStyle = WarpTextStyle.Display,)
        TextItem("title1", style = WarpTheme.typography.title1, warpTextStyle = WarpTextStyle.Title1)
        TextItem("title2", style = WarpTheme.typography.title2, warpTextStyle = WarpTextStyle.Title2)
        TextItem("title3", style = WarpTheme.typography.title3, warpTextStyle = WarpTextStyle.Title3)
        TextItem("title4", style = WarpTheme.typography.title4, warpTextStyle = WarpTextStyle.Title4)
        TextItem("title5", style = WarpTheme.typography.title5, warpTextStyle = WarpTextStyle.Title5)
        TextItem("title6", style = WarpTheme.typography.title6, warpTextStyle = WarpTextStyle.Title6)
        TextItem("preamble", style = WarpTheme.typography.preamble, warpTextStyle = WarpTextStyle.Preamble)
        TextItem("body", style = WarpTheme.typography.body, warpTextStyle = WarpTextStyle.Body)
        TextItem("bodyStrong", style = WarpTheme.typography.bodyStrong, warpTextStyle = WarpTextStyle.BodyStrong)
        TextItem("caption", style = WarpTheme.typography.caption, warpTextStyle = WarpTextStyle.Caption)
        TextItem("captionStrong", style = WarpTheme.typography.captionStrong, warpTextStyle = WarpTextStyle.CaptionStrong)
        TextItem("detail", style = WarpTheme.typography.detail, warpTextStyle = WarpTextStyle.Detail)
        TextItem("detailStrong", style = WarpTheme.typography.detailStrong, warpTextStyle = WarpTextStyle.DetailStrong)
    }
}

@Composable
private fun TextItem(text: String, style: TextStyle, warpTextStyle: WarpTextStyle) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WarpText(text = text, style = warpTextStyle)
        WarpText("${style.fontSize.value} ${style.fontSize.type}")
    }
    Divider(Modifier.fillMaxWidth())
}

@Preview
@Composable
internal fun PreviewTypographyScreen(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        TypographyContent()
    }
}

