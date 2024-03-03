package com.moragar.codechallengeyape.data.repositories

import com.moragar.codechallengeyape.domain.entities.RecipeEntity
import com.moragar.codechallengeyape.domain.repositories.RecipeRepository

class FakeRecipeRepository: RecipeRepository {

    private var fakeList = mutableListOf<RecipeEntity>()

    override suspend fun getRecipes(): List<RecipeEntity> =
        fakeList.toList()

    fun addFakeElements(element: RecipeEntity){
        fakeList.add(element)
    }
}