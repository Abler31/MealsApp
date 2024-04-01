package com.abler31.pizzaapp.feature_menu.data.database

import androidx.room.TypeConverter
import com.abler31.pizzaapp.feature_menu.data.model.database.category.CategoryData
import com.abler31.pizzaapp.feature_menu.data.model.database.meal.MealData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    var gson = Gson()

    @TypeConverter
    fun stringToMealData(data: String?): List<MealData?>? {
        if (data == null) {
            return emptyList<MealData>()
        }
        val listType = object : TypeToken<List<MealData?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun mealDataListToString(someObjects: List<MealData?>?): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToCategoryData(data: String?): List<CategoryData?>? {
        if (data == null) {
            return emptyList<CategoryData>()
        }
        val listType = object : TypeToken<List<CategoryData?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun categoryDataListToString(categoryDataList: List<CategoryData?>?): String? {
        return gson.toJson(categoryDataList)
    }
}