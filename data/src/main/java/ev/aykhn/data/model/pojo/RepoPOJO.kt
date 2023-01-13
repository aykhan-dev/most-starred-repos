package ev.aykhn.data.model.pojo

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RepoPOJO(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("stargazers_count") val starCount: Int?,
    @SerialName("owner") val owner: UserPOJO,
) {
    companion object {
        fun getDummy(): RepoPOJO {
            return RepoPOJO(
                id = 1,
                name = "dummy name",
                description = "dummy description",
                starCount = 1,
                owner = UserPOJO.getDummy(),
            )
        }
    }
}