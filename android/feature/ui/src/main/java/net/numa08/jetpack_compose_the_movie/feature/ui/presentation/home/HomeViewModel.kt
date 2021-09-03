package net.numa08.jetpack_compose_the_movie.feature.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import net.numa08.jetpack_compose_the_movie.domain.data.title.Genre
import net.numa08.jetpack_compose_the_movie.domain.data.title.Poster
import net.numa08.jetpack_compose_the_movie.domain.repository.TitleRepository
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val titleRepository: TitleRepository
) : ViewModel() {
    private val postersInGenreCache = ConcurrentHashMap<Genre, StateFlow<PagingData<Poster>>>()

    val genres: StateFlow<List<Genre>> =
        titleRepository.genresHasTitles.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun postersInGenre(genre: Genre): StateFlow<PagingData<Poster>> =
        postersInGenreCache.getOrPut(genre) {
            val flow = MutableStateFlow(PagingData.empty<Poster>())
            viewModelScope.launch {
                titleRepository.allPostersInGenre(genre).collect {
                    flow.value = it
                }
            }
            flow.cachedIn(viewModelScope)
                .stateIn(viewModelScope, SharingStarted.Eagerly, PagingData.empty())
        }

}