package net.numa08.jetpack_compose_the_movie.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TitleDao {
    @Insert
    fun insertGenre(genre: GenreMaster)

    @Insert
    fun insertOriginalTitle(originalTitle: OriginalTitle)

    @Insert
    fun insertTitle(title: Title)

    @Insert
    fun insertGenreTitle(genreTitle: GenreTitle)

    @Query("SELECT * FROM japanese_title")
    fun allTitleWithJapanese(): Flow<List<JapaneseTitle>>
}