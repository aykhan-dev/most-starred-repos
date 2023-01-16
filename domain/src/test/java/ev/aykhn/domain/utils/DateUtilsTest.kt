package ev.aykhn.domain.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DateUtilsTest {

    @Test
    fun `should give '2022-12-17' when calculation date is '2023-01-16'`() {
        val calendarActual = Calendar.getInstance()
        val calendarExpected = Calendar.getInstance()

        calendarActual.set(2023, 0, 16)

        calendarExpected.set(2023, 0, 16)
        calendarExpected.add(Calendar.DATE, -30)

        val actual = DateUtils.get30DaysBeforeDate(calendarActual)
        val expected = calendarExpected.time

        assertEquals(expected, actual)
    }

    @Test
    fun `should give '2023-01-16' when calendar is 16th January of 2023`() {
        val calendar = Calendar.getInstance()

        calendar.set(2023, 0, 16)

        val formattedString = DateUtils.formatDate(calendar.time)

        assertEquals("2023-01-16", formattedString)
    }

}