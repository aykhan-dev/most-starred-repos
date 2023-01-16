package ev.aykhn.data.dataSourceImpl.remote

import ev.aykhn.data.dataSource.remote.SearchRemoteDataSource
import ev.aykhn.data.model.pojo.RepoPOJO
import ev.aykhn.data.model.pojo.SearchResponsePOJO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteDataSourceImpl : SearchRemoteDataSource {

    @GET("search/repositories")
    override suspend fun getMostStarredRepos(
        @Query("q") customQuery: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("per_page") pageSize: Int,
        @Query("page") pageIndex: Int,
    ): Response<SearchResponsePOJO<RepoPOJO>>

}