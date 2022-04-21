package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.menuprueba.R
import com.example.menuprueba.databinding.FragmentCongratulationsBinding

class CongratulationsFragment : Fragment(R.layout.fragment_congratulations) {
    private val  imgCong = "https://firebasestorage.googleapis.com/v0/b/" +
            "gamificationapp-2ff7c.appspot.com/o/Presentacion_Descanso%2" +
            "FCongratulations.png?alt=media&token=10fcd0ee-32d5-43bb-b4b" +
            "b-823056a73f74"
    private lateinit var binding: FragmentCongratulationsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCongratulationsBinding.bind(view)
        val puntos = binding.PX

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_congratulationsFragment_to_nav_listaEjerciciosFragment)
        }

        setFragmentResultListener("requestKey"){ key, bundle ->
            val result = bundle.getString("bundleKey")
            puntos.text = "¡Felicidades!\nhas ganado\n${result}\npuntos"
        }

        Glide
            .with(this@CongratulationsFragment)
            .load(imgCong)
            .fitCenter()
            .centerCrop()
            .into(binding.congratulations)
    }
}