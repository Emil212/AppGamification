package com.example.menuprueba.domain.auth

import com.google.firebase.auth.FirebaseUser

//interfaz es una coleccion de metodos

interface LoginRepo {
    suspend fun signIn(email: String, password: String): FirebaseUser?
}

