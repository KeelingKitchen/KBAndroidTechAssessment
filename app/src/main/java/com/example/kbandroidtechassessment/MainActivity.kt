package com.example.kbandroidtechassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kbandroidtechassessment.data.TransactionRepository
import com.example.kbandroidtechassessment.ui.screen.mainscreen.MainScreenContent
import com.example.kbandroidtechassessment.ui.screen.mainscreen.MainScreenViewModel
import com.example.kbandroidtechassessment.ui.theme.KBAndroidTechAssessmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KBAndroidTechAssessmentTheme {
                val viewModel: MainScreenViewModel = viewModel()
                viewModel.setTransactions(TransactionRepository().getTransactions())

                MainScreenContent(
                    startingBalance = getStartingBalance(),
                    viewModel = viewModel
                )
            }
        }
    }
}

private fun getStartingBalance() = 000.00
