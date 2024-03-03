package com.moragar.codechallengeyape.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.moragar.codechallengeyape.domain.entities.RecipeEntity
import com.moragar.codechallengeyape.domain.usecases.recipes.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
): ViewModel() {
    val _homeModelStore = MutableStateFlow(HomeState())
    val homeModelStore = _homeModelStore.asStateFlow()

    data class HomeState(
        val recipeList: List<RecipeEntity> = emptyList(),
        val currentRecipe: RecipeEntity = RecipeEntity(location = LatLng(1.35, 103.87)),
        val requestState: RequestState = RequestState.Loading
    )

    private fun getRecipes() = viewModelScope.launch(Main) {
        _homeModelStore.value = _homeModelStore.value.copy(requestState = RequestState.Loading)
        getRecipesUseCase().handleResponse(
            onSuccess = {recipeEntities ->
                _homeModelStore.value = _homeModelStore.value.copy(
                    requestState = RequestState.Success,
                    recipeList = recipeEntities)
            },
            onFailure = {
                _homeModelStore.value = _homeModelStore.value.copy(requestState = RequestState.Error)
            }
        )
    }

    private fun setCurrentRecipe(recipe: RecipeEntity){
        _homeModelStore.value = _homeModelStore.value.copy(currentRecipe = recipe)
    }

    fun onAction(action: Action){
        when(action){
            is Action.GetRecipes -> getRecipes()
            is Action.SetCurrentRecipe -> setCurrentRecipe(action.recipe)
        }
    }

    sealed class Action{
        data object GetRecipes: Action()
        data class SetCurrentRecipe(val recipe: RecipeEntity): Action()
    }

    sealed class RequestState{
        data object Success: RequestState()
        data object Error: RequestState()
        data object Loading: RequestState()
    }
}