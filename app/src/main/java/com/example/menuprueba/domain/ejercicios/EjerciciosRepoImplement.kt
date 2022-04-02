package com.example.menuprueba.domain.ejercicios

import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.videosGif
import com.example.menuprueba.domain.network.IRepo

class EjerciciosRepoImplement(private val repo : IRepo) : EjerciciosRepo {
/*    override suspend fun getFlexibilidad() {
        TODO("Not yet implemented")
    }

    override suspend fun getResistencia() {
        TODO("Not yet implemented")
    }

    override suspend fun getAerobicos() {
        TODO("Not yet implemented")
    }

    override suspend fun getGif(): MutableList<*> {
        TODO("Not yet implemented")
    }*/

    override suspend fun getRutina(): Result<MutableList<videosGif>> = repo.getRutinaRepo()
}