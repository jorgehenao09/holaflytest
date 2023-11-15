package com.jh.holaflytest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jh.holaflytest.ui.screens.comicDetail.ComicDetailScreen
import com.jh.holaflytest.ui.screens.home.HomeScreen
import com.jh.holaflytest.ui.screens.splash.SplashScreen
import com.jh.holaflytest.ui.screens.superHeroComics.SuperHeroComicsScreen

/**
 * Created by Jorge Henao on 13/11/23.
 */
@Composable
fun HolaflyTestNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.name
    ) {
        composable(Screens.SplashScreen.name) {
            SplashScreen { route ->
                navController.navigate(route)
            }
        }
        composable(Screens.HomeScreen.name) {
            HomeScreen(
                menuOptionsViewModel = hiltViewModel()
            ) { route ->
                navController.navigate(route)
            }
        }
        composable(
            route = Screens.SuperHeroComics.name + "/{superHeroId}/{superHeroName}",
            arguments = listOf(
                navArgument(name = "superHeroId") { type = NavType.LongType },
                navArgument(name = "superHeroName") { type = NavType.StringType }
            )
        ) {
            SuperHeroComicsScreen(
                superHeroComicsViewModel = hiltViewModel(),
                superHeroId = it.arguments?.getLong("superHeroId") ?: 0,
                superHeroName = it.arguments?.getString("superHeroName") ?: ""
            ) { route ->
                navController.navigate(route)
            }
        }
        composable(
            route = Screens.ComicDetails.name + "/{comicId}",
            arguments = listOf(
                navArgument(name = "comicId") { type = NavType.StringType }
            )
        ) {
            ComicDetailScreen(
                comicDetailViewModel = hiltViewModel(),
                comicId = it.arguments?.getString("comicId") ?: ""
            )
        }
    }
}
