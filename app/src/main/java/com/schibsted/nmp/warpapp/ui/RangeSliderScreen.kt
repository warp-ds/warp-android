package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpRangeSlider
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
@ExperimentalMaterial3Api
fun RangeSliderScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpRangeSlider",
        onUp = onUp
    ) {
        RangeSliderScreenContent()
    }
}

@ExperimentalMaterial3Api
@Composable
fun RangeSliderScreenContent() {

    val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
    Column(
        modifier = Modifier
            .padding(dimensions.space3)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var from by remember { mutableStateOf(items.first().toString()) }
        var to by remember { mutableStateOf(items.last().toString()) }
        WarpText(
            "Range slider with integers",
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(bottom = dimensions.space1)
        )
        WarpText("From $from to $to")
        WarpRangeSlider(
            showTooltips = false,
            items = items,
            onRightValueChanged = {
                to = it.toString()
            },
            onLeftValueChanged = {
                from = it.toString()
            },
            onValueChangeFinished = {

            },
            showRange = false
        )
        val stringItems = listOf(
            "1900",
            "1910",
            "1920",
            "1930",
            "1940",
            "1950",
            "1960",
            "1970",
            "1980",
            "1990",
            "2000",
            "2010",
            "2020"
        )
        val startItem = stringItems[5]
        val endItem = stringItems[8]
        var from2 by remember { mutableStateOf(startItem) }
        var to2 by remember { mutableStateOf(endItem) }
        WarpText(
            "Range slider with strings",
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(bottom = dimensions.space1)
        )
        WarpText("From $from2 to $to2")
        WarpRangeSlider(
            modifier = Modifier.padding(bottom = dimensions.space1),
            items = stringItems,
            initialStartItem = startItem,
            initialEndItem = endItem,
            onRightValueChanged = {
                to2 = it.toString()
            },
            onLeftValueChanged = {
                from2 = it.toString()
            },
            onValueChangeFinished = {

            },
            showRange = false

        )
        val formattedItems = listOf(
            "1000",
            "2000",
            "3000",
            "4000",
            "5000",
            "6000",
            "7000",
            "8000",
            "9000",
            "10 000",
            "20 000",
            "30 000",
            "40 000",
            "50 000",
            "60 000",
            "70 000",
            "80 000",
            "90 000",
            "100 000",
            "200 000",
            "300 000",
            "400 000"
        )
        val resetStart = "Min"
        val resetEnd = "Max"

        var from3 by remember { mutableStateOf(resetStart) }
        var to3 by remember { mutableStateOf(resetEnd) }

        WarpText(
            "Range slider with resets",
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(bottom = dimensions.space1)
        )
        WarpText("$from3 - $to3")
        WarpRangeSlider(
            modifier = Modifier.padding(bottom = dimensions.space1),
            items = formattedItems,
            onRightValueChanged = {
                to3 = it.toString()
            },
            onLeftValueChanged = {
                from3 = it.toString()
            },
            onValueChangeFinished = {

            },
            resetAtStartText = resetStart,
            resetAtEndText = resetEnd,
            showRange = false
        )

        val items4 = listOf(
            100,
            200,
            300,
            400,
            500,
            600,
            700,
            800,
            900
        )

        var from4 by remember { mutableStateOf(items4[2].toString()) }
        var to4 by remember { mutableStateOf(items4[7].toString()) }
        WarpText(
            "Range slider with indicators",
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(bottom = dimensions.space1)
        )
        WarpText("From $from4 to $to4")
        WarpRangeSlider(
            showTooltips = true,
            items = items4,
            initialStartItem = items4[2],
            initialEndItem = items4[7],
            onRightValueChanged = {
                to4 = it.toString()
            },
            onLeftValueChanged = {
                from4 = it.toString()
            },
            onValueChangeFinished = {},
            showRange = true
        )

        WarpText(
            "Disabled range slider",
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(bottom = dimensions.space1)
        )
        WarpRangeSlider(
            items = items,
            enabled = false,
            initialStartItem = 4,
            initialEndItem = 18,
        )
    }
}
