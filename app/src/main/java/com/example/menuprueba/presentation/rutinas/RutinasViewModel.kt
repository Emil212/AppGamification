package com.example.menuprueba.presentation.rutinas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.menuprueba.domain.auth.AuthRepo
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import com.example.menuprueba.presentation.auth.AuthViewModel
import kotlinx.coroutines.Dispatchers
import com.example.menuprueba.core.Result

///////3ra capa///////////////

class RutinasViewModel(val RepoImpl: EjerciciosRepoImpl) : ViewModel() {


    fun getFlexibilidad() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(RepoImpl.getFlexibilidad()))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

//con corrutina
    /* fun getAllFlexibilidad() = liveData(Dispatchers.IO) {
         emit(Result.Loading())
         try {
             emit(Result.Success(RepoImpl.getAllFlexibilidad()))
         } catch (e: Exception) {
             emit(Result.Failure(e))
         }
     }*/

    fun getAllFlexibilidad() {
        RepoImpl.getAllFlexibilidad()
    }
}


///Prueba implementacion de ViewModel---eliminar*
class RutinasViewModelFactory(private val repo: EjerciciosRepoImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RutinasViewModel(repo) as T
    }
}
