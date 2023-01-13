package ev.aykhn.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ev.aykhn.data.dataSource.local.ReposLocalDataSource
import ev.aykhn.data.dataSource.remote.SearchRemoteDataSource
import ev.aykhn.data.dataSourceImpl.local.AppDatabase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideSearchRemoteDataSource(retrofit: Retrofit): SearchRemoteDataSource {
        return retrofit.create(SearchRemoteDataSource::class.java)
    }

    @Singleton
    @Provides
    fun providesLocalReposDataSource(
        database: AppDatabase
    ): ReposLocalDataSource = database.reposDao

}