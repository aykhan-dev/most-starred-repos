package ev.aykhn.domain.usecase

import ev.aykhn.domain.base.BaseFlowUseCase
import ev.aykhn.domain.base.BaseUseCase
import ev.aykhn.domain.model.Repo
import ev.aykhn.domain.repository.ReposRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MostStarredReposUseCase {

    class Get @Inject constructor(
        private val repository: ReposRepository
    ) : BaseFlowUseCase<Unit, List<Repo>>() {

        override fun createFlow(params: Unit): Flow<List<Repo>> {
            return repository.repos
        }

    }

    class Fetch @Inject constructor(
        private val repository: ReposRepository
    ) : BaseUseCase<Fetch.Params, Unit>() {

        data class Params(val currentIndex: Int)

        override suspend fun execute(params: Params) {
            repository.fetch(params.currentIndex)
        }

    }

}