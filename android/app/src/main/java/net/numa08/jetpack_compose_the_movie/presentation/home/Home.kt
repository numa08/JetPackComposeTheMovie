package net.numa08.jetpack_compose_the_movie.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun HomePage(navController: NavController, viewModel: HomeViewModel) {
    HomePageContent(viewModel = viewModel)
}

@Composable
fun HomePageContent(viewModel: HomeViewModel) {
    val titles by viewModel.allTitle.collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = { Icon(Icons.Filled.Home, contentDescription = null) }
            )
        }
    ) {
        LazyColumn {
            items(titles) {
                Text(text = it.localizedTitle ?: it.originalTitle ?: "No title")
            }
        }
    }
}

@Preview
@Composable
fun HomePagePreview() {
//    HomePageContent()
}