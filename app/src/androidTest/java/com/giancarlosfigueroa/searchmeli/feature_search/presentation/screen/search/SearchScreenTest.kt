package com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.search

import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.giancarlosfigueroa.searchmeli.di.AppModule
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.MainActivity
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.navigation.AppScreens
import com.giancarlosfigueroa.searchmeli.feature_search.presentation.screen.SearchScreen
import com.giancarlosfigueroa.searchmeli.ui.theme.SearchmeliTheme
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class SearchScreenTest{

    @get:Rule(order=0)
    val hiltRule=HiltAndroidRule(this)

    @get:Rule(order=1)
    val composeRule= createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp(){
        hiltRule.inject()

        composeRule.clearAndSetContent {
            SearchmeliTheme() {
                val navController= rememberNavController()

                NavHost(navController =navController , startDestination = AppScreens.SearchScreen.route){
                    composable(route=AppScreens.SearchScreen.route){
                        SearchScreen(navController = navController)
                    }
                }
            }
        }
    }

    fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.clearAndSetContent(content: @Composable () -> Unit) {
        (this.activity.findViewById<ViewGroup>(android.R.id.content)?.getChildAt(0) as? ComposeView)?.setContent(content)
            ?: this.setContent(content)
    }
    //Test when valueSearch is blank
    @Test
    fun clickRequired(){
        composeRule.onNodeWithText("No puede ser vacio").assertDoesNotExist()
        composeRule.onNodeWithTag("buscarTag").performClick()
        composeRule.onNodeWithText("No puede ser vacio").assertIsDisplayed()
    }
}