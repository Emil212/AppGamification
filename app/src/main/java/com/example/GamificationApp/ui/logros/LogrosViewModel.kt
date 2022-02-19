package com.example.GamificationApp.ui.logros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogrosViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Fragmento Logros"
    }
    val text: LiveData<String> = _text
}