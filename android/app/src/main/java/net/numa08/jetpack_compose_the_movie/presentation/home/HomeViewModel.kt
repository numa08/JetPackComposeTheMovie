package net.numa08.jetpack_compose_the_movie.presentation.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import net.numa08.jetpack_compose_the_movie.data.JapaneseTitle
import net.numa08.jetpack_compose_the_movie.data.TitleDao
import net.numa08.jetpack_compose_the_movie.data.TitleData
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val titleDao: TitleDao
) : ViewModel() {

    val allTitle: Flow<List<JapaneseTitle>> = titleDao.allTitleWithJapanese()
    val allGenres: Flow<List<String>> = titleDao.allGenres()

    fun titlesInGenre(genre: String): Pager<Int, TitleData> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = titleDao.titlesInGenre(genre).asPagingSourceFactory()
    )

}