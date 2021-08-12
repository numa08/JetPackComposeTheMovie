package net.numa08.jetpack_compose_the_movie.presentation.home

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
import net.numa08.jetpack_compose_the_movie.presentation.navigation.BottomNavigationItem
import net.numa08.jetpack_compose_the_movie.presentation.navigation.Page
import net.numa08.jetpack_compose_the_movie.widget.GenreAndImageRow

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
                val titles = viewModel.titlesInGenre(it)
                GenreAndImageRow(genre = it, images = titles, onClickItem = { title ->
                    navController.navigate(Page.MovieDetail.generateRoute(title.originalTitle.titleId)) {
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