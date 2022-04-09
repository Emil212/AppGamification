package com.example.menuprueba.presentation.logros

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.menuprueba.domain.logros.LogrosRepo
import com.example.menuprueba.domain.logros.LogrosRepoImplement

class LogrosViewModelFactory (private val repo: LogrosRepoImplement) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LogrosRepo::class.java).newInstance(repo)
    }
}