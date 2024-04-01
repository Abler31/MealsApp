package com.abler31.pizzaapp.feature_menu.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abler31.pizzaapp.feature_menu.data.model.database.category.CategoryData
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryData)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<CategoryData>>

}