package ev.aykhn.data.dataSource.remote

import ev.aykhn.data.model.pojo.RepoPOJO
import ev.aykhn.data.model.pojo.SearchResponsePOJO
import retrofit2.Response

//A datasource for repo operations on the network (server-side, API)
interface SearchRemoteDataSource {

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
    }

    suspend fun getMostStarredRepos(
        customQuery: String,
        sort: String,
        order: String,
        pageSize: Int = DEFAULT_PAGE_SIZE,
        pageIndex: Int,
    ): Response<SearchResponsePOJO<RepoPOJO>>

}