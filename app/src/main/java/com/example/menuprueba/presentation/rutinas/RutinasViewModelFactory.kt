package com.example.menuprueba.presentation.rutinas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.menuprueba.domain.ejercicios.EjerciciosRepo
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImplement

class RutinasViewModelFactory(private val repo: EjerciciosRepoImplement) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(EjerciciosRepo::class.java).newInstance(repo)
    }
}