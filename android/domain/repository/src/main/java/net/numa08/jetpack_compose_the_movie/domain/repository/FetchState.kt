package net.numa08.jetpack_compose_the_movie.domain.repository

sealed class FetchState<T> {
    object Init : FetchState<Any>()
    object Loading : FetchState<Any>()
    data class Success<T>(val data: T) : FetchState<T>()
    data class Failed<T>(val exception: Exception) : FetchState<T>()
}
