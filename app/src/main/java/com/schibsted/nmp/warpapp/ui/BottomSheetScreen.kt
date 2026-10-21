package com.schibsted.nmp.warpapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.DiscouragedWarpApi
import com.schibsted.nmp.warp.components.SearchConfiguration
import com.schibsted.nmp.warp.components.TabConfiguration
import com.schibsted.nmp.warp.components.TabData
import com.schibsted.nmp.warp.components.TopAppBarAction
import com.schibsted.nmp.warp.components.TopAppBarConfiguration
import com.schibsted.nmp.warp.components.TopAppBarMenuItem
import com.schibsted.nmp.warp.components.TopAppBarNavigation
import com.schibsted.nmp.warp.components.WarpBottomSheet
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpDivider
import com.schibsted.nmp.warp.components.WarpLockableBottomSheet
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpResources.icons
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

private enum class SampleSheet { Categories, TitleAndAction, Action, DestructiveAction, Back, FavouriteAndMenu, SearchAndTabs, NotDismissible }

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
        SheetButton("Favourite and menu") { openSheet = SampleSheet.FavouriteAndMenu }
        SheetButton("Search and tabs") { openSheet = SampleSheet.SearchAndTabs }
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
            topAppBar = TopAppBarConfiguration(title = "Sort by"),
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
            topAppBar = TopAppBarConfiguration(
                title = "Filters",
                actions = listOf(TopAppBarAction.Text("Reset") {}),
            ),
        ) {
            WarpText(
                text = "Content below a top app bar with a quiet text action.",
                style = WarpTextStyle.Body,
                modifier = Modifier.padding(dimensions.space2),
            )
        }

        SampleSheet.DestructiveAction -> WarpBottomSheet(
            onDismissRequest = closeSheet,
            topAppBar = TopAppBarConfiguration(
                title = "Edit education",
                actions = listOf(TopAppBarAction.Text("Delete", destructive = true) {}),
            ),
        ) {
            WarpText(
                text = "Content below a top app bar with a quiet destructive action.",
                style = WarpTextStyle.Body,
                modifier = Modifier.padding(dimensions.space2),
            )
        }

        SampleSheet.Back -> TwoStepSheet(onDismissRequest = closeSheet)

        SampleSheet.FavouriteAndMenu -> FavouriteAndMenuSheet(onDismissRequest = closeSheet)

        SampleSheet.SearchAndTabs -> SearchAndTabsSheet(onDismissRequest = closeSheet)

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
        topAppBar = TopAppBarConfiguration(
            title = "Step $step of 2",
            navigation = if (step == 2) TopAppBarNavigation.Back { step = 1 } else null,
        ),
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
        topAppBar = TopAppBarConfiguration(title = "Save changes"),
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
private fun FavouriteAndMenuSheet(onDismissRequest: () -> Unit) {
    var favourite by rememberSaveable { mutableStateOf(false) }
    var newestFirst by rememberSaveable { mutableStateOf(true) }
    WarpBottomSheet(
        onDismissRequest = onDismissRequest,
        topAppBar = TopAppBarConfiguration(
            title = "Vintage armchair",
            subtitle = "Oslo",
            actions = listOf(
                TopAppBarAction.Icon(icon = icons.shareAndroid, label = "Share") {},
                TopAppBarAction.Toggle(
                    icon = icons.heart,
                    checkedIcon = icons.heartFilled,
                    checked = favourite,
                    label = "Favourite",
                    onCheckedChange = { favourite = it },
                ),
                TopAppBarAction.Menu(
                    listOf(
                        TopAppBarMenuItem("Newest first", checked = newestFirst) { newestFirst = true },
                        TopAppBarMenuItem("Oldest first", checked = !newestFirst) { newestFirst = false },
                    )
                ),
            ),
        ),
    ) {
        WarpText(
            text = if (favourite) "Saved as a favourite." else "Not a favourite yet.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(dimensions.space2),
        )
        WarpText(
            text = if (newestFirst) "Sorted newest first." else "Sorted oldest first.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(horizontal = dimensions.space2),
        )
    }
}

@Composable
private fun SearchAndTabsSheet(onDismissRequest: () -> Unit) {
    val searchState = remember { TextFieldState("") }
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    WarpBottomSheet(
        onDismissRequest = onDismissRequest,
        skipPartiallyExpanded = true,
        topAppBar = TopAppBarConfiguration(
            title = "Categories",
            search = SearchConfiguration(
                state = searchState,
                onSearch = {},
                hint = "Search categories",
                collapsible = true,
            ),
            tabs = TabConfiguration(
                tabs = listOf(TabData("All", "all"), TabData("Popular", "popular")),
                selectedIndex = selectedTab,
                onTabSelected = { selectedTab = it },
            ),
        ),
    ) {
        LazyColumn(modifier = Modifier.padding(horizontal = dimensions.space3)) {
            items(sampleCategories) { category ->
                WarpText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensions.space2),
                    text = category,
                    style = WarpTextStyle.Body,
                )
                WarpDivider()
            }
        }
    }
}

@Composable
private fun CategoriesSheet(onSelect: (String) -> Unit, onDismissRequest: () -> Unit) {
    WarpBottomSheet(
        onDismissRequest = onDismissRequest,
        topAppBar = TopAppBarConfiguration(title = "Categories"),
    ) {
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
