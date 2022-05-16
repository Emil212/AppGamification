package com.example.menuprueba.ui.logros

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.menuprueba.R
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.model.logros.infoUsers
import com.example.menuprueba.data.remote.logros.LogrosDataSource
import com.example.menuprueba.databinding.FragmentLogrosBinding
import com.example.menuprueba.domain.logros.LogrosRepoImplement
import com.example.menuprueba.presentation.logros.LogrosViewModel
import com.example.menuprueba.presentation.logros.LogrosViewModelFactory
import com.example.menuprueba.ui.rutinas.IOnBackPressed


class LogrosFragment : Fragment(R.layout.fragment_logros), IOnBackPressed {
    private lateinit var binding: FragmentLogrosBinding

    private val viewModel by viewModels<LogrosViewModel> {
        LogrosViewModelFactory(LogrosRepoImplement(LogrosDataSource()))
    }

    override fun onBackPressed(): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLogrosBinding.bind(view)
        observeData()
        //logros(10, 30, 60)
    }

    private fun observeData() {
        viewModel.getInfoUsers().observe(viewLifecycleOwner, {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.scrollView.visibility = View.GONE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textLogros.visibility = View.VISIBLE
                    binding.scrollView.visibility = View.VISIBLE
                    val infoUser = it.data
                    clearInfoUser(infoUser)
                }
                is Result.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error: ${it.exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun clearInfoUser(infoUser: Result<infoUsers>) {
        var infoUserCls = infoUser.toString().replace("Success(data=infoUsers(username=", "")
        infoUserCls = infoUserCls.replace("))", "")
        infoUserCls = infoUserCls.split(", points=").toString()
        infoUserCls = infoUserCls.replace("[", "")
        infoUserCls = infoUserCls.replace("]", "")
        infoUserCls = infoUserCls.replace("repRoutine1=", "")
        infoUserCls = infoUserCls.replace("repRoutine2=", "")
        infoUserCls = infoUserCls.replace("repRoutine3=", "")
        val username = infoUserCls.substringBefore(",")
        var points = infoUserCls.substringAfter(", ")
        points = points.substringBefore(", ")
        val textView = binding.textLogros
        textView.text = "$username tiene $points puntos"
        val valor1 = infoUserCls.substringAfter(", ")
        val x = valor1.substringAfter(", ")
        val xx = x.substringBefore(", ")
        val routine1 = xx
        val y = x.substringAfter(", ")
        val yy = y.substringBefore(",")
        val routine2 = yy
        val zz = y.substringAfter(", ")
        val routine3 = zz
        logros(routine1, routine2, routine3 ) //cuidar que no queden espacios en blanco
    }

    private fun conditionToast(meta: Int, valor: Int){
        if (valor >= meta){
            Toast.makeText(requireContext(), "Lograste alcanzar el objetivo, $meta repeticiones", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Necesitas $meta repeticiones para alcanzar este logro y llevas $valor", Toast.LENGTH_LONG).show()
        }
    }

    private fun routineA (valor1: Int){
        while (valor1>=10){
            binding.logro1a.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro11a.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro111a.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor1>=30){
            binding.logro2a.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro22a.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro222a.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor1>=60){
            binding.logro3a.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro33a.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro333a.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor1>=100){
            binding.logro4a.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro44a.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro444a.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor1>=200){
            binding.logro5a.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro55a.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro555a.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }

        binding.logro1a.setOnClickListener {
            conditionToast(10, valor1)
        }
        binding.logro2a.setOnClickListener {
            conditionToast(30, valor1)
        }
        binding.logro3a.setOnClickListener {
            conditionToast(60, valor1)
        }
        binding.logro4a.setOnClickListener {
            conditionToast(100, valor1)
        }
        binding.logro5a.setOnClickListener {
            conditionToast(200, valor1)
        }

    }

    private fun routineB (valor2: Int){
        while (valor2>=10){
            binding.logro1b.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro11b.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro111b.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor2>=30){
            binding.logro2b.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro22b.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro222b.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor2>=60){
            binding.logro3b.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro33b.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro333b.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor2>=100){
            binding.logro4b.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro44b.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro444b.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor2>=200){
            binding.logro5b.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro55b.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro555b.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        binding.logro1b.setOnClickListener {
            conditionToast(10, valor2)
        }
        binding.logro2b.setOnClickListener {
            conditionToast(30, valor2)
        }
        binding.logro3b.setOnClickListener {
            conditionToast(60, valor2)
        }
        binding.logro4b.setOnClickListener {
            conditionToast(100, valor2)
        }
        binding.logro5b.setOnClickListener {
            conditionToast(200, valor2)
        }
    }

    private fun routineC (valor3: Int){
        while (valor3>=10){
            binding.logro1c.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro11c.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro111c.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor3>=30){
            binding.logro2c.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro22c.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro222c.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor3>=60){
            binding.logro3c.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro33c.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro333c.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor3>=100){
            binding.logro4c.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro44c.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro444c.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        while (valor3>=200){
            binding.logro5c.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro55c.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            binding.logro555c.setColorFilter(ContextCompat.getColor(requireContext(),R.color.cup_unlock))
            break
        }
        binding.logro1c.setOnClickListener {
            conditionToast(10, valor3)
        }
        binding.logro2c.setOnClickListener {
            conditionToast(30, valor3)
        }
        binding.logro3c.setOnClickListener {
            conditionToast(60, valor3)
        }
        binding.logro4c.setOnClickListener {
            conditionToast(100, valor3)
        }
        binding.logro5c.setOnClickListener {
            conditionToast(200, valor3)
        }
    }

    private fun logros (valor1: String, valor2: String, valor3: String){
        routineA(valor1.toInt())
        routineB(valor2.toInt())
        routineC(valor3.toInt())
    }
}