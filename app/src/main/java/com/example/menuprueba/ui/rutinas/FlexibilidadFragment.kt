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
import com.example.menuprueba.databinding.FragmentFlexibilidadBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import com.example.menuprueba.presentation.rutinas.RutinasViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory

class FlexibilidadFragment : Fragment(R.layout.fragment_flexibilidad) {

    private lateinit var binding: FragmentFlexibilidadBinding
    private val viewModel by viewModels<RutinasViewModel> {
        RutinasViewModelFactory(
            EjerciciosRepoImpl(
                EjerciciosDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFlexibilidadBinding.bind(view)
        getAerobicos()
    }

    fun getAerobicos (){
        binding.buttonNextFlexibility.setOnClickListener {
/*            viewModel.getAllFlexibilidad()
            viewModel.getAllResistencia()
            viewModel.getAllAerobicos()*/
            findNavController().navigate(R.id.action_nav_flexibilidadFragment_to_aerobicosFragment)
        }
    }
}