package com.schibsted.nmp.warpapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.schibsted.nmp.warp.components.WarpButton
import com.schibsted.nmp.warp.components.WarpButtonStyle
import com.schibsted.nmp.warp.components.WarpDatePicker
import com.schibsted.nmp.warp.components.WarpDatePickerType
import com.schibsted.nmp.warp.components.WarpText
import com.schibsted.nmp.warp.components.WarpTextStyle
import com.schibsted.nmp.warp.theme.WarpTheme.dimensions
import java.util.Locale

@Composable
fun DatePickerScreen(onUp: () -> Unit) {
    DetailsScaffold(
        title = "WarpDatePicker",
        onUp = onUp
    ) {
        DatePickerScreenContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerScreenContent() {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WarpText(text = "Inline type", style = WarpTextStyle.Title3)

        var dateinMillis by remember { mutableStateOf(System.currentTimeMillis()) }
        val formatter = remember { DatePickerDefaults.dateFormatter() }
        var date = formatter.formatDate(dateinMillis, Locale.getDefault())
        var dateString by remember { mutableStateOf(date) }
        WarpDatePicker(
            type = WarpDatePickerType.INLINE,
            onDateSelected = {
                dateinMillis = it
                dateString = formatter.formatDate(dateinMillis, Locale.getDefault())
            }
        )
        WarpText(text = "Selected date: ${dateString}")

        WarpText(
            text = "Dialog type",
            style = WarpTextStyle.Title3,
            modifier = Modifier.padding(top = dimensions.space3)
        )
        var showDialog by remember { mutableStateOf(false) }
        WarpButton(
            modifier = Modifier.padding(vertical = dimensions.space3),
            text = "Show date picker",
            style = WarpButtonStyle.Primary,
            onClick = { showDialog = true })
        if (showDialog) {
            WarpDatePicker(
                type = WarpDatePickerType.DIALOG,
                preselectedDateMillis = dateinMillis,
                onDateSelected = {
                    dateinMillis = it
                    dateString = formatter.formatDate(dateinMillis, Locale.getDefault())
                    showDialog = false
                },
                onDismiss = { showDialog = !showDialog }
            )
        }
        WarpText(text = "Selected date: ${dateString}")
    }
}
