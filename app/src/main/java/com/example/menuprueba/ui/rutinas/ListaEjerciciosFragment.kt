package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.menuprueba.R
import com.example.menuprueba.databinding.FragmentListaEjerciciosBinding
import androidx.navigation.fragment.findNavController

class ListaEjerciciosFragment : Fragment(R.layout.fragment_lista_ejercicios) {

    private lateinit var binding: FragmentListaEjerciciosBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentListaEjerciciosBinding.bind(view)
        binding.buttonGetResistencia.setOnClickListener {
            findNavController().navigate(R.id.action_listaEjerciciosFragment_to_nav_rutinas)
        }
    }
}