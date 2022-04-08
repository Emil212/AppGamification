package com.example.menuprueba.domain.ejercicios

import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.All
import com.example.menuprueba.data.model.ejercicios.infoEjercicios
import com.example.menuprueba.data.model.ejercicios.videosGif
import com.example.menuprueba.domain.network.IRepo

class EjerciciosRepoImplement(private val repo : IRepo) : EjerciciosRepo {
    override suspend fun getRutina(): Result<MutableList<videosGif>> = repo.getRutinaRepo()
    override suspend fun getAll(): Result<MutableList<All>> = repo.getAllRepo()
    override suspend fun getInfoEjercicios(): Result<MutableList<infoEjercicios>> = repo.getInfoEjerciciosRepo()
}