package com.abler31.pizzaapp.feature_menu.data.mappers

import com.abler31.pizzaapp.feature_menu.data.model.meal.MealEntity
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meal

class MealEntityToDomainMapper : Mapper<MealEntity, Meal> {
    override fun transform(data: MealEntity) = Meal(
        idMeal = data.idMeal,
        strCategory = data.strCategory,
        strIngredient1 = data.strIngredient1,
        strIngredient10 = data.strIngredient10,
        strIngredient11 = data.strIngredient11,
        strIngredient12 =  data.strIngredient12,
        strIngredient13 =  data.strIngredient13,
        strIngredient14 = data.strIngredient14,
        strIngredient15 = data.strIngredient15,
        strIngredient16 = data.strIngredient16,
        strIngredient17 = data.strIngredient17,
        strIngredient18 = data.strIngredient18,
        strIngredient19 = data.strIngredient19,
        strIngredient2 = data.strIngredient2,
        strIngredient20 = data.strIngredient20,
        strIngredient3 = data.strIngredient3,
        strIngredient4 = data.strIngredient4,
        strIngredient5 = data.strIngredient5,
        strIngredient6 = data.strIngredient6,
        strIngredient7 = data.strIngredient7,
        strIngredient8 = data.strIngredient8,
        strIngredient9 = data.strIngredient9,
        strMeal = data.strMeal,
        strMealThumb = data.strMealThumb,
    )
}