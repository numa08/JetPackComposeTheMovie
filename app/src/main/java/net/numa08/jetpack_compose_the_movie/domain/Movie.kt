package net.numa08.jetpack_compose_the_movie.domain

import java.time.Year

data class Movie(
    val tconst: Tconst,
    val primaryTitle: String?,
    val primaryLocalizedTitle: String?,
    val originalTitle: String?,
    val isAdult: Boolean,
    val startYear: Year?,
    val endYear: Year?,
    val runtimeMinutes: Long?,
    val genres: List<Genre>
)

enum class Genre

data class Tconst(
    val value: String
)
