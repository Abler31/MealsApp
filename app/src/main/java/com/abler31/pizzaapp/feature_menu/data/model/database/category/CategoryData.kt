package com.abler31.pizzaapp.feature_menu.data.model.database.category

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "categories")
data class CategoryData (
    @PrimaryKey val id: String,
    val strCategoryData: String
)