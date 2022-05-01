package com.example.menuprueba.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.menuprueba.R
import com.example.menuprueba.data.remote.auth.AuthDataSource
import com.example.menuprueba.databinding.FragmentLogOutBinding
import com.example.menuprueba.domain.auth.AuthRepoImpl
import com.example.menuprueba.presentation.auth.AuthViewModel
import com.example.menuprueba.presentation.auth.AuthViewModelFactory
import com.example.menuprueba.core.Result
import com.example.menuprueba.ui.MainActivity
import com.example.menuprueba.ui.rutinas.IOnBackPressed
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlin.system.exitProcess


class LogoutFragment : Fragment(R.layout.fragment_log_out), IOnBackPressed {
    private lateinit var binding: FragmentLogOutBinding
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModelFactory(
            AuthRepoImpl(
                AuthDataSource()
            )
        )
    }
    override fun onBackPressed(): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLogOutBinding.bind(view)
        validateUserIsLogIn()
        doLogOut()
    }

    private fun validateUserIsLogIn(){
        if (FirebaseAuth.getInstance().currentUser == null) {
            findNavController().navigate(R.id.action_logOutFragment_to_loginFragment)
        }
    }

    private fun doLogOut() {
        binding.btnLogOut.setOnClickListener {
            logOut()
        }
    }

    private fun logOut() {
        viewModel.logOut().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnLogOut.visibility = View.GONE
                    binding.txtLogOut.visibility = View.GONE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_logOutFragment_to_loginFragment)
                }

                is Result.Failure -> {

                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.btnLogOut.visibility = View.VISIBLE
                            binding.txtLogOut.visibility = View.VISIBLE
                        }, 2000
                    )

                    Toast.makeText(
                        requireContext(),
                        "Error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

}