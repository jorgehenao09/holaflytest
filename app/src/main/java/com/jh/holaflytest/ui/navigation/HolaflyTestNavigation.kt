package com.jh.holaflytest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jh.holaflytest.ui.screens.SplashScreen

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

        }
    }
}
