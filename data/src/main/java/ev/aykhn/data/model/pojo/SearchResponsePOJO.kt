package ev.aykhn.data.model.pojo

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SearchResponsePOJO<T>(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("incomplete_results") val isIncompleteResult: Boolean,
    @SerialName("items") val items: List<T>,
)