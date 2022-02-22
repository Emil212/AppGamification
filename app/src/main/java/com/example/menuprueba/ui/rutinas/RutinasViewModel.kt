package com.example.menuprueba.ui.rutinas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RutinasViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento Rutinas"
    }
    val text: LiveData<String> = _text
}