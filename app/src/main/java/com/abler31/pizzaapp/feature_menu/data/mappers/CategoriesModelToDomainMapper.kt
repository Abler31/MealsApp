package com.abler31.pizzaapp.feature_menu.data.mappers

import com.abler31.pizzaapp.feature_menu.data.model.network.category.CategoriesModeNetworkEntity
import com.abler31.pizzaapp.feature_menu.data.model.network.category.CategoryNetworkEntity
import com.abler31.pizzaapp.feature_menu.domain.model.category.Categories
import com.abler31.pizzaapp.feature_menu.domain.model.category.Category

class CategoriesModelToDomainMapper(
    private val categoryNetworkEntityToDomainMapper: Mapper<CategoryNetworkEntity, Category>
): Mapper<CategoriesModeNetworkEntity, Categories> {
    override fun transform(data: CategoriesModeNetworkEntity): Categories {
        val categories = Categories(
            data.categories.map {
                categoryNetworkEntityToDomainMapper.transform(it)
            }
        )
        return categories
    }
}