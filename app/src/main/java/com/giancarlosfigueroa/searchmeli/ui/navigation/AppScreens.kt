package com.giancarlosfigueroa.searchmeli.ui.navigation

sealed class AppScreens(val route:String) {
    object SplashScreen:AppScreens("splash_screen")
    object SearchScreen:AppScreens("search_screen")
    object ResultsScreen:AppScreens("results_screen")
    object DetailScreen:AppScreens("detail_screen")
}