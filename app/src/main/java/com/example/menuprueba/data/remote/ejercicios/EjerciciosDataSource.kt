package com.example.menuprueba.data.remote.ejercicios

import android.util.Log
import androidx.browser.customtabs.PostMessageService
import com.example.menuprueba.data.model.ejercicios.Ejercicios
import com.example.menuprueba.data.model.ejercicios.EjerciciosNombre
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.menuprueba.core.Result
import com.example.menuprueba.core.Result.Loading
import kotlinx.coroutines.flow.flow

//Lógica para traer los datos del servidor (1ra Capa)
class EjerciciosDataSource {

    private val db = FirebaseFirestore.getInstance()

    ///////////////////////Objetos individuales
    //////Flexibilidad
    suspend fun getFlexibilidad() {
        val docRef = db.collection("Flexibilidad").document("Prueba")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val descripcion = document.getString("Descripcion")
                    print(descripcion)
                    Log.d("Descripcion: ", "$descripcion")
                    Log.d("AL", "Holi")
                } else {
                    Log.d("Descripcion: No existe ", "No hay")
                }
            }
            .await()
    }
    //////Aerobicos
    suspend fun getAerobicos() {
        val docRef = db.collection("Aerobicos").document("Prueba")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val descripcion = document.getString("Descripcion")
                    Log.d("Descripcion: ", "$descripcion")
                } else {
                    Log.d("Descripcion: No existe ", "No hay")
                }
            }
            .await()
    }

    //////Resistencia
    fun getResistencia() {
        val docRef = db.collection("Resistencia").document("Prueba")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val descripcion = document.getString("Descripcion")
                    Log.d("Descripcion: ", "$descripcion")
                } else {
                    Log.d("Descripcion: No existe ", "No hay")
                }
            }
    }
    ///////////////////////////////////Lista de Nombres
    //////Flexibilidad
    fun getFlexibilidadNombres() {
        val listaEjercicios =
            mutableListOf<EjerciciosNombre>() //Crea una lista editable de tipo Ejercicios
        //"Ejercicios(data\model\ejercicios\ejercicios)"
        val docRef = db.collection("Flexibilidad")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val ejercicios =
                        documento.toObject(EjerciciosNombre::class.java)         //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    listaEjercicios.add(ejercicios) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }
                Log.d("Ejercicios Flexibilidad", "$listaEjercicios") //Muestra la lista
            }
    }
    ///////Resistencia
    fun getResistenciaNombres() {
        val listaEjercicios =
            mutableListOf<EjerciciosNombre>() //Crea una lista editable de tipo Ejercicios
        //"Ejercicios(data\model\ejercicios\ejercicios)"
        val docRef = db.collection("Resistencia")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val ejercicios =
                        documento.toObject(EjerciciosNombre::class.java)         //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    listaEjercicios.add(ejercicios) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }
                Log.d("Ejercicios Resistencia", "$listaEjercicios") //Muestra la lista
            }
    }
    ///////Aerobicos
    fun getAerobicosNombres() {
        val listaEjercicios =
            mutableListOf<EjerciciosNombre>() //Crea una lista editable de tipo Ejercicios
        //"Ejercicios(data\model\ejercicios\ejercicios)"
        val docRef = db.collection("Aerobicos")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val ejercicios =
                        documento.toObject(EjerciciosNombre::class.java)         //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    listaEjercicios.add(ejercicios) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }
                Log.d("Ejercicios Aerobicos", "$listaEjercicios") //Muestra la lista
            }
    }
    ///////////////////////////////////Todos los doucmentos
    ///////////Flexibilidad
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
    //////////////////Resistencia
    fun getAllResistenciaDocuments() {
        val listaEjercicios =
            mutableListOf<Ejercicios>() //Crea una lista editable de tipo Ejercicios
        //"Ejercicios(data\model\ejercicios\ejercicios)"
        val docRef = db.collection("Resistencia")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val ejercicios =
                        documento.toObject(Ejercicios::class.java)         //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    listaEjercicios.add(ejercicios) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }
                Log.d("Ejercicios Resistencia", "$listaEjercicios") //Muestra la lista
            }
    }
    //////////////////Aerobicos
    fun getAllAerobicosDocuments() {
        val listaEjercicios =
            mutableListOf<Ejercicios>() //Crea una lista editable de tipo Ejercicios
        //"Ejercicios(data\model\ejercicios\ejercicios)"
        val docRef = db.collection("Aerobicos")
        docRef.get()
            .addOnSuccessListener {
                for (documento in it) {
                    val ejercicios =
                        documento.toObject(Ejercicios::class.java)         //Toma los valores del documento
                    //la variable documento es donde se almacena la información        // y los pasa al objeto de tipo Ejercicios (DataClass)
                    listaEjercicios.add(ejercicios) //Toma la lista editable y le agrega el nuevo objeto (DataClass)
                }
                Log.d("Ejercicios Aerobicos", "$listaEjercicios") //Muestra la lista
            }
    }
}
