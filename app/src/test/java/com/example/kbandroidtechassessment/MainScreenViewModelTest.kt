package com.example.kbandroidtechassessment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.kbandroidtechassessment.data.Transaction
import com.example.kbandroidtechassessment.ui.screen.mainscreen.MainScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.threeten.bp.LocalDate

@ExperimentalCoroutinesApi
class MainScreenViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainScreenViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainScreenViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testSetTransactions() = runTest {
        val transactions = listOf(
            Transaction("2024-09-22", "Restaurant", -35.00),
            Transaction("2024-09-24", "Car Repair", -150.00),
            Transaction("2024-09-11", "Utilities", -150.00)
        )
        viewModel.setTransactions(transactions)
        assertEquals(transactions, viewModel.transactions.value)
    }

    @Test
    fun testCalculateBalance() = runTest {
        val startingBalance = 5000.00
        val transactions = listOf(
            Transaction("2024-09-22", "Restaurant", -35.00),
            Transaction("2024-09-24", "Car Repair", -150.00),
            Transaction("2024-09-11", "Utilities", -150.00)
        )
        viewModel.setTransactions(transactions)
        val balance = viewModel.calculateBalance(startingBalance)
        assertEquals(4665.00, balance, 0.01)
    }

    @Test
    fun testSetStartDate() = runTest {
        val startDate = LocalDate.of(2024, 9, 1)
        viewModel.setStartDate(startDate.toEpochDay() * 24 * 60 * 60 * 1000)
        assertEquals(startDate, viewModel.selectedStartDate.value)
    }

    @Test
    fun testSetEndDate() = runTest {
        val endDate = LocalDate.of(2024, 9, 30)
        viewModel.setEndDate(endDate.toEpochDay() * 24 * 60 * 60 * 1000)
        assertEquals(endDate, viewModel.selectedEndDate.value)
    }

    @Test
    fun testResetDateRange() = runTest {
        viewModel.resetDateRange()
        assertEquals(null, viewModel.selectedStartDate.value)
        assertEquals(null, viewModel.selectedEndDate.value)
    }
}