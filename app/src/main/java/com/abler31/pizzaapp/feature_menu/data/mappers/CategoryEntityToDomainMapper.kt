package com.abler31.pizzaapp.feature_menu.data.mappers

import com.abler31.pizzaapp.feature_menu.data.model.network.category.CategoryNetworkEntity
import com.abler31.pizzaapp.feature_menu.domain.model.category.Category

class CategoryEntityToDomainMapper: Mapper<CategoryNetworkEntity, Category> {
    override fun transform(data: CategoryNetworkEntity) = Category(
        idCategory = data.idCategory,
        strCategory = data.strCategory
    )
}