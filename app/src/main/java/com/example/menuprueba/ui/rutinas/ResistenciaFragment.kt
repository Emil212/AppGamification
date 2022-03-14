package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.menuprueba.R
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentAerobicosBinding
import com.example.menuprueba.databinding.FragmentResistenciaBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import com.example.menuprueba.presentation.rutinas.RutinasViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory

class ResistenciaFragment : Fragment(R.layout.fragment_resistencia) {

    private lateinit var binding: FragmentResistenciaBinding
    private val viewModel by viewModels<RutinasViewModel> {
        RutinasViewModelFactory(
            EjerciciosRepoImpl(
                EjerciciosDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResistenciaBinding.bind(view)
        save()
    }

    fun save (){
        binding.buttonSave.setOnClickListener {
            findNavController().navigate(R.id.action_nav_resistenciaFragment_to_nav_rutinas)
            //Aquí va la lógica del para guardar las rutinas
        }
    }
}