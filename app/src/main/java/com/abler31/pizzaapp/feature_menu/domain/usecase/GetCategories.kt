package com.abler31.pizzaapp.feature_menu.domain.usecase

import com.abler31.pizzaapp.feature_menu.domain.Resource
import com.abler31.pizzaapp.feature_menu.domain.model.category.Categories
import com.abler31.pizzaapp.feature_menu.domain.repository.MenuRepository

class GetCategories(
    private val repository: MenuRepository
) {

    suspend operator fun invoke(): Resource<Categories>{
        return repository.getCategories()
    }

}