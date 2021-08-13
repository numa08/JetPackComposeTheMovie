package net.numa08.jetpack_compose_the_movie.domain.repository.di

import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.numa08.jetpack_compose_the_movie.data.database.imdb.IMDBDao
import net.numa08.jetpack_compose_the_movie.data.datastore.ApplicationStateOuterClass
import net.numa08.jetpack_compose_the_movie.domain.repository.ApplicationStateRepository
import net.numa08.jetpack_compose_the_movie.domain.repository.TitleRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideApplicationStateRepository(dataStore: DataStore<ApplicationStateOuterClass.ApplicationState>): ApplicationStateRepository =
        ApplicationStateRepository(dataStore)

    @Singleton
    @Provides
    fun provideTitleRepository(imdbDao: IMDBDao): TitleRepository =
        TitleRepository(imdbDao = imdbDao)
}