package net.numa08.jetpack_compose_the_movie.data.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.numa08.jetpack_compose_the_movie.data.datastore.ApplicationStateOuterClass
import net.numa08.jetpack_compose_the_movie.data.datastore.applicationStateDataStore
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatastoreModule {
    @Singleton
    @Provides
    fun providesApplicationStateDataStore(@ApplicationContext context: Context): DataStore<ApplicationStateOuterClass.ApplicationState> =
        context.applicationStateDataStore
}