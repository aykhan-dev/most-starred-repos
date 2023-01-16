package ev.aykhn.data.dataSourceImpl.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ev.aykhn.data.BuildConfig
import ev.aykhn.data.dataSourceImpl.local.ReposLocalDataSourceImpl
import ev.aykhn.data.model.entity.RepoEntity

@Database(entities = [RepoEntity::class], version = BuildConfig.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract val reposDao: ReposLocalDataSourceImpl
}