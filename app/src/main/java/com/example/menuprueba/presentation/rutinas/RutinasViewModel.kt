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

    //con corrutina
    fun getFlexibilidad() = liveData(Dispatchers.IO) {
         emit(Result.Loading())
         try {
             emit(Result.Success(RepoImpl.getFlexibilidad()))
         } catch (e: Exception) {
             emit(Result.Failure(e))
         }
     }
    //con corrutina
    fun getAerobicos() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(RepoImpl.getAerobicos()))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

    ///////////////////////Objetos individuales
    /*fun getFlexibilidad() {
            RepoImpl.getFlexibilidad()
    }
    fun getAerobicos(){
        RepoImpl.getAerobico()
    }*/
    fun getResistencia(){
        RepoImpl.getResistencia()
    }


    ////////////////////////Nombres
    fun getFlexibilidadNombres(){
        RepoImpl.getFlexibilidadNombres()
    }
    fun getResistenciaNombres(){
        RepoImpl.getResistenciaNombres()
    }
    fun getAerobicosNombres(){
        RepoImpl.getAerobicosNombres()
    }

    /////////////////////////Todos los documentos
    fun getAllFlexibilidad() {
        RepoImpl.getAllFlexibilidad()
    }
    fun getAllResistencia(){
        RepoImpl.getAllResistencia()
    }
    fun getAllAerobicos(){
        RepoImpl.getAllAerobicos()
    }
}


///Implementacion de ViewModel
class RutinasViewModelFactory(private val repo: EjerciciosRepoImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RutinasViewModel(repo) as T
    }
}
