package com.moragar.codechallengeyape.domain.usecases.recipes

import androidx.lifecycle.ViewModel
import com.moragar.codechallengeyape.domain.entities.RecipeEntity
import com.moragar.codechallengeyape.domain.repositories.RecipeRepository
import com.moragar.codechallengeyape.domain.usecases.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository,
    dispatcher: CoroutineDispatcher
): UseCase<List<RecipeEntity>, Unit>(dispatcher) {

    override suspend fun run(input: Unit?): List<RecipeEntity> =
        repository.getRecipes()
}