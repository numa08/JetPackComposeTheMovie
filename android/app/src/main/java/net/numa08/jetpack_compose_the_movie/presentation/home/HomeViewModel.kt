package net.numa08.jetpack_compose_the_movie.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import net.numa08.jetpack_compose_the_movie.data.JapaneseTitle
import net.numa08.jetpack_compose_the_movie.data.TitleDao
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val titleDao: TitleDao
) : ViewModel() {

    val allTitle: Flow<List<JapaneseTitle>> = titleDao.allTitleWithJapanese()

}