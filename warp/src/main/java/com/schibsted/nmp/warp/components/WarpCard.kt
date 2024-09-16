package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.shapes

@Composable
fun WarpCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    content: @Composable ColumnScope.() -> Unit
) {
    val cardColors = CardColors(
        containerColor = colors.background.default,
        contentColor = colors.text.default,
        disabledContainerColor = colors.background.disabled,
        disabledContentColor = colors.text.disabled,
    )
    val cardElevation = cardElevation(
        defaultElevation = 2.dp,
        pressedElevation = 8.dp,
        focusedElevation = 4.dp,
        hoveredElevation = 4.dp,
        draggedElevation = 4.dp,
        disabledElevation = 0.dp,
    )
    Card(
        modifier = modifier,
        shape = shapes.roundedMedium,
        colors = cardColors,
        elevation = cardElevation,
        //border = BorderStroke(2.dp, colors.border.default),
        content = content
    )
}