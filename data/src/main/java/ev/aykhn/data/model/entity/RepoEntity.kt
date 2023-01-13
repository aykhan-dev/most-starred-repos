package ev.aykhn.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepoEntity(
    @PrimaryKey
    val id: Long,
    val name: String?,
    val description: String?,
    val starCount: Int?,
    val username: String?,
    val userAvatarUrl: String?,
)