package ev.aykhn.domain.repository

import ev.aykhn.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    val repos: Flow<List<Repo>>

    suspend fun fetchRepos(date: String, pageIndex: Int)
}