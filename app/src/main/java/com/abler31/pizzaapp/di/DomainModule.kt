package com.abler31.pizzaapp.di

import com.abler31.pizzaapp.feature_menu.domain.repository.MenuRepository
import com.abler31.pizzaapp.feature_menu.domain.usecase.GetCategories
import com.abler31.pizzaapp.feature_menu.domain.usecase.GetCategoryData
import com.abler31.pizzaapp.feature_menu.domain.usecase.GetMealData
import com.abler31.pizzaapp.feature_menu.domain.usecase.GetMeals
import com.abler31.pizzaapp.feature_menu.domain.usecase.InsertCategoryData
import com.abler31.pizzaapp.feature_menu.domain.usecase.InsertMealData
import com.abler31.pizzaapp.feature_menu.domain.usecase.MenuUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideMenuUseCases(repository: MenuRepository): MenuUseCases{
        return MenuUseCases(
            getMeals = GetMeals(repository),
            getCategories = GetCategories(repository),
            getMealData = GetMealData(repository),
            insertMealData = InsertMealData(repository),
            getCategoryData = GetCategoryData(repository),
            insertCategoryData = InsertCategoryData(repository)
        )
    }

}