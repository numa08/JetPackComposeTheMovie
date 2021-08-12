package net.numa08.jetpack_compose_the_movie.data.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.numa08.jetpack_compose_the_movie.data.database.Database
import net.numa08.jetpack_compose_the_movie.data.database.imdb.IMDBDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "main.db")
            .build()

    @Provides
    fun providesIMDBDao(database: Database): IMDBDao = database.imdbDao()
}