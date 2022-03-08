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

class ListaEjerciciosFragment : Fragment(R.layout.fragment_lista_ejercicios) {

    private lateinit var binding : FragmentListaEjerciciosBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListaEjerciciosBinding.bind(view)
    }
}