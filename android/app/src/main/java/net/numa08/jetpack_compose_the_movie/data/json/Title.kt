package net.numa08.jetpack_compose_the_movie.data.json

import kotlinx.serialization.Serializable

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
)

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
)