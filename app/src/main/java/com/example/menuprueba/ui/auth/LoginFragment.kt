package com.example.menuprueba.ui.auth


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.menuprueba.R
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.remote.auth.AuthDataSource
import com.example.menuprueba.databinding.FragmentLoginBinding
import com.example.menuprueba.domain.auth.AuthRepoImpl
import com.example.menuprueba.presentation.auth.AuthViewModel
import com.example.menuprueba.presentation.auth.AuthViewModelFactory
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val viewModel by viewModels<AuthViewModel> {
        AuthViewModelFactory(
            AuthRepoImpl(
                AuthDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null && user.isEmailVerified) {
            isUserLoggedIn()
        }

        binding.btnSignin.setOnClickListener {
            val email = binding.editTextEmail.editText?.text.toString().trim()
            val password = binding.editTextPassword.editText?.text.toString().trim()
            if (validateCredentials(email, password)) return@setOnClickListener
            signIn(email, password)

        }

        binding.txtSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.txtRecoveryPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoveryPasswordFragment)
        }


    }

    private fun isUserLoggedIn() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_nav_listaEjerciciosFragment)
        }
    }

    private fun validateCredentials(email: String, password: String): Boolean {

        return when {
            email.isEmpty() && password.isEmpty() -> {
                binding.editTextEmail.error = "El correo electrónico esta en blanco"
                binding.editTextPassword.error = "La contraseña esta en blanco"
                true
            }

            email.isEmpty() -> {
                binding.editTextEmail.error = "El correo electrónico esta en blanco"
                true
            }

            password.isEmpty() -> {
                binding.editTextPassword.error = "La contraseña esta en blanco"
                true
            }
            else -> {
                binding.editTextPassword.error = null
                false
            }

        }

    }

    private fun signIn(email: String, password: String) {
        viewModel.signIn(email, password).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignin.isEnabled = false
                }
                is Result.Success -> {
                    binding.btnSignin.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                    val user = FirebaseAuth.getInstance().currentUser
                    user?.reload()
                    if (user?.isEmailVerified == false) {
                        user.sendEmailVerification()
                        Toast.makeText(
                            requireContext(),
                            "Favor de verificar su email para continuar",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.editTextEmail.editText?.text = null
                        binding.editTextPassword.editText?.text = null
                    } else {
                        findNavController().navigate(R.id.action_loginFragment_to_nav_listaEjerciciosFragment)
                    }

                }
                is Result.Failure -> {

                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            binding.progressBar.visibility = View.GONE
                            binding.btnSignin.isEnabled = true
                            binding.editTextEmail.error = null
                            binding.editTextPassword.error = null
                        }, 2000 // value in milliseconds
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