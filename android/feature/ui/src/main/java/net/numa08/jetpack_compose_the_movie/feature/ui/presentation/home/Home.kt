package net.numa08.jetpack_compose_the_movie.feature.ui.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import net.numa08.jetpack_compose_the_movie.feature.ui.presentation.navigation.BottomNavigationItem
import net.numa08.jetpack_compose_the_movie.feature.ui.presentation.navigation.Page
import net.numa08.jetpack_compose_the_movie.feature.ui.widget.GenreAndPosterRow

@Composable
fun HomePage(navController: NavController, viewModel: HomeViewModel) {
    HomePageContent(navController = navController, viewModel = viewModel)
}

@Composable
fun HomePageContent(navController: NavController, viewModel: HomeViewModel) {
    val genres by viewModel.allGenres.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = { Icon(Icons.Filled.Home, contentDescription = null) }
            )
        }
    ) {
        LazyColumn {
            items(genres) {
                val posters = viewModel.postersInGenre(it)
                GenreAndPosterRow(
                    genre = it.localizedGenre,
                    posters = posters,
                    onClickItem = { poster ->
                        navController.navigate(Page.MovieDetail.generateRoute(poster.titleId.id)) {
                            popUpTo(BottomNavigationItem.Home.route)
                        }
                    })
            }
        }
    }
}

@Preview
@Composable
fun HomePagePreview() {
//    HomePageContent()
}