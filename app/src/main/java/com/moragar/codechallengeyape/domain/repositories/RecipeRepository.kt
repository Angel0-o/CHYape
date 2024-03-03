package com.moragar.codechallengeyape.domain.repositories

import com.moragar.codechallengeyape.domain.entities.RecipeEntity

interface RecipeRepository {

    suspend fun getRecipes(): List<RecipeEntity>
}