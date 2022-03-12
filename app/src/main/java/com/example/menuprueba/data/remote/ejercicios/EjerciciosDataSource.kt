package com.example.menuprueba.data.remote.ejercicios

import android.content.ContentValues.TAG
import android.util.Log
import com.example.menuprueba.data.model.ejercicios.Ejercicios
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

//Lógica para traer los datos del servidor (1ra Capa)
class EjerciciosDataSource {

    val db = FirebaseFirestore.getInstance()

    suspend fun getFlexibilidad() {
        val docRef = db.collection("Flexibilidad").document("Prueba")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()){
                    val descripcion = document.getString("Descripcion")
                    Log.d("Descripcion: ", "$descripcion")
                } else {
                    Log.d("Descripcion: No existe ", "No hay")
                }
            }
            .await()
    }

    fun getAllFlexibilidadDocuments() {
        val listaEjercicios =
            mutableListOf<Ejercicios>() //Crea una lista editable de tipo Ejercicios
        //"Ejercicios(data\model\ejercicios\ejercicios)"
        val docRef = db.collection("Flexibilidad")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val ejercicios =
                        documento.toObject(Ejercicios::class.java)         //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    listaEjercicios.add(ejercicios) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }
                Log.d("Ejercicios Flexibilidad", "$listaEjercicios") //Muestra la lista
            }
    }

    ///////////////////////////////////

    suspend fun getResistencia() {
        val docRef = db.collection("Resistencia").document("3eI1Nvs3hGWvD2STEwah")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "Document Snapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Get failed with ", exception)
            }

    }


    suspend fun getAerobicos() {
        val docRef = db.collection("Aerobicos").document("7rWjTD0WTzhuIfSSxCuI")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "Document Snapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Get failed with ", exception)
            }
    }
}
