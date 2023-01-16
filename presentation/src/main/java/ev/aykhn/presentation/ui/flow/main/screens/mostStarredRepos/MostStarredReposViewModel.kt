package ev.aykhn.presentation.ui.flow.main.screens.mostStarredRepos

import dagger.hilt.android.lifecycle.HiltViewModel
import ev.aykhn.domain.usecase.MostStarredReposUseCase
import ev.aykhn.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MostStarredReposViewModel @Inject constructor(
    getMostStarredReposUseCase: MostStarredReposUseCase.Get,
    private val fetchMostStarredReposUseCase: MostStarredReposUseCase.Fetch,
) : BaseViewModel<MostStarredReposState, MostStarredReposEvents>() {

    private var currentIndex = 0

    // repos list retrieved from database in the format of paged data
    val repos by lazy {
        getMostStarredReposUseCase.execute(Unit)
            .onEach { currentIndex = it.lastOrNull()?.pageIndex ?: 0 }
    }

    override fun provideInitialState(): MostStarredReposState = MostStarredReposState()

    override fun onEvent(event: MostStarredReposEvents) {
        when (event) {
            is MostStarredReposEvents.Fetch -> fetch()
        }
    }

    override fun onError(context: CoroutineContext, throwable: Throwable) {
        Timber.e(throwable)
        emitState(state.value.copy(errorMessage = throwable.message))
    }

    private fun fetch() {
        emitState(state.value.copy(isLoading = true))

        val params = MostStarredReposUseCase.Fetch.Params(currentIndex = currentIndex)
        use(fetchMostStarredReposUseCase, params) {
            emitState(state.value.copy(isLoading = false))
        }
    }

    fun clearErrorMessage() {
        emitState(state.value.copy(errorMessage = null))
    }

}

data class MostStarredReposState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null, // for business logic broker errors
)

sealed class MostStarredReposEvents {
    object Fetch : MostStarredReposEvents()
}