package com.abler31.pizzaapp.feature_menu.domain.usecase

import com.abler31.pizzaapp.feature_menu.data.model.database.category.CategoryData
import com.abler31.pizzaapp.feature_menu.domain.repository.MenuRepository

class InsertCategoryData(
    private val repository: MenuRepository
) {

    suspend operator fun invoke(categoryData: CategoryData){
        return repository.insertCategoryData(categoryData)
    }

}