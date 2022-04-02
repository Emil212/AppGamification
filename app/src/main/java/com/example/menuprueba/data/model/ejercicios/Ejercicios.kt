package com.example.menuprueba.data.model.ejercicios
//Variables para tener accesos a la DB
data class Ejercicios(
    val Descripcion: String = "Sin Descripcion",
    val Duracion: Int = -1,
    val Nombre: String = "Sin Nombre",
    val Video: String = "Sin Video"
)
data class  EjerciciosNombre (
    val Nombre: String = "Sin Nombre"
        )

data class  videosGif (val Video : String = "Video")




