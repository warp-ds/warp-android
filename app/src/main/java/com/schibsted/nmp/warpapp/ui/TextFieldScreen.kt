package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.components.WarpTextField

@Composable
fun TextFieldScreen(onUp: () -> Unit) {
    DetailsScaffold(title = "WarpTextField", onUp = onUp) {
        Column(
            modifier = Modifier.padding(16.dp)
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
            WarpTextField(
                value = text,
                onValueChange = { text = it },
                isError = showError,
                label = label.takeIf { showLabel },
                helpText = helpText.takeIf { showHint },
                hintText = hintText.takeIf { showPlaceholder },
                leadingIcon = leadingIcon.takeIf { showLeadingIcon },
                trailingIcon = trailingIcon.takeIf { showTrailingIcon },
                readOnly = readOnly,
                enabled = !disabled,

            )

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = showError, onCheckedChange = {showError = it})
                Text("Show error")
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = showLabel, onCheckedChange = {showLabel = it})
                Text("Show label")
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = showHint, onCheckedChange = {showHint = it})
                Text("Show supporting text")
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = showPlaceholder, onCheckedChange = {showPlaceholder = it})
                Text("Show placeholder")
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = showLeadingIcon, onCheckedChange = {showLeadingIcon = it})
                Text("Show leading icon")
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = showTrailingIcon, onCheckedChange = {showTrailingIcon = it})
                Text("Show trailing icon")
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = disabled, onCheckedChange = {disabled = it})
                Text("Disabled")
            }
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = readOnly, onCheckedChange = {readOnly = it})
                Text("Set read only")
            }

        }
    }
}

