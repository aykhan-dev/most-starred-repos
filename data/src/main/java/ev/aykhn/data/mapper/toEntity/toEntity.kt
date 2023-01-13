package ev.aykhn.data.mapper.toEntity

import ev.aykhn.data.model.entity.RepoEntity
import ev.aykhn.data.model.pojo.RepoPOJO

fun RepoPOJO.toEntity(): RepoEntity {
    return RepoEntity(
        id = id,
        name = name,
        description = description,
        starCount = starCount,
        username = owner.username,
        userAvatarUrl = owner.avatarUrl,
    )
}