package com.example.GamificationApp.ui.recordatorios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecordatoriosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento Recordatorios"
    }
    val text: LiveData<String> = _text
}