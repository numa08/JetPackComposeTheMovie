package net.numa08.jetpack_compose_the_movie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.numa08.jetpack_compose_the_movie.data.database.imdb.*
import net.numa08.jetpack_compose_the_movie.data.database.omdb.OMDBDao
import net.numa08.jetpack_compose_the_movie.data.database.omdb.Poster

@Database(
    entities = [
        GenreMasterEntity::class,
        TitleEntity::class,
        OriginalTitleEntity::class,
        GenreTitleEntity::class,
        Poster::class
    ],
    version = 1,
    exportSchema = true,
    views = [JapaneseTitleView::class]
)
abstract class Database : RoomDatabase() {
    abstract fun imdbDao(): IMDBDao
    abstract fun omdbDao(): OMDBDao
}