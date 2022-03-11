package com.example.menuprueba.data.remote.auth

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import com.example.menuprueba.data.model.ejercicios.EjerciciosList
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

//Lógica para traer los datos del servidor (1ra Capa)
class EjerciciosDataSource {

    val db = FirebaseFirestore.getInstance()


    fun getFlexibilidad() {
        val docRef = db.collection("Flexibilidad").document("Prueba")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val Descripcion = document.getString("Descripcion")
                    Log.d(TAG, "Document Snapshot data: , $Descripcion" )
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Get failed with ", exception)
            }
    }

    fun getResistencia(): EjerciciosList {
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
        return EjerciciosList()
    }


    fun getAerobicos(): EjerciciosList {
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
        return EjerciciosList()
    }
}
