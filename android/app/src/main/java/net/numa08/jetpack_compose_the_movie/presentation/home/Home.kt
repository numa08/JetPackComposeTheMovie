package net.numa08.jetpack_compose_the_movie.presentation.home

import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import net.numa08.jetpack_compose_the_movie.widget.TitleAndImageRow

@Composable
fun HomePage(navController: NavController) {
    HomePageContent()
}

@Composable
fun HomePageContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = { Icon(Icons.Filled.Home, contentDescription = null) }
            )
        }
    ) {
        
    }
}

@Preview
@Composable
fun HomePagePreview() {
    HomePageContent()
}