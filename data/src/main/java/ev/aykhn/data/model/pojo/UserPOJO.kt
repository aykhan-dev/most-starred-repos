package ev.aykhn.data.model.pojo

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class UserPOJO(
    @SerialName("id") val id: Long,
    @SerialName("login") val username: String?,
    @SerialName("avatar_url") val avatarUrl: String?,
)
