package net.numa08.jetpack_compose_the_movie.feature.ui.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
fun HomePageContent(@Suppress("unused") navController: NavController, viewModel: HomeViewModel) {
    val genres by viewModel.genres.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = { Icon(Icons.Filled.Home, contentDescription = null) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            genres
                .forEach {
                    Text(text = it.localizedGenre)
                }
        }
    }
}

@Preview
@Composable
fun HomePagePreview() {
//    HomePageContent()
}