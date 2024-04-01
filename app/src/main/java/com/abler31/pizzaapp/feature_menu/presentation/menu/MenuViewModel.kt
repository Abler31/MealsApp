package com.abler31.pizzaapp.feature_menu.presentation.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abler31.pizzaapp.feature_menu.data.model.meal.MealsModelEntity
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
class MenuViewModel @Inject constructor (
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

    fun getMealsByCategory(category: Category){
        val meals = meals.value
        when(meals){
            is Resource.Success -> {
                val mealsResult = MealsModelEntity(mealEntities = meals.data!!.mealEntities.filter {
                    it.strCategory == category.strCategory})
                _mealsByCategory.postValue(Resource.Success(data =  mealsResult))

            }
            is Resource.Error -> {

            }
            is Resource.Loading -> {

            }

            else -> {}
        }
    }
}