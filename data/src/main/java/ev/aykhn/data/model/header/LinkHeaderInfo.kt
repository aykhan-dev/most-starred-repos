package ev.aykhn.data.model.header

import ev.aykhn.data.enums.LinkHeaderType

data class LinkHeaderInfo(
    val link: String,
    val linkType: LinkHeaderType,
)