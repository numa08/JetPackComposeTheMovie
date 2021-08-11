package net.numa08.jetpack_compose_the_movie.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import net.numa08.jetpack_compose_the_movie.widget.TitleAndImageRow

@Composable
fun HomePage(@Suppress("UNUSED_PARAMETER") navController: NavController, viewModel: HomeViewModel) {
    HomePageContent(viewModel = viewModel)
}

@Composable
fun HomePageContent(viewModel: HomeViewModel) {
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
                TitleAndImageRow(title = it, images = titles, onClickItem = {})
            }
        }
    }
}

@Preview
@Composable
fun HomePagePreview() {
//    HomePageContent()
}