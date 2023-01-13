package ev.aykhn.data.repositoryImpl

import ev.aykhn.data.dataSource.local.ReposLocalDataSource
import ev.aykhn.data.dataSource.remote.SearchRemoteDataSource
import ev.aykhn.data.mapper.toDomain.toDomain
import ev.aykhn.data.mapper.toEntity.toEntity
import ev.aykhn.domain.model.Repo
import ev.aykhn.domain.repository.ReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReposRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource,
    private val localDataSource: ReposLocalDataSource,
) : ReposRepository {
    override val repos: Flow<List<Repo>> get() = localDataSource
        .getRepos()
        .map { list -> list.map { it.toDomain() } }

    override suspend fun fetchRepos(date: String, pageIndex: Int) {
        val remoteData = remoteDataSource.getMostStarredRepos(date, pageIndex)
        localDataSource.insertRepos(remoteData.items.map { it.toEntity() })
    }
}