package com.example.menuprueba.data.remote.logros

import android.util.Log
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.logros.infoUsers
import com.example.menuprueba.domain.logros.IRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class LogrosDataSource : IRepo {

    private val user = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("users")
    override suspend fun getInfoUserRepo(): Result.Success<MutableList<*>> {

        fun getUID(): String? {/////////////////////////////Obtiene el UID
            user.currentUser.let {
                return user.uid
            }
        }
        var nombre = "SIN NOMBRE"
        var puntos : Long = -1
        var infoUser = mutableListOf(nombre, puntos)
        getUID()?.let {
            docRef.document(it).get()
                .addOnSuccessListener { document ->
                    nombre = document.data?.get("username") as String
                    puntos = document.data?.get("points") as Long
                    infoUser = mutableListOf(nombre, puntos)
                    infoUser.set(0, nombre)
                    infoUser.set(1, puntos)
                    Log.d("Prueba1", "$infoUser")
                    Log.d("UID", "${getUID()}")
                }
                .addOnFailureListener {
                    Log.d("Error", "Error")
                }.await()
            Thread.sleep(100)
        }
        return Result.Success(infoUser)
    }


}