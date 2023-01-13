package ev.aykhn.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ev.aykhn.data.model.pojo.UserPOJO
import ev.aykhn.domain.model.Repo

@Entity
data class RepoEntity(
    @PrimaryKey
    val id: Long,
    val name: String?,
    val description: String?,
    val starCount: Int?,
    val username: String?,
    val userAvatarUrl: String?,
) {
    companion object {
        fun getDummy(): RepoEntity {
            return RepoEntity(
                id = 1,
                name = "dummy name",
                description = "dummy description",
                starCount = 1,
                username = "dummy username",
                userAvatarUrl = "dummy avatar url",
            )
        }
    }
}