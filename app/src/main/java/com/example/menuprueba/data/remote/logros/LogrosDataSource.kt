package com.example.menuprueba.data.remote.logros

import android.util.Log
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.logros.infoUsers
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LogrosDataSource {

    private val user = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    private val docRef = db.collection("users")
    var nombre = "SIN NOMBRE"
    var puntos: Long = -1
    var infoUser = infoUsers(nombre, puntos)

    suspend fun getInfoUserRepo(): Result<infoUsers> {

        user.uid?.let {
            docRef.document(it).get()
                .addOnSuccessListener { document ->
                    val username = document.get("username").toString()
                    val points = document.get("points") as Long
                    val repRoutine1 = document.get("repRoutine1") as Long
                    val repRoutine2 = document.get("repRoutine2") as Long
                    val repRoutine3 = document.get("repRoutine3") as Long
                    infoUser = infoUsers(username, points, repRoutine1, repRoutine2, repRoutine3)
                    Log.d("Pruebas final", infoUser.toString())
                }

        }?.await()
        Thread.sleep(1000)
        Log.d("Prueba", "Manda a fragment $infoUser")

        return Result.Success(infoUser)

    }
}

