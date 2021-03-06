package com.example.menuprueba.data.remote.ejercicios

import android.util.Log
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.menuprueba.domain.ejercicios.IRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue

//Lógica para traer los datos del servidor (1ra Capa)
class EjerciciosDataSource : IRepo {

    override suspend fun getRutina0Repo(): Result<MutableList<videosGif>> {
        //val source = Source.CACHE //source se pasa como parametro en get()
        val db = FirebaseFirestore.getInstance()
        val listaVideos =
            mutableListOf<videosGif>() //Crea una lista editable de tipo Ejercicios
        //"Ejercicios(data\model\ejercicios\ejercicios)"
        val docRef = db.collection("Rutina1")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val ejercicios =
                        documento.toObject(videosGif::class.java)      //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    listaVideos.add(ejercicios) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }

            }
            .addOnFailureListener {

            }
            .await()

        return Result.Success(listaVideos)
    }

    override suspend fun getRutina1Repo(): Result<MutableList<videosGif>> {
        //val source = Source.CACHE //source se pasa como parametro en get()
        val db = FirebaseFirestore.getInstance()
        val listaVideos =
            mutableListOf<videosGif>() //Crea una lista editable de tipo Ejercicios
        //"Ejercicios(data\model\ejercicios\ejercicios)"
        val docRef = db.collection("Rutina2")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val ejercicios =
                        documento.toObject(videosGif::class.java)      //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    listaVideos.add(ejercicios) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }

            }
            .addOnFailureListener {

            }
            .await()

        return Result.Success(listaVideos)
    }
    override suspend fun getRutina2Repo(): Result<MutableList<videosGif>> {
        //val source = Source.CACHE //source se pasa como parametro en get()
        val db = FirebaseFirestore.getInstance()
        val listaVideos =
            mutableListOf<videosGif>() //Crea una lista editable de tipo Ejercicios
        //"Ejercicios(data\model\ejercicios\ejercicios)"
        val docRef = db.collection("Rutina3")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val ejercicios =
                        documento.toObject(videosGif::class.java)      //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    listaVideos.add(ejercicios) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }
            }
            .addOnFailureListener {
            }
            .await()

        return Result.Success(listaVideos)
    }

    ////////Obtiene la información para los CardView (Título, descripción y vista previa)
    override suspend fun getAllRepo(): Result<MutableList<All>> {
        val db = FirebaseFirestore.getInstance()
        val lista =
            mutableListOf<All>()
        val docRef = db.collection("Rutinas")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val todo =
                        documento.toObject(All::class.java)
                    lista.add(todo)
                }
            }
            .addOnFailureListener {
            }
            .await()
        return Result.Success(lista)
    }

    ////////Obtiene el array de nombres de los ejercicios de las rutinas
    override suspend fun getInfoEjerciciosRepo(): Result<MutableList<infoEjercicios>> {
        val db = FirebaseFirestore.getInstance()
        val lista = mutableListOf<infoEjercicios>()
        val docRef = db.collection("Rutinas")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val todo =
                        documento.toObject(infoEjercicios::class.java)         //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    lista.add(todo) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }
            }
            .addOnFailureListener {
            }
            .await()
        return Result.Success(lista)
    }

    override suspend fun incrementPuntuacion(puntuacion: Long) {
        val authResult = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        authResult.currentUser?.uid?.let { uid ->
            val docRef = db.collection("users").document(uid)
            docRef.update("points", FieldValue.increment(puntuacion)).await()
        }
    }

    override suspend fun incrementRoutines(routine1 : Long, routine2 : Long, routine3 : Long){
        val authResult = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        authResult.currentUser?.uid?.let { uid ->
            val docRef = db.collection("users").document(uid)
            docRef.update("repRoutine1", FieldValue.increment(routine1)).await()
            docRef.update("repRoutine2", FieldValue.increment(routine2)).await()
            docRef.update("repRoutine3", FieldValue.increment(routine3)).await()
        }
    }

}
