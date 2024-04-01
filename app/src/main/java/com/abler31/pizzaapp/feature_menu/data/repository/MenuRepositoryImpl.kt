package com.abler31.pizzaapp.feature_menu.data.repository

import android.util.Log
import com.abler31.pizzaapp.feature_menu.data.mappers.CategoriesModelToDomainMapper
import com.abler31.pizzaapp.feature_menu.data.mappers.CategoryEntityToDomainMapper
import com.abler31.pizzaapp.feature_menu.data.mappers.MealEntityToDomainMapper
import com.abler31.pizzaapp.feature_menu.data.mappers.MealsModelToDomainMapper
import com.abler31.pizzaapp.feature_menu.data.model.category.CategoriesModelEntity
import com.abler31.pizzaapp.feature_menu.data.model.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.data.network.NetworkApi
import com.abler31.pizzaapp.feature_menu.domain.Resource
import com.abler31.pizzaapp.feature_menu.domain.model.category.Categories
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meals
import com.abler31.pizzaapp.feature_menu.domain.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MenuRepositoryImpl(private val networkApi: NetworkApi): MenuRepository {
    override suspend fun getCategories(): Resource<Categories> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<CategoriesModelEntity> = networkApi.getCategories()
                if (response.isSuccessful) {
                    Log.d("test", "категории загрузились")
                    val data = CategoriesModelToDomainMapper(CategoryEntityToDomainMapper())
                        .transform(response.body()!!)
                    Resource.Success(data = data)
                } else {
                    Log.d("test", "категории не загрузились")
                    Resource.Error(errorMessage = response.errorBody().toString())
                }

            } catch (e: HttpException) {
                Resource.Error(errorMessage = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                Resource.Error("Please check your network connection")
            } catch (e: Exception) {
                Resource.Error(errorMessage = "Something went wrong")
            }
        }
    }

    override suspend fun getMeals(): Resource<MealsModelEntity> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<MealsModelEntity> = networkApi.getMeals()
                if (response.isSuccessful) {
                    Log.d("test", "response в getMeals ${response.body()!!.mealEntities[0].strMeal}")
                    Log.d("test", "блюда загрузились")

                    Log.d("test", "после маппера")
                    Resource.Success(data = response.body()!!)
                } else {
                    Log.d("test", "блюда не загрузились")
                    Resource.Error(errorMessage = response.errorBody().toString())
                }

            } catch (e: HttpException) {
                Resource.Error(errorMessage = e.message ?: "Something went wrong in getMeals")
            } catch (e: IOException) {
                Resource.Error("Please check your network connection")
            } catch (e: Exception) {
                Resource.Error(errorMessage = "Something went wrong in getMeals")
            }
        }
    }
}