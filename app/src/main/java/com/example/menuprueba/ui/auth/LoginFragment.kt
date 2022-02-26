package com.example.menuprueba.ui.auth


import android.os.Bundle
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
import java.util.regex.Pattern

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
        isUserLoggedIn()
        doLogin()
        goToSignUpPage()
    }

    private fun isUserLoggedIn() {
        firebaseAuth.currentUser?.let {
            findNavController().navigate(R.id.action_loginFragment_to_nav_rutinas)
        }
    }

    private fun doLogin() {
        binding.btnSignin.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            validateCredentials(email, password)
            signIn(email, password)
        }
    }

    private fun goToSignUpPage(){
        binding.txtSignup.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun validateCredentials(email: String, password: String) {
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +     //at least 1 digit
                    "(?=.*[a-z])" +     //at leat 1 lower case
                    "(?=.*[A-Z])" +     //at leat 1 upper case
                    "(?=.*[@#$%])" +    //at leats  special character
                    "(?=\\S+$)" +       //no white spaces
                    ".{4,}" +           //at lears 4 characters
                    "$"
        )

        if (email.isEmpty()) {
            binding.editTextEmail.error = "El correo electrónico esta en blanco"
            return
        }
        if (password.isEmpty()) {
            binding.editTextEmail.error = "La contraseña esta en blanco"
            return
        }
//        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
//            binding.editTextEmail.error = "Ingrese un correo electrónico válido"
//            return
//        }
//        if (!passwordRegex.matcher(password).matches()) {
//            binding.editTextEmail.error =
//                "La contraeña debe de contener al menos 4 caracteres, una mayuscula, una minuscula, un numero y un caracter especial"
//            return
//        }
    }

    private fun signIn(email: String, password: String) {
        viewModel.signIn(email, password).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSignin.isEnabled = false
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_nav_rutinas)
                }
                is Result.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSignin.isEnabled = true
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