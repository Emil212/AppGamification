package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menuprueba.R
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.All
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentRutinasBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import com.example.menuprueba.presentation.rutinas.RutinaViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory

class RutinasFragment() : Fragment(R.layout.fragment_rutinas) {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            RutinasViewModelFactory(EjerciciosRepoImpl(EjerciciosDataSource()))
        ).get(RutinaViewModel::class.java)
    }

    private lateinit var binding: FragmentRutinasBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRutinasBinding.bind(view)
        observeData()
    }

    fun showProgressBar (){
        binding.progressBar.visibility = View.VISIBLE
    }
    fun hideProgressBar (){
        binding.progressBar.visibility = View.GONE
    }
    fun observeData() {
        viewModel.getAll.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    showProgressBar()
                }
                is Result.Success -> {
                    hideProgressBar()
                    var lista = result.data //Lista de tipo MutableList<Titulos>
                    val recyclerView = binding.recyclerView
                    val newList = makeList(lista)
                    val titulos = makeListTitulos(newList)
                    val detalles = makeListDescripcion(newList)
                    val vP = makeListVP(newList)
                    Log.d("VP2:", "${lista}")
                    //Log.d("TP:", "${makeListTitulos(makeList(lista))[1].length}")
                    //Log.d("DP:", "${makeListDescripcion(makeList(lista))[1].length}")
                    val adapter = CustomAdapter(titulos, detalles, vP, requireContext())
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                    ////////////////////Envia dato prueba
                    val iRutina = 0
                    val result = iRutina
                    Log.d("iRutina", "$iRutina sendr" )
                    setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                    ////////////////////Fin de enviar dato prueba

                }
                is Result.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error: ${result.exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
    private fun makeList(lista: MutableList<All>) : MutableList<String>{
        var newList = mutableListOf<String>()
        for (aux in lista) {
            var modific = aux.toString()
            modific = modific.subSequence(startIndex = 4, endIndex = (modific.length - 1)) as String
            newList.add(modific)
        }
        return newList
    }
    private fun makeListTitulos(lista: MutableList<String>) : MutableList<String>{ //20 caracteres de titulos
        var newList = mutableListOf<String>()
        for (aux in lista) {
            var modific = aux
            modific = modific.subSequence(startIndex = 91, endIndex = 110) as String
            modific = modific.replace(".", "")
            newList.add(modific)
        }
        return newList
    }

    private fun makeListDescripcion(lista: MutableList<String>) : MutableList<String>{//70 caracteres de descripcion
        //El tamaño de la descripción debe de ser de 40 Caracteres -> 70
        var newList = mutableListOf<String>()
        for (aux in lista) {
            var modific = aux
            modific = modific.subSequence(startIndex = 12, endIndex = 81) as String
            modific = modific.replace(".", "")
            newList.add(modific)
        }
        return newList
    }
    private fun makeListVP(lista: MutableList<String>) : MutableList<String>{//150 caracteres de VP con nombre VP_R####.jpg
        var newList = mutableListOf<String>()
        for (aux in lista) {
            var modific = aux
            modific = modific.subSequence(startIndex = 126, endIndex = (modific.length)) as String
            newList.add(modific)
        }
        return newList
    }

}