package ev.aykhn.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ev.aykhn.data.repositoryImpl.ReposRepositoryImpl
import ev.aykhn.domain.repository.ReposRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindReposRepository(repositoryImpl: ReposRepositoryImpl): ReposRepository
}