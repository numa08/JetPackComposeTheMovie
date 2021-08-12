package net.numa08.jetpack_compose_the_movie.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.numa08.jetpack_compose_the_movie.database.imdb.*

@Database(
    entities = [
        GenreMaster::class,
        Title::class,
        OriginalTitle::class,
        GenreTitle::class,
    ],
    version = 1,
    exportSchema = true,
    views = [JapaneseTitle::class]
)
abstract class Database: RoomDatabase() {
    abstract fun imdbDao(): IMDBDao
}