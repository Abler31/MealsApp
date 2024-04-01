package com.abler31.pizzaapp.feature_menu.data.repository

import android.util.Log
import com.abler31.pizzaapp.feature_menu.data.database.CategoryDao
import com.abler31.pizzaapp.feature_menu.data.database.MealDao
import com.abler31.pizzaapp.feature_menu.data.mappers.CategoriesModelToDomainMapper
import com.abler31.pizzaapp.feature_menu.data.mappers.CategoryEntityToDomainMapper
import com.abler31.pizzaapp.feature_menu.data.model.database.category.CategoryData
import com.abler31.pizzaapp.feature_menu.data.model.database.meal.MealData
import com.abler31.pizzaapp.feature_menu.data.model.network.category.CategoriesModeNetworkEntity
import com.abler31.pizzaapp.feature_menu.data.model.network.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.data.network.NetworkApi
import com.abler31.pizzaapp.feature_menu.domain.Resource
import com.abler31.pizzaapp.feature_menu.domain.model.category.Categories
import com.abler31.pizzaapp.feature_menu.domain.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MenuRepositoryImpl(
    private val networkApi: NetworkApi,
    private val mealDao: MealDao,
    private val categoryDao: CategoryDao
): MenuRepository {
    override suspend fun getCategories(): Resource<Categories> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<CategoriesModeNetworkEntity> = networkApi.getCategories()
                if (response.isSuccessful) {
                    Log.d("test", response.body()!!.categories[0].strCategory)
                    Log.d("test", "категории загрузились")
                    val data = CategoriesModelToDomainMapper(CategoryEntityToDomainMapper())
                        .transform(response.body()!!)
                    Log.d("test", "после трансформ категорис")
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

    override suspend fun getMealData(): Flow<List<MealData>> {
        return mealDao.getAllMeals()
    }

    override suspend fun insertMealData(mealData: MealData) {
        mealDao.insertMeal(mealData)
    }

    override suspend fun getCategoryData(): Flow<List<CategoryData>> {
        return categoryDao.getAllCategories()
    }

    override suspend fun insertCategoryData(categoryData: CategoryData) {
        categoryDao.insertCategory(categoryData)
    }
}