package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.schibsted.nmp.warp.components.WarpDivider
import com.schibsted.nmp.warp.components.WarpSearchBar
import com.schibsted.nmp.warp.components.WarpSearchBarAction
import com.schibsted.nmp.warp.components.WarpSearchBarButton
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions

@Composable
fun WarpSearchBarDemoScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpSearchBar",
        onUp = onUp
    ) {
        WarpSearchBarDemoScreenContent(onUp = onUp)
    }
}

@Composable
fun WarpSearchBarDemoScreenContent(onUp: () -> Unit) {
    val interactiveState = remember { TextFieldState("") }
    val leadingActionState = remember { TextFieldState("") }
    val secondaryActionState = remember { TextFieldState("") }
    var filterApplied by remember { mutableStateOf(false) }
    var buttonQuery by remember { mutableStateOf("iPhone 15 Pro") }
    var buttonTapCount by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(dimensions.space2)
    ) {
        SearchBarDemoSection(
            title = "Interactive search bar",
            description = "Type to update the text below. The clear button appears once there is text and actually empties the field."
        ) {
            WarpSearchBar(
                textFieldState = interactiveState,
                clearContentDescription = "Clear search",
                hint = "Search here",
                onClearClick = { interactiveState.clearText() }
            )
            WarpText(
                text = "Current text: \"${interactiveState.text}\"",
                style = WarpTextStyle.Body,
                color = colors.text.subtle,
                modifier = Modifier.padding(top = dimensions.space1)
            )
        }

        WarpDivider(modifier = Modifier.padding(vertical = dimensions.space3))

        SearchBarDemoSection(
            title = "Leading action (back arrow)",
            description = "Warp's search bar hardcodes a leading magnifier icon. leadingAction lets a caller replace it, e.g. with a back arrow to exit search."
        ) {
            WarpSearchBar(
                textFieldState = leadingActionState,
                clearContentDescription = "Clear search",
                hint = "Search here",
                leadingAction = WarpSearchBarAction(
                    icon = icons.arrowLeft,
                    contentDescription = "Back",
                    onClick = onUp
                ),
                onClearClick = { leadingActionState.clearText() }
            )
        }

        WarpDivider(modifier = Modifier.padding(vertical = dimensions.space3))

        SearchBarDemoSection(
            title = "Secondary action (filter)",
            description = "secondaryAction adds a trailing icon action, e.g. to open a filter sheet."
        ) {
            WarpSearchBar(
                textFieldState = secondaryActionState,
                clearContentDescription = "Clear search",
                hint = "Search here",
                secondaryAction = WarpSearchBarAction(
                    icon = icons.filter,
                    contentDescription = "Filter results",
                    onClick = { filterApplied = !filterApplied }
                ),
                onClearClick = { secondaryActionState.clearText() }
            )
            WarpText(
                text = if (filterApplied) "Filters: applied" else "Filters: none",
                style = WarpTextStyle.Body,
                color = colors.text.subtle,
                modifier = Modifier.padding(top = dimensions.space1)
            )
        }

        WarpDivider(modifier = Modifier.padding(vertical = dimensions.space3))

        SearchBarDemoSection(
            title = "Read-only button bar",
            description = "WarpSearchBarButton looks like a search bar but is not editable in place. Tap it to simulate navigating to a full search screen. Clear resets the preview query."
        ) {
            WarpSearchBarButton(
                text = buttonQuery,
                clearContentDescription = "Clear search",
                hint = "Search in Recommerce",
                onClick = { buttonTapCount += 1 },
                onClearClick = { buttonQuery = "" }
            )
            WarpText(
                text = "Tapped $buttonTapCount time(s) to open full search",
                style = WarpTextStyle.Body,
                color = colors.text.subtle,
                modifier = Modifier.padding(top = dimensions.space1)
            )
        }
    }
}

@Composable
private fun SearchBarDemoSection(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        WarpText(
            text = title,
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(bottom = dimensions.space1)
        )
        WarpText(
            text = description,
            style = WarpTextStyle.Body,
            color = colors.text.subtle,
            modifier = Modifier.padding(bottom = dimensions.space2)
        )
        content()
    }
}

@Composable
@Preview
fun WarpSearchBarDemoScreenPreview() {
    WarpSearchBarDemoScreenContent(onUp = {})
}
