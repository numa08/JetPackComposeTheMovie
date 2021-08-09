package net.numa08.jetpack_compose_the_movie.presentation.navigation

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.numa08.jetpack_compose_the_movie.presentation.home.HomePage

sealed class Screen(val route: String, val icon: ImageVector) {
    object Home : Screen(route = "home", icon = Icons.Outlined.Home)
    object New : Screen(route = "new", icon = Icons.Outlined.PlayArrow)
    object Search : Screen(route = "search", icon = Icons.Outlined.Search)
    object Download : Screen(route = "download", icon = Icons.Outlined.Download)
}

val screens = listOf(
    Screen.Home, Screen.New, Screen.Search, Screen.Download
)

@Composable
fun MainApplicationNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        composable(Screen.Home.route) {
            HomePage(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }
        composable(Screen.New.route) { Text(text = "new") }
        composable(Screen.Search.route) { Text(text = "search") }
        composable(Screen.Download.route) { Text(text = "download") }
    }
}