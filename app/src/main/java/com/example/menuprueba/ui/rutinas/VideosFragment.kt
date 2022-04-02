package com.example.menuprueba.ui.rutinas

import android.os.*
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.menuprueba.R
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.EjerciciosNombre
import com.example.menuprueba.data.model.ejercicios.videosGif
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentVideosBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImplement
import com.example.menuprueba.presentation.rutinas.RutinaViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory

class VideosFragment : Fragment(R.layout.fragment_videos) {
    private var indexContador = 0
    private var puntuacion = 0
    private lateinit var binding: FragmentVideosBinding
    private val Break =
        "https://firebasestorage.googleapis.com/v0/b/gamificationapp-2ff7c.appspot.com/o/Presentacion_Descanso%2FDescanso.png?alt=media&token=ce61336d-91d2-47de-979e-659040468d7e"
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            RutinasViewModelFactory(EjerciciosRepoImplement(EjerciciosDataSource()))
        ).get(RutinaViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVideosBinding.bind(view)
        observeData()
    }

    private fun rutina1 (video0: String, video1: String, video2: String, video3: String){
        showRoutine(video0, 11000)
        binding.siguinte.setOnClickListener {
            when(indexContador){
                1->{
                    showRoutine(video1, 11000)
                }
                2->{
                    showRoutine(video2, 11000)
                }
                3-> {
                    showRoutine(video3, 11000)
                }
                else ->{
                    puntuacion = 100
                    Log.d("Puntos", "Ganaste $puntuacion puntos")
                    findNavController().navigate(R.id.action_videosFragment_to_nav_rutinas)
                    setPuntuacion()
                }
            }
        }//onClick
    }

    private fun setPuntuacion() {
        //seteo de la puntuación a la DB
    }

    private fun showButton() {
        binding.siguinte.isEnabled = true
    }

    private fun hideButton() {
        binding.siguinte.isEnabled = false
    }

    private fun showProgressBar() {
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressbar.visibility = View.GONE
    }

    fun showRoutine(elemento: String, duracion : Long) {
        indexContador ++

        val imageview = binding.imageView
        val seeTime = binding.time
        object : CountDownTimer(duracion, 1000) {
            override fun onTick(p0: Long) {
                hideButton()
                Glide
                    .with(this@VideosFragment)
                    .load(elemento)
                    .placeholder(R.drawable.ic_rutinas)//carga el drawable en lo que se ejecuta "load()"
                    .fitCenter()
                    .centerCrop()
                    .into(imageview)
                val segundos = (p0 / 1000) % 60
                val mostrar = String.format("%02d", segundos)
                seeTime.setText("$mostrar s")
            }

            override fun onFinish() {
                timeBreak()
            }
        }.start()
    }

    private fun timeBreak() {
        var imageview = binding.imageView
        val seeTime = binding.time
        object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                hideButton()
                Log.d("onTick Descanso", "Descanso")
                Glide
                    .with(this@VideosFragment)
                    .load(Break)
                    .fitCenter()
                    .centerCrop()
                    .into(imageview)
                val segundos = (p0 / 1000) % 60
                val mostrar = String.format("%02d", segundos)
                seeTime.setText(mostrar)
            }
            override fun onFinish() {
                showButton()
                //seeGif1(videos[1].toString(), 10000)
                //imageview.setImageResource(android.R.color.transparent)
                //seeTime.setText("Fin")
                Log.d("onFinish Descanso", "Finish")
            }
        }.start()
    }




    private fun makeElement(lista: MutableList<videosGif>): String {
        var index = 0
        var elemento = lista[index].toString()
        elemento = elemento.subSequence(startIndex = 16, endIndex = (elemento.length - 1)) as String
        return elemento
    }

    private fun observeData() {
        viewModel.getRutina.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    showProgressBar()
                }
                is Result.Success -> {
                    var lista = result.data //Lista de tipo MutableList<videosGif>
                    Log.d("Lista ", "${makeList(lista)}")
                    val videos = makeList(lista)
                    hideProgressBar()
                    rutina1(videos[0],videos[1], videos[2],videos[3])
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

    private fun makeList(lista: MutableList<videosGif>) : MutableList<String>{
        var newList = mutableListOf<String>()
        for (aux in lista) {
            var modific = aux.toString()
            modific = modific.subSequence(startIndex = 16, endIndex = (modific.length - 1)) as String
            newList.add(modific)
        }
        return newList
    }
}
