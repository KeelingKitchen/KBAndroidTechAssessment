package com.example.kbandroidtechassessment.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.kbandroidtechassessment.dto.Transaction
import com.example.kbandroidtechassessment.ui.component.TransactionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    startingBalance: Double,
    transactions: List<Transaction>,
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text("Travel Savings Account")
                },
                actions = {
                    /*
                    todo: task #2 - Date Filter: Add a date filter UI element
                     (e.g., date pickers or text fields).
                    */

                    /*
                    todo: task #5 - Reset Filter: Provide a way to reset the filters
                     and view all transactions.
                    */
                    IconButton(onClick = { /* Handle filter action */ }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Filter"
                        )
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
                /*
                todo: task #1 - Calculate Balance: Accurately calculate
                 and display the available balance based on the transactions.
                */

                /*
                todo: task #4 - Update Balance: As transactions are filtered,
                 recalculate and display the filtered balance.
                */
                Text(
                    text = "$0.00",
                    fontWeight = FontWeight.W600,
                    fontSize = TextUnit(24f, TextUnitType.Sp),
                )
            }
            HorizontalDivider()
            LazyColumn {
                items(transactions) { transaction ->
                    /*
                    todo: task #3 - Filter Transactions:
                     When a filter is applied, update the transaction list to
                     display only transactions within the specified date range.
                    */
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
        transactions = listOf(
            Transaction("2024-09-22", "Restaurant", -35.00),
            Transaction("2024-09-24", "Car Repair", -150.00),
            Transaction("2024-09-11", "Utilities", -150.00),
        )
    )
}