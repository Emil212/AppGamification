package com.example.menuprueba.domain.network

import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.videosGif

interface IRepo {
    suspend fun getRutinaRepo() : Result<MutableList<videosGif>>
}