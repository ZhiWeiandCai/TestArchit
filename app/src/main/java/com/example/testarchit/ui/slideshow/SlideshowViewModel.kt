package com.example.testarchit.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text

    private val _texts = MutableLiveData<List<TableDish>>().apply {
        value = (1..16).mapIndexed { _, i ->
            TableDish(i,"Table $i", listOf(
                DishItem("dish $i", 2),
                DishItem("dish $i", 2),
                DishItem("dish $i", 2),
                DishItem("dish $i", 2),
                DishItem("dish $i", 2),
                DishItem("dish $i", 2)
            ))
        }
    }

    val texts: LiveData<List<TableDish>> = _texts


}