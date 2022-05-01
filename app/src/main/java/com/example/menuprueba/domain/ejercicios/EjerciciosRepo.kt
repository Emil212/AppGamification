package com.example.menuprueba.domain.ejercicios

import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.All
import com.example.menuprueba.data.model.ejercicios.infoEjercicios
import com.example.menuprueba.data.model.ejercicios.videosGif


interface EjerciciosRepo {
    suspend fun getRutina0 () : Result<MutableList<videosGif>>
    suspend fun getAll(): Result<MutableList<All>>
    suspend fun getInfoEjercicios(): Result<MutableList<infoEjercicios>>
    suspend fun incrementPuntuacion(puntuacion: Long)
    suspend fun incrementRoutines(routine1 : Long, routine2 : Long, routine3 : Long)
    suspend fun incrementRoutine2()
    suspend fun incrementRoutine3()
}