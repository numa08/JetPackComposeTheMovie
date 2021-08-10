package net.numa08.jetpack_compose_the_movie.di

import android.content.Context
import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.numa08.jetpack_compose_the_movie.data.datastore.ApplicationStateOuterClass.ApplicationState
import net.numa08.jetpack_compose_the_movie.data.datastore.applicationStateDataStore
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {
    @Singleton
    @Provides
    fun providesApplicationStateDataStore(@ApplicationContext context: Context): DataStore<ApplicationState> =
        context.applicationStateDataStore
}