package com.moragar.codechallengeyape.presentation.navigation

sealed class Screens(val route: String) {
    data object Map : Screens(route = "Map_Screen")
    data object Detail : Screens(route = "Detail_Screen")
    data object Home : Screens(route = "Home_Screen")
}
