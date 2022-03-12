package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.menuprueba.R
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentRutinasBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
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

        //getFlexibilidad() //Obtener un solo dato de la collección

        getAllFlexibilidad() //Obtener todos los documentos de la collección
    }


    fun getFlexibilidad() {
        binding.buttonGetFlexibilidad.setOnClickListener {
            viewModel.getFlexibilidad()
        }
    }

    fun getAllFlexibilidad() {
        binding.buttonGetFlexibilidad.setOnClickListener {
            viewModel.getAllFlexibilidad()
        }
    }
}