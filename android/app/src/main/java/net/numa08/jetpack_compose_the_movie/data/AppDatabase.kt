package net.numa08.jetpack_compose_the_movie.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GenreMaster::class, Title::class, OriginalTitle::class],
    version = 1,
    exportSchema = false,
    views = [JapaneseTitle::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun titleDao(): TitleDao
}