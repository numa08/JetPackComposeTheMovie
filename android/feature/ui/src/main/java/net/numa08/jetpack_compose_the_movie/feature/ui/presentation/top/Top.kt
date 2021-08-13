package net.numa08.jetpack_compose_the_movie.feature.ui.presentation.top

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import net.numa08.jetpack_compose_the_movie.feature.ui.presentation.navigation.MainApplicationNavigation
import net.numa08.jetpack_compose_the_movie.feature.ui.presentation.navigation.bottomNavigation
import net.numa08.jetpack_compose_the_movie.feature.ui.presentation.theme.MainApplicationTheme

@Composable
fun TopPage() {
    TopPageContent()
}

@Composable
private fun TopPageContent() {
    val navController = rememberNavController()
    MainApplicationTheme {
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    bottomNavigation.forEach { screen ->
                        BottomNavigationItem(
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(text = "Home") })
                    }

                }
            }
        ) { innerPadding ->
            MainApplicationNavigation(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview
@Composable
fun TopPagePreview() {
    TopPageContent()
}