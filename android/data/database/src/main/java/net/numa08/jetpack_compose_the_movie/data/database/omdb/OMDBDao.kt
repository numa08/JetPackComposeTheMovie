package net.numa08.jetpack_compose_the_movie.data.database.omdb

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OMDBDao {
    @Query("SELECT * FROM poster WHERE titleId is :titleId")
    fun findPoster(titleId: String): Flow<Poster>
}
