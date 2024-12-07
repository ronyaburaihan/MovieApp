package com.englesoft.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.englesoft.movieapp.presentation.details.DetailsScreen
import com.englesoft.movieapp.presentation.home.HomeScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("search/{imdbID}") {
            DetailsScreen(
                navController = navController,
                imdbID = it.arguments?.getString("imdbID") ?: ""
            )
        }
    }
}