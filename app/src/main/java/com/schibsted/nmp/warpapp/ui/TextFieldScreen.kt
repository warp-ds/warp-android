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
            var text by remember { mutableStateOf("") }
            var showError by remember { mutableStateOf(false) }
            var showLabel by remember { mutableStateOf(false) }
            var showHint by remember { mutableStateOf(false) }
            var showPlaceholder by remember { mutableStateOf(false) }
            var showLeadingIcon by remember { mutableStateOf(false) }
            var showTrailingIcon by remember { mutableStateOf(false) }
            var readOnly by remember { mutableStateOf(false) }
            var disabled by remember { mutableStateOf(false) }
            val label = "This is the label"
            val helpText = "This is the supporting text"
            val placeholderText = "This is the placeholder"
            val trailingIcon: @Composable () -> Unit = {
                Icon(
                    Icons.Filled.DateRange,
                    contentDescription = "Content description for the trailing icon"
                )
            }
            val leadingIcon: @Composable () -> Unit = {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = "Content description for the leading icon"
                )
            }


            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "Text field only",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholderText = "Placeholder",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "With a label and a placeholder",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = "A label",
                    placeholderText = "A placeholder",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "With a label and a placeholder with min lines of 3",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholderText = "A placeholder",
                    label = "A label",
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "With a label, optional label, placeholder and help text",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = "A label",
                    optionalLabel = "Optional",
                    placeholderText = "A placeholder",
                    helpText = "Help text"
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "Disabled state",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = "A label",
                    enabled = false,
                    helpText = "Disabled"
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "Read-only state",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                WarpTextField(
                    value = "Read only value",
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = "A label",
                    readOnly = true
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "Error state",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = "A label",
                    helpText = "Error!",
                    isError = true
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "With prefix",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = "A label",
                    prefixText = "kr"
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "With suffix",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = "A  label",
                    suffixText = "kronor"
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "Icons",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(checked = showLeadingIcon, onCheckedChange = { showLeadingIcon = it })
                    WarpText("Show leading icon")
                }
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = showTrailingIcon,
                        onCheckedChange = { showTrailingIcon = it })
                    WarpText("Show trailing icon")
                }
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    isError = showError,
                    label = "With icons",
                    helpText = "Helping text",
                    leadingIcon = leadingIcon.takeIf { showLeadingIcon },
                    trailingIcon = trailingIcon.takeIf { showTrailingIcon },
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                WarpText(
                    "Text style",
                    style = WarpTextStyle.Body,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                WarpTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    isError = showError,
                    label = "With title1 as text style",
                    helpText = "Helping text",
                    leadingIcon = leadingIcon.takeIf { showLeadingIcon },
                    trailingIcon = trailingIcon.takeIf { showTrailingIcon },
                    textStyle = WarpTextStyle.Title1,
                )
            }
        }
    }
}

