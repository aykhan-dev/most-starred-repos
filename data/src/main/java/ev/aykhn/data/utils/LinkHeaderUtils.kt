package ev.aykhn.data.utils

import ev.aykhn.data.enums.LinkHeaderType
import ev.aykhn.data.model.header.LinkHeaderInfo
import okhttp3.Headers
import java.util.regex.Pattern

object LinkHeaderUtils {

    //Example header -> link: <link-url>; ref="next", <link-url>; ref="last"

    const val LINK_REGEX = "(?<=<)(.*?)(?=>)" //retrieval the link from the header value
    const val LINK_TYPE_REGEX = "(?<=\")(.*?)(?=\")" //retrieval the link type from the header value

    fun getLinkInfo(headers: Headers?): List<LinkHeaderInfo> {
        val header = headers?.get("link") ?: return emptyList() //gets whole header value
        val headerLinks = header.split(",").map { it.trim() } //split it into separate link info parts

        return headerLinks.map { link ->
            val parts = link.split(";").map { it.trim() } //separate the link and link type

            LinkHeaderInfo(
                link = findInBetweenRegexResults(LINK_REGEX, parts[0])!!,
                linkType = LinkHeaderType.valueOf(
                    findInBetweenRegexResults(
                        LINK_TYPE_REGEX,
                        parts[1]
                    )!!.uppercase()
                )
            )
        }
    }

    //regex search for given pattern
    fun findInBetweenRegexResults(regex: String, data: String): String? {
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(data)

        return if (matcher.find()) matcher.group(0) else null
    }

}