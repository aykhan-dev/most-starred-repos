package ev.aykhn.data.dataSource.local

import ev.aykhn.data.model.entity.RepoEntity
import ev.aykhn.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface ReposLocalDataSource {
    suspend fun insertRepos(items: List<RepoEntity>)
    fun getRepos(): Flow<List<RepoEntity>>
}