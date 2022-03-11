package com.example.menuprueba.domain.auth

import com.google.firebase.auth.FirebaseUser

//interfaz es una coleccion de metodos que irán a buscar datos al servidor

interface AuthRepo {
    suspend fun signIn(email: String, password: String): FirebaseUser?
    suspend fun signUp(email: String, password: String,username: String): FirebaseUser?
}

