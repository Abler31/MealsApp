package com.abler31.pizzaapp.feature_menu.data.mappers

import android.util.Log
import com.abler31.pizzaapp.feature_menu.data.model.network.meal.MealEntity
import com.abler31.pizzaapp.feature_menu.data.model.network.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meal
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meals

class MealsModelToDomainMapper (
    private val mealEntityToDomainMapper: Mapper<MealEntity, Meal>
): Mapper<MealsModelEntity, Meals>{
    override fun transform(data: MealsModelEntity): Meals {
        val meal = Meals(
            data.mealEntities.map {
                mealEntityToDomainMapper.transform(it)
            })
        return meal
    }
}