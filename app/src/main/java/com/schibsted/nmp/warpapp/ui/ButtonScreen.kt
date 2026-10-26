@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonSize
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpIconButton
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpResources.icons
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

private val newStyles = listOf(
    WarpButtonStyle.Prominent,
    WarpButtonStyle.Tonal,
    WarpButtonStyle.Text,
    WarpButtonStyle.Flat,
    WarpButtonStyle.Elevated,
    WarpButtonStyle.Static,
    WarpButtonStyle.InvertedStatic,
    WarpButtonStyle.Destructive,
    WarpButtonStyle.DestructiveText,
)

@Suppress("DEPRECATION")
private val deprecatedStyles = listOf(
    WarpButtonStyle.Primary,
    WarpButtonStyle.Secondary,
    WarpButtonStyle.Quiet,
    WarpButtonStyle.Negative,
    WarpButtonStyle.NegativeQuiet,
    WarpButtonStyle.Utility,
    WarpButtonStyle.UtilityQuiet,
    WarpButtonStyle.UtilityOverlay,
)

// Stand-in for the photo or video the static styles sit on.
private val ImageStandIn = Color(0xFF6E6E78)

@Composable
fun ButtonScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Buttons", onUp = onUp) {
        ButtonScreenContent()
    }
}

@Composable
private fun SectionTitle(text: String) {
    WarpText(
        text = text,
        modifier = Modifier.padding(
            top = dimensions.space2,
            bottom = dimensions.space05,
            start = dimensions.space2,
            end = dimensions.space2
        )
    )
}

@Composable
private fun NewButtonsSection() {
    var size by rememberSaveable { mutableStateOf(WarpButtonSize.Medium) }
    SectionTitle("Size")
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensions.space1),
        modifier = Modifier.padding(horizontal = dimensions.space2)
    ) {
        WarpButtonSize.values().forEach { option ->
            WarpButton(
                text = option.name,
                onClick = { size = option },
                style = if (option == size) WarpButtonStyle.Prominent else WarpButtonStyle.Tonal,
                size = WarpButtonSize.Small
            )
        }
    }
    for (style in newStyles) {
        SectionTitle(style.name)
        val onImage = style == WarpButtonStyle.Static || style == WarpButtonStyle.InvertedStatic
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .then(if (onImage) Modifier.background(ImageStandIn) else Modifier)
                .padding(horizontal = dimensions.space2, vertical = dimensions.space1),
            verticalArrangement = Arrangement.spacedBy(dimensions.space1)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensions.space1),
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                WarpButton(text = "Click me", onClick = {}, style = style, size = size)
                WarpButton(text = "Loading", onClick = {}, style = style, size = size, loading = true)
                WarpButton(text = "Disabled", onClick = {}, style = style, size = size, enabled = false)
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensions.space1),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                WarpIconButton(icon = icons.share, contentDescription = "Share", onClick = {}, style = style, size = size)
                WarpIconButton(icon = icons.share, contentDescription = "Share", onClick = {}, style = style, size = size, loading = true)
                WarpIconButton(icon = icons.share, contentDescription = "Share", onClick = {}, style = style, size = size, enabled = false)
                WarpButton(
                    text = "With icon",
                    onClick = {},
                    style = style,
                    size = size,
                    leadingIcon = R.drawable.warp_animalpaw
                )
            }
        }
    }
}

@Suppress("DEPRECATION")
@Composable
private fun ButtonScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(vertical = dimensions.space2)
    ) {
        NewButtonsSection()
        SectionTitle("Deprecated styles")
        for (style in deprecatedStyles) {
            WarpText(
                text = style.name,
                modifier = Modifier.padding(
                    top = dimensions.space2,
                    bottom = dimensions.space05,
                    start = dimensions.space2,
                    end = dimensions.space2
                )
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensions.space2)
            ) {
                WarpButton(onClick = { }, style = style, text = "Click me")
                WarpButton(onClick = { }, style = style, text = "Loading", loading = true)
                WarpButton(
                    onClick = { },
                    style = style,
                    text = "Disabled",
                    enabled = false
                )

            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensions.space2)
            ) {
                WarpButton(onClick = { /*TODO*/ }, style = style, text = "Small", small = true)
                WarpButton(
                    onClick = { /*TODO*/ },
                    style = style,
                    text = "Small",
                    loading = true,
                    small = true
                )
                WarpButton(
                    onClick = { /*TODO*/ },
                    style = style,
                    text = "Small",
                    enabled = false,
                    small = true
                )

            }
        }
        WarpText(
            text = "Full width",
            modifier = Modifier.padding(
                top = dimensions.space2,
                bottom = dimensions.space05,
                start = dimensions.space2,
                end = dimensions.space2
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.space2)
        ) {
            WarpButton(
                onClick = { /*TODO*/ },
                style = WarpButtonStyle.Primary,
                text = "Full width primary",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        WarpText(
            text = "With optional leading icon",
            modifier = Modifier.padding(
                top = dimensions.space2,
                bottom = dimensions.space05,
                start = dimensions.space2,
                end = dimensions.space2
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.space2)
        ) {
            WarpButton(
                onClick = { /*TODO*/ },
                style = WarpButtonStyle.Secondary,
                text = "With icon",
                leadingIcon = com.google.android.material.R.drawable.ic_clock_black_24dp,
                leadingIconContentDescr = "Clock icon",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(dimensions.space2)
        ) {
            WarpButton(
                onClick = { /*TODO*/ },
                style = WarpButtonStyle.Secondary,
                text = "Small",
                leadingIcon = com.google.android.material.R.drawable.ic_clock_black_24dp,
                leadingIconContentDescr = "Clock icon",
                small = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        WarpText(
            text = "With optional trailing icon",
            modifier = Modifier.padding(
                top = dimensions.space2,
                bottom = dimensions.space05,
                start = dimensions.space2,
                end = dimensions.space2
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.space2)
        ) {
            WarpButton(
                onClick = { /*TODO*/ },
                style = WarpButtonStyle.Negative,
                text = "With icon",
                trailingIcon = com.google.android.material.R.drawable.ic_clock_black_24dp,
                trailingIconContentDescr = "Clear icon",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row(
            horizontalArrangement = Arrangement.Start, modifier = Modifier
                .padding(dimensions.space2)
        ) {
            WarpButton(
                onClick = { /*TODO*/ },
                style = WarpButtonStyle.Negative,
                text = "Small",
                trailingIcon = com.google.android.material.R.drawable.ic_clock_black_24dp,
                trailingIconContentDescr = "Clear icon",
                small = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        WarpText(
            text = "Focused",
            modifier = Modifier.padding(
                top = dimensions.space2,
                bottom = dimensions.space05,
                start = dimensions.space2,
                end = dimensions.space2
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.space2)
        ) {
            val focus = remember { FocusRequester() }

            WarpButton(
                modifier = Modifier.focusRequester(focus),
                onClick = { focus.requestFocus() },
                style = WarpButtonStyle.Quiet,
                text = "Click to focus",
                )
        }
        WarpText(
            text = "With multiple lines",
            modifier = Modifier.padding(
                top = dimensions.space2,
                bottom = dimensions.space05,
                start = dimensions.space2,
                end = dimensions.space2
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensions.space2)
        ) {
            val focus = remember { FocusRequester() }

            WarpButton(
                modifier = Modifier.focusRequester(focus),
                onClick = { focus.requestFocus() },
                text = "Explore All Features and Compare Plans Before You Choose",
                maxLines = 2
            )
        }
    }
}


@Preview
@Composable
fun ButtonPreviews(
    @PreviewParameter(FlavorPreviewProvider::class) flavor: String
) {
    BrandTheme(flavor = flavor).invoke {
        ButtonScreenContent()
    }
}