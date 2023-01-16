package ev.aykhn.data.utils

import ev.aykhn.domain.utils.DateUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class QueryUtilsTest {

    @Test
    fun `should return creation date filter query`() {
        val date = DateUtils.get30DaysBeforeDate()
        val formattedDate = DateUtils.formatDate(date)

        val expected = "created:>$formattedDate"
        val actual = QueryUtils.generateSinceCreationDateQuery(formattedDate)

        assertEquals(expected, actual)
    }

}