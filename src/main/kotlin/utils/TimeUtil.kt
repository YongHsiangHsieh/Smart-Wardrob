package utils

import java.time.LocalDate

object TimeUtil {
    fun getCurrentMonthAsInt(): Int {
        return LocalDate.now().monthValue
    }
}