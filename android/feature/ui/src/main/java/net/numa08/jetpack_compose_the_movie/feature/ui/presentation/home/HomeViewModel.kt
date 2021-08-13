package net.numa08.jetpack_compose_the_movie.feature.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import net.numa08.jetpack_compose_the_movie.domain.data.title.Genre
import net.numa08.jetpack_compose_the_movie.domain.data.title.Poster
import net.numa08.jetpack_compose_the_movie.domain.repository.TitleRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val titleRepository: TitleRepository
) : ViewModel() {

    val allGenres: Flow<List<Genre>> = titleRepository.allGenres()

    fun postersInGenre(genre: Genre): Flow<PagingData<Poster>>
        = titleRepository.allPostersInGenre(genre = genre)
}