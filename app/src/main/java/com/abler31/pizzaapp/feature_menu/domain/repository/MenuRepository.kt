package com.abler31.pizzaapp.feature_menu.domain.repository

import com.abler31.pizzaapp.feature_menu.data.model.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.domain.Resource
import com.abler31.pizzaapp.feature_menu.domain.model.category.Categories
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meals

interface MenuRepository {

    suspend fun getCategories(): Resource<Categories>

    suspend fun getMeals(): Resource<MealsModelEntity>

}