package ev.aykhn.data.mapper

import ev.aykhn.data.mapper.toDomain.toDomain
import ev.aykhn.data.mapper.toEntity.toEntity
import ev.aykhn.data.model.entity.RepoEntity
import ev.aykhn.data.model.pojo.RepoPOJO
import org.junit.Test

class DomainMappersTest {

    @Test
    fun `mapping from RepoEntity to Repo should work properly`() {
        val repoEntity = RepoEntity.getDummy()
        val repoDomain = repoEntity.toDomain()
        assert(repoDomain.id == repoEntity.id)
    }

}