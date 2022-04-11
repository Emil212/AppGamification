package com.example.menuprueba.domain.ejercicios

import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.All
import com.example.menuprueba.data.model.ejercicios.infoEjercicios
import com.example.menuprueba.data.model.ejercicios.videosGif

interface IRepo {
    suspend fun getRutina0Repo() : Result<MutableList<videosGif>>
    suspend fun getAllRepo(): Result<MutableList<All>>
    suspend fun getInfoEjerciciosRepo():Result<MutableList<infoEjercicios>>
}