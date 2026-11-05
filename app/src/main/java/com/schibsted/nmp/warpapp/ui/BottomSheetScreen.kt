package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.res.painterResource
import com.schibsted.nmp.warpapp.R
import com.schibsted.nmp.warp.components.WarpBottomSheet
import com.schibsted.nmp.warp.components.WarpBottomSheetDefaults
import com.schibsted.nmp.warp.components.WarpBottomSheetHeader
import com.schibsted.nmp.warp.components.WarpBottomSheetHeaderIcon
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpDivider
import com.schibsted.nmp.warp.components.WarpIcon
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
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

private enum class SampleSheet { Categories, CustomHeader }

private enum class HeaderSample(val label: String) {
    Title("Title"),
    TitleAndClose("Title and close button"),
    CloseOnly("Close button only"),
    IconAndTitle("Icon and title"),
    IconTitleAndClose("Icon, title and close button"),
    ImageTitleAndClose("Image, title and close button"),
    IconOnly("Icon only"),
    LongTitle("Long title and close button"),
    LongTitleWithIcon("Long title, icon and close button"),
    VeryLongTitleWithIcon("Very long title, icon and close button"),
    Empty("Empty header"),
}

private const val LONG_TITLE = "What will we use the information you share with us for?"
private const val VERY_LONG_TITLE =
    "We use the information to verify your identity, prevent fraud and follow financial regulations"

@Composable
private fun HeaderSample.header(): WarpBottomSheetHeader {
    val sparkles = WarpBottomSheetHeaderIcon.Icon(icons.sparkles, color = colors.icon.primary)
    return when (this) {
        HeaderSample.Title -> WarpBottomSheetHeader(title = "Sort by")
        HeaderSample.TitleAndClose -> WarpBottomSheetHeader(title = "Sort by", showCloseButton = true)
        HeaderSample.CloseOnly -> WarpBottomSheetHeader(showCloseButton = true)
        HeaderSample.IconAndTitle -> WarpBottomSheetHeader(title = "About the short version", icon = sparkles)
        HeaderSample.IconTitleAndClose -> WarpBottomSheetHeader(
            title = "About the short version",
            showCloseButton = true,
            icon = sparkles,
        )
        HeaderSample.ImageTitleAndClose -> WarpBottomSheetHeader(
            title = "Verify with your bank",
            showCloseButton = true,
            icon = WarpBottomSheetHeaderIcon.Image(painterResource(R.drawable.blocket_badge)),
        )
        HeaderSample.IconOnly -> WarpBottomSheetHeader(icon = sparkles)
        HeaderSample.LongTitle -> WarpBottomSheetHeader(title = LONG_TITLE, showCloseButton = true)
        HeaderSample.LongTitleWithIcon -> WarpBottomSheetHeader(
            title = LONG_TITLE,
            showCloseButton = true,
            icon = WarpBottomSheetHeaderIcon.Icon(icons.info),
        )
        HeaderSample.VeryLongTitleWithIcon -> WarpBottomSheetHeader(
            title = VERY_LONG_TITLE,
            showCloseButton = true,
            icon = WarpBottomSheetHeaderIcon.Icon(icons.info),
        )
        HeaderSample.Empty -> WarpBottomSheetHeader.empty()
    }
}

@Composable
fun BottomSheetScreenContent() {
    var openSheet by rememberSaveable { mutableStateOf<SampleSheet?>(null) }
    var openHeaderSample by rememberSaveable { mutableStateOf<HeaderSample?>(null) }
    var selectedCategory by rememberSaveable { mutableStateOf<String?>(null) }
    val closeSheet = { openSheet = null }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(dimensions.space2),
        verticalArrangement = Arrangement.spacedBy(dimensions.space2),
    ) {
        WarpText(text = "Header variants", style = WarpTextStyle.Title4)
        HeaderSample.entries.forEach { sample ->
            SheetButton(sample.label, style = WarpButtonStyle.Secondary) { openHeaderSample = sample }
        }
        WarpText(text = "In use", style = WarpTextStyle.Title4)
        SheetButton("Select a category") { openSheet = SampleSheet.Categories }
        selectedCategory?.let {
            WarpText(text = "Selected: $it", style = WarpTextStyle.Body)
        }
        SheetButton("Custom header") { openSheet = SampleSheet.CustomHeader }
    }

    openHeaderSample?.let { sample ->
        HeaderSampleSheet(header = sample.header(), onDismissRequest = { openHeaderSample = null })
    }

    when (openSheet) {
        SampleSheet.Categories -> CategoriesSheet(
            onSelect = { selectedCategory = it },
            onDismissRequest = closeSheet,
        )

        SampleSheet.CustomHeader -> CustomHeaderSheet(onDismissRequest = closeSheet)

        null -> Unit
    }
}

@Composable
private fun HeaderSampleSheet(header: WarpBottomSheetHeader, onDismissRequest: () -> Unit) {
    WarpBottomSheet(onDismissRequest = onDismissRequest, header = header) { dismiss ->
        Column(
            modifier = Modifier.padding(
                horizontal = WarpBottomSheetDefaults.contentHorizontalPadding,
                vertical = dimensions.space2,
            ),
        ) {
            WarpText(
                text = "Sheet content lines up with the header's title, and starts below the header's last line.",
                style = WarpTextStyle.Body,
            )
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
}

@Composable
private fun SheetButton(text: String, style: WarpButtonStyle = WarpButtonStyle.Primary, onClick: () -> Unit) {
    WarpButton(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        onClick = onClick,
        style = style,
    )
}

/**
 * A sheet whose header needs more than a title, so it passes an empty Warp header and draws its own.
 * Prefer [WarpBottomSheetHeader]'s title and close button whenever they are enough.
 */
@Composable
private fun CustomHeaderSheet(onDismissRequest: () -> Unit) {
    WarpBottomSheet(onDismissRequest = onDismissRequest, header = WarpBottomSheetHeader.empty()) { dismiss ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = WarpBottomSheetDefaults.contentHorizontalPadding, end = dimensions.space05),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WarpText(text = "Saved search", style = WarpTextStyle.Title3, modifier = Modifier.weight(1f))
            WarpButton(text = "Share", onClick = {}, style = WarpButtonStyle.Quiet)
            IconButton(onClick = dismiss) { WarpIcon(icon = icons.close) }
        }
        WarpText(
            text = "The content draws its own header, here with a share action, and closes the sheet with dismiss.",
            style = WarpTextStyle.Body,
            modifier = Modifier.padding(
                horizontal = WarpBottomSheetDefaults.contentHorizontalPadding,
                vertical = dimensions.space2,
            ),
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
