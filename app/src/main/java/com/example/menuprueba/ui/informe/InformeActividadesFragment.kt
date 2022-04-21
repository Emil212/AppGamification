package com.example.menuprueba.ui.informe

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.menuprueba.R
import com.example.menuprueba.databinding.FragmentRutinasBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth


class InformeActividadesFragment : Fragment(R.layout.fragment_informe_actividades) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser!!
        user.sendEmailVerification()
        user.isEmailVerified

        Log.d("Verificar usuario", user.isEmailVerified.toString())

    }
}