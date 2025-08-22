package com.schibsted.nmp.warp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun WarpDatePicker() {
    Locale.setDefault(Locale.GERMAN)
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
    DatePicker(state = datePickerState, modifier = Modifier.padding(4.dp),
        colors = DatePickerDefaults.colors(
            containerColor = colors.background.default,
            titleContentColor = colors.text.default,
            selectedDayContainerColor = colors.icon.primary,
        ))
    /**
     * Investigate typeface
     * Customizable stuff
     */

/*
        containerColor: Color = Color.Unspecified,
        titleContentColor: Color = Color.Unspecified,
        headlineContentColor: Color = Color.Unspecified,
        weekdayContentColor: Color = Color.Unspecified,
        subheadContentColor: Color = Color.Unspecified,
        navigationContentColor: Color = Color.Unspecified,
        yearContentColor: Color = Color.Unspecified,
        disabledYearContentColor: Color = Color.Unspecified,
        currentYearContentColor: Color = Color.Unspecified,
        selectedYearContentColor: Color = Color.Unspecified,
        disabledSelectedYearContentColor: Color = Color.Unspecified,
        selectedYearContainerColor: Color = Color.Unspecified,
        disabledSelectedYearContainerColor: Color = Color.Unspecified,
        dayContentColor: Color = Color.Unspecified,
        disabledDayContentColor: Color = Color.Unspecified,
        selectedDayContentColor: Color = Color.Unspecified,
        disabledSelectedDayContentColor: Color = Color.Unspecified,
        selectedDayContainerColor: Color = Color.Unspecified,
        disabledSelectedDayContainerColor: Color = Color.Unspecified,
        todayContentColor: Color = Color.Unspecified,
        todayDateBorderColor: Color = Color.Unspecified,
        dayInSelectionRangeContentColor: Color = Color.Unspecified,
        dayInSelectionRangeContainerColor: Color = Color.Unspecified,
        dividerColor: Color = Color.Unspecified,
        dateTextFieldColors: TextFieldColors? = null
 */
    Text(
        "Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}"
    )

}