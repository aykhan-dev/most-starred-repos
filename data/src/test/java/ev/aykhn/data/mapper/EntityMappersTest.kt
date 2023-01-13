package ev.aykhn.data.mapper

import ev.aykhn.data.mapper.toEntity.toEntity
import ev.aykhn.data.model.pojo.RepoPOJO
import org.junit.Test

class EntityMappersTest {

    @Test
    fun `mapping from RepoPOJO to RepoEntity should work properly`() {
        val repoPOJO = RepoPOJO.getDummy()
        val repoEntity = repoPOJO.toEntity()
        assert(repoPOJO.id == repoEntity.id)
    }

}