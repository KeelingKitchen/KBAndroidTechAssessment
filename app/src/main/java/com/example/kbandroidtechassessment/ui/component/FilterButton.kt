package com.example.kbandroidtechassessment.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FilterDateRangeButton(
    onDateRangeSelected: (startDate: Long?, endDate: Long?) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }

    Column {
        // Filter button to trigger the date picker
        FilterButton(onClick = { showDatePicker = true })

        // Show DatePickerModal when button is clicked
        if (showDatePicker) {
            DateRangePickerModal(
                onDateRangeSelected = { dateRange ->
                    onDateRangeSelected(dateRange.first, dateRange.second)
                },
                onDismiss = {
                    showDatePicker = false
                }
            )
        }

    }
}

@Composable
fun FilterButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "Filter Date range"
        )
    }
}


@Preview
@Composable
fun FilterDateRangeButtonPreview() {
    FilterDateRangeButton(
        onDateRangeSelected = { _, _ -> }
    )
}