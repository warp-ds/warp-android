@file:OptIn(ExperimentalMaterial3Api::class)

package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import com.schibsted.nmp.warp.utils.FlavorPreviewProvider
import com.schibsted.nmp.warpapp.BrandTheme

@Composable
fun ButtonScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "Buttons", onUp = onUp) {
        ButtonScreenContent()
    }
}

@Composable
private fun ButtonScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(vertical = dimensions.space2)
    ) {
        for (style in WarpButtonStyle.values()) {
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