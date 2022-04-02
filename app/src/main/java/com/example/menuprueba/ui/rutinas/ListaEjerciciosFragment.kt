package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.menuprueba.R
import com.example.menuprueba.databinding.FragmentListaEjerciciosBinding
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentRutinasBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import com.example.menuprueba.presentation.rutinas.RutinasViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory

class ListaEjerciciosFragment : Fragment(R.layout.fragment_lista_ejercicios) {

    val videos : VideosFragment = VideosFragment()


    private lateinit var binding: FragmentListaEjerciciosBinding
/*    private val viewModel by viewModels<RutinasViewModel> {
        RutinasViewModelFactory(
            EjerciciosRepoImpl(
                EjerciciosDataSource()
            )
        )
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //getRutinas()
        binding= FragmentListaEjerciciosBinding.bind(view)
        binding.buttonGetResistencia.setOnClickListener {
            findNavController().navigate(R.id.action_listaEjerciciosFragment_to_nav_rutinas)
        }
    }
/*
    fun getRutinas(){
        viewModel.getAllResistencia()
        viewModel.getAllAerobicos()
        viewModel.getAllFlexibilidad()
        //videos.getGif()
    }*/
}