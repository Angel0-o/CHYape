package com.moragar.codechallengeyape.di

import com.moragar.codechallengeyape.data.remote.source.RecipesRemoteDataSourceImpl
import com.moragar.codechallengeyape.data.repositories.RecipeRepositoryImpl
import com.moragar.codechallengeyape.domain.repositories.RecipeRepository
import com.moragar.codechallengeyape.domain.source.RecipeRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindProductRepository(repositoryImpl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    fun bindProductRemoteDataSource(remoteDataSourceImpl: RecipesRemoteDataSourceImpl):RecipeRemoteDataSource
}