package com.example.menuprueba.domain.ejercicios


import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource

////////////////////////2da Capa/////////////////

class EjerciciosRepoImpl(val dataSource: EjerciciosDataSource) {

    ///////////////////////////////Objetos individuales
    suspend  fun getFlexibilidad() {
        dataSource.getFlexibilidad()
    }
    suspend fun getAerobicos() {
        dataSource.getAerobicos()
    }
    fun getResistencia() {
        dataSource.getResistencia()
    }

    /////////////////////////////////Nombres
    fun getFlexibilidadNombres() {
        dataSource.getFlexibilidadNombres()
    }
    fun getAerobicosNombres(){
        dataSource.getAerobicosNombres()
    }
    fun getResistenciaNombres(){
        dataSource.getResistenciaNombres()
    }

    /////////////////////////////Todos los documentos
    fun getAllFlexibilidad() {
        dataSource.getAllFlexibilidadDocuments()
    }
    fun getAllResistencia(){
        dataSource.getAllResistenciaDocuments()
    }
    fun getAllAerobicos(){
        dataSource.getAllAerobicosDocuments()
    }
}