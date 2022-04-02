package com.example.menuprueba.domain.ejercicios

import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.videosGif


interface EjerciciosRepo {
    suspend fun getRutina () : Result<MutableList<videosGif>>
}