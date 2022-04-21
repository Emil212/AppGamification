package com.example.menuprueba.ui.logros

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class LogrosFragment : Fragment(R.layout.fragment_logros) {
    private lateinit var binding: FragmentLogrosBinding

    private val viewModel by viewModels<LogrosViewModel> {
        LogrosViewModelFactory(LogrosRepoImplement(LogrosDataSource()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLogrosBinding.bind(view)
        observeData()
    }

    private fun observeData() {
        viewModel.getInfoUsers().observe(viewLifecycleOwner, {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textLogros.visibility = View.VISIBLE
                    val infoUser = it.data
                    ////////////////////////
                    val user = FirebaseAuth.getInstance().currentUser!!
                    user.sendEmailVerification()

                    Log.d("Verificacion", "Email enviado a ${user.email}")


                    ////////////////////////

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

}