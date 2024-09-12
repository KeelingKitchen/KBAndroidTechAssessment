package com.example.kbandroidtechassessment.ui.screen.mainscreen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.kbandroidtechassessment.data.Transaction
import com.example.kbandroidtechassessment.ui.component.FilterDateRangeButton
import com.example.kbandroidtechassessment.ui.component.FilterResetButton
import com.example.kbandroidtechassessment.ui.component.TransactionItem
import com.example.kbandroidtechassessment.utils.extension.toCurrencyNZDString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    startingBalance: Double,
    viewModel: MainScreenViewModel,
) {
    // Collect state from ViewModel
    val selectedStartDate by viewModel.selectedStartDate.collectAsState()
    val selectedEndDate by viewModel.selectedEndDate.collectAsState()
    val transactions by viewModel.transactions.collectAsState()

    val filteredTransactions = viewModel.getPresentedTransactions()

    Log.v(TAG, "selectedStartDate: $selectedStartDate")
    Log.v(TAG, "selectedEndDate: $selectedEndDate")
    Log.d(TAG, "transactions.size: ${transactions.size}")
    Log.d(TAG, "filteredTransactions.size: ${filteredTransactions.size}")

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Travel Savings Account")
                },
                actions = {
                    if (selectedStartDate != null || selectedEndDate != null) {
                        FilterResetButton(
                            onClick = viewModel::resetDateRange,
                        )
                    }

                    FilterDateRangeButton { startDateMillis, endDateMillis ->
                        viewModel.setStartDate(startDateMillis)
                        viewModel.setEndDate(endDateMillis)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Available Balance:  ",
                    fontWeight = FontWeight.W300,
                    fontSize = TextUnit(16f, TextUnitType.Sp),
                )
                Text(
                    text = viewModel.calculateBalance(startingBalance).toCurrencyNZDString(),
                    fontWeight = FontWeight.W600,
                    fontSize = TextUnit(24f, TextUnitType.Sp),
                )
            }
            HorizontalDivider()
            LazyColumn {
                items(filteredTransactions) { transaction ->
                    TransactionItem(transaction = transaction)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {
    MainScreenContent(
        startingBalance = 5000.00,
        viewModel = MainScreenViewModel().apply {
            setTransactions(
                listOf(
                    Transaction("2024-09-22", "Restaurant", -35.00),
                    Transaction("2024-09-24", "Car Repair", -150.00),
                    Transaction("2024-09-11", "Utilities", -150.00),
                )
            )
        }
    )
}