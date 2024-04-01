package com.abler31.pizzaapp.feature_menu.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abler31.pizzaapp.feature_menu.data.model.database.meal.MealData
import com.abler31.pizzaapp.feature_menu.data.model.database.meal.MealsData
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(meal: MealData)

    @Query("SELECT * FROM meals")
    fun getAllMeals(): Flow<List<MealData>>
}