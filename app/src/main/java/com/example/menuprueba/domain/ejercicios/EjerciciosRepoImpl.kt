package com.example.menuprueba.domain.ejercicios


import com.example.menuprueba.data.model.ejercicios.EjerciciosList
import com.example.menuprueba.data.remote.auth.EjerciciosDataSource

////////////////////////2da Capa/////////////////7777

class EjerciciosRepoImpl (val dataSource: EjerciciosDataSource) {

    fun getFlexibilidad () {
        dataSource.getFlexibilidad()

    }

    suspend fun getResistencia() {
        TODO("Not yet implemented")
    }

    suspend fun getAerobicos() {
        TODO("Not yet implemented")
    }
}