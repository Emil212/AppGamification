package com.example.menuprueba.ui.informe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InformeActividadesViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento Informe de Actividades"
    }
    val text: LiveData<String> = _text
}