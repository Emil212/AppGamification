package com.example.menuprueba.presentation.rutinas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.menuprueba.core.Result
import com.example.menuprueba.domain.ejercicios.EjerciciosRepo
import kotlinx.coroutines.Dispatchers

class RutinaViewModel(repo : EjerciciosRepo) : ViewModel() {

        val getRutina = liveData(Dispatchers.IO){
            emit(Result.Loading())
            try {
                val lista = repo.getRutina()
                emit(lista)
            }catch (e: Exception){
                emit(Result.Failure(e))
            }
        }



    val getAll = liveData(Dispatchers.IO){
        emit(Result.Loading())
        try {
            val lista = repo.getAll()
            emit(lista)
        }catch (e: Exception){
            emit(Result.Failure(e))
        }
    }

    val getInfoEjercicios = liveData(Dispatchers.IO){
        emit(Result.Loading())
        try {
            val lista = repo.getInfoEjercicios()
            emit(lista)
        }catch (e: Exception){
            emit(Result.Failure(e))
        }
    }
}