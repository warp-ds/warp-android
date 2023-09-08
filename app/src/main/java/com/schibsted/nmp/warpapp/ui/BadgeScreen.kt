package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpBadge
import com.schibsted.nmp.warp.components.WarpBadgeStyle
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun BadgeScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpBadge", onUp = onUp) {
        BadgeContent()
    }
}

@Composable
private fun BadgeContent() {
    Column {
        Card(
            Modifier
                .padding(8.dp)
                .background(Color.White),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
            ) {
                WarpBadge(
                    text = "Default",
                    alignment = Alignment.Center
                )
                WarpBadge(
                    text = "Error",
                    style = WarpBadgeStyle.Error,
                    alignment = Alignment.TopStart,
                )
                WarpBadge(
                    text = "Warning",
                    style = WarpBadgeStyle.Warning,
                    alignment = Alignment.TopEnd
                )
                WarpBadge(
                    text = "Success",
                    style = WarpBadgeStyle.Success,
                    alignment = Alignment.BottomStart
                )
                WarpBadge(
                    text = "Info",
                    style = WarpBadgeStyle.Info,
                    alignment = Alignment.BottomEnd
                )
                WarpBadge(
                    text = "Disabled",
                    style = WarpBadgeStyle.Disabled,
                    alignment = Alignment.CenterStart
                )
            }
        }
    }
}

@Preview
@Composable
internal fun BadgeContentPreview(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor) {
        BadgeContent()
    }
}