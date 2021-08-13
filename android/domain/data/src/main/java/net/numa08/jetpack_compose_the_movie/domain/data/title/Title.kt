package net.numa08.jetpack_compose_the_movie.domain.data.title

import java.net.URL

data class TitleId(
    val id: String
)

data class Genre(
    val genre: String,
    val localizedGenre: String
)

data class TitleDetail(
    val titleId: TitleId,
    val originalTitle: String,
    val localizedTitle: String? = null,
    val posterUrl: URL? = null,
    val genres: List<Genre> = emptyList(),
)

data class Poster(
    val titleId: TitleId,
    val posterUrl: URL? = null
)

data class PosterInGenre(
    val genre: Genre,
    val posters: List<Poster> = emptyList()
)