package com.abler31.pizzaapp.feature_menu.domain.usecase

import com.abler31.pizzaapp.feature_menu.data.model.database.category.CategoryData
import com.abler31.pizzaapp.feature_menu.data.model.database.meal.MealData
import com.abler31.pizzaapp.feature_menu.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow

class GetCategoryData(
    private val repository: MenuRepository
) {
    suspend operator fun invoke(): Flow<List<CategoryData>> {
        return repository.getCategoryData()
    }
}