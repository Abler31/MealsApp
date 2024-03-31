package com.abler31.pizzaapp.feature_menu.data.mappers

import com.abler31.pizzaapp.feature_menu.data.model.meal.MealEntity
import com.abler31.pizzaapp.feature_menu.data.model.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meal
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meals

class MealsModelToDomainMapper (
    private val mealEntityToDomainMapper: Mapper<MealEntity, Meal>
): Mapper<MealsModelEntity, Meals>{
    override fun transform(data: MealsModelEntity) = Meals(
        data.mealEntities.map {
            mealEntityToDomainMapper.transform(it)
        }
    )
}