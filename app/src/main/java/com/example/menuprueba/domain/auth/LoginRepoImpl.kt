package com.example.menuprueba.domain.auth

import com.example.menuprueba.data.remote.auth.LoginDataSource
import com.google.firebase.auth.FirebaseUser

//Repositorio que implementa la interfaz, la interfaz es una coleccion de metodos

class LoginRepoImpl (private val dataSource:LoginDataSource): LoginRepo{

    override suspend fun signIn(email: String, password: String): FirebaseUser? = dataSource.signIn(email,password)

}