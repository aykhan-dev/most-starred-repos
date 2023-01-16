@file:OptIn(ExperimentalCoroutinesApi::class)

package ev.aykhn.data.repositoryImpl

import ev.aykhn.data.dataSource.local.ReposLocalDataSource
import ev.aykhn.data.dataSource.remote.SearchRemoteDataSource
import ev.aykhn.data.model.pojo.RepoPOJO
import ev.aykhn.data.model.pojo.SearchResponsePOJO
import ev.aykhn.domain.model.Repo
import ev.aykhn.domain.repository.ReposRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ReposRepositoryImplTest {

    @MockK
    lateinit var localReposDataSource: ReposLocalDataSource

    @MockK
    lateinit var remoteDataSource: SearchRemoteDataSource

    private lateinit var repository: ReposRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = ReposRepositoryImpl(
            localReposDataSource = localReposDataSource,
            remoteDataSource = remoteDataSource,
        )
    }

    @Test
    fun `gives flow of repos directly from the local database`() = runTest {
        every { localReposDataSource.getRepos() } returns flow { emit(emptyList()) }

        val actual = repository.repos.first()

        assertEquals(emptyList<Repo>(), actual)
    }

    @Test
    fun `fetches new page via increasing the index by one and caches the data`() = runTest {
        val currentPageIndex = 0
        val response = SearchResponsePOJO<RepoPOJO>(
            totalCount = 10,
            isIncompleteResult = false,
            items = emptyList(),
        )

        coEvery {
            remoteDataSource.getMostStarredRepos(
                customQuery = any(),
                sort = any(),
                order = any(),
                pageIndex = any(),
            )
        } returns Response.success(response)

        coEvery { localReposDataSource.insertRepos(any()) } returns Unit

        repository.fetch(currentPageIndex)

        coVerify { remoteDataSource.getMostStarredRepos(any(), any(), any(), any(), any()) }
        coVerify { localReposDataSource.insertRepos(any()) }
    }

}