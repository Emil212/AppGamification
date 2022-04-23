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
import com.example.menuprueba.data.model.ejercicios.videosGif
import com.example.menuprueba.data.remote.ejercicios.EjerciciosDataSource
import com.example.menuprueba.databinding.FragmentVideosBinding
import com.example.menuprueba.domain.ejercicios.EjerciciosRepoImpl
import com.example.menuprueba.presentation.rutinas.RutinaViewModel
import com.example.menuprueba.presentation.rutinas.RutinasViewModelFactory
import java.util.*

class VideosFragment : Fragment(R.layout.fragment_videos) {
    private var indexContador = 0
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
            RutinasViewModelFactory(EjerciciosRepoImpl(EjerciciosDataSource()))
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
        showRoutine(video0, 10000, 5000)
        binding.siguinte.setOnClickListener {
            when (indexContador) {
                1 -> {
                    showRoutine(video1, 10000, 5000)
                }
                2 -> {
                    showRoutine(video2, 10000, 5000)
                }
                3 -> {
                    showRoutine(video3, 10000, 5000)
                }
                else -> {
                    //puntuacion
                    Log.d("Puntos", "Ganaste $puntuacion puntos")
                    val result = puntuacion.toString()
                    setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                    findNavController().navigate(R.id.action_videosFragment_to_congratulationsFragment)
                    setUpPuntuacion(puntuacion)
                }
            }//when()
        }
    }

    private fun setUpPuntuacion(puntuacion: Long) {
        viewModel.incrementPuntuacion(puntuacion).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    Log.d("Cargando", "Cargando puntuacion")
                }

                is Result.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Incremento: ${puntuacion}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Result.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
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

    fun showRoutine(elemento: String, duracion: Long, descanso:Long) {
        indexContador++


        val imageview = binding.imageView
        val seeTime = binding.time
        val temp: CountDownTimer? = object : CountDownTimer(duracion+100, 1000- 100) {
            override fun onTick(p0: Long) {
                hideButton()
                binding.cancel.isEnabled = true
                Glide
                    .with(this@VideosFragment)
                    .load(elemento)
                    //.placeholder(R.drawable.ic_loading)//carga el drawable en lo que se ejecuta "load()"
                    //.fitCenter()
                    //.centerCrop()
                    .into(imageview)
                val segundos = (p0 / 1000) % 60
                val mostrar = String.format("%02d", segundos)
                seeTime.setText(mostrar)

            }
            override fun onFinish() {
                timeBreak(descanso)
            }
        }.start()

        binding.cancel.setOnClickListener {
            if (temp != null) {
                temp.cancel()
                findNavController().navigate(R.id.action_videosFragment_to_nav_listaEjerciciosFragment)
            }
        }
    }

    private fun timeBreak(descanso: Long) {
        val imageview = binding.imageView
        val seeTime = binding.time
        val temp = object : CountDownTimer(descanso + 100, 1000-100) {
            override fun onTick(p0: Long) {
                hideButton()
                binding.cancel.isEnabled = true
                Log.d("onTick Descanso", "Descanso")
                Glide
                    .with(this@VideosFragment)
                    .load(Break)
                    //.fitCenter()
                    //.centerCrop()
                    .into(imageview)
                val segundos = (p0 / 1000) % 60
                val mostrar = String.format("%02d", segundos)
                seeTime.setText(mostrar)
            }

            override fun onFinish() {
                showButton()
                binding.cancel.isEnabled = false
            }
        }.start()

        binding.cancel.setOnClickListener {
            if (temp != null) {
                temp.cancel()
                findNavController().navigate(R.id.action_videosFragment_to_nav_listaEjerciciosFragment)
            }
        }
    }

    fun observeRoutine0() {
        viewModel.getRutina0.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    showProgressBar()
                }
                is Result.Success -> {
                    val lista = result.data //Lista de tipo MutableList<videosGif>
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
        val newList = mutableListOf<String>()
        for (aux in lista) {
            var modific = aux.toString()
            modific =
                modific.subSequence(startIndex = 16, endIndex = (modific.length - 1)) as String
            newList.add(modific)
        }
        return newList
    }
}
