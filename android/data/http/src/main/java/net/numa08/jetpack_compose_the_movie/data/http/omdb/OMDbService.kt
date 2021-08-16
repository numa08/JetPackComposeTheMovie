package net.numa08.jetpack_compose_the_movie.data.http.omdb

import net.numa08.jetpack_compose_the_movie.data.json.JsonType
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbService {

    @GET("/")
    suspend fun getTitle(@Query("i") titleId: String): JsonType.OMDBResponse
}