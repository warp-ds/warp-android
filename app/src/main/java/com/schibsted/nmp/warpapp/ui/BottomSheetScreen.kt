package com.schibsted.nmp.warpapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.BottomSheetAction
import com.schibsted.nmp.warp.components.BottomSheetBackButton
import com.schibsted.nmp.warp.components.DiscouragedWarpApi
import com.schibsted.nmp.warp.components.WarpBottomSheet
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpDivider
import com.schibsted.nmp.warp.components.WarpLockableBottomSheet
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun BottomSheetScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpBottomSheet",
        onUp = onUp
    ) {
        BottomSheetScreenContent()
    }
}

private enum class SampleSheet { Categories, TitleAndAction, Action, DestructiveAction, Back, NotDismissible }

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
        SheetButton("Title and action") { openSheet = SampleSheet.TitleAndAction }
        SheetButton("Action") { openSheet = SampleSheet.Action }
        SheetButton("Destructive action") { openSheet = SampleSheet.DestructiveAction }
        SheetButton("Back button") { openSheet = SampleSheet.Back }
        SheetButton("Not dismissible while saving") { openSheet = SampleSheet.NotDismissible }
    }

    when (openSheet) {
        SampleSheet.Categories -> CategoriesSheet(
            onSelect = {
                selectedCategory = it
                closeSheet()
            },
            onDismissRequest = closeSheet,
        )

        SampleSheet.TitleAndAction -> WarpBottomSheet(
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

        SampleSheet.Action -> WarpBottomSheet(
            onDismissRequest = closeSheet,
            title = "Filters",
            action = BottomSheetAction("Reset") {},
        ) {
            WarpText(
                text = "Content below a header with a quiet text action.",
                style = WarpTextStyle.Body,
                modifier = Modifier.padding(dimensions.space2),
            )
        }

        SampleSheet.DestructiveAction -> WarpBottomSheet(
            onDismissRequest = closeSheet,
            title = "Edit education",
            action = BottomSheetAction("Delete", destructive = true) {},
        ) {
            WarpText(
                text = "Content below a header with a quiet destructive action.",
                style = WarpTextStyle.Body,
                modifier = Modifier.padding(dimensions.space2),
            )
        }

        SampleSheet.Back -> TwoStepSheet(onDismissRequest = closeSheet)

        SampleSheet.NotDismissible -> SavingSheet(onDismissRequest = closeSheet)

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
        backButton = if (step == 2) BottomSheetBackButton { step = 1 } else null,
        title = "Step $step of 2",
    ) { dismiss ->
        BackHandler(enabled = step == 2) { step = 1 }
        Column(modifier = Modifier.padding(dimensions.space2)) {
            WarpText(
                text = if (step == 1) {
                    "Close, or system back, dismisses the sheet."
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

@OptIn(DiscouragedWarpApi::class)
@Composable
private fun SavingSheet(onDismissRequest: () -> Unit) {
    var saving by rememberSaveable { mutableStateOf(false) }
    WarpLockableBottomSheet(
        onDismissRequest = onDismissRequest,
        locked = saving,
        title = "Save changes",
    ) { dismiss ->
        if (saving) {
            LaunchedEffect(Unit) {
                delay(2000.milliseconds)
                dismiss()
            }
        }
        Column(modifier = Modifier.padding(dimensions.space2)) {
            WarpText(
                text = "The sheet can't be dismissed while saving.",
                style = WarpTextStyle.Body,
            )
            WarpButton(
                text = "Save",
                onClick = { saving = true },
                style = WarpButtonStyle.Primary,
                loading = saving,
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
        LazyColumn(modifier = Modifier.padding(horizontal = dimensions.space3)) {
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
