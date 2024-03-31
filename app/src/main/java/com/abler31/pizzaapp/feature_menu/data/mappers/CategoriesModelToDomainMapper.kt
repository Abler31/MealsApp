package com.abler31.pizzaapp.feature_menu.data.mappers

import com.abler31.pizzaapp.feature_menu.data.model.category.CategoriesModelEntity
import com.abler31.pizzaapp.feature_menu.data.model.category.CategoryEntity
import com.abler31.pizzaapp.feature_menu.domain.model.category.Categories
import com.abler31.pizzaapp.feature_menu.domain.model.category.Category

class CategoriesModelToDomainMapper(
    private val categoryEntityToDomainMapper: Mapper<CategoryEntity, Category>
): Mapper<CategoriesModelEntity, Categories> {
    override fun transform(data: CategoriesModelEntity) = Categories(
        data.categories.map {
            categoryEntityToDomainMapper.transform(it)
        }
    )
}