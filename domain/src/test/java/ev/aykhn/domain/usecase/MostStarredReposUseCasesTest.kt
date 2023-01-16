@file:OptIn(ExperimentalCoroutinesApi::class)

package ev.aykhn.domain.usecase

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

class MostStarredReposUseCasesTest {

    @MockK
    lateinit var repository: ReposRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `get-use-case returns flow of repos from the local database`() = runTest {
        val usecase = MostStarredReposUseCase.Get(repository)

        every { repository.repos } returns flow { emit(emptyList()) }

        val actual = usecase.execute(Unit).first()

        assertEquals(emptyList<Repo>(), actual)
    }

    @Test
    fun `fetch-use-case should trigger repository fetch call`() = runTest {
        val usecase = MostStarredReposUseCase.Fetch(repository)

        val params = MostStarredReposUseCase.Fetch.Params(currentIndex = 0)

        coEvery { repository.fetch(any()) } returns Unit

        usecase.start(params)

        coVerify { repository.fetch(params.currentIndex) }
    }

}