package com.abler31.pizzaapp.feature_menu.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is cart Fragment"
    }
    val text: LiveData<String> = _text
}