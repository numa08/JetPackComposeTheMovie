package net.numa08.jetpack_compose_the_movie.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.numa08.jetpack_compose_the_movie.data.database.imdb.IMDBDao
import net.numa08.jetpack_compose_the_movie.domain.data.title.Genre
import net.numa08.jetpack_compose_the_movie.domain.data.title.Poster
import net.numa08.jetpack_compose_the_movie.domain.data.title.PosterInGenre
import net.numa08.jetpack_compose_the_movie.domain.data.title.TitleId

class TitleRepository(
    private val imdbDao: IMDBDao
) {

    fun allGenres(): Flow<List<Genre>> = imdbDao.allGenres().map { genres ->
        genres.map {
            Genre(
                genre = it.genre,
                localizedGenre = it.jaGenre
            )
        }
    }

    fun allPostersInGenre(genre: Genre): Flow<PagingData<Poster>> {
        val pagingSource = imdbDao
            .titlesInGenre(genre = genre.genre)
            .map {
                Poster(
                    titleId = TitleId(it.title.titleId),
                    posterUrl = null
                )
            }
            .asPagingSourceFactory()
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = pagingSource
        ).flow
    }

}