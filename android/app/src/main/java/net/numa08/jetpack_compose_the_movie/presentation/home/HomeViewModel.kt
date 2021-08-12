package net.numa08.jetpack_compose_the_movie.presentation.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import net.numa08.jetpack_compose_the_movie.data.database.imdb.IMDBDao
import net.numa08.jetpack_compose_the_movie.data.database.imdb.JapaneseTitle
import net.numa08.jetpack_compose_the_movie.data.database.imdb.TitleData
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val titleDao: IMDBDao
) : ViewModel() {

    val allTitle: Flow<List<JapaneseTitle>> = titleDao.allTitleWithJapanese()
    val allGenres: Flow<List<String>> = titleDao.allGenres()

    fun titlesInGenre(genre: String): Pager<Int, TitleData> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = titleDao.titlesInGenre(genre).asPagingSourceFactory()
    )

}