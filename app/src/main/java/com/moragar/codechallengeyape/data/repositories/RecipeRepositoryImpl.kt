package com.moragar.codechallengeyape.data.repositories

import com.moragar.codechallengeyape.data.mappers.toEntitu
import com.moragar.codechallengeyape.domain.entities.RecipeEntity
import com.moragar.codechallengeyape.domain.repositories.RecipeRepository
import com.moragar.codechallengeyape.domain.source.RecipeRemoteDataSource
import javax.inject.Inject

class RecipeRepositoryImpl@Inject constructor(
    private val remoteDataSource: RecipeRemoteDataSource
): RecipeRepository {
    override suspend fun getRecipes(): List<RecipeEntity> =
        remoteDataSource.getRecipes().map { it.toEntitu() }
}