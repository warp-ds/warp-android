package com.schibsted.nmp.warpapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpBottomSheet
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

private enum class SampleSheet { Categories, Title, TitleAndAction, NavigationIcon, NotDraggable }

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
        SheetButton("Select a category") { openSheet = SampleSheet.Categories }
        selectedCategory?.let {
            WarpText(text = "Selected: $it", style = WarpTextStyle.Body)
        }
        SheetButton("Title") { openSheet = SampleSheet.Title }
        SheetButton("Title and action") { openSheet = SampleSheet.TitleAndAction }
        SheetButton("Custom navigation icon") { openSheet = SampleSheet.NavigationIcon }
        SheetButton("Not draggable") { openSheet = SampleSheet.NotDraggable }
    }

    when (openSheet) {
        SampleSheet.Categories -> CategoriesSheet(
            onSelect = {
                selectedCategory = it
                closeSheet()
            },
            onDismissRequest = closeSheet,
        )

        SampleSheet.Title -> WarpBottomSheet(
            onDismissRequest = closeSheet,
            title = "Sort by",
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

        SampleSheet.TitleAndAction -> WarpBottomSheet(
            onDismissRequest = closeSheet,
            title = "Filters",
            actionText = "Reset",
            onActionClick = {},
        ) {
            WarpText(
                text = "Content below a header with a title and an action.",
                style = WarpTextStyle.Body,
                modifier = Modifier.padding(dimensions.space2),
            )
        }

        SampleSheet.NavigationIcon -> TwoStepSheet(onDismissRequest = closeSheet)

        SampleSheet.NotDraggable -> WarpBottomSheet(
            onDismissRequest = closeSheet,
            draggable = false,
            title = "Reorder photos",
        ) {
            WarpText(
                text = "Content with its own drag gesture, e.g. drag-to-reorder list items.",
                style = WarpTextStyle.Body,
                modifier = Modifier.padding(dimensions.space2),
            )
        }

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

@Composable
private fun TwoStepSheet(onDismissRequest: () -> Unit) {
    var step by rememberSaveable { mutableIntStateOf(1) }
    WarpBottomSheet(
        onDismissRequest = onDismissRequest,
        navigationIcon = { dismiss ->
            if (step == 1) {
                IconButton(onClick = dismiss) { WarpIcon(icon = icons.close) }
            } else {
                IconButton(onClick = { step = 1 }) { WarpIcon(icon = icons.arrowLeft) }
            }
        },
        title = "Step $step of 2",
    ) { dismiss ->
        BackHandler(enabled = step == 2) { step = 1 }
        Column(modifier = Modifier.padding(dimensions.space2)) {
            WarpText(
                text = if (step == 1) {
                    "Close dismisses the sheet. Continue to see the back button."
                } else {
                    "Back, or system back, returns to step 1."
                },
                style = WarpTextStyle.Body,
            )
            WarpButton(
                text = if (step == 1) "Next" else "Done",
                onClick = { if (step == 1) step = 2 else dismiss() },
                style = WarpButtonStyle.Primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensions.space2),
            )
        }
    }
}

@Composable
private fun CategoriesSheet(onSelect: (String) -> Unit, onDismissRequest: () -> Unit) {
    WarpBottomSheet(onDismissRequest = onDismissRequest, title = "Categories") {
        LazyColumn(modifier = Modifier.padding(horizontal = dimensions.space2)) {
            items(sampleCategories) { category ->
                WarpText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(category) }
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
