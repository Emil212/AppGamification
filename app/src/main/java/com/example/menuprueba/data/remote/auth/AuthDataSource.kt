package com.example.menuprueba.data.remote.auth

import android.util.Log
import com.example.menuprueba.data.model.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


//Logica con firebase para poder logear al usuario

class AuthDataSource {
    suspend fun signIn(email: String, password: String): FirebaseUser? {
        val authResult =
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return authResult.user
    }

    suspend fun signUp(email: String, password: String, username: String): FirebaseUser? {
        val authResult =
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        authResult.user?.uid?.let { uid ->
            FirebaseFirestore.getInstance().collection("users").document(uid)
                .set(User(email, username, 0)).await()
        }
        return authResult.user
    }

    fun logOut() {
        FirebaseAuth.getInstance().signOut()
        Log.d("Cerrar Sesion", "Se cierra la sesion")
    }
}

