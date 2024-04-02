package com.abler31.pizzaapp.feature_menu.domain.usecase

import com.abler31.pizzaapp.feature_menu.data.model.database.meal.MealData
import com.abler31.pizzaapp.feature_menu.data.model.network.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.domain.Resource
import com.abler31.pizzaapp.feature_menu.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow


class GetMealData(
    private val repository: MenuRepository
) {

    suspend operator fun invoke(): Flow<List<MealData>> {
        return repository.getMealData()
    }
}