package com.moragar.codechallengeyape.domain.source

import com.moragar.codechallengeyape.data.models.RecipeDto

interface RecipeRemoteDataSource {
    suspend fun getRecipes(): List<RecipeDto>
}