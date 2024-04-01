package com.abler31.pizzaapp.feature_menu.domain.repository

import com.abler31.pizzaapp.feature_menu.data.model.database.category.CategoryData
import com.abler31.pizzaapp.feature_menu.data.model.database.meal.MealData
import com.abler31.pizzaapp.feature_menu.data.model.database.meal.MealsData
import com.abler31.pizzaapp.feature_menu.data.model.network.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.domain.Resource
import com.abler31.pizzaapp.feature_menu.domain.model.category.Categories
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meals
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    suspend fun getCategories(): Resource<Categories>

    suspend fun getMeals(): Resource<MealsModelEntity>

    suspend fun getMealData(): Flow<List<MealData>>

    suspend fun insertMealData(mealData: MealData)

    suspend fun getCategoryData(): Flow<List<CategoryData>>

    suspend fun insertCategoryData(categoryData: CategoryData)

}