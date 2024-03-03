package com.moragar.codechallengeyape.data.models

data class RecipesDto(
    val recipes: List<RecipeDto> = emptyList()
)

data class RecipeDto(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val description: String = "",
    val location: LocationDto = LocationDto(),
    val ingredients: List<IngredientDto> = emptyList()
)

data class IngredientDto(
    val name: String = "",
    val quantity: String = ""
)

data class LocationDto(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
