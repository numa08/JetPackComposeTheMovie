package net.numa08.jetpack_compose_the_movie.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.numa08.jetpack_compose_the_movie.data.database.imdb.IMDBDao
import net.numa08.jetpack_compose_the_movie.data.database.omdb.OMDBDao
import net.numa08.jetpack_compose_the_movie.data.http.omdb.OMDbService
import net.numa08.jetpack_compose_the_movie.domain.data.title.Genre
import net.numa08.jetpack_compose_the_movie.domain.data.title.Poster
import net.numa08.jetpack_compose_the_movie.domain.data.title.TitleId
import java.net.URL

class TitleRepository(
    private val imdbDao: IMDBDao,
    private val omdbDao: OMDBDao,
    private val omDbService: OMDbService
) {

    fun allGenres(): Flow<List<Genre>> = imdbDao.allGenres().map { genres ->
        genres.map {
            Genre(
                genre = it.genre,
                localizedGenre = it.jaGenre
            )
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    fun allPostersInGenre(genre: Genre): Flow<PagingData<Poster>> {
        val pagingSource = imdbDao
            .titlesInGenre(genre = genre.genre)
            .map {
                Poster(
                    titleId = TitleId(it.title.titleId),
                    posterUrl = it.poster?.let { p -> p.poster?.let { u -> URL(u) } }
                )
            }
            .asPagingSourceFactory()
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = pagingSource,
            remoteMediator = PosterMediator(genre = genre, imdbDao, omdbDao, omDbService)
        ).flow
    }

}