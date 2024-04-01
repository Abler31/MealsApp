package com.abler31.pizzaapp.feature_menu.data.network

import com.abler31.pizzaapp.feature_menu.data.model.network.category.CategoriesModeNetworkEntity
import com.abler31.pizzaapp.feature_menu.data.model.network.meal.MealsModelEntity
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories(): Response<CategoriesModeNetworkEntity>

    @GET("api/json/v1/1/search.php?s")
    suspend fun getMeals(): Response<MealsModelEntity>

}