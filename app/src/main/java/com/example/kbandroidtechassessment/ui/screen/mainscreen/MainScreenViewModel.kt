package com.example.kbandroidtechassessment.ui.screen.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kbandroidtechassessment.dto.Transaction
import com.example.kbandroidtechassessment.utils.DateUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate

class MainScreenViewModel : ViewModel() {
    private val _selectedStartDate = MutableStateFlow<LocalDate?>(null)
    val selectedStartDate: StateFlow<LocalDate?> = _selectedStartDate

    private val _selectedEndDate = MutableStateFlow<LocalDate?>(null)
    val selectedEndDate: StateFlow<LocalDate?> = _selectedEndDate

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    fun setTransactions(transactions: List<Transaction>) {
        viewModelScope.launch {
            _transactions.value = transactions
        }
    }

    fun setStartDate(startDateMillis: Long?) {
        val startDate = startDateMillis?.let { DateUtil.millisecondsToLocalDate(it) }
        viewModelScope.launch {
            _selectedStartDate.value = startDate
        }
    }

    fun setEndDate(endDateMillis: Long?) {
        val endDate = endDateMillis?.let { DateUtil.millisecondsToLocalDate(it) }
        viewModelScope.launch {
            _selectedEndDate.value = endDate
        }
    }

    fun resetDateRange() {
        viewModelScope.launch {
            _selectedStartDate.value = null
            _selectedEndDate.value = null
        }
    }

    fun calculateBalance(startingBalance: Double): Double {
        val currentTransactions = getPresentedTransactions()
        return currentTransactions.fold(startingBalance) { balance, transaction ->
            balance + transaction.amount
        }
    }

    fun getPresentedTransactions(): List<Transaction> {
        val startDate = selectedStartDate.value
        val endDate = selectedEndDate.value
        return transactions.value.filter { transaction ->
            val transactionDate = DateUtil.stringToLocalDate(transaction.date)
            (startDate == null || !transactionDate.isBefore(startDate)) &&
                    (endDate == null || !transactionDate.isAfter(endDate))
        }
    }
}
