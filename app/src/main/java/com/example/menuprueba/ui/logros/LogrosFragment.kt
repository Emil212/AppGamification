package com.example.menuprueba.ui.logros

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.menuprueba.R
import com.example.menuprueba.core.Result
import com.example.menuprueba.data.remote.logros.LogrosDataSource
import com.example.menuprueba.databinding.FragmentLogrosBinding
import com.example.menuprueba.domain.logros.LogrosRepoImplement
import com.example.menuprueba.presentation.logros.LogrosViewModel
import com.example.menuprueba.presentation.logros.LogrosViewModelFactory

class LogrosFragment : Fragment(R.layout.fragment_logros) {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            LogrosViewModelFactory(LogrosRepoImplement(LogrosDataSource()))
        ).get(LogrosViewModel::class.java)
    }
    private lateinit var binding: FragmentLogrosBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLogrosBinding.bind(view)
        observeData()
    }

    fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    fun observeData() {
        viewModel.getInfoUser.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    showProgressBar()
                }
                is Result.Success -> {
                    hideProgressBar()
                    var info = result.data
                    Log.d("Prueba2", "$info")
                    Log.d("Prueba2", "${info.size}")
                    val textView = binding.textLogros
                    textView.text = "${info[0]}, tienes ${info[1]} puntos"
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
}