package com.example.menuprueba.domain.ejercicios

import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.All
import com.example.menuprueba.data.model.ejercicios.infoEjercicios
import com.example.menuprueba.data.model.ejercicios.videosGif

class EjerciciosRepoImpl(private val repo : IRepo) : EjerciciosRepo {
    override suspend fun getRutina0(): Result<MutableList<videosGif>> = repo.getRutina0Repo()
    override suspend fun getRutina1(): Result<MutableList<videosGif>> = repo.getRutina1Repo()
    override suspend fun getRutina2(): Result<MutableList<videosGif>> = repo.getRutina2Repo()
    override suspend fun getAll(): Result<MutableList<All>> = repo.getAllRepo()
    override suspend fun getInfoEjercicios(): Result<MutableList<infoEjercicios>> = repo.getInfoEjerciciosRepo()
    override suspend fun incrementPuntuacion(puntuacion: Long) = repo.incrementPuntuacion(puntuacion)
    override suspend fun incrementRoutines(routine1 : Long, routine2 : Long, routine3 : Long) = repo.incrementRoutines(routine1,routine2,routine3)
}