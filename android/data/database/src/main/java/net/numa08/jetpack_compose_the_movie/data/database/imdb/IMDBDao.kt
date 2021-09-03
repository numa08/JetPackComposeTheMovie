package net.numa08.jetpack_compose_the_movie.data.database.imdb

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface IMDBDao {
    @Insert
    fun insertGenre(genre: GenreMasterEntity)

    @Insert
    fun insertOriginalTitle(originalTitle: OriginalTitleEntity)

    @Insert
    fun insertTitle(title: TitleEntity)

    @Insert
    fun insertGenreTitle(genreTitle: GenreTitleEntity)

    @Query("SELECT * FROM japanese_title")
    fun allTitleWithJapanese(): Flow<List<JapaneseTitleView>>

    @Transaction
    @Query("SELECT * FROM title WHERE tconst IN (SELECT titleId FROM genre_title WHERE genre is :genre)")
    fun titlesInGenre(genre: String): DataSource.Factory<Int, TitleData>

    @Query("""
    SELECT
        * 
    FROM
        genre_master 
    WHERE
        genre  IN (
            SELECT
                genre                 
            FROM
                genre_title                 
            GROUP BY
                genre
        )
    """)
    fun genresHasTitles(): Flow<List<GenreMasterEntity>>
}