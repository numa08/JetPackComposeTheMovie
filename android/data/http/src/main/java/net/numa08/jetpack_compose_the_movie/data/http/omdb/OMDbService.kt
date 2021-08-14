package net.numa08.jetpack_compose_the_movie.data.http.omdb

import kotlinx.coroutines.flow.Flow
import net.numa08.jetpack_compose_the_movie.data.json.JsonType
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbService {

    @GET
    fun getTitle(@Query("i") titleId: String): Flow<JsonType.OMDBResponse>
}