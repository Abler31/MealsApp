package com.abler31.pizzaapp.feature_menu.presentation.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abler31.pizzaapp.feature_menu.data.model.database.category.CategoryData
import com.abler31.pizzaapp.feature_menu.data.model.database.meal.MealData
import com.abler31.pizzaapp.feature_menu.data.model.network.meal.MealEntity
import com.abler31.pizzaapp.feature_menu.data.model.network.meal.MealsModelEntity
import com.abler31.pizzaapp.feature_menu.domain.Resource
import com.abler31.pizzaapp.feature_menu.domain.model.category.Categories
import com.abler31.pizzaapp.feature_menu.domain.model.category.Category
import com.abler31.pizzaapp.feature_menu.domain.model.meal.Meals
import com.abler31.pizzaapp.feature_menu.domain.usecase.MenuUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuUseCases: MenuUseCases
) : ViewModel() {

    private val _categories = MutableLiveData<Resource<Categories>>()
    val categories: LiveData<Resource<Categories>> = _categories

    private val _meals = MutableLiveData<Resource<MealsModelEntity>>()
    val meals: LiveData<Resource<MealsModelEntity>> = _meals

    private val _mealsByCategory = MutableLiveData<Resource<MealsModelEntity>>()
    val mealsByCategory: LiveData<Resource<MealsModelEntity>> = _mealsByCategory

    fun getCategories() = viewModelScope.launch(Dispatchers.IO) {
        _categories.postValue(Resource.Loading())
        _categories.postValue(menuUseCases.getCategories.invoke())
    }

    fun getMeals() = viewModelScope.launch(Dispatchers.IO) {
        _meals.postValue(Resource.Loading())
        _meals.postValue(menuUseCases.getMeals.invoke())
    }

    fun getMealsByCategory(category: Category) {
        val meals = meals.value
        when (meals) {
            is Resource.Success -> {
                val mealsResult = MealsModelEntity(mealEntities = meals.data!!.mealEntities.filter {
                    it.strCategory == category.strCategory
                })
                _mealsByCategory.postValue(Resource.Success(data = mealsResult))

            }

            is Resource.Error -> {

            }

            is Resource.Loading -> {

            }

            else -> {}
        }
    }

    fun getMealsData() {
        viewModelScope.launch(Dispatchers.IO) {
            menuUseCases.getMealData.invoke().collect { meals ->
                if (!meals.isEmpty()) {
                    val mealEntities = meals.map {
                        MealEntity(
                            strCategory = it.strCategory,
                            strIngredient1 = it.strIngredient1,
                            strIngredient10 = it.strIngredient10,
                            strIngredient11 = it.strIngredient11,
                            strIngredient12 = it.strIngredient12,
                            strIngredient13 = it.strIngredient13,
                            strIngredient14 = it.strIngredient14,
                            strIngredient15 = it.strIngredient15,
                            strIngredient16 = it.strIngredient16,
                            strIngredient17 = it.strIngredient17,
                            strIngredient18 = it.strIngredient18,
                            strIngredient19 = it.strIngredient19,
                            strIngredient2 = it.strIngredient2,
                            strIngredient20 = it.strIngredient20,
                            strIngredient3 = it.strIngredient3,
                            strIngredient4 = it.strIngredient4,
                            strIngredient5 = it.strIngredient5,
                            strIngredient6 = it.strIngredient6,
                            strIngredient7 = it.strIngredient7,
                            strIngredient8 = it.strIngredient8,
                            strIngredient9 = it.strIngredient9,
                            strMeal = it.strMeal,
                            strMealThumb = ""
                        )
                    }
                    val mealsResult = MealsModelEntity(mealEntities = mealEntities)
                    _meals.postValue(Resource.Success(data = mealsResult))
                }
            }
        }
    }

    fun getCategoryData() {
        viewModelScope.launch(Dispatchers.IO) {
            menuUseCases.getCategoryData.invoke().collect { categoriesData ->
                if (categoriesData.isNotEmpty()) {
                    val categories = categoriesData.map {
                        Category(
                            strCategory = it.strCategoryData
                        )
                    }
                    val categoryResult = Categories(categories = categories)
                    _categories.postValue(Resource.Success(data = categoryResult))
                }
            }
        }
    }

    fun insertMealData(mealEntity: MealEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val mealData = MealData(
                id = mealEntity.idMeal,
                strCategory = mealEntity.strCategory,
                strIngredient1 = mealEntity.strIngredient1,
                strIngredient10 = mealEntity.strIngredient10,
                strIngredient11 = mealEntity.strIngredient11,
                strIngredient12 = mealEntity.strIngredient12,
                strIngredient13 = mealEntity.strIngredient13,
                strIngredient14 = mealEntity.strIngredient14,
                strIngredient15 = mealEntity.strIngredient15,
                strIngredient16 = mealEntity.strIngredient16,
                strIngredient17 = mealEntity.strIngredient17,
                strIngredient18 = mealEntity.strIngredient18,
                strIngredient19 = mealEntity.strIngredient19,
                strIngredient2 = mealEntity.strIngredient2,
                strIngredient20 = mealEntity.strIngredient20,
                strIngredient3 = mealEntity.strIngredient3,
                strIngredient4 = mealEntity.strIngredient4,
                strIngredient5 = mealEntity.strIngredient5,
                strIngredient6 = mealEntity.strIngredient6,
                strIngredient7 = mealEntity.strIngredient7,
                strIngredient8 = mealEntity.strIngredient8,
                strIngredient9 = mealEntity.strIngredient9,
                strMeal = mealEntity.strMeal,
                strMealThumb = mealEntity.strMealThumb
            )
            menuUseCases.insertMealData.invoke(mealData)
        }
    }

    fun insertCategoryData(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            val categoryData = CategoryData(
                id = category.idCategory,
                strCategoryData = category.strCategory
            )
            menuUseCases.insertCategoryData.invoke(categoryData)
        }
    }
}