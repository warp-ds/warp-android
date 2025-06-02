package com.schibsted.nmp.warpapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.complexcomponents.states.EmptyWarpState
import com.schibsted.nmp.warp.complexcomponents.states.ErrorWarpState
import com.schibsted.nmp.warp.complexcomponents.states.LoadingWarpState
import com.schibsted.nmp.warp.complexcomponents.states.LoginWarpState
import com.schibsted.nmp.warp.complexcomponents.states.OfflineWarpState


@Composable
fun WarpStateScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpState", onUp = onUp) {
        WarpStateContent()
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun WarpStateContent() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val itemHeight = maxHeight * .9f
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            LoadingWarpState(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
            )
            HorizontalDivider()
            EmptyWarpState(
                title = "No data",
                description = "No data available",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
            )
            HorizontalDivider()
            ErrorWarpState(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
            )
            HorizontalDivider()
            OfflineWarpState(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
            )
            HorizontalDivider()
            LoginWarpState(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
            )
            HorizontalDivider()
        }
    }
}