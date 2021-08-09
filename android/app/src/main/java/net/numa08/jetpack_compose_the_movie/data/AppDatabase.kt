package net.numa08.jetpack_compose_the_movie.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Title::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()