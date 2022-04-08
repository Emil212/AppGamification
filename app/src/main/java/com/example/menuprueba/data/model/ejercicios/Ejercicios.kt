package com.example.menuprueba.data.model.ejercicios

//Variables para tener accesos a la DB

data class videosGif(val Video: String = "Video")
data class All(
    val Descripcion: String = "Sin Descripcion",
    val Titulo: String = "Sin Titulo",
    val Vista_Previa: String = "Sin Vista Previa"
)

data class infoEjercicios(
    val Nombres: MutableList<String> = mutableListOf(
        "Ex 1",
        "Ex 2",
        "Ex 3",
        "Ex 4"
    )
)




