package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.menuprueba.R
import com.example.menuprueba.databinding.FragmentRutinasBinding


class RutinasFragment : Fragment(R.layout.fragment_rutinas) {

    private lateinit var binding: FragmentRutinasBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRutinasBinding.bind(view)
    }
}