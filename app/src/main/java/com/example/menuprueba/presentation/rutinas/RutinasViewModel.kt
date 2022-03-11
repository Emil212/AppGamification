package com.example.menuprueba.presentation.rutinas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.menuprueba.domain.auth.AuthRepo
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import com.example.menuprueba.presentation.auth.AuthViewModel

///////3ra capa///////////////7

class RutinasViewModel (val RepoImpl : EjerciciosRepoImpl) : ViewModel() {

    fun getFlexibilidad (){
        RepoImpl.getFlexibilidad()
    }
}
///Prueba implementacion de ViewModel---eliminar
class RutinasViewModelFactory(private val repo: EjerciciosRepoImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RutinasViewModel(repo) as T
    }
}
