package ev.aykhn.domain.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val simplifiedDateFormat by lazy { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    //format the date into a string, for example, 2023-01-16
    fun formatDate(date: Date): String {
        return simplifiedDateFormat.format(date)
    }

    //returns 30 days ago date
    fun get30DaysBeforeDate(calendar: Calendar? = null): Date {
        val date = (calendar ?: Calendar.getInstance()).apply {
            add(Calendar.DATE, -30)
        }
        return date.time
    }

}