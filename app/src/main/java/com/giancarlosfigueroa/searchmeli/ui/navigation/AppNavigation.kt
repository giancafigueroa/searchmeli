package com.giancarlosfigueroa.searchmeli.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.giancarlosfigueroa.searchmeli.ui.screen.DetailScreen
import com.giancarlosfigueroa.searchmeli.ui.screen.ResultsScreen
import com.giancarlosfigueroa.searchmeli.ui.screen.SearchScreen
import com.giancarlosfigueroa.searchmeli.ui.screen.SplashScreen

@Composable
fun AppNavigation(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route ){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.SearchScreen.route){
            SearchScreen(navController)
        }
        composable(AppScreens.ResultsScreen.route){
            ResultsScreen(navController)
        }
        composable(AppScreens.DetailScreen.route){
            DetailScreen(navController)
        }
    }
}