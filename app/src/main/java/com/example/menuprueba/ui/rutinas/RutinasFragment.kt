package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.menuprueba.R
import com.example.menuprueba.data.remote.auth.AuthDataSource
import com.example.menuprueba.data.remote.auth.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentRutinasBinding
import com.example.menuprueba.domain.auth.AuthRepoImpl
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import com.example.menuprueba.presentation.auth.AuthViewModel
import com.example.menuprueba.presentation.auth.AuthViewModelFactory
import com.example.menuprueba.presentation.rutinas.RutinasViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory

////////////////////////////////////////////////////////////////4ta Capa/////
class RutinasFragment() : Fragment(R.layout.fragment_rutinas) {

    private lateinit var binding: FragmentRutinasBinding
    private val viewModel by viewModels<RutinasViewModel> {
        RutinasViewModelFactory(
            EjerciciosRepoImpl(
                EjerciciosDataSource()
            )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRutinasBinding.bind(view)
        getFlexibilidad()
    }


    fun getFlexibilidad() {
        binding.buttonGetFlexibilidad.setOnClickListener {
            viewModel.getFlexibilidad()
        }

    }
}