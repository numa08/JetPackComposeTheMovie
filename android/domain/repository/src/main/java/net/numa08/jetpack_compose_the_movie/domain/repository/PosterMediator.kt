package net.numa08.jetpack_compose_the_movie.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.numa08.jetpack_compose_the_movie.data.database.imdb.IMDBDao
import net.numa08.jetpack_compose_the_movie.data.database.omdb.OMDBDao
import net.numa08.jetpack_compose_the_movie.data.http.omdb.OMDbService
import net.numa08.jetpack_compose_the_movie.domain.data.title.Genre
import net.numa08.jetpack_compose_the_movie.domain.data.title.Poster
import net.numa08.jetpack_compose_the_movie.data.database.omdb.Poster as OMDBPoster

@OptIn(ExperimentalPagingApi::class)
class PosterMediator(
    private val genre: Genre,
    private val imdbDao: IMDBDao,
    private val omdbDao: OMDBDao,
    private val omDbService: OMDbService
) : RemoteMediator<Int, Poster>() {

    private val scope = CoroutineScope(Job() + Dispatchers.IO)

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Poster>): MediatorResult {
        val posters = state.pages.map { it.data }.flatten()
        posters
            .filter { it.posterUrl == null }
            .map { it.titleId.id }
            .toSet()
            .forEach { titleId ->
                scope.launch {
                    val omdbTitle = omDbService.getTitle(titleId = titleId)
                    val omdbPoster =
                        OMDBPoster(titleId = omdbTitle.imdbID, poster = omdbTitle.poster)
                    omdbDao.insertIfNotExistPoster(omdbPoster)
                }
            }

        return MediatorResult.Success(endOfPaginationReached = false)
    }

}