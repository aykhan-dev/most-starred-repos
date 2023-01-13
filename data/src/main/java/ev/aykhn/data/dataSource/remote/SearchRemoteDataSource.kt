package ev.aykhn.data.dataSource.remote

import ev.aykhn.data.model.pojo.RepoPOJO
import ev.aykhn.data.model.pojo.SearchResponsePOJO
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchRemoteDataSource {

    @GET("search/repositories?q=created:>{date}&sort=stars&or&page={pageIndex}")
    fun getMostStarredRepos(
        @Path("date") date: String,
        @Path("pageIndex") pageIndex: Int,
    ): SearchResponsePOJO<RepoPOJO>

}