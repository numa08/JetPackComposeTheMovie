package net.numa08.jetpack_compose_the_movie.database.imdb

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface IMDBDao {
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

    @Transaction
    @Query("SELECT * FROM title WHERE tconst IN (SELECT titleId FROM genre_title WHERE genre is :genre)")
    fun titlesInGenre(genre: String): DataSource.Factory<Int, TitleData>

    @Query("SELECT genre FROM genre_master")
    fun allGenres(): Flow<List<String>>
}