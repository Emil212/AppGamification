package com.example.menuprueba.data.remote.logros

import android.util.Log
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.logros.infoUsers
import com.example.menuprueba.domain.logros.IRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await

class LogrosDataSource {

    private val user = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    private val docRef = db.collection("users")
    var nombre = "SIN NOMBRE"
    var puntos: Long = -1
    var infoUser = infoUsers(nombre, puntos)

//    var infoUser : infoUsers

    suspend fun getInfoUserRepo(): Result<infoUsers> {

        //data class infoUsers(val username: String = "SIN NOMBRE", val points : Long = -1)
        //var infoUser: infoUsers

        user.uid?.let {
            docRef.document(it).get()
                .addOnSuccessListener { document ->
                    val username = document.get("username").toString()
                    val points = document.get("points") as Long
                    Log.d("Pruebas", "nombre de usuario: $username")
                    Log.d("Pruebas", "puntos: $points")
                    Log.d("Pruebas", username)
                    Log.d("Pruebas", points.toString())
                    infoUser = infoUsers(username, points)
                    Log.d("Pruebas final", infoUser.toString())
                }

        }?.await()
        Thread.sleep(1000)
        Log.d("Prueba", "Manda a fragment $infoUser")


        return Result.Success(infoUser)

    }
}

/*
        var infoUser = mutableListOf(nombre, puntos)
        user.uid?.let {
            docRef.document(it).get()
                .addOnSuccessListener { document ->
                    nombre = document.data?.get("username") as String
                    puntos = document.data?.get("points") as Long
                    infoUser = mutableListOf(nombre, puntos)
                    (infoUser as MutableList<Any>)[0] = nombre
                    (infoUser as MutableList<Any>)[1] = puntos
                    Log.d("Prueba1", "$infoUser")
                }
                .addOnFailureListener {
                    Log.d("Error", "Error")
                }.await()
            Thread.sleep(100)

            return Result.Success(infoUser)


        }


/*


        fun getUID(): String? {
            user.currentUser.let {
                return user.uid
            }
        }
        var nombre = "SIN NOMBRE"
        var puntos: Long = -1
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
        */
        return Result.Success(infoUser)



    }


}


 */
