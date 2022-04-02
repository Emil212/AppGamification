package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menuprueba.R
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentRutinasBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import com.example.menuprueba.presentation.rutinas.RutinasViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory

class RutinasFragment() : Fragment(R.layout.fragment_rutinas) {

    private lateinit var binding: FragmentRutinasBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRutinasBinding.bind(view)
        val recyclerView = binding.recyclerView
        val adapter = CustomAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

    }
}