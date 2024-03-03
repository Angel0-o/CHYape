package com.moragar.codechallengeyape.presentation.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.LatLng
import com.moragar.codechallengeyape.domain.entities.RecipeEntity
import com.moragar.codechallengeyape.presentation.ui.theme.CodeChallengeYapeTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateHomeScreenWhenSuccessButListEmpty(){
        composeTestRule.setContent {
            CodeChallengeYapeTheme {
                HomeScreen(
                    recipeList = listOf(),
                    navController = rememberNavController(),
                    requestState = HomeViewModel.RequestState.Success
                )
            }
        }

        composeTestRule.onNodeWithTag("GenericSection").assertIsDisplayed()
        composeTestRule.onNodeWithText("No hay resultados").assertIsDisplayed()
    }

    @Test
    fun validateHomeScreenWhenError(){
        composeTestRule.setContent {
            CodeChallengeYapeTheme {
                HomeScreen(
                    recipeList = listOf(),
                    navController = rememberNavController(),
                    requestState = HomeViewModel.RequestState.Error
                )
            }
        }

        composeTestRule.onNodeWithTag("GenericSection").assertIsDisplayed()
        composeTestRule.onNodeWithText("Algo salio mal, por favor intenta de nuevo").assertIsDisplayed()
    }

    @Test
    fun validateHomeScreenWhenIsLoading(){
        composeTestRule.setContent {
            CodeChallengeYapeTheme {
                HomeScreen(
                    recipeList = listOf(),
                    navController = rememberNavController(),
                    requestState = HomeViewModel.RequestState.Loading
                )
            }
        }

        composeTestRule.onNodeWithTag("MyProgressBar").assertIsDisplayed()
    }

    @Test
    fun validateHomeScreenWhenSuccessAndListIsNotEmpty(){
        val fakeList: List<RecipeEntity> = listOf(
        RecipeEntity(
            id = "001",
            name = "Mojito",
            image = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg",
            location = LatLng(1.35, 103.87),
            description = "Refrescante cóctel que combina hojas de menta, azúcar, zumo de lima, soda, ron y hielo picado. Decorado con estilo y servido con pajita."
        ),
        RecipeEntity(
            id = "002",
            name = "Paella",
            image = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg",
            location = LatLng(1.35, 103.87),
            description = "Refrescante cóctel que combina hojas de menta, azúcar, zumo de lima, soda, ron y hielo picado. Decorado con estilo y servido con pajita."
        )
    )
        composeTestRule.setContent {
            CodeChallengeYapeTheme {
                HomeScreen(
                    recipeList = fakeList,
                    navController = rememberNavController(),
                    requestState = HomeViewModel.RequestState.Success
                )
            }
        }

        composeTestRule.onNodeWithTag("RecipeSection").assertIsDisplayed()
    }
}