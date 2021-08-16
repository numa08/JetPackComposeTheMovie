package net.numa08.jetpack_compose_the_movie.data.database.omdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class OMDBDao {
    @Transaction
    open suspend fun insertIfNotExistPoster(poster: Poster) {
        val isExist = findPoster(titleId = poster.titleId) != null
        if (!isExist) {
            insertPoster(poster)
        }
    }

    @Insert
    abstract suspend fun insertPoster(poster: Poster)

    @Query("SELECT * FROM poster WHERE titleId is :titleId")
    abstract suspend fun findPoster(titleId: String): Poster?
}
