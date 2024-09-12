package com.example.kbandroidtechassessment.utils

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

object DateUtil {
    // Convert milliseconds to LocalDate
    fun millisecondsToLocalDate(milliseconds: Long): LocalDate {
        val instant = Instant.ofEpochMilli(milliseconds)
        return instant.atZone(ZoneId.systemDefault()).toLocalDate()
    }

    // Convert transaction date String to LocalDate
    fun stringToLocalDate(dateString: String): LocalDate {
        return LocalDate.parse(dateString)
    }
}