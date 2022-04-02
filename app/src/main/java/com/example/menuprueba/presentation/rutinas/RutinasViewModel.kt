package com.example.menuprueba.presentation.rutinas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import com.example.menuprueba.core.Result
import com.example.menuprueba.domain.ejercicios.EjerciciosRepo

///////3ra capa///////////////

class RutinasViewModel(private val RepoImpl: EjerciciosRepo) : ViewModel() {

    //con corrutina
/*    fun getFlexibilidad() = liveData(Dispatchers.IO) {
         emit(Result.Loading())
         try {
             emit(Result.Success(RepoImpl.getFlexibilidad()))
         } catch (e: Exception) {
             emit(Result.Failure(e))
         }
     }*/

    //con corrutina
/*    fun getAerobicos() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(RepoImpl.getAerobicos()))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }*/

    ///////////////////////Objetos individuales
    /*fun getFlexibilidad() {
            RepoImpl.getFlexibilidad()
    }
    fun getAerobicos(){
        RepoImpl.getAerobico()
    }*/
/*    suspend fun getResistencia(){
        RepoImpl.getResistencia()
    }*/


/*    ////////////////////////Nombres
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
    }*/


/*    fun getGif() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(Result.Success(RepoImpl.getGif()))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
        return@liveData
    }*/

    fun getAllResistencia() {
        TODO("Not yet implemented")
    }

    fun getAllAerobicos() {
        TODO("Not yet implemented")
    }

    fun getAllFlexibilidad() {
        TODO("Not yet implemented")
    }
}


///Implementacion de ViewModel

