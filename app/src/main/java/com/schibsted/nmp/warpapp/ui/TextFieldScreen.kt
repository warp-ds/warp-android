package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextField
import com.schibsted.nmp.warp.components.WarpTextStyle

@Composable
fun TextFieldScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpTextField", onUp = onUp) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            var text by remember { mutableStateOf("")}
            var showError by remember { mutableStateOf(false)}
            var showLabel by remember { mutableStateOf(false)}
            var showHint by remember { mutableStateOf(false)}
            var showPlaceholder by remember { mutableStateOf(false)}
            var showLeadingIcon by remember { mutableStateOf(false)}
            var showTrailingIcon by remember { mutableStateOf(false)}
            var readOnly by remember { mutableStateOf(false) }
            var disabled by remember { mutableStateOf(false) }
            val label = "This is the label"
            val helpText = "This is the supporting text"
            val hintText = "This is the placeholder"
            val trailingIcon: @Composable () -> Unit = { Icon(Icons.Filled.DateRange, contentDescription = "Content description for the trailing icon") }
            val leadingIcon: @Composable () -> Unit = { Icon(Icons.Filled.Email, contentDescription = "Content description for the leading icon") }


            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText("Standard with hint", style = WarpTextStyle.Body, modifier = Modifier.padding(vertical = 16.dp))
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    helpText = "A hint",
                    label = "Standard",
                    placeholderText = hintText.takeIf { showPlaceholder },
                    )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText("Optional with placeholder", style = WarpTextStyle.Body, modifier = Modifier.padding(vertical = 16.dp))
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = "A label",
                    optionalLabel = "Optional",
                    placeholderText = "A placeholder",
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText("Disabled", style = WarpTextStyle.Body, modifier = Modifier.padding(vertical = 16.dp))
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = "A optional label",
                    enabled = false
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText("Read-only", style = WarpTextStyle.Body, modifier = Modifier.padding(vertical = 16.dp))
                WarpTextField(
                    value = "Read only",
                    onValueChange = { text = it },
                    label = "A optional label",
                    readOnly = true
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText("Invalid", style = WarpTextStyle.Body, modifier = Modifier.padding(vertical = 16.dp))
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = "A optional label",
                    helpText = "Error!",
                    isError = true
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText("Icons", style = WarpTextStyle.Body, modifier = Modifier.padding(vertical = 16.dp))
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = showLeadingIcon, onCheckedChange = {showLeadingIcon = it})
                    WarpText("Show leading icon")
                }
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = showTrailingIcon, onCheckedChange = {showTrailingIcon = it})
                    WarpText("Show trailing icon")
                }
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    isError = showError,
                    label = "With icons",
                    leadingIcon = leadingIcon.takeIf { showLeadingIcon },
                    trailingIcon = trailingIcon.takeIf { showTrailingIcon },

                    )
            }
        }
    }
}

