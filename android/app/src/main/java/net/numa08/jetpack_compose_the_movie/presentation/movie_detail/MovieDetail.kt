package net.numa08.jetpack_compose_the_movie.presentation.movie_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MovieDetailPage(
    @Suppress("UNUSED_PARAMETER") navController: NavController,
    @Suppress("UNUSED_PARAMETER") viewModel: MovieDetailViewModel,
    @Suppress("UNUSED_PARAMETER") titleId: String
) {
    Text(text = "movie detail")
}