package ev.aykhn.data.repositoryImpl

import ev.aykhn.data.dataSource.local.ReposLocalDataSource
import ev.aykhn.data.dataSource.remote.SearchRemoteDataSource
import ev.aykhn.data.enums.GithubRepoOrderTypes
import ev.aykhn.data.enums.GithubRepoSortTypes
import ev.aykhn.data.mapper.toDomain.toDomain
import ev.aykhn.data.mapper.toEntity.toEntity
import ev.aykhn.data.utils.QueryUtils
import ev.aykhn.domain.model.Repo
import ev.aykhn.domain.repository.ReposRepository
import ev.aykhn.domain.utils.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReposRepositoryImpl @Inject constructor(
    private val localReposDataSource: ReposLocalDataSource,
    private val remoteDataSource: SearchRemoteDataSource,
) : ReposRepository {
    override val repos: Flow<List<Repo>>
        get() = localReposDataSource.getRepos()
            .map { it -> it.map { it.toDomain() } }

    override suspend fun fetch(currentIndex: Int) {
        val startDate = DateUtils.formatDate(DateUtils.get30DaysBeforeDate())
        val customQuery = QueryUtils.generateSinceCreationDateQuery(startDate)

        val cursorIndex = currentIndex + 1 // pageIndex for upcoming loads

        val response = remoteDataSource.getMostStarredRepos(
            customQuery = customQuery,
            sort = GithubRepoSortTypes.STARS.name,
            order = GithubRepoOrderTypes.DESC.name,
            pageIndex = cursorIndex,
        )

        val items = response.body()?.items ?: return // stop in case of no response
        localReposDataSource.insertRepos(items.map { it.toEntity(cursorIndex) }) // caching
    }
}