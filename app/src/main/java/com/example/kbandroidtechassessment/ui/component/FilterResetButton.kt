package com.example.kbandroidtechassessment.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FilterListOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FilterResetButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.FilterListOff,
            contentDescription = "Reset Filter"
        )
    }
}


@Preview
@Composable
fun FilterResetButtonPreview() {
    FilterResetButton(
        onClick = { }
    )
}