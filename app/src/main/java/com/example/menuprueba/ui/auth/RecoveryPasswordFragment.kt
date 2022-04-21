package com.example.menuprueba.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.menuprueba.R
import com.example.menuprueba.databinding.FragmentLoginBinding
import com.example.menuprueba.databinding.FragmentRecoveryPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class RecoveryPasswordFragment : Fragment(R.layout.fragment_recovery_password) {

    private lateinit var binding: FragmentRecoveryPasswordBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecoveryPasswordBinding.bind(view)
        binding.btnRecoveryPassword.setOnClickListener{
            val email = binding.editTextEmail.editText?.text.toString().trim()
            if(validateEmail(email)) return@setOnClickListener
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            Toast.makeText(
                requireContext(),
                "Se ha enviado un correo para restablecer la contraseña",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_recoveryPasswordFragment_to_loginFragment)
        }
    }

    private fun validateEmail(email: String): Boolean {
        return when {
            email.isEmpty() -> {
                binding.editTextEmail.error = "El correo electrónico esta en blanco"
                true
            }

            else -> {
                binding.editTextEmail.error = null
                false
            }
        }
    }

}