package com.moragar.codechallengeyape.domain.usecases.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<T, in Input>(private val dispatcher: CoroutineDispatcher) {
    protected abstract suspend fun run(input: Input? = null): T

    suspend operator fun invoke(input: Input? = null): Response<T> = withContext(dispatcher){
        try{
            Response.Success(run(input))
        }catch (error: Exception){
            Response.Failure(error)
        }
    }
}