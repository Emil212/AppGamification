package com.example.menuprueba.ui.auth


import android.os.Bundle
import android.view.View
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.menuprueba.R
import com.example.menuprueba.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        isUserLoggedIn()
        doLogin()

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
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.error = "Ingrese un correo electrónico válido"
            return
        }
        if (!passwordRegex.matcher(password).matches()) {
            binding.editTextEmail.error =
                "La contraeña debe de contener al menos 4 caracteres, una mayuscula, una minuscula, un numero y un caracter especial"
            return
        }
    }

    private fun signIn(email: String, password: String) {

    }

}