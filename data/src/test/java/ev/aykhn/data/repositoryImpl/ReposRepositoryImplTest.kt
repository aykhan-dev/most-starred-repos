@file:OptIn(ExperimentalCoroutinesApi::class)

package ev.aykhn.data.repositoryImpl

import ev.aykhn.data.dataSource.local.ReposLocalDataSource
import ev.aykhn.data.dataSource.remote.SearchRemoteDataSource
import ev.aykhn.data.mapper.toEntity.toEntity
import ev.aykhn.data.model.entity.RepoEntity
import ev.aykhn.data.model.pojo.RepoPOJO
import ev.aykhn.data.model.pojo.SearchResponsePOJO
import ev.aykhn.data.model.pojo.UserPOJO
import ev.aykhn.domain.repository.ReposRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ReposRepositoryImplTest {

    @MockK
    lateinit var reposLocalDataSource: ReposLocalDataSource

    @MockK
    lateinit var searchRemoteDataSource: SearchRemoteDataSource

    private lateinit var repository: ReposRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = ReposRepositoryImpl(
            remoteDataSource = searchRemoteDataSource,
            localDataSource = reposLocalDataSource,
        )
    }

    @Test
    fun `getRepos() should map entities to domain models`() = runTest {
        val entity = RepoEntity(
            id = 1,
            name = "test-name",
            description = "test-description",
            username = "test-username",
            userAvatarUrl = "test-user-avatar-url",
            starCount = 0,
        )

        every { reposLocalDataSource.getRepos() } returns flow { emit(listOf(entity)) }

        val domainModels = repository.repos.first()

        assertEquals(1, domainModels.size)
    }

    @Test
    fun `fetchRepos() should map pojos to entity and cache them`() = runTest {
        val repoPOJO = RepoPOJO(
            id = 0,
            name = "test-name",
            description = "test-description",
            owner = UserPOJO(
                id = 0,
                username = "test-username",
                avatarUrl = "test-user-avatar-url",
            ),
            starCount = 0,
        )

        val response = SearchResponsePOJO(
            totalCount = 0,
            isIncompleteResult = false,
            items = listOf(repoPOJO)
        )

        val mappedPOJO = response.items.map { it.toEntity() }

        every { searchRemoteDataSource.getMostStarredRepos(any(), any()) } returns response
        coEvery { reposLocalDataSource.insertRepos(any()) } returns Unit

        repository.fetchRepos("2023-01-01", 1)

        coVerify { reposLocalDataSource.insertRepos(mappedPOJO) }
    }

}