package net.numa08.jetpack_compose_the_movie.presentation.movie_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MovieDetailPage(
    navController: NavController,
    viewModel: MovieDetailViewModel,
    titleId: String
) {
    Text(text = "movie detail")
}