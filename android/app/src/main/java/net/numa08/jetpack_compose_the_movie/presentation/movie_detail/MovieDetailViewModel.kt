package net.numa08.jetpack_compose_the_movie.presentation.movie_detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import net.numa08.jetpack_compose_the_movie.data.TitleDao
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val titleDao: TitleDao
) : ViewModel() {
}