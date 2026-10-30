package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpBottomSheet
import com.schibsted.nmp.warp.components.WarpBottomSheetHeader
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpDivider
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpResources.icons
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

private enum class SampleSheet { Title, Categories, NoTitle, CustomHeader }

@Composable
fun BottomSheetScreenContent() {
    var openSheet by rememberSaveable { mutableStateOf<SampleSheet?>(null) }
    var selectedCategory by rememberSaveable { mutableStateOf<String?>(null) }
    val closeSheet = { openSheet = null }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensions.space2),
        verticalArrangement = Arrangement.spacedBy(dimensions.space2),
    ) {
        SheetButton("Title") { openSheet = SampleSheet.Title }
        SheetButton("Select a category") { openSheet = SampleSheet.Categories }
        selectedCategory?.let {
            WarpText(text = "Selected: $it", style = WarpTextStyle.Body)
        }
        SheetButton("No title") { openSheet = SampleSheet.NoTitle }
        SheetButton("Custom header") { openSheet = SampleSheet.CustomHeader }
    }

    when (openSheet) {
        SampleSheet.Title -> WarpBottomSheet(
            onDismissRequest = closeSheet,
            header = WarpBottomSheetHeader(title = "Sort by"),
        ) { dismiss ->
            Column(modifier = Modifier.padding(dimensions.space2)) {
                WarpText(text = "Newest first", style = WarpTextStyle.Body)
                WarpText(text = "Oldest first", style = WarpTextStyle.Body)
                WarpButton(
                    text = "Done",
                    onClick = dismiss,
                    style = WarpButtonStyle.Primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensions.space2),
                )
            }
        }

        SampleSheet.Categories -> CategoriesSheet(
            onSelect = { selectedCategory = it },
            onDismissRequest = closeSheet,
        )

        SampleSheet.NoTitle -> WarpBottomSheet(
            onDismissRequest = closeSheet,
            header = WarpBottomSheetHeader(),
        ) { dismiss ->
            Column(modifier = Modifier.padding(dimensions.space2)) {
                WarpText(
                    text = "A sheet without a title: the header only holds the close button.",
                    style = WarpTextStyle.Body,
                )
                WarpButton(
                    text = "Turn on",
                    onClick = dismiss,
                    style = WarpButtonStyle.Primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensions.space2),
                )
            }
        }

        SampleSheet.CustomHeader -> CustomHeaderSheet(onDismissRequest = closeSheet)

        null -> Unit
    }
}

@Composable
private fun SheetButton(text: String, onClick: () -> Unit) {
    WarpButton(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        onClick = onClick,
        style = WarpButtonStyle.Primary,
    )
}

/**
 * A sheet whose header needs more than a title, so it passes no Warp header and draws its own.
 * Prefer [WarpBottomSheetHeader] whenever a title and the close button are enough.
 */
@Composable
private fun CustomHeaderSheet(onDismissRequest: () -> Unit) {
    WarpBottomSheet(onDismissRequest = onDismissRequest, header = null) { dismiss ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimensions.space2, end = dimensions.space05),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WarpText(text = "Saved search", style = WarpTextStyle.Title3, modifier = Modifier.weight(1f))
            WarpButton(text = "Share", onClick = {}, style = WarpButtonStyle.Quiet)
            IconButton(onClick = dismiss) { WarpIcon(icon = icons.close) }
        }
        WarpText(
            text = "The content draws its own header, here with a share action, and closes the sheet with dismiss.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(dimensions.space2),
        )
    }
}

@Composable
private fun CategoriesSheet(onSelect: (String) -> Unit, onDismissRequest: () -> Unit) {
    WarpBottomSheet(
        onDismissRequest = onDismissRequest,
        header = WarpBottomSheetHeader(title = "Categories"),
    ) { dismiss ->
        LazyColumn(modifier = Modifier.padding(horizontal = dimensions.space3)) {
            items(sampleCategories) { category ->
                WarpText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSelect(category)
                            dismiss()
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
