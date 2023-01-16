package ev.aykhn.data.utils

object QueryUtils {

    //creates appropriate query string for github repository search
    fun generateSinceCreationDateQuery(date: String): String {
        return "created:>$date"
    }

}