package com.example.testarchit.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text

    private val _texts = MutableLiveData<List<String>>().apply {
        value = (1..16).mapIndexed { _, i ->
            "This is item # $i"
        }
    }

    val texts: LiveData<List<String>> = _texts

    private val _tables = MutableLiveData<List<String>>().apply {
        value = (1..16).mapIndexed { _, i ->
            "Table # $i"
        }
    }

    val tables: LiveData<List<String>> = _tables
}