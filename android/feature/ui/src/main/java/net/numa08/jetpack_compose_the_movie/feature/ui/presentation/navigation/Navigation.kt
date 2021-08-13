package net.numa08.jetpack_compose_the_movie.feature.ui.presentation.navigation

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.numa08.jetpack_compose_the_movie.feature.ui.presentation.home.HomePage
import net.numa08.jetpack_compose_the_movie.feature.ui.presentation.movie_detail.MovieDetailPage

sealed class BottomNavigationItem(val route: String, val icon: ImageVector) {
    object Home : BottomNavigationItem(route = "home", icon = Icons.Outlined.Home)
    object New : BottomNavigationItem(route = "new", icon = Icons.Outlined.PlayArrow)
    object Search : BottomNavigationItem(route = "search", icon = Icons.Outlined.Search)
    object Download : BottomNavigationItem(route = "download", icon = Icons.Outlined.Download)
}

val bottomNavigation = listOf(
    BottomNavigationItem.Home,
    BottomNavigationItem.New,
    BottomNavigationItem.Search,
    BottomNavigationItem.Download
)

sealed class Page {
    object MovieDetail : Page() {
        const val argKeyTitleId = "titleId"
        const val route = "movie/{$argKeyTitleId}"

        fun generateRoute(titleId: String) = "movie/$titleId"
    }
}

@Composable
fun MainApplicationNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationItem.Home.route,
        modifier = modifier,
    ) {
        composable(BottomNavigationItem.Home.route) {
            HomePage(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }
        composable(BottomNavigationItem.New.route) { Text(text = "new") }
        composable(BottomNavigationItem.Search.route) { Text(text = "search") }
        composable(BottomNavigationItem.Download.route) { Text(text = "download") }
        composable(Page.MovieDetail.route) {
            val titleId = requireNotNull(it.arguments?.getString(Page.MovieDetail.argKeyTitleId))
            MovieDetailPage(
                navController = navController,
                viewModel = hiltViewModel(),
                titleId = titleId
            )
        }
    }
}