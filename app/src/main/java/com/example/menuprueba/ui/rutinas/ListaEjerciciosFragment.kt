package com.example.menuprueba.ui.rutinas

import android.content.ClipData
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.menuprueba.R
import com.example.menuprueba.databinding.FragmentListaEjerciciosBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.All
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentRutinasBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImplement
import com.example.menuprueba.presentation.rutinas.RutinaViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory

class ListaEjerciciosFragment : Fragment(R.layout.fragment_lista_ejercicios) {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            RutinasViewModelFactory(EjerciciosRepoImplement(EjerciciosDataSource()))
        ).get(RutinaViewModel::class.java)
    }

    private lateinit var binding: FragmentListaEjerciciosBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListaEjerciciosBinding.bind(view)
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
                    val newList = makeList(lista)
                    val titulos = makeListTitulos(newList)
                    val detalles = makeListDescripcion(newList)
                    val vP = makeListVP(newList)
                    rutina(vP[0], titulos[0], detalles[0], 0, binding.itemImage0, binding.itemTitle0, binding.itemDetal0, binding.cardView0)
                    rutina(vP[1], titulos[1], detalles[1], 1, binding.itemImage1, binding.itemTitle1, binding.itemDetal1, binding.cardView1)
                    rutina(vP[2], titulos[2], detalles[2], 2, binding.itemImage2, binding.itemTitle2, binding.itemDetal2, binding.cardView2)
                    rutina(vP[3], titulos[3], detalles[3], 3, binding.itemImage3, binding.itemTitle3, binding.itemDetal3, binding.cardView3)
                    rutina(vP[4], titulos[4], detalles[4], 4, binding.itemImage4, binding.itemTitle4, binding.itemDetal4, binding.cardView4)
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

    private fun rutina (vP: String, titulos : String, detalles: String, iRutina: Int, itemVP: ImageView,itemTitle : TextView, itemDetail: TextView, cardView: CardView){
        itemDetail.setText(detalles)
        Glide
            .with(requireContext())
            .load(vP)
            .fitCenter()
            .centerCrop()
            .into(itemVP)
        itemTitle.setText(titulos)
        cardView.setOnClickListener {
            findNavController().navigate(R.id.action_nav_listaEjerciciosFragment_to_presentacionFragment)
            val result = iRutina
            Log.d("iRutina", "$iRutina sendr" )
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        }

    }


}