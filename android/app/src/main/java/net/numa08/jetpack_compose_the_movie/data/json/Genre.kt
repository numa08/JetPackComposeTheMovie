package net.numa08.jetpack_compose_the_movie.data.json

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val genre: String,
    val ja: String
)

@Serializable
data class GenreTitle(
    val genre: String,
    val titleId: String
)