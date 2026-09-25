package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpBottomSheet
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpDivider
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun BottomSheetScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpBottomSheet",
        onUp = onUp
    ) {
        BottomSheetScreenContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreenContent() {
    var openSheet by rememberSaveable { mutableStateOf(false) }
    var selectedCategory by rememberSaveable { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2)
    ) {
        WarpButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Select a category",
            onClick = { openSheet = true },
            style = WarpButtonStyle.Primary,
        )
        selectedCategory?.let {
            WarpText(
                modifier = Modifier.padding(top = dimensions.space2),
                text = "Selected: $it",
                style = WarpTextStyle.Body,
            )
        }
    }

    if (openSheet) {
        WarpBottomSheet(onDismiss = { openSheet = false }) {
            LazyColumn(modifier = Modifier.padding(horizontal = dimensions.space3)) {
                item {
                    WarpText(
                        modifier = Modifier.padding(bottom = dimensions.space2),
                        text = "Categories",
                        style = WarpTextStyle.Title3,
                    )
                }
                items(sampleCategories) { category ->
                    WarpText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedCategory = category
                                openSheet = false
                            }
                            .padding(vertical = dimensions.space2),
                        text = category,
                        style = WarpTextStyle.Body,
                    )
                    WarpDivider()
                }
            }
        }
    }
}

private val sampleCategories = listOf(
    "Cars",
    "Motorcycles",
    "Boats",
    "Real estate",
    "Vacation homes",
    "Rentals",
    "Jobs",
    "Torget",
    "Electronics",
    "Furniture",
    "Kids and family",
    "Sports and leisure",
    "Music instruments",
    "Books and magazines",
    "Clothing and accessories",
    "Antiques and collectibles",
    "Animals and pets",
    "Handmade",
    "Services",
    "Business",
)
