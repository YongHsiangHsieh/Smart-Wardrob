package utils

import java.time.LocalDate

object TimeUtil {
    fun getCurrentMonthAsInt(currentDate: LocalDate = LocalDate.now()): Int = currentDate.monthValue

}