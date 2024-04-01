package com.abler31.pizzaapp.feature_menu.data.mappers

interface Mapper<S, D> {
    fun transform(data: S): D
}