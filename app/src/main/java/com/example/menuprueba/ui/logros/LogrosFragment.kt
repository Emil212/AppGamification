package com.example.menuprueba.ui.logros

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.type.Color


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
        logros(50)
    }

    private fun observeData() {
        viewModel.getInfoUsers().observe(viewLifecycleOwner, {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.scroll1.visibility = View.GONE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textLogros.visibility = View.VISIBLE
                    binding.scroll1.visibility = View.VISIBLE
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
        val username = infoUserCls.substringBefore(",")
        val points = infoUserCls.substringAfter(", ")
        val textView = binding.textLogros
        textView.text = "$username tiene $points puntos"
    }

    private fun logros (meta: Int/*, meta2: Int, meta3: Int, meta4: Int*/){

        binding.logro1.setOnClickListener {
            if (meta>=10){
                Toast.makeText(requireContext(), "Lograste alcanzar el objetivo c:", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Te faltan ${10 - meta} repeticiones para alcanzar este logro", Toast.LENGTH_LONG).show()
            }
        }
        binding.logro2.setOnClickListener {
            if (meta>=30){
                Toast.makeText(requireContext(), "Lograste alcanzar el objetivo c:", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Te faltan ${30 - meta} repeticiones para alcanzar este logro", Toast.LENGTH_LONG).show()
            }
        }
        binding.logro3.setOnClickListener {
            if (meta>=60){
                Toast.makeText(requireContext(), "Lograste alcanzar el objetivo c:", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Te faltan ${60 - meta} repeticiones para alcanzar este logro", Toast.LENGTH_LONG).show()
            }
        }
        binding.logro4.setOnClickListener {
            if (meta>=100){
                Toast.makeText(requireContext(), "Lograste alcanzar el objetivo c:", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Te faltan ${100 - meta} repeticiones para alcanzar este logro", Toast.LENGTH_LONG).show()
            }
        }
        binding.logro5.setOnClickListener {
            if (meta>=200){
                Toast.makeText(requireContext(), "Lograste alcanzar el objetivo c:", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Te faltan ${200 - meta} repeticiones para alcanzar este logro", Toast.LENGTH_LONG).show()
            }
        }
        while (meta>=10){
            binding.logro1.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro11.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            break
        }
        while (meta>=30){
            binding.logro2.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro22.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            /*if (meta>=30){
                Toast.makeText(requireContext(), "Lograste alcanzar el objetivo c:", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Te faltan ${30 - meta} repeticiones para alcanzar este logro", Toast.LENGTH_LONG).show()
            }*/
            break
        }
        while (meta>=60){
            binding.logro3.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro33.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
           /* if (meta>=60){
                Toast.makeText(requireContext(), "Lograste alcanzar el objetivo c:", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Te faltan ${60 - meta} repeticiones para alcanzar este logro", Toast.LENGTH_LONG).show()
            }*/
            break
        }
        while (meta>=100){
            binding.logro4.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro44.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
            /*if (meta>=100){
                Toast.makeText(requireContext(), "Lograste alcanzar el objetivo c:", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Te faltan ${100 - meta} repeticiones para alcanzar este logro", Toast.LENGTH_LONG).show()
            }*/
            break
        }
        while (meta>=200){
            binding.logro5.setStrokeColor(ContextCompat.getColor(requireContext(),R.color.border_unlock))
            binding.logro55.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.filling_unlock))
           /* if (meta>=200){
                Toast.makeText(requireContext(), "Lograste alcanzar el objetivo máximo c:", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Te faltan ${200 - meta} repeticiones para alcanzar este logro", Toast.LENGTH_LONG).show()
            }*/
            break
        }
    }
}