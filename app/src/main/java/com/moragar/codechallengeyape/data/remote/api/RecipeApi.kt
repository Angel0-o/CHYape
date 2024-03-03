package com.moragar.codechallengeyape.data.remote.api

import com.moragar.codechallengeyape.data.models.RecipesDto
import retrofit2.http.GET

interface RecipeApi {

    @GET("recetas")
    suspend fun getRecipes(): RecipesDto
}