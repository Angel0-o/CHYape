package com.moragar.codechallengeyape.data.remote.source

import com.moragar.codechallengeyape.data.models.RecipeDto
import com.moragar.codechallengeyape.data.remote.api.RecipeApi
import com.moragar.codechallengeyape.domain.source.RecipeRemoteDataSource
import javax.inject.Inject

class RecipesRemoteDataSourceImpl @Inject constructor(
    private val api: RecipeApi
): RecipeRemoteDataSource {
    override suspend fun getRecipes(): List<RecipeDto> =
        api.getRecipes().recipes
}