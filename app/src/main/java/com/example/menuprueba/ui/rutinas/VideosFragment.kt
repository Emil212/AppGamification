package com.example.menuprueba.ui.rutinas

import android.os.*
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.menuprueba.R
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.ejercicios.infoEjercicios
import com.example.menuprueba.data.model.ejercicios.videosGif
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentVideosBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImplement
import com.example.menuprueba.presentation.rutinas.RutinaViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory
import java.util.*

class VideosFragment : Fragment(R.layout.fragment_videos) {
    private var indexContador = 0
    private var puntuacion = 0
    private lateinit var binding: FragmentVideosBinding
    private val Break =
        "https://firebasestorage.googleapis.com/v0/b/" +
                "gamificationapp-2ff7c.appspot.com/o/" +
                "Presentacion_Descanso%2FDescanso.png?" +
                "alt=media&token=ce61336d-91d2-47de-97" +
                "9e-659040468d7e"
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            RutinasViewModelFactory(EjerciciosRepoImplement(EjerciciosDataSource()))
        ).get(RutinaViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVideosBinding.bind(view)
        selectRoutine()
        //getIRutina()
    }

    private fun getIRutina(): Int {
        var index : Int = -1
        setFragmentResultListener("requestKey2"){ key, bundle ->
            index = bundle.get("bundleKey2") as Int
            Log.d("iRutina", "$index receive")
        }
        return index
    }

    private fun selectRoutine() {
        var indexRutina : Int
        setFragmentResultListener("requestKey2") { key, bundle ->  //recibe dato
            indexRutina = bundle.get("bundleKey2") as Int //recibe dato
            Log.d("iRutina", "$indexRutina receivev")
            //indexRutina es el indice de la rutna
            when (indexRutina) {
                0 -> {
                    observeRoutine0()
                }
                1 -> {

                    Log.d("nuevo", "el indice es: $indexRutina")
                    Toast.makeText(
                        requireContext(),
                        "Elegiste la rutina $indexRutina",
                        Toast.LENGTH_LONG
                    ).show()
                }
                2 -> {
                    Toast.makeText(
                        requireContext(),
                        "Elegiste la rutina $indexRutina",
                        Toast.LENGTH_LONG
                    ).show()

                }
                3 -> {
                    Toast.makeText(
                        requireContext(),
                        "Elegiste la rutina $indexRutina",
                        Toast.LENGTH_LONG
                    ).show()
                }
                4 -> {
                    Toast.makeText(
                        requireContext(),
                        "Elegiste la rutina $indexRutina",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }//when
        }//setFragmentResultListener
    }

    private fun playListRoutine(video0: String, video1: String, video2: String, video3: String, puntuacion: Long) {
        showRoutine(video0, 1000)
        binding.siguinte.setOnClickListener {
            when (indexContador) {
                1 -> {
                    showRoutine(video1, 1000)
                }
                2 -> {
                    showRoutine(video2, 1000)
                }
                3 -> {
                    showRoutine(video3, 1000)
                }
                else -> {
                    puntuacion
                    Log.d("Puntos", "Ganaste $puntuacion puntos")
                    val result = puntuacion.toString()
                    setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                    findNavController().navigate(R.id.action_videosFragment_to_congratulationsFragment)
                    setUpPuntuacion()
                }
            }//when()
        }
    }

    private fun setUpPuntuacion() {
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

    fun showRoutine(elemento: String, duracion: Long) {
        indexContador++

        val imageview = binding.imageView
        val seeTime = binding.time
        object : CountDownTimer(duracion, 1000) {
            override fun onTick(p0: Long) {
                hideButton()
                Glide
                    .with(this@VideosFragment)
                    .load(elemento)
                    //.placeholder(R.drawable.ic_loading)//carga el drawable en lo que se ejecuta "load()"
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
        object : CountDownTimer(1000, 1000) {
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
                Log.d("onFinish Descanso", "Finish")
            }
        }.start()
    }

    fun observeRoutine0() {
        viewModel.getRutina0.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    showProgressBar()
                }
                is Result.Success -> {
                    var lista = result.data //Lista de tipo MutableList<videosGif>
                    Log.d("Lista ", "${makeListVideos(lista)}")
                    val videos = makeListVideos(lista)
                    playListRoutine(videos[0], videos[1], videos[2], videos[3], 200) //Los videos y la puntuacón se mandan por parametro
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

    private fun makeListVideos(lista: MutableList<videosGif>): MutableList<String> {
        var newList = mutableListOf<String>()
        for (aux in lista) {
            var modific = aux.toString()
            modific =
                modific.subSequence(startIndex = 16, endIndex = (modific.length - 1)) as String
            newList.add(modific)
        }
        return newList
    }
}
