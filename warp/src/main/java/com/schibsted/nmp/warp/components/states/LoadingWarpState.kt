package com.schibsted.nmp.warp.components.states

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.theme.WarpTheme

@Composable
fun LoadingWarpState(
    modifier: Modifier = Modifier.fillMaxSize(),
    color: Color = WarpTheme.colors.background.secondary,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = color)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingWarpStatePreview() {
    LoadingWarpState()
}
