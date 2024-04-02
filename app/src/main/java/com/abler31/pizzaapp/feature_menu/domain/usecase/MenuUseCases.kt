package com.abler31.pizzaapp.feature_menu.domain.usecase

data class MenuUseCases(
    val getMeals: GetMeals,
    val getCategories: GetCategories,
    val getMealData: GetMealData,
    val insertMealData: InsertMealData,
    val getCategoryData: GetCategoryData,
    val insertCategoryData: InsertCategoryData
)