package com.schibsted.nmp.warp.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.schibsted.nmp.warp.R
import com.schibsted.nmp.warp.theme.WarpTheme.colors
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull

/**
 * A date picker in the warp design system. Uses default Locale
 * @param modifier The modifier to be applied to the date picker.
 * @param onDateSelected A callback that is invoked when a date is selected. Returns time in millis
 * @param onDismiss A callback that is invoked when the date picker is dismissed. Applies only to [WarpDatePickerType.DIALOG].
 * @param preselectedDateMillis The preselected date in milliseconds. Todays' date will be used if nothing is provided
 * @param type The type of the date picker. Defaults to [WarpDatePickerType.DIALOG].
 * @param selectableDates The selectable dates to be used. Defaults to [DatePickerDefaults.AllDates].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarpDatePicker(
    modifier: Modifier = Modifier,
    onDateSelected: (Long) -> Unit,
    onDismiss: (() -> Unit)? = null,
    preselectedDateMillis: Long? = null,
    type: WarpDatePickerType = WarpDatePickerType.DIALOG,
    selectableDates: SelectableDates = DatePickerDefaults.AllDates
) {
    var selectedDate by remember {
        mutableStateOf(
            preselectedDateMillis ?: System.currentTimeMillis()
        )
    }

    val datePickerState: DatePickerState = rememberDatePickerState(
        initialSelectedDateMillis = preselectedDateMillis ?: selectedDate,
        initialDisplayMode = DisplayMode.Picker,
        selectableDates = selectableDates
    )

    when (type) {
        WarpDatePickerType.DIALOG -> {
            DatePickerDialog(
                onDismissRequest = { onDismiss?.invoke() },
                confirmButton = {
                    WarpButton(style = WarpButtonStyle.Quiet, text = "OK", onClick = {
                        datePickerState.selectedDateMillis?.let {
                            onDateSelected(it)
                            selectedDate = it
                        }
                        onDismiss
                    })

                },
                dismissButton = {
                    WarpButton(
                        style = WarpButtonStyle.Quiet,
                        text = stringResource(id = R.string.cancel),
                        onClick = onDismiss ?: {})
                },
                colors = datePickerColors()
            ) {
                DatePickerView(datePickerState, modifier)
            }
        }

        WarpDatePickerType.INLINE -> {
            //Keep track of the value change
            LaunchedEffect(datePickerState) {
                snapshotFlow { datePickerState.selectedDateMillis }
                    .filterNotNull()
                    .distinctUntilChanged()
                    .collect { newSelectedTimestamp ->
                        onDateSelected(newSelectedTimestamp)
                        selectedDate = newSelectedTimestamp
                    }
            }
            DatePickerView(datePickerState, modifier)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DatePickerView(
    datePickerState: DatePickerState,
    modifier: Modifier
) {
    DatePicker(
        state = datePickerState,
        modifier = modifier,
        title = null,
        headline = null,
        showModeToggle = false,
        colors = datePickerColors()
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun datePickerColors(): DatePickerColors = DatePickerDefaults
    .colors(
        containerColor = colors.background.default,
        titleContentColor = colors.text.default,
        headlineContentColor = colors.text.default,
        weekdayContentColor = colors.text.default,
        subheadContentColor = colors.text.default,
        navigationContentColor = colors.text.default,
        yearContentColor = colors.text.default,
        dayContentColor = colors.text.default,
        disabledYearContentColor = colors.text.disabled,
        currentYearContentColor = colors.text.default,
        selectedDayContainerColor = colors.background.primary,
        selectedYearContentColor = colors.text.inverted,
        selectedYearContainerColor = colors.background.primary,
        selectedDayContentColor = colors.text.inverted,
        disabledSelectedYearContentColor = colors.text.disabled,
        disabledSelectedYearContainerColor = colors.background.disabled,
        disabledSelectedDayContentColor = colors.text.inverted,
        disabledSelectedDayContainerColor = colors.background.disabled,
        disabledDayContentColor = colors.text.disabled,
        todayContentColor = colors.text.default,
        todayDateBorderColor = colors.border.focus,
        dayInSelectionRangeContentColor = colors.text.default,
        dayInSelectionRangeContainerColor = colors.background.primary,
        dividerColor = colors.background.subtle
    )

enum class WarpDatePickerType {
    DIALOG,
    INLINE
}
