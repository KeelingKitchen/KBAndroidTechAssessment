package com.example.kbandroidtechassessment.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.kbandroidtechassessment.dto.Transaction
import com.example.kbandroidtechassessment.extension.TAG
import com.example.kbandroidtechassessment.extension.toCurrencyNZDString
import com.example.kbandroidtechassessment.ui.component.FilterDateRangeButton
import com.example.kbandroidtechassessment.ui.component.TransactionItem
import com.example.kbandroidtechassessment.utils.DateUtil
import org.threeten.bp.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    startingBalance: Double,
    transactions: List<Transaction>,
) {
    // State to keep track of the selected date range
    var selectedStartDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedEndDate by remember { mutableStateOf<LocalDate?>(null) }

    // Filter transactions based on the selected date range
    val filteredTransactions = transactions.filter { transaction ->
        val transactionDate = DateUtil.stringToLocalDate(transaction.date)
        (selectedStartDate == null || !transactionDate.isBefore(selectedStartDate!!)) &&
                (selectedEndDate == null || !transactionDate.isAfter(selectedEndDate!!))
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Travel Savings Account")
                },
                actions = {
                    /*
                    todo: task #5 - Reset Filter: Provide a way to reset the filters
                     and view all transactions.
                    */
                    FilterDateRangeButton(
                        onDateRangeSelected = { startDateMillis, endDateMillis ->
                            startDateMillis?.let {
                                selectedStartDate = DateUtil.millisecondsToLocalDate(it)
                            }
                            endDateMillis?.let {
                                selectedEndDate = DateUtil.millisecondsToLocalDate(it)
                            }
                            Log.d(TAG, "onDateRangeSelected: $selectedStartDate, $selectedEndDate")
                        }
                    )
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
                    text = calculateBalance(
                        startingBalance = startingBalance,
                        transactions = filteredTransactions,
                    ).toCurrencyNZDString(),
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

private fun calculateBalance(
    startingBalance: Double,
    transactions: List<Transaction>,
): Double {
    var balance = startingBalance
    transactions.forEach { transaction ->
        balance += transaction.amount
    }
    return balance
}


@Preview(showBackground = true)
@Composable
fun MainScreenContentPreview() {
    MainScreenContent(
        startingBalance = 5000.00,
        transactions = listOf(
            Transaction("2024-09-22", "Restaurant", -35.00),
            Transaction("2024-09-24", "Car Repair", -150.00),
            Transaction("2024-09-11", "Utilities", -150.00),
        )
    )
}