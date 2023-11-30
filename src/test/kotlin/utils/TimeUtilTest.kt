package utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class TimeUtilTest {

    @Test
    fun `getCurrentMonthAsInt returns correct month for January`() {
        val testDate = LocalDate.of(2021, 1, 1) // January 1, 2021
        assertEquals(1, TimeUtil.getCurrentMonthAsInt(testDate))
    }

    @Test
    fun `getCurrentMonthAsInt returns correct month for June`() {
        val testDate = LocalDate.of(2021, 6, 15) // June 15, 2021
        assertEquals(6, TimeUtil.getCurrentMonthAsInt(testDate))
    }

    @Test
    fun `getCurrentMonthAsInt returns correct month for December`() {
        val testDate = LocalDate.of(2021, 12, 31) // December 31, 2021
        assertEquals(12, TimeUtil.getCurrentMonthAsInt(testDate))
    }
}
