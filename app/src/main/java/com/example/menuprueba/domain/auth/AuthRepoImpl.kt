package com.example.menuprueba.domain.auth

import com.example.menuprueba.data.remote.auth.AuthDataSource
import com.google.firebase.auth.FirebaseUser

//Repositorio que implementa la interfaz, la interfaz es una coleccion de metodos

class AuthRepoImpl (private val dataSource:AuthDataSource): AuthRepo{

    override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email,password)
    override suspend fun signUp(email: String, password: String, username: String): FirebaseUser?  = dataSource.signUp(email,password,username)

}