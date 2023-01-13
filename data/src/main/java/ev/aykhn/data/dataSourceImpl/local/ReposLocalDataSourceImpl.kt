package ev.aykhn.data.dataSourceImpl.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ev.aykhn.data.dataSource.local.ReposLocalDataSource
import ev.aykhn.data.model.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ReposLocalDataSourceImpl : ReposLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract override suspend fun insertRepos(items: List<RepoEntity>)

    @Query("SELECT * FROM repoentity ORDER BY starCount DESC")
    abstract override fun getRepos(): Flow<List<RepoEntity>>

}