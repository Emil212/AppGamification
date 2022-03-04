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
        val boton_add_rutinas = binding.buttonAddRutina
        val name_rutinas = binding.editTextRutinas
        val list_view = binding.listaRutinas
        var lista = mutableListOf<String>()



        boton_add_rutinas.setOnClickListener {
            val text = name_rutinas.getText().toString().trim()
            lista.add(text)
            name_rutinas.getText().clear()
            var adapter = activity?.let {
                ArrayAdapter<String>(it,android.R.layout.simple_list_item_1,lista)
            }
            list_view.adapter = adapter
        }

        list_view.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(activity?.let { it }, "Item Clicked: $i", Toast.LENGTH_SHORT).show()
        })



    }
}