@file:OptIn(ExperimentalCoroutinesApi::class)

package ev.aykhn.presentation.flow.screens.mostStarredRepos

import ev.aykhn.domain.usecase.MostStarredReposUseCase
import ev.aykhn.presentation.MainCoroutineRule
import ev.aykhn.presentation.ui.flow.main.screens.mostStarredRepos.MostStarredReposViewModel
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class MostStarredReposViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var get: MostStarredReposUseCase.Get

    @MockK
    lateinit var fetch: MostStarredReposUseCase.Fetch

    private lateinit var viewModel: MostStarredReposViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MostStarredReposViewModel(
            getMostStarredReposUseCase = get,
            fetchMostStarredReposUseCase = fetch,
        )
    }

    @Test
    fun `should clear error message`() = runTest {
        viewModel.emitState(viewModel.state.value.copy(errorMessage = "Some error"))

        delay(1000L)

        assertEquals("Some error", viewModel.state.value.errorMessage)

        viewModel.clearErrorMessage()

        delay(1000L)

        assertEquals(null, viewModel.state.value.errorMessage)
    }

    @Test
    fun `should update the state onError`() = runTest {
        viewModel.onError(Dispatchers.Main, throwable = IOException("Some error"))

        delay(1000L)

        val errorMessage = viewModel.state.first().errorMessage

        assertEquals("Some error", errorMessage)
    }

}