package com.example.menuprueba.ui.rutinas

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.menuprueba.R
import com.example.menuprueba.application.AppConstants.vp0
import com.example.menuprueba.application.AppConstants.vp1
import com.example.menuprueba.application.AppConstants.vp2
import com.example.menuprueba.application.AppConstants.vp3
import com.example.menuprueba.application.AppConstants.vp4
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.infoEjercicios
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentPresentacionBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import com.example.menuprueba.presentation.rutinas.RutinaViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory


class PresentacionFragment : Fragment(R.layout.fragment_presentacion), IOnBackPressed {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            RutinasViewModelFactory(EjerciciosRepoImpl(EjerciciosDataSource()))
        ).get(RutinaViewModel::class.java)
    }
    private lateinit var binding: FragmentPresentacionBinding

    override fun onBackPressed(): Boolean {
        return if (1==1) {
            true //permitir ir atrás
        } else {
            false //cancelar acción
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPresentacionBinding.bind(view)
        gotoVideosFragment()
        viewRoutine0()
    }

    private fun selectRoutine(lista : MutableList<infoEjercicios>) {
        val recyclerView = binding.recyclerView
        var indexRutina : Int
        var nombres : MutableList<String>
        setFragmentResultListener("requestKey") { key, bundle ->  //recibe dato
            indexRutina = bundle.get("bundleKey") as Int //recibe dato
            Log.d("iRutina", "$indexRutina receivep")
            //indexRutina es el indice de la rutna
            when (indexRutina) {
                0 -> {
                    nombres = makeListNombres(lista[0])
                    val adapter1 = CustomAdapterPresentation(nombres, requireContext(), vp0)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter1
                    ////////////////////Envia dato prueba
                    sedIndex(indexRutina)
                    ////////////////////Fin de enviar dato prueba
                }
                1 -> {
                    nombres = makeListNombres(lista[1])
                    val adapter = CustomAdapterPresentation(nombres, requireContext(), vp1)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                    ////////////////////Envia dato prueba
                    sedIndex(indexRutina)
                    ////////////////////Fin de enviar dato prueba
                }
                2 -> {
                    nombres = makeListNombres(lista[2])
                    val adapter = CustomAdapterPresentation(nombres, requireContext(), vp2)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                    ////////////////////Envia dato prueba
                    sedIndex(indexRutina)
                    ////////////////////Fin de enviar dato prueba
                }
                3 -> {
                    Toast.makeText(
                        requireContext(),
                        "Estos son los ejercicios de la rutina $indexRutina",
                        Toast.LENGTH_LONG
                    ).show()
                    nombres = makeListNombres(lista[3])
                    val adapter = CustomAdapterPresentation(nombres, requireContext(), vp3)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                    ////////////////////Envia dato prueba
                    sedIndex(indexRutina)
                    ////////////////////Fin de enviar dato prueba
                }
                4 -> {
                    nombres = makeListNombres(lista[4])
                    val adapter = CustomAdapterPresentation(nombres, requireContext(), vp4)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                    ////////////////////Envia dato prueba
                    sedIndex(indexRutina)
                    ////////////////////Fin de enviar dato prueba
                }
            }//when
        }//setFragmentResultListener
    }

    private fun sedIndex(iRutina: Int){
        val result = iRutina
        Log.d("iRutina", "$iRutina sendp" )
        setFragmentResult("requestKey2", bundleOf("bundleKey2" to result))
    }

    fun viewRoutine0() {
        viewModel.getInfoEjercicios.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    showProgressBar()
                }
                is Result.Success -> {
                    var lista = result.data //Lista de tipo MutableList<videosGif>
                    Log.d("Nombres", "${lista}")
                    selectRoutine(lista)
                    hideProgressBar()
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
    private fun makeListNombres(elemento: infoEjercicios): MutableList<String> {
        var modific = elemento.toString()
        modific = modific.subSequence(startIndex = 24, endIndex = (modific.length - 2)) as String
        var x: MutableList<String> = modific.split(",") as MutableList<String>
        return x
    }

    protected fun gotoVideosFragment() {
        val iniciar = binding.buttonIniciar
        iniciar.setOnClickListener {
            findNavController().navigate(R.id.action_presentacionFragment_to_videosFragment)
        }
    }
    private fun showProgressBar() {
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressbar.visibility = View.GONE
    }
}
