package com.example.kbandroidtechassessment.extension

import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrencyNZDString(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("en", "NZ"))
    return format.format(this)
}
