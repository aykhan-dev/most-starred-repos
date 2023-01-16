package ev.aykhn.data.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class LinkHeaderUtilsTest {

    @Test
    fun `should find link in the link header`() {
        val link = "<link>"
        val part = LinkHeaderUtils.findInBetweenRegexResults(LinkHeaderUtils.LINK_REGEX, link)

        assertEquals("link", part)
    }

    @Test
    fun `should find link type in the link header`() {
        val link = "ref=\"link-type\""
        val part = LinkHeaderUtils.findInBetweenRegexResults(LinkHeaderUtils.LINK_TYPE_REGEX, link)

        assertEquals("link-type", part)
    }

}