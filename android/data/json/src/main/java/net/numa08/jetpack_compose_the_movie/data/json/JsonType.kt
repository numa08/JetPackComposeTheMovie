@file:Suppress("ClassName")

package net.numa08.jetpack_compose_the_movie.data.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

sealed class JsonType {

    companion object {
        inline fun <reified T : JsonType> fromJsonString(string: String): T =
            Json.decodeFromString(string)

    }

    @Serializable
    data class Genre(
        val genre: String,
        val ja: String
    ) : JsonType()

    @Serializable
    data class GenreTitle(
        val genre: String,
        val titleId: String
    ) : JsonType()

    @Serializable
    data class TitleAka(
        val titleId: String,
        val ordering: Int,
        val title: String?,
        val region: String?,
        val language: String?,
        val types: String?,
        val attributes: String?,
        val isOriginalTitle: Boolean
    ) : JsonType()

    @Serializable
    data class TitleBasic(
        val tconst: String,
        val titleType: String,
        val primaryTitle: String?,
        val originalTitle: String?,
        val isAdult: Boolean,
        val startYear: Float?,
        val endYear: Float?,
        val runtimeMinutes: Float?,
        val genres: String?
    ) : JsonType()

    @Serializable
    data class OMDBResponse(
        @SerialName("Title")
        val title: String,

        @SerialName("Year")
        val year: String,

        @SerialName("Rated")
        val rated: String,

        @SerialName("Released")
        val released: String,

        @SerialName("Runtime")
        val runtime: String,

        @SerialName("Genre")
        val genre: String,

        @SerialName("Director")
        val director: String,

        @SerialName("Writer")
        val writer: String,

        @SerialName("Actors")
        val actors: String,

        @SerialName("Plot")
        val plot: String,

        @SerialName("Language")
        val language: String,

        @SerialName("Country")
        val country: String,

        @SerialName("Awards")
        val awards: String,

        @SerialName("Poster")
        val poster: String,

        @SerialName("Ratings")
        val ratings: List<Rating>,

        @SerialName("Metascore")
        val metascore: String,

        val imdbRating: String,
        val imdbVotes: String,
        val imdbID: String,

        @SerialName("Type")
        val type: String,

        @SerialName("DVD")
        val dvd: String,

        @SerialName("BoxOffice")
        val boxOffice: String,

        @SerialName("Production")
        val production: String,

        @SerialName("Website")
        val website: String,

        @SerialName("Response")
        val response: String
    ) : JsonType()

    @Serializable
    data class Rating(
        @SerialName("Source")
        val source: String,

        @SerialName("Value")
        val value: String
    ) : JsonType()

}
