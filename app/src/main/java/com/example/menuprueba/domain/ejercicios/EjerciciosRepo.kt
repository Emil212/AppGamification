package com.example.menuprueba.domain.ejercicios


import com.example.menuprueba.data.model.ejercicios.EjerciciosList

interface EjerciciosRepo {
    suspend fun getFlexibilidad ()
    suspend fun getResistencia () : EjerciciosList
    suspend fun getAerobicos () : EjerciciosList
}