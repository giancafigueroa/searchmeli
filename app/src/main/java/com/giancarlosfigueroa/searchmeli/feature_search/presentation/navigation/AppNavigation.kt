package com.giancarlosfigueroa.searchmeli.feature_search.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.DetailScreen
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.ResultsScreen
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.SearchScreen
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreens.SearchScreen.route) {
            SearchScreen(navController)
        }
        composable(
            route=AppScreens.ResultsScreen.route +
                    "?q={q}",
            arguments = listOf(
                navArgument(
                    name="q"
                ){
                    type= NavType.StringType
                    defaultValue=""
                }
            )
        ) {
            val searchValue=it.arguments?.getString("q")
            ResultsScreen(navController, searchValue )
        }
        composable(
            route=AppScreens.DetailScreen.route+
                    "?id={id}",
            arguments = listOf(
                navArgument(
                    name="id"
                ){
                    type= NavType.StringType
                    defaultValue=""
                }
            )
        ) {
            val id=it.arguments?.getString("id")

            DetailScreen(navController,id)
        }
    }
}