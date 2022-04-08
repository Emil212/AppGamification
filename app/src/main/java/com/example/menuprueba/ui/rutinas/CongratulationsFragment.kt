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
            "gamificationapp-2ff7c.appspot.com/o/Presentacion_Descanso%2F" +
            "Congratulations.png?alt=media&token=37d88dd6-1a74-4cda-bbf6-" +
            "7fb7dc66cfe8"
    private lateinit var binding: FragmentCongratulationsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCongratulationsBinding.bind(view)
        val puntos = binding.PX

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_congratulationsFragment_to_nav_rutinas)
        }

        setFragmentResultListener("requestKey"){ key, bundle ->
            val result = bundle.getString("bundleKey")
            puntos.setText("${result}")
        }

        Glide
            .with(this@CongratulationsFragment)
            .load(imgCong)
            .fitCenter()
            .centerCrop()
            .into(binding.congratulations)
    }
}