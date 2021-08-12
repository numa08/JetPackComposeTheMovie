@file:Suppress("ClassName")

package net.numa08.jetpack_compose_the_movie.data.json

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
}
