package ev.aykhn.data.model.pojo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserPOJO(
    @SerialName("id") val id: Long,
    @SerialName("login") val username: String?,
    @SerialName("avatar_url") val avatarUrl: String?,
) {
    companion object {
        fun getDummy(): UserPOJO {
            return UserPOJO(
                id = 1,
                username = "dummy username",
                avatarUrl = "dummy avatar url",
            )
        }
    }
}
