package com.abler31.pizzaapp.feature_menu.data.model.network.meal

import com.google.gson.annotations.SerializedName

data class MealsModelEntity(
    @SerializedName("meals") val mealEntities: List<MealEntity>
)