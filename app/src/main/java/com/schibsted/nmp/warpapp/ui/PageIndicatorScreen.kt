package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpPageIndicator
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun PageIndicatorScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpPageIndicator",
        onUp = onUp
    ) {
        PageIndicatorScreenContent()
    }
}

@Composable
fun PageIndicatorScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val pagerState = rememberPagerState(pageCount = {5})
        HorizontalPager(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(400.dp).fillMaxWidth(),
            state = pagerState,
        ) { page ->
            WarpText(
                modifier = Modifier.padding(dimensions.space2),
                text = "Your pager content here! Page: ${page + 1}",
            )
        }
        WarpPageIndicator(
            pageCount = pagerState.pageCount,
            currentPage = pagerState.currentPage
        )
    }
}