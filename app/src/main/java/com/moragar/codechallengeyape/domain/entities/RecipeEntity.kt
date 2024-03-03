package com.moragar.codechallengeyape.domain.entities

import com.google.android.gms.maps.model.LatLng

data class RecipeEntity(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val description: String = "",
    val location: LatLng,
    val ingredients: List<IngredientEntity> = emptyList()
)

data class IngredientEntity(
    val name: String = "",
    val quantity: String = "",
)