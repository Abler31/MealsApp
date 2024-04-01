package com.abler31.pizzaapp.feature_menu.domain.usecase

import com.abler31.pizzaapp.feature_menu.data.model.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.domain.Resource
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meals
import com.abler31.pizzaapp.feature_menu.domain.repository.MenuRepository

class GetMeals(
    private val repository: MenuRepository
) {

    suspend operator fun invoke(): Resource<Meals>{
        //Log.d("test", repository.getMeals().data?.mealEntities?.get(1)?.strMeal ?: "пришел null GetMeals")
        return repository.getMeals()
    }

}