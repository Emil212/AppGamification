package com.example.menuprueba.domain.ejercicios


interface EjerciciosRepo {
    suspend fun getFlexibilidad ()
    suspend fun getResistencia ()
    suspend fun getAerobicos ()
}