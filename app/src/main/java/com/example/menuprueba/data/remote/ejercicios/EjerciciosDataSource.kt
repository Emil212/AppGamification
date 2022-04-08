package com.example.menuprueba.data.remote.ejercicios

import android.util.Log
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.menuprueba.domain.network.IRepo
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.local.Persistence
import com.google.firebase.ktx.Firebase

//Lógica para traer los datos del servidor (1ra Capa)
class EjerciciosDataSource : IRepo{

    override suspend fun getRutinaRepo(): Result<MutableList<videosGif>> {
        val source = Source.CACHE //source se pasa como parametro en get()
        val db = FirebaseFirestore.getInstance()
        val listaVideos =
            mutableListOf<videosGif>() //Crea una lista editable de tipo Ejercicios
        //"Ejercicios(data\model\ejercicios\ejercicios)"
        val docRef = db.collection("Rutina0")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val ejercicios =
                        documento.toObject(videosGif::class.java)      //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    listaVideos.add(ejercicios) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }
                Log.d("Lista de videos", "$listaVideos") //Muestra la lista
            }
            .addOnFailureListener {
                Log.d("Lista de videos", "Error")
            }
            .await()

        return Result.Success(listaVideos)
    }

    override suspend fun getAllRepo(): Result<MutableList<All>> {
        val source = Source.CACHE
        val db = FirebaseFirestore.getInstance()
        val lista =
            mutableListOf<All>()
        val docRef = db.collection("Rutinas")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val todo =
                        documento.toObject(All::class.java)         //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    lista.add(todo) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }
                Log.d("Lista de rutinas", "$lista") //Muestra la lista
            }
            .addOnFailureListener {
                Log.d("Lista de rutinas", "Error")
            }
            .await()
        return Result.Success(lista)
    }

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
                Log.d("Lista de rutinas", "$lista") //Muestra la lista
            }
            .addOnFailureListener {
                Log.d("Lista de rutinas", "Error")
            }
            .await()
        return Result.Success(lista)
    }
}
