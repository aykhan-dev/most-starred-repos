package ev.aykhn.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ev.aykhn.data.dataSource.local.ReposLocalDataSource
import ev.aykhn.data.dataSource.remote.SearchRemoteDataSource
import ev.aykhn.data.dataSourceImpl.local.AppDatabase
import ev.aykhn.data.dataSourceImpl.remote.SearchRemoteDataSourceImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            name = "MostStarredReposAppDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideSearchRemoteDataSource(retrofit: Retrofit): SearchRemoteDataSource {
        return retrofit.create(SearchRemoteDataSourceImpl::class.java)
    }

    @Singleton
    @Provides
    fun providesLocalReposDataSource(
        database: AppDatabase
    ): ReposLocalDataSource = database.reposDao

}