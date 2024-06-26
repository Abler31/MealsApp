package com.abler31.pizzaapp.feature_menu.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abler31.pizzaapp.feature_menu.data.model.database.category.CategoryData
import com.abler31.pizzaapp.feature_menu.data.model.database.meal.MealData

@Database(entities = [MealData::class, CategoryData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun mealDao(): MealDao
}