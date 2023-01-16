package ev.aykhn.domain.repository

import ev.aykhn.domain.model.Repo
import kotlinx.coroutines.flow.Flow

//A repository for managing all repo operations
interface ReposRepository {
    val repos: Flow<List<Repo>>

    suspend fun fetch(currentIndex: Int)
}