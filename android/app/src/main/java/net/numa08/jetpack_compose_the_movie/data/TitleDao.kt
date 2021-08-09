package net.numa08.jetpack_compose_the_movie.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {
    @Query("SELECT * FROM japanese_title")
    fun allTitleWithJapanese(): Flow<List<JapaneseTitle>>
}