package com.example.menuprueba.ui.carrera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CarreraViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento Carrera"
    }
    val text: LiveData<String> = _text
}