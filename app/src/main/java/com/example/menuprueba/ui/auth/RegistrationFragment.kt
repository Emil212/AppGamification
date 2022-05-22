package com.example.menuprueba.ui.auth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.menuprueba.R
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.remote.auth.AuthDataSource
import com.example.menuprueba.databinding.FragmentRegistrationBinding
import com.example.menuprueba.domain.auth.AuthRepoImpl
import com.example.menuprueba.presentation.auth.AuthViewModel
import com.example.menuprueba.presentation.auth.AuthViewModelFactory
import com.example.menuprueba.ui.rutinas.IOnBackPressed
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


class RegistrationFragment : Fragment(R.layout.fragment_registration), IOnBackPressed{

    private lateinit var binding: FragmentRegistrationBinding
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
        binding = FragmentRegistrationBinding.bind(view)
        signUp()
    }

    private fun signUp() {
        binding.btnSignUp.setOnClickListener {

            val username = binding.editTextUserName.editText?.text.toString().trim()
            val password = binding.editTextPassword.editText?.text.toString().trim()
            val confirmPassword = binding.editTextConfirmPassword.editText?.text.toString().trim()
            val email = binding.editTextEmail.editText?.text.toString().trim()

            if (validateUserData(
                    password,
                    confirmPassword,
                    username,
                    email
                )
            ) return@setOnClickListener

            createUser(email, password, username)

        }

    }

    private fun createUser(email: String, password: String, username: String) {
        viewModel.signUp(email, password, username).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignUp.isEnabled = false
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "El usuario se ha creado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)

                }
                is Result.Failure -> {
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            binding.progressBar.visibility = View.GONE
                            binding.btnSignUp.isEnabled = true
                            binding.editTextUserName.error=null
                            binding.editTextEmail.error=null
                            binding.editTextPassword.error=null
                            binding.editTextConfirmPassword.error=null
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

    private fun validateUserData(
        password: String,
        confirmPassword: String,
        username: String,
        email: String
    ): Boolean {

        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +     //at least 1 digit
                    "(?=.*[a-z])" +     //at leat 1 lower case
                    "(?=.*[A-Z])" +     //at leat 1 upper case
                    ".{6,}" +           //at leats 4 characters
                    "$"
        )


        if (password != confirmPassword) {
            binding.editTextConfirmPassword.error = "Las contraseñas no coinciden"
            return true
        }

        if (username.isEmpty()) {
            binding.editTextUserName.error = "El usuario esta vacio"
            return true
        }

        if (email.isEmpty()) {
            binding.editTextEmail.error = "El correo electronico esta vacio"
            return true
        }

        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.error = "Ingrese un correo electrónico válido"
            return true
        }

        if (password.isEmpty()) {
            binding.editTextPassword.error = "La contraseña esta vacia"
            return true
        }

        if (confirmPassword.isEmpty()) {
            binding.editTextConfirmPassword.error = "La contraseña esta vacia"
            return true
        }

        if (!passwordRegex.matcher(password).matches()) {
            binding.editTextPassword.error =
                "La contraseña debe de contener al menos 6 caracteres, una mayúscula, una minúscula y un número"
            return true
        }

        return false
    }
}