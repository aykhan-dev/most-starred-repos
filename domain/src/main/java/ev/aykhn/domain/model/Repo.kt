package ev.aykhn.domain.model

data class Repo(
    val id: Long,
    val name: String?,
    val description: String?,
    val starCount: Int?,
    val username: String?,
    val userAvatarUrl: String?,
)
