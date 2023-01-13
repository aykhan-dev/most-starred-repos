package ev.aykhn.data.mapper.toDomain

import ev.aykhn.data.model.entity.RepoEntity
import ev.aykhn.domain.model.Repo

fun RepoEntity.toDomain(): Repo {
    return Repo(
        id = id,
        name = name,
        description = description,
        starCount = starCount,
        username = username,
        userAvatarUrl = userAvatarUrl,
    )
}