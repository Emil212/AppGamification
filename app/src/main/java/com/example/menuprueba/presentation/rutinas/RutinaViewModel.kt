package com.example.menuprueba.presentation.rutinas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.menuprueba.core.Result
import com.example.menuprueba.domain.ejercicios.EjerciciosRepo
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import kotlinx.coroutines.Dispatchers

class RutinaViewModel(private val repo : EjerciciosRepo) : ViewModel() {

        val getRutina0 = liveData(Dispatchers.IO){
            emit(Result.Loading())
            try {
                val lista = repo.getRutina0()
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

    fun incrementPuntuacion(puntuacion: Long) = liveData(Dispatchers.IO){
        emit(Result.Loading())
        try{
            emit(Result.Success(repo.incrementPuntuacion(puntuacion)))
        }catch (e: Exception){
            emit(Result.Failure(e))
        }
    }

}

class RutinasViewModelFactory(private val repo: EjerciciosRepoImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return modelClass.getConstructor(EjerciciosRepo::class.java).newInstance(repo)
    return RutinaViewModel(repo) as T

    }
}