package com.abler31.pizzaapp.di

import com.abler31.pizzaapp.feature_menu.data.database.CategoryDao
import com.abler31.pizzaapp.feature_menu.data.database.MealDao
import com.abler31.pizzaapp.feature_menu.data.network.NetworkApi
import com.abler31.pizzaapp.feature_menu.data.repository.MenuRepositoryImpl
import com.abler31.pizzaapp.feature_menu.domain.repository.MenuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesBaseUrl(): String = "https://themealdb.com/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideNetworkApi(retrofit: Retrofit): NetworkApi = retrofit.create(NetworkApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(networkApi: NetworkApi, categoryDao: CategoryDao, mealDao: MealDao): MenuRepository{
        return MenuRepositoryImpl(networkApi, mealDao, categoryDao)
    }

}