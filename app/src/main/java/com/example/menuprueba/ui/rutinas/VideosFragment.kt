package com.example.menuprueba.ui.rutinas

import android.content.Context
import android.os.*
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
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
import java.util.concurrent.TimeUnit
import androidx.core.content.ContextCompat.getSystemService

import android.os.Vibrator


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
    }

    fun vibrateDevice(context: Context) {
        val vibrator = getSystemService(context, Vibrator::class.java)
        vibrator?.let {
            if (Build.VERSION.SDK_INT >= 26) {
                it.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                it.vibrate(100)
            }
        }
    }


    private fun selectRoutine() {
        var indexRutina: Int
        setFragmentResultListener("requestKey2") { key, bundle ->  //recibe dato
            indexRutina = bundle.get("bundleKey2") as Int //recibe dato
            Log.d("iRutina", "$indexRutina receivev")
            //indexRutina es el indice de la rutna
            when (indexRutina) {
                0 -> {
                    observeRoutine0()
                }
                1 -> {
                    observeRoutine1()
                }
                2 -> {
                    observeRoutine2()
                }
            }//when
        }//setFragmentResultListener
    }

    private fun playListRoutine(
        video0: String,
        video1: String,
        video2: String,
        video3: String,
        video4: String,
        video5: String,
        video6: String,
        video7: String,
        puntuacion: Long,
        routine1: Long,
        routine2: Long,
        routine3: Long
    ) {
        showRoutine(video0, 5000, 5000)
        binding.siguinte.setOnClickListener {
            when (indexContador) {
                1 -> {
                    showRoutine(video1, 5000, 5000)
                }
                2 -> {
                    showRoutine(video2, 5000, 5000)
                }
                3 -> {
                    showRoutine(video3, 5000, 5000)
                }
                4 -> {
                    showRoutine(video4, 5000, 5000)
                }
                5 -> {
                    showRoutine(video5, 5000, 5000)
                }
                6 -> {
                    showRoutine(video6, 5000, 5000)
                }
                7 -> {
                    showRoutine(video7, 5000, 5000)
                }
                else -> {
                    //puntuacion
                    Log.d("Puntos", "Ganaste $puntuacion puntos")
                    val result = puntuacion.toString()
                    setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                    findNavController().navigate(R.id.action_videosFragment_to_congratulationsFragment)
                    setUpRepRoutines(routine1, routine2, routine3)
                    setUpPuntuacion(puntuacion)
                }
            }//when()
        }
    }


    private fun playListRoutine(
        video0: String,
        video1: String,
        video2: String,
        video3: String,
        puntuacion: Long,
        routine1: Long,
        routine2: Long,
        routine3: Long
    ) {
        showRoutine(video0, 5000, 5000)
        binding.siguinte.setOnClickListener {
            when (indexContador) {
                1 -> {
                    showRoutine(video1, 5000, 5000)
                }
                2 -> {
                    showRoutine(video2, 5000, 5000)
                }
                3 -> {
                    showRoutine(video3, 5000, 5000)
                }
                else -> {
                    //puntuacion
                    Log.d("Puntos", "Ganaste $puntuacion puntos")
                    val result = puntuacion.toString()
                    setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                    findNavController().navigate(R.id.action_videosFragment_to_congratulationsFragment)
                    setUpRepRoutines(routine1, routine2, routine3)
                    setUpPuntuacion(puntuacion)
                }
            }//when()
        }
    }

    private fun playListRoutine(
        video0: String,
        video1: String,
        puntuacion: Long,
        routine1: Long,
        routine2: Long,
        routine3: Long
    ) {
        showRoutine(video0, 5000, 5000)
        binding.siguinte.setOnClickListener {
            when (indexContador) {
                1 -> {
                    showRoutine(video1, 5000, 5000)
                }
                else -> {
                    //puntuacion
                    Log.d("Puntos", "Ganaste $puntuacion puntos")
                    val result = puntuacion.toString()
                    setFragmentResult("requestKey", bundleOf("bundleKey" to result))
                    findNavController().navigate(R.id.action_videosFragment_to_congratulationsFragment)
                    setUpRepRoutines(routine1, routine2, routine3)
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

    private fun setUpRepRoutines(routine1: Long, routine2: Long, routine3: Long) {
        viewModel.incrementRoutines(routine1, routine2, routine3)
            .observe(viewLifecycleOwner, { result ->
                when (result) {
                    is Result.Loading -> {
                        Log.d("Cargando", "Cargando puntuacion")
                    }

                    is Result.Success -> {
                        Toast.makeText(
                            requireContext(),
                            "Se incremento el contador de la rutina, R1: $routine1, R2: $routine2, R3: $routine3 ",
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

    fun showRoutine(elemento: String, duracion: Long, descanso: Long) {
        indexContador++
        val imageview = binding.imageView
        val seeTime = binding.time
        val temp: CountDownTimer? = object : CountDownTimer(duracion + 100, 1000 - 100) {
            override fun onTick(p0: Long) {
                hideButton()
                binding.cancel.isEnabled = true
                Glide
                    .with(this@VideosFragment)
                    .load(elemento)
                    //.placeholder(R.drawable.ic_loading)//carga el drawable en lo que se ejecuta "load()"
                    .fitCenter()
                    .centerCrop()
                    .into(imageview)
                seeTime.setText(
                    "" + String.format(
                        "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(p0),
                        TimeUnit.MILLISECONDS.toSeconds(p0) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(p0))
                    )
                )
            }


            //@RequiresApi(Build.VERSION_CODES.S)
            override fun onFinish() {
                vibrateDevice(requireContext())
                timeBreak(descanso)
                //  val vibratorManager:VibratorManager = requireContext().getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                // vibratorManager.defaultVibrator
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
        val temp = object : CountDownTimer(descanso + 100, 1000 - 100) {
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
                seeTime.setText(
                    "" + String.format(
                        "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(p0),
                        TimeUnit.MILLISECONDS.toSeconds(p0) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(p0))
                    )
                )
            }

            override fun onFinish() {
                vibrateDevice(requireContext())
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
        viewModel.getRutina0.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    showProgressBar()
                }
                is Result.Success -> {
                    val lista = result.data //Lista de tipo MutableList<videosGif>
                    Log.d("Lista ", "${makeListVideos(lista)}")
                    val videos = makeListVideos(lista)
                    playListRoutine(
                        videos[0],
                        videos[1],
                        videos[2],
                        videos[3],
                        videos[4],
                        videos[5],
                        videos[6],
                        videos[7],
                        200,
                        1,
                        0,
                        0
                    ) //Los videos y la puntuacón se mandan por parametro
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

    fun observeRoutine1() {
        viewModel.getRutina1.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    showProgressBar()
                }
                is Result.Success -> {
                    val lista = result.data //Lista de tipo MutableList<videosGif>
                    Log.d("Lista ", "${makeListVideos(lista)}")
                    val videos = makeListVideos(lista)
                    playListRoutine(
                        videos[0],
                        videos[1],
                        videos[2],
                        videos[3],
                        200,
                        0,
                        1,
                        0
                    ) //Los videos y la puntuacón se mandan por parametro
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

    fun observeRoutine2() {
        viewModel.getRutina2.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    showProgressBar()
                }
                is Result.Success -> {
                    val lista = result.data //Lista de tipo MutableList<videosGif>
                    Log.d("Lista ", "${makeListVideos(lista)}")
                    val videos = makeListVideos(lista)
                    playListRoutine(
                        videos[0],
                        videos[1],
                        200,
                        0,
                        0,
                        1
                    ) //Los videos y la puntuacón se mandan por parametro
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
