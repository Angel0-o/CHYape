package com.moragar.codechallengeyape.di

import com.moragar.codechallengeyape.data.remote.api.RecipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {


    @Singleton
    @Provides
    fun provideInterceptor(): OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY))

    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient.Builder) : Retrofit =
        Retrofit.Builder()
            .baseUrl("http://demo5677992.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
            .build()

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): RecipeApi =
        retrofit.create(RecipeApi::class.java)
}