package com.example.kbandroidtechassessment.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kbandroidtechassessment.data.Transaction
import com.example.kbandroidtechassessment.utils.extension.toCurrencyNZDString

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Column(modifier = Modifier.weight(1f)) { // Left side
            Text(text = transaction.description)
            Text(text = transaction.date, style = MaterialTheme.typography.labelSmall)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = transaction.amount.toCurrencyNZDString(),
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun TransactionItemPreview() {
    TransactionItem(
        Transaction("2024-09-22", "Restaurant", -35.00)
    )
}