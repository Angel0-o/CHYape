package com.moragar.codechallengeyape.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moragar.codechallengeyape.presentation.home.DetailScreen
import com.moragar.codechallengeyape.presentation.home.HomeScreen
import com.moragar.codechallengeyape.presentation.home.HomeViewModel
import com.moragar.codechallengeyape.presentation.home.MapScreen

@Composable
fun RecipeNavHost(
    navController: NavHostController = rememberNavController(),
    homeViewState: HomeViewModel.HomeState,
    onAction: (HomeViewModel.Action) -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Map.route) {
            MapScreen(
                recipeEntity = homeViewState.currentRecipe,
                navController = navController
            )
        }
        composable(route = Screens.Detail.route) {
            DetailScreen(
                recipeEntity = homeViewState.currentRecipe,
                navController = navController
            )
        }
        composable(route = Screens.Home.route) {
            HomeScreen(
                recipeList = homeViewState.recipeList,
                requestState = homeViewState.requestState,
                onAction = onAction,
                navController = navController
            )
        }
    }

}