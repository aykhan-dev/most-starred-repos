package ev.aykhn.data.dataSource.local

import ev.aykhn.data.model.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

//A data source controls repo operations on the local database (cache)
interface ReposLocalDataSource {
    suspend fun insertRepos(items: List<RepoEntity>)
    fun getRepos(): Flow<List<RepoEntity>>
}