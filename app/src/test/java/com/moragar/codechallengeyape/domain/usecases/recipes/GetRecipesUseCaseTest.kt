package com.moragar.codechallengeyape.domain.usecases.recipes

import com.google.android.gms.maps.model.LatLng
import com.moragar.codechallengeyape.data.repositories.FakeRecipeRepository
import com.moragar.codechallengeyape.domain.entities.RecipeEntity
import io.mockk.MockKAnnotations
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetRecipesUseCaseTest{

    private val repository = FakeRecipeRepository()
    private lateinit var useCase: GetRecipesUseCase
    private lateinit var dispatcher: CoroutineDispatcher

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        dispatcher = Dispatchers.IO
        useCase = GetRecipesUseCase(repository, dispatcher)
    }

    @Test
    fun `response success but list empty`(): Unit = runBlocking {
        val response = repository.getRecipes()
        Assert.assertTrue(response.isEmpty())
    }

    @Test
    fun `response success with elements`(): Unit = runBlocking {
        val response = repository.getRecipes()
        repository.addFakeElements(RecipeEntity(
            id = "001",
            name = "Mojito",
            image = "https://www.thecocktaildb.com/images/media/drink/metwgh1606770327.jpg",
            location = LatLng(1.35, 103.87),
            description = "Refrescante cóctel que combina hojas de menta, azúcar, zumo de lima, soda, ron y hielo picado. Decorado con estilo y servido con pajita."
        ))
        Assert.assertTrue(response.isNotEmpty())
    }

}