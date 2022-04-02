package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.menuprueba.R
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.videosGif
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentPresentacionBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImplement
import com.example.menuprueba.presentation.rutinas.RutinaViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory


class PresentacionFragment : Fragment(R.layout.fragment_presentacion) {


    private lateinit var binding: FragmentPresentacionBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPresentacionBinding.bind(view)
        seePresentation()
        gotoVideosFragment()
    }

    protected fun gotoVideosFragment() {
        val iniciar = binding.buttonIniciar
        iniciar.setOnClickListener {
            findNavController().navigate(R.id.action_presentacionFragment_to_videosFragment)
        }
    }

    private fun seePresentation() {
        val imageview = binding.presentacion
        val Image =
            "https://firebasestorage.googleapis.com/v0/b/gamificationapp-2ff7c.appspot.com/o/Presentacion_Descanso%2FPresentacion.png?alt=media&token=9bf4369a-1895-420c-a039-943b3cbe3455"
        Glide
            .with(this@PresentacionFragment)
            .load(Image)
            .fitCenter()
            .centerCrop()
            .into(imageview)
    }
}
