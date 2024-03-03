package com.moragar.codechallengeyape.domain.usecases.base

sealed class Response<out T>{
    data class Success<out T>(val response: T) : Response<T>()
    data class Failure(val error: Exception) : Response<Nothing>()
    data class Loading(val isLoading: Boolean) : Response<Nothing>()

    fun handleResponse(
        onSuccess: (T) -> Unit,
        onFailure: (Exception) -> Unit = {},
        onLoading: (Boolean) -> Unit = {}
    ): Unit =
        when(this){
            is Success -> onSuccess(response)
            is Failure -> onFailure(error)
            is Loading -> onLoading(isLoading)
        }
}