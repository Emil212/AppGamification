package com.example.menuprueba.domain.ejercicios


import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource

////////////////////////2da Capa/////////////////

class EjerciciosRepoImpl (val dataSource: EjerciciosDataSource) {

   suspend fun getFlexibilidad () { //corrutina
        dataSource.getFlexibilidad()
    }

    fun getAllFlexibilidad(){ //sin corrutina
        dataSource.getAllFlexibilidadDocuments()
    }
/////////////////////////////////////////////////
    suspend fun getResistencia() {
        TODO("Not yet implemented")
    }

    suspend fun getAerobicos() {
        TODO("Not yet implemented")
    }
}